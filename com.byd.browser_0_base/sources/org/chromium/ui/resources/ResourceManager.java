package org.chromium.ui.resources;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.SparseArray;
import defpackage.df0;
import defpackage.iy2;
import defpackage.k61;
import defpackage.xs2;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.resources.a;

/* JADX INFO: loaded from: classes3.dex */
public class ResourceManager implements a.InterfaceC0158a {
    public final SparseArray<a> a;
    public final SparseArray<SparseArray<k61>> b;
    public final float c;
    public long d;

    public ResourceManager(Resources resources, int i, long j) {
        SparseArray<a> sparseArray = new SparseArray<>();
        this.a = sparseArray;
        this.b = new SparseArray<>();
        this.c = 1.0f / resources.getDisplayMetrics().density;
        sparseArray.put(0, new xs2(this, resources));
        sparseArray.put(1, new df0(1, this));
        sparseArray.put(2, new df0(2, this));
        sparseArray.put(3, new iy2(this, i));
        this.d = j;
    }

    @CalledByNative
    public static ResourceManager create(WindowAndroid windowAndroid, long j) {
        Context context = windowAndroid.i.get();
        if (context == null) {
            throw new IllegalStateException("Context should not be null during initialization.");
        }
        Point point = windowAndroid.h.c;
        return new ResourceManager(context.getResources(), Math.min(point.x, point.y), j);
    }

    public final df0 a() {
        return (df0) this.a.get(1);
    }

    public final void b(int i, int[] iArr, int[] iArr2) {
        a aVar = this.a.get(i);
        for (int i2 : iArr2) {
            aVar.c(Integer.valueOf(i2).intValue());
        }
        for (int i3 : iArr) {
            aVar.a(Integer.valueOf(i3).intValue());
        }
    }

    @CalledByNative
    public final void destroy() {
        this.d = 0L;
    }

    @CalledByNative
    public final long getNativePtr() {
        return this.d;
    }

    @CalledByNative
    public final void preloadResource(int i, int i2) {
        a aVar = this.a.get(i);
        if (aVar != null) {
            aVar.c(i2);
        }
    }

    @CalledByNative
    public final void resourceRequested(int i, int i2) {
        a aVar = this.a.get(i);
        if (aVar != null) {
            aVar.a(i2);
        }
    }
}
