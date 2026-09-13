package Arithmetic;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            Arithmetic service = new ArithmeticImpl();
            registry.rebind("ArithmeticService", service);

            System.out.println("==============================================");
            System.out.println(" RMI Server running on port 1099...");
            System.out.println(" Ready for client connections from Vivek Garg");
            System.out.println("==============================================");
        } catch (Exception e) {
            System.err.println("Server Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}