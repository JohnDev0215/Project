package defpackage;

import android.graphics.Bitmap;
import android.os.Looper;
import defpackage.na0;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import org.chromium.base.SysUtils;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: BitmapCache.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ue {
    public static final HashMap d = new HashMap();
    public static int e;
    public final int a;
    public na0.a<a> b;
    public final na0 c;

    /* JADX INFO: compiled from: BitmapCache.java */
    public static class a extends ua1<String, Bitmap> {
        public a(int i) {
            super(i);
        }

        @Override // defpackage.ua1
        public final Bitmap a(String str) {
            WeakReference weakReference = (WeakReference) ue.d.get(str);
            if (weakReference == null) {
                return null;
            }
            return (Bitmap) weakReference.get();
        }

        @Override // defpackage.ua1
        public final int e(String str, Bitmap bitmap) {
            return bitmap.getByteCount();
        }
    }

    public ue(na0 na0Var, int i) {
        Object obj = ThreadUtils.a;
        this.c = na0Var;
        this.a = i;
        this.b = na0Var.a(new a(i));
    }

    public final Bitmap a(String str) {
        Object obj = ThreadUtils.a;
        if (this.b == null) {
            return null;
        }
        Bitmap bitmapB = b().b(str);
        int i = e + 1;
        e = i;
        if (i >= d.size()) {
            e = 0;
            Looper.myQueue().addIdleHandler(new te());
        }
        return bitmapB;
    }

    public final a b() {
        a aVar = this.b.a;
        if (aVar != null) {
            return aVar;
        }
        a aVar2 = new a(this.a);
        this.b = this.c.a(aVar2);
        return aVar2;
    }

    public final void c(Bitmap bitmap, String str) {
        Object obj = ThreadUtils.a;
        if (bitmap == null || this.b == null) {
            return;
        }
        if (!SysUtils.isLowEndDevice()) {
            b().c(str, bitmap);
        }
        int i = e + 1;
        e = i;
        HashMap map = d;
        if (i >= map.size()) {
            e = 0;
            Looper.myQueue().addIdleHandler(new te());
        }
        map.put(str, new WeakReference(bitmap));
    }
}
