package com.lolizeppelin.fluipay

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle

import android.view.Window


open class IpayMainActivity : Activity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT //横屏：根据传感器横向切换
    }

//
//    override fun onNewIntent(intent: Intent) {
//        super.onNewIntent(intent)
//        setIntent(intent)
//        try {
//            WXAPiHandler.wxApi?.handleIntent(intent, this)
//        }catch (e: Exception){
//            e.printStackTrace()
//            finish()
//        }
//    }


}
