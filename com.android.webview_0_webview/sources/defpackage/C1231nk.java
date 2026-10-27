package defpackage;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import com.google.android.gms.vision.text.internal.client.zza;
import com.google.android.gms.vision.text.internal.client.zzf;

/* JADX INFO: renamed from: nk, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1231nk {
    public zzf[] a;
    public Point[] b;
    public Rect c;

    public C1231nk(SparseArray sparseArray) {
        this.a = new zzf[sparseArray.size()];
        int i = 0;
        while (true) {
            zzf[] zzfVarArr = this.a;
            if (i >= zzfVarArr.length) {
                return;
            }
            zzfVarArr[i] = (zzf) sparseArray.valueAt(i);
            i++;
        }
    }

    public Point[] a() {
        C1231nk c1231nk;
        zzf[] zzfVarArr;
        C1231nk c1231nk2 = this;
        if (c1231nk2.b == null) {
            char c = 0;
            if (c1231nk2.a.length == 0) {
                c1231nk2.b = new Point[0];
                c1231nk = c1231nk2;
            } else {
                int iMax = Integer.MIN_VALUE;
                int iMax2 = Integer.MIN_VALUE;
                int iMin = Integer.MAX_VALUE;
                int iMin2 = Integer.MAX_VALUE;
                int i = 0;
                while (true) {
                    zzfVarArr = c1231nk2.a;
                    if (i >= zzfVarArr.length) {
                        break;
                    }
                    zza zzaVar = zzfVarArr[i].t;
                    zza zzaVar2 = zzfVarArr[c].t;
                    int i2 = -zzaVar2.s;
                    int i3 = -zzaVar2.t;
                    double dSin = Math.sin(Math.toRadians(zzaVar2.w));
                    double dCos = Math.cos(Math.toRadians(zzaVar2.w));
                    Point[] pointArr = new Point[4];
                    pointArr[c] = new Point(zzaVar.s, zzaVar.t);
                    pointArr[c].offset(i2, i3);
                    int i4 = iMax2;
                    int i5 = (int) ((((double) pointArr[c].y) * dSin) + (((double) pointArr[c].x) * dCos));
                    int i6 = (int) ((((double) pointArr[0].y) * dCos) + (((double) (-pointArr[0].x)) * dSin));
                    pointArr[0].x = i5;
                    pointArr[0].y = i6;
                    pointArr[1] = new Point(zzaVar.u + i5, i6);
                    pointArr[2] = new Point(zzaVar.u + i5, zzaVar.v + i6);
                    pointArr[3] = new Point(i5, i6 + zzaVar.v);
                    iMax2 = i4;
                    for (int i7 = 0; i7 < 4; i7++) {
                        Point point = pointArr[i7];
                        iMin = Math.min(iMin, point.x);
                        iMax = Math.max(iMax, point.x);
                        iMin2 = Math.min(iMin2, point.y);
                        iMax2 = Math.max(iMax2, point.y);
                    }
                    i++;
                    c = 0;
                    c1231nk2 = this;
                }
                int i8 = iMax2;
                zza zzaVar3 = zzfVarArr[0].t;
                int i9 = zzaVar3.s;
                int i10 = zzaVar3.t;
                double dSin2 = Math.sin(Math.toRadians(zzaVar3.w));
                double dCos2 = Math.cos(Math.toRadians(zzaVar3.w));
                Point[] pointArr2 = {new Point(iMin, iMin2), new Point(iMax, iMin2), new Point(iMax, i8), new Point(iMin, i8)};
                for (int i11 = 0; i11 < 4; i11++) {
                    int i12 = (int) ((((double) pointArr2[i11].x) * dCos2) - (((double) pointArr2[i11].y) * dSin2));
                    int i13 = (int) ((((double) pointArr2[i11].y) * dCos2) + (((double) pointArr2[i11].x) * dSin2));
                    pointArr2[i11].x = i12;
                    pointArr2[i11].y = i13;
                    pointArr2[i11].offset(i9, i10);
                }
                c1231nk = this;
                c1231nk.b = pointArr2;
            }
        } else {
            c1231nk = c1231nk2;
        }
        return c1231nk.b;
    }
}
