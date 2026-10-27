package com.king.zxing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import java.io.ByteArrayOutputStream;
import java.util.Map;

/* JADX INFO: compiled from: DecodeHandler.java */
/* JADX INFO: loaded from: classes.dex */
final class j extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4619a = j.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f4620b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.king.zxing.camera.d f4621c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final CaptureHandler f4622d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.google.zxing.e f4623e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f4624f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private long f4625g;

    j(Context context, com.king.zxing.camera.d dVar, CaptureHandler captureHandler, Map<DecodeHintType, Object> map) {
        com.google.zxing.e eVar = new com.google.zxing.e();
        this.f4623e = eVar;
        eVar.d(map);
        this.f4620b = context;
        this.f4621c = dVar;
        this.f4622d = captureHandler;
    }

    private com.google.zxing.f a(byte[] bArr, int i, int i2, boolean z) {
        if (!z) {
            return this.f4621c.a(bArr, i, i2);
        }
        byte[] bArr2 = new byte[bArr.length];
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i; i4++) {
                bArr2[(((i4 * i2) + i2) - i3) - 1] = bArr[(i3 * i) + i4];
            }
        }
        return this.f4621c.a(bArr2, i2, i);
    }

    private static void b(com.google.zxing.f fVar, Bundle bundle) {
        int[] iArrJ = fVar.j();
        int i = fVar.i();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iArrJ, 0, i, i, fVar.h(), Bitmap.Config.ARGB_8888);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapCreateBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        bundle.putByteArray("barcode_bitmap", byteArrayOutputStream.toByteArray());
        bundle.putFloat("barcode_scaled_factor", i / fVar.d());
    }

    private void c(byte[] bArr, int i, int i2, boolean z, boolean z2) throws NotFoundException {
        boolean z3;
        long jCurrentTimeMillis = System.currentTimeMillis();
        com.google.zxing.f fVarA = a(bArr, i, i2, z);
        com.google.zxing.h hVarC = null;
        if (fVarA != null) {
            try {
                hVarC = this.f4623e.c(new com.google.zxing.b(new com.google.zxing.common.i(fVarA)));
                z3 = false;
            } catch (Exception unused) {
                z3 = true;
            }
            if (z3 && this.f4622d.d()) {
                try {
                    hVarC = this.f4623e.c(new com.google.zxing.b(new com.google.zxing.common.i(fVarA.e())));
                    z3 = false;
                } catch (Exception unused2) {
                    z3 = true;
                }
            }
            if (z3) {
                try {
                    hVarC = this.f4623e.c(new com.google.zxing.b(new com.google.zxing.common.g(fVarA)));
                    z3 = false;
                } catch (Exception unused3) {
                    z3 = true;
                }
            }
            if (z3 && z2) {
                com.google.zxing.f fVarA2 = a(bArr, i, i2, !z);
                if (fVarA2 != null) {
                    try {
                        fVarA = fVarA2;
                        hVarC = this.f4623e.c(new com.google.zxing.b(new com.google.zxing.common.i(fVarA2)));
                    } catch (Exception unused4) {
                        fVarA = fVarA2;
                    }
                } else {
                    fVarA = fVarA2;
                }
            }
            this.f4623e.reset();
        }
        if (hVarC == null) {
            CaptureHandler captureHandler = this.f4622d;
            if (captureHandler != null) {
                Message.obtain(captureHandler, p.f4646b).sendToTarget();
                return;
            }
            return;
        }
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        Log.d(f4619a, "Found barcode in " + (jCurrentTimeMillis2 - jCurrentTimeMillis) + " ms");
        BarcodeFormat barcodeFormatB = hVarC.b();
        CaptureHandler captureHandler2 = this.f4622d;
        if (captureHandler2 != null && captureHandler2.c() && barcodeFormatB == BarcodeFormat.QR_CODE) {
            com.google.zxing.i[] iVarArrE = hVarC.e();
            if (iVarArrE.length >= 3) {
                if (d((int) Math.max(Math.max(com.google.zxing.i.b(iVarArrE[0], iVarArrE[1]), com.google.zxing.i.b(iVarArrE[1], iVarArrE[2])), com.google.zxing.i.b(iVarArrE[0], iVarArrE[2])), i)) {
                    Message messageObtain = Message.obtain();
                    messageObtain.what = p.f4647c;
                    messageObtain.obj = hVarC;
                    if (this.f4622d.b()) {
                        Bundle bundle = new Bundle();
                        b(fVarA, bundle);
                        messageObtain.setData(bundle);
                    }
                    this.f4622d.sendMessageDelayed(messageObtain, 300L);
                    return;
                }
            }
        }
        CaptureHandler captureHandler3 = this.f4622d;
        if (captureHandler3 != null) {
            Message messageObtain2 = Message.obtain(captureHandler3, p.f4647c, hVarC);
            if (this.f4622d.b()) {
                Bundle bundle2 = new Bundle();
                b(fVarA, bundle2);
                messageObtain2.setData(bundle2);
            }
            messageObtain2.sendToTarget();
        }
    }

    private boolean d(int i, int i2) {
        Camera cameraA;
        if (this.f4625g > System.currentTimeMillis() - 1000) {
            return true;
        }
        if (i >= i2 / 5 || (cameraA = this.f4621c.f().a()) == null) {
            return false;
        }
        Camera.Parameters parameters = cameraA.getParameters();
        if (!parameters.isZoomSupported()) {
            Log.i(f4619a, "Zoom not supported");
            return false;
        }
        int maxZoom = parameters.getMaxZoom();
        parameters.setZoom(Math.min(parameters.getZoom() + (maxZoom / 5), maxZoom));
        cameraA.setParameters(parameters);
        this.f4625g = System.currentTimeMillis();
        return true;
    }

    private boolean e() {
        Display defaultDisplay = ((WindowManager) this.f4620b.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point.x < point.y;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) throws NotFoundException {
        if (message == null || !this.f4624f) {
            return;
        }
        int i = message.what;
        if (i == p.f4645a) {
            c((byte[]) message.obj, message.arg1, message.arg2, e(), this.f4622d.e());
        } else if (i == p.f4649e) {
            this.f4624f = false;
            Looper.myLooper().quit();
        }
    }
}
