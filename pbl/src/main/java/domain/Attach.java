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
public class Attach {
	private String uuid;	//첨부파일ID * 유니버셜유니크아이디
	private String path;	//업로드 경로
	private boolean image; //이미지
	private String origin; //덮어쓰기방지
	private Long bno;	//게시글번호
	private Long rno;	//댓글번호
}
