#import "RnPhotoViewer.h"
#import "GKPhotoBrowser.h"

@implementation RnPhotoViewer
RCT_EXPORT_MODULE()

- (dispatch_queue_t)methodQueue {
  return dispatch_get_main_queue();
}

- (void)showPhoto:(double)index
          images:(NSArray *)images
          options:(JS::NativeRnPhotoViewer::RnPhotoViewerOptions &)options {
  
  NSMutableArray *photos = [NSMutableArray new];
  
  for (int i=0; i < images.count; i++) {
    GKPhoto *photo = [GKPhoto new];
    if ([images[i][@"isVideo"] boolValue]) {
      photo.videoUrl = [NSURL URLWithString:images[i][@"url"]];
    } else {
      photo.url = [NSURL URLWithString:images[i][@"url"]];
    }
    [photos addObject:photo];
  }
  
  GKPhotoBrowser *browser = [GKPhotoBrowser photoBrowserWithPhotos:photos currentIndex:index];
  
  GKPhotoBrowserConfigure *configure = GKPhotoBrowserConfigure.defaultConfig;
  
  configure.hidesSavedBtn = options.isHidesSavedBtn();
  configure.isVideoReplay = options.isVideoReplay();
  configure.isStatusBarShow = options.isStatusBarShow();
  configure.isHideSourceView = options.isHideSourceView();
  configure.isUpSlideDismissDisabled = options.isUpSlideDismissDisabled();
  
  configure.loadStyle = GKPhotoBrowserLoadStyleDeterminateSector;
  configure.originLoadStyle = GKPhotoBrowserLoadStyleDeterminateSector;
  configure.failStyle = GKPhotoBrowserFailStyleOnlyImage;
  
  configure.videoLoadStyle = GKPhotoBrowserLoadStyleDeterminateSector;
  configure.videoFailStyle = GKPhotoBrowserFailStyleOnlyImage;
  configure.isVideoPausedWhenScrollBegan = YES;
  
  configure.liveLoadStyle = GKPhotoBrowserLoadStyleDeterminateSector;
  
  browser.configure = configure;
  
  UIViewController *rootViewController = [UIApplication sharedApplication].delegate.window.rootViewController;
  
  [browser showFromVC:rootViewController];
}

- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativeRnPhotoViewerSpecJSI>(params);
}

@end
