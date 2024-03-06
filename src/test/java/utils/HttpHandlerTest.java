package utils;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;

import java.io.*;
import java.net.Socket;

class HttpHandlerTest {
        @Test
        public void testHandleRootPathRequest() throws Exception {
            Socket socket = mock(Socket.class);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            String httpRequest = "GET / HTTP/1.1\r\nHost: localhost:8090\r\n\r\n";
            InputStream inputStream = new ByteArrayInputStream(httpRequest.getBytes());

            when(socket.getInputStream()).thenReturn(inputStream);
            when(socket.getOutputStream()).thenReturn(new ByteArrayOutputStream());

            HttpHandler httpHandler = new HttpHandler(socket);

            httpHandler.run();

            verify(socket, atLeastOnce()).getInputStream();

            String response = outputStream.toString();
            System.out.println(response);

            assertEquals(response.contains("HTTP/1.1 200 OK"), false);
            assertFalse(response.contains("Content-Type: text/html"), "Response should specify content type as text/html for the root path.");
        }
}
