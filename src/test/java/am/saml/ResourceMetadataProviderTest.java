package am.saml;

import am.AbstractIntegrationTest;
import org.junit.Test;
import org.opensaml.saml2.metadata.EntityDescriptor;
import org.opensaml.saml2.metadata.provider.MetadataProvider;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.parse.XMLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ResourceMetadataProviderTest extends AbstractIntegrationTest {

  @Autowired
  @Qualifier("central-idp")
  private MetadataProvider metadataProvider;

  @Value("${central_idp.entity_id}")
  private String centralIdpEntityId;

  @Test
  public void test() throws MetadataProviderException, XMLParserException, ConfigurationException {
    EntityDescriptor entityDescriptor = (EntityDescriptor) metadataProvider.getMetadata();
    String entityID = entityDescriptor.getEntityID();
    assertEquals(centralIdpEntityId, entityID);
  }

  @Test(expected = MetadataProviderException.class)
  public void testException() throws MetadataProviderException, XMLParserException, ConfigurationException {
    ResourceMetadataProvider provider = new ResourceMetadataProvider(new ClassPathResource("bogus"));
    provider.doGetMetadata();
  }


}
