declare module "@byron-react-native/photo-viewer" {
  class PhotoView {
    static show(params: {
      list: string[];
      index?: number;
      onChange?: (index: number) => void;
    }): void;
  }
  export default PhotoView;
}
