package X3;

import N0.q;
import a4.AbstractC0927h;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import h2.s;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import u.C2472l;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class j extends b {

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final StringBuilder f10068D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final RectF f10069E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final Matrix f10070F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final P3.a f10071G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final P3.a f10072H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final HashMap f10073I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final C2472l f10074J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final ArrayList f10075K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final R3.f f10076L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public final O3.j f10077M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public final O3.a f10078N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public final int f10079O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public final R3.f f10080P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public final R3.f f10081Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public final R3.f f10082R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public final R3.f f10083S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public final R3.f f10084T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public final R3.f f10085U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public final R3.f f10086V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public final R3.f f10087W;

    public j(O3.j jVar, e eVar) {
        s sVar;
        s sVar2;
        V3.a aVar;
        s sVar3;
        V3.a aVar2;
        s sVar4;
        V3.a aVar3;
        q qVar;
        V3.a aVar4;
        q qVar2;
        V3.b bVar;
        q qVar3;
        V3.b bVar2;
        q qVar4;
        V3.a aVar5;
        q qVar5;
        V3.a aVar6;
        super(jVar, eVar);
        this.f10068D = new StringBuilder(2);
        this.f10069E = new RectF();
        this.f10070F = new Matrix();
        P3.a aVar7 = new P3.a(1, 1);
        aVar7.setStyle(Paint.Style.FILL);
        this.f10071G = aVar7;
        P3.a aVar8 = new P3.a(1, 2);
        aVar8.setStyle(Paint.Style.STROKE);
        this.f10072H = aVar8;
        this.f10073I = new HashMap();
        this.f10074J = new C2472l((Object) null);
        this.f10075K = new ArrayList();
        this.f10079O = 2;
        this.f10077M = jVar;
        this.f10078N = eVar.f10038b;
        R3.f fVar = new R3.f(3, (List) eVar.f10049q.f476m);
        this.f10076L = fVar;
        fVar.a(this);
        d(fVar);
        io.sentry.internal.debugmeta.c cVar = eVar.f10050r;
        if (cVar != null && (qVar5 = (q) cVar.f16569m) != null && (aVar6 = (V3.a) qVar5.f4399m) != null) {
            R3.e eVarT0 = aVar6.t0();
            this.f10080P = (R3.f) eVarT0;
            eVarT0.a(this);
            d(eVarT0);
        }
        if (cVar != null && (qVar4 = (q) cVar.f16569m) != null && (aVar5 = (V3.a) qVar4.f4400n) != null) {
            R3.e eVarT02 = aVar5.t0();
            this.f10081Q = (R3.f) eVarT02;
            eVarT02.a(this);
            d(eVarT02);
        }
        if (cVar != null && (qVar3 = (q) cVar.f16569m) != null && (bVar2 = (V3.b) qVar3.o) != null) {
            R3.f fVarT0 = bVar2.t0();
            this.f10082R = fVarT0;
            fVarT0.a(this);
            d(fVarT0);
        }
        if (cVar != null && (qVar2 = (q) cVar.f16569m) != null && (bVar = (V3.b) qVar2.f4401p) != null) {
            R3.f fVarT02 = bVar.t0();
            this.f10083S = fVarT02;
            fVarT02.a(this);
            d(fVarT02);
        }
        if (cVar != null && (qVar = (q) cVar.f16569m) != null && (aVar4 = (V3.a) qVar.l) != null) {
            R3.e eVarT03 = aVar4.t0();
            this.f10084T = (R3.f) eVarT03;
            eVarT03.a(this);
            d(eVarT03);
        }
        if (cVar != null && (sVar4 = (s) cVar.f16570n) != null && (aVar3 = (V3.a) sVar4.f15238b) != null) {
            R3.e eVarT04 = aVar3.t0();
            this.f10085U = (R3.f) eVarT04;
            eVarT04.a(this);
            d(eVarT04);
        }
        if (cVar != null && (sVar3 = (s) cVar.f16570n) != null && (aVar2 = (V3.a) sVar3.c) != null) {
            R3.e eVarT05 = aVar2.t0();
            this.f10086V = (R3.f) eVarT05;
            eVarT05.a(this);
            d(eVarT05);
        }
        if (cVar != null && (sVar2 = (s) cVar.f16570n) != null && (aVar = (V3.a) sVar2.d) != null) {
            R3.e eVarT06 = aVar.t0();
            this.f10087W = (R3.f) eVarT06;
            eVarT06.a(this);
            d(eVarT06);
        }
        if (cVar == null || (sVar = (s) cVar.f16570n) == null) {
            return;
        }
        this.f10079O = sVar.f15237a;
    }

    public static void o(String str, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawText(str, 0, str.length(), 0.0f, 0.0f, paint);
    }

    public static void p(Path path, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawPath(path, paint);
    }

    @Override // X3.b, Q3.e
    public final void a(RectF rectF, Matrix matrix, boolean z6) {
        super.a(rectF, matrix, z6);
        O3.a aVar = this.f10078N;
        rectF.set(0.0f, 0.0f, aVar.f5077k.width(), aVar.f5077k.height());
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0369  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0371  */
    @Override // X3.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void h(android.graphics.Canvas r31, android.graphics.Matrix r32, int r33, a4.C0920a r34) {
        /*
            Method dump skipped, instruction units count: 1305
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: X3.j.h(android.graphics.Canvas, android.graphics.Matrix, int, a4.a):void");
    }

    public final void n(U3.b bVar, int i7, int i10) {
        R3.f fVar = this.f10080P;
        P3.a aVar = this.f10071G;
        if (fVar == null || !r(i10)) {
            aVar.setColor(bVar.f9002h);
        } else {
            aVar.setColor(((Integer) fVar.d()).intValue());
        }
        R3.f fVar2 = this.f10081Q;
        P3.a aVar2 = this.f10072H;
        if (fVar2 == null || !r(i10)) {
            aVar2.setColor(bVar.f9003i);
        } else {
            aVar2.setColor(((Integer) fVar2.d()).intValue());
        }
        R3.f fVar3 = this.f10015w.f7422j;
        int iIntValue = 100;
        int iIntValue2 = fVar3 == null ? 100 : ((Integer) fVar3.d()).intValue();
        R3.f fVar4 = this.f10084T;
        if (fVar4 != null && r(i10)) {
            iIntValue = ((Integer) fVar4.d()).intValue();
        }
        int iRound = Math.round((((iIntValue / 100.0f) * ((iIntValue2 * 255.0f) / 100.0f)) * i7) / 255.0f);
        aVar.setAlpha(iRound);
        aVar2.setAlpha(iRound);
        R3.f fVar5 = this.f10082R;
        if (fVar5 == null || !r(i10)) {
            aVar2.setStrokeWidth(AbstractC0927h.c() * bVar.f9004j);
        } else {
            aVar2.setStrokeWidth(((Float) fVar5.d()).floatValue());
        }
    }

    public final i q(int i7) {
        ArrayList arrayList = this.f10075K;
        for (int size = arrayList.size(); size < i7; size++) {
            i iVar = new i();
            iVar.f10066a = "";
            iVar.f10067b = 0.0f;
            arrayList.add(iVar);
        }
        return (i) arrayList.get(i7 - 1);
    }

    public final boolean r(int i7) {
        R3.f fVar;
        int length = ((U3.b) this.f10076L.d()).f8997a.length();
        R3.f fVar2 = this.f10085U;
        if (fVar2 == null || (fVar = this.f10086V) == null) {
            return true;
        }
        int iMin = Math.min(((Integer) fVar2.d()).intValue(), ((Integer) fVar.d()).intValue());
        int iMax = Math.max(((Integer) fVar2.d()).intValue(), ((Integer) fVar.d()).intValue());
        R3.f fVar3 = this.f10087W;
        if (fVar3 != null) {
            int iIntValue = ((Integer) fVar3.d()).intValue();
            iMin += iIntValue;
            iMax += iIntValue;
        }
        if (this.f10079O == 2) {
            return i7 >= iMin && i7 < iMax;
        }
        float f10 = (i7 / length) * 100.0f;
        return f10 >= ((float) iMin) && f10 < ((float) iMax);
    }

    public final boolean s(Canvas canvas, U3.b bVar, int i7, float f10) {
        PointF pointF = bVar.l;
        PointF pointF2 = bVar.f9006m;
        float fC = AbstractC0927h.c();
        float f11 = (i7 * bVar.f9000f * fC) + (pointF == null ? 0.0f : (bVar.f9000f * fC) + pointF.y);
        if (this.f10077M.B && pointF2 != null && pointF != null && f11 >= pointF.y + pointF2.y + bVar.c) {
            return false;
        }
        float f12 = pointF == null ? 0.0f : pointF.x;
        float f13 = pointF2 != null ? pointF2.x : 0.0f;
        int iC = AbstractC2641j.c(bVar.d);
        if (iC == 0) {
            canvas.translate(f12, f11);
        } else if (iC == 1) {
            canvas.translate((f12 + f13) - f10, f11);
        } else if (iC == 2) {
            canvas.translate(((f13 / 2.0f) + f12) - (f10 / 2.0f), f11);
        }
        return true;
    }

    public final List t(String str, float f10, U3.c cVar, float f11, float f12, boolean z6) {
        float fMeasureText;
        int i7 = 0;
        int i10 = 0;
        boolean z10 = false;
        int i11 = 0;
        float f13 = 0.0f;
        float f14 = 0.0f;
        float f15 = 0.0f;
        for (int i12 = 0; i12 < str.length(); i12++) {
            char cCharAt = str.charAt(i12);
            if (z6) {
                U3.d dVar = (U3.d) this.f10078N.f5074h.c(U3.d.a(cCharAt, cVar.f9007a, cVar.c));
                if (dVar != null) {
                    fMeasureText = (AbstractC0927h.c() * ((float) dVar.c) * f11) + f12;
                }
            } else {
                fMeasureText = this.f10071G.measureText(str.substring(i12, i12 + 1)) + f12;
            }
            if (cCharAt == ' ') {
                z10 = true;
                f15 = fMeasureText;
            } else if (z10) {
                z10 = false;
                i11 = i12;
                f14 = fMeasureText;
            } else {
                f14 += fMeasureText;
            }
            f13 += fMeasureText;
            if (f10 > 0.0f && f13 >= f10 && cCharAt != ' ') {
                i7++;
                i iVarQ = q(i7);
                if (i11 == i10) {
                    iVarQ.f10066a = str.substring(i10, i12).trim();
                    iVarQ.f10067b = (f13 - fMeasureText) - ((r10.length() - r8.length()) * f15);
                    i10 = i12;
                    i11 = i10;
                    f13 = fMeasureText;
                    f14 = f13;
                } else {
                    iVarQ.f10066a = str.substring(i10, i11 - 1).trim();
                    iVarQ.f10067b = ((f13 - f14) - ((r8.length() - r14.length()) * f15)) - f15;
                    f13 = f14;
                    i10 = i11;
                }
            }
        }
        if (f13 > 0.0f) {
            i7++;
            i iVarQ2 = q(i7);
            iVarQ2.f10066a = str.substring(i10);
            iVarQ2.f10067b = f13;
        }
        return this.f10075K.subList(0, i7);
    }
}
