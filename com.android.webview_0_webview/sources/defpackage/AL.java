package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.media.FaceDetector;
import java.util.Objects;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AL implements InterfaceC0658dM {
    public final int s;

    public AL(C1544tM c1544tM) {
        boolean z = c1544tM.c;
        this.s = Math.min(c1544tM.b, 32);
    }

    @Override // defpackage.LI
    public void I(WJ wj) {
    }

    @Override // defpackage.InterfaceC0822gJ, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // defpackage.InterfaceC0658dM
    public void g(GM gm, final InterfaceC0602cM interfaceC0602cM) {
        Bitmap bitmap;
        Bitmap bitmapA = AbstractC1818yL.a(gm);
        if (bitmapA == null) {
            Ts.a("FaceDetectionImpl", "Error converting Mojom Bitmap to Android Bitmap", new Object[0]);
            ((C1325pM) interfaceC0602cM).a(new C1048kM[0]);
            return;
        }
        HM hm = gm.b;
        int i = hm.e;
        final int i2 = (i % 2) + i;
        final int i3 = hm.f;
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
        PostTask.a(C1242nv.h, new Runnable(this, i2, i3, bitmapCreateBitmap2, interfaceC0602cM) { // from class: zL
            public final AL s;
            public final int t;
            public final int u;
            public final Bitmap v;
            public final InterfaceC0602cM w;

            {
                this.s = this;
                this.t = i2;
                this.u = i3;
                this.v = bitmapCreateBitmap2;
                this.w = interfaceC0602cM;
            }

            @Override // java.lang.Runnable
            public void run() {
                AL al = this.s;
                int i4 = this.t;
                int i5 = this.u;
                Bitmap bitmap2 = this.v;
                InterfaceC0602cM interfaceC0602cM2 = this.w;
                Objects.requireNonNull(al);
                FaceDetector faceDetector = new FaceDetector(i4, i5, al.s);
                FaceDetector.Face[] faceArr = new FaceDetector.Face[al.s];
                int iFindFaces = faceDetector.findFaces(bitmap2, faceArr);
                C1048kM[] c1048kMArr = new C1048kM[iFindFaces];
                for (int i6 = 0; i6 < iFindFaces; i6++) {
                    c1048kMArr[i6] = new C1048kM();
                    FaceDetector.Face face = faceArr[i6];
                    PointF pointF = new PointF();
                    face.getMidPoint(pointF);
                    float fEyesDistance = face.eyesDistance();
                    c1048kMArr[i6].b = new SG();
                    c1048kMArr[i6].b.b = pointF.x - fEyesDistance;
                    c1048kMArr[i6].b.c = pointF.y - fEyesDistance;
                    float f = fEyesDistance * 2.0f;
                    c1048kMArr[i6].b.d = f;
                    c1048kMArr[i6].b.e = f;
                    c1048kMArr[i6].c = new C1599uM[0];
                }
                ((C1325pM) interfaceC0602cM2).a(c1048kMArr);
            }
        }, 0L);
    }
}
