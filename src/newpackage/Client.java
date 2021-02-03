/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

//import com.sun.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;
import javax.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.math.*;
import java.util.*;
import org.xml.sax.SAXException;
import javax.swing.JOptionPane;
/**
 *
 * @author Пользователь
 */
public class Client {
    
    static String protocol = new String ("http://");
    static String host = new String ("localhost");; //"127.0.0.1"
    static int port = 8080;
    
  // public static NewJFrame x1;
    static NewJFrame x1;
    //Методы
    static String ping=new String ("/Ping");
    static String stop=new String ("/Stop");
    //для клиента
    static String getinput=new String ("/GetInputData");
    static Input get1;
    static boolean get1b=false;
    static String writeanswer=new String ("/WriteAnswer");
    static Output get11;
    //для самого сервера
    static String postinput=new String ("/PostInputData");
    static Input post1;
    static boolean post1b=false;
    static String getanswer=new String ("/GetAnswer");
    static Output post11;
    static String USER_AGENT = "Mozilla/5.0";
    
    public static void send (String method ) throws Exception {
        
       protocol = new String ("http://");
       host = new String ("localhost"); //"127.0.0.1"
       port = Inner_server.port;
       
        // создаем объект который отображает вышеописанный IP-адрес.
        String url = new String(protocol+host+":"+port+method); 
       // JOptionPane.showMessageDialog(null, "URL = "+url);

//Это пинг или стоп - простые GET Запросы
        if((method.equalsIgnoreCase(ping)||(method.equalsIgnoreCase(stop)))){
  
        URL obj = new URL(url);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
       // JOptionPane.showMessageDialog(null, "URL = "+obj.toString());
        
        // optional default is GET
	con.setRequestMethod("GET");

	//add request header
	con.setRequestProperty("User-Agent", USER_AGENT);

        //Считываем тело ответа    
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
     // response.append("\r\n");

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine).append("\r\n");
        }
        in.close();
           
        System.out.println(response.toString());
        
        //Считываем тело ответа
        int beg=0;
        StringBuffer response_body = new StringBuffer();
        if(response.indexOf("\r\n\r\n")!=-1){
            beg=response.indexOf("\r\n\r\n");
        response_body.append(response.substring(beg+4));    
        }
         // JOptionPane.showMessageDialog(null, "Тело ответа:  "+response_body.toString());
         
       // JOptionPane.showMessageDialog(null, "Ответ = "+response.toString());
        
         Client.update("\r\n"+con.getRequestMethod()+"  "+obj.getProtocol()+"  "+obj.toURI()+"\r\n"+response_body+"\r\n", response.toString());
         con.disconnect();
         
        } 
        
// Прием входных данных - GetInputData       
        if (method.equalsIgnoreCase(getinput)) {
            
        URL obj = new URL(url);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
       // JOptionPane.showMessageDialog(null, "URL = "+obj.toString());
        
        // optional default is GET
	con.setRequestMethod("GET");

	//add request header
	con.setRequestProperty("User-Agent", USER_AGENT);

        //Считываем тело ответа    
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
     // response.append("\r\n");

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine).append("\r\n");
        }
        in.close();
           
        Client.update("\r\n"+con.getRequestMethod()+"  "+obj.getProtocol()+"  "+obj.toURI()+"\r\n", response.toString()); //+"\r\n" 
        System.out.println(response.toString());
        
        //Считываем тело ответа
        int beg=0;
        StringBuffer response_body = new StringBuffer();
        if(response.indexOf("\r\n\r\n")!=-1){
            beg=response.indexOf("\r\n\r\n");
        response_body.append(response.substring(beg+4));    
        }
         // JOptionPane.showMessageDialog(null, "Тело ответа:  "+response_body.toString());
            
            get1=new Input("json",response_body.toString());
            get1b=true;
            
            con.disconnect();
            
        }
        
