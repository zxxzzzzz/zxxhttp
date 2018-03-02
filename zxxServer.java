package base;
import java.io.FileInputStream;
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.net.ServerSocket;  
import java.net.Socket;
import java.util.Map;
import base.zxxResponse;
import base.zxxRequest;
public class zxxServer {
	ServerSocket myServerSocket;
	String basePath;
	int port;
    public zxxServer(int port) {
		// TODO Auto-generated constructor stub
    	try {
    		this.myServerSocket = new ServerSocket(port);
    		this.basePath = "e:";
    		this.port = port;
    		System.out.println("use port at "+ String.valueOf(port));
    		System.out.println("file base path:"+ this.basePath);
		} catch (Exception e) {
			System.out.println("port has been used");
		}
	}
    
    
    public void start() {
    	byte[] buf = null;
		while(true) {
    		try {
    			Socket s = this.myServerSocket.accept(); //获取客户端对象  
                InputStream in = s.getInputStream(); //IO操作流规律       
                buf=new byte[4096];
                int len=in.read(buf);  
                String request=new String(buf, 0, len);
                Runnable r = ()->{
                	this.response(s,request);
                };
                Thread thread =new Thread(r);
            	thread.start();
			} catch (Exception e) {
				System.out.println("port has been used2");
				// TODO: handle exception
			}
       }                
	}
    
    private void response(Socket s ,String request) {
    	
		
		zxxRequest req = new zxxRequest(request ,this.basePath); //basepath use for ensure url full path
		zxxResponse res = new zxxResponse(s,req);
		/*Map<String, String> map = zxxRequest.stringToMap(request);
		InputStream f=null;
		try {
			f = new FileInputStream(this.basePath + map.get("url"));
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(zxxResponse.now() +" "+ map.get("method") + " " + map.get("url"));
			System.out.println(basePath + map.get("url") + " file no exist");
		}
		OutputStream out=s.getOutputStream();  
		String response = zxxResponse.header("HTTP/1.1 200 OK",
				"name:fuck","Content-Type:text/html;charset=utf-8") + zxxResponse.body("");
        out.write(response.getBytes());
        byte[] bytes = new byte[1024];
        while (f.read(bytes)!=-1) {
			out.write(bytes);
		}
        out.flush();
        out.close();
        s.close();
        System.out.println(zxxResponse.now() +" "+ map.get("method") + " " + map.get("url")+" state:200 ok");*/
			
    }
   
    

	public static void main(String[] args) throws IOException {  
    	zxxServer zxx = new zxxServer(8090);
    	zxx.start();
    }
  
}  
