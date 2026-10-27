package e0;

import A.G0;
import T.C0735d;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import l3.P;
import n7.AbstractC1998m;
import u.C2484x;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: e0.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1184x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final A7.o f13746a;
    public boolean c;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public G2.a f13750g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public C1182v f13751h;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AtomicReference f13747b = new AtomicReference(null);
    public final G0 d = new G0(this, 11);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final S0.p f13748e = new S0.p(this, 15);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final V.d f13749f = new V.d(new C1182v[16]);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f13752i = -1;

    /* JADX WARN: Multi-variable type inference failed */
    public C1184x(InterfaceC2782k interfaceC2782k) {
        this.f13746a = (A7.o) interfaceC2782k;
    }

    public static final boolean a(C1184x c1184x) {
        boolean z6;
        Set set;
        synchronized (c1184x.f13749f) {
            z6 = c1184x.c;
        }
        if (z6) {
            return false;
        }
        boolean z10 = false;
        while (true) {
            AtomicReference atomicReference = c1184x.f13747b;
            Object obj = atomicReference.get();
            Set set2 = null;
            objSubList = null;
            Object objSubList = null;
            if (obj != null) {
                if (obj instanceof Set) {
                    set = (Set) obj;
                } else {
                    if (!(obj instanceof List)) {
                        C0735d.z("Unexpected notification");
                        throw null;
                    }
                    List list = (List) obj;
                    set = (Set) list.get(0);
                    if (list.size() == 2) {
                        objSubList = list.get(1);
                    } else if (list.size() > 2) {
                        objSubList = list.subList(1, list.size());
                    }
                }
                Object obj2 = objSubList;
                while (!atomicReference.compareAndSet(obj, obj2)) {
                    if (atomicReference.get() != obj) {
                        break;
                    }
                }
                set2 = set;
            }
            if (set2 == null) {
                return z10;
            }
            synchronized (c1184x.f13749f) {
                V.d dVar = c1184x.f13749f;
                int i7 = dVar.f9279n;
                if (i7 > 0) {
                    Object[] objArr = dVar.l;
                    int i10 = 0;
                    do {
                        z10 = ((C1182v) objArr[i10]).b(set2) || z10;
                        i10++;
                    } while (i10 < i7);
                }
            }
        }
    }

    public final void b() {
        synchronized (this.f13749f) {
            V.d dVar = this.f13749f;
            int i7 = dVar.f9279n;
            if (i7 > 0) {
                Object[] objArr = dVar.l;
                int i10 = 0;
                do {
                    C1182v c1182v = (C1182v) objArr[i10];
                    ((C2484x) c1182v.f13737e.l).a();
                    c1182v.f13738f.a();
                    ((C2484x) c1182v.f13743k.l).a();
                    c1182v.l.clear();
                    i10++;
                } while (i10 < i7);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(java.lang.Object r22) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            V.d r2 = r0.f13749f
            monitor-enter(r2)
            V.d r0 = r0.f13749f     // Catch: java.lang.Throwable -> L9a
            int r3 = r0.f9279n     // Catch: java.lang.Throwable -> L9a
            r5 = 0
            r6 = 0
        Ld:
            if (r5 >= r3) goto La0
            java.lang.Object[] r7 = r0.l     // Catch: java.lang.Throwable -> L9a
            r7 = r7[r5]     // Catch: java.lang.Throwable -> L9a
            e0.v r7 = (e0.C1182v) r7     // Catch: java.lang.Throwable -> L9a
            u.x r8 = r7.f13738f     // Catch: java.lang.Throwable -> L9a
            java.lang.Object r8 = r8.g(r1)     // Catch: java.lang.Throwable -> L9a
            u.u r8 = (u.C2481u) r8     // Catch: java.lang.Throwable -> L9a
            if (r8 != 0) goto L21
        L1f:
            r15 = r5
            goto L81
        L21:
            java.lang.Object[] r9 = r8.f22634b     // Catch: java.lang.Throwable -> L9a
            int[] r10 = r8.c     // Catch: java.lang.Throwable -> L9a
            long[] r8 = r8.f22633a     // Catch: java.lang.Throwable -> L9a
            int r11 = r8.length     // Catch: java.lang.Throwable -> L9a
            int r11 = r11 + (-2)
            if (r11 < 0) goto L1f
            r12 = 0
        L2d:
            r13 = r8[r12]     // Catch: java.lang.Throwable -> L9a
            r15 = r5
            long r4 = ~r13     // Catch: java.lang.Throwable -> L9a
            r16 = 7
            long r4 = r4 << r16
            long r4 = r4 & r13
            r16 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r4 = r4 & r16
            int r4 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r4 == 0) goto L77
            int r4 = r12 - r11
            int r4 = ~r4     // Catch: java.lang.Throwable -> L9a
            int r4 = r4 >>> 31
            r5 = 8
            int r4 = 8 - r4
            r5 = 0
        L4b:
            if (r5 >= r4) goto L70
            r17 = 255(0xff, double:1.26E-321)
            long r17 = r13 & r17
            r19 = 128(0x80, double:6.3E-322)
            int r17 = (r17 > r19 ? 1 : (r17 == r19 ? 0 : -1))
            if (r17 >= 0) goto L67
            int r17 = r12 << 3
            int r17 = r17 + r5
            r18 = r8
            r8 = r9[r17]     // Catch: java.lang.Throwable -> L9a
            r17 = r10[r17]     // Catch: java.lang.Throwable -> L9a
            r7.d(r1, r8)     // Catch: java.lang.Throwable -> L9a
        L64:
            r8 = 8
            goto L6a
        L67:
            r18 = r8
            goto L64
        L6a:
            long r13 = r13 >> r8
            int r5 = r5 + 1
            r8 = r18
            goto L4b
        L70:
            r18 = r8
            r8 = 8
            if (r4 != r8) goto L81
            goto L79
        L77:
            r18 = r8
        L79:
            if (r12 == r11) goto L81
            int r12 = r12 + 1
            r5 = r15
            r8 = r18
            goto L2d
        L81:
            u.x r4 = r7.f13738f     // Catch: java.lang.Throwable -> L9a
            int r4 = r4.f22645e     // Catch: java.lang.Throwable -> L9a
            if (r4 == 0) goto L89
            r4 = 1
            goto L8a
        L89:
            r4 = 0
        L8a:
            if (r4 != 0) goto L8f
            int r6 = r6 + 1
            goto L9c
        L8f:
            if (r6 <= 0) goto L9c
            java.lang.Object[] r4 = r0.l     // Catch: java.lang.Throwable -> L9a
            int r5 = r15 - r6
            r7 = r4[r15]     // Catch: java.lang.Throwable -> L9a
            r4[r5] = r7     // Catch: java.lang.Throwable -> L9a
            goto L9c
        L9a:
            r0 = move-exception
            goto Lac
        L9c:
            int r5 = r15 + 1
            goto Ld
        La0:
            java.lang.Object[] r1 = r0.l     // Catch: java.lang.Throwable -> L9a
            int r4 = r3 - r6
            r5 = 0
            java.util.Arrays.fill(r1, r4, r3, r5)     // Catch: java.lang.Throwable -> L9a
            r0.f9279n = r4     // Catch: java.lang.Throwable -> L9a
            monitor-exit(r2)
            return
        Lac:
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: e0.C1184x.c(java.lang.Object):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0020 A[Catch: all -> 0x0092, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x000a, B:7:0x000d, B:13:0x001c, B:15:0x0020, B:10:0x0017), top: B:33:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d(java.lang.Object r9, z7.InterfaceC2782k r10, z7.InterfaceC2772a r11) {
        /*
            r8 = this;
            V.d r0 = r8.f13749f
            monitor-enter(r0)
            V.d r1 = r8.f13749f     // Catch: java.lang.Throwable -> L92
            int r2 = r1.f9279n     // Catch: java.lang.Throwable -> L92
            r3 = 0
            if (r2 <= 0) goto L1b
            java.lang.Object[] r4 = r1.l     // Catch: java.lang.Throwable -> L92
            r5 = 0
        Ld:
            r6 = r4[r5]     // Catch: java.lang.Throwable -> L92
            r7 = r6
            e0.v r7 = (e0.C1182v) r7     // Catch: java.lang.Throwable -> L92
            z7.k r7 = r7.f13735a     // Catch: java.lang.Throwable -> L92
            if (r7 != r10) goto L17
            goto L1c
        L17:
            int r5 = r5 + 1
            if (r5 < r2) goto Ld
        L1b:
            r6 = r3
        L1c:
            e0.v r6 = (e0.C1182v) r6     // Catch: java.lang.Throwable -> L92
            if (r6 != 0) goto L33
            e0.v r6 = new e0.v     // Catch: java.lang.Throwable -> L92
            java.lang.String r2 = "null cannot be cast to non-null type kotlin.Function1<kotlin.Any, kotlin.Unit>"
            A7.m.d(r10, r2)     // Catch: java.lang.Throwable -> L92
            r2 = 1
            A7.E.e(r2, r10)     // Catch: java.lang.Throwable -> L92
            z7.k r10 = (z7.InterfaceC2782k) r10     // Catch: java.lang.Throwable -> L92
            r6.<init>(r10)     // Catch: java.lang.Throwable -> L92
            r1.b(r6)     // Catch: java.lang.Throwable -> L92
        L33:
            monitor-exit(r0)
            e0.v r10 = r8.f13751h
            long r0 = r8.f13752i
            r4 = -1
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 == 0) goto L7a
            long r4 = T.C0735d.C()
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 != 0) goto L47
            goto L7a
        L47:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "Detected multithreaded access to SnapshotStateObserver: previousThreadId="
            r8.<init>(r9)
            r8.append(r0)
            java.lang.String r9 = "), currentThread={id="
            r8.append(r9)
            long r9 = T.C0735d.C()
            r8.append(r9)
            java.lang.String r9 = ", name="
            r8.append(r9)
            java.lang.Thread r9 = java.lang.Thread.currentThread()
            java.lang.String r9 = r9.getName()
            r8.append(r9)
            java.lang.String r9 = "}. Note that observation on multiple threads in layout/draw is not supported. Make sure your measure/layout/draw for each Owner (AndroidComposeView) is executed on the same thread."
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            T.C0735d.X(r8)
            throw r3
        L7a:
            r8.f13751h = r6     // Catch: java.lang.Throwable -> L8c
            long r2 = T.C0735d.C()     // Catch: java.lang.Throwable -> L8c
            r8.f13752i = r2     // Catch: java.lang.Throwable -> L8c
            S0.p r2 = r8.f13748e     // Catch: java.lang.Throwable -> L8c
            r6.a(r9, r2, r11)     // Catch: java.lang.Throwable -> L8c
            r8.f13751h = r10
            r8.f13752i = r0
            return
        L8c:
            r9 = move-exception
            r8.f13751h = r10
            r8.f13752i = r0
            throw r9
        L92:
            r8 = move-exception
            monitor-exit(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: e0.C1184x.d(java.lang.Object, z7.k, z7.a):void");
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, java.util.Collection] */
    public final void e() {
        G0 g02 = this.d;
        P p10 = AbstractC1174n.f13720a;
        AbstractC1174n.f(C1172l.o);
        synchronized (AbstractC1174n.f13721b) {
            AbstractC1174n.f13724g = AbstractC1998m.o0(AbstractC1174n.f13724g, g02);
        }
        this.f13750g = new G2.a(g02);
    }
}
