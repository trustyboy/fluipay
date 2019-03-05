package com.lolizeppelin.fluipay.handler

import android.util.Log

import com.iapppay.sdk.main.IAppPay
import com.iapppay.interfaces.callback.IPayResultCallback;

import com.lolizeppelin.fluipay.constant.IPAYCommon

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.PluginRegistry


object IPayApi {


    private var registrar: PluginRegistry.Registrar? = null
    private var channel: MethodChannel? = null

    private var appId:String = ""


    fun setRegistrar(registrar: PluginRegistry.Registrar) {
        IPayApi.registrar = registrar
    }

    fun setMethodChannel(channel: MethodChannel) {
        IPayApi.channel = channel
    }


    fun registerApp(call: MethodCall, result: MethodChannel.Result) {

        if (call.argument<Boolean>(IPAYCommon.ANDROID) == false) {
            return
        }

        if (this.appId != "") {
            Log.d("ipay", "will not init agin")
            result.success(mapOf(
                    IPAYCommon.PLATFORM to IPAYCommon.ANDROID,
                    IPAYCommon.RESULT to true
            ))
            return
        }

        val appId: String? = call.argument(IPAYCommon.APP_ID)
        if (appId.isNullOrBlank()) {
            result.error("invalid app id", "are you sure your app id is correct ?", appId)
            return
        }

        if (IPayApi.registrar == null) {
            result.error("invalid registrar", "call setRegistrar first",  null)
            return
        }

        Log.d("ipay", "Try =init IAppPay")
        IAppPay.init(IPayApi.registrar?.activity(), IAppPay.PORTRAIT, appId)
        Log.d("ipay", "Init IAppPay success")
        this.appId = appId

        result.success(mapOf(
                IPAYCommon.PLATFORM to IPAYCommon.ANDROID,
                IPAYCommon.RESULT to true
        ))

    }


    fun pay(call: MethodCall, result: MethodChannel.Result) {
        // 爱贝支付不需专门做异步操作 直接await pay即可
        var transid:String? = call.argument(IPAYCommon.TRANSID)
        var params = "transid=$transid&appid=$appId"

        val payCallback = object: IPayResultCallback  {
            override fun onPayResult(resultCode: Int, signValue: String, resultInfo: String) {
                result.success(mapOf(
                        IPAYCommon.PLATFORM to IPAYCommon.ANDROID,
                        IPAYCommon.RESULT to true,
                        IPAYCommon.DATA to mapOf(
                                "resultcode" to resultCode,
                                "sign" to signValue,
                                "info" to resultInfo
                        )
                ))
            }
        }

        IAppPay.startPay(registrar?.activity(), params, payCallback)

    }

}