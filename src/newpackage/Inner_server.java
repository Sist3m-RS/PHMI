/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import com.sun.net.httpserver.*;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.math.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.xml.sax.SAXException;
/**
 *
 * @author n.shushakov
 */
public class Inner_server {
    
    public static HttpServer server;
  // public static NewJFrame x1;
    static NewJFrame x1;
    static int port = 8080;
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
    
    public static void Run (int port_n, int qu) throws Exception {
        
       // HttpServer server = HttpServer.create();
        port=port_n;
        
        server = HttpServer.create();
        server.bind(new InetSocketAddress(port_n), qu);

        HttpContext context = server.createContext("/", new EchoHandler());
      //  context.setAuthenticator(new Auth());
        
  //      ExecutorService executor = Executors.newFixedThreadPool(5);
        server.setExecutor(null); //server.setExecutor(executor);
        server.start();
 //       executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
        
      //  JOptionPane.showMessageDialog(null, "Я запущен !");
    }

    static class EchoHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            
        //  StringBuilder builder = new StringBuilder();
            StringBuffer builder = new StringBuffer();
            
            //Получаем метод из строки адреса
            StringBuffer In_URI = new StringBuffer (exchange.getRequestURI().getPath());
           // JOptionPane.showMessageDialog(null, "Принят запрос, метод =  "+In_URI+" ");
            
            StringBuffer request_header = new StringBuffer ();

             int response_lenght=0;
             
             //Читаем заголовок запроса и выводим в поле
             
             Headers req_header = exchange.getRequestHeaders();
             for (String header : req_header.keySet()) {
               if (header.equalsIgnoreCase("Content-Length")){ //Если в запросе есть тело, то определяем его длину
                   response_lenght=Integer.parseInt(req_header.getFirst(header)); 
               }
               request_header.append("\r\n").append(header).append(":    ").append(req_header.getFirst(header)); //.append("\r\n")
            }
             request_header.append("\r\n");
             
             
             //Читаем тело запроса и выводим в поле
             
             InputStream in_body = exchange.getRequestBody();
             //Считываем тело запроса

             StringBuffer body_s_b=new StringBuffer();
             
             int ch=in_body.read();
             while (ch!=-1){
             body_s_b.append((char)ch);
             ch=in_body.read();
             }
            String body_s=new String (body_s_b);
             
