package org.chromium.media;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Base64InputStream;
import android.view.Surface;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("media")
public class MediaPlayerBridge {
    private static final String TAG = "MediaPlayerBridge";
    private static ResourceLoadingFilter sResourceLoadFilter;
    private LoadDataUriTask mLoadDataUriTask;
    private long mNativeMediaPlayerBridge;
    private MediaPlayer mPlayer;

    public static class ResourceLoadingFilter {
        public boolean shouldOverrideResourceLoading(MediaPlayer mediaPlayer, Context context, Uri uri) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeOnDidSetDataUriDataSource(long j, boolean z);

    public static void setResourceLoadingFilter(ResourceLoadingFilter resourceLoadingFilter) {
        sResourceLoadFilter = resourceLoadingFilter;
    }

    private static MediaPlayerBridge create(long j) {
        return new MediaPlayerBridge(j);
    }

    protected MediaPlayerBridge(long j) {
        this.mNativeMediaPlayerBridge = j;
    }

    protected MediaPlayerBridge() {
    }

    protected void destroy() {
        LoadDataUriTask loadDataUriTask = this.mLoadDataUriTask;
        if (loadDataUriTask != null) {
            loadDataUriTask.cancel(true);
            this.mLoadDataUriTask = null;
        }
        this.mNativeMediaPlayerBridge = 0L;
    }

    protected MediaPlayer getLocalPlayer() {
        if (this.mPlayer == null) {
            this.mPlayer = new MediaPlayer();
        }
        return this.mPlayer;
    }

    protected void setSurface(Surface surface) {
        getLocalPlayer().setSurface(surface);
    }

    protected boolean prepareAsync() {
        try {
            getLocalPlayer().prepareAsync();
            return true;
        } catch (IllegalStateException e) {
            Log.e(TAG, "Unable to prepare MediaPlayer.", e);
            return false;
        } catch (Exception e2) {
            Log.e(TAG, "Unable to prepare MediaPlayer.", e2);
            return false;
        }
    }

    protected boolean isPlaying() {
        return getLocalPlayer().isPlaying();
    }

    protected boolean hasVideo() {
        return hasTrack(1);
    }

    protected boolean hasAudio() {
        return hasTrack(2);
    }

    private boolean hasTrack(int i) {
        try {
            MediaPlayer.TrackInfo[] trackInfo = getLocalPlayer().getTrackInfo();
            if (trackInfo.length == 0) {
                return true;
            }
            for (MediaPlayer.TrackInfo trackInfo2 : trackInfo) {
                if (i == trackInfo2.getTrackType() || trackInfo2.getTrackType() == 0) {
                    return true;
                }
            }
            return false;
        } catch (RuntimeException unused) {
            return true;
        }
    }

    protected int getVideoWidth() {
        return getLocalPlayer().getVideoWidth();
    }

    protected int getVideoHeight() {
        return getLocalPlayer().getVideoHeight();
    }

    protected int getCurrentPosition() {
        return getLocalPlayer().getCurrentPosition();
    }

    protected int getDuration() {
        return getLocalPlayer().getDuration();
    }

    protected void release() {
        getLocalPlayer().release();
    }

    protected void setVolume(double d) {
        float f = (float) d;
        getLocalPlayer().setVolume(f, f);
    }

    protected void start() {
        getLocalPlayer().start();
    }

    protected void pause() {
        getLocalPlayer().pause();
    }

    protected void seekTo(int i) throws IllegalStateException {
        getLocalPlayer().seekTo(i);
    }

