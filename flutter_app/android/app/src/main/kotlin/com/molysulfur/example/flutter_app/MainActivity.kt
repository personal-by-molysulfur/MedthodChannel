package com.molysulfur.example.flutter_app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel


class MainActivity : FlutterActivity() {

    companion object {
        private const val CHANNEL = "app.channel.shared.data"
        private const val PREFERENCE_NAME = "preference_app"
        private const val TOKEN_KEY = "token_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.action == Intent.ACTION_GET_CONTENT) {
            val pref = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            val bundle = Bundle()
            val intent = Intent()
            bundle.putString("text", pref.getString(TOKEN_KEY, "Empty"))
            intent.putExtras(bundle)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
                .setMethodCallHandler { call: MethodCall, result: MethodChannel.Result ->
                    if (call.method.contentEquals("saveToken")) {
                        val token = call.argument<String?>("token")
                        savePreference(token)
                    }
                }
    }

    private fun savePreference(token: String?) {
        val pref = getSharedPreferences(PREFERENCE_NAME,  Context.MODE_PRIVATE)
        Log.e("token" ,"$token")
        val edit = pref.edit()
        edit.putString(TOKEN_KEY, token)
        edit.apply()
    }


}
