package am.control;

import org.junit.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.ui.ModelMap;

import static org.junit.Assert.*;

public class MappingsControllerTest {

  private MappingsController subject = new MappingsController();

  @Test
  public void testIndex() throws Exception {
    String view = subject.mappings(null, null);
    assertEquals("index", view);
  }

  @Test
  public void testLanding() throws Exception {
    String view = subject.mappings(new TestingAuthenticationToken("principal", "N/A"), new ModelMap());
    assertEquals("mappings", view);

  }
}
