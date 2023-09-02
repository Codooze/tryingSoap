package com.calculator.trayingSoap.sever;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public class CalculatorService {

    //I think I can inject some dependency here to avoid this long line
    private final Calculator calculator;

    public CalculatorService() {
        this.calculator = new Calculator();
    }

    @WebMethod
    public double add(double a, double b) {
        return calculator.add(a, b);
    }

    @WebMethod
    public double subtract(double a, double b) {
        return calculator.subtract(a, b);
    }

    @WebMethod
    public double multiply(double a, double b) {
        return calculator.multiply(a, b);
    }

    @WebMethod
    public double divide(double a, double b) {
        return calculator.divide(a, b);
    }
}
/*
@WebService: This annotation tells Java that this class should be exposed as a SOAP web service.
@WebMethod: This annotation indicates that the method should be accessible via the SOAP service.

In React or SvelteKit, you'd make an API call to a server endpoint to fetch or send data.
Here, instead of calling an HTTP endpoint directly, you're calling a SOAP service.

The CalculatorService class is like that server endpoint. It listens for SOAP requests,
processes them using the Calculator class, and sends back a SOAP response.
 */