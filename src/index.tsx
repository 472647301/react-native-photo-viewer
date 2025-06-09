import RnPhotoViewer from './NativeRnPhotoViewer';

export interface RnPhotoViewerOptions {
  isVideoReplay: boolean;
  isStatusBarShow: boolean;
  isHidesSavedBtn: boolean;
  isHideSourceView: boolean;
  /** 是否禁止上滑消失，默认NO */
  isUpSlideDismissDisabled: boolean;
}

export interface RnPhotoViewerItem {
  url: string;
  isVideo: boolean;
}

export function showPhoto(
  index: number,
  images: RnPhotoViewerItem[],
  options?: Partial<RnPhotoViewerOptions>
): void {
  return RnPhotoViewer.showPhoto(index, images, {
    isVideoReplay: false,
    isStatusBarShow: false,
    isHidesSavedBtn: false,
    isHideSourceView: true,
    isUpSlideDismissDisabled: false,
    ...options,
  });
}
