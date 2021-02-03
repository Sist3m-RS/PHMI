/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import junit.framework.Assert;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.OutputStream;
import javax.xml.ws.spi.http.HttpExchange;
import java.io.IOException;
import com.sun.net.httpserver.HttpHandler;
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
public class ClientTest {
    
    public ClientTest() {
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
     * Test of send method, of class Client.
     */
    @Test
    public void testSend() throws Exception {
        System.out.println("send");
        String method = "/ping";
        try {
        Client.send(method);
        Assert.fail("The test case is a prototype.");
        }  catch (Exception x) { Assert.assertTrue(true); }
        // TODO review the generated test code and remove the default call to fail.
        
    }
    /**
     * Test of update method, of class Client.
     */
/*    
    @Test
    public void testUpdate() {
        System.out.println("update");
        String reqs = "";
        String resp = "";
        Client.update(reqs, resp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

*/
}
