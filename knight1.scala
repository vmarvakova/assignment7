
type Pos = (Int, Int)    // a position on a chessboard
type Path = List[Pos]    // a path...a list of positions


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

assert(legal_moves(8, Nil, (2,2)) ==
  List((3,4), (4,3), (4,1), (3,0), (1,0), (0,1), (0,3), (1,4)))
assert(legal_moves(8, Nil, (7,7)) == List((6,5), (5,6)))
assert(legal_moves(8, List((4,1), (1,0)), (2,2)) ==
  List((3,4), (4,3), (3,0), (0,1), (0,3), (1,4)))
assert(legal_moves(8, List((6,6)), (7,7)) == List((6,5), (5,6)))

def count_tours(dim: Int, path: Path): Int = {
  def moving( position: Pos ) : List[Pos] = {
    val possibles = List(Pair(2, 1), Pair(2, -1), Pair(-2, 1), Pair(-2, -1),
      Pair(1, 2), Pair(-1, 2), Pair(1, -2), Pair(-1, -2))
    possibles.map(move => Pair(position._1 + move._1, position._2 + move._2)).filter(is_legal(dim, path))
  }
  val moves = moving(path.head)
  if (path.length == dim*dim) {
    1
  }
  else {
    (for (i <- moves) yield count_tours(dim,i::path)).sum
  }
}
count_tours(5,List((0,0)))

def enum_tours(dim: Int, path: Path): List[Path] = {
  def moving( position: Pos ) : List[Pos] = {
    val possibles = List(Pair(2, 1), Pair(2, -1), Pair(-2, 1), Pair(-2, -1),
      Pair(1, 2), Pair(-1, 2), Pair(1, -2), Pair(-1, -2))
    possibles.map(move => Pair(position._1 + move._1, position._2 + move._2)).filter(is_legal(dim, path))
  }

  val moves = moving(path.head)
  if (path.length == dim*dim) {
    List(path)
  }
  else {
    (for (i <- moves) yield enum_tours(dim,i::path)).flatten
  }
}

