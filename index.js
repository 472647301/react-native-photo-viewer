import { NativeModules, NativeEventEmitter } from "react-native";

const { ByronPhotoViewModule } = NativeModules;
const emitter = new NativeEventEmitter(ByronPhotoViewModule);

class PhotoView {
  static onChange;

  static show(params) {
    ByronPhotoViewModule.show({
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