    protected boolean setDataSource(Context context, String str, String str2, String str3, boolean z) {
        Uri uri = Uri.parse(str);
        HashMap map = new HashMap();
        if (z) {
            map.put("x-hide-urls-from-log", "true");
        }
        if (!TextUtils.isEmpty(str2)) {
            map.put("Cookie", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            map.put("User-Agent", str3);
        }
        map.put("allow-cross-domain-redirect", "false");
        try {
            ResourceLoadingFilter resourceLoadingFilter = sResourceLoadFilter;
            if (resourceLoadingFilter != null && resourceLoadingFilter.shouldOverrideResourceLoading(getLocalPlayer(), context, uri)) {
                return true;
            }
            getLocalPlayer().setDataSource(context, uri, map);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    protected boolean setDataSourceFromFd(int i, long j, long j2) {
        try {
            ParcelFileDescriptor parcelFileDescriptorAdoptFd = ParcelFileDescriptor.adoptFd(i);
            getLocalPlayer().setDataSource(parcelFileDescriptorAdoptFd.getFileDescriptor(), j, j2);
            parcelFileDescriptorAdoptFd.close();
            return true;
        } catch (IOException e) {
            Log.e(TAG, "Failed to set data source from file descriptor: " + e, new Object[0]);
            return false;
        }
    }

    protected boolean setDataUriDataSource(Context context, String str) {
        int iIndexOf;
        LoadDataUriTask loadDataUriTask = this.mLoadDataUriTask;
        if (loadDataUriTask != null) {
            loadDataUriTask.cancel(true);
            this.mLoadDataUriTask = null;
        }
        if (!str.startsWith("data:") || (iIndexOf = str.indexOf(44)) == -1) {
            return false;
        }
        String strSubstring = str.substring(0, iIndexOf);
        String strSubstring2 = str.substring(iIndexOf + 1);
        String[] strArrSplit = strSubstring.substring(5).split(";");
        if (strArrSplit.length != 2 || !"base64".equals(strArrSplit[1])) {
            return false;
        }
        LoadDataUriTask loadDataUriTask2 = new LoadDataUriTask(context, strSubstring2);
        this.mLoadDataUriTask = loadDataUriTask2;
        loadDataUriTask2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        return true;
    }

    private class LoadDataUriTask extends AsyncTask<Void, Void, Boolean> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final Context mContext;
        private final String mData;
        private File mTempFile;

        public LoadDataUriTask(Context context, String str) {
            this.mData = str;
            this.mContext = context;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Void... voidArr) throws Throwable {
            FileOutputStream fileOutputStream = null;
            try {
                try {
                    this.mTempFile = File.createTempFile("decoded", "mediadata");
                    FileOutputStream fileOutputStream2 = new FileOutputStream(this.mTempFile);
                    try {
                        Base64InputStream base64InputStream = new Base64InputStream(new ByteArrayInputStream(this.mData.getBytes()), 0);
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int i = base64InputStream.read(bArr);
                            if (i == -1) {
                                break;
                            }
                            fileOutputStream2.write(bArr, 0, i);
                        }
                        base64InputStream.close();
                        try {
                            fileOutputStream2.close();
                        } catch (IOException unused) {
                        }
                        return true;
                    } catch (IOException unused2) {
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException unused3) {
                            }
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException unused4) {
                            }
                        }
                        throw th;
                    }
                } catch (IOException unused5) {
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            if (isCancelled()) {
                deleteFile();
                return;
            }
            try {
                MediaPlayerBridge.this.getLocalPlayer().setDataSource(this.mContext, Uri.fromFile(this.mTempFile));
            } catch (IOException unused) {
                bool = false;
            }
            deleteFile();
            MediaPlayerBridge mediaPlayerBridge = MediaPlayerBridge.this;
            mediaPlayerBridge.nativeOnDidSetDataUriDataSource(mediaPlayerBridge.mNativeMediaPlayerBridge, bool.booleanValue());
        }

        private void deleteFile() {
            File file = this.mTempFile;
            if (file == null || file.delete()) {
                return;
            }
            Log.e(MediaPlayerBridge.TAG, "Failed to delete temporary file: " + this.mTempFile, new Object[0]);
        }
    }

    protected void setOnBufferingUpdateListener(MediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener) {
        getLocalPlayer().setOnBufferingUpdateListener(onBufferingUpdateListener);
    }

    protected void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        getLocalPlayer().setOnCompletionListener(onCompletionListener);
    }

    protected void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
        getLocalPlayer().setOnErrorListener(onErrorListener);
    }

    protected void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        getLocalPlayer().setOnPreparedListener(onPreparedListener);
    }

    protected void setOnSeekCompleteListener(MediaPlayer.OnSeekCompleteListener onSeekCompleteListener) {
        getLocalPlayer().setOnSeekCompleteListener(onSeekCompleteListener);
    }

    protected void setOnVideoSizeChangedListener(MediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        getLocalPlayer().setOnVideoSizeChangedListener(onVideoSizeChangedListener);
    }

    protected static class AllowedOperations {
        private final boolean mCanPause;
        private final boolean mCanSeekBackward;
        private final boolean mCanSeekForward;

        public AllowedOperations(boolean z, boolean z2, boolean z3) {
            this.mCanPause = z;
            this.mCanSeekForward = z2;
            this.mCanSeekBackward = z3;
        }

        private boolean canPause() {
            return this.mCanPause;
        }

        private boolean canSeekForward() {
            return this.mCanSeekForward;
        }

        private boolean canSeekBackward() {
            return this.mCanSeekBackward;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0180 A[PHI: r7 r8
  0x0180: PHI (r7v8 boolean) = (r7v4 boolean), (r7v5 boolean), (r7v6 boolean), (r7v7 boolean), (r7v19 boolean), (r7v19 boolean) binds: [B:49:0x0154, B:52:0x016c, B:43:0x0124, B:46:0x013c, B:20:0x00f1, B:22:0x0105] A[DONT_GENERATE, DONT_INLINE]
  0x0180: PHI (r8v8 boolean) = (r8v4 boolean), (r8v5 boolean), (r8v6 boolean), (r8v7 boolean), (r8v29 boolean), (r8v29 boolean) binds: [B:49:0x0154, B:52:0x016c, B:43:0x0124, B:46:0x013c, B:20:0x00f1, B:22:0x0105] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected org.chromium.media.MediaPlayerBridge.AllowedOperations getAllowedOperations() {
        /*
            Method dump skipped, instruction units count: 392
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.MediaPlayerBridge.getAllowedOperations():org.chromium.media.MediaPlayerBridge$AllowedOperations");
    }
}
