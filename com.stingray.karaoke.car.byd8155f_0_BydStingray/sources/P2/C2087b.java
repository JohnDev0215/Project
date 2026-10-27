package p2;

import android.content.Context;
import android.content.IntentFilter;
import android.util.SparseArray;
import d5.AbstractC1095q;
import d5.C1077F;
import d5.I;
import d5.K;
import d5.d0;
import e2.C1195c;
import h2.AbstractC1343A;
import java.util.Objects;
import l3.C1764u;

/* JADX INFO: renamed from: p2.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2087b {
    public static final C2087b c = new C2087b(I.B(C2086a.d));
    public static final d0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final K f20695e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SparseArray f20696a = new SparseArray();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f20697b;

    static {
        Object[] objArr = {2, 5, 6};
        AbstractC1095q.d(3, objArr);
        d = I.t(3, objArr);
        B5.u uVar = new B5.u(4);
        uVar.s(5, 6);
        uVar.s(17, 6);
        uVar.s(7, 6);
        uVar.s(30, 10);
        uVar.s(18, 6);
        uVar.s(6, 8);
        uVar.s(8, 8);
        uVar.s(14, 8);
        f20695e = uVar.g();
    }

    public C2087b(d0 d0Var) {
        for (int i7 = 0; i7 < d0Var.o; i7++) {
            C2086a c2086a = (C2086a) d0Var.get(i7);
            this.f20696a.put(c2086a.f20693a, c2086a);
        }
        int iMax = 0;
        for (int i10 = 0; i10 < this.f20696a.size(); i10++) {
            iMax = Math.max(iMax, ((C2086a) this.f20696a.valueAt(i10)).f20694b);
        }
        this.f20697b = iMax;
    }

    public static d0 a(int[] iArr, int i7) {
        C1077F c1077fU = I.u();
        if (iArr == null) {
            iArr = new int[0];
        }
        for (int i10 : iArr) {
            c1077fU.a(new C2086a(i10, i7));
        }
        return c1077fU.h();
    }

    /* JADX WARN: Removed duplicated region for block: B:88:0x026b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static p2.C2087b b(android.content.Context r16, android.content.Intent r17, e2.C1195c r18, l3.C1764u r19) {
        /*
            Method dump skipped, instruction units count: 709
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p2.C2087b.b(android.content.Context, android.content.Intent, e2.c, l3.u):p2.b");
    }

    public static C2087b c(Context context, C1195c c1195c, C1764u c1764u) {
        return b(context, context.registerReceiver(null, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG")), c1195c, c1764u);
    }

    /* JADX WARN: Removed duplicated region for block: B:73:0x00fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.util.Pair d(e2.C1195c r17, e2.C1208p r18) {
        /*
            Method dump skipped, instruction units count: 291
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p2.C2087b.d(e2.c, e2.p):android.util.Pair");
    }

    public final boolean e(int i7) {
        SparseArray sparseArray = this.f20696a;
        int i10 = AbstractC1343A.f15195a;
        return sparseArray.indexOfKey(i7) >= 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0019 A[EDGE_INSN: B:13:0x0019->B:27:0x004a BREAK  A[LOOP:0: B:21:0x0032->B:25:0x0047]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean equals(java.lang.Object r9) {
        /*
            r8 = this;
            r0 = 1
            if (r8 != r9) goto L4
            return r0
        L4:
            boolean r1 = r9 instanceof p2.C2087b
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            p2.b r9 = (p2.C2087b) r9
            android.util.SparseArray r1 = r8.f20696a
            android.util.SparseArray r3 = r9.f20696a
            int r4 = h2.AbstractC1343A.f15195a
            if (r1 != 0) goto L17
            if (r3 != 0) goto L19
            goto L49
        L17:
            if (r3 != 0) goto L1b
        L19:
            r1 = r2
            goto L4a
        L1b:
            int r4 = h2.AbstractC1343A.f15195a
            r5 = 31
            if (r4 < r5) goto L26
            boolean r1 = F0.AbstractC0207i.B(r1, r3)
            goto L4a
        L26:
            int r4 = r1.size()
            int r5 = r3.size()
            if (r4 == r5) goto L31
            goto L19
        L31:
            r5 = r2
        L32:
            if (r5 >= r4) goto L49
            int r6 = r1.keyAt(r5)
            java.lang.Object r7 = r1.valueAt(r5)
            java.lang.Object r6 = r3.get(r6)
            boolean r6 = java.util.Objects.equals(r7, r6)
            if (r6 != 0) goto L47
            goto L19
        L47:
            int r5 = r5 + r0
            goto L32
        L49:
            r1 = r0
        L4a:
            if (r1 == 0) goto L53
            int r8 = r8.f20697b
            int r9 = r9.f20697b
            if (r8 != r9) goto L53
            goto L54
        L53:
            r0 = r2
        L54:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p2.C2087b.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int iContentHashCode;
        SparseArray sparseArray = this.f20696a;
        if (AbstractC1343A.f15195a >= 31) {
            iContentHashCode = sparseArray.contentHashCode();
        } else {
            int iHashCode = 17;
            for (int i7 = 0; i7 < sparseArray.size(); i7++) {
                iHashCode = Objects.hashCode(sparseArray.valueAt(i7)) + ((sparseArray.keyAt(i7) + (iHashCode * 31)) * 31);
            }
            iContentHashCode = iHashCode;
        }
        return (iContentHashCode * 31) + this.f20697b;
    }

    public final String toString() {
        return "AudioCapabilities[maxChannelCount=" + this.f20697b + ", audioProfiles=" + this.f20696a + "]";
    }
}
