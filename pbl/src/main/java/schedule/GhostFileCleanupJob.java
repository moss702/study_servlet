package schedule;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import controller.attach.UploadFile;
import domain.Attach;
import lombok.extern.slf4j.Slf4j;
import mapper.AttachMapper;
import util.MyBatisUtil;

@Slf4j
public class GhostFileCleanupJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		// 파일 인스턴스 생성 > 어제 날짜의 업로드 폴더
		File file = new File(UploadFile.UPLOAD_PATH, genYesterdayPath());
		log.info("{}, {}", file, file.exists());
		
		if(!file.exists() || !file.isDirectory()) {
			return;
		}

		List<File> fsListFiles = new ArrayList<>(Arrays.asList(file.listFiles()));
		SqlSession session = MyBatisUtil.getsqlSession();
		
		
		List<Attach> attachs = new ArrayList<>(session.getMapper(AttachMapper.class).selectYesterdayList());
//		log.info("attachs {}", attachs);
		log.info("======== attachs =========");
		attachs.forEach(a -> log.info("{}, ", a));
		List<Attach> thumbs = new ArrayList<>(attachs).stream().filter(Attach::isImage).map(Attach::toThumb).toList();
//		log.info("thumbs {}", thumbs);
		log.info("======== thumbs =========");
		thumbs.forEach(a -> log.info("{}, ", a));
		
		attachs.addAll(thumbs);
//		log.info("attachs+thumbs {}", attachs);
		log.info("======== attachs+thumbs =========");
		attachs.forEach(a -> log.info("{}, ", a));
		
		List<File> dbListFiles = attachs.stream().map(Attach::toFile).toList();
		log.info("======== dbListFiles (삭제하지 말아야할 파일) =========");
		dbListFiles.forEach(a -> log.info("{}, ", a));
		
//		List<File> dbListFiles = session.getMapper(AttachMapper.class).selectYesterdayList().stream().map(Attach::toFile).toList();
//		session.close();
		
		fsListFiles.removeAll(dbListFiles);
//		log.info("전부 지웟나요? {}", fsListFiles);
//		fsListFiles.forEach(f -> f.delete());

	}
	private String genYesterdayPath() {
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date().getTime() - 1000 * 60 * 60 * 24);
	}
	public static void main(String[] args) throws JobExecutionException {
		new GhostFileCleanupJob().execute(null);
	}

}


