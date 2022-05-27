# react-native-photo-viewer

## Getting started

`$ npm install @byron-react-native/photo-viewer --save`

### Or

`$ yarn add @byron-react-native/photo-viewer`

## Usage
```javascript
import React, {Component} from 'react';
import {StyleSheet, Text, View, TouchableOpacity} from 'react-native';
import PhotoView from '@byron-react-native/photo-viewer';

const BaseUrl = 'https://images.pexels.com/photos';

const photos = [
  `${BaseUrl}/45170/kittens-cat-cat-puppy-rush-45170.jpeg`,
  'https://kaiguang-assets.ibanquan.com/gxuqtvv6tfd4xik9la0ulrl1k6t8',
  `${BaseUrl}/142615/pexels-photo-142615.jpeg`,
  `${BaseUrl}/248261/pexels-photo-248261.jpeg`,
  `https://media.giphy.com/media/3o6vXWzHtGfMR3XoXu/giphy.gif`,
];

export default class App extends Component {
  onShow = async () => {
    PhotoView.show({
      list: photos,
      index: 1,
      onChange: index => {
        console.log(' >> onChange', index);
      },
    });
  };

  render() {
    return (
      <View style={styles.page}>
        <TouchableOpacity style={styles.button} onPress={this.onShow}>
          <Text style={styles.text}>Show Photo Modal</Text>
        </TouchableOpacity>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  page: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  button: {
    width: 200,
    height: 48,
    borderRadius: 24,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: '#2da44e',
  },
  text: {
    fontSize: 18,
    color: '#fff',
  },
});
```
