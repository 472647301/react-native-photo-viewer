import { NativeModules, NativeEventEmitter } from "react-native";

const { ByronPhotoView } = NativeModules;
const emitter = new NativeEventEmitter(ByronPhotoView);

class PhotoView {
  static onChange;

  static show(params) {
    ByronPhotoView.show({
      list: params.list,
      index: params.index,
    });
    this.onChange = params.onChange;
  }
}

emitter.addListener("onChange", (index) => {
  PhotoView.onChange && PhotoView.onChange(index);
});

export default PhotoView;