            //Обновляем поля на форме 
            Inner_server.update(request_header.toString()+body_s,"");
            
// Это пинг
            if(In_URI.toString().equalsIgnoreCase(Inner_server.ping)) {
                
          // JOptionPane.showMessageDialog(null, "Это прост пинг !");
            
            StringBuffer response_header = new StringBuffer ();
            response_header.append("\r\nHttpStatusCode.Ok(200)\r\n");   
             // response_header.append(request_header);
            
            
            byte[] bytes = response_header.toString().getBytes("UTF-8");
            exchange.sendResponseHeaders(200, bytes.length);
            
            
            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
            
            Inner_server.update("",response_header.toString());
            
            }
           
// Это стоп
            if(In_URI.toString().equalsIgnoreCase(Inner_server.stop)) {
                
            StringBuffer response_header = new StringBuffer ();
            response_header.append("\r\nServer stopped\r\n");   
            // response_header.append(request_header);
        
            byte[] bytes = response_header.toString().getBytes("UTF-8");
            exchange.sendResponseHeaders(200, bytes.length);
            
 
            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
            
            Inner_server.update("",response_header.toString());
            
            Inner_server.server.stop(3);
            x1.use=false;    
            Inner_server.update("",response_header.toString());
            
            }
            
// Это запрос входных данных (GetInputData)
            if(In_URI.toString().equalsIgnoreCase(Inner_server.getinput)) {
                
          // JOptionPane.showMessageDialog(null, "Это прост пинг !");
            
            Inner_server.get1b=true;
            Inner_server.get1=new Input ();
            String Body;
                try {
                    Body=new String (Inner_server.get1.Request("json"));
                } catch (TransformerConfigurationException ex) {
                    Logger.getLogger(Inner_server.class.getName()).log(Level.SEVERE, null, ex);
                    Body=new String ("Error to serialize! Method: /GetInputData");
                } catch (TransformerException ex) {
                    Logger.getLogger(Inner_server.class.getName()).log(Level.SEVERE, null, ex);
                    Body=new String ("Error to serialize! Method: /GetInputData");
                }
                
            byte[] bytex = Body.toString().getBytes("UTF-8");
            int body_size=bytex.length;
            
            StringBuffer response = new StringBuffer ();
            response.append("\r\nHttpStatusCode.Ok(200)\r\n"); 
            response.append("Method:   ").append(exchange.getRequestURI().getPath()).append("\r\n");
            response.append("Content-Length: ").append(body_size).append("\r\n").append("\r\n");  
            response.append(Body);
         
            byte[] bytes = response.toString().getBytes("UTF-8");
            exchange.sendResponseHeaders(200, bytes.length);
            
 
            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
            
            Inner_server.update("",response.toString());
            
            }
            
// Это прием и сравнение ответа по задаче (WriteAnswer)
            if(In_URI.toString().equalsIgnoreCase(Inner_server.writeanswer)) {
                
          // JOptionPane.showMessageDialog(null, "Это прост пинг !");
                
            StringBuffer response = new StringBuffer ();
            StringBuffer Body;
            Body=new StringBuffer ("\r\nОбъект (ответ по задаче - Output) корректно принят !\r\n");
               
            try {
                    Inner_server.get11=new Output ("json",body_s);
                } catch (SAXException ex) {
                    Logger.getLogger(Inner_server.class.getName()).log(Level.SEVERE, null, ex);
                    Body=new StringBuffer ("Error SAXException to serialize! Method: /WriteAnswer");
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(Inner_server.class.getName()).log(Level.SEVERE, null, ex);
                    Body=new StringBuffer ("Error ParserConfigurationException to serialize! Method: /WriteAnswer");
                }
            
            if(Inner_server.get1b) {         // Если до этого был запрос входных данных по задаче
          //  Inner_server.get11=new Output (get1);
               Output test = new Output (get1);
                    try { 
                            Body.append("Вы прислали:").append(body_s).append("\r\nВерный ответ:\r\n\r\n").append(test.Respect("json"));
                    } catch (TransformerConfigurationException ex) {
                        Logger.getLogger(Inner_server.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (TransformerException ex) {
                        Logger.getLogger(Inner_server.class.getName()).log(Level.SEVERE, null, ex);
                    }
             
            Inner_server.get1b=false;
           
            } 
            else { Body.append("\r\nВы не запросили входных данных, чтоб спрашивать ответ !\r\n"); } 
  
            
            byte[] bytex = Body.toString().getBytes("UTF-8");
            int body_size=bytex.length;
            
            response.append("\r\n\r\nHttpStatusCode.Ok(200)\r\n"); 
            response.append("Method:   ").append(exchange.getRequestURI().getPath()).append("\r\n");
            response.append("Content-Length: ").append(body_size).append("\r\n").append("\r\n");  
            response.append(Body);
              
            byte[] bytes = response.toString().getBytes("UTF-8");
            exchange.sendResponseHeaders(200, bytes.length);

            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
          
            Inner_server.update("",response.toString());
            
            }  
            
// Это прием входных данных (PostInputData)
            if(In_URI.toString().equalsIgnoreCase(Inner_server.postinput)) {
                
          // JOptionPane.showMessageDialog(null, "Это прост пинг !");
         //  StringBuffer response = new StringBuffer ();    
            StringBuffer Body;
            Body=new StringBuffer ("\r\nОбъект (входные данные по задаче - Input) корректно  принят !\r\n");
            
                try {
                    Inner_server.post1=new Input ("json",body_s);
                    
                } catch (SAXException ex) {        // Обрабатываем 1 исключение, если оно есть
                    
                    Logger.getLogger(Inner_server.class.getName()).log(Level.SEVERE, null, ex);
                    Body=new StringBuffer ("Error SAXException to serialize by create Input ! Method: /PostInputData");
                    
                    Inner_server.post1b=false; 

                    byte[] bytex = Body.toString().getBytes("UTF-8");
                    int body_size=bytex.length;

                    StringBuffer response = new StringBuffer ();
                    response.append("\r\nHttpStatusCode.Ok(200)\r\n"); 
                    response.append("Method:   ").append(exchange.getRequestURI().getPath()).append("\r\n");
                    response.append("Content-Length: ").append(body_size).append("\r\n").append("\r\n");  
                    response.append(Body);

                    byte[] bytes = response.toString().getBytes("UTF-8");
                    exchange.sendResponseHeaders(200, bytes.length);


                    OutputStream os = exchange.getResponseBody();
                    os.write(bytes);
                    os.close();

                    Inner_server.update("",response.toString());
                    
                } catch (ParserConfigurationException ex) { // Обрабатываем 2 исключение, если оно есть
                    
                    Logger.getLogger(Inner_server.class.getName()).log(Level.SEVERE, null, ex);
                    Body=new StringBuffer ("Error ParserConfigurationException to serialize by create Input ! Method: /PostInputData");
                    
                    Inner_server.post1b=false; 
               
                    byte[] bytex = Body.toString().getBytes("UTF-8");
                    int body_size=bytex.length;

                    StringBuffer response = new StringBuffer ();
                    response.append("\r\nHttpStatusCode.Ok(200)\r\n"); 
                    response.append("Method:   ").append(exchange.getRequestURI().getPath()).append("\r\n");
                    response.append("Content-Length: ").append(body_size).append("\r\n").append("\r\n");  
                    response.append(Body);

                    byte[] bytes = response.toString().getBytes("UTF-8");
                    exchange.sendResponseHeaders(200, bytes.length);


                    OutputStream os = exchange.getResponseBody();
                    os.write(bytes);
                    os.close();

                    Inner_server.update("",response.toString());
                }
                
            // Нормальное развитие событий
                
            Inner_server.post1b=true; 
               
            byte[] bytex = Body.toString().getBytes("UTF-8");
            int body_size=bytex.length;
            
            StringBuffer response = new StringBuffer ();
            response.append("\r\nHttpStatusCode.Ok(200)\r\n"); 
            response.append("Method:   ").append(exchange.getRequestURI().getPath()).append("\r\n");
            response.append("Content-Length: ").append(body_size).append("\r\n").append("\r\n");  
            response.append(Body);
         
            byte[] bytes = response.toString().getBytes("UTF-8");
            exchange.sendResponseHeaders(200, bytes.length);
            
 
            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
            
            Inner_server.update("",response.toString());
            
            }
            
// Это отдача ответа по задаче (GetAnswer)
            if(In_URI.toString().equalsIgnoreCase(Inner_server.getanswer)) {
                
          // JOptionPane.showMessageDialog(null, "Это прост пинг !");
                
            StringBuffer response = new StringBuffer ();
            String Body;
            
            if(Inner_server.post1b) {         // Если до этого был прием входных данных по задаче
            Inner_server.post11=new Output (post1);
                try {
                    Body=new String (Inner_server.post11.Respect("json"));
                } catch (TransformerConfigurationException ex) {
                    Logger.getLogger(Inner_server.class.getName()).log(Level.SEVERE, null, ex);
                    Body=new String ("Error TransformerConfigurationException to serialize! Method: /GetAnswer");
                } catch (TransformerException ex) {
                    Logger.getLogger(Inner_server.class.getName()).log(Level.SEVERE, null, ex);
                    Body=new String ("Error TransformerException to serialize! Method: /GetAnswer");
                }
            Inner_server.get1b=false;
            
            } 
            else { Body=new String ("\r\nВы не запросили входных данных, чтоб спрашивать ответ !\r\n"); } 
            
            byte[] bytex = Body.toString().getBytes("UTF-8");
            int body_size=bytex.length;
            
            response.append("\r\nHttpStatusCode.Ok(200)\r\n"); 
            response.append("Method:   ").append(exchange.getRequestURI().getPath()).append("\r\n");
            response.append("Content-Length: ").append(body_size).append("\r\n").append("\r\n");  
            response.append(Body);
              
            
            byte[] bytes = response.toString().getBytes("UTF-8");
            exchange.sendResponseHeaders(200, bytes.length);
            
 
            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
          
            Inner_server.update("",response.toString());
            
            }      
        }
    }
    
    static void update (String reqs, String resp) {
       x1.update_server(reqs, resp);
    }

    
}
