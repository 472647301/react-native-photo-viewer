#import "ReactNativePhotoViewer.h"

NSInteger const LRDRCTSimpleByronToastBottomOffset = 40;
double const LRDRCTSimpleByronToastShortDuration = 3.0;
double const LRDRCTSimpleByronToastLongDuration = 5.0;

@implementation ByronPhotoView {
    NSURL *downloadUrl;
}

RCT_EXPORT_MODULE()

- (dispatch_queue_t)methodQueue {
  return dispatch_get_main_queue();
}

RCT_EXPORT_METHOD(show:(NSDictionary *)params) {
    NSMutableArray *photos = [NSMutableArray new];
    NSNumber *index = params[@"index"];
    NSNumber *isHideDownload = params[@"isHideDownload"];
    int currentIndex = [index intValue];
    NSArray *imageList = params[@"list"];
    for (int i = 0; i < imageList.count; i++) {
        GKPhoto *photo = [GKPhoto new];
        photo.url = [NSURL URLWithString:imageList[i]];
        [photos addObject:photo];
        if (i == currentIndex) {
            self->downloadUrl = photo.url;
        }
    }
    GKPhotoBrowser *browser = [GKPhotoBrowser photoBrowserWithPhotos:photos currentIndex:currentIndex];
    UIPageControl *pageControl = [UIPageControl new];
    pageControl.hidden = YES;
    [browser setPageControl:pageControl];
    NSString *dataURIStr = @"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAAAXNSR0IArs4c6QAAAepJREFUaEPtl7EuBlEQhc95AF5AQsE7aOkoCSoFDSFoaKhoaGgQQkOhIqGko/UOFCRegAc4sslKNn+u/5/Ze5f8yd3y7uzc882cvbtDdPnFLtePDPDfHWysA5K2AUyVgDckd5qAbRLgE0BPKfqLZG/XAEgaAfDYIniU5FNqiEY6kAEcbcodCBUrWyhbyFGBbKHIYuVTKFsoWyiyAtlCkQV0nULWIcX7K2HNG2L1AlSHlCLfFsm91sQeAEmbAHYrOVzDjxlAUj+At0AVNkgeVNetAJLWAewHcg6QfLe4ywxQJJN0AmApkHiV5PHPugVA0gqAo0CuU5LLFvFFjAughLgGMB3YYJHkeRnTdqSUtADgLJCjGP5nrOJrAXSAmCd52a4DkuYAXKQQXxugA8QsgI/QUA+gD8BVKvFRAB0gChsUVqteobXivts21aTudyBwZP72TlisHCU+ugOVU6cORLT4ZAAd7BTqRBLxSQEcEMnEJwcwQCQV3whAG4jk4hsDCEA0Ir4WgKRhAGOWM7JGzAPJZ89zru+ApFsAE54NasTekZy0PmcGkDQI4MWaODJuiOSrJYcZoPR160Bj2cMb08xAUwIMATgEMO5VZYy/B7BG0txpVweMIv40LAP8abkDm+UO5A5EVqDrLfQN5y37MSBdpZkAAAAASUVORK5CYII=";
    NSURL   *picURL  = [NSURL URLWithString:dataURIStr];
    NSData  *picData = [NSData dataWithContentsOfURL:picURL];
    UIImage *icon     = [UIImage imageWithData:picData];
    
    UIButton* btn = [UIButton buttonWithType:UIButtonTypeCustom];
    btn.frame = CGRectMake(100, 10, 24, 24);
    [btn setImage:icon forState:UIControlStateNormal];
    [btn addTarget:self action:@selector(onDownload:) forControlEvents:UIControlEventTouchUpInside];
    if ([isHideDownload intValue] == 0) {
        [browser setSaveBtn:btn];
    }
    browser.showStyle = GKPhotoBrowserShowStyleNone;
    browser.delegate = self;
    [browser showFromVC:[self getRootView]];
}

-(void)onDownload:(UIButton*) btn {
    NSData *data = [NSData dataWithContentsOfURL:self->downloadUrl];
    UIImage *image = [UIImage imageWithData:data];
    [[PHPhotoLibrary sharedPhotoLibrary]performChanges:^{
            [PHAssetChangeRequest creationRequestForAssetFromImage:image];
        } completionHandler:^(BOOL success, NSError * _Nullable error) {
            if (error) {
                dispatch_async(dispatch_get_main_queue(), ^{
                  UIViewController* presentedViewController = [self getRootView];
                  UIView * view = [self getToastView:presentedViewController];
                    UIView __weak *blockView = view;
                    [view makeByronToast:@"保存失败"
                           duration:LRDRCTSimpleByronToastShortDuration
                           position:CSByronToastPositionBottom
                              title:nil
                              image:nil
                              style:nil
                         completion:^(BOOL didTap) {
                             [blockView removeFromSuperview];
                         }];
                });
            } else {
                dispatch_async(dispatch_get_main_queue(), ^{
                  UIViewController* presentedViewController = [self getRootView];
                  UIView * view = [self getToastView:presentedViewController];
                    UIView __weak *blockView = view;
                    [view makeByronToast:@"保存成功"
                           duration:LRDRCTSimpleByronToastShortDuration
                           position:CSByronToastPositionBottom
                              title:nil
                              image:nil
                              style:nil
                         completion:^(BOOL didTap) {
                             [blockView removeFromSuperview];
                         }];
                });
            }
        }];
}

- (UIViewController*)visibleViewController:(UIViewController*)rootViewController {
    if (rootViewController.presentedViewController == nil) {
        return rootViewController;
    }
    if ([rootViewController.presentedViewController isKindOfClass:[UINavigationController class]]) {
        UINavigationController* navigationController = (UINavigationController*)rootViewController.presentedViewController;
        UIViewController* lastViewController = [[navigationController viewControllers] lastObject];

        return [self visibleViewController:lastViewController];
    }
    if ([rootViewController.presentedViewController isKindOfClass:[UITabBarController class]]) {
        UITabBarController* tabBarController = (UITabBarController*)rootViewController.presentedViewController;
        UIViewController* selectedViewController = tabBarController.selectedViewController;

        return [self visibleViewController:selectedViewController];
    }

    UIViewController* presentedViewController = (UIViewController*)rootViewController.presentedViewController;

    return [self visibleViewController:presentedViewController];
}

- (UIViewController*)getRootView {
    UIViewController* rootView =
        [self visibleViewController:[UIApplication sharedApplication].keyWindow.rootViewController];
    return rootView;
}

- (UIView *)getToastView: (UIViewController*) ctrl {
    UIView *root = [ctrl view];
    CGRect bound = root.bounds;
    if (bound.size.height > LRDRCTSimpleByronToastBottomOffset*2) {
        bound.origin.y += LRDRCTSimpleByronToastBottomOffset;
        bound.size.height -= LRDRCTSimpleByronToastBottomOffset*2;
    }
    UIView *view = [[UIView alloc] initWithFrame:bound];
    view.userInteractionEnabled = NO;
    [root addSubview:view];
    return view;
}

- (NSArray<NSString *> *)supportedEvents {
  return @[
      @"onChange",
  ];
}

- (void)photoBrowser:(GKPhotoBrowser *)browser didChangedIndex:(NSInteger)index {
    GKPhoto *photo = browser.photos[index];
    self->downloadUrl = photo.url;
    [self sendEventWithName:@"onChange" body:@(index)];
}

// 单击事件
- (void)photoBrowser:(GKPhotoBrowser *)browser singleTapWithIndex:(NSInteger)index {
    [browser dismiss];
}

@end
