package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.internal.zziqe;
import com.google.android.gms.vision.text.internal.client.RecognitionOptions;
import com.google.android.gms.vision.text.internal.client.zzf;
import com.google.android.gms.vision.text.internal.client.zzn;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class DL implements InterfaceC1709wM {
    public C1286ok s = new C1286ok(new C1725wk(Is.a, new zzn()), null);

    public static InterfaceC1709wM x() {
        if (C1223nc.c.e(Is.a) == 0) {
            return new DL();
        }
        Ts.a("TextDetectionImpl", "Google Play Services not available", new Object[0]);
        return null;
    }

    @Override // defpackage.LI
    public void I(WJ wj) {
        this.s.a();
    }

    @Override // defpackage.InterfaceC0822gJ, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.s.a();
    }

    @Override // defpackage.InterfaceC1709wM
    public void m(GM gm, InterfaceC1654vM interfaceC1654vM) {
        byte[] bArrArray;
        int i;
        zzf[] zzfVarArr;
        String string;
        int i2;
        if (!this.s.c.a()) {
            Ts.a("TextDetectionImpl", "TextDetector is not operational", new Object[0]);
            ((EM) interfaceC1654vM).a(new C1764xM[0]);
            return;
        }
        C0153Ij c0153IjB = AbstractC1818yL.b(gm);
        if (c0153IjB == null) {
            Ts.a("TextDetectionImpl", "Error converting Mojom Bitmap to Frame", new Object[0]);
            ((EM) interfaceC1654vM).a(new C1764xM[0]);
            return;
        }
        C1286ok c1286ok = this.s;
        Objects.requireNonNull(c1286ok);
        RecognitionOptions recognitionOptions = new RecognitionOptions(new Rect());
        zziqe zziqeVarB = zziqe.b(c0153IjB);
        Bitmap bitmapDecodeByteArray = c0153IjB.c;
        if (bitmapDecodeByteArray == null) {
            C0135Hj c0135Hj = c0153IjB.a;
            ByteBuffer byteBufferA = c0153IjB.a();
            Objects.requireNonNull(c0135Hj);
            int i3 = zziqeVarB.s;
            int i4 = zziqeVarB.t;
            if (byteBufferA.hasArray() && byteBufferA.arrayOffset() == 0) {
                bArrArray = byteBufferA.array();
            } else {
                byte[] bArr = new byte[byteBufferA.capacity()];
                byteBufferA.get(bArr);
                bArrArray = bArr;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new YuvImage(bArrArray, -1, i3, i4, null).compressToJpeg(new Rect(0, 0, i3, i4), 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            bitmapDecodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        }
        Bitmap bitmapCreateBitmap = bitmapDecodeByteArray;
        int width = bitmapCreateBitmap.getWidth();
        int height = bitmapCreateBitmap.getHeight();
        if (zziqeVarB.w != 0) {
            Matrix matrix = new Matrix();
            int i5 = zziqeVarB.w;
            if (i5 == 0) {
                i2 = 0;
            } else if (i5 == 1) {
                i2 = 90;
            } else if (i5 == 2) {
                i2 = 180;
            } else {
                if (i5 != 3) {
                    throw new IllegalArgumentException("Unsupported rotation degree.");
                }
                i2 = 270;
            }
            matrix.postRotate(i2);
            i = 1;
            bitmapCreateBitmap = Bitmap.createBitmap(bitmapCreateBitmap, 0, 0, width, height, matrix, false);
        } else {
            i = 1;
        }
        int i6 = zziqeVarB.w;
        if (i6 == i || i6 == 3) {
            zziqeVarB.s = height;
            zziqeVarB.t = width;
        }
        if (!recognitionOptions.s.isEmpty()) {
            Rect rect = recognitionOptions.s;
            C0135Hj c0135Hj2 = c0153IjB.a;
            int i7 = c0135Hj2.a;
            int i8 = c0135Hj2.b;
            int i9 = zziqeVarB.w;
            if (i9 == i) {
                rect = new Rect(i8 - rect.bottom, rect.left, i8 - rect.top, rect.right);
            } else if (i9 == 2) {
                rect = new Rect(i7 - rect.right, i8 - rect.bottom, i7 - rect.left, i8 - rect.top);
            } else if (i9 == 3) {
                rect = new Rect(rect.top, i7 - rect.right, rect.bottom, i7 - rect.left);
            }
            recognitionOptions.s.set(rect);
        }
        zziqeVarB.w = 0;
        C1725wk c1725wk = c1286ok.c;
        if (c1725wk.a()) {
            try {
                BinderC0615cg binderC0615cg = new BinderC0615cg(bitmapCreateBitmap);
                C1505sk c1505sk = (C1505sk) ((InterfaceC1341pk) c1725wk.e());
                Parcel parcelF = c1505sk.f();
                AbstractC0383Vg.c(parcelF, binderC0615cg);
                AbstractC0383Vg.d(parcelF, zziqeVarB);
                AbstractC0383Vg.d(parcelF, recognitionOptions);
                Parcel parcelC = c1505sk.c(3, parcelF);
                zzf[] zzfVarArr2 = (zzf[]) parcelC.createTypedArray(zzf.CREATOR);
                parcelC.recycle();
                zzfVarArr = zzfVarArr2;
            } catch (RemoteException e) {
                Log.e("TextNativeHandle", "Error calling native text recognizer", e);
                zzfVarArr = new zzf[0];
            }
        } else {
            zzfVarArr = new zzf[0];
        }
        SparseArray sparseArray = new SparseArray();
        for (zzf zzfVar : zzfVarArr) {
            SparseArray sparseArray2 = (SparseArray) sparseArray.get(zzfVar.B);
            if (sparseArray2 == null) {
                sparseArray2 = new SparseArray();
                sparseArray.append(zzfVar.B, sparseArray2);
            }
            sparseArray2.append(zzfVar.C, zzfVar);
        }
        SparseArray sparseArray3 = new SparseArray(sparseArray.size());
        for (int i10 = 0; i10 < sparseArray.size(); i10++) {
            sparseArray3.append(sparseArray.keyAt(i10), new C1231nk((SparseArray) sparseArray.valueAt(i10)));
        }
        C1764xM[] c1764xMArr = new C1764xM[sparseArray3.size()];
        for (int i11 = 0; i11 < sparseArray3.size(); i11++) {
            c1764xMArr[i11] = new C1764xM();
            C1231nk c1231nk = (C1231nk) sparseArray3.valueAt(i11);
            C1764xM c1764xM = c1764xMArr[i11];
            zzf[] zzfVarArr3 = c1231nk.a;
            if (zzfVarArr3.length == 0) {
                string = "";
            } else {
                StringBuilder sb = new StringBuilder(zzfVarArr3[0].w);
                for (int i12 = 1; i12 < c1231nk.a.length; i12++) {
                    sb.append("\n");
                    sb.append(c1231nk.a[i12].w);
                }
                string = sb.toString();
            }
            c1764xM.b = string;
            if (c1231nk.c == null) {
                int iMin = Integer.MAX_VALUE;
                int iMin2 = Integer.MAX_VALUE;
                int iMax = Integer.MIN_VALUE;
                int iMax2 = Integer.MIN_VALUE;
                for (Point point : c1231nk.a()) {
                    iMin = Math.min(iMin, point.x);
                    iMax = Math.max(iMax, point.x);
                    iMin2 = Math.min(iMin2, point.y);
                    iMax2 = Math.max(iMax2, point.y);
                }
                c1231nk.c = new Rect(iMin, iMin2, iMax, iMax2);
            }
            Rect rect2 = c1231nk.c;
            c1764xMArr[i11].c = new SG();
            c1764xMArr[i11].c.b = rect2.left;
            c1764xMArr[i11].c.c = rect2.top;
            c1764xMArr[i11].c.d = rect2.width();
            c1764xMArr[i11].c.e = rect2.height();
            Point[] pointArrA = c1231nk.a();
            c1764xMArr[i11].d = new RG[pointArrA.length];
            for (int i13 = 0; i13 < pointArrA.length; i13++) {
                c1764xMArr[i11].d[i13] = new RG();
                c1764xMArr[i11].d[i13].b = pointArrA[i13].x;
                c1764xMArr[i11].d[i13].c = pointArrA[i13].y;
            }
        }
        ((EM) interfaceC1654vM).a(c1764xMArr);
    }
}
