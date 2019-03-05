package com.lolizeppelin.fluipay


import com.lolizeppelin.fluipay.handler.IPayApi
import com.lolizeppelin.fluipay.constant.IPAYCommon

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class FluipayPlugin(private val registrar: Registrar, private val channel: MethodChannel): MethodCallHandler {
  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
        val channel = MethodChannel(registrar.messenger(), "com.lolizeppelin/fluipay")
        IPayApi.setRegistrar(registrar)
        IPayApi.setMethodChannel(channel)
        channel.setMethodCallHandler(FluipayPlugin(registrar, channel))
    }
  }

    override fun onMethodCall(call: MethodCall, result: Result) {

        when(call.method) {
            "getPlatformVersion" -> result.success("Android ${android.os.Build.VERSION.RELEASE}")
            IPAYCommon.REGISTER_APP -> IPayApi.registerApp(call, result)
            IPAYCommon.PAY -> IPayApi.pay(call, result)
            else -> result.notImplemented()
        }

    }
}
