package com.king.zxing.camera.open;

import android.hardware.Camera;

/* JADX INFO: compiled from: OpenCamera.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f4585a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Camera f4586b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final CameraFacing f4587c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f4588d;

    public a(int i, Camera camera, CameraFacing cameraFacing, int i2) {
        this.f4585a = i;
        this.f4586b = camera;
        this.f4587c = cameraFacing;
        this.f4588d = i2;
    }

    public Camera a() {
        return this.f4586b;
    }

    public CameraFacing b() {
        return this.f4587c;
    }

    public int c() {
        return this.f4588d;
    }

    public String toString() {
        return "Camera #" + this.f4585a + " : " + this.f4587c + ',' + this.f4588d;
    }
}
