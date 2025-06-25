package domain;


import org.apache.ibatis.type.Alias;

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
@Alias("attach")
public class Attach {
	private String uuid;	//첨부파일ID * 유니버셜유니크아이디
	private String path;	//업로드 경로
	private boolean image; //이미지
	private String origin; //덮어쓰기방지
	private Long bno;	//게시글번호
	private Long rno;	//댓글번호
	private int odr; //첨부파일 순서 * 인티저 기본값 null, 인트 기본값 0
	
	@Setter
	private String info;
	
	public String getInfo() {
		String[] strs = {"uuid=" + uuid, "path=" + path, "origin=" + origin};
		return String.join("&", strs);
	}

}
