package domain;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@Data
public class Board {
	private Long bno;	//게시글번호
	private String title; //게시글 제목
	private String content;	//게시글 내용
	private String id;		//아이디
	private String regdate;		//작성일자
	private String moddate;		//수정일자
	private Integer cnt;	//조회수
}
