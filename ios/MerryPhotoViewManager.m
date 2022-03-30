//  Created by react-native-create-bridge

#import "MerryPhotoViewManager.h"
#import "MerryPhotoView.h"
#import <Foundation/Foundation.h>

@implementation MerryPhotoViewManager

//@synthesize bridge = _bridge;

// Export a native module
// https://facebook.github.io/react-native/docs/native-modules-ios.html
RCT_EXPORT_MODULE();

// Return the native view that represents your React component
- (UIView*)view
{
    return [[MerryPhotoView alloc] initWithBridge:self.bridge];
}

RCT_EXPORT_VIEW_PROPERTY(data, NSArray)
RCT_EXPORT_VIEW_PROPERTY(initial, NSInteger)
RCT_EXPORT_VIEW_PROPERTY(hideStatusBar, BOOL)
RCT_EXPORT_VIEW_PROPERTY(hideHeader, BOOL)
RCT_EXPORT_VIEW_PROPERTY(hideFooter, BOOL)

RCT_EXPORT_VIEW_PROPERTY(onClose, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onChange, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onDownload, RCTBubblingEventBlock)

@end
