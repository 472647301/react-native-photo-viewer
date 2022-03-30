package byron.photo.viewer;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stfalcon.frescoimageviewer.ImageViewer;


/**
 * Created by bang on 26/07/2017.
 */

public class MerryPhotoOverlay extends RelativeLayout {
    private TextView headerText;
    private RelativeLayout footer;
    private String url;
    private OnDownloadListener onDownloadListener;

    public MerryPhotoOverlay(Context context) {
        super(context);
        init();
    }

    public MerryPhotoOverlay(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MerryPhotoOverlay(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setOnDownloadListener(OnDownloadListener onDownloadListener) {
        this.onDownloadListener = onDownloadListener;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHeaderText(String text) {
        headerText.setText(text);
    }

    public void setHideHeader(Boolean hideHeader) {
        headerText.setVisibility(hideHeader? View.GONE : View.VISIBLE);
    }

    public void setHideFooter(Boolean hideFooter) {
        footer.setVisibility(hideFooter? View.GONE : View.VISIBLE);
    }

    private void init() {
        View view = inflate(getContext(), R.layout.photo_viewer_overlay, this);
        headerText = (TextView) view.findViewById(R.id.header);
        footer = (RelativeLayout) view.findViewById(R.id.footer);
        ImageButton download = (ImageButton) view.findViewById(R.id.download);

        download.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onDownloadListener != null) {
                    onDownloadListener.onDownload(url);
                }
            }
        });
    }

    public interface OnDownloadListener {
        void onDownload(String url);
    }
}

