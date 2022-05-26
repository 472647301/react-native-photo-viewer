#import <UIKit/UIKit.h>

extern const NSString * CSByronToastPositionTop;
extern const NSString * CSByronToastPositionCenter;
extern const NSString * CSByronToastPositionBottom;

@class CSByronToastStyle;

@interface UIView (ByronToast)

- (void)makeByronToast:(NSString *)message;

- (void)makeByronToast:(NSString *)message
         duration:(NSTimeInterval)duration
         position:(id)position;

- (void)makeByronToast:(NSString *)message
         duration:(NSTimeInterval)duration
         position:(id)position
            style:(CSByronToastStyle *)style;

- (void)makeByronToast:(NSString *)message
         duration:(NSTimeInterval)duration
         position:(id)position
            title:(NSString *)title
            image:(UIImage *)image
            style:(CSByronToastStyle *)style
       completion:(void(^)(BOOL didTap))completion;

- (UIView *)toastViewForMessage:(NSString *)message
                          title:(NSString *)title
                          image:(UIImage *)image
                          style:(CSByronToastStyle *)style;

- (void)hideByronToast;

- (void)hideByronToast:(UIView *)toast;

- (void)hideAllByronToasts:(BOOL)includeActivity clearQueue:(BOOL)clearQueue;

- (void)makeByronToastActivity:(id)position;

- (void)showByronToast:(UIView *)toast
         duration:(NSTimeInterval)duration
         position:(id)position
       completion:(void(^)(BOOL didTap))completion;

@end

@interface CSByronToastStyle : NSObject

@property (strong, nonatomic) UIColor *backgroundColor;

@property (strong, nonatomic) UIColor *titleColor;

@property (strong, nonatomic) UIColor *messageColor;

@property (assign, nonatomic) CGFloat maxWidthPercentage;

@property (assign, nonatomic) CGFloat maxHeightPercentage;

@property (assign, nonatomic) CGFloat horizontalPadding;

@property (assign, nonatomic) CGFloat verticalPadding;

@property (assign, nonatomic) CGFloat cornerRadius;

@property (strong, nonatomic) UIFont *titleFont;

@property (strong, nonatomic) UIFont *messageFont;

@property (assign, nonatomic) NSTextAlignment titleAlignment;

@property (assign, nonatomic) NSTextAlignment messageAlignment;

@property (assign, nonatomic) NSInteger titleNumberOfLines;

@property (assign, nonatomic) NSInteger messageNumberOfLines;

@property (assign, nonatomic) BOOL displayShadow;

@property (strong, nonatomic) UIColor *shadowColor;

@property (assign, nonatomic) CGFloat shadowOpacity;

@property (assign, nonatomic) CGFloat shadowRadius;

@property (assign, nonatomic) CGSize shadowOffset;

@property (assign, nonatomic) CGSize imageSize;

@property (assign, nonatomic) CGSize activitySize;

@property (assign, nonatomic) NSTimeInterval fadeDuration;

- (instancetype)initWithDefaultStyle NS_DESIGNATED_INITIALIZER;

- (instancetype)init NS_UNAVAILABLE;

@end

@interface CSByronToastManager : NSObject

+ (void)setSharedStyle:(CSByronToastStyle *)sharedStyle;

+ (CSByronToastStyle *)sharedStyle;

+ (void)setTapToDismissEnabled:(BOOL)tapToDismissEnabled;

+ (BOOL)isTapToDismissEnabled;

+ (void)setQueueEnabled:(BOOL)queueEnabled;

+ (BOOL)isQueueEnabled;

+ (void)setDefaultDuration:(NSTimeInterval)duration;

+ (NSTimeInterval)defaultDuration;

+ (void)setDefaultPosition:(id)position;

+ (id)defaultPosition;

@end
