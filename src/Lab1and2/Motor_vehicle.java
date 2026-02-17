package Lab1and2;

public class Motor_vehicle extends Vehicle {
    private double enginePower;

    public double getEnginePower(){ return enginePower; }

    //public boolean getTurbo(){ return turboOn; }

    public void startEngine(){ currentSpeed = 0.1; }

    public void stopEngine(){ currentSpeed = 0; }

    public void setEnginePower(double amount){ enginePower = amount; }

    @Override
    public void incrementSpeed(double amount){ currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower()); }

    public void gas(double amount){
        amount = Zero_to_One(amount);
        incrementSpeed(amount);
    }

    public void brake(double amount){
        amount = Zero_to_One(amount);
        decrementSpeed(amount);
    }
}
