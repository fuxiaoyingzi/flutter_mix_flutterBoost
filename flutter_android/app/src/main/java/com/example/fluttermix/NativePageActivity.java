package com.example.fluttermix;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NativePageActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mOpenNative;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.native_page2);
        mOpenNative = findViewById(R.id.open_native);
        mOpenNative.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mOpenNative) {
            PageRouter.openPageByUrl(this, PageRouter.MAIN_NATIVE_PAGE_URL, null);
        }
    }
}