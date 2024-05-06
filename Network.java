import java.util.ArrayList;
import java.util.Scanner;

public class Network {
    public static void main(String[] args) {
        int connections, Ndevice;
        ArrayList<Device> devices = new ArrayList<>();

        Scanner in = new Scanner(System.in);

        System.out.println("enter wifi Connections ");
        connections = in.nextInt();
        Router router = new Router(connections);

        System.out.println("enter number of connection Clients ");
        Ndevice = in.nextInt();

        for (int i = 0; i < Ndevice; i++) {
            Device newDevice = new Device(in.next(), in.next(), router);
            devices.add(newDevice);
        }

        for (int i = 0; i < Ndevice; i++) {
            devices.get(i).start();
        }
    }
}