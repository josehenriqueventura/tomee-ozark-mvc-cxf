package org.superbiz;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.View;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Controller
@Path("home")
public class HomeController {

    @Inject
    private Models models;

    @GET
    @View("home.jsp")
    public void homePage() {
        models.put("printMessage","Hello World!!!");
    }
}