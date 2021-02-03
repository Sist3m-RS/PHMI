/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Пользователь
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({newpackage.ClientTest.class, newpackage.InputTest.class, newpackage.Inner_serverTest.class,newpackage.NewJFrameTest.class, newpackage.OutputTest.class})
public class NewpackageSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
