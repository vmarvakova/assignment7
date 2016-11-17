// Part 1 about finding and counting Knight's tours
//==================================================

type Pos = (Int, Int)    // a position on a chessboard 
type Path = List[Pos]    // a path...a list of positions

//(1a) Complete the function that tests whether the position 
// is inside the board and not yet element in the path.

def is_legal(dim: Int, path: Path)(x: Pos): Boolean = ...


//(1b) Complete the function that calculates for a position 
// all legal onward moves that are not already in the path. 
// The moves should be ordered in a "clockwise" order.
 
def legal_moves(dim: Int, path: Path, x: Pos): List[Pos] = ...

//assert(legal_moves(8, Nil, (2,2)) == 
//  List((3,4), (4,3), (4,1), (3,0), (1,0), (0,1), (0,3), (1,4)))
//assert(legal_moves(8, Nil, (7,7)) == List((6,5), (5,6)))
//assert(legal_moves(8, List((4,1), (1,0)), (2,2)) == 
//  List((3,4), (4,3), (3,0), (0,1), (0,3), (1,4)))
//assert(legal_moves(8, List((6,6)), (7,7)) == List((6,5), (5,6)))


//(1c) Complete the two recursive functions below. 
// They exhaustively search for open tours starting from the 
// given path. The first function counts all possible open tours, 
// and the second collects all open tours in a list of paths.

def count_tours(dim: Int, path: Path): Int = ...

def enum_tours(dim: Int, path: Path): List[Path] = ...


