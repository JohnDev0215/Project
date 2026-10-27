package com.king.zxing.camera;

import android.graphics.Point;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;

/* JADX INFO: compiled from: PreviewCallback.java */
/* JADX INFO: loaded from: classes.dex */
final class e implements Camera.PreviewCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4580a = e.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b f4581b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Handler f4582c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f4583d;

    e(b bVar) {
        this.f4581b = bVar;
    }

    void a(Handler handler, int i) {
        this.f4582c = handler;
        this.f4583d = i;
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        Point pointB = this.f4581b.b();
        Handler handler = this.f4582c;
        if (pointB == null || handler == null) {
            Log.d(f4580a, "Got preview callback, but no handler or resolution available");
        } else {
            handler.obtainMessage(this.f4583d, pointB.x, pointB.y, bArr).sendToTarget();
            this.f4582c = null;
        }
    }
}
