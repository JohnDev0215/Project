package W2;

import B2.AbstractC0062b;
import d5.I;
import e2.C1207o;
import e2.C1208p;
import e2.K;
import e2.L;
import h2.AbstractC1346c;
import h2.t;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class h extends i {
    public static final byte[] o = {79, 112, 117, 115, 72, 101, 97, 100};

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final byte[] f9738p = {79, 112, 117, 115, 84, 97, 103, 115};

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f9739n;

    public static boolean e(t tVar, byte[] bArr) {
        if (tVar.a() < bArr.length) {
            return false;
        }
        int i7 = tVar.f15242b;
        byte[] bArr2 = new byte[bArr.length];
        tVar.f(0, bArr.length, bArr2);
        tVar.H(i7);
        return Arrays.equals(bArr2, bArr);
    }

    @Override // W2.i
    public final long b(t tVar) {
        byte[] bArr = tVar.f15241a;
        return (((long) this.f9746i) * AbstractC0062b.j(bArr[0], bArr.length > 1 ? bArr[1] : (byte) 0)) / 1000000;
    }

    @Override // W2.i
    public final boolean c(t tVar, long j3, M.t tVar2) {
        if (e(tVar, o)) {
            byte[] bArrCopyOf = Arrays.copyOf(tVar.f15241a, tVar.c);
            int i7 = bArrCopyOf[9] & 255;
            ArrayList arrayListA = AbstractC0062b.a(bArrCopyOf);
            if (((C1208p) tVar2.l) != null) {
                return true;
            }
            C1207o c1207o = new C1207o();
            c1207o.f14246m = L.l("audio/opus");
            c1207o.B = i7;
            c1207o.f14228C = 48000;
            c1207o.f14248p = arrayListA;
            tVar2.l = new C1208p(c1207o);
            return true;
        }
        if (!e(tVar, f9738p)) {
            AbstractC1346c.k((C1208p) tVar2.l);
            return false;
        }
        AbstractC1346c.k((C1208p) tVar2.l);
        if (this.f9739n) {
            return true;
        }
        this.f9739n = true;
        tVar.I(8);
        K kR = AbstractC0062b.r(I.x((String[]) AbstractC0062b.u(tVar, false, false).f527m));
        if (kR == null) {
            return true;
        }
        C1207o c1207oA = ((C1208p) tVar2.l).a();
        c1207oA.f14245k = kR.d(((C1208p) tVar2.l).l);
        tVar2.l = new C1208p(c1207oA);
        return true;
    }

    @Override // W2.i
    public final void d(boolean z6) {
        super.d(z6);
        if (z6) {
            this.f9739n = false;
        }
    }
}
