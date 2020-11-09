package com.seyoung.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.rxbinding4.view.RxView;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

import static io.reactivex.rxjava3.core.Observable.merge;

public class MainActivity2 extends AppCompatActivity {
    private MainViewModel viewModel = new MainViewModel();

    CompositeDisposable disposeBag = new CompositeDisposable();

    private TextView textCounter;
    private Button buttonMinus, buttonPlus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textCounter = findViewById(R.id.textCounter);
        buttonMinus = findViewById(R.id.button_minus);
        buttonPlus = findViewById(R.id.button_plus);

//        Disposable d3 = viewModel.getValueStream()
//                .map(String::valueOf)
//                .subscribe(textCounter::setText);// .subscribe(c -> textCounter.setText(c));
//
//        Disposable d1 = merge(
//                RxView.clicks(buttonMinus).map(v -> -1),
//                RxView.clicks(buttonPlus).map(v -> 1)
//        )
//                .withLatestFrom(viewModel.getValueStream(), (n, c) -> n + c)// .map(n -> count.getValue() + n)
//                .subscribe(viewModel::setValue);
//
//        disposeBag.addAll(d1, d3);
    }

    @Override
    protected void onDestroy() {
        disposeBag.dispose();
        super.onDestroy();
    }
}
