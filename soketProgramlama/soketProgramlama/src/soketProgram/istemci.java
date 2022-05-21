package soketProgram;
 
import java.io.*;
import java.net.*;
import java.util.*;
 
public class istemci {
 
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        String bilgi;
        PrintWriter out = null;
        Scanner in = null;
        Scanner ogrenci = null;
          
        //Port aralığı 0-65535 arasındadır. Herhangi bir kullanılan port ile çakışma yaşanmaması için 65534 portu üzerinden soket açılmaktadır.
        try {
            //Localhost sunucusuna bağlanmak için localhost ve 65534 portu yazılabilir. Bağlanılacak olan sunucuya göre IP adresi girilmesi gerekmektedir.
            socket = new Socket("localhost", 65534);
        } catch (Exception e) {
            System.out.println("Girilen Port Hatalıdır!");
        }
        System.out.println("Sunucuya Başarıyla Bağlanıldı!");
        // İstemci ve sunucu tarafında gelen ve giden veriler için kullanılan nesneler https://cs.lmu.edu/~ray/notes/javanetexamples/ adresinde bulunan örnekler baz alınarak oluşturulmuştur.  
        // Sunucuya istemciden girilmiş olan bilgilerin gönderilmesi için PrintWriter nesnesi oluşturduk
        out = new PrintWriter(socket.getOutputStream(), true);
          
        // Sunucu tarafından gelen bilgileri tutabilmek için Scanner nesnesi oluşturduk 
        in = new Scanner(socket.getInputStream());
          
        //Kullanıcıdan öğrenci numarasını ve adını istedik
        System.out.println("Öğrenci Numaranızı ve Adınızı Giriniz: ");
 
        // Sunucuya gönderilecek öğrenci bilgilerinin girişini tuttuk
        ogrenci = new Scanner(System.in);
          
        //Girilen bilgilere göre sunucu tarafından gelen sonucu ekrana yazdık
        while((bilgi = ogrenci.nextLine()) != null) {
            out.println(bilgi);
            System.out.println("Sunucudan gelen sonuç = " + in.nextLine());
        }
    }
}