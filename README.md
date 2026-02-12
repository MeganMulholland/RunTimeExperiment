# RunTimeExperiment
This project experimentally compares the performance of linear search and binary search by generating a large set of unique random integers, sorting them, and measuring the time required to search for multiple target values. It also analyzes how search efficiency changes with input size and reports metrics such as total time, average time, number of successful searches, and memory usage.

Requirements:
- Compatible version of Java
- classmexer.jar in the same directory as the source file

Compile Instructions:
- If you do not want to take account the memory measurement, this can be run in any IDE with the memory portions removed.
- To compile the entire project, use Windows/Powershell
- Make sure you are in the correct directory with the project file and classmexer file (use dir command).
- Compile command: javac -cp ".;classmexer.jar" Project2Experiment.java
- Run Instructions: java --add-opens java.base/java.util=ALL-UNNAMED ^
     --add-opens java.base/java.lang=ALL-UNNAMED ^
     -javaagent:classmexer.jar ^
     -cp ".;classmexer.jar" ^
     Project2Experiment
- Remember to recompile after any additions or changes to the code.

  Deeper Code Instructions:
  - For data sizes less than 1e8, use the HashSet. This will guarantee no duplicates in the dataset.
  - Keep the range as n*100.
  - For data sizes greater than or equal to 1e8, use the array to generate the dataset.
  - Lower the range to an acceptable value that allows the program to run smoothly.
  - Warning!! If you do not switch to using the array for the data set, the HashSet will use all available memory. (it is expensive but ensures no duplicate values)
  - Comment out the code for which version you will not be using.
