package e0;

import E0.C0155e;
import N1.C0420a;
import T.C0735d;
import T.C0759p;
import java.util.HashMap;
import u.C2453A;
import u.C2481u;
import u.C2484x;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: e0.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1182v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC2782k f13735a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f13736b;
    public C2481u c;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f13742j;
    public int d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0420a f13737e = new C0420a(12);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C2484x f13738f = new C2484x();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C2453A f13739g = new C2453A();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final V.d f13740h = new V.d(new T.D[16]);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C0759p f13741i = new C0759p(this, 1);

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final C0420a f13743k = new C0420a(12);
    public final HashMap l = new HashMap();

    public C1182v(InterfaceC2782k interfaceC2782k) {
        this.f13735a = interfaceC2782k;
    }

    public final void a(Object obj, S0.p pVar, InterfaceC2772a interfaceC2772a) {
        long[] jArr;
        long[] jArr2;
        int i7;
        Object obj2 = this.f13736b;
        C2481u c2481u = this.c;
        int i10 = this.d;
        this.f13736b = obj;
        this.c = (C2481u) this.f13738f.e(obj);
        if (this.d == -1) {
            this.d = AbstractC1174n.k().d();
        }
        C0759p c0759p = this.f13741i;
        V.d dVarE = C0735d.E();
        try {
            dVarE.b(c0759p);
            AbstractC1179s.e(interfaceC2772a, pVar);
            dVarE.o(dVarE.f9279n - 1);
            Object obj3 = this.f13736b;
            A7.m.c(obj3);
            int i11 = this.d;
            C2481u c2481u2 = this.c;
            if (c2481u2 != null) {
                long[] jArr3 = c2481u2.f22633a;
                int length = jArr3.length - 2;
                if (length >= 0) {
                    int i12 = 0;
                    while (true) {
                        long j3 = jArr3[i12];
                        if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i13 = 8;
                            int i14 = 8 - ((~(i12 - length)) >>> 31);
                            int i15 = 0;
                            while (i15 < i14) {
                                if ((j3 & 255) < 128) {
                                    int i16 = (i12 << 3) + i15;
                                    Object obj4 = c2481u2.f22634b[i16];
                                    jArr2 = jArr3;
                                    boolean z6 = c2481u2.c[i16] != i11;
                                    if (z6) {
                                        d(obj3, obj4);
                                    }
                                    if (z6) {
                                        c2481u2.e(i16);
                                    }
                                    i7 = 8;
                                } else {
                                    jArr2 = jArr3;
                                    i7 = i13;
                                }
                                j3 >>= i7;
                                i15++;
                                i13 = i7;
                                jArr3 = jArr2;
                            }
                            jArr = jArr3;
                            if (i14 != i13) {
                                break;
                            }
                        } else {
                            jArr = jArr3;
                        }
                        if (i12 == length) {
                            break;
                        }
                        i12++;
                        jArr3 = jArr;
                    }
                }
            }
            this.f13736b = obj2;
            this.c = c2481u;
            this.d = i10;
        } catch (Throwable th) {
            dVarE.o(dVarE.f9279n - 1);
            throw th;
        }
    }

    /*  JADX ERROR: Type inference failed with stack overflow
        jadx.core.utils.exceptions.JadxOverflowException
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    public final boolean b(java.util.Set r46) {
        /*
            Method dump skipped, instruction units count: 1851
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: e0.C1182v.b(java.util.Set):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(java.lang.Object r21, int r22, java.lang.Object r23, u.C2481u r24) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r2 = r22
            r3 = r24
            int r4 = r0.f13742j
            if (r4 <= 0) goto Ld
            return
        Ld:
            int r4 = r3.b(r1)
            if (r4 >= 0) goto L16
            int r4 = ~r4
            r6 = -1
            goto L1a
        L16:
            int[] r6 = r3.c
            r6 = r6[r4]
        L1a:
            java.lang.Object[] r7 = r3.f22634b
            r7[r4] = r1
            int[] r3 = r3.c
            r3[r4] = r2
            boolean r3 = r1 instanceof T.D
            r4 = 2
            if (r3 == 0) goto L8f
            if (r6 == r2) goto L8f
            r2 = r1
            T.D r2 = (T.D) r2
            T.C r2 = r2.j()
            java.util.HashMap r3 = r0.l
            java.lang.Object r7 = r2.f8179f
            r3.put(r1, r7)
            u.u r2 = r2.f8178e
            N1.a r3 = r0.f13743k
            r3.o(r1)
            java.lang.Object[] r7 = r2.f22634b
            long[] r2 = r2.f22633a
            int r8 = r2.length
            int r8 = r8 - r4
            if (r8 < 0) goto L8f
            r10 = 0
        L47:
            r11 = r2[r10]
            long r13 = ~r11
            r15 = 7
            long r13 = r13 << r15
            long r13 = r13 & r11
            r15 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r13 = r13 & r15
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 == 0) goto L8a
            int r13 = r10 - r8
            int r13 = ~r13
            int r13 = r13 >>> 31
            r14 = 8
            int r13 = 8 - r13
            r15 = 0
        L61:
            if (r15 >= r13) goto L88
            r16 = 255(0xff, double:1.26E-321)
            long r16 = r11 & r16
            r18 = 128(0x80, double:6.3E-322)
            int r16 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r16 >= 0) goto L84
            int r16 = r10 << 3
            int r16 = r16 + r15
            r16 = r7[r16]
            r9 = r16
            e0.A r9 = (e0.InterfaceC1154A) r9
            boolean r5 = r9 instanceof e0.AbstractC1155B
            if (r5 == 0) goto L81
            r5 = r9
            e0.B r5 = (e0.AbstractC1155B) r5
            r5.h(r4)
        L81:
            r3.e(r9, r1)
        L84:
            long r11 = r11 >> r14
            int r15 = r15 + 1
            goto L61
        L88:
            if (r13 != r14) goto L8f
        L8a:
            if (r10 == r8) goto L8f
            int r10 = r10 + 1
            goto L47
        L8f:
            r2 = -1
            if (r6 != r2) goto La3
            boolean r2 = r1 instanceof e0.AbstractC1155B
            if (r2 == 0) goto L9c
            r2 = r1
            e0.B r2 = (e0.AbstractC1155B) r2
            r2.h(r4)
        L9c:
            N1.a r0 = r0.f13737e
            r2 = r23
            r0.e(r1, r2)
        La3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: e0.C1182v.c(java.lang.Object, int, java.lang.Object, u.u):void");
    }

    public final void d(Object obj, Object obj2) {
        C0420a c0420a = this.f13737e;
        c0420a.n(obj2, obj);
        if (!(obj2 instanceof T.D) || ((C2484x) c0420a.l).b(obj2)) {
            return;
        }
        this.f13743k.o(obj2);
        this.l.remove(obj2);
    }

    public final void e() {
        C0155e c0155e;
        long[] jArr;
        int i7;
        C0155e c0155e2;
        long[] jArr2;
        int i10;
        int i11;
        int i12;
        char c;
        long j3;
        int i13;
        int i14;
        C0155e c0155e3 = C0155e.f1656s;
        C2484x c2484x = this.f13738f;
        long[] jArr3 = c2484x.f22643a;
        int length = jArr3.length - 2;
        if (length < 0) {
            return;
        }
        int i15 = 0;
        while (true) {
            long j10 = jArr3[i15];
            char c10 = 7;
            long j11 = -9187201950435737472L;
            if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i16 = 8;
                int i17 = 8 - ((~(i15 - length)) >>> 31);
                int i18 = 0;
                while (i18 < i17) {
                    if ((j10 & 255) < 128) {
                        int i19 = (i15 << 3) + i18;
                        Object obj = c2484x.f22644b[i19];
                        C2481u c2481u = (C2481u) c2484x.c[i19];
                        Boolean bool = (Boolean) c0155e3.b(obj);
                        if (bool.booleanValue()) {
                            Object[] objArr = c2481u.f22634b;
                            int[] iArr = c2481u.c;
                            long[] jArr4 = c2481u.f22633a;
                            int length2 = jArr4.length - 2;
                            c0155e2 = c0155e3;
                            jArr2 = jArr3;
                            i10 = length;
                            if (length2 >= 0) {
                                int i20 = 0;
                                while (true) {
                                    long j12 = jArr4[i20];
                                    i12 = i17;
                                    long[] jArr5 = jArr4;
                                    c = 7;
                                    j3 = -9187201950435737472L;
                                    if ((((~j12) << 7) & j12 & (-9187201950435737472L)) != -9187201950435737472L) {
                                        int i21 = 8 - ((~(i20 - length2)) >>> 31);
                                        int i22 = 0;
                                        while (i22 < i21) {
                                            if ((j12 & 255) < 128) {
                                                int i23 = (i20 << 3) + i22;
                                                i14 = i15;
                                                Object obj2 = objArr[i23];
                                                int i24 = iArr[i23];
                                                d(obj, obj2);
                                            } else {
                                                i14 = i15;
                                            }
                                            j12 >>= 8;
                                            i22++;
                                            i15 = i14;
                                        }
                                        i11 = i15;
                                        if (i21 != 8) {
                                            break;
                                        }
                                    } else {
                                        i11 = i15;
                                    }
                                    if (i20 == length2) {
                                        break;
                                    }
                                    i20++;
                                    i17 = i12;
                                    jArr4 = jArr5;
                                    i15 = i11;
                                }
                            } else {
                                i11 = i15;
                                i12 = i17;
                                j3 = -9187201950435737472L;
                                c = 7;
                            }
                        } else {
                            c0155e2 = c0155e3;
                            jArr2 = jArr3;
                            i10 = length;
                            i11 = i15;
                            i12 = i17;
                            c = c10;
                            j3 = j11;
                        }
                        if (bool.booleanValue()) {
                            c2484x.h(i19);
                        }
                        i13 = 8;
                    } else {
                        c0155e2 = c0155e3;
                        jArr2 = jArr3;
                        i10 = length;
                        i11 = i15;
                        i12 = i17;
                        c = c10;
                        j3 = j11;
                        i13 = i16;
                    }
                    j10 >>= i13;
                    i18++;
                    i16 = i13;
                    j11 = j3;
                    c10 = c;
                    c0155e3 = c0155e2;
                    jArr3 = jArr2;
                    length = i10;
                    i17 = i12;
                    i15 = i11;
                }
                c0155e = c0155e3;
                jArr = jArr3;
                int i25 = length;
                int i26 = i15;
                if (i17 != i16) {
                    return;
                }
                length = i25;
                i7 = i26;
            } else {
                c0155e = c0155e3;
                jArr = jArr3;
                i7 = i15;
            }
            if (i7 == length) {
                return;
            }
            i15 = i7 + 1;
            c0155e3 = c0155e;
            jArr3 = jArr;
        }
    }
}
