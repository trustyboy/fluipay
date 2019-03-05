import 'dart:async';

import 'package:flutter/services.dart';


final MethodChannel _channel = const MethodChannel('com.lolizeppelin/fluipay')
  ..setMethodCallHandler(_handler);

/* java调用回调 */
Future<dynamic> _handler(MethodCall methodCall) {

//  if (methodCall.method == "onPayResponse") {
//    _responsePaymentController.add(WeChatPaymentResponse.fromMap(methodCall.arguments));
//  }
  return Future.value(true);
}


Future<String> get platformVersion async {
  final String version = await _channel.invokeMethod('getPlatformVersion');
  return version;
}


Future register({String appId, bool doOnIOS: true, doOnAndroid: true,}) async {
  return await _channel.invokeMethod("registerApp", {
    "appId": appId,
    "android": doOnAndroid,
    "ios": doOnIOS,
  });
}

Future pay({String transid}) async {
  return await _channel.invokeMethod("payWithIpay", {"transid": transid});
}


void dispose() {

}





