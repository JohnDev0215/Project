package A;

import android.os.Handler;
import android.os.Looper;
import android.text.Layout;
import android.util.SparseBooleanArray;
import e2.C1206n;
import g4.C1295A;
import h2.AbstractC1346c;
import java.util.ArrayList;
import x0.InterfaceC2660a;
import x3.AbstractC2673a;

/* JADX INFO: renamed from: A.y0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0050y0 implements InterfaceC2660a, io.sentry.android.replay.util.b {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f300m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f301n;

    public /* synthetic */ C0050y0(int i7, Object obj, boolean z6) {
        this.l = i7;
        this.f301n = obj;
        this.f300m = z6;
    }

    @Override // x0.InterfaceC2660a
    public long N(int i7, long j3, long j10) {
        if (!this.f300m) {
            return 0L;
        }
        S0 s02 = (S0) this.f301n;
        if (s02.f100a.b()) {
            return 0L;
        }
        return s02.g(s02.c(s02.f100a.d(s02.c(s02.f(j10)))));
    }

    @Override // io.sentry.android.replay.util.b
    public int a(int i7) {
        return ((N0.H) this.f301n).i(i7);
    }

    @Override // io.sentry.android.replay.util.b
    public int b() {
        return ((N0.H) this.f301n).f4349b.f4394f;
    }

    @Override // io.sentry.android.replay.util.b
    public Integer c() {
        return null;
    }

    @Override // io.sentry.android.replay.util.b
    public int d(int i7) {
        return ((N0.H) this.f301n).e(i7, true);
    }

    @Override // io.sentry.android.replay.util.b
    public int e(int i7) {
        return C7.a.W(((N0.H) this.f301n).f4349b.d(i7));
    }

    public void f(int i7) {
        AbstractC1346c.j(!this.f300m);
        ((SparseBooleanArray) this.f301n).append(i7, true);
    }

    @Override // io.sentry.android.replay.util.b
    public float g(int i7, int i10) {
        N0.H h10 = (N0.H) this.f301n;
        float fD = h10.d(i10, true);
        return (this.f300m || b() != 1) ? fD : fD - h10.g(i7);
    }

    @Override // io.sentry.android.replay.util.b
    public int h(int i7) {
        N0.o oVar = ((N0.H) this.f301n).f4349b;
        oVar.k(i7);
        ArrayList arrayList = oVar.f4396h;
        Layout layout = ((N0.r) arrayList.get(AbstractC2673a.D(i7, arrayList))).f4402a.d.f5050f;
        O0.w wVar = O0.y.f5060a;
        return layout.getEllipsisCount(i7) > 0 ? 1 : 0;
    }

    @Override // io.sentry.android.replay.util.b
    public int i(int i7) {
        return C7.a.W(((N0.H) this.f301n).f4349b.b(i7));
    }

    public void j(C1206n c1206n) {
        for (int i7 = 0; i7 < c1206n.f14224a.size(); i7++) {
            f(c1206n.b(i7));
        }
    }

    public void k(int... iArr) {
        for (int i7 : iArr) {
            f(i7);
        }
    }

    public C1206n l() {
        AbstractC1346c.j(!this.f300m);
        this.f300m = true;
        return new C1206n((SparseBooleanArray) this.f301n);
    }

    public boolean m() {
        return this.f300m;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean o(int r7, java.lang.CharSequence r8) {
        /*
            r6 = this;
            r0 = 1
            if (r8 == 0) goto L44
            if (r7 < 0) goto L44
            int r1 = r8.length()
            int r1 = r1 - r7
            if (r1 < 0) goto L44
            java.lang.Object r1 = r6.f301n
            D1.e r1 = (D1.e) r1
            if (r1 != 0) goto L17
            boolean r6 = r6.m()
            return r6
        L17:
            r1 = 0
            r2 = 2
            r3 = r1
            r4 = r2
        L1b:
            if (r3 >= r7) goto L39
            if (r4 != r2) goto L39
            char r4 = r8.charAt(r3)
            byte r4 = java.lang.Character.getDirectionality(r4)
            A.y0 r5 = D1.f.f1137a
            if (r4 == 0) goto L36
            if (r4 == r0) goto L34
            if (r4 == r2) goto L34
            switch(r4) {
                case 14: goto L36;
                case 15: goto L36;
                case 16: goto L34;
                case 17: goto L34;
                default: goto L32;
            }
        L32:
            r4 = r2
            goto L37
        L34:
            r4 = r1
            goto L37
        L36:
            r4 = r0
        L37:
            int r3 = r3 + r0
            goto L1b
        L39:
            if (r4 == 0) goto L43
            if (r4 == r0) goto L42
            boolean r0 = r6.m()
            goto L43
        L42:
            r0 = r1
        L43:
            return r0
        L44:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: A.C0050y0.o(int, java.lang.CharSequence):boolean");
    }

    public synchronized void p(g4.x xVar, boolean z6) {
        try {
            if (this.f300m || z6) {
                ((Handler) this.f301n).obtainMessage(1, xVar).sendToTarget();
            } else {
                this.f300m = true;
                xVar.e();
                this.f300m = false;
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // x0.InterfaceC2660a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object q0(long r3, long r5, q7.InterfaceC2189c r7) throws java.lang.Throwable {
        /*
            r2 = this;
            boolean r3 = r7 instanceof A.C0048x0
            if (r3 == 0) goto L13
            r3 = r7
            A.x0 r3 = (A.C0048x0) r3
            int r4 = r3.o
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r4 & r0
            if (r1 == 0) goto L13
            int r4 = r4 - r0
            r3.o = r4
            goto L1a
        L13:
            A.x0 r3 = new A.x0
            s7.c r7 = (s7.AbstractC2410c) r7
            r3.<init>(r2, r7)
        L1a:
            java.lang.Object r4 = r3.f297m
            r7.a r7 = r7.EnumC2303a.l
            int r0 = r3.o
            r1 = 1
            if (r0 == 0) goto L33
            if (r0 != r1) goto L2b
            long r5 = r3.l
            h8.AbstractC1386c.u(r4)
            goto L49
        L2b:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r3 = "call to 'resume' before 'invoke' with coroutine"
            r2.<init>(r3)
            throw r2
        L33:
            h8.AbstractC1386c.u(r4)
            boolean r4 = r2.f300m
            if (r4 == 0) goto L52
            r3.l = r5
            r3.o = r1
            java.lang.Object r2 = r2.f301n
            A.S0 r2 = (A.S0) r2
            java.lang.Object r4 = r2.b(r5, r3)
            if (r4 != r7) goto L49
            return r7
        L49:
            a1.o r4 = (a1.o) r4
            long r2 = r4.f11044a
            long r2 = a1.o.d(r5, r2)
            goto L54
        L52:
            r2 = 0
        L54:
            a1.o r4 = new a1.o
            r4.<init>(r2)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: A.C0050y0.q0(long, long, q7.c):java.lang.Object");
    }

    public String toString() {
        switch (this.l) {
            case 2:
                return this.f300m ? "FALL_THROUGH" : String.valueOf(this.f301n);
            default:
                return super.toString();
        }
    }

    public C0050y0(N0.H h10, boolean z6) {
        this.l = 8;
        A7.m.f(h10, "layout");
        this.f301n = h10;
        this.f300m = z6;
    }

    public C0050y0(String str, boolean z6) {
        this.l = 7;
        this.f300m = z6;
        this.f301n = str;
    }

    public C0050y0(int i7) {
        this.l = i7;
        switch (i7) {
            case 6:
                this.f301n = new Handler(Looper.getMainLooper(), new C1295A());
                break;
            case 9:
                break;
            default:
                this.f301n = new SparseBooleanArray();
                break;
        }
    }

    public C0050y0(D1.e eVar, boolean z6) {
        this.l = 1;
        this.l = 1;
        this.f301n = eVar;
        this.f300m = z6;
    }
}
