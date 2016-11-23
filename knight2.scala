// Part 2 about finding a single tour for a board
//================================================

// copy any function you need from file knight1.scala

type Pos = (Int, Int)    // a position on a chessboard
type Path = List[Pos]    // a path...a list of positions


//(2a) Implement a first-function that finds the first
// element, say x, in the list xs where f is not None.
// In that case return f(x), otherwise none.


def first(xs: List[Pos], f: Pos => Option[Path]): Option[Path] = xs match {

  case Nil => None

  case (pos :: xs) => {
    if (f(pos) != None) {
      f(pos)
    }
    else {
      first(xs, f)
    }
  }

}

//(2b) Implement a function that uses the first-function for
// trying out onward moves, and searches recursively for an
// *open* tour on a dim * dim-board.

def is_legal(dim: Int, path: Path)(x: Pos): Boolean = {
  val newD =dim-1
  if(x._1 < 0 || x._2 < 0 || x._1 > newD || x._2 > newD || path.contains(x)) {
    false
  }
  else {
    true
  }
}

def legal_moves(dim: Int, path: Path, x: Pos): List[Pos]= {
  val c = x._1
  val y = x._2
  val possibles = List((c + 1, y + 2), (c + 2, y + 1), (c + 2, y - 1), (c + 1, y - 2), (c - 1, y - 2), (c - 2, y - 1),
    (c - 2, c + 1), (c - 1, y + 2))
  val unwanted = path.toSet
  possibles.filterNot(unwanted).filter(a => a._1 < dim && a._2 < dim)
}


def first_tour(dim: Int, path: Path): Option[Path] = {
  def moving( position: Pos ) : List[Pos] = {
    val possibles = List(Pair(2, 1), Pair(2, -1), Pair(-2, 1), Pair(-2, -1),
      Pair(1, 2), Pair(-1, 2), Pair(1, -2), Pair(-1, -2))
    possibles.map(move => Pair(position._1 + move._1, position._2 + move._2)).filter(is_legal(dim, path))
  }
  val moves = moving(path.head)
  if (path.length == dim*dim) {
    Some(path)
  }
  else {
    first(moves,i => first_tour(dim,i::path))
  }
}


first_tour(2,List((0,0)))