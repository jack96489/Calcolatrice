package calc;

import lib.Sockets.StopAndWait;
import lib.Sockets.UDPSocketUtils;

import java.io.IOException;
import java.net.SocketException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Server {

    public static void main(String[] args) {
        UDPSocketUtils socket = null;
        try {
            socket = new StopAndWait(5555);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                String s = socket.receiveString();
                double ris = 0;
                if (Pattern.matches("([0-9]*.[0-9]+|[0-9]+)[-+*x:/]([0-9]*.[0-9]+|[0-9]+)", s)) {
                    String[] parts = s.split("[-+*x:/]");
                    System.out.println(Arrays.toString(parts));

                    if (s.contains("+"))
                        ris = Double.parseDouble(parts[0]) + Double.parseDouble(parts[1]);
                    if (s.contains("-"))
                        ris = Double.parseDouble(parts[0]) - Double.parseDouble(parts[1]);
                    if (s.contains("*") || s.contains("x"))
                        ris = Double.parseDouble(parts[0]) * Double.parseDouble(parts[1]);
                    if (s.contains("/") || s.contains(":"))
                        ris = Double.parseDouble(parts[0]) / Double.parseDouble(parts[1]);

                }
                socket.sendString(Double.toString(ris), socket.getSocketAddress());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
