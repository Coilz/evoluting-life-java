# Evoluting Life
This application is an experiment to train robots to travel without colliding. To achieve this, it uses 
a Genetic Algorithm to train a Recurrent Neural Network. The fitness of a robot is its age whihc in turn is indirectly determined by traveling a distance as large as possible within a limited amout of time and with no collisions. Robots can be run in a simulation as well as on a raspberry-pi.
## Build
To build this application using a Terminal, navigate to the root of this project. Make sure you have 
installed Maven and type:
```bash
mvn clean package
```
## Running the simulation
To run this application as a simulation using a Terminal, navigate to the target
sub folder and type:
```bash
java -jar evoluting-life-java-<version>-jar-with-dependencies.jar
```
Where the <version>-placeholder needs to be replaced by the current version.
## Running on a Raspberry-Pi
### Prerequisites
#### pi4j
To control the pins on your Raspberry-Pi, this application uses the
[pi4j library](http://pi4j.com/index.html). The library needs to be installed on
your Raspberry-Pi. You can find instructions here: [Install pi4j](http://pi4j.com/install.html)
#### ServoBlaster
To control the servo that holds and turns the sensor, the application uses
[ServoBlaster](https://github.com/richardghirst/PiBits/tree/master/ServoBlaster).
This repository contains a servoBlaster.sh which should be started on the Raspberry-Pi before 
starting the application.
#### runRobot
To start the application in a convenient way, it is recommended to copy the runRobot.sh file
in this repository to your Raspberry-Pi.
#### The application
Also copy the application 'evoluting-life-java-<version>-jar-with-dependencies.jar' that
was previously build to your Raspberry-Pi.
### Pin setup
The pin numbering of wiringpi wil be used in this description and in code. Visit
[Raspberry Pi Pinout](http://pinout.xyz/pinout/wiringpi) for reference.
The following pins are used for running the application on the Raspberry-Pi:
* GPIO_07 : Servo               (output)
* GPIO_10 : Sensor echo         (input)
* GPIO_14 : Sensor trigger      (output)
* GPIO_23 : Left motor forward  (output, hard PWM)
* GPIO_25 : Left motor reverse  (output, soft PWM)
* GPIO_26 : Right motor forward (output, hard PWM)
* GPIO_28 : Right motor reverse (output, soft PWM)
* GPIO_29 : Led                 (output)  

## Structure of the code
The green colored boxes represent classes that are part of the core and are used in both simulation
and on the Raspberry-Pi. The orange colored boxed represent classes that are used for the simulation only.
The grey colored boxes represent classes that are used on the Raspberry-Pi only. The red lines represent
a dependency that is used to report velocity, position, collision etc. to the SimRobot class.
 
![Class Diagram](https://docs.google.com/drawings/d/18I7Fg6CTmE0s5LimI8FwSKXlY61ioaHY7mvGooyhNbY/pub?w=1119&h=640)

## Simulation physics
A robot has three motors: two motors to move around and one motor for turning the sensor. When a motor accelerates to move forward, backward or to turn the sensor, the motors use energy of the robot.

When a robot is moving or turning its sensor, it experiences drag. So without applying a force to accelerate, any movement will come to a halt.

### Health of a robot
The health of a robot is determined by actions that cost energy and actions that produce energy. When a robot has zero or less energy, the robot is considered 'dead' and is replaced by the GA.

Time, acceleration and collisions cost energy. Travelling distance produces energy.

Each time tick costs some amount of energy, starting with a relative low number. When a robot from the population has reached its maximum age, the cost per time tick is increased by a small amount, so that robots will have to travel a larger distance per time tick in order to keep their health.

## Recurrent Neural Networks
The robots are equiped with a Neural Network that contains Gated Recurrent Units. For more information on GRUs, see [Understanding LSTM Networks](http://colah.github.io/posts/2015-08-Understanding-LSTMs/) by Christopher Olah.