package controller.attach;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import domain.Attach;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import util.S3Util;

@WebServlet("/upload")
@MultipartConfig(location = "d:/upload/tmp",
	maxRequestSize = 50 * 1024 * 1024, //한번의 요청당 최대 파일 크기 
	maxFileSize = 10 * 1024 * 1024, //파일 하나당 최대 크기
	fileSizeThreshold = 10 * 1024 * 1024) // 이 크기를 넘어가면 location위치에 buffer를 기록함
@Slf4j
public class UploadFile extends HttpServlet {
	public final static String UPLOAD_PATH = "d:/upload/files";
	//전역함수로 올렷음. 다른곳에서 호출할때 UploadFile.UPLOAD_PATH 하면됨

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/uploadForm.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//업로드된 파일 처리
		Collection<Part> parts = req.getParts();

		
		//첨부파일 리스트 생성
		List<Attach> attachs = new ArrayList<Attach>();
		
		int odr = 0;
		
		for(Part part : parts) {
			if(part.getSize() == 0) {
				continue; //무시하고 반복해라
			}
//			String name = part.getName();
//			Collection<String> headers = part.getHeaderNames();
//			String contentDisposition = part.getHeader("content-disposition");
			Long fileSize = part.getSize();
			String origin = part.getSubmittedFileName();
			String contentType = part.getHeader("content-type");
			
			part.write(UPLOAD_PATH + "/" + origin);
			
			//ext 확장자 추출
			int idx = origin.lastIndexOf("."); //못찾으면 -1 출력
			String ext = "";
			if (idx >= 0) { //확장자가 존재함
				ext = origin.substring(idx);
			}
			
			//UUID 생성
			UUID uuid = UUID.randomUUID();
			String fileName = uuid + ext;
			
			//이미지 여부 확인
			boolean image = contentType.startsWith("image");
			
			String path = genPath();
			
			//날짜별 폴더 생성
			String realPath = UPLOAD_PATH + "/" + path + "/";
			File file = new File(realPath);
			if(!file.exists()) {
				file.mkdirs();
			}
//			log.info(realPath + fileName);
			part.write(realPath + fileName);
			
			// 이녀석이 key가 된다
			S3Util.upload(part, "upload/" + path + "/" + fileName);
			
			
			//첨부파일이 이미지인 경우, 썸네일 생성
			if(image) {
				try {
					File thumb = new File(realPath + "t_" + fileName);
					Thumbnails.of(new File(realPath + fileName)).size(150, 150).toFile(thumb);
					S3Util.upload(thumb, "upload/" + path + "/t_" + fileName);
				}
				catch (Exception e) {
					//이미지썸네일레이터를 이용해서 썸네일 만들려고 했는데 안되면?
					// 그냥 너는 이제 이미지가 아닌것이다.
					image = false;
				}
			}

			log.info("{} :: {} :: {} :: {}", fileSize, origin, contentType, ext);
			attachs.add(Attach.builder()
					.uuid(fileName)
					.origin(origin)
					.image(image)
					.path(path)
					.odr(odr++)
					.size(fileSize)
				.build());
		}
//		resp.sendRedirect("upload");
		//비동기로 할거니까 응답을 JSON으로 만들어준다
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().print(new Gson().toJson(attachs));
	}
	
//	private String genPath() {
//		return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
//	}
	private String genPath() {
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date().getTime() - 1000 * 60 * 60 * 24);
	}	
	
}
