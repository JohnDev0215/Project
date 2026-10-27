package org.chromium.ui.resources;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.SparseArray;
import defpackage.C0716eP;
import defpackage.C0828gP;
import defpackage.TO;
import defpackage.UO;
import defpackage.ZO;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ResourceManager implements TO {
    public final SparseArray a = new SparseArray();
    public final SparseArray b = new SparseArray();
    public final float c;
    public long d;

    public ResourceManager(Resources resources, int i, long j) {
        this.c = 1.0f / resources.getDisplayMetrics().density;
        C0716eP c0716eP = new C0716eP(0, this, resources);
        this.a.put(c0716eP.a, c0716eP);
        ZO zo = new ZO(1, this);
        this.a.put(zo.a, zo);
        ZO zo2 = new ZO(2, this);
        this.a.put(zo2.a, zo2);
        C0828gP c0828gP = new C0828gP(3, this, i);
        this.a.put(c0828gP.a, c0828gP);
        this.d = j;
    }

    public static ResourceManager create(WindowAndroid windowAndroid, long j) {
        Context context = (Context) windowAndroid.S().get();
        if (context == null) {
            throw new IllegalStateException("Context should not be null during initialization.");
        }
        Point point = windowAndroid.t.c;
        return new ResourceManager(context.getResources(), Math.min(point.x, point.y), j);
    }

    private void destroy() {
        this.d = 0L;
    }

    private long getNativePtr() {
        return this.d;
    }

    private void preloadResource(int i, int i2) {
        UO uo = (UO) this.a.get(i);
        if (uo != null) {
            uo.c(i2);
        }
    }

    private void resourceRequested(int i, int i2) {
        UO uo = (UO) this.a.get(i);
        if (uo != null) {
            uo.a(i2);
        }
    }
}
