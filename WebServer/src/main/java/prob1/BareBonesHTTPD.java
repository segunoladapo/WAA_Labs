package prob1;

import java.io.*;
import java.lang.reflect.Method;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BareBonesHTTPD extends Thread {

    private static final int PortNumber = 8080;

    Socket connectedClient = null;

    String folderUri = "C:\\Users\\986296\\Desktop\\document_folder";

    public BareBonesHTTPD(Socket client) {
        connectedClient = client;
    }


    public void run() {

        try {
            System.out.println(connectedClient.getInetAddress() + ":" + connectedClient.getPort() + " is connected");

            BBHttpRequest httpRequest = getRequest(connectedClient.getInputStream());

            if (httpRequest != null) {
                BBHttpResponse httpResponse = new BBHttpResponse();

                processRequest(httpRequest, httpResponse);

                sendResponse(httpResponse);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processRequest(BBHttpRequest httpRequest, BBHttpResponse httpResponse) throws Exception {
        StringBuilder response = new StringBuilder();
        Properties prop = new Properties();
        String configFileName = "config.properties";
        ClassLoader classLoader = BareBonesHTTPD.class.getClassLoader();

        // Make sure that the configuration file exists
        URL res = Objects.requireNonNull(classLoader.getResource(configFileName),
                "Can't find configuration file config.properties");

        InputStream is = new FileInputStream(res.getFile());
        prop.load(is);
        if (prop.getProperty(httpRequest.getUri()) != null) {
            String className = prop.getProperty(httpRequest.getUri());
            Class<?> c = Class.forName(className);
            Object pageInstance = c.newInstance();
            Method getNameMethod = pageInstance.getClass().getMethod(prop.getProperty("methodname"));
            String result = (String) getNameMethod.invoke(pageInstance);
            response.append(result);
            httpResponse.setStatusCode(200);
        } else {
            String fileName = httpRequest.getUri();
            String fileFullPathName = folderUri + fileName;
            Path path = Paths.get(fileFullPathName);
            if (Files.exists(path)) {
                //go ahead and read files
                try (BufferedReader br = new BufferedReader(new FileReader(fileFullPathName))) {
                    response.append("<!DOCTYPE html>");
                    response.append("<html>");
                    String sCurrentLine;
                    while ((sCurrentLine = br.readLine()) != null) {
                        response.append(sCurrentLine).append("<br />");
                    }
                    response.append("</html>");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                httpResponse.setStatusCode(200);

            } else {
                //Else return 404
                response.append("<!DOCTYPE html>");
                response.append("<html>");
                response.append("<head>");
                response.append("<title>Almost an HTTP Server</title>");
                response.append("</head>");
                response.append("<html>");
                response.append("</html>");
                httpResponse.setStatusCode(404);
            }
        }
        httpResponse.setMessage(response.toString());
    }

    private BBHttpRequest getRequest(InputStream inputStream) throws IOException {

        BBHttpRequest httpRequest = new BBHttpRequest();

        BufferedReader fromClient = new BufferedReader(new InputStreamReader(inputStream));

        String headerLine = fromClient.readLine();

        if ((headerLine == null) || (headerLine.isEmpty())) {
            return null;
        }

        System.out.println("The HTTP request is ....");
        System.out.println(headerLine);

        // Header Line
        StringTokenizer tokenizer = new StringTokenizer(headerLine);
        httpRequest.setMethod(tokenizer.nextToken());
        httpRequest.setUri(tokenizer.nextToken());
        httpRequest.setHttpVersion(tokenizer.nextToken());

        // Header Fields and Body
        boolean readingBody = false;
        ArrayList<String> fields = new ArrayList<>();
        ArrayList<String> body = new ArrayList<>();

        while (fromClient.ready()) {

            headerLine = fromClient.readLine();
            System.out.println(headerLine);

            if (!headerLine.isEmpty()) {
                if (readingBody) {
                    body.add(headerLine);
                } else {
                    fields.add(headerLine);
                }
            } else {
                readingBody = true;
            }
        }
        httpRequest.setFields(fields);
        httpRequest.setMessage(body);
        return httpRequest;
    }

    private void sendResponse(BBHttpResponse response) throws IOException {

        String statusLine = null;
        if (response.getStatusCode() == 200) {
            statusLine = "HTTP/1.1 200 OK" + "\r\n";
        } else {
            statusLine = "HTTP/1.1 " + response.getStatusCode() + "\r\n";
        }

        String serverdetails = "Server: BareBones HTTPServer";
        String contentLengthLine = "Content-Length: " + response.getMessage().length() + "\r\n";
        String contentTypeLine = "Content-Type: " + response.getContentType() + " \r\n";

        try (DataOutputStream toClient = new DataOutputStream(connectedClient.getOutputStream())) {

            toClient.writeBytes(statusLine);
            toClient.writeBytes(serverdetails);
            toClient.writeBytes(contentTypeLine);
            toClient.writeBytes(contentLengthLine);
            toClient.writeBytes("Connection: close\r\n");
            toClient.writeBytes("\r\n");
            toClient.writeBytes(response.getMessage());

        }
    }

    public static void main(String args[]) throws Exception {

        try (ServerSocket server = new ServerSocket(PortNumber, 10, InetAddress.getByName("127.0.0.1"))) {
            System.out.println("Server Started on port " + PortNumber);

            while (true) {
                Socket connected = server.accept();
                (new BareBonesHTTPD(connected)).start();
            }
        }
    }
}
