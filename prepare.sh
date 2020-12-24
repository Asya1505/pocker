#!/bin/bash
RUN apt-get update && apt-get install -y curl wget openjdk-15-jdk
RUN javac -sourcepath src -d build src/*.java