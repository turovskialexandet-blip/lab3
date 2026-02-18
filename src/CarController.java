import Lab1and2.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Motor_vehicle> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Lab1and2.Volvo240());
        cc.cars.add(new Lab1and2.Saab95());
        cc.cars.add(new Lab1and2.Scania());

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // This
            int current_Car = 0;
            for (Motor_vehicle car : cars) {
                car.move();
                int x = (int) Math.round(car.getCoordinates().x);
                int y = (int) Math.round(car.getCoordinates().y);
                frame.drawPanel.moveit(x, y, current_Car);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
                current_Car++;
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Motor_vehicle car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brakeAmount = ((double) amount) / 100;
        for (Motor_vehicle car : cars) {
            car.brake(brakeAmount);
        }

    }

    void start() {
        for (Motor_vehicle car : cars) {
            car.startEngine();
        }
    }

    void stop() {
        for (Motor_vehicle car : cars) {
            car.stopEngine();
        }
    }

    void turboOn() {
        for (Motor_vehicle car : cars) {
            if (car instanceof Saab95) ((Saab95) car).setTurboOn();
            else if (car instanceof Scania) ((Scania) car).setTurboOn();
        }
    }

    void turboOff() {
        for (Motor_vehicle car : cars) {
            if (car instanceof Saab95) ((Saab95) car).setTurboOff();
            else if (car instanceof Scania) ((Scania) car).setTurboOff();
        }
    }

    void liftBed () {
        for (Motor_vehicle car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).RaiseFlatbed(45);
            }
        }
    }

        void lowerBed () {
                for (Motor_vehicle car : cars) {
                    if (car instanceof Scania) {
                        ((Scania) car).LowerFlatbed(45);
                    }
                }
            }
        }