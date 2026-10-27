package e0;

import java.util.ArrayList;
import java.util.Iterator;
import n7.AbstractC1996k;
import n7.AbstractC1998m;
import n7.AbstractC2000o;

/* JADX INFO: renamed from: e0.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1171k implements Iterable, B7.a {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C1171k f13713p = new C1171k(0, 0, 0, null);
    public final long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f13714m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f13715n;
    public final int[] o;

    public C1171k(long j3, long j10, int i7, int[] iArr) {
        this.l = j3;
        this.f13714m = j10;
        this.f13715n = i7;
        this.o = iArr;
    }

    public final C1171k d(C1171k c1171k) {
        int[] iArr;
        C1171k c1171k2 = f13713p;
        if (c1171k == c1171k2) {
            return this;
        }
        if (this == c1171k2) {
            return c1171k2;
        }
        int i7 = c1171k.f13715n;
        int[] iArr2 = c1171k.o;
        long j3 = c1171k.f13714m;
        long j10 = c1171k.l;
        int i10 = this.f13715n;
        if (i7 == i10 && iArr2 == (iArr = this.o)) {
            return new C1171k(this.l & (~j10), this.f13714m & (~j3), i10, iArr);
        }
        if (iArr2 != null) {
            for (int i11 : iArr2) {
                this = this.l(i11);
            }
        }
        int i12 = c1171k.f13715n;
        if (j3 != 0) {
            for (int i13 = 0; i13 < 64; i13++) {
                if (((1 << i13) & j3) != 0) {
                    this = this.l(i13 + i12);
                }
            }
        }
        if (j10 != 0) {
            for (int i14 = 0; i14 < 64; i14++) {
                if (((1 << i14) & j10) != 0) {
                    this = this.l(i14 + 64 + i12);
                }
            }
        }
        return this;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return P8.n.G(new C1170j(this, null));
    }

    public final C1171k l(int i7) {
        int[] iArr;
        int iB;
        int i10 = this.f13715n;
        int i11 = i7 - i10;
        if (i11 >= 0 && i11 < 64) {
            long j3 = 1 << i11;
            long j10 = this.f13714m;
            if ((j10 & j3) != 0) {
                return new C1171k(this.l, j10 & (~j3), i10, this.o);
            }
        } else if (i11 >= 64 && i11 < 128) {
            long j11 = 1 << (i11 - 64);
            long j12 = this.l;
            if ((j12 & j11) != 0) {
                return new C1171k((~j11) & j12, this.f13714m, i10, this.o);
            }
        } else if (i11 < 0 && (iArr = this.o) != null && (iB = AbstractC1179s.b(iArr, i7)) >= 0) {
            int length = iArr.length;
            int i12 = length - 1;
            if (i12 == 0) {
                return new C1171k(this.l, this.f13714m, this.f13715n, null);
            }
            int[] iArr2 = new int[i12];
            if (iB > 0) {
                AbstractC1996k.C(0, 0, iB, iArr, iArr2);
            }
            if (iB < i12) {
                AbstractC1996k.C(iB, iB + 1, length, iArr, iArr2);
            }
            return new C1171k(this.l, this.f13714m, this.f13715n, iArr2);
        }
        return this;
    }

    public final boolean q(int i7) {
        int[] iArr;
        int i10 = i7 - this.f13715n;
        if (i10 >= 0 && i10 < 64) {
            return (this.f13714m & (1 << i10)) != 0;
        }
        if (i10 >= 64 && i10 < 128) {
            return (this.l & (1 << (i10 - 64))) != 0;
        }
        if (i10 <= 0 && (iArr = this.o) != null) {
            return AbstractC1179s.b(iArr, i7) >= 0;
        }
        return false;
    }

    public final C1171k r(C1171k c1171k) {
        int[] iArr;
        C1171k c1171kS = this;
        C1171k c1171kS2 = c1171k;
        C1171k c1171k2 = f13713p;
        if (c1171kS2 == c1171k2) {
            return c1171kS;
        }
        if (c1171kS == c1171k2) {
            return c1171kS2;
        }
        int i7 = c1171kS2.f13715n;
        long j3 = c1171kS.f13714m;
        long j10 = c1171kS.l;
        int[] iArr2 = c1171kS2.o;
        long j11 = c1171kS2.f13714m;
        long j12 = c1171kS2.l;
        int i10 = c1171kS.f13715n;
        if (i7 == i10 && iArr2 == (iArr = c1171kS.o)) {
            return new C1171k(j10 | j12, j3 | j11, i10, iArr);
        }
        int[] iArr3 = c1171kS.o;
        if (iArr3 == null) {
            if (iArr3 != null) {
                for (int i11 : iArr3) {
                    c1171kS2 = c1171kS2.s(i11);
                }
            }
            int i12 = c1171kS.f13715n;
            if (j3 != 0) {
                for (int i13 = 0; i13 < 64; i13++) {
                    if (((1 << i13) & j3) != 0) {
                        c1171kS2 = c1171kS2.s(i13 + i12);
                    }
                }
            }
            if (j10 != 0) {
                for (int i14 = 0; i14 < 64; i14++) {
                    if (((1 << i14) & j10) != 0) {
                        c1171kS2 = c1171kS2.s(i14 + 64 + i12);
                    }
                }
            }
            return c1171kS2;
        }
        if (iArr2 != null) {
            for (int i15 : iArr2) {
                c1171kS = c1171kS.s(i15);
            }
        }
        int i16 = c1171kS2.f13715n;
        if (j11 != 0) {
            for (int i17 = 0; i17 < 64; i17++) {
                if (((1 << i17) & j11) != 0) {
                    c1171kS = c1171kS.s(i17 + i16);
                }
            }
        }
        if (j12 == 0) {
            return c1171kS;
        }
        for (int i18 = 0; i18 < 64; i18++) {
            if (((1 << i18) & j12) != 0) {
                c1171kS = c1171kS.s(i18 + 64 + i16);
            }
        }
        return c1171kS;
    }

    public final C1171k s(int i7) {
        int i10;
        long j3;
        int i11 = this.f13715n;
        int i12 = i7 - i11;
        long j10 = this.f13714m;
        if (i12 < 0 || i12 >= 64) {
            long j11 = this.l;
            if (i12 < 64 || i12 >= 128) {
                int[] iArrY0 = this.o;
                if (i12 < 128) {
                    if (iArrY0 == null) {
                        return new C1171k(j11, j10, i11, new int[]{i7});
                    }
                    int iB = AbstractC1179s.b(iArrY0, i7);
                    if (iB < 0) {
                        int i13 = -(iB + 1);
                        int length = iArrY0.length;
                        int[] iArr = new int[length + 1];
                        AbstractC1996k.C(0, 0, i13, iArrY0, iArr);
                        AbstractC1996k.C(i13 + 1, i13, length, iArrY0, iArr);
                        iArr[i13] = i7;
                        return new C1171k(this.l, this.f13714m, this.f13715n, iArr);
                    }
                } else if (!q(i7)) {
                    int i14 = ((i7 + 1) / 64) * 64;
                    int i15 = this.f13715n;
                    ArrayList arrayList = null;
                    long j12 = j11;
                    while (true) {
                        if (i15 >= i14) {
                            i10 = i15;
                            j3 = j10;
                            break;
                        }
                        if (j10 != 0) {
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                                if (iArrY0 != null) {
                                    for (int i16 : iArrY0) {
                                        arrayList.add(Integer.valueOf(i16));
                                    }
                                }
                            }
                            for (int i17 = 0; i17 < 64; i17++) {
                                if (((1 << i17) & j10) != 0) {
                                    arrayList.add(Integer.valueOf(i17 + i15));
                                }
                            }
                        }
                        if (j12 == 0) {
                            i10 = i14;
                            j3 = 0;
                            break;
                        }
                        i15 += 64;
                        j10 = j12;
                        j12 = 0;
                    }
                    if (arrayList != null) {
                        iArrY0 = AbstractC1998m.y0(arrayList);
                    }
                    return new C1171k(j12, j3, i10, iArrY0).s(i7);
                }
            } else {
                long j13 = 1 << (i12 - 64);
                if ((j11 & j13) == 0) {
                    return new C1171k(j13 | j11, j10, i11, this.o);
                }
            }
        } else {
            long j14 = 1 << i12;
            if ((j10 & j14) == 0) {
                return new C1171k(this.l, j10 | j14, i11, this.o);
            }
        }
        return this;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [");
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(this, 10));
        Iterator it = iterator();
        while (it.hasNext()) {
            arrayList.add(String.valueOf(((Number) it.next()).intValue()));
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append((CharSequence) "");
        int size = arrayList.size();
        int i7 = 0;
        for (int i10 = 0; i10 < size; i10++) {
            Object obj = arrayList.get(i10);
            i7++;
            if (i7 > 1) {
                sb2.append((CharSequence) ", ");
            }
            if (obj != null ? obj instanceof CharSequence : true) {
                sb2.append((CharSequence) obj);
            } else if (obj instanceof Character) {
                sb2.append(((Character) obj).charValue());
            } else {
                sb2.append((CharSequence) String.valueOf(obj));
            }
        }
        sb2.append((CharSequence) "");
        sb.append(sb2.toString());
        sb.append(']');
        return sb.toString();
    }
}
