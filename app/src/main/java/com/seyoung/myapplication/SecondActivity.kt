package com.seyoung.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    private val disposeBag = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val count:Int = intent.getIntExtra("value", 0)
        edit_number.setText(count.toString(), TextView.BufferType.EDITABLE)

        btn_go_first.clicks()
            .subscribe {
                val intent = Intent()
                intent.putExtra("value", edit_number.text.toString().toIntOrNull() ?: 0)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
            .addTo(disposeBag)
    }

    override fun onDestroy() {
        disposeBag.dispose()
        super.onDestroy()
    }
}