// copy any function you need from files knight1.scala and
// knight2.scala

import scala.annotation.tailrec

type Pos = (Int, Int)    // a position on a chessboard
type Path = List[Pos]    // a path...a list of positions

//(3a) Complete the function that calculates a list of onward
// moves like in (1b) but orders them according to the Warnsdorf’s
// rule. That means moves with the fewest legal onward moves
// should come first.

def is_legal(dim: Int, path: Path)(x: Pos): Boolean = {
  if (x._1<=dim-1 && x._1>=0 && x._2>=0 && x._2<=dim-1 && !path.contains(x)) true
  else false
}

def legal_moves(dim: Int, path: Path, x: Pos): List[Pos] = {
  val list = List((x._1+1, x._2+2),(x._1+2, x._2+1),(x._1+2, x._2-1),  (x._1+1, x._2-2),(x._1-1, x._2-2),(x._1-2, x._2-1),(x._1-2, x._2+1),(x._1-1, x._2+2))
  list.filter(is_legal(dim,path))
}


def ordered_moves(dim: Int, path: Path, x: Pos): List[Pos] = {
  legal_moves(dim,path,x).sortBy(c => legal_moves(dim,path,c).size)
}


@tailrec
def first(xs: List[Pos], f: Pos => Option[Path]): Option[Path] = xs match {
  case Nil => None

  case (s:: xs) =>
    val newF=f(s)
    if (newF.isDefined) {
      newF
    }
    else {
      first(xs, f)
    }

}


def first_closed_tour_heuristic(dim: Int, path: Path): Option[Path] = {
  val dim2= dim*dim
  val top = path.head
  if (path.size==dim2 && List((top._1+1, top._2+2),(top._1+2, top._2+1),
    (top._1+2, top._2-1),  (top._1+1, top._2-2),(top._1-1, top._2-2),
    (top._1-2, top._2-1),(top._1-2, top._2+1),
    (top._1-1,top._2+2)).contains(path.last)) {
    //need to return option
    Some(path)
  }
  else  {
    first(ordered_moves(dim,path,top),f => first_closed_tour_heuristic(dim,f::path))
  }

}

def first_tour_heuristic(dim: Int, path: Path): Option[Path] = {
  val dim2= dim*dim
  val top = path.head
  if (path.size>=dim2) {
    Some(path)
  }

  else {
    val moves = ordered_moves(dim,path,top)
    if (moves.nonEmpty)  first_tour_heuristic(dim,ordered_moves(dim,path,top).head::path)

    else first(ordered_moves(dim,path,top),f => first_tour_heuristic(dim,f::path))

  }

}

first_closed_tour_heuristic(5,List((0,0)))