package org.superbiz.test;

import java.io.File;
import java.io.StringReader;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class HomeControllerTest {

    @ArquillianResource
    private URL base;

    private Client client;

    @Deployment
    public static WebArchive createDeployment() {
        File[] files = Maven.resolver().loadPomFromFile("pom.xml")
                .importRuntimeDependencies().resolve().withTransitivity().asFile();
        return ShrinkWrap.create(WebArchive.class, "test.war").addPackages(true, "org.superbiz")
                .addAsLibraries(files).addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/views/home.jsp"), "/views/home.jsp");
    }

    @Before
    public void before() {
        this.client = ClientBuilder.newClient();
    }

    @Test @RunAsClient
    public void healthCheckTestOne() {
        WebTarget webTarget = this.client.target(this.base.toExternalForm());
        String html = webTarget.path("/app/home").request().get().readEntity(String.class);
        Document doc = Jsoup.parse(html);
        Element htmlH1 = doc.getElementById("printId");
        assertEquals("Hello World!!!", htmlH1.text());
    }

    @After
    public void after() {
        this.client.close();
    }
}
