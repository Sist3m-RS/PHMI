/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import junit.framework.Assert;
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
public class NewJFrameTest {
    
    public NewJFrameTest() {
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
     * Test of update_server method, of class NewJFrame.
     */
/*    
    @Test
    public void testUpdate_server() {
        System.out.println("update_server");
        String reqs = "";
        String resp = "";
        NewJFrame instance = new NewJFrame();
        instance.update_server(reqs, resp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    /**
     * Test of update_client method, of class NewJFrame.
     */
/*
    @Test
    public void testUpdate_client() {
        System.out.println("update_client");
        String reqs = "";
        String resp = "";
        NewJFrame instance = new NewJFrame();
        instance.update_client(reqs, resp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    /**
     * Test of main method, of class NewJFrame.
     */
    @Test
    public void testMain() {
        try{
        System.out.println("main");
        String[] args = null;
        NewJFrame.main(args);
        Assert.assertTrue(true); 
        }
        // TODO review the generated test code and remove the default call to fail.
        catch (Exception ex) { Assert.fail("The test case is a prototype."); }
    }
}
