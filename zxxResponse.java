package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;



public class zxxResponse {
	private String header(String... header) {
		String re = "";
		for (String string : header) {
			re = re + string + "\r\n";
		}
		return re;
	}
	
	private String body(String body) {
		return "\r\n" + body;
	}
	
	public zxxResponse(Socket s ,zxxRequest req) {
		// TODO Auto-generated constructor stub
		InputStream f = null;
		byte[] res = null;
		try {
			System.out.println(req.getRequestValue("url"));
			f = new FileInputStream(req.getRequestValue("url"));
			System.out.println("port has been used8");
			byte[] header = header("HTTP/1.1 200 OK").getBytes();
			byte[] body = body("").getBytes();
			byte[] bytes = new byte[1024];
			while (f.read(bytes)!=-1) {
				body = this.byteAppend(body, bytes);
			}
			
			
			System.out.println("port has been used9");
			
			res = this.byteAppend(header, body);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("port has been used3");
		}
		
		this.response(s, res);
	}
	
	private byte[] byteAppend(byte[] a, byte[] b) {
		byte[] c = new byte[a.length + b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b ,0 ,c ,a.length, b.length);
		return c;
	}
	
	
	public int response(Socket s, byte[] b) {
		OutputStream out = null;
		try {
			out = s.getOutputStream();
			out.write(b);
			out.flush();
			out.close();
			s.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 1;
	}
	public static void main(String[] args) throws IOException {  
    	
    }
	public static String now() {
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return df.format(day);
	}
}
