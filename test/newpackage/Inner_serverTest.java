/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import junit.framework.Assert;
import java.io.*;
import java.net.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Пользователь
 */
public class Inner_serverTest {
    
       String protocol;
       String host;
       int port ;
       String method;
       String resp ;
       String ping;
    
    public Inner_serverTest() {
        protocol = new String ("http://");
        host = new String ("localhost");
        port = Inner_server.port;
        method=new String ("/Ping");
        resp = new String ("\r\nHttpStatusCode.Ok(200)\r\n");
        String ping=new String ("/Ping");
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of Run method, of class Inner_server.
     */
    @Test
    public void testRun() throws Exception {
        
        String USER_AGENT = "Mozilla/5.0";
        System.out.println("Run");
        
        int port_n = Inner_server.port;
        int qu = 10;
       
         try {
             /*
              ExecutorService executor = Executors.newFixedThreadPool(5);
              Inner_server.server.setExecutor(executor);
              executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
             */ 
              Inner_server.Run(port_n, qu);
              
         } catch (Exception ex) {
             Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO review the generated test code and remove the default call to fail.
   
     // Client.send(ping);

        // создаем объект который отображает вышеописанный IP-адрес.
        String url = new String(protocol+host+":"+port+method); 
//      JOptionPane.showMessageDialog(null, "URL = "+url);
        
        URL obj = new URL(url);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
       // JOptionPane.showMessageDialog(null, "URL = "+obj.toString());
        
        con.setReadTimeout(20000);
        
        // optional default is GET
	con.setRequestMethod("GET");

	//add request header
	con.setRequestProperty("User-Agent", USER_AGENT);
        
       // TimeUnit.SECONDS.sleep(3);
        
        try {
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
        
	//print result
	System.out.println(response.toString());
        
        if (response.toString().equalsIgnoreCase(resp)) {  
            Assert.assertTrue(true);  
        } else { Assert.fail("The test case is a prototype."); } 
        
        } catch (Exception x) { Assert.assertTrue(true); }  
    }

    /**
     * Test of update method, of class Inner_server.
     */
    /*
    @Test
    public void testUpdate() {
        System.out.println("update");
        String reqs = "";
        String resp = "";
        Inner_server.update(reqs, resp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     * 
     */
}
