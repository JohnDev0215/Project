package w8;

import A7.D;
import A7.m;
import B8.t;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import W7.n;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Surface;
import androidx.compose.ui.platform.AndroidComposeView;
import c8.C1039d;
import d8.C1129p;
import d8.C1135v;
import io.sentry.android.core.RunnableC1447x;
import java.util.ArrayList;
import l5.C1778f;
import n7.AbstractC1998m;
import o8.C2058c;
import o8.C2061f;
import u.C2472l;
import x.AbstractC2652s;
import x.C2617F;
import x.H0;
import x.InterfaceC2616E;
import x.InterfaceC2653t;
import y0.q;
import y0.r;
import y0.s;
import z2.C2759e;
import z2.u;

/* JADX INFO: renamed from: w8.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2607b implements InterfaceC2653t, H0, u {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f23345m;

    public /* synthetic */ C2607b(Object obj, int i7) {
        this.l = i7;
        this.f23345m = obj;
    }

    @Override // x.H0, x.F0
    public boolean a() {
        ((t) this.f23345m).getClass();
        return false;
    }

    @Override // x.F0
    public long b(AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3) {
        return ((t) this.f23345m).b(abstractC2652s, abstractC2652s2, abstractC2652s3);
    }

    @Override // x.F0
    public AbstractC2652s c(long j3, AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3) {
        return ((t) this.f23345m).c(j3, abstractC2652s, abstractC2652s2, abstractC2652s3);
    }

    @Override // z2.u
    public void d() {
        C2759e c2759e = (C2759e) this.f23345m;
        Surface surface = c2759e.f24290a1;
        if (surface != null) {
            m2.b bVar = c2759e.f24279P0;
            Handler handler = (Handler) bVar.l;
            if (handler != null) {
                handler.post(new RunnableC1447x(bVar, surface, SystemClock.elapsedRealtime(), 2));
            }
            c2759e.f24292d1 = true;
        }
    }

    @Override // z2.u
    public void e() {
        C2759e c2759e = (C2759e) this.f23345m;
        if (c2759e.f24290a1 != null) {
            c2759e.J0(0, 1);
        }
    }

    public t4.e f(C1778f c1778f, AndroidComposeView androidComposeView) {
        boolean z6;
        long j3;
        long j10;
        ArrayList arrayList;
        int i7;
        int i10;
        ArrayList arrayList2 = (ArrayList) c1778f.f18638m;
        C2472l c2472l = new C2472l(arrayList2.size());
        int size = arrayList2.size();
        int i11 = 0;
        while (i11 < size) {
            s sVar = (s) arrayList2.get(i11);
            long j11 = sVar.f24089a;
            C2472l c2472l2 = (C2472l) this.f23345m;
            r rVar = (r) c2472l2.b(j11);
            if (rVar == null) {
                j10 = sVar.f24090b;
                j3 = sVar.d;
                z6 = false;
            } else {
                long jB = androidComposeView.B(rVar.f24088b);
                long j12 = rVar.f24087a;
                z6 = rVar.c;
                j3 = jB;
                j10 = j12;
            }
            ArrayList arrayList3 = sVar.f24095i;
            long j13 = sVar.f24096j;
            long j14 = sVar.f24097k;
            long j15 = sVar.f24089a;
            c2472l.e(j15, new q(j15, sVar.f24090b, sVar.d, sVar.f24091e, sVar.f24092f, j10, j3, z6, sVar.f24093g, arrayList3, j13, j14));
            long j16 = sVar.f24089a;
            boolean z10 = sVar.f24091e;
            if (z10) {
                i10 = i11;
                arrayList = arrayList2;
                i7 = size;
                c2472l2.e(j16, new r(sVar.f24090b, sVar.c, z10));
            } else {
                arrayList = arrayList2;
                i7 = size;
                i10 = i11;
                c2472l2.f(j16);
            }
            i11 = i10 + 1;
            arrayList2 = arrayList;
            size = i7;
        }
        return new t4.e(c2472l, 13, c1778f);
    }

    public InterfaceC0652e g(n nVar) {
        m.f(nVar, "javaClass");
        C2058c c2058cC = nVar.c();
        Class cls = nVar.f9891a;
        Class<?> declaringClass = cls.getDeclaringClass();
        n nVar2 = declaringClass != null ? new n(declaringClass) : null;
        if (nVar2 != null) {
            InterfaceC0652e interfaceC0652eG = g(nVar2);
            y8.n nVarL0 = interfaceC0652eG != null ? interfaceC0652eG.l0() : null;
            InterfaceC0654g interfaceC0654gC = nVarL0 != null ? nVarL0.c(C2061f.e(cls.getSimpleName()), Y7.b.f10803s) : null;
            if (interfaceC0654gC instanceof InterfaceC0652e) {
                return (InterfaceC0652e) interfaceC0654gC;
            }
            return null;
        }
        C2058c c2058cE = c2058cC.e();
        m.e(c2058cE, "fqName.parent()");
        C1129p c1129p = (C1129p) AbstractC1998m.a0(((C1039d) this.f23345m).c(c2058cE));
        if (c1129p == null) {
            return null;
        }
        C1135v c1135v = c1129p.f13618v.d;
        c1135v.getClass();
        return c1135v.v(C2061f.e(cls.getSimpleName()), nVar);
    }

    @Override // x.InterfaceC2653t
    public InterfaceC2616E get(int i7) {
        switch (this.l) {
            case 2:
                return (C2617F) this.f23345m;
            default:
                return (InterfaceC2616E) this.f23345m;
        }
    }

    @Override // x.F0
    public AbstractC2652s o(long j3, AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3) {
        return ((t) this.f23345m).o(j3, abstractC2652s, abstractC2652s2, abstractC2652s3);
    }

    @Override // x.F0
    public AbstractC2652s t(AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3) {
        return ((t) this.f23345m).t(abstractC2652s, abstractC2652s2, abstractC2652s3);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0028 A[PHI: r11
  0x0028: PHI (r11v1 int) = (r11v0 int), (r11v6 int), (r11v7 int) binds: [B:5:0x0019, B:10:0x0022, B:12:0x0025] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public C2607b(int[] r23, float[] r24, float[][] r25) {
        /*
            r22 = this;
            r0 = r22
            r1 = r24
            r2 = 1
            r0.l = r2
            r22.<init>()
            int r2 = r1.length
            r3 = 1
            int r2 = r2 - r3
            x.u[][] r4 = new x.C2654u[r2][]
            r5 = 0
            r7 = r3
            r8 = r7
            r6 = r5
        L13:
            if (r6 >= r2) goto L6d
            r9 = r23[r6]
            r10 = 2
            r11 = 3
            if (r9 == 0) goto L28
            if (r9 == r3) goto L31
            if (r9 == r10) goto L2f
            if (r9 == r11) goto L2a
            r11 = 4
            if (r9 == r11) goto L28
            r11 = 5
            if (r9 == r11) goto L28
            goto L33
        L28:
            r8 = r11
            goto L33
        L2a:
            if (r7 != r3) goto L31
            goto L2f
        L2d:
            r8 = r7
            goto L33
        L2f:
            r7 = r10
            goto L2d
        L31:
            r7 = r3
            goto L2d
        L33:
            r9 = r25[r6]
            int r11 = r9.length
            int r11 = r11 / r10
            int r9 = r9.length
            int r9 = r9 % r10
            int r9 = r9 + r11
            x.u[] r10 = new x.C2654u[r9]
            r15 = r5
        L3d:
            if (r15 >= r9) goto L68
            int r11 = r15 * 2
            x.u r19 = new x.u
            r13 = r1[r6]
            int r12 = r6 + 1
            r14 = r1[r12]
            r16 = r25[r6]
            r17 = r16[r11]
            int r18 = r11 + 1
            r16 = r16[r18]
            r12 = r25[r12]
            r20 = r12[r11]
            r18 = r12[r18]
            r11 = r19
            r12 = r8
            r21 = r15
            r15 = r17
            r17 = r20
            r11.<init>(r12, r13, r14, r15, r16, r17, r18)
            r10[r21] = r19
            int r15 = r21 + 1
            goto L3d
        L68:
            r4[r6] = r10
            int r6 = r6 + 1
            goto L13
        L6d:
            r0.f23345m = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: w8.C2607b.<init>(int[], float[], float[][]):void");
    }

    public C2607b(int i7) {
        this.l = i7;
        switch (i7) {
            case 7:
                break;
            default:
                this.f23345m = new C2472l((Object) null);
                break;
        }
    }

    public C2607b(float f10, float f11, AbstractC2652s abstractC2652s) {
        Object c2607b;
        this.l = 4;
        if (abstractC2652s != null) {
            c2607b = new D(f10, f11, abstractC2652s);
        } else {
            c2607b = new C2607b(f10, f11);
        }
        this.f23345m = new t(c2607b, 26);
    }

    public C2607b(float f10, float f11) {
        this.l = 2;
        this.f23345m = new C2617F(f10, f11, 0.01f);
    }
}
