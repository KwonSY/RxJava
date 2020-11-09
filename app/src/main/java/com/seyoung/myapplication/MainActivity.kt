package com.seyoung.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.kotlin.addTo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()
    private var disposeBag = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getValueStream()
            .map{ it.toString() }
            .subscribe(Consumer<String> { textCounter.text = it })
            .addTo(disposeBag)

        Observable.merge(button_minus.clicks().map { -1 }, button_plus.clicks().map { 1 })
            .withLatestFrom(viewModel.getValueStream(), BiFunction<Int, Int, Int> { n, c -> n + c })
            .subscribe {viewModel.setValue(it)}
            .addTo(disposeBag)

        btn_go_second.clicks()
            .subscribe { goSecondActivity() }
            .addTo(disposeBag)
    }

    override fun onDestroy() {
        disposeBag.dispose()
        super.onDestroy()
    }

    private fun goSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("value", viewModel.getValue())
        startActivityForResult(intent, 1000)

//        viewModel.getValueStream()
//            .take(1)
//            .map {
//                val intent = Intent( this, SecondActivity::class.java)
//                intent.putExtra("value", viewModel.getValue())
//                intent
//            }
//            .subscribe { intent -> startActivityForResult(intent, 1000)}
//            .addTo(disposeBag)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                val value = data?.getIntExtra("value", 0) ?: 0
                viewModel.setValue(value)
            }
        }
    }
}