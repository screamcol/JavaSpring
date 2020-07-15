package Lesson_4;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URL;
import java.security.ProtectionDomain;

public class Launcher {
    public static void main(String[] args) throws Exception {
        PrepareData.forcePrepareData();
        Server server = new Server(8189);

        ProtectionDomain domain = Launcher.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/lesson4");
        webAppContext.setWar(location.toExternalForm());

        server.setHandler(webAppContext);
        server.start();
        server.join();
    }
}
