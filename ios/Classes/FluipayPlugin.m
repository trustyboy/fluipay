#import "FluipayPlugin.h"
#import <fluipay/fluipay-Swift.h>

@implementation FluipayPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFluipayPlugin registerWithRegistrar:registrar];
}
@end
