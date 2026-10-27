package l5;

import j5.y;
import q5.C2185a;

/* JADX INFO: renamed from: l5.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1779g extends y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile y f18640a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ boolean f18641b;
    public final /* synthetic */ boolean c;
    public final /* synthetic */ j5.m d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ C2185a f18642e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ C1780h f18643f;

    public C1779g(C1780h c1780h, boolean z6, boolean z10, j5.m mVar, C2185a c2185a) {
        this.f18643f = c1780h;
        this.f18641b = z6;
        this.c = z10;
        this.d = mVar;
        this.f18642e = c2185a;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x008c A[SYNTHETIC] */
    @Override // j5.y
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(r5.C2297a r10) throws java.io.IOException {
        /*
            r9 = this;
            boolean r0 = r9.f18641b
            if (r0 == 0) goto L9
            r10.W()
            r9 = 0
            return r9
        L9:
            j5.y r0 = r9.f18640a
            if (r0 == 0) goto Lf
            goto L94
        Lf:
            j5.m r0 = r9.d
            l5.h r1 = r9.f18643f
            q5.a r2 = r9.f18642e
            r0.getClass()
            java.lang.String r3 = "skipPast must not be null"
            java.util.Objects.requireNonNull(r1, r3)
            m5.i r3 = r0.d
            r3.getClass()
            m5.h r4 = m5.C1832i.f19224n
            if (r1 != r4) goto L27
            goto L6a
        L27:
            java.util.concurrent.ConcurrentHashMap r4 = r3.f19225m
            java.lang.Class r5 = r2.f21253a
            java.lang.Object r6 = r4.get(r5)
            j5.z r6 = (j5.z) r6
            if (r6 == 0) goto L36
            if (r6 != r1) goto L6b
            goto L6a
        L36:
            java.lang.Class<k5.a> r6 = k5.InterfaceC1687a.class
            java.lang.annotation.Annotation r6 = r5.getAnnotation(r6)
            k5.a r6 = (k5.InterfaceC1687a) r6
            if (r6 != 0) goto L41
            goto L6b
        L41:
            java.lang.Class r6 = r6.value()
            java.lang.Class<j5.z> r7 = j5.z.class
            boolean r7 = r7.isAssignableFrom(r6)
            if (r7 != 0) goto L4e
            goto L6b
        L4e:
            l5.f r7 = r3.l
            q5.a r8 = new q5.a
            r8.<init>(r6)
            l5.o r6 = r7.h(r8)
            java.lang.Object r6 = r6.H()
            j5.z r6 = (j5.z) r6
            java.lang.Object r4 = r4.putIfAbsent(r5, r6)
            j5.z r4 = (j5.z) r4
            if (r4 == 0) goto L68
            r6 = r4
        L68:
            if (r6 != r1) goto L6b
        L6a:
            r1 = r3
        L6b:
            java.util.List r3 = r0.f17027e
            java.util.Iterator r3 = r3.iterator()
            r4 = 0
        L72:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L8c
            java.lang.Object r5 = r3.next()
            j5.z r5 = (j5.z) r5
            if (r4 != 0) goto L84
            if (r5 != r1) goto L72
            r4 = 1
            goto L72
        L84:
            j5.y r5 = r5.a(r0, r2)
            if (r5 == 0) goto L72
            r0 = r5
            goto L92
        L8c:
            if (r4 != 0) goto L99
            j5.y r0 = r0.c(r2)
        L92:
            r9.f18640a = r0
        L94:
            java.lang.Object r9 = r0.a(r10)
            return r9
        L99:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r0 = "GSON cannot serialize or deserialize "
            r10.<init>(r0)
            r10.append(r2)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: l5.C1779g.a(r5.a):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x008b A[SYNTHETIC] */
    @Override // j5.y
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(r5.C2298b r10, java.lang.Object r11) throws java.io.IOException {
        /*
            r9 = this;
            boolean r0 = r9.c
            if (r0 == 0) goto L8
            r10.v()
            return
        L8:
            j5.y r0 = r9.f18640a
            if (r0 == 0) goto Le
            goto L93
        Le:
            j5.m r0 = r9.d
            l5.h r1 = r9.f18643f
            q5.a r2 = r9.f18642e
            r0.getClass()
            java.lang.String r3 = "skipPast must not be null"
            java.util.Objects.requireNonNull(r1, r3)
            m5.i r3 = r0.d
            r3.getClass()
            m5.h r4 = m5.C1832i.f19224n
            if (r1 != r4) goto L26
            goto L69
        L26:
            java.util.concurrent.ConcurrentHashMap r4 = r3.f19225m
            java.lang.Class r5 = r2.f21253a
            java.lang.Object r6 = r4.get(r5)
            j5.z r6 = (j5.z) r6
            if (r6 == 0) goto L35
            if (r6 != r1) goto L6a
            goto L69
        L35:
            java.lang.Class<k5.a> r6 = k5.InterfaceC1687a.class
            java.lang.annotation.Annotation r6 = r5.getAnnotation(r6)
            k5.a r6 = (k5.InterfaceC1687a) r6
            if (r6 != 0) goto L40
            goto L6a
        L40:
            java.lang.Class r6 = r6.value()
            java.lang.Class<j5.z> r7 = j5.z.class
            boolean r7 = r7.isAssignableFrom(r6)
            if (r7 != 0) goto L4d
            goto L6a
        L4d:
            l5.f r7 = r3.l
            q5.a r8 = new q5.a
            r8.<init>(r6)
            l5.o r6 = r7.h(r8)
            java.lang.Object r6 = r6.H()
            j5.z r6 = (j5.z) r6
            java.lang.Object r4 = r4.putIfAbsent(r5, r6)
            j5.z r4 = (j5.z) r4
            if (r4 == 0) goto L67
            r6 = r4
        L67:
            if (r6 != r1) goto L6a
        L69:
            r1 = r3
        L6a:
            java.util.List r3 = r0.f17027e
            java.util.Iterator r3 = r3.iterator()
            r4 = 0
        L71:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L8b
            java.lang.Object r5 = r3.next()
            j5.z r5 = (j5.z) r5
            if (r4 != 0) goto L83
            if (r5 != r1) goto L71
            r4 = 1
            goto L71
        L83:
            j5.y r5 = r5.a(r0, r2)
            if (r5 == 0) goto L71
            r0 = r5
            goto L91
        L8b:
            if (r4 != 0) goto L97
            j5.y r0 = r0.c(r2)
        L91:
            r9.f18640a = r0
        L93:
            r0.b(r10, r11)
            return
        L97:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "GSON cannot serialize or deserialize "
            r10.<init>(r11)
            r10.append(r2)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: l5.C1779g.b(r5.b, java.lang.Object):void");
    }
}
