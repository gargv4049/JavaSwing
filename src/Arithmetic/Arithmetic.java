package Arithmetic;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Arithmetic extends Remote {
    double add(double a, double b) throws RemoteException;
    double subtract(double a, double b) throws RemoteException;
    double multiply(double a, double b) throws RemoteException;
    double divide(double a, double b) throws RemoteException;
    double power(double base, double exp) throws RemoteException;
    double modulo(double a, double b) throws RemoteException;
    double squareRoot(double a) throws RemoteException;
}