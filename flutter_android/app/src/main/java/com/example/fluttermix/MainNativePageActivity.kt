package com.example.fluttermix

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fluttermix.base.MyApplication
import io.flutter.embedding.android.FlutterActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 *原生加载 Flutter界面
 */
class MainNativePageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toFlutterBtn.setOnClickListener {
            /* startActivity(
                 FlutterActivity.createDefaultIntent(this)
             )*/
            //使用缓存的情况
            startActivity(
                FlutterActivity
                    .withCachedEngine(MyApplication.FLUTTER_ACTIVITY_ENGINE_ID)
                    .build(this)
            )
        }
    }
}
