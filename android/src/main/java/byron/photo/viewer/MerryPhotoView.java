
package byron.photo.viewer;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.stfalcon.frescoimageviewer.ImageViewer;

/**
 * Created by bang on 07/08/2017.
 */

public class MerryPhotoView extends View implements MerryPhotoOverlay.OnDownloadListener {
    protected ImageViewer.Builder builder;
    protected MerryPhotoData[] data;
    protected int initial;
    protected boolean hideStatusBar;
    protected boolean hideHeader;
    protected boolean hideFooter;
    private MerryPhotoOverlay overlayView;

    public MerryPhotoData[] getData() {
        return data;
    }

    public int getInitial() {
        return initial;
    }

    public void setData(MerryPhotoData[] data) {
        this.data = data;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public boolean isHideStatusBar() {
        return hideStatusBar;
    }

    public boolean isHideHeader() {
        return hideHeader;
    }

    public boolean isHideFooter() {
        return hideFooter;
    }

    public void setHideStatusBar(boolean hideStatusBar) {
        this.hideStatusBar = hideStatusBar;
    }

    public void setHideHeader(boolean hideHeader) {
        this.hideHeader = hideHeader;
    }

    public void setHideFooter(boolean hideFooter) {
        this.hideFooter = hideFooter;
    }

    public MerryPhotoView(Context context) {
        super(context);
    }

    protected void init() {
        if (builder != null) {
            return;
        }
        final Context context = getContext();
        builder = new ImageViewer.Builder(context, getData());
        builder.setFormatter(new ImageViewer.Formatter<MerryPhotoData>() {
            @Override
            public String format(MerryPhotoData o) {
                return o.source.getString("uri");
            }
        });
        builder.setOnDismissListener(getDismissListener());

        builder.setImageChangeListener(getImageChangeListener());
        builder.setStartPosition(getInitial());
        builder.hideStatusBar(isHideStatusBar());
        builder.setCustomDraweeHierarchyBuilder(progressBarDrawableBuilder());
        overlayView = new MerryPhotoOverlay(context);
        overlayView.setHideHeader(hideHeader);
        overlayView.setHideFooter(hideFooter);
        overlayView.setOnDownloadListener(this);
        builder.setOverlayView(overlayView);
        ImageViewer imageViewer = builder.build();
        imageViewer.show();
    }

    private ImageViewer.OnImageChangeListener getImageChangeListener() {
        return new ImageViewer.OnImageChangeListener() {
            @Override
            public void onImageChange(int position) {
                final MerryPhotoData merryPhotoData = getData()[position];
                String url = merryPhotoData.source.getString("uri");
                overlayView.setUrl(url);
                overlayView.setHeaderText((position + 1) + " / " + getData().length);
                // onChange event from js side
                WritableMap map = Arguments.createMap();
                map.putInt("index", position);
                onNavigateToPhoto(map);
            }
        };
    }

    /**
     * on dismiss
     */
    protected void onDialogDismiss() {
        final Context context = getContext();
        builder = null;
        overlayView = null;
        if (context instanceof ReactContext) {
            ((ReactContext) context).getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onDismiss", null);
        }
    }

    /**
     * on photo change
     */
    protected void onNavigateToPhoto(WritableMap map) {
        final Context context = getContext();
        if (context instanceof ReactContext) {
            ((ReactContext) context).getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onChange", map);
        }
    }

    private ImageViewer.OnDismissListener getDismissListener() {
        return new ImageViewer.OnDismissListener() {
            @Override
            public void onDismiss() {
                onDialogDismiss();
            }
        };
    }

    private GenericDraweeHierarchyBuilder progressBarDrawableBuilder() {
        return GenericDraweeHierarchyBuilder.newInstance(getResources())
                .setProgressBarImage(
                        new CircleProgressBarDrawable()
                );
    }

    public void onDownloadImage(String url) {
        WritableMap map = Arguments.createMap();
        map.putString("url", url);
        final Context context = getContext();
        if (context instanceof ReactContext) {
            ((ReactContext) context).getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onDownload", map);
        }
    }

    @Override
    public void onDownload(String url) {
        onDownloadImage(url);
    }
}
