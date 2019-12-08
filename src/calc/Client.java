package calc;

import lib.Sockets.StopAndWait;
import lib.Sockets.UDPSocketUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Client {
    private static final SocketAddress serverAddress = new InetSocketAddress("localhost", 5555);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UDPSocketUtils socket = null;
        try {
            socket = new StopAndWait();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while (true) {
            System.out.println("Scrivi un'operazione");
            String s = sc.nextLine();
            if (!Pattern.matches("([0-9]*.[0-9]+|[0-9]+)[-+*x:/]([0-9]*.[0-9]+|[0-9]+)", s))
                continue;
            try {
                socket.sendString(s, serverAddress);
                System.out.println(socket.receiveString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
