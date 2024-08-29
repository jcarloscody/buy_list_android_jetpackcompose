package com.jcarloscody.github.buylist

data class CounterModel (var count: Int)


class CounterRepository{
    private var counter = CounterModel(0)

    fun getCounter() = counter

    fun incr(){
        counter.count++
    }


    fun dec(){
        counter.count--
    }
}