package mapper;

import java.util.List;

import domain.Board;
import domain.dto.Criteria;

public interface BoardMapper {
	
	List<Board> list(Criteria cri);

	Board selectOne(long bno);

	void insert(Board board);
	
	void update(Board board);
	
	void delete(Long bno);	
	
	long getCount(Criteria cri);
	
	void increaseCnt(Long bno);
	
	void updateGrpMyself(Board board);
	
	void updateSeqIncrease(Board parent);
	
	void insertChild(Board board);
	
	int selectMaxSeq(Board parent);
}