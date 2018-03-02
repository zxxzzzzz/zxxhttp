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
			
			int count = 0;
			int total = 0;
			while ((count=f.read(bytes))!=-1) {
				total = count + total;
			}
			byte[] body2 = new byte[total];
			
			System.out.println("port has been used9");
			
			f.reset();
			int count2 = 0;
			int total2 = 0;
			while ((count2 = f.read(bytes))!=-1) {
				System.arraycopy(bytes, 0, body2, total2, bytes.length);
				total2 = total2 + count2;
			}
			System.out.println("port has been used10");
			System.arraycopy(bytes, 0, body2, total2, bytes.length);
			res = this.byteAppend(header, body);
			res = this.byteAppend(res, body2);
			
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
