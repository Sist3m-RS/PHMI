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
public class InputTest {
    
     static int k=10;
     static BigDecimal Sum[]; 
     static int[] Mul;
     static  String serialize_json;
     static  String serialize_xml;
     
    public InputTest() {
        
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
     * Test of Request method, of class Input.
     */
    @Test
    public void testRequest_json() throws Exception {
        System.out.println("Request");
    
        Input instance = new Input(k,Sum,Mul);
        
        String expResult_j = new String ("{\"K\":\"10\",\"Sums\":\"[1.0,2.0,3.0]\",\"Muls\":\"[4,5,6]\"}");
        String result_j = instance.Request(serialize_json);

        if(!result_j.equalsIgnoreCase(expResult_j)){Assert.fail("The test case is a prototype.");}

    }
     @Test
    public void testRequest_xml() throws Exception {
        System.out.println("Request");
 
        Input instance = new Input(k,Sum,Mul);

        String expResult_x = new String ("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n<Input>\r\n<K>10</K>\r\n<Sums>\r\n<decimal>1.0</decimal>\r\n<decimal>2.0</decimal>\r\n<decimal>3.0</decimal>\r\n</Sums>\r\n<Muls>\r\n<int>4</int>\r\n<int>5</int>\r\n<int>6</int>\r\n</Muls>\r\n</Input>\r\n");
        String result_x = instance.Request(serialize_xml);
      
        if(!result_x.equalsIgnoreCase(expResult_x)){Assert.fail("The test case is a prototype.");}
    }
     @Test
    public void test_create_json() throws Exception {
        System.out.println("Create_json");
        try{
        Input instance = new Input("json","non-json");
        } 
        catch (Exception x) { Assert.assertTrue(true); }
     }
     
     @Test
    public void test__norm_create_json() throws Exception {
        System.out.println("Create_json");
        String expResult_j = new String ("{\"K\":\"10\",\"Sums\":\"[1.0,2.0,3.0]\",\"Muls\":\"[4,5,6]\"}");
        try{
        Input instance = new Input("json",expResult_j);
        Assert.assertTrue(true);
        } 
        catch (Exception x) { Assert.fail("Error of norm create - json."); }

    }
    
     @Test
    public void test_create_xml() throws Exception {
        System.out.println("Create_xml");
        try{
        Input instance = new Input("xml","non-xml");
        } 
        catch (Exception x) { Assert.assertTrue(true);  }

    }
      @Test
    public void test__norm_create_xml() throws Exception {
        System.out.println("Create_json");
        String expResult_x = new String ("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n<Input>\r\n<K>10</K>\r\n<Sums>\r\n<decimal>1.0</decimal>\r\n<decimal>2.0</decimal>\r\n<decimal>3.0</decimal>\r\n</Sums>\r\n<Muls>\r\n<int>4</int>\r\n<int>5</int>\r\n<int>6</int>\r\n</Muls>\r\n</Input>\r\n");
        try{
        Input instance = new Input("xml",expResult_x);
        Assert.assertTrue(true);
        } 
        catch (Exception x) { Assert.fail("Error of norm create - xml."); }

    }
     @Test
    public void test_create() throws Exception {
        System.out.println("Create");
        try{
        Input instance = new Input("Unknown_serilize","non-formatted-string");
        } 
        catch (Exception x) { Assert.assertTrue(true);  }

    }
}
