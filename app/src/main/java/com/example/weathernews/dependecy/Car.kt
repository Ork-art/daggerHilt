package com.example.weathernews.dependecy

class Car(val engine: Engine, val transmitter: Transmitter) {

    fun startCar(){
        engine.startEngine()
        transmitter.starTransmitter()
    }

}

class Engine {
    fun startEngine(){
        println("Start Engine")
    }
}

class Transmitter {
    fun starTransmitter(){
        println("Star Transmitter")
    }
}