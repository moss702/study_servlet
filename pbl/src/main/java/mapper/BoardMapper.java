package mapper;

import java.util.List;

import domain.Board;

public interface BoardMapper {
	
	List<Board> list();

	List<Board> findBy(long bno);

	Board selectOne(long bno);

	void insert(Board board);
	
	
	
}
