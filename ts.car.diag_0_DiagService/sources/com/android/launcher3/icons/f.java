package com.android.launcher3.icons;

import android.content.Context;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class f extends b {
    private static final Object s = new Object();
    private static f t;
    private static int u;
    private final int q;
    private f r;

    private f(Context context, int i, int i2, int i3) {
        super(context, i, i2);
        this.q = i3;
    }

    public static f m(Context context) {
        synchronized (s) {
            if (t == null) {
                return new f(context, context.getResources().getConfiguration().densityDpi, context.getResources().getDimensionPixelSize(R$dimen.default_icon_bitmap_size), u);
            }
            f fVar = t;
            t = fVar.r;
            fVar.r = null;
            return fVar;
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        n();
    }

    public void n() {
        synchronized (s) {
            if (u != this.q) {
                return;
            }
            c();
            this.r = t;
            t = this;
        }
    }
}
