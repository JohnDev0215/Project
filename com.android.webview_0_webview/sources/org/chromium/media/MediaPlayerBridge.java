package org.chromium.media;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.view.Surface;
import defpackage.FH;
import defpackage.Is;
import defpackage.Su;
import defpackage.Ts;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class MediaPlayerBridge {
    public FH a;
    public MediaPlayer b;
    public long c;

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class AllowedOperations {
        public final boolean a;
        public final boolean b;

        public AllowedOperations(boolean z, boolean z2) {
            this.a = z;
            this.b = z2;
        }

        private boolean canSeekBackward() {
            return this.b;
        }

        private boolean canSeekForward() {
            return this.a;
        }
    }

    public MediaPlayerBridge(long j) {
        this.c = j;
    }

    public static MediaPlayerBridge create(long j) {
        return new MediaPlayerBridge(j);
    }

    public final void a() {
        FH fh = this.a;
        if (fh != null) {
            fh.b(true);
            this.a = null;
        }
    }

    public MediaPlayer b() {
        if (this.b == null) {
            this.b = new MediaPlayer();
        }
        return this.b;
    }

    public void destroy() {
        a();
        this.c = 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.chromium.media.MediaPlayerBridge.AllowedOperations getAllowedOperations() {
        /*
            Method dump skipped, instruction units count: 314
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.MediaPlayerBridge.getAllowedOperations():org.chromium.media.MediaPlayerBridge$AllowedOperations");
    }

    public int getCurrentPosition() {
        return b().getCurrentPosition();
    }

    public int getDuration() {
        return b().getDuration();
    }

    public boolean isPlaying() {
        return b().isPlaying();
    }

    public void pause() {
        b().pause();
    }

    public boolean prepareAsync() {
        try {
            b().prepareAsync();
            return true;
        } catch (IllegalStateException e) {
            Ts.a("media", "Unable to prepare MediaPlayer.", e);
            return false;
        } catch (Exception e2) {
            Ts.a("media", "Unable to prepare MediaPlayer.", e2);
            return false;
        }
    }

    public void release() {
        a();
        b().release();
    }

    public void seekTo(int i) {
        b().seekTo(i);
    }

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
        map.put("allow-cross-domain-redirect", "false");
        try {
            b().setDataSource(Is.a, uri, map);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean setDataSourceFromFd(int i, long j, long j2) {
        try {
            ParcelFileDescriptor parcelFileDescriptorAdoptFd = ParcelFileDescriptor.adoptFd(i);
            b().setDataSource(parcelFileDescriptorAdoptFd.getFileDescriptor(), j, j2);
            parcelFileDescriptorAdoptFd.close();
            return true;
        } catch (IOException e) {
            Ts.a("media", "Failed to set data source from file descriptor: " + e, new Object[0]);
            return false;
        }
    }

    public boolean setDataUriDataSource(String str) {
        int iIndexOf;
        a();
        if (!str.startsWith("data:") || (iIndexOf = str.indexOf(44)) == -1) {
            return false;
        }
        String strSubstring = str.substring(0, iIndexOf);
        String strSubstring2 = str.substring(iIndexOf + 1);
        String[] strArrSplit = strSubstring.substring(5).split(";");
        if (strArrSplit.length != 2 || !"base64".equals(strArrSplit[1])) {
            return false;
        }
        FH fh = new FH(this, strSubstring2);
        this.a = fh;
        fh.d(Su.f);
        return true;
    }

    public void setSurface(Surface surface) {
        b().setSurface(surface);
    }

    public void setVolume(double d) {
        float f = (float) d;
        b().setVolume(f, f);
    }

    public void start() {
        b().start();
    }
}
