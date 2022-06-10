import { NativeModules, NativeEventEmitter, Platform } from "react-native";

const { ByronPhotoView } = NativeModules;
const emitter = new NativeEventEmitter(
  Platform.OS === "ios" ? ByronPhotoView : null
);

class PhotoView {
  static onChange;

  static show(params) {
    ByronPhotoView.show({
      list: params.list,
      index: params.index || 0,
      isHideDownload: params.isHideDownload ? 1 : 0,
    });
    if (params.onChange) {
      this.onChange = params.onChange;
    }
  }
}

emitter.addListener("onChange", (index) => {
  PhotoView.onChange && PhotoView.onChange(index);
});

export default PhotoView;
