
package byron.photo.viewer;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cc.shinichi.library.ImagePreview;
import cc.shinichi.library.view.listener.OnBigImagePageChangeListener;

public class ByronPhotoViewModule extends ReactContextBaseJavaModule {
    ReactApplicationContext mContext;
    private DeviceEventManagerModule.RCTDeviceEventEmitter mEmitter;

    public ByronPhotoViewModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.mContext = reactContext;
    }

    @NonNull
    @Override
    public String getName() {
        return "ByronPhotoView";
    }

    @ReactMethod
    public void show(ReadableMap params) {
        ReadableArray rnList = params.getArray("list");
        List<String> imageList = new ArrayList<>();
        boolean isHideDownload = params.getBoolean("isHideDownload");
        mEmitter = mContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
        for (int i = 0; i < Objects.requireNonNull(rnList).size(); i++) {
            imageList.add(rnList.getString(i));
        }

        ImagePreview.getInstance().setContext(Objects.requireNonNull(getCurrentActivity()))
                .setIndex(params.getInt("index"))
                .setImageList(imageList)
                .setShowDownButton(isHideDownload)
                .setBigImagePageChangeListener(new OnBigImagePageChangeListener() {
                    @Override
                    public void onPageScrolled(int i, float v, int i1) {

                    }

                    @Override
                    public void onPageSelected(int i) {
                        mEmitter.emit("onChange", i);
                    }

                    @Override
                    public void onPageScrollStateChanged(int i) {

                    }
                })
                .start();
    }
}
