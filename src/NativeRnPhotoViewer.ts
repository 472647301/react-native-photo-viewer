import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

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
export interface Spec extends TurboModule {
  showPhoto(
    index: number,
    images: Array<{ url: string; isVideo: boolean }>,
    options: RnPhotoViewerOptions
  ): void;
}

export default TurboModuleRegistry.getEnforcing<Spec>('RnPhotoViewer');
