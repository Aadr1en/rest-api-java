import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.NoSuchElementException;

public class TaskHandler implements HttpHandler {
    private static FIFO queue = new FIFO();
    private static TaskService taskService = new TaskService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String response = "";

        if ("POST".equals(exchange.getRequestMethod()) && path.equals("/enqueue")) {
            Task task = taskService.createTask("Agence Yas", "05");
            queue.enqueue(task.getId());
            response = "Ticket généré automatiquement :\n" + task.toString() +
                       "\nTickets en attente : " + queue.size();

        } else if ("GET".equals(exchange.getRequestMethod()) && path.equals("/peek")) {
            try {
                int id = queue.peek();
                Task ticket = taskService.getTaskById(id);
                response = "Prochain ticket :\n" + ticket.toString();
            } catch (NoSuchElementException e) {
                response = "La file est vide.";
            }

        } else if ("GET".equals(exchange.getRequestMethod()) && path.equals("/dequeue")) {
            try {
                int id = queue.dequeue();
                Task ticket = taskService.getTaskById(id);
                response = "Ticket servi :\n" + ticket.toString();
            } catch (NoSuchElementException e) {
                response = "La file est vide.";
            }

        } else if ("GET".equals(exchange.getRequestMethod()) && path.equals("/isempty")) {
            response = "Est vide ? " + queue.isEmpty();

        } else if ("GET".equals(exchange.getRequestMethod()) && path.equals("/size")) {
            response = "Taille de la file : " + queue.size();

        } else {
            response = "Endpoint inconnu.";
        }

        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
