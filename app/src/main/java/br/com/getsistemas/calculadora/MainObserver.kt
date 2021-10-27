package br.com.getsistemas.calculadora

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainObserver: LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun OnCreate() {
        Log.i("MainObserver", "OnCreate is Called")
//        operatorButtons = arrayOf(
//            addButton, subButton, mulButton, divButton
//        )
//
//        numberButtons.forEach { button ->
//            button.setOnClickListener { numberButtonClick(button) }
//        }
//
//        operatorButtons.forEach { button ->
//            button.setOnClickListener { operatorButtonClick(button) }
//        }
    }
}