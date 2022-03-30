
package byron.photo.viewer;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MerryPhotoViewManager extends SimpleViewManager<MerryPhotoView> {
    public static final String REACT_CLASS = "MerryPhotoView";
    private Context context;

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    public MerryPhotoView createViewInstance(@NonNull ThemedReactContext context) {
        return new MerryPhotoView(context);
    }

    @Override
    public void onDropViewInstance(@NonNull MerryPhotoView view) {
        super.onDropViewInstance(view);
    }

    @Override
    protected void onAfterUpdateTransaction(@NonNull MerryPhotoView merryPhotoView) {
        super.onAfterUpdateTransaction(merryPhotoView);
        merryPhotoView.init();
    }

    @ReactProp(name = "data")
    public void setData(MerryPhotoView merryPhotoView, @Nonnull ReadableArray prop) {
        MerryPhotoData[] merryPhotoDatas = new MerryPhotoData[]{};
        ArrayList<MerryPhotoData> list = new ArrayList<>();
        for (int i = 0; i < prop.size(); i++) {
            try {
                MerryPhotoData merryPhotoData = new MerryPhotoData() {};
                ReadableMap rm = prop.getMap(i);
                if (rm.hasKey("source")) {
                    merryPhotoData.source = rm.getMap("source");
                }
                list.add(merryPhotoData);
            } catch (Exception e) {
                Log.e("PHOTO_VIEWER: ", e.toString());
            }
        }
        merryPhotoView.setData(list.toArray(merryPhotoDatas));
    }

    @ReactProp(name = "initial")
    public void setInitial(MerryPhotoView merryPhotoView, int prop) {
        merryPhotoView.setInitial(prop);
    }

    @ReactProp(name = "hideStatusBar")
    public void setHideStatusBar(MerryPhotoView merryPhotoView, Boolean prop) {
        merryPhotoView.setHideStatusBar(prop);
    }

    @ReactProp(name = "hideHeader")
    public void setHideHeader(MerryPhotoView merryPhotoView, Boolean prop) {
        merryPhotoView.setHideHeader(prop);
    }

    @ReactProp(name = "hideFooter")
    public void setHideFooter(MerryPhotoView merryPhotoView, Boolean prop) {
        merryPhotoView.setHideFooter(prop);
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.<String, Object>builder()
                .put("onChange", MapBuilder.of("registrationName", "onChange"))
                .put("onDownload", MapBuilder.of("registrationName", "onDownload"))
                .put("onDismiss", MapBuilder.of("registrationName", "onDismiss")).build();
    }
}
