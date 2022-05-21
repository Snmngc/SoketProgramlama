package soketProgram;

import java.io.*;
import java.net.*;
import java.util.*;

public class sunucuThread {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null; 
        ClientHandler handler = null;
        Socket clientSocket = null;
        int i = 1;
    
        try {
          //Sunucu 65534 portu üzerinden istemciden veri bekliyor
            serverSocket = new ServerSocket(65534);
        } catch (Exception e){
            System.out.println("Girilen Port Hatalıdır!");
        }
        
        System.out.println("Sunucu Bağlantı İçin Hazır Durumdadır.");
        
        do {
            //İstemciler üzerinden gelecek olan bağlantıları kabul ediyoruz. Birden fazla istemcinin bağlanabilmesi için do-while yapısı kullandık.
            clientSocket = serverSocket.accept();
            System.out.println("İstemci "+ i + " ile bağlantı kuruldu.");
            //Thread sınıfını extend eden ClientHandler sınıfından handler değişkenini oluşturduk.
            handler = new ClientHandler(clientSocket);
            //start() metodu ile thread çalıştırıldı.
            handler.start(); 
            i++;
        }
        while (true);
    }
}

//Birden fazla istemci üzerinden gelen veriyi kontrol edip alabilmek için ClientHandler ile Thread mirasını kullandık. Her bir istemci bağlantısında yeni bir thread oluşturmaktadır.
//Buradaki kodları yazılmadan önce https://www.tutorialspoint.com/java/java_multithreading.htm ve https://www.geeksforgeeks.org/multithreading-in-java/ kaynakları örnekler incelenmiştir.
class ClientHandler extends Thread {
    Socket clientSocket = null;
    Scanner in = null;
    PrintWriter out = null;
    
    public ClientHandler(Socket socket) {
        clientSocket = socket;
        try{
            //İstemciler tarafından gelen veriyi almak için Scanner nesnesi oluşturduk.
            in = new Scanner(clientSocket.getInputStream());
            //İstemcilere gönderilen veriler için PrintWriter nesnesi oluşturduk.
            out = new PrintWriter(clientSocket.getOutputStream(),true);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Threadin yapması gerektiği işi run() metodu içerisinde belirttik.
    public void run() {
        String istemci;
        istemci = in.nextLine();
        System.out.println("İstemciden gelen veri = " + istemci);
        out.println(istemci + " İle İletişim Başarılı olmuştur.");
    }
}