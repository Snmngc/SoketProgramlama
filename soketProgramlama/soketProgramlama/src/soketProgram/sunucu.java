package soketProgram;
 
import java.io.*;
import java.net.*;
import java.util.*;
 
public class sunucu {
 
    public static void main(String[] args) throws IOException {
        String istemci;
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        Scanner in = null;
        PrintWriter out = null;
          
        try {
            //Sunucu 65534 portu üzerinden istemciden veri bekliyor
            serverSocket = new ServerSocket(65534);
        } catch (Exception e) {
            System.out.println("Girilen Port Hatalıdır!");
        }
        System.out.println("Sunucu Bağlantı İçin Hazır Durumdadır.");
          
        //Bağlantı bekliyor
        clientSocket = serverSocket.accept();
 
        //İstemci tarafına veri gönderimi için PrintWriter nesnesini oluşturduk
        out = new PrintWriter(clientSocket.getOutputStream(), true);
 
        //İstemci tarafından gelen verileri tutan Scanner nesnesini oluşturduk
        in = new Scanner(clientSocket.getInputStream());
          
        while((istemci = in.nextLine()) != null) {
            System.out.println(istemci);
            System.out.println("İstemciden gelen veri = " + istemci);
            out.println(istemci + " İletişim Başarılı olmuştur.");
        }
    }
}