package com.google.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import java.util.Arrays;
import p.au;
import p.p01;

/* JADX INFO: loaded from: classes.dex */
public final class o {
    public static final o f = new o(0, new int[0], new Object[0], false);
    public int a;
    public int[] b;
    public Object[] c;
    public int d;
    public boolean e;

    public o() {
        this(0, new int[8], new Object[8], true);
    }

    public static o e(o oVar, o oVar2) {
        int i = oVar.a + oVar2.a;
        int[] iArrCopyOf = Arrays.copyOf(oVar.b, i);
        System.arraycopy(oVar2.b, 0, iArrCopyOf, oVar.a, oVar2.a);
        Object[] objArrCopyOf = Arrays.copyOf(oVar.c, i);
        System.arraycopy(oVar2.c, 0, objArrCopyOf, oVar.a, oVar2.a);
        return new o(i, iArrCopyOf, objArrCopyOf, true);
    }

    public final void a() {
        if (!this.e) {
            throw new UnsupportedOperationException();
        }
    }

    public final void b(int i) {
        int[] iArr = this.b;
        if (i > iArr.length) {
            int i2 = this.a;
            int i3 = (i2 / 2) + i2;
            if (i3 >= i) {
                i = i3;
            }
            if (i < 8) {
                i = 8;
            }
            this.b = Arrays.copyOf(iArr, i);
            this.c = Arrays.copyOf(this.c, i);
        }
    }

    public final int c() {
        int iO;
        int i = this.d;
        if (i != -1) {
            return i;
        }
        int iC = 0;
        for (int i2 = 0; i2 < this.a; i2++) {
            int i3 = this.b[i2];
            int i4 = i3 >>> 3;
            int i5 = i3 & 7;
            if (i5 == 0) {
                iO = h.O(i4, ((Long) this.c[i2]).longValue());
            } else if (i5 == 1) {
                ((Long) this.c[i2]).longValue();
                iO = h.z(i4);
            } else if (i5 == 2) {
                iO = h.v(i4, (ByteString) this.c[i2]);
            } else if (i5 == 3) {
                iC = ((o) this.c[i2]).c() + (h.L(i4) * 2) + iC;
            } else {
                if (i5 != 5) {
                    throw new IllegalStateException(InvalidProtocolBufferException.d());
                }
                ((Integer) this.c[i2]).intValue();
                iO = h.y(i4);
            }
            iC = iO + iC;
        }
        this.d = iC;
        return iC;
    }

    public final boolean d(int i, au auVar) throws InvalidProtocolBufferException.InvalidWireTypeException {
        int iA;
        a();
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            f(i, Long.valueOf(auVar.s()));
            return true;
        }
        if (i3 == 1) {
            f(i, Long.valueOf(auVar.p()));
            return true;
        }
        if (i3 == 2) {
            f(i, auVar.l());
            return true;
        }
        if (i3 != 3) {
            if (i3 == 4) {
                return false;
            }
            if (i3 != 5) {
                throw InvalidProtocolBufferException.d();
            }
            f(i, Integer.valueOf(auVar.o()));
            return true;
        }
        o oVar = new o();
        do {
            iA = auVar.A();
            if (iA == 0) {
                break;
            }
        } while (oVar.d(iA, auVar));
        auVar.a((i2 << 3) | 4);
        f(i, oVar);
        return true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof o)) {
            return false;
        }
        o oVar = (o) obj;
        int i = this.a;
        if (i == oVar.a) {
            int[] iArr = this.b;
            int[] iArr2 = oVar.b;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    Object[] objArr = this.c;
                    Object[] objArr2 = oVar.c;
                    int i3 = this.a;
                    for (int i4 = 0; i4 < i3; i4++) {
                        if (objArr[i4].equals(objArr2[i4])) {
                        }
                    }
                    return true;
                }
                if (iArr[i2] != iArr2[i2]) {
                    break;
                }
                i2++;
            }
        }
        return false;
    }

    public final void f(int i, Object obj) {
        a();
        b(this.a + 1);
        int[] iArr = this.b;
        int i2 = this.a;
        iArr[i2] = i;
        this.c[i2] = obj;
        this.a = i2 + 1;
    }

    public final void g(p01 p01Var) {
        if (this.a == 0) {
            return;
        }
        p01Var.getClass();
        for (int i = 0; i < this.a; i++) {
            int i2 = this.b[i];
            Object obj = this.c[i];
            int i3 = i2 >>> 3;
            int i4 = i2 & 7;
            if (i4 == 0) {
                p01Var.T(i3, ((Long) obj).longValue());
            } else if (i4 == 1) {
                p01Var.P(i3, ((Long) obj).longValue());
            } else if (i4 == 2) {
                p01Var.L(i3, (ByteString) obj);
            } else if (i4 == 3) {
                ((h) p01Var.e).g0(i3, 3);
                ((o) obj).g(p01Var);
                ((h) p01Var.e).g0(i3, 4);
            } else {
                if (i4 != 5) {
                    throw new RuntimeException(InvalidProtocolBufferException.d());
                }
                p01Var.O(i3, ((Integer) obj).intValue());
            }
        }
    }

    public final int hashCode() {
        int i = this.a;
        int i2 = (527 + i) * 31;
        int[] iArr = this.b;
        int iHashCode = 17;
        int i3 = 17;
        for (int i4 = 0; i4 < i; i4++) {
            i3 = (i3 * 31) + iArr[i4];
        }
        int i5 = (i2 + i3) * 31;
        Object[] objArr = this.c;
        int i6 = this.a;
        for (int i7 = 0; i7 < i6; i7++) {
            iHashCode = (iHashCode * 31) + objArr[i7].hashCode();
        }
        return i5 + iHashCode;
    }

    public o(int i, int[] iArr, Object[] objArr, boolean z) {
        this.d = -1;
        this.a = i;
        this.b = iArr;
        this.c = objArr;
        this.e = z;
    }
}
