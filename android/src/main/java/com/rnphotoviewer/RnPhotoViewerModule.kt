package com.rnphotoviewer

import cc.shinichi.library.ImagePreview
import cc.shinichi.library.bean.ImageInfo
import cc.shinichi.library.bean.Type
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.module.annotations.ReactModule

@ReactModule(name = RnPhotoViewerModule.NAME)
class RnPhotoViewerModule(reactContext: ReactApplicationContext) :
  NativeRnPhotoViewerSpec(reactContext)  {

  override fun getName(): String {
    return NAME
  }

  // Example method
  // See https://reactnative.dev/docs/native-modules-android
  override fun showPhoto(index: Double, images: ReadableArray, options: ReadableMap) {
    val imageInfoList: MutableList<ImageInfo> = ArrayList()
    for (i in 0 until images.size()) {
      val item = images.getMap(i) ?: continue
      val imageInfo = ImageInfo()
      val isVideo = item.getBoolean("isVideo")
      if (isVideo) {
        imageInfo.type =Type.VIDEO // 指定媒体类型：VIDEO/IMAGE
      } else {
        imageInfo.type =Type.IMAGE
      }
      imageInfo.originUrl = item.getString("url").toString() // 原图url
      imageInfoList.add(imageInfo)
    }
    currentActivity?.let {
      ImagePreview.instance.with(it)
        .setLoadStrategy(ImagePreview.LoadStrategy.AlwaysOrigin)
        .setMediaInfoList(imageInfoList)
        .start()
    }
  }

  companion object {
    const val NAME = "RnPhotoViewer"
  }
}
