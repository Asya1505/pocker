java version "15.0.1"

Implemented only for texas-holdem

**_build_**

javac -sourcepath src -d build src/*.java

**_run_**

java -classpath build Pocker <game-type> [<5 board cards>] <hand 1> <hand 2> <...> <hand N>

**_example_**

java -classpath build Pocker texas-holdem 4cKs4h8s7s Ad4s Ac4d As9s KhKd 5d6d

