//
//  MerryPhotoData.m
//  MerryPhotoViewer
//
//  Created by bang on 27/07/2017.
//  Copyright © 2017 Merryjs.com. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "MerryPhotoData.h"

NSString* const kMerryPhotoSource = @"source";

@interface MerryPhotoData ()
@end
@implementation MerryPhotoData

/**
 * Instantiate the instance using the passed dictionary values to set the properties values
 */

- (instancetype)initWithDictionary:(NSDictionary*)dictionary
{
    self = [super init];
    if (![dictionary[kMerryPhotoSource] isKindOfClass:[NSNull class]]) {
        self.source = [RCTConvert RCTImageSource: dictionary[kMerryPhotoSource]];
    }
    return self;
}
@end
