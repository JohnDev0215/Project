package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.util.Log;
import defpackage.sj0;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: FaceDetectionImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class mj0 implements kj0 {
    public final int e;

    public mj0(tj0 tj0Var) {
        boolean z = tj0Var.c;
        this.e = Math.min(tj0Var.b, 32);
    }

    @Override // defpackage.kj0
    public final void D0(xe xeVar, final sj0.e eVar) {
        Bitmap bitmap;
        Bitmap bitmapA = ze.a(xeVar);
        if (bitmapA == null) {
            Log.e("cr_FaceDetectionImpl", "Error converting Mojom Bitmap to Android Bitmap");
            eVar.c(new rj0[0]);
            return;
        }
        ye yeVar = xeVar.b;
        int i = yeVar.c;
        final int i2 = (i % 2) + i;
        final int i3 = yeVar.d;
        if (i2 != i) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
            new Canvas(bitmapCreateBitmap).drawBitmap(bitmapA, 0.0f, 0.0f, (Paint) null);
            bitmap = bitmapCreateBitmap;
        } else {
            bitmap = bitmapA;
        }
        int[] iArr = new int[i2 * i3];
        bitmap.getPixels(iArr, 0, i2, 0, 0, i2, i3);
        final Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(iArr, i2, i3, Bitmap.Config.RGB_565);
        PostTask.c(d73.g, new Runnable() { // from class: lj0
            @Override // java.lang.Runnable
            public final void run() {
                mj0 mj0Var = this.e;
                mj0Var.getClass();
                int i4 = i2;
                int i5 = i3;
                int i6 = mj0Var.e;
                FaceDetector faceDetector = new FaceDetector(i4, i5, i6);
                FaceDetector.Face[] faceArr = new FaceDetector.Face[i6];
                int iFindFaces = faceDetector.findFaces(bitmapCreateBitmap2, faceArr);
                rj0[] rj0VarArr = new rj0[iFindFaces];
                for (int i7 = 0; i7 < iFindFaces; i7++) {
                    rj0VarArr[i7] = new rj0();
                    FaceDetector.Face face = faceArr[i7];
                    PointF pointF = new PointF();
                    face.getMidPoint(pointF);
                    float fEyesDistance = face.eyesDistance();
                    rj0 rj0Var = rj0VarArr[i7];
                    y92 y92Var = new y92(0);
                    rj0Var.b = y92Var;
                    y92Var.b = pointF.x - fEyesDistance;
                    y92Var.c = pointF.y - fEyesDistance;
                    float f = fEyesDistance * 2.0f;
                    y92Var.d = f;
                    y92Var.e = f;
                    rj0Var.c = new a51[0];
                }
                ((sj0.e) eVar).c(rj0VarArr);
            }
        });
    }

    @Override // defpackage.y11, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // defpackage.fx
    public final void q(ki1 ki1Var) {
    }
}
