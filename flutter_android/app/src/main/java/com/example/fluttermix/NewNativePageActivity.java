package com.example.fluttermix;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class NewNativePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_native_page);
        findViewById(R.id.toMainBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PageRouter.openPageByUrl(NewNativePageActivity.this, PageRouter.NATIVE_PAGE_URL, null);
            }
        });
    }
}
