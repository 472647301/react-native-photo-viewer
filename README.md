# rn-photo-viewer

rn-photo-viewer

## Installation

```sh
npm install rn-photo-viewer2
```

## Usage

```js
import { View, StyleSheet, Button } from 'react-native';
import { showPhoto } from 'rn-photo-viewer';

const photos = [
  'https://images.pexels.com/photos/45170/kittens-cat-cat-puppy-rush-45170.jpeg',
  'https://kaiguang-assets.ibanquan.com/gxuqtvv6tfd4xik9la0ulrl1k6t8',
  'https://stream7.iqilu.com/10339/upload_transcode/202002/09/20200209104902N3v5Vpxuvb.mp4',
  'https://images.pexels.com/photos/142615/pexels-photo-142615.jpeg',
  'https://images.pexels.com/photos/248261/pexels-photo-248261.jpeg',
  'https://media.giphy.com/media/3o6vXWzHtGfMR3XoXu/giphy.gif',
];

export default function App() {
  const onPress = () => {
    showPhoto(
      0,
      photos.map((url) => ({ url, isVideo: url.split('.').pop() === 'mp4' })),
      { isHidesSavedBtn: false }
    );
  };
  return (
    <View style={styles.container}>
      <Button title="showPhoto" onPress={onPress} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
```

## Props

| Prop | Type | Description | Platform | Default |
| ------ | ------ | ------ | ------ | ------ |  
| isVideoReplay | `` boolean `` |  Automatic video replay | iOS  | `` false `` |

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)
