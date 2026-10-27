package o2;

import c5.C1033j;
import e2.c0;
import e2.e0;
import e2.f0;
import java.util.HashMap;
import java.util.Random;
import u2.C2492C;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final C1033j f20417h = new C1033j(3);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Random f20418i = new Random();
    public j d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f20422f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e0 f20419a = new e0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c0 f20420b = new c0();
    public final HashMap c = new HashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public f0 f20421e = f0.f14097a;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f20423g = -1;

    public final void a(f fVar) {
        long j3 = fVar.c;
        if (j3 != -1) {
            this.f20423g = j3;
        }
        this.f20422f = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0097 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final o2.f b(int r16, u2.C2492C r17) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            java.util.HashMap r3 = r0.c
            java.util.Collection r4 = r3.values()
            java.util.Iterator r4 = r4.iterator()
            r5 = 0
            r6 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
        L15:
            boolean r8 = r4.hasNext()
            if (r8 == 0) goto L9b
            java.lang.Object r8 = r4.next()
            o2.f r8 = (o2.f) r8
            long r9 = r8.c
            r11 = -1
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 != 0) goto L51
            int r9 = r8.f20413b
            if (r1 != r9) goto L51
            if (r2 == 0) goto L51
            o2.g r9 = r8.f20416g
            java.util.HashMap r10 = r9.c
            java.lang.String r13 = r9.f20422f
            java.lang.Object r10 = r10.get(r13)
            o2.f r10 = (o2.f) r10
            if (r10 == 0) goto L44
            long r13 = r10.c
            int r10 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r10 == 0) goto L44
            goto L49
        L44:
            long r9 = r9.f20423g
            r13 = 1
            long r13 = r13 + r9
        L49:
            long r9 = r2.d
            int r13 = (r9 > r13 ? 1 : (r9 == r13 ? 0 : -1))
            if (r13 < 0) goto L51
            r8.c = r9
        L51:
            u2.C r9 = r8.d
            if (r2 != 0) goto L5a
            int r10 = r8.f20413b
            if (r1 != r10) goto L15
            goto L7d
        L5a:
            long r13 = r2.d
            if (r9 != 0) goto L6b
            boolean r10 = r17.c()
            if (r10 != 0) goto L15
            long r11 = r8.c
            int r10 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r10 != 0) goto L15
            goto L7d
        L6b:
            long r10 = r9.d
            int r10 = (r13 > r10 ? 1 : (r13 == r10 ? 0 : -1))
            if (r10 != 0) goto L15
            int r10 = r2.f22664b
            int r11 = r9.f22664b
            if (r10 != r11) goto L15
            int r10 = r2.c
            int r11 = r9.c
            if (r10 != r11) goto L15
        L7d:
            long r10 = r8.c
            r12 = -1
            int r12 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r12 == 0) goto L97
            int r12 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r12 >= 0) goto L8a
            goto L97
        L8a:
            if (r12 != 0) goto L15
            int r10 = h2.AbstractC1343A.f15195a
            u2.C r10 = r5.d
            if (r10 == 0) goto L15
            if (r9 == 0) goto L15
            r5 = r8
            goto L15
        L97:
            r5 = r8
            r6 = r10
            goto L15
        L9b:
            if (r5 != 0) goto Lad
            c5.j r4 = o2.g.f20417h
            java.lang.Object r4 = r4.get()
            java.lang.String r4 = (java.lang.String) r4
            o2.f r5 = new o2.f
            r5.<init>(r15, r4, r1, r2)
            r3.put(r4, r5)
        Lad:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: o2.g.b(int, u2.C):o2.f");
    }

    public final synchronized String c(f0 f0Var, C2492C c2492c) {
        return b(f0Var.g(c2492c.f22663a, this.f20420b).c, c2492c).f20412a;
    }

    public final void d(C2024a c2024a) {
        C2492C c2492c;
        boolean zP = c2024a.f20397b.p();
        HashMap map = this.c;
        if (zP) {
            String str = this.f20422f;
            if (str != null) {
                f fVar = (f) map.get(str);
                fVar.getClass();
                a(fVar);
                return;
            }
            return;
        }
        f fVar2 = (f) map.get(this.f20422f);
        int i7 = c2024a.c;
        C2492C c2492c2 = c2024a.d;
        this.f20422f = b(i7, c2492c2).f20412a;
        e(c2024a);
        if (c2492c2 == null || !c2492c2.c()) {
            return;
        }
        long j3 = c2492c2.d;
        if (fVar2 != null && fVar2.c == j3 && (c2492c = fVar2.d) != null && c2492c.f22664b == c2492c2.f22664b && c2492c.c == c2492c2.c) {
            return;
        }
        b(i7, new C2492C(j3, c2492c2.f22663a));
        this.d.getClass();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002b A[Catch: all -> 0x0050, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0010, B:9:0x0014, B:11:0x0024, B:20:0x0036, B:22:0x0042, B:24:0x0048, B:14:0x002b, B:30:0x0053, B:32:0x005f, B:33:0x0063, B:35:0x0068, B:37:0x006e, B:39:0x0085, B:40:0x00b3, B:42:0x00b7, B:43:0x00be, B:45:0x00c8, B:47:0x00cc, B:49:0x00d9, B:52:0x00e0), top: B:57:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized void e(o2.C2024a r10) {
        /*
            Method dump skipped, instruction units count: 254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: o2.g.e(o2.a):void");
    }
}
