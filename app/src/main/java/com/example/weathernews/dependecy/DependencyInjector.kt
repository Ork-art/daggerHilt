package com.example.weathernews.dependecy

object DependencyInjector {

    lateinit var engine: Engine
    lateinit var transmitter: Transmitter
    var car: Car? = null

    fun injectCar(){
        car = Car(engine, transmitter)
    }
    fun injectEngine(engine: Engine){
        DependencyInjector.engine = engine
    }
    fun injectTransmitter(transmitter: Transmitter) {
        DependencyInjector.transmitter = transmitter
    }
}