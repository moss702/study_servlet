package util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordEncoder {

    public static String encode(String rawPassword) {
//        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
        return BCrypt.withDefaults().hashToString(10, rawPassword.toCharArray());
        //몇번 암호화 할지(단계높이기). 라이브러리 수정은 불가하니 그냥 숫자 때려넣어준다.
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return result.verified;
    }
    
    public static void main(String[] args) {
		String origin = "평문";
		String encoded = encode(origin);
		String encoded2 = encode(origin);
		String encoded3 = encode(origin);
		
		System.out.println(encoded);
		System.out.println(encoded2);
		System.out.println(encoded3);
		
		//--------------------------------
		
		String[] encodeds = {
				"$2a$04$VP9REwpRS2igiIB3vEyk7.foPGQ76Gxapc1jjSkz65TDg6a3UFubm",
				"$2a$04$vMuVAOezAsdd1xbfovMZkeGubOWMv2ivZT5otS5LFITWDFVXNnPUO",
				"$2a$04$AWH1j3Sv5eABwCwKafOGRudPUPjRLxYH/1YSc8Hm4l6TFDmwWxKBa"				
		};
		
		System.out.println(matches("평문", encodeds[0]));
						//(matches("rawPassword", 암호화된 문구));
    }
}