#!/bin/bash
apt-get update && apt-get install default-jdk
javac -sourcepath src -d build src/*.java