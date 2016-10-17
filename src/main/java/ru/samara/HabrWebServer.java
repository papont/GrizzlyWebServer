package ru.samara;

import org.glassfish.grizzly.Grizzly;
import org.glassfish.grizzly.http.server.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HabrWebServer {

    private static final Logger LOGGER = Grizzly.logger(HabrWebServer.class);

    public static void main(String[] args) {
        // create a basic server that listens  0.0.0.0:8080
        final HttpServer server = HttpServer.createSimpleServer();
        final ServerConfiguration config = server.getServerConfiguration();
        // Map the path, '/', to the Handler
        config.addHttpHandler(new HabrHandler(), "/");
        try {
            server.start();
            System.out.println("The server is running. \nPress enter to stop...");
            System.in.read();
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, ioe.toString(), ioe);
        } finally {
            server.shutdownNow();
        }
    }

    private static class HabrHandler extends HttpHandler {
        @Override
        public void service(Request request, Response response) throws Exception {
            response.setContentType("text/plain");
            response.getWriter().write("Hello Habrahabr!");
        }
    }
}
