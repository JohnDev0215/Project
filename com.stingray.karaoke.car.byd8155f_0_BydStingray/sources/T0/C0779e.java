package T0;

import F0.T;
import N0.H;
import N0.K;
import a.AbstractC0904a;
import android.graphics.Matrix;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.InputMethodManager;
import androidx.compose.ui.platform.AndroidComposeView;
import l3.P;
import n0.AbstractC1861K;
import n0.C1854D;
import x3.AbstractC2673a;

/* JADX INFO: renamed from: T0.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0779e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AndroidComposeView f8436a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final P f8437b;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8438e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f8439f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f8440g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f8441h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f8442i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public y f8443j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public H f8444k;
    public s l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public m0.d f8446n;
    public m0.d o;
    public final Object c = new Object();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public A7.o f8445m = C0778d.o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final CursorAnchorInfo.Builder f8447p = new CursorAnchorInfo.Builder();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final float[] f8448q = C1854D.a();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Matrix f8449r = new Matrix();

    public C0779e(AndroidComposeView androidComposeView, P p10) {
        this.f8436a = androidComposeView;
        this.f8437b = p10;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, m7.f] */
    /* JADX WARN: Type inference failed for: r3v3, types: [A7.o, z7.k] */
    public final void a() {
        boolean z6;
        P p10 = this.f8437b;
        ?? r22 = p10.f18538n;
        InputMethodManager inputMethodManager = (InputMethodManager) r22.getValue();
        View view = (View) p10.f18537m;
        if (inputMethodManager.isActive(view)) {
            ?? r32 = this.f8445m;
            float[] fArr = this.f8448q;
            r32.b(new C1854D(fArr));
            AndroidComposeView androidComposeView = this.f8436a;
            androidComposeView.y();
            C1854D.g(fArr, androidComposeView.f11648a0);
            float fD = m0.c.d(androidComposeView.f11652e0);
            float fE = m0.c.e(androidComposeView.f11652e0);
            float[] fArr2 = androidComposeView.f11647W;
            C1854D.d(fArr2);
            C1854D.h(fD, fE, 0.0f, fArr2);
            float fO = T.o(0, 0, fArr2, fArr);
            float fO2 = T.o(0, 1, fArr2, fArr);
            float fO3 = T.o(0, 2, fArr2, fArr);
            float fO4 = T.o(0, 3, fArr2, fArr);
            float fO5 = T.o(1, 0, fArr2, fArr);
            float fO6 = T.o(1, 1, fArr2, fArr);
            float fO7 = T.o(1, 2, fArr2, fArr);
            float fO8 = T.o(1, 3, fArr2, fArr);
            float fO9 = T.o(2, 0, fArr2, fArr);
            float fO10 = T.o(2, 1, fArr2, fArr);
            float fO11 = T.o(2, 2, fArr2, fArr);
            float fO12 = T.o(2, 3, fArr2, fArr);
            float fO13 = T.o(3, 0, fArr2, fArr);
            float fO14 = T.o(3, 1, fArr2, fArr);
            float fO15 = T.o(3, 2, fArr2, fArr);
            float fO16 = T.o(3, 3, fArr2, fArr);
            fArr[0] = fO;
            fArr[1] = fO2;
            fArr[2] = fO3;
            fArr[3] = fO4;
            fArr[4] = fO5;
            fArr[5] = fO6;
            fArr[6] = fO7;
            fArr[7] = fO8;
            fArr[8] = fO9;
            fArr[9] = fO10;
            fArr[10] = fO11;
            fArr[11] = fO12;
            fArr[12] = fO13;
            fArr[13] = fO14;
            fArr[14] = fO15;
            fArr[15] = fO16;
            Matrix matrix = this.f8449r;
            AbstractC1861K.x(matrix, fArr);
            y yVar = this.f8443j;
            A7.m.c(yVar);
            s sVar = this.l;
            A7.m.c(sVar);
            H h10 = this.f8444k;
            A7.m.c(h10);
            m0.d dVar = this.f8446n;
            A7.m.c(dVar);
            m0.d dVar2 = this.o;
            A7.m.c(dVar2);
            boolean z10 = this.f8439f;
            boolean z11 = this.f8440g;
            boolean z12 = this.f8441h;
            boolean z13 = this.f8442i;
            CursorAnchorInfo.Builder builder = this.f8447p;
            builder.reset();
            builder.setMatrix(matrix);
            long j3 = yVar.f8482b;
            int iE = K.e(j3);
            builder.setSelectionRange(iE, K.d(j3));
            Y0.h hVar = Y0.h.f10616m;
            if (!z10 || iE < 0) {
                z6 = z13;
            } else {
                int iB = sVar.b(iE);
                m0.d dVarC = h10.c(iB);
                z6 = z13;
                float fU = AbstractC2673a.u(dVarC.f18882a, 0.0f, (int) (h10.c >> 32));
                boolean zT = H7.H.t(dVar, fU, dVarC.f18883b);
                boolean zT2 = H7.H.t(dVar, fU, dVarC.d);
                boolean z14 = h10.a(iB) == hVar;
                int i7 = (zT || zT2) ? 1 : 0;
                if (!zT || !zT2) {
                    i7 |= 2;
                }
                int i10 = z14 ? i7 | 4 : i7;
                float f10 = dVarC.f18883b;
                float f11 = dVarC.d;
                builder.setInsertionMarkerLocation(fU, f10, f11, f11, i10);
            }
            if (z11) {
                K k10 = yVar.c;
                int iE2 = k10 != null ? K.e(k10.f4357a) : -1;
                int iD = k10 != null ? K.d(k10.f4357a) : -1;
                if (iE2 >= 0 && iE2 < iD) {
                    builder.setComposingText(iE2, yVar.f8481a.l.subSequence(iE2, iD));
                    int iB2 = sVar.b(iE2);
                    int iB3 = sVar.b(iD);
                    float[] fArr3 = new float[(iB3 - iB2) * 4];
                    h10.f4349b.a(AbstractC0904a.q(iB2, iB3), fArr3);
                    while (iE2 < iD) {
                        int iB4 = sVar.b(iE2);
                        int i11 = (iB4 - iB2) * 4;
                        float f12 = fArr3[i11];
                        int i12 = iD;
                        float f13 = fArr3[i11 + 1];
                        int i13 = iB2;
                        float f14 = fArr3[i11 + 2];
                        float f15 = fArr3[i11 + 3];
                        s sVar2 = sVar;
                        int i14 = (dVar.c <= f12 || f14 <= dVar.f18882a || dVar.d <= f13 || f15 <= dVar.f18883b) ? 0 : 1;
                        if (!H7.H.t(dVar, f12, f13) || !H7.H.t(dVar, f14, f15)) {
                            i14 |= 2;
                        }
                        builder.addCharacterBounds(iE2, f12, f13, f14, f15, h10.a(iB4) == hVar ? i14 | 4 : i14);
                        iE2++;
                        iD = i12;
                        iB2 = i13;
                        sVar = sVar2;
                    }
                }
            }
            int i15 = Build.VERSION.SDK_INT;
            if (i15 >= 33 && z12) {
                AbstractC0776b.a(builder, dVar2);
            }
            if (i15 >= 34 && z6) {
                AbstractC0777c.a(builder, h10, dVar);
            }
            ((InputMethodManager) r22.getValue()).updateCursorAnchorInfo(view, builder.build());
            this.f8438e = false;
        }
    }
}
