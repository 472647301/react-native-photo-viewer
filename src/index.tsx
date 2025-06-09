import RnPhotoViewer from './NativeRnPhotoViewer';

export interface RnPhotoViewerOptions {
  failureText: string;
  videoFailureText: string;
  isVideoReplay: boolean;
  isStatusBarShow: boolean;
  isHidesSavedBtn: boolean;
  isHideSourceView: boolean;
  /** 是否禁止上滑消失，默认NO */
  isUpSlideDismissDisabled: boolean;
}

export function showPhoto(
  index: number,
  images: Array<{ url: string; isVideo: boolean }>,
  options?: Partial<RnPhotoViewerOptions>
): void {
  return RnPhotoViewer.showPhoto(index, images, {
    failureText: 'loading failed',
    videoFailureText: 'loading failed',
    isVideoReplay: false,
    isStatusBarShow: false,
    isHidesSavedBtn: false,
    isHideSourceView: true,
    isUpSlideDismissDisabled: false,
    ...options,
  });
}
