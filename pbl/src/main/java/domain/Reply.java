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
public class Reply {
	private Long rno;			//댓글번호
	private String content;    //댓글내용
	private String id;		  //아이디
	private String regdate;  //작성일시
	private Long bno;		//게시글번호
	
}
