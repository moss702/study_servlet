package domain;

import java.util.Date;

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
public class Review {
	private Long rno;		 //리뷰번호
	private String content;  //리뷰내용
	private String regdate;    //작성일시
	private Integer rating;  //별점
	private String writer;   //작성자
	private Long pno; 		 //상품번호
}
