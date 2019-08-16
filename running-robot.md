# Running a robot
Once you have copied the necessary files to run a robot to your raspberry pi, you can start it.  

## Login
If you connect to your robot using ssh:
```bash
$ ssh pi@<ip-address>
```
And login to the raspberry pi.

## Run
Once logged in, you may find a folder structure on your rasspberry pi that looks like this:  
```
.
|-- some-file.yml  
|-- evoluting-life  
|   |-- runRobot.sh  
|   |-- startServoBlaster.sh  
|   |-- genes  
|   |   |-- 33-36-scanner-004.json  
|   |   |-- 35-37-scanner-003.json  
|   |   |-- 40-47-scanner-003.json  
|-- evoluting-life-v2  
|   |-- runRobot.sh  
|   |-- startServoBlaster.sh  
|   |-- genes  
|   |   |-- v2-12-001.json  
|-- evoluting-life-v3  
|   |-- runRobot.sh  
|   |-- startServoBlaster.sh  
|   |-- genes  
|   |   |-- 113-001.json  
```

Go to folder with a specific version of the robot, for example ‘evoluting-life’. And then start the servo blaster using:

```bash
$ cd evoluting-life
$ ./startServoBlaster.sh
```

The servo blaster has to be started only once. After it has started, you can run the robot and provide it with a genes json-file using for example:

```bash
$ ./runRobot.sh genes/40-47-scanner-003.json <i-the gene> <delay>
```

Where &lt;i-th gene&gt; has a value starting from 0 and &lt;delay&gt; is the delay per tick in milliseconds (usually 40). The delay will make the robot run a bit slower.

## Stop
Press `Ctrl+C` to stop the running robot and it will exit in a clean way, also making sure the servo motor has a rotation of 0 degrees.