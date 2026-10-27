package defpackage;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import java.util.Iterator;

/* JADX INFO: renamed from: lH, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1099lH implements Iterable {
    public MediaCodecInfo[] s;

    public C1099lH() {
        try {
            this.s = new MediaCodecList(1).getCodecInfos();
        } catch (Throwable unused) {
        }
    }

    public static int e(C1099lH c1099lH) {
        if (c1099lH.f()) {
            return c1099lH.s.length;
        }
        try {
            return MediaCodecList.getCodecCount();
        } catch (RuntimeException unused) {
            return 0;
        }
    }

    public final boolean f() {
        return this.s != null;
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return new C1043kH(this, null);
    }
}
