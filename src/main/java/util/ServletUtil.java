package util;

import java.io.BufferedReader;
import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;

public class ServletUtil {

	public static String getCorpoReq(HttpServletRequest request) throws IOException {
		BufferedReader br = request.getReader();
		String str, corpo = "";
		str = br.readLine();
		while (str != null) {
			corpo += str;
			str = br.readLine();
		}
		return corpo;
	}

}
