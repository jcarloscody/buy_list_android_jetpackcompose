package com.jcarloscody.github.buylist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ConterViewModel() : ViewModel() {
    private val repository: CounterRepository = CounterRepository()
    private val _count = mutableStateOf(repository.getCounter().count)

    val count: MutableState<Int> = _count

    fun inc(){
        repository.incr()
        _count.value = repository.getCounter().count
    }

    fun dec(){
        repository.dec()
        _count.value = repository.getCounter().count
    }
}