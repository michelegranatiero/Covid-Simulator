# Covid-Simulator

### Java Project - Sapienza University of Rome

This is my first java project for a university course to simulate the covid virus spreading among people.

The simulator is built for monitoring the daily propagation of a virus among the population via a GUI in real time.

A set of rules and parameters were defined to adjust the simulation and make it more realistic. The virus behavior can be tuned by adjusting parameters like infectivity, symptomaticity and lethality. Resources, number of people and number of daily meetings among people can be controlled.

Different people states (colors) are used to distinguish among healthy🟢, infected with symptoms🔴, infected without symptoms🟡, recovered🔵 and deceased⚫ people.

People can produce and consume resources daily, based on their state. Infected with symptoms and deceased people movement is freezed in the simulation.

Various possible strategies were implemented to try to reduce the virus spreading.

A collision detection mechanism is applied to determine when two people meet, and decide, based on the virus infectivity (probability), if the other person has been infected.

The gui is created with Java Swing.

<img src="https://github.com/michelegranatiero/Covid-Simulator/assets/53618768/1c29e67d-12b1-405b-b22f-765506cdaf0a" width=800>

### Here is a demo of the simulator:

https://github.com/michelegranatiero/Covid-Simulator/assets/53618768/fd5b9aac-eee0-44d4-8921-b14da5e97f1c

