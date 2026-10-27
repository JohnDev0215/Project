package com.byd.auto_emanual.image;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import com.byd.auto_emanual.R;
import com.byd.auto_emanual.util.Downloader;
import com.byd.auto_emanual.util.LoadingUtil;
import com.byd.auto_emanual.util.LocalPathUtil;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.ViewUtil;
import com.byd.auto_emanual.video.db.VideoDbUtil;
import java.io.File;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class KImageView extends AppCompatImageView {
    private static final int MSG_DOWNLOAD_COMPLETE = 1;
    private static final int MSG_DOWNLOAD_START = 0;
    private static final int MSG_NETWORK_UNAVAILABLE = 2;
    private static final String TAG = "KImageView";
    private boolean calculateHeight;
    private int errorResId;
    private ImageHandler handler;
    private Downloader.IDownloadListener listener;
    private int loadingResId;

    public KImageView(Context context) {
        this(context, null);
    }

    public KImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        ViewUtil.setViewCorner(this, getResources().getDimension(R.dimen.recommend_video_radius));
    }

    public KImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.loadingResId = R.drawable.loading_30;
        this.errorResId = R.drawable.net_error_image;
        this.listener = new Downloader.IDownloadListener() { // from class: com.byd.auto_emanual.image.KImageView.1
            private static final int RETRY_COUNT = 3;
            private int count;

            @Override // com.byd.auto_emanual.util.Downloader.IDownloadListener
            public void onDownloadStart(String path, String localPath, int from, int to, int fileSize) {
                KImageView.this.handler.sendEmptyMessage(0);
                if (from <= 0) {
                    VideoDbUtil.getInstance(KImageView.this.getContext()).addMediaCache(localPath, path, 0, fileSize);
                }
            }

            @Override // com.byd.auto_emanual.util.Downloader.IDownloadListener
            public void onDownloading(String path, String localPath, int from, int downloadedSize, int fileSize) {
                VideoDbUtil.getInstance(KImageView.this.getContext()).updateCachePos(path, from + downloadedSize);
            }

            @Override // com.byd.auto_emanual.util.Downloader.IDownloadListener
            public void onDownloadError(String path, String localPath, int from, int responseCode) {
                int i = this.count + 1;
                this.count = i;
                if (i > 3) {
                    MLog.e(KImageView.TAG, "onDownloadError. path = " + path);
                } else {
                    LocalPathUtil.getLocalPath(KImageView.this.getContext(), path, this);
                }
            }

            @Override // com.byd.auto_emanual.util.Downloader.IDownloadListener
            public void onDownloadComplete(String path, String localPath) {
                KImageView.this.handler.sendMessage(KImageView.this.handler.obtainMessage(1, localPath));
                VideoDbUtil.getInstance(KImageView.this.getContext()).changeCacheState(path, 1);
            }

            @Override // com.byd.auto_emanual.util.Downloader.IDownloadListener
            public void onNetworkUnavailable() {
                KImageView.this.handler.sendMessage(KImageView.this.handler.obtainMessage(2));
            }
        };
        this.handler = new ImageHandler(Looper.getMainLooper(), this);
    }

    public void setLoadingResId(int loadingResId) {
        this.loadingResId = loadingResId;
    }

    public void setCalculateHeight(boolean calculateHeight) {
        this.calculateHeight = calculateHeight;
    }

    public void setErrorResId(int errorResId) {
        this.errorResId = errorResId;
    }

    public void setImagePath(String path) {
        LocalPathUtil.getLocalPath(getContext(), path, this.listener);
    }

    public static class ImageHandler extends Handler {
        private WeakReference<KImageView> kImage;

        public ImageHandler(Looper looper, KImageView view) {
            super(looper);
            this.kImage = new WeakReference<>(view);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            if (i == 0) {
                if (this.kImage.get() != null) {
                    this.kImage.get().setImageDrawable(ContextCompat.getDrawable(this.kImage.get().getContext(), this.kImage.get().loadingResId));
                    LoadingUtil.startLoading(this.kImage.get().getContext(), this.kImage.get());
                    return;
                }
                return;
            }
            if (i == 1) {
                if (this.kImage.get() != null) {
                    String str = (String) msg.obj;
                    if (str != null) {
                        LoadingUtil.stopLoading(this.kImage.get());
                        this.kImage.get().setImageURI(Uri.fromFile(new File(str)));
                        this.kImage.get().setTag(str);
                        this.kImage.get().calculateHeight(this.kImage.get());
                        return;
                    }
                    this.kImage.get().setTag("");
                    return;
                }
                return;
            }
            if (i == 2 && this.kImage.get() != null) {
                LoadingUtil.stopLoading(this.kImage.get());
                this.kImage.get().setImageDrawable(ContextCompat.getDrawable(this.kImage.get().getContext(), this.kImage.get().errorResId));
                this.kImage.get().setTag(Integer.valueOf(this.kImage.get().errorResId));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void calculateHeight(KImageView imageView) {
        if (this.calculateHeight) {
            int intrinsicWidth = imageView.getDrawable().getIntrinsicWidth();
            int intrinsicHeight = imageView.getDrawable().getIntrinsicHeight();
            int width = imageView.getWidth();
            if (intrinsicWidth > width) {
                float f = (intrinsicWidth * 1.0f) / width;
                float f2 = (intrinsicHeight * 1.0f) / f;
                MLog.d(TAG, "scale = " + f);
                ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                layoutParams.height = (int) f2;
                imageView.setLayoutParams(layoutParams);
            }
        }
    }
}
