package com.example.fluttermix.base

import android.app.Application
import android.util.Log
import com.example.fluttermix.PageRouter
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.Utils.assembleUrl
import com.idlefish.flutterboost.interfaces.INativeRouter
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

/**
 * @author 付影影
 * @desc
 * @date 2020/4/22
 */
class MyApplication : Application() {
    private lateinit var flutterEngine: FlutterEngine

    companion object {
        const val FLUTTER_ACTIVITY_ENGINE_ID = "my_engine_id"
    }

    override fun onCreate() {
        super.onCreate()

        // Instantiate a FlutterEngine.
        flutterEngine = FlutterEngine(this)

        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
            .getInstance()
            .put(FLUTTER_ACTIVITY_ENGINE_ID, flutterEngine)


        //flutter-boost
        val router: INativeRouter =
            INativeRouter { context, url, urlParams, requestCode, exts ->
                val assembleUrl: String = assembleUrl(url, urlParams)
                Log.d("hh", "assembleUrl = $assembleUrl")
                PageRouter.openPageByUrl(context, assembleUrl, urlParams)
            }

        val boostLifecycleListener: FlutterBoost.BoostLifecycleListener =
            object : FlutterBoost.BoostLifecycleListener {
                override fun beforeCreateEngine() {}
                override fun onEngineCreated() {}
                override fun onPluginsRegistered() {}
                override fun onEngineDestroy() {}
            }

        //
        // AndroidManifest.xml 中必须要添加 flutterEmbedding 版本设置
        //
        //   <meta-data android:name="flutterEmbedding"
        //               android:value="2">
        //    </meta-data>
        // GeneratedPluginRegistrant 会自动生成 新的插件方式　
        //
        // 插件注册方式请使用
        // FlutterBoost.instance().engineProvider().getPlugins().add(new FlutterPlugin());
        // GeneratedPluginRegistrant.registerWith()，是在engine 创建后马上执行，放射形式调用
        //

        //

        val platform: com.idlefish.flutterboost.Platform =
            FlutterBoost.ConfigBuilder(this, router)
                .isDebug(true)
                .whenEngineStart(FlutterBoost.ConfigBuilder.ANY_ACTIVITY_CREATED)
                .renderMode(FlutterView.RenderMode.texture)
                .lifecycleListener(boostLifecycleListener)
                .build()
        FlutterBoost.instance().init(platform)
    }
}