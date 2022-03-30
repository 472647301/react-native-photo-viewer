import React, { useRef, useState } from "react";
import { requireNativeComponent } from "react-native";
import { useImperativeHandle, forwardRef } from "react";

const RNMerryPhotoView = requireNativeComponent("MerryPhotoView");

export const PhotoView = forwardRef((_, ref) => {
  const props = useRef({});
  const current = useRef(0);
  const [visible, setVisible] = useState(false);

  useImperativeHandle(ref, () => ({
    show: (args) => {
      if (!args.data) {
        return;
      }
      if (!args.data.length) {
        return;
      }
      props.current = args;
      setVisible(true);
    },
    hide: () => {
      onDismiss();
    },
  }));

  const onChange = (event) => {
    const { index } = event.nativeEvent;
    current.current = index;
    if (!props.current.onChange) {
      return;
    }
    props.current.onChange(index);
  };

  const onDownload = () => {
    if (!props.current.onDownload) {
      return;
    }
    const item = props.current.data[current.current] || {};
    props.current.onDownload(item.source.uri);
  };

  const onDismiss = () => {
    props.current = {};
    setVisible(false);
  };

  if (!visible) {
    return null;
  }
  return (
    <RNMerryPhotoView
      {...props.current}
      onChange={onChange}
      onDismiss={onDismiss}
      onDownload={onDownload}
      onClose={onDismiss}
    />
  );
});

export const PhotoModal = {
  ref: null,
  show: function (props) {
    this.ref?.show(props);
  },
  hide: function () {
    this.ref?.hide();
  },
};

const PhotoViewModal = () => {
  return <PhotoView ref={(ref) => (PhotoModal.ref = ref)} />;
};

export default PhotoViewModal;
