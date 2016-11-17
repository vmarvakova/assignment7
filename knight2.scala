// Part 2 about finding a single tour for a board
//================================================

// copy any function you need from file knight1.scala

type Pos = (Int, Int)    // a position on a chessboard 
type Path = List[Pos]    // a path...a list of positions


//(2a) Implement a first-function that finds the first 
// element, say x, in the list xs where f is not None. 
// In that case return f(x), otherwise none.

def first(xs: List[Pos], f: Pos => Option[Path]): Option[Path] = ...

//(2b) Implement a function that uses the first-function for
// trying out onward moves, and searches recursively for an 
// *open* tour on a dim * dim-board.

def first_tour(dim: Int, path: Path): Option[Path] = ...
 
