package controller.endpoint;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import lombok.extern.slf4j.Slf4j;

@ServerEndpoint("/echo")
@Slf4j
public class EchoEndPoint {
	// 모든 연결된 세션을 저장할 Set (add remove)
	private static final Set<Session> SESSIONS = new CopyOnWriteArraySet<Session>();
	
	// 최소 3개의 메서드 정의
	
	// ========== 연결
	//세션 아이디값으로 사용자 구분 :세션의 크기 = 현재접속자 수
	@OnOpen
	public void onOpen(Session session) {
		SESSIONS.add(session);
		log.info("현재 접속자 수는 {}명 입니다.", SESSIONS.size());
	}
	// ========== 메시지 발송
	@OnMessage
	public void onMessage(String msg, Session session) throws IOException {
		log.info("{}", msg);
		for(Session s : SESSIONS) {
			s.getBasicRemote().sendText(session.getId() + " : " + msg);
		}
	}

	// ========== 종료
	@OnClose
	public void onClose(Session session) {
		SESSIONS.remove(session);
		log.info("현재 접속자 수는 {}명 입니다.", SESSIONS.size());
	}

}
