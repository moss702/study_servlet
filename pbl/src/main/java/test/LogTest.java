package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.ConsoleAppender;

public class LogTest {
	private static final Logger logger = LoggerFactory.getLogger(LogTest.class);
	public static void main(String[] args) {
		logger.debug("디버그 로그");
		logger.info("인포 로그");
		logger.warn("워닝 로그");
		logger.error("에러 로그");
		//로그레벨 : 기록을 남길때 등급
//		ConsoleAppender<E>
	}
}
