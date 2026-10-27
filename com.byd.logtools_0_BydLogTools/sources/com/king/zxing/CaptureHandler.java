package com.king.zxing;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class CaptureHandler extends Handler implements com.google.zxing.j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f4534b = CaptureHandler.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final n f4535c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final k f4536d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private State f4537e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final com.king.zxing.camera.d f4538f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Activity f4539g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final ViewfinderView f4540h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;

    private enum State {
        PREVIEW,
        SUCCESS,
        DONE
    }

    CaptureHandler(Activity activity, ViewfinderView viewfinderView, n nVar, Collection<BarcodeFormat> collection, Map<DecodeHintType, Object> map, String str, com.king.zxing.camera.d dVar) {
        this.f4539g = activity;
        this.f4540h = viewfinderView;
        this.f4535c = nVar;
        k kVar = new k(activity, dVar, this, collection, map, str, this);
        this.f4536d = kVar;
        kVar.start();
        this.f4537e = State.SUCCESS;
        this.f4538f = dVar;
        dVar.t();
        g();
    }

    private com.google.zxing.i l(com.google.zxing.i iVar) {
        float fC;
        float fD;
        int iMax;
        Point pointG = this.f4538f.g();
        Point pointC = this.f4538f.c();
        int i = pointG.x;
        int i2 = pointG.y;
        if (i < i2) {
            fC = (iVar.c() * ((i * 1.0f) / pointC.y)) - (Math.max(pointG.x, pointC.y) / 2);
            fD = iVar.d() * ((i2 * 1.0f) / pointC.x);
            iMax = Math.min(pointG.y, pointC.x) / 2;
        } else {
            fC = (iVar.c() * ((i * 1.0f) / pointC.x)) - (Math.min(pointG.y, pointC.y) / 2);
            fD = iVar.d() * ((i2 * 1.0f) / pointC.y);
            iMax = Math.max(pointG.x, pointC.x) / 2;
        }
        return new com.google.zxing.i(fC, fD - iMax);
    }

    @Override // com.google.zxing.j
    public void a(com.google.zxing.i iVar) {
        if (this.f4540h != null) {
            this.f4540h.a(l(iVar));
        }
    }

    public boolean b() {
        return this.j;
    }

    public boolean c() {
        return this.k;
    }

    public boolean d() {
        return this.l;
    }

    public boolean e() {
        return this.i;
    }

    public void f() {
        this.f4537e = State.DONE;
        this.f4538f.u();
        Message.obtain(this.f4536d.a(), p.f4649e).sendToTarget();
        try {
            this.f4536d.join(100L);
        } catch (InterruptedException unused) {
        }
        removeMessages(p.f4647c);
        removeMessages(p.f4646b);
    }

    public void g() {
        if (this.f4537e == State.SUCCESS) {
            this.f4537e = State.PREVIEW;
            this.f4538f.j(this.f4536d.a(), p.f4645a);
            this.f4540h.j();
        }
    }

    public void h(boolean z) {
        this.j = z;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        int i = message.what;
        if (i == p.f4650f) {
            g();
            return;
        }
        if (i != p.f4647c) {
            if (i == p.f4646b) {
                this.f4537e = State.PREVIEW;
                this.f4538f.j(this.f4536d.a(), p.f4645a);
                return;
            }
            return;
        }
        this.f4537e = State.SUCCESS;
        Bundle data = message.getData();
        float f2 = 1.0f;
        if (data != null) {
            byte[] byteArray = data.getByteArray("barcode_bitmap");
            bitmapCopy = byteArray != null ? BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, null).copy(Bitmap.Config.ARGB_8888, true) : null;
            f2 = data.getFloat("barcode_scaled_factor");
        }
        this.f4535c.a((com.google.zxing.h) message.obj, bitmapCopy, f2);
    }

    public void i(boolean z) {
        this.k = z;
    }

    public void j(boolean z) {
        this.l = z;
    }

    public void k(boolean z) {
        this.i = z;
    }
}
