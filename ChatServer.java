import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String chat = "";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/add-message?")) {
            String [] parameters = url.getQuery().split("&"); 
            String messageString = URLDecoder.decode(parameters[0].split("=")[1],StandardCharsets.UTF_8);
            String userString = URLDecoder.decode(parameters[1].split("=")[1], StandardCharsets.UTF_8);
            chat += userString + ": " +messageString + "/n";
            return chat;
        }else{
            return "404 Not Found!";
        }
    }
}

class ChatServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
