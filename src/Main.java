import com.sun.net.httpserver.*;
import java.net.*;
import java.io.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpServer s = HttpServer.create(new InetSocketAddress(8080), 0);
        TaskHandler h = new TaskHandler();

        s.createContext("/enqueue", h);
        s.createContext("/peek", h);
        s.createContext("/dequeue", h);
        s.createContext("/isempty", h);
        s.createContext("/size", h);

        s.createContext("/", e -> {
            try {
                // Récupère le chemin de `presentation.html` dans le dossier `src`
                Path htmlPath = Paths.get(Main.class.getResource("presentation.html").toURI());
                byte[] html = Files.readAllBytes(htmlPath);
                e.getResponseHeaders().add("Content-Type", "text/html");
                e.sendResponseHeaders(200, html.length);
                e.getResponseBody().write(html);
            } catch (Exception ex) {
                String msg = "404 - fichier 'presentation.html' introuvable.";
                e.sendResponseHeaders(404, msg.length());
                e.getResponseBody().write(msg.getBytes());
            }
            e.close();
        });

        s.setExecutor(null);
        s.start();
        System.out.println(" Serveur lancé sur http://localhost:8080/");
    }
}
