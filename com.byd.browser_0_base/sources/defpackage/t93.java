package defpackage;

import android.graphics.Bitmap;
import defpackage.ue;
import java.util.ArrayDeque;
import java.util.Locale;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.thumbnail.generator.ThumbnailGenerator;

/* JADX INFO: compiled from: ThumbnailProviderImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class t93 implements v93 {
    public static final Object h = new Object();
    public final ue a;
    public final int b;
    public final ua1<String, Object> c = new ua1<>(100);
    public final ArrayDeque d = new ArrayDeque();
    public s93 e;
    public final r93 f;
    public int g;

    public t93(na0 na0Var) {
        Object obj = ThreadUtils.a;
        this.a = new ue(na0Var, 15728640);
        this.f = new r93(this, new ThumbnailGenerator());
        this.b = 0;
    }

    public final Bitmap a(int i, String str) {
        Bitmap bitmapA = this.a.a(String.format(Locale.US, "id=%s, size=%d", str, Integer.valueOf(i)));
        int i2 = this.b;
        oj2.O("Android.ThumbnailProvider.CachedBitmap.Found.".concat(i2 != 0 ? i2 != 1 ? "Other" : "NTPSnippets" : "DownloadHome"), bitmapA != null);
        return bitmapA;
    }

    public final void b(Bitmap bitmap, String str) {
        int i;
        s93 s93Var = this.e;
        if (s93Var == null) {
            return;
        }
        if (bitmap != null) {
            this.a.c(bitmap, String.format(Locale.US, "id=%s, size=%d", str, Integer.valueOf(s93Var.b())));
            ua1<String, Object> ua1Var = this.c;
            ua1Var.getClass();
            i21.e(str, "key");
            synchronized (ua1Var.c) {
                va1<String, Object> va1Var = ua1Var.b;
                va1Var.getClass();
                Object objRemove = va1Var.a.remove(str);
                if (objRemove != null) {
                    ua1Var.d -= ua1Var.d(str, objRemove);
                }
                vi3 vi3Var = vi3.a;
            }
            this.e.a(bitmap);
            int i2 = this.g;
            ue.a aVarB = this.a.b();
            synchronized (aVarB.c) {
                i = aVarB.d;
            }
            this.g = Math.max(i2, i);
        } else {
            this.c.c(str, h);
            this.e.a(null);
        }
        this.e = null;
        PostTask.c(rh3.a, new eu1(26, this));
    }
}
