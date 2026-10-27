package defpackage;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import defpackage.z73;
import org.chromium.gms.ChromiumPlayServicesAvailability;

/* JADX INFO: compiled from: TextDetectionImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class x73 implements w73 {
    public final TextRecognizer e = new TextRecognizer.Builder(xz.c()).build();

    public static x73 t() {
        if (ChromiumPlayServicesAvailability.isGooglePlayServicesAvailable(xz.c())) {
            return new x73();
        }
        fa1.a("TextDetectionImpl", "Google Play Services not available");
        return null;
    }

    @Override // defpackage.w73
    public final void X0(xe xeVar, z73.g gVar) {
        TextRecognizer textRecognizer = this.e;
        if (!textRecognizer.isOperational()) {
            fa1.a("TextDetectionImpl", "TextDetector is not operational");
            gVar.c(new y73[0]);
            return;
        }
        Frame frameB = ze.b(xeVar);
        if (frameB == null) {
            fa1.a("TextDetectionImpl", "Error converting Mojom Bitmap to Frame");
            gVar.c(new y73[0]);
            return;
        }
        SparseArray sparseArrayDetect = textRecognizer.detect(frameB);
        y73[] y73VarArr = new y73[sparseArrayDetect.size()];
        for (int i = 0; i < sparseArrayDetect.size(); i++) {
            y73VarArr[i] = new y73();
            TextBlock textBlock = (TextBlock) sparseArrayDetect.valueAt(i);
            y73VarArr[i].b = textBlock.getValue();
            Rect boundingBox = textBlock.getBoundingBox();
            y73 y73Var = y73VarArr[i];
            y92 y92Var = new y92();
            y73Var.c = y92Var;
            y92Var.b = boundingBox.left;
            y92Var.c = boundingBox.top;
            y92Var.d = boundingBox.width();
            y73VarArr[i].c.e = boundingBox.height();
            Point[] cornerPoints = textBlock.getCornerPoints();
            y73VarArr[i].d = new l22[cornerPoints.length];
            for (int i2 = 0; i2 < cornerPoints.length; i2++) {
                y73VarArr[i].d[i2] = new l22();
                l22 l22Var = y73VarArr[i].d[i2];
                Point point = cornerPoints[i2];
                l22Var.b = point.x;
                l22Var.c = point.y;
            }
        }
        gVar.c(y73VarArr);
    }

    @Override // defpackage.y11, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.e.release();
    }

    @Override // defpackage.fx
    public final void q(ki1 ki1Var) {
        close();
    }
}
