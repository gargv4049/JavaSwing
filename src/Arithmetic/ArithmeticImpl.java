package Arithmetic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ArithmeticImpl extends UnicastRemoteObject implements Arithmetic {
    private static final long serialVersionUID = 1L;

    public ArithmeticImpl() throws RemoteException {
        super();
    }

    @Override
    public double add(double a, double b) throws RemoteException { return a + b; }

    @Override
    public double subtract(double a, double b) throws RemoteException { return a - b; }

    @Override
    public double multiply(double a, double b) throws RemoteException { return a * b; }

    @Override
    public double divide(double a, double b) throws RemoteException {
        if (b == 0) throw new ArithmeticException("Division by zero is undefined.");
        return a / b;
    }

    @Override
    public double power(double base, double exp) throws RemoteException { return Math.pow(base, exp); }

    @Override
    public double modulo(double a, double b) throws RemoteException { return a % b; }

    @Override
    public double squareRoot(double a) throws RemoteException {
        if (a < 0) throw new ArithmeticException("Square root of negative number is undefined.");
        return Math.sqrt(a);
    }
}