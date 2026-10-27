package x;

import java.util.Arrays;
import n7.AbstractC1996k;
import u.C2475o;
import u.C2476p;

/* JADX INFO: loaded from: classes.dex */
public final class O implements InterfaceC2659z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final N f23442a;

    public O(N n10) {
        this.f23442a = n10;
    }

    @Override // x.InterfaceC2615D
    public final /* bridge */ /* synthetic */ H0 e() {
        return a(E0.f23396a);
    }

    @Override // x.InterfaceC2659z, x.InterfaceC2647m
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public final K0 a(D0 d02) {
        int[] iArr;
        Object[] objArr;
        int[] iArr2;
        Object[] objArr2;
        int i7;
        N n10 = this.f23442a;
        C2476p c2476p = n10.f23441b;
        C2475o c2475o = new C2475o(c2476p.f22617e + 2);
        C2476p c2476p2 = new C2476p(c2476p.f22617e);
        int[] iArr3 = c2476p.f22616b;
        Object[] objArr3 = c2476p.c;
        long[] jArr = c2476p.f22615a;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i10 = 0;
            while (true) {
                long j3 = jArr[i10];
                if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i11 = 8;
                    int i12 = 8 - ((~(i10 - length)) >>> 31);
                    int i13 = 0;
                    while (i13 < i12) {
                        if ((255 & j3) < 128) {
                            int i14 = (i10 << 3) + i13;
                            int i15 = iArr3[i14];
                            M m10 = (M) objArr3[i14];
                            c2475o.a(i15);
                            iArr2 = iArr3;
                            objArr2 = objArr3;
                            c2476p2.i(i15, new J0((AbstractC2652s) d02.f23394a.b(m10.f23438a), m10.f23439b, m10.c));
                            i7 = 8;
                        } else {
                            iArr2 = iArr3;
                            objArr2 = objArr3;
                            i7 = i11;
                        }
                        j3 >>= i7;
                        i13++;
                        i11 = i7;
                        iArr3 = iArr2;
                        objArr3 = objArr2;
                    }
                    iArr = iArr3;
                    objArr = objArr3;
                    if (i12 != i11) {
                        break;
                    }
                } else {
                    iArr = iArr3;
                    objArr = objArr3;
                }
                if (i10 == length) {
                    break;
                }
                i10++;
                iArr3 = iArr;
                objArr3 = objArr;
            }
        }
        if (!c2476p.b(0)) {
            int i16 = c2475o.f22614b;
            if (i16 < 0) {
                throw new IndexOutOfBoundsException("Index 0 must be in 0.." + c2475o.f22614b);
            }
            c2475o.b(i16 + 1);
            int[] iArr4 = c2475o.f22613a;
            int i17 = c2475o.f22614b;
            if (i17 != 0) {
                AbstractC1996k.C(1, 0, i17, iArr4, iArr4);
            }
            iArr4[0] = 0;
            c2475o.f22614b++;
        }
        if (!c2476p.b(n10.f23440a)) {
            c2475o.a(n10.f23440a);
        }
        int i18 = c2475o.f22614b;
        if (i18 != 0) {
            int[] iArr5 = c2475o.f22613a;
            A7.m.f(iArr5, "<this>");
            Arrays.sort(iArr5, 0, i18);
        }
        return new K0(c2475o, c2476p2, n10.f23440a, AbstractC2614C.c);
    }
}
