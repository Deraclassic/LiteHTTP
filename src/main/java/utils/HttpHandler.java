//package utils;
//
//import java.io.*;
//import java.net.Socket;
//
//public class HttpHandler implements Runnable {
//    private Socket socket;
//    private final static String BASE_PATH = "/Users/mac/IdeaProjects/week7_task/src/main/resources/Chidera_Portfolio";
//
//    public HttpHandler(Socket socket) {
//        this.socket = socket;
//    }
//
//    @Override
//    public void run() {
//        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {
//
//            String request = input.readLine();
//
//            if (request != null) {
//                String[] parts = request.split("\\s+");
//                if (parts.length >= 2 && parts[0].equals("GET")) {
//                    String path = parts[1];
//                    if (path.equals("/")) {
//                        serveFile(output, "/index.html");
//                    } else {
//                        serveFile(output, path);
//                    }
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void serveFile(PrintWriter output, String path) throws IOException {
//        if ("/".equals(path)) {
//            path = "/index.html";
//        } else if ("/json".equals(path)) {
//            path = "/data.json";
//        }
//
//        File file = new File(BASE_PATH + path);
//
//        if (file.exists() && !file.isDirectory()) {
//            String contentType = determineContentType(path);
//            sendResponse(output, file, contentType);
//        } else {
//            sendNotFoundResponse(output);
//        }
//    }
//
//    private String determineContentType(String path) {
//        if (path.endsWith(".html")) {
//            return "text/html";
//        } else if (path.endsWith(".css")) {
//            return "text/css";
//        }
//        else {
//            return "text/plain";
//        }
//    }
//
//    private void sendNotFoundResponse(PrintWriter output) {
//        output.println("HTTP/1.1 404 Not Found");
//        output.println("Content-Type: text/html");
//        output.println();
//        output.println("<html><body><h1>404 Not Found</h1><p>The requested resource was not found on this server.</p></body></html>");
//    }
//
//    private void sendResponse(PrintWriter output, File file, String contentType) throws IOException {
//        output.println("HTTP/1.1 200 OK");
//        output.println("Content-Type: " + contentType);
//        output.println();
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                output.println(line);
//            }
//        }
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////package utils;
////
////import commons.SimpleHttpServer;
////
////import java.io.*;
////import java.net.Socket;
////
////import static commons.SimpleHttpServer.HTML_FILE_PATH;
////import static commons.SimpleHttpServer.JSON_FILE_PATH;
////
////public class HttpHandler extends Thread{
////    private Socket socket;
////
////    public HttpHandler(Socket socket) {
////        this.socket = socket;
////    }
////
////    @Override
////    public void run() {
////        try(BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
////            PrintWriter output = new PrintWriter(socket.getOutputStream(), true)){
////
////            String request = input.readLine();
////
////            if(request != null){
////                String[] parts = request.split("\\s+");
////                if(parts.length >= 2 && parts[0].equals("GET")){
////                    String path = parts[1];
////                    if (path.equals("/") || path.equals("index.html")){
////                        sendNewHtmlResponse(output);
////                    } else if (path.equals("/json")){
////                        sendNewJsonResponse(output);
////                    } else {
////                        sendNewNotFoundResponse(output);
////                    }
////                }
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        } finally {
////            try {
////                socket.close();
////            } catch (IOException e) {
////                e.printStackTrace();
////
////            }
////        }
////    }
////
////    private void sendNewNotFoundResponse(PrintWriter output) throws IOException{
////        File file = new File(HTML_FILE_PATH);
////
////        if(file.exists()){
////            sendResponse(output, file, "text/html");
////        } else {
////            sendNewNotFoundResponse(output);
////        }
////    }
////
////
////    private void sendNewJsonResponse(PrintWriter output) throws IOException{
////        File file = new File(JSON_FILE_PATH);
////        if (file.exists()){
////            sendResponse(output, file, "application/json");
////            } else {
////            sendNewNotFoundResponse(output);
////        }
////    }
////
////    private void sendNewHtmlResponse(PrintWriter output) {
////        output.println("HTTP/1.1 404 Not Found");
////        output.println("Content-type: text/plain");
////        output.println();
////        output.println("404 Not Found - The Requested Response Was Not Found on This Server");
////    }
////
////    private void sendResponse(PrintWriter output, File file, String contentType) throws IOException{
////        output.println("HTTP/1.1 200 OK");
////        output.println("Content-type: " + contentType);
////        output.println();
////
////        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
////            String line;
////
////            while ((line = reader.readLine()) != null){
////                output.println(line);
////                System.out.println(line);
////            }
////        }
////    }
////
////}
//
////package utils;
////
////import java.io.*;
////import java.net.Socket;
////
////public class HttpHandler implements Runnable {
////    private Socket socket;
////    private final static String BASE_PATH = System.getProperty("user.home") + "/Desktop/Chidera_Portfolio";
////
////    public HttpHandler(Socket socket) {
////        this.socket = socket;
////    }
////
////    @Override
////    public void run() {
////        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
////             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {
////
////            String request = input.readLine();
////
////            if (request != null) {
////                String[] parts = request.split("\\s+");
////                if (parts.length >= 2 && parts[0].equals("GET")) {
////                    String path = parts[1];
////                    // Adjusted to serve files dynamically from the portfolio directory
////                    serveFile(output, path.equals("/") ? "/index.html" : path);
////                }
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        } finally {
////            try {
////                socket.close();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////        }
////    }
////
////    private void serveFile(PrintWriter output, String path) throws IOException {
////        File file = new File(BASE_PATH + path);
////
////        // Security check to prevent path traversal attacks
////        if (!file.getCanonicalPath().startsWith(BASE_PATH)) {
////            sendNotFoundResponse(output);
////            return;
////        }
////
////        if (file.exists() && !file.isDirectory()) {
////            String contentType = determineContentType(path);
////            sendResponse(output, file, contentType);
////        } else {
////            sendNotFoundResponse(output);
////        }
////    }
////
////    private String determineContentType(String path) {
////        if (path.endsWith(".html")) {
////            return "text/html";
////        } else if (path.endsWith(".css")) {
////            return "text/css";
////        } else if (path.endsWith(".json")) {
////            return "application/json";
////        } else {
////            return "text/plain";
////        }
////    }
////
////    private void sendNotFoundResponse(PrintWriter output) {
////        output.println("HTTP/1.1 404 Not Found");
////        output.println("Content-type: text/html");
////        output.println();
////        output.println("<html><body><h1>404 Not Found</h1><p>The requested resource was not found on this server.</p></body></html>");
////    }
////
////    private void sendResponse(PrintWriter output, File file, String contentType) throws IOException {
////        output.println("HTTP/1.1 200 OK");
////        output.println("Content-type: " + contentType);
////        output.println();
////
////        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
////            String line;
////            while ((line = reader.readLine()) != null) {
////                output.println(line);
////            }
////        }
////    }
////}


package utils;

import java.io.*;
import java.net.Socket;

public class HttpHandler implements Runnable {
    private Socket socket;

    public HttpHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

            String request = input.readLine();

            if (request != null) {
                String[] parts = request.split("\\s+");
                if (parts.length >= 2 && parts[0].equals("GET")) {
                    String path = parts[1];
                    if (path.equals("/")) {
                        serveFile(output, "index.html");
                    } else if (path.equals("/json")) {
                        serveFile(output, "data.json");
                    } else {
                        sendNotFoundResponse(output);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void serveFile(PrintWriter output, String resourceName) throws IOException {
        InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(resourceName);
        if (resourceStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceStream));
            String contentType = determineContentType(resourceName);
            sendResponse(output, reader, contentType);
        } else {
            sendNotFoundResponse(output);
        }
    }

    private String determineContentType(String path) {
        if (path.endsWith(".html")) {
            return "text/html";
        } else if (path.endsWith(".css")) {
            return "text/css";
        } else if (path.endsWith(".json")) {
            return "application/json";
        } else {
            return "text/plain";
        }
    }

    private void sendNotFoundResponse(PrintWriter output) {
        output.println("HTTP/1.1 404 Not Found");
        output.println("Content-Type: text/html");
        output.println();
        output.println("<html><body><h1>404 Not Found</h1><p>The requested resource was not found on this server.</p></body></html>");
    }

    private void sendResponse(PrintWriter output, BufferedReader reader, String contentType) throws IOException {
        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: " + contentType);
        output.println();
        String line;
        while ((line = reader.readLine()) != null) {
            output.println(line);
        }
    }
}
