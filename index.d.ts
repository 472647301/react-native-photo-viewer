declare module "@byron-react-native/photo-viewer" {
  import { ImageSourcePropType } from "react-native";
  export interface PhotoProps {
    initial: number;
    hideHeader: boolean;
    hideFooter: boolean;
    hideStatusBar: boolean;
    data: Array<{ source: ImageSourcePropType }>;
    onChange: (index: number) => void;
    onDownload: (url: string) => void;
  }
  export class PhotoView extends React.Component {
    show(props: Partial<PhotoProps>): void;
    hide(): void;
  }

  export class PhotoModal {
    static show(props: Partial<PhotoProps>): void;
    static hide(): void;
  }

  class PhotoViewModal extends React.Component {}

  export default PhotoViewModal;
}
