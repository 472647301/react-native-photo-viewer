import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

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
export interface Spec extends TurboModule {
  showPhoto(
    index: number,
    images: RnPhotoViewerItem[],
    options: RnPhotoViewerOptions
  ): void;
}

export default TurboModuleRegistry.getEnforcing<Spec>('RnPhotoViewer');
