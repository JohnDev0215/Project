package defpackage;

import android.graphics.PointF;
import android.util.SparseArray;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.Landmark;
import defpackage.sj0;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: FaceDetectionImplGmsCore.java */
/* JADX INFO: loaded from: classes3.dex */
public final class nj0 implements kj0 {
    public final int e;
    public final boolean f;
    public final FaceDetector g;

    public nj0(tj0 tj0Var) {
        FaceDetector.Builder builder = new FaceDetector.Builder(xz.c());
        int iMin = Math.min(tj0Var.b, 32);
        this.e = iMin;
        boolean z = tj0Var.c;
        this.f = z;
        try {
            builder.setMode(z ? 0 : 1);
            builder.setLandmarkType(1);
            if (iMin == 1) {
                builder.setProminentFaceOnly(true);
            }
        } catch (IllegalArgumentException e) {
            fa1.a("FaceDetectionImpl", "Unexpected exception " + e);
        }
        this.g = builder.build();
    }

    @Override // defpackage.kj0
    public final void D0(xe xeVar, sj0.e eVar) {
        FaceDetector faceDetector = this.g;
        if (!faceDetector.isOperational()) {
            fa1.a("FaceDetectionImpl", "FaceDetector is not operational");
            tj0 tj0Var = new tj0();
            tj0Var.c = this.f;
            tj0Var.b = this.e;
            new mj0(tj0Var).D0(xeVar, eVar);
            return;
        }
        Frame frameB = ze.b(xeVar);
        if (frameB == null) {
            fa1.a("FaceDetectionImpl", "Error converting Mojom Bitmap to Frame");
            eVar.c(new rj0[0]);
            return;
        }
        SparseArray sparseArrayDetect = faceDetector.detect(frameB);
        rj0[] rj0VarArr = new rj0[sparseArrayDetect.size()];
        for (int i = 0; i < sparseArrayDetect.size(); i++) {
            rj0VarArr[i] = new rj0();
            Face face = (Face) sparseArrayDetect.valueAt(i);
            PointF position = face.getPosition();
            rj0 rj0Var = rj0VarArr[i];
            y92 y92Var = new y92();
            rj0Var.b = y92Var;
            y92Var.b = position.x;
            y92Var.c = position.y;
            y92Var.d = face.getWidth();
            rj0VarArr[i].b.e = face.getHeight();
            List landmarks = face.getLandmarks();
            ArrayList arrayList = new ArrayList(landmarks.size());
            for (int i2 = 0; i2 < landmarks.size(); i2++) {
                Landmark landmark = (Landmark) landmarks.get(i2);
                int type = landmark.getType();
                if (type == 4 || type == 10 || type == 0 || type == 6) {
                    a51 a51Var = new a51();
                    a51Var.b = new l22[]{l22Var};
                    l22 l22Var = new l22();
                    l22Var.b = landmark.getPosition().x;
                    a51Var.b[0].c = landmark.getPosition().y;
                    if (type == 4 || type == 10) {
                        a51Var.c = 1;
                    } else if (type == 0) {
                        a51Var.c = 0;
                    } else {
                        a51Var.c = 2;
                    }
                    arrayList.add(a51Var);
                }
            }
            rj0VarArr[i].c = (a51[]) arrayList.toArray(new a51[arrayList.size()]);
        }
        eVar.c(rj0VarArr);
    }

    @Override // defpackage.y11, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.g.release();
    }

    @Override // defpackage.fx
    public final void q(ki1 ki1Var) {
        close();
    }
}
