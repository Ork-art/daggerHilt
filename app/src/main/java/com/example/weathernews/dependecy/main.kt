package com.example.weathernews.dependecy

fun main() {

//    val car = Car(Engine(), Transmitter())
//    car.startCar()

    DependencyInjector.injectEngine(Engine())
    DependencyInjector.injectTransmitter(Transmitter())
    DependencyInjector.injectCar()

    val  car = DependencyInjector.car

    println(car?.startCar())
}