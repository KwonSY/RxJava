package com.seyoung.myapplication

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class MainViewModel : ViewModel() {
    private val count :BehaviorSubject<Int> = BehaviorSubject.createDefault(0)

    fun getValue(): Int {
        return count.value!!
    }

    fun getValueStream(): Observable<Int> {
        return count
    }

    fun setValue(n: Int) : Unit {
        count.onNext(n)
    }
}