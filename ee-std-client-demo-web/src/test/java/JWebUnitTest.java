/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import net.sourceforge.jwebunit.util.TestingEngineRegistry;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adam
 */
public class JWebUnitTest {
    
    public JWebUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void prepare() {
     setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT);
      setBaseUrl("http://localhost:8080/ee-std-client-demo-web");
    }
    
   /** @Test
    public void testLoginpage(){
       beginAt("/");
     assertTitleEquals("Post");
      assertLinkPresent("login");
    clickLink("login");
      assertTitleEquals("Login");
    }
    @Test
    public void testRegistrationpage(){
       beginAt("/");
     assertTitleEquals("Post");
      assertLinkPresent("registration");
    clickLink("registration");
       assertTitleEquals("Registration");
    }
    
    @Test
    public void testSubscribtionpage(){
       beginAt("/");
    assertTitleEquals("Post");
   assertLinkPresent("subscribtion");
   clickLink("subscribtion");
      assertTitleEquals("Mail Page");
    }
    
    @Test
    public void testRulespage(){
      beginAt("/");
    assertTitleEquals("Post");
      assertLinkPresent("rules");
    clickLink("rules");
       assertTitleEquals("Regulamin");
    }**/
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
