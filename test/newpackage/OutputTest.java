/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import junit.framework.Assert;
import java.math.*;
import java.util.*;
import javax.swing.JOptionPane;

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
public class OutputTest {
    
     static int k=10;
     static BigDecimal Sum[]; 
     static int[] Mul;
     static  String serialize_json;
     static  String serialize_xml;
     static Input in;
     
    public OutputTest() {
        
        Sum=new BigDecimal [3];
        Sum[0]=BigDecimal.valueOf(1.0);
        Sum[1]=BigDecimal.valueOf(2.0);
        Sum[2]=BigDecimal.valueOf(3.0);
        Mul= new int [3];
        Mul[0]=4;
        Mul[1]=5;
        Mul[2]=6;
        serialize_json = "json";
        serialize_xml = "xml";
        in = new Input(k,Sum,Mul);
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
     * Test of Respect method, of class Output.
     */
   @Test
    public void testRequest_json() throws Exception {
        System.out.println("Respect");

        Output instance = new Output (in);
        
        String expResult_j = new String ("{\"SumResult\":60.00,\"MulResult\":120,\"SortedInputs\":\"[1.0,2.0,3.0,4.0,5.0,6.0]\"}");
        String result_j = instance.Respect(serialize_json);

        if(!result_j.equalsIgnoreCase(expResult_j)){ Assert.fail("The test case is a prototype."); }

    }
     @Test
    public void testRequest_xml() throws Exception {
        System.out.println("Respect");
 
        Output instance = new Output (in);

        String expResult_x = new String ("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n<Output>\r\n<SumResult>60.0</SumResult>\r\n<MulResult>120</MulResult>\r\n<SortedInputs>\r\n<decimal>1.0</decimal>\r\n<decimal>2.0</decimal>\r\n<decimal>3.0</decimal>\r\n<decimal>4.0</decimal>\r\n<decimal>5.0</decimal>\r\n<decimal>6.0</decimal>\r\n</SortedInputs>\r\n</Output>\r\n");
        String result_x = instance.Respect(serialize_xml);
      
        if(!result_x.equalsIgnoreCase(expResult_x)){ Assert.fail("The test case is a prototype."); }
    }
     @Test
    public void test_create_json() throws Exception {
        System.out.println("Create_json");
        try{
        Output instance = new Output("json","non-json");
        } 
        catch (Exception x) { Assert.assertTrue(true);  }

    }
      @Test
    public void test__norm_create_json() throws Exception {
        System.out.println("Create_json");
        String expResult_j = new String ("{\"SumResult\":60.00,\"MulResult\":120,\"SortedInputs\":\"[1.0,2.0,3.0,4.0,5.0,6.0]\"}");
        try{
        Output instance = new Output("json",expResult_j);
        Assert.assertTrue(true);
        } 
        catch (Exception x) { Assert.fail("Error of norm create - json."); }

    }
     @Test
    public void test_create_xml() throws Exception {
        System.out.println("Create_xml");
        try{
        Output instance = new Output("xml","non-xml");
        } 
        catch (Exception x) { Assert.assertTrue(true);  }

    }
      @Test
    public void test__norm_create_xml() throws Exception {
        System.out.println("Create_json");
        String expResult_x = new String ("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n<Output>\r\n<SumResult>60.0</SumResult>\r\n<MulResult>120</MulResult>\r\n<SortedInputs>\r\n<decimal>1.0</decimal>\r\n<decimal>2.0</decimal>\r\n<decimal>3.0</decimal>\r\n<decimal>4.0</decimal>\r\n<decimal>5.0</decimal>\r\n<decimal>6.0</decimal>\r\n</SortedInputs>\r\n</Output>\r\n");
         try{
        Output instance = new Output("xml",expResult_x);
        Assert.assertTrue(true);
        } 
        catch (Exception x) { Assert.fail("Error of norm create - xml."); }

    }
     @Test
    public void test_create() throws Exception {
        System.out.println("Create");
        try{
        Output instance = new Output("Unknown_serilize","non-formatted-string");
        } 
        catch (Exception x) { Assert.assertTrue(true); }

    }
}
