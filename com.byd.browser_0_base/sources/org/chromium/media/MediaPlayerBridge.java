package org.chromium.media;

import J.N;
import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Base64InputStream;
import android.util.Log;
import android.view.Surface;
import defpackage.a8;
import defpackage.c6;
import defpackage.i9;
import defpackage.xz;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class MediaPlayerBridge {
    public a a;
    public MediaPlayer b;
    public long c;

    public static class AllowedOperations {
        public final boolean a;
        public final boolean b;

        public AllowedOperations(boolean z, boolean z2) {
            this.a = z;
            this.b = z2;
        }

        @CalledByNative
        public final boolean canSeekBackward() {
            return this.b;
        }

        @CalledByNative
        public final boolean canSeekForward() {
            return this.a;
        }
    }

    public class a extends i9<Boolean> {
        public final String h;
        public File i;

        public a(String str) {
            this.h = str;
        }

        @Override // defpackage.i9
        public final Boolean b() throws Throwable {
            FileOutputStream fileOutputStream;
            Boolean bool;
            Base64InputStream base64InputStream;
            byte[] bArr;
            FileOutputStream fileOutputStream2 = null;
            try {
                try {
                    this.i = File.createTempFile("decoded", "mediadata");
                    fileOutputStream = new FileOutputStream(this.i);
                } catch (IOException unused) {
                }
            } catch (Throwable th) {
                th = th;
                fileOutputStream = fileOutputStream2;
            }
            try {
                base64InputStream = new Base64InputStream(new ByteArrayInputStream(c6.b(this.h)), 0);
                bArr = new byte[1024];
            } catch (IOException unused2) {
                fileOutputStream2 = fileOutputStream;
                bool = Boolean.FALSE;
                a8.s(fileOutputStream2);
            } catch (Throwable th2) {
                th = th2;
                a8.s(fileOutputStream);
                throw th;
            }
            while (true) {
                int i = base64InputStream.read(bArr);
                if (i == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, i);
                return bool;
            }
            base64InputStream.close();
            bool = Boolean.TRUE;
            a8.s(fileOutputStream);
            return bool;
        }

        @Override // defpackage.i9
        public final void l(Boolean bool) {
            Boolean bool2 = bool;
            if (i()) {
                o();
                return;
            }
            boolean zBooleanValue = bool2.booleanValue();
            MediaPlayerBridge mediaPlayerBridge = MediaPlayerBridge.this;
            if (zBooleanValue) {
                try {
                    mediaPlayerBridge.a().setDataSource(xz.a, Uri.fromFile(this.i));
                } catch (IOException unused) {
                    bool2 = Boolean.FALSE;
                }
            }
            o();
            N.Mo4Rd8TE(mediaPlayerBridge.c, mediaPlayerBridge, bool2.booleanValue());
        }

        public final void o() {
            File file = this.i;
            if (file == null || file.delete()) {
                return;
            }
            Log.e("cr_media", "Failed to delete temporary file: " + this.i);
        }
    }

    public MediaPlayerBridge(long j) {
        this.c = j;
    }

    @CalledByNative
    public static MediaPlayerBridge create(long j) {
        return new MediaPlayerBridge(j);
    }

    public final MediaPlayer a() {
        if (this.b == null) {
            this.b = new MediaPlayer();
        }
        return this.b;
    }

    @CalledByNative
    public void destroy() {
        a aVar = this.a;
        if (aVar != null) {
            aVar.a(true);
            this.a = null;
        }
        this.c = 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0098  */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.chromium.media.MediaPlayerBridge.AllowedOperations getAllowedOperations() {
        /*
            Method dump skipped, instruction units count: 293
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.MediaPlayerBridge.getAllowedOperations():org.chromium.media.MediaPlayerBridge$AllowedOperations");
    }

    @CalledByNative
    public int getCurrentPosition() {
        return a().getCurrentPosition();
    }

    @CalledByNative
    public int getDuration() {
        return a().getDuration();
    }

    @CalledByNative
    public boolean isPlaying() {
        return a().isPlaying();
    }

    @CalledByNative
    public void pause() {
        a().pause();
    }

    @CalledByNative
    public boolean prepareAsync() {
        try {
            a().prepareAsync();
            return true;
        } catch (IllegalStateException e) {
            Log.e("cr_media", "Unable to prepare MediaPlayer.", e);
            return false;
        } catch (Exception e2) {
            Log.e("cr_media", "Unable to prepare MediaPlayer.", e2);
            return false;
        }
    }

    @CalledByNative
    public void release() {
        a aVar = this.a;
        if (aVar != null) {
            aVar.a(true);
            this.a = null;
        }
        a().release();
    }

    @CalledByNative
    public void seekTo(int i) {
        a().seekTo(i);
    }

    @CalledByNative
    public boolean setDataSource(String str, String str2, String str3, boolean z) {
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
        map.put("android-allow-cross-domain-redirect", "0");
        try {
            a().setDataSource(xz.a, uri, map);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    @CalledByNative
    public boolean setDataSourceFromFd(int i, long j, long j2) {
        try {
            ParcelFileDescriptor parcelFileDescriptorAdoptFd = ParcelFileDescriptor.adoptFd(i);
            a().setDataSource(parcelFileDescriptorAdoptFd.getFileDescriptor(), j, j2);
            parcelFileDescriptorAdoptFd.close();
            return true;
        } catch (IOException e) {
            Log.e("cr_media", "Failed to set data source from file descriptor: " + e);
            return false;
        }
    }

    @CalledByNative
    public boolean setDataUriDataSource(String str) {
        int iIndexOf;
        a aVar = this.a;
        if (aVar != null) {
            aVar.a(true);
            this.a = null;
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
        a aVar2 = new a(strSubstring2);
        this.a = aVar2;
        aVar2.c(i9.e);
        return true;
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        a().setOnCompletionListener(onCompletionListener);
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
        a().setOnErrorListener(onErrorListener);
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        a().setOnPreparedListener(onPreparedListener);
    }

    public void setOnVideoSizeChangedListener(MediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        a().setOnVideoSizeChangedListener(onVideoSizeChangedListener);
    }

    @SuppressLint({"NewApi"})
    @CalledByNative
    public void setPlaybackRate(double d) {
        try {
            MediaPlayer mediaPlayerA = a();
            mediaPlayerA.setPlaybackParams(mediaPlayerA.getPlaybackParams().setSpeed((float) d));
        } catch (IllegalArgumentException e) {
            Log.e("cr_media", "Unable to set playback rate", e);
        } catch (IllegalStateException e2) {
            Log.e("cr_media", "Unable to set playback rate", e2);
        }
    }

    @CalledByNative
    public void setSurface(Surface surface) {
        a().setSurface(surface);
    }

    @CalledByNative
    public void setVolume(double d) {
        float f = (float) d;
        a().setVolume(f, f);
    }

    @CalledByNative
    public void start() {
        a().start();
    }

    public MediaPlayerBridge() {
    }
}
