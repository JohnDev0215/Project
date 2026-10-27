package defpackage;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PointF;
import android.util.SparseArray;
import com.google.android.gms.internal.zziqe;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class BL implements InterfaceC0658dM {
    public final int s;
    public final boolean t;
    public final C0507ak u;

    /* JADX WARN: Removed duplicated region for block: B:27:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public BL(defpackage.C1544tM r10) {
        /*
            r9 = this;
            r9.<init>()
            android.content.Context r0 = defpackage.Is.a
            int r1 = r10.b
            r2 = 32
            int r1 = java.lang.Math.min(r1, r2)
            r9.s = r1
            boolean r10 = r10.c
            r9.t = r10
            r1 = 0
            r2 = 1
            if (r10 == 0) goto L19
            r10 = 0
            goto L1a
        L19:
            r10 = 1
        L1a:
            r3 = 2
            if (r10 == 0) goto L3f
            if (r10 == r2) goto L3f
            if (r10 != r3) goto L22
            goto L3f
        L22:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException     // Catch: java.lang.IllegalArgumentException -> L3b
            r5 = 25
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.IllegalArgumentException -> L3b
            r6.<init>(r5)     // Catch: java.lang.IllegalArgumentException -> L3b
            java.lang.String r5 = "Invalid mode: "
            r6.append(r5)     // Catch: java.lang.IllegalArgumentException -> L3b
            r6.append(r10)     // Catch: java.lang.IllegalArgumentException -> L3b
            java.lang.String r10 = r6.toString()     // Catch: java.lang.IllegalArgumentException -> L3b
            r4.<init>(r10)     // Catch: java.lang.IllegalArgumentException -> L3b
            throw r4     // Catch: java.lang.IllegalArgumentException -> L3b
        L3b:
            r10 = move-exception
            r4 = 0
            r5 = 0
            goto L4d
        L3f:
            int r4 = r9.s     // Catch: java.lang.IllegalArgumentException -> L48
            if (r4 != r2) goto L45
            r4 = 1
            goto L46
        L45:
            r4 = 0
        L46:
            r5 = 1
            goto L67
        L48:
            r4 = move-exception
            r5 = 1
            r8 = r4
            r4 = r10
            r10 = r8
        L4d:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Unexpected exception "
            r6.append(r7)
            r6.append(r10)
            java.lang.String r10 = r6.toString()
            java.lang.Object[] r6 = new java.lang.Object[r1]
            java.lang.String r7 = "FaceDetectionImpl"
            defpackage.Ts.a(r7, r10, r6)
            r10 = r4
            r4 = 0
        L67:
            com.google.android.gms.vision.face.internal.client.zzd r6 = new com.google.android.gms.vision.face.internal.client.zzd
            r6.<init>()
            r6.s = r10
            r6.t = r5
            r6.u = r1
            r6.v = r4
            r6.w = r2
            r4 = -1082130432(0xffffffffbf800000, float:-1.0)
            r6.x = r4
            java.lang.String r4 = "FaceDetector"
            if (r10 == r3) goto L87
            if (r5 != r3) goto L87
            java.lang.String r10 = "Contour is not supported for non-SELFIE mode."
            android.util.Log.e(r4, r10)
            r10 = 0
            goto L88
        L87:
            r10 = 1
        L88:
            int r5 = r6.t
            if (r5 != r3) goto L96
            int r3 = r6.u
            if (r3 != r2) goto L96
            java.lang.String r10 = "Classification is not supported with contour."
            android.util.Log.e(r4, r10)
            goto L97
        L96:
            r1 = r10
        L97:
            if (r1 == 0) goto La7
            dk r10 = new dk
            r10.<init>(r0, r6)
            ak r0 = new ak
            r1 = 0
            r0.<init>(r10, r1)
            r9.u = r0
            return
        La7:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Invalid build options"
            r10.<init>(r0)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.BL.<init>(tM):void");
    }

    @Override // defpackage.LI
    public void I(WJ wj) {
        this.u.a();
    }

    @Override // defpackage.InterfaceC0822gJ, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.u.a();
    }

    @Override // defpackage.InterfaceC0658dM
    public void g(GM gm, InterfaceC0602cM interfaceC0602cM) {
        ByteBuffer byteBufferA;
        C0458Zj[] c0458ZjArrF;
        SparseArray sparseArray;
        int i;
        if (!this.u.d.a()) {
            Ts.a("FaceDetectionImpl", "FaceDetector is not operational", new Object[0]);
            C1544tM c1544tM = new C1544tM();
            c1544tM.c = this.t;
            c1544tM.b = this.s;
            new AL(c1544tM).g(gm, interfaceC0602cM);
            return;
        }
        C0153Ij c0153IjB = AbstractC1818yL.b(gm);
        if (c0153IjB == null) {
            Ts.a("FaceDetectionImpl", "Error converting Mojom Bitmap to Frame", new Object[0]);
            ((C1325pM) interfaceC0602cM).a(new C1048kM[0]);
            return;
        }
        C0507ak c0507ak = this.u;
        Objects.requireNonNull(c0507ak);
        Bitmap bitmap = c0153IjB.c;
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int i2 = width * height;
            byteBufferA = ByteBuffer.allocateDirect(((((height + 1) / 2) * ((width + 1) / 2)) << 1) + i2);
            int i3 = i2;
            for (int i4 = 0; i4 < i2; i4++) {
                int i5 = i4 % width;
                int i6 = i4 / width;
                int pixel = bitmap.getPixel(i5, i6);
                float fRed = Color.red(pixel);
                float fGreen = Color.green(pixel);
                float fBlue = Color.blue(pixel);
                byteBufferA.put(i4, (byte) ((0.114f * fBlue) + (0.587f * fGreen) + (0.299f * fRed)));
                if (i6 % 2 == 0 && i5 % 2 == 0) {
                    float f = (fBlue * 0.5f) + ((-0.331f) * fGreen) + ((-0.169f) * fRed) + 128.0f;
                    float f2 = (fBlue * (-0.081f)) + (fGreen * (-0.419f)) + (fRed * 0.5f) + 128.0f;
                    int i7 = i3 + 1;
                    byteBufferA.put(i3, (byte) f);
                    i3 = i7 + 1;
                    byteBufferA.put(i7, (byte) f2);
                }
            }
        } else {
            byteBufferA = c0153IjB.a();
        }
        synchronized (c0507ak.e) {
            if (!c0507ak.f) {
                throw new RuntimeException("Cannot use detector after release()");
            }
            c0458ZjArrF = c0507ak.d.f(byteBufferA, zziqe.b(c0153IjB));
        }
        HashSet hashSet = new HashSet();
        SparseArray sparseArray2 = new SparseArray(c0458ZjArrF.length);
        int iMax = 0;
        for (C0458Zj c0458Zj : c0458ZjArrF) {
            int i8 = c0458Zj.a;
            iMax = Math.max(iMax, i8);
            if (hashSet.contains(Integer.valueOf(i8))) {
                i8 = iMax + 1;
                iMax = i8;
            }
            hashSet.add(Integer.valueOf(i8));
            C0046Ck c0046Ck = c0507ak.c;
            Objects.requireNonNull(c0046Ck);
            synchronized (C0046Ck.c) {
                i = c0046Ck.a.get(i8, -1);
                if (i == -1) {
                    i = C0046Ck.d;
                    C0046Ck.d++;
                    c0046Ck.a.append(i8, i);
                    c0046Ck.b.append(i, i8);
                }
            }
            sparseArray2.append(i, c0458Zj);
        }
        C1048kM[] c1048kMArr = new C1048kM[sparseArray2.size()];
        int i9 = 0;
        while (i9 < sparseArray2.size()) {
            c1048kMArr[i9] = new C1048kM();
            C0458Zj c0458Zj2 = (C0458Zj) sparseArray2.valueAt(i9);
            List list = c0458Zj2.f;
            ArrayList arrayList = new ArrayList(list.size());
            int i10 = 0;
            int i11 = -1;
            int i12 = -1;
            int i13 = -1;
            while (i10 < list.size()) {
                C0563bk c0563bk = (C0563bk) list.get(i10);
                int i14 = c0563bk.b;
                if (i14 == 4 || i14 == 10 || i14 == 0 || i14 == 6) {
                    C1599uM c1599uM = new C1599uM();
                    c1599uM.b = new RG[]{new RG()};
                    sparseArray = sparseArray2;
                    RG[] rgArr = c1599uM.b;
                    RG rg = rgArr[0];
                    PointF pointF = c0563bk.a;
                    rg.b = pointF.x;
                    rgArr[0].c = pointF.y;
                    if (i14 == 4) {
                        c1599uM.c = 1;
                        i11 = i10;
                    } else if (i14 == 10) {
                        c1599uM.c = 1;
                        i12 = i10;
                    } else if (i14 == 0) {
                        c1599uM.c = 0;
                        i13 = i10;
                    } else {
                        c1599uM.c = 2;
                        arrayList.add(c1599uM);
                    }
                    arrayList.add(c1599uM);
                } else {
                    sparseArray = sparseArray2;
                }
                i10++;
                sparseArray2 = sparseArray;
            }
            SparseArray sparseArray3 = sparseArray2;
            c1048kMArr[i9].c = (C1599uM[]) arrayList.toArray(new C1599uM[arrayList.size()]);
            PointF pointF2 = c0458Zj2.b;
            PointF pointF3 = new PointF(pointF2.x - (c0458Zj2.c / 2.0f), pointF2.y - (c0458Zj2.d / 2.0f));
            c1048kMArr[i9].b = new SG();
            if (i11 == -1 || i12 == -1 || Math.abs(c0458Zj2.e) >= 15.0f) {
                c1048kMArr[i9].b.b = pointF3.x;
                c1048kMArr[i9].b.c = pointF3.y;
                c1048kMArr[i9].b.d = c0458Zj2.c;
                c1048kMArr[i9].b.e = c0458Zj2.d;
            } else {
                PointF pointF4 = ((C0563bk) list.get(i11)).a;
                PointF pointF5 = ((C0563bk) list.get(i12)).a;
                float f3 = pointF4.x - pointF5.x;
                float f4 = i13 != -1 ? ((C0563bk) list.get(i13)).a.y - pointF4.y : -1.0f;
                PointF pointF6 = new PointF((c0458Zj2.c / 2.0f) + pointF3.x, pointF4.y);
                c1048kMArr[i9].b.b = (pointF5.x * 2.0f) - pointF6.x;
                c1048kMArr[i9].b.c = pointF6.y - f3;
                float f5 = 2.0f * f3;
                c1048kMArr[i9].b.d = f5;
                SG sg = c1048kMArr[i9].b;
                if (f4 > f3) {
                    f5 = f4 + f3;
                }
                sg.e = f5;
            }
            i9++;
            sparseArray2 = sparseArray3;
        }
        ((C1325pM) interfaceC0602cM).a(c1048kMArr);
    }
}