// Отдача ответа по задаче - выходных данных - WriteAnswer      
        if (method.equalsIgnoreCase(writeanswer)) {   
            if(get1b){
            get11=new Output(get1);
            byte[] data = null;
            String url_body = new String ("\r\n\r\n"+get11.Respect("json"));
            
                URL obj = new URL(url);
		//HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                con.setDoInput(true);
                
                //add reuqest header
                con.setRequestProperty("User-Agent", USER_AGENT);
                con.setRequestProperty("Content-Length", "" + Integer.toString(url_body.getBytes().length));
                
                OutputStream os = con.getOutputStream();
                data = url_body.getBytes("UTF-8");
                os.write(data);
                data = null;
             
                con.connect();
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post body : " + url_body);
		System.out.println("Response Code : " + responseCode);

                
		BufferedReader in = new BufferedReader(
		new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
                response.append("\r\n");
		while ((inputLine = in.readLine()) != null) {
		response.append(inputLine).append("\r\n");
		}
		in.close();

                System.out.println(response.toString());
        
               //Считываем тело ответа
                int beg=0;
                StringBuffer response_body = new StringBuffer();
                if(response.indexOf("\r\n\r\n")!=-1){
                    beg=response.indexOf("\r\n\r\n");
                response_body.append(response.substring(beg+4));    
                }
                // JOptionPane.showMessageDialog(null, "Тело ответа:  "+response_body.toString());
         
		//print result
		System.out.println(response.toString());
                Client.update("\r\n"+con.getRequestMethod()+"  "+obj.getProtocol()+"  "+obj.toURI()+"\r\n"+url_body+"\r\n", response.toString());

            } else { JOptionPane.showMessageDialog(null, "Вы не запрашивали (входных данных - класс Input методом /GetInput) условий задачи ! Нам нечего передавать !"); }
            get1b=false;
        }
        
// Отправка входных данных по задаче - PostInputData      
        if (method.equalsIgnoreCase(postinput)) {   
     
            post1=new Input();
            byte[] data = null;
            String url_body = new String ("\r\n\r\n"+post1.Request("json"));
            
                URL obj = new URL(url);
		//HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                con.setDoInput(true);
                
                //add reuqest header
                con.setRequestProperty("User-Agent", USER_AGENT);
                con.setRequestProperty("Content-Length", "" + Integer.toString(url_body.getBytes().length));
                
                OutputStream os = con.getOutputStream();
                data = url_body.getBytes("UTF-8");
                os.write(data);
                data = null;
             
                con.connect();
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post body : " + url_body);
		System.out.println("Response Code : " + responseCode);

                
		BufferedReader in = new BufferedReader(
		new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
                response.append("\r\n");
		while ((inputLine = in.readLine()) != null) {
		response.append(inputLine).append("\r\n");
		}
		in.close();

                System.out.println(response.toString());
        
               //Считываем тело ответа
                int beg=0;
                StringBuffer response_body = new StringBuffer();
                if(response.indexOf("\r\n\r\n")!=-1){
                    beg=response.indexOf("\r\n\r\n");
                response_body.append(response.substring(beg+4));    
                }
                // JOptionPane.showMessageDialog(null, "Тело ответа:  "+response_body.toString());
         
		//print result
		System.out.println(response.toString());
                Client.update("\r\n"+con.getRequestMethod()+"  "+obj.getProtocol()+"  "+obj.toURI()+"\r\n"+url_body+"\r\n", response.toString());

          
            get1b=false;
        }
        
// Прием ответа по задаче - GetAnswer      
        if (method.equalsIgnoreCase(getanswer)) {
            
        URL obj = new URL(url);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
       // JOptionPane.showMessageDialog(null, "URL = "+obj.toString());
        
        // optional default is GET
	con.setRequestMethod("GET");

	//add request header
	con.setRequestProperty("User-Agent", USER_AGENT);

        //Считываем тело ответа    
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
     // response.append("\r\n");

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine).append("\r\n");
        }
        in.close();
           
        Client.update("\r\n"+con.getRequestMethod()+"  "+obj.getProtocol()+"  "+obj.toURI()+"\r\n", response.toString()); //+"\r\n" 
        System.out.println(response.toString());
        
        //Считываем тело ответа
        int beg=0;
        StringBuffer response_body = new StringBuffer();
        if(response.indexOf("\r\n\r\n")!=-1){
            beg=response.indexOf("\r\n\r\n");
        response_body.append(response.substring(beg+4));    
        }
         // JOptionPane.showMessageDialog(null, "Тело ответа:  "+response_body.toString());
            
            post11=new Output("json",response_body.toString());
            post1b=true;
            
            con.disconnect();
            
        }
    }     
    
     static void update (String reqs, String resp) {
       x1.update_client(reqs, resp);
    }
}
