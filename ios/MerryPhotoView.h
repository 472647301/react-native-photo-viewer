//  Created by react-native-create-bridge

// import UIKit so you can subclass off UIView
#import <UIKit/UIKit.h>

#import <React/RCTComponent.h>
#import <React/RCTInvalidating.h>

#import <NYTPhotoViewerArrayDataSource.h>
#import <NYTPhotosViewController.h>

#import "MerryCommonHeader.h"

#import "MerryPhoto.h"
#import "MerryPhotoData.h"

@class RCTBridge;
@class RCTImageSource;

@class RCTEventDispatcher;

@interface MerryPhotoView : UIView <NYTPhotosViewControllerDelegate, RCTInvalidating>

// Define view properties here with @property
@property (nonatomic) NSInteger initial;
@property (nonatomic) NSArray* data;
@property (nonatomic) BOOL hideStatusBar;
@property (nonatomic, copy) RCTBubblingEventBlock onClose;
@property (nonatomic, copy) RCTBubblingEventBlock onChange;
@property (nonatomic, copy) RCTBubblingEventBlock onDownload;
@property (nonatomic) BOOL hideHeader;
@property (nonatomic) BOOL hideFooter;

// Initializing with the event dispatcher allows us to communicate with JS
//- (instancetype)initWithEventDispatcher:(RCTEventDispatcher*)eventDispatcher NS_DESIGNATED_INITIALIZER;
- (instancetype)initWithBridge:(RCTBridge*)bridge NS_DESIGNATED_INITIALIZER;

@property (nonatomic) NSMutableArray* photos;
@property (nonatomic) NSMutableArray* reactPhotos;

@property (nonatomic) NYTPhotoViewerArrayDataSource* dataSource;
@property (nonatomic) NYTPhotosViewController* nytPhotosViewController;
@end
