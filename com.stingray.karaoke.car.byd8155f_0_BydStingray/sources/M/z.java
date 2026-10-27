package M;

import B2.O;
import F0.W0;
import H7.H;
import K.C0331b0;
import O.K;
import android.graphics.Rect;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f4172a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final t f4173b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public C0331b0 f4174e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public K f4175f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public W0 f4176g;
    public Rect l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final w f4181m;
    public A7.o c = C0402a.o;
    public A7.o d = C0402a.f4125p;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public T0.y f4177h = new T0.y("", N0.K.f4356b, 4);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public T0.m f4178i = T0.m.f8459g;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ArrayList f4179j = new ArrayList();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Object f4180k = h6.r.n(m7.g.f19257m, new K7.K(this, 5));

    public z(View view, C0404c c0404c, t tVar) {
        this.f4172a = view;
        this.f4173b = tVar;
        this.f4181m = new w(c0404c, tVar);
    }

    public final B a(EditorInfo editorInfo) {
        int i7;
        int i10;
        boolean z6;
        T0.y yVar = this.f4177h;
        String str = yVar.f8481a.l;
        T0.m mVar = this.f4178i;
        int i11 = mVar.f8462e;
        boolean zA = T0.l.a(i11, 1);
        boolean z10 = mVar.f8460a;
        if (zA) {
            i7 = z10 ? 6 : 0;
        } else if (T0.l.a(i11, 0)) {
            i7 = 1;
        } else if (T0.l.a(i11, 2)) {
            i7 = 2;
        } else if (T0.l.a(i11, 6)) {
            i7 = 5;
        } else if (T0.l.a(i11, 5)) {
            i7 = 7;
        } else if (T0.l.a(i11, 3)) {
            i7 = 3;
        } else if (T0.l.a(i11, 4)) {
            i7 = 4;
        } else {
            if (!T0.l.a(i11, 7)) {
                throw new IllegalStateException("invalid ImeAction");
            }
        }
        editorInfo.imeOptions = i7;
        A.f4114a.a(editorInfo, mVar.f8463f);
        int i12 = mVar.d;
        if (T0.o.a(i12, 1)) {
            i10 = 1;
        } else if (T0.o.a(i12, 2)) {
            editorInfo.imeOptions |= Integer.MIN_VALUE;
            i10 = 1;
        } else if (T0.o.a(i12, 3)) {
            i10 = 2;
        } else if (T0.o.a(i12, 4)) {
            i10 = 3;
        } else if (T0.o.a(i12, 5)) {
            i10 = 17;
        } else if (T0.o.a(i12, 6)) {
            i10 = 33;
        } else if (T0.o.a(i12, 7)) {
            i10 = 129;
        } else if (T0.o.a(i12, 8)) {
            i10 = 18;
        } else {
            if (!T0.o.a(i12, 9)) {
                throw new IllegalStateException("Invalid Keyboard Type");
            }
            i10 = 8194;
        }
        editorInfo.inputType = i10;
        if (!z10 && (i10 & 1) == 1) {
            editorInfo.inputType = i10 | 131072;
            if (T0.l.a(mVar.f8462e, 1)) {
                editorInfo.imeOptions |= TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
            }
        }
        if ((editorInfo.inputType & 1) == 1) {
            int i13 = mVar.f8461b;
            if (T0.n.a(i13, 1)) {
                editorInfo.inputType |= 4096;
            } else if (T0.n.a(i13, 2)) {
                editorInfo.inputType |= 8192;
            } else if (T0.n.a(i13, 3)) {
                editorInfo.inputType |= 16384;
            }
            if (mVar.c) {
                editorInfo.inputType |= 32768;
            }
        }
        int i14 = N0.K.c;
        long j3 = yVar.f8482b;
        editorInfo.initialSelStart = (int) (j3 >> 32);
        editorInfo.initialSelEnd = (int) (j3 & 4294967295L);
        H.P(editorInfo, str);
        editorInfo.imeOptions |= 33554432;
        if (!L.e.f3874a || T0.o.a(i12, 7) || T0.o.a(i12, 8)) {
            if (B1.b.a()) {
                z6 = false;
                H1.a.b(editorInfo, false);
            } else {
                z6 = false;
            }
            if (editorInfo.extras == null) {
                editorInfo.extras = new Bundle();
            }
            editorInfo.extras.putBoolean("androidx.core.view.inputmethod.EditorInfoCompat.STYLUS_HANDWRITING_ENABLED", z6);
        } else {
            if (B1.b.a()) {
                H1.a.b(editorInfo, true);
            }
            if (editorInfo.extras == null) {
                editorInfo.extras = new Bundle();
            }
            editorInfo.extras.putBoolean("androidx.core.view.inputmethod.EditorInfoCompat.STYLUS_HANDWRITING_ENABLED", true);
            o.f4148a.a(editorInfo);
        }
        x xVar = y.f4171a;
        if (T1.i.c()) {
            T1.i.a().h(editorInfo);
        }
        B b10 = new B(this.f4177h, new O(this, 28), this.f4178i.c, this.f4174e, this.f4175f, this.f4176g);
        this.f4179j.add(new WeakReference(b10));
        return b10;
    }
}
