import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] arg) {
        int serverPort = 12345; // 
        String serverIp = "127.0.0.1"; 

        try {
            InetAddress ip = InetAddress.getByName(serverIp); // создаем объект который отображает вышеописанный IP-адрес.
            System.out.println("подключение к серверу с ip " + serverIp + " и портом " + serverPort);
            Socket socket = new Socket(ip, serverPort); // создаем сокет используя IP-адрес и порт сервера.
            //System.out.println("Yes! I just got hold of the program.");

            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом. 
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            // Создаем поток для чтения с клавиатуры.
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            System.out.println("напишите сообщение и нажмите ENTER");
            System.out.println();

            while (true) {
                line = keyboard.readLine(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.
                System.out.println("отправка на server...");
                out.writeUTF(line); // отсылаем введенную строку текста серверу.
                out.flush(); // заставляем поток закончить передачу данных.
                line = in.readUTF(); // ждем пока сервер отошлет строку текста.
                System.out.println("Получен ответ от sever : " + line);
                //System.out.println("Looks like the server is pleased with us. Go ahead and enter more lines.");
                System.out.println();
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}