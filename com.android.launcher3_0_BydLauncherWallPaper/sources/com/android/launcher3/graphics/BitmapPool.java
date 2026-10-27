package com.android.launcher3.graphics;

import android.graphics.Bitmap;
import java.lang.ref.WeakReference;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes4.dex */
public class BitmapPool {
    private static BitmapPool INSTANCE = new BitmapPool();
    private final LinkedList<WeakReference<Bitmap>> mPool = new LinkedList<>();

    private BitmapPool() {
    }

    public static BitmapPool getInstance() {
        return INSTANCE;
    }

    public void put(Bitmap bitmap) {
        this.mPool.add(new WeakReference<>(bitmap));
    }

    public Bitmap get() {
        Bitmap bitmap = null;
        while (!this.mPool.isEmpty() && (bitmap = this.mPool.removeFirst().get()) == null) {
        }
        return bitmap;
    }
}
