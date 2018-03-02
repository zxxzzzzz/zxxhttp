package base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class zxxRequest {
	private Map<String, String> map;
	private Map<String,String> stringToMap(String request){
		Map<String, String> map = new HashMap<>();
		String a[] = request.split("\r\n");
		map.put("method", a[0].split(" ")[0]);
		map.put("url", a[0].split(" ")[1]);
		map.put("http", a[0].split(" ")[2]);
		for (String string : a) {
			String t[] = string.split(": ");
			if(t.length == 2) {
				map.put(t[0], t[1]);
			}
		}
		return map;	
	}
	
	public zxxRequest(String request ,String basepath) {
		// TODO Auto-generated constructor stub
		this.map = this.stringToMap(request);
		this.map.replace("url", "e:\\1.html");
		//(basepath + this.map.get("url")).replace("/", "\\")
	} 
	public String getRequestValue(String key) {
		return this.map.get(key);
	}
	public static void main(String[] args) throws IOException {  
    	
    }
	
}
