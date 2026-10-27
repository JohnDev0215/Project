package com.byd.auto_emanual.image;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.byd.auto_emanual.R;
import com.byd.auto_emanual.util.Downloader;
import com.byd.auto_emanual.util.LocalPathUtil;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.video.db.VideoDbUtil;
import java.lang.ref.WeakReference;
import java.util.StringJoiner;
import org.apache.shiro.config.Ini;

/* JADX INFO: loaded from: classes.dex */
public class ImageGetter implements Html.ImageGetter {
    private static final int MSG_DOWNLOAD_COMPLETE = 1;
    private static final int MSG_DOWNLOAD_START = 0;
    private static final String TAG = "ImageGetter";
    private Context context;
    private GetterDrawable drawable;
    private TextView textView;
    Downloader.IDownloadListener listener = new Downloader.IDownloadListener() { // from class: com.byd.auto_emanual.image.ImageGetter.1
        @Override // com.byd.auto_emanual.util.Downloader.IDownloadListener
        public void onDownloadError(String path, String localPath, int from, int responseCode) {
        }

        @Override // com.byd.auto_emanual.util.Downloader.IDownloadListener
        public void onNetworkUnavailable() {
        }

        @Override // com.byd.auto_emanual.util.Downloader.IDownloadListener
        public void onDownloadStart(String path, String localPath, int from, int to, int fileSize) {
            if (from <= 0) {
                VideoDbUtil.getInstance(ImageGetter.this.context).addMediaCache(localPath, path, 0, fileSize);
            }
        }

        @Override // com.byd.auto_emanual.util.Downloader.IDownloadListener
        public void onDownloading(String path, String localPath, int from, int downloadedSize, int fileSize) {
            VideoDbUtil.getInstance(ImageGetter.this.context).updateCachePos(path, from + downloadedSize);
        }

        @Override // com.byd.auto_emanual.util.Downloader.IDownloadListener
        public void onDownloadComplete(String path, String localPath) {
            MLog.d(ImageGetter.TAG, "path = " + path + "\n, localPath " + localPath);
            ImageGetter.this.handler.obtainMessage(1, ImageGetter.this.new DownloadData(path, localPath)).sendToTarget();
            VideoDbUtil.getInstance(ImageGetter.this.context).changeCacheState(path, 1);
        }
    };
    private ImageHandler handler = new ImageHandler(this);

    public ImageGetter(Context context, TextView aaa) {
        this.context = context;
        this.textView = aaa;
    }

    @Override // android.text.Html.ImageGetter
    public Drawable getDrawable(String source) {
        String str = TAG;
        MLog.d(str, "getDrawable " + source);
        this.drawable = new GetterDrawable();
        Drawable drawable = ContextCompat.getDrawable(this.context, R.drawable.loading_30);
        int intrinsicHeight = drawable.getIntrinsicHeight();
        drawable.setBounds(0, 0, intrinsicHeight, intrinsicHeight);
        MLog.d(str, "" + this.drawable.getIntrinsicWidth() + ", " + this.drawable.getIntrinsicWidth());
        this.drawable.setDrawable(drawable);
        LocalPathUtil.getLocalPath(this.context, source, this.listener);
        return this.drawable;
    }

    class GetterDrawable extends BitmapDrawable {
        private Drawable drawable;

        GetterDrawable() {
        }

        @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            super.draw(canvas);
            MLog.d(ImageGetter.TAG, "draw");
            Drawable drawable = this.drawable;
            if (drawable != null) {
                drawable.draw(canvas);
            }
        }

        public void setDrawable(Drawable drawable) {
            this.drawable = drawable;
        }

        public Drawable getDrawable() {
            return this.drawable;
        }
    }

    class DownloadData {
        String localPath;
        String path;

        DownloadData(String path, String localPath) {
            this.path = path;
            this.localPath = localPath;
        }

        public String toString() {
            return new StringJoiner(", ", DownloadData.class.getSimpleName() + Ini.SECTION_PREFIX, Ini.SECTION_SUFFIX).add("path='" + this.path + "'").add("localPath='" + this.localPath + "'").toString();
        }
    }

    static class ImageHandler extends Handler {
        WeakReference<ImageGetter> getter;

        public ImageHandler(ImageGetter imageGetter) {
            this.getter = new WeakReference<>(imageGetter);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what != 1) {
                return;
            }
            this.getter.get().updateDrawable((DownloadData) msg.obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDrawable(DownloadData data) {
        String str = TAG;
        MLog.d(str, data.toString());
        if (TextUtils.isEmpty(data.localPath)) {
            MLog.i(str, "can't get localPath");
            return;
        }
        BitmapDrawable bitmapDrawable = (BitmapDrawable) Drawable.createFromPath(data.localPath);
        if (bitmapDrawable == null) {
            MLog.i(str, "can't get drawable for");
            return;
        }
        int width = (this.textView.getWidth() - bitmapDrawable.getIntrinsicWidth()) / 2;
        int intrinsicWidth = bitmapDrawable.getIntrinsicWidth() + width;
        MLog.d(str, "updateDrawable textView.getWidth() = " + this.textView.getWidth() + " textView.getHeight() = " + this.textView.getHeight() + " drawable.getIntrinsicWidth() = " + bitmapDrawable.getIntrinsicWidth() + " drawable.getIntrinsicHeight() = " + bitmapDrawable.getIntrinsicHeight() + " l = " + width + " r = " + intrinsicWidth);
        bitmapDrawable.setBounds(width, 0, intrinsicWidth, bitmapDrawable.getIntrinsicHeight());
        this.drawable.setDrawable(bitmapDrawable);
        this.textView.setText(this.textView.getText());
        TextView textView = this.textView;
        textView.setHeight(textView.getHeight() + bitmapDrawable.getIntrinsicHeight());
        this.textView.invalidate();
    }
}
