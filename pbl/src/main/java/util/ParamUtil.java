package util;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParamUtil {
	public static <T> T get(HttpServletRequest req, Class<T> clazz) {
		try {
			T t = clazz.getDeclaredConstructor().newInstance();
			
//			Method[] methods = clazz.getDeclaredMethods(); //내가 선언한 필드만 가져오기
//			for(Method m : methods) {
//				log.info(m.getName()); //메서드 이름
//				
//				if(m.getName().equals("setBno")) {
//					m.invoke(t, 100L);
//				}
//			}
			
			Field[] fields = clazz.getDeclaredFields();
			for(Field f : fields) {
				log.info("{}, {}", f.getType(), f.getName());
				String param = req.getParameter(f.getName());
				// 필드명 가져오고, req가 가져온거랑 같으냐 묻기. NN이라면? 무조건 본 값을 가져온다.
				
				if(param != null) {
					f.setAccessible(true); //필드의 접근제한자를 가시성있게(프라이빗>퍼블릭) 바꾼다. 이 포문안에 있을때만.
					Object o = convert(param, f.getType());
					f.set(t, o); 
					
				}
			}
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private static Object convert(String param, Class<?> type) {
		// int
		if(type == int.class || type == Integer.class) return Integer.parseInt(param);
		
		// long
		if(type == long.class || type == Long.class) return Long.parseLong(param);
		
		// boolean
		if(type == boolean.class || type == Boolean.class) return Boolean.parseBoolean(param);

		// enum
		if(type.isEnum()) return Enum.valueOf(type.asSubclass(Enum.class), param.toUpperCase());
		
		//string
		return param;
	}
	
	
//	public static void main(String[] args) {
////		Reply b = get(null,Reply.class);
//		Board b = get(null,Board.class);
//		log.info("{}",b);
//	}
}
