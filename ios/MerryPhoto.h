//
//  MerryPhoto.h
//  MerryPhotoViewer
//
//  Created by bang on 15/07/2017.
//  Copyright © 2017 Merryjs.com. All rights reserved.
//

#ifndef MerryPhoto_h
#define MerryPhoto_h
#import <Foundation/Foundation.h>
#import <NYTPhoto.h>

@interface MerryPhoto : NSObject<NYTPhoto>

@property(nonatomic) UIImage *image;
@property(nonatomic) NSData *imageData;
@property(nonatomic) UIImage *placeholderImage;

@end

#endif /* MerryPhoto_h */
