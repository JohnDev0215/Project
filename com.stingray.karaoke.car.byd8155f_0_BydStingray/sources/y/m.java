package Y;

import T.C0735d;
import T.S;
import a0.C0905a;
import a0.C0906b;
import java.util.Arrays;
import n7.AbstractC1996k;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final m f10600e = new m(0, 0, new Object[0], null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f10601a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f10602b;
    public final C0906b c;
    public Object[] d;

    public m(int i7, int i10, Object[] objArr, C0906b c0906b) {
        this.f10601a = i7;
        this.f10602b = i10;
        this.c = c0906b;
        this.d = objArr;
    }

    public static m j(int i7, Object obj, Object obj2, int i10, Object obj3, Object obj4, int i11, C0906b c0906b) {
        if (i11 > 30) {
            return new m(0, 0, new Object[]{obj, obj2, obj3, obj4}, c0906b);
        }
        int iW = l9.d.W(i7, i11);
        int iW2 = l9.d.W(i10, i11);
        if (iW != iW2) {
            return new m((1 << iW) | (1 << iW2), 0, iW < iW2 ? new Object[]{obj, obj2, obj3, obj4} : new Object[]{obj3, obj4, obj, obj2}, c0906b);
        }
        return new m(0, 1 << iW, new Object[]{j(i7, obj, obj2, i10, obj3, obj4, i11 + 5, c0906b)}, c0906b);
    }

    public final Object[] a(int i7, int i10, int i11, Object obj, Object obj2, int i12, C0906b c0906b) {
        Object obj3 = this.d[i7];
        m mVarJ = j(obj3 != null ? obj3.hashCode() : 0, obj3, x(i7), i11, obj, obj2, i12 + 5, c0906b);
        int iT = t(i10);
        int i13 = iT + 1;
        Object[] objArr = this.d;
        Object[] objArr2 = new Object[objArr.length - 1];
        AbstractC1996k.G(0, i7, 6, objArr, objArr2);
        AbstractC1996k.D(i7, i7 + 2, i13, objArr, objArr2);
        objArr2[iT - 1] = mVarJ;
        AbstractC1996k.D(iT, i13, objArr.length, objArr, objArr2);
        return objArr2;
    }

    public final int b() {
        if (this.f10602b == 0) {
            return this.d.length / 2;
        }
        int iBitCount = Integer.bitCount(this.f10601a);
        int length = this.d.length;
        for (int i7 = iBitCount * 2; i7 < length; i7++) {
            iBitCount += s(i7).b();
        }
        return iBitCount;
    }

    public final boolean c(Object obj) {
        G7.b bVarW = AbstractC2673a.W(AbstractC2673a.b0(0, this.d.length), 2);
        int i7 = bVarW.l;
        int i10 = bVarW.f2712m;
        int i11 = bVarW.f2713n;
        if ((i11 > 0 && i7 <= i10) || (i11 < 0 && i10 <= i7)) {
            while (!A7.m.a(obj, this.d[i7])) {
                if (i7 != i10) {
                    i7 += i11;
                }
            }
            return true;
        }
        return false;
    }

    public final boolean d(int i7, int i10, Object obj) {
        int iW = 1 << l9.d.W(i7, i10);
        if (h(iW)) {
            return A7.m.a(obj, this.d[f(iW)]);
        }
        if (!i(iW)) {
            return false;
        }
        m mVarS = s(t(iW));
        return i10 == 30 ? mVarS.c(obj) : mVarS.d(i7, i10 + 5, obj);
    }

    public final boolean e(m mVar) {
        if (this == mVar) {
            return true;
        }
        if (this.f10602b != mVar.f10602b || this.f10601a != mVar.f10601a) {
            return false;
        }
        int length = this.d.length;
        for (int i7 = 0; i7 < length; i7++) {
            if (this.d[i7] != mVar.d[i7]) {
                return false;
            }
        }
        return true;
    }

    public final int f(int i7) {
        return Integer.bitCount(this.f10601a & (i7 - 1)) * 2;
    }

    public final Object g(int i7, int i10, Object obj) {
        int iW = 1 << l9.d.W(i7, i10);
        if (h(iW)) {
            int iF = f(iW);
            if (A7.m.a(obj, this.d[iF])) {
                return x(iF);
            }
            return null;
        }
        if (!i(iW)) {
            return null;
        }
        m mVarS = s(t(iW));
        if (i10 != 30) {
            return mVarS.g(i7, i10 + 5, obj);
        }
        G7.b bVarW = AbstractC2673a.W(AbstractC2673a.b0(0, mVarS.d.length), 2);
        int i11 = bVarW.l;
        int i12 = bVarW.f2712m;
        int i13 = bVarW.f2713n;
        if ((i13 <= 0 || i11 > i12) && (i13 >= 0 || i12 > i11)) {
            return null;
        }
        while (!A7.m.a(obj, mVarS.d[i11])) {
            if (i11 == i12) {
                return null;
            }
            i11 += i13;
        }
        return mVarS.x(i11);
    }

    public final boolean h(int i7) {
        return (this.f10601a & i7) != 0;
    }

    public final boolean i(int i7) {
        return (this.f10602b & i7) != 0;
    }

    public final m k(int i7, e eVar) {
        eVar.getClass();
        eVar.c(eVar.f10593q - 1);
        eVar.o = x(i7);
        Object[] objArr = this.d;
        if (objArr.length == 2) {
            return null;
        }
        if (this.c != eVar.f10590m) {
            return new m(0, 0, l9.d.B(i7, objArr), eVar.f10590m);
        }
        this.d = l9.d.B(i7, objArr);
        return this;
    }

    public final m l(int i7, Object obj, Object obj2, int i10, e eVar) {
        m mVarL;
        int iW = 1 << l9.d.W(i7, i10);
        boolean zH = h(iW);
        C0906b c0906b = this.c;
        if (zH) {
            int iF = f(iW);
            if (!A7.m.a(obj, this.d[iF])) {
                eVar.getClass();
                eVar.c(eVar.f10593q + 1);
                C0906b c0906b2 = eVar.f10590m;
                if (c0906b != c0906b2) {
                    return new m(this.f10601a ^ iW, this.f10602b | iW, a(iF, iW, i7, obj, obj2, i10, c0906b2), c0906b2);
                }
                this.d = a(iF, iW, i7, obj, obj2, i10, c0906b2);
                this.f10601a ^= iW;
                this.f10602b |= iW;
                return this;
            }
            eVar.o = x(iF);
            if (x(iF) == obj2) {
                return this;
            }
            if (c0906b == eVar.f10590m) {
                this.d[iF + 1] = obj2;
                return this;
            }
            eVar.f10592p++;
            Object[] objArr = this.d;
            Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
            A7.m.e(objArrCopyOf, "copyOf(this, size)");
            objArrCopyOf[iF + 1] = obj2;
            return new m(this.f10601a, this.f10602b, objArrCopyOf, eVar.f10590m);
        }
        if (!i(iW)) {
            eVar.getClass();
            eVar.c(eVar.f10593q + 1);
            C0906b c0906b3 = eVar.f10590m;
            int iF2 = f(iW);
            if (c0906b != c0906b3) {
                return new m(this.f10601a | iW, this.f10602b, l9.d.z(iF2, obj, obj2, this.d), c0906b3);
            }
            this.d = l9.d.z(iF2, obj, obj2, this.d);
            this.f10601a |= iW;
            return this;
        }
        int iT = t(iW);
        m mVarS = s(iT);
        if (i10 == 30) {
            G7.b bVarW = AbstractC2673a.W(AbstractC2673a.b0(0, mVarS.d.length), 2);
            int i11 = bVarW.l;
            int i12 = bVarW.f2712m;
            int i13 = bVarW.f2713n;
            if ((i13 <= 0 || i11 > i12) && (i13 >= 0 || i12 > i11)) {
                eVar.getClass();
                eVar.c(eVar.f10593q + 1);
                mVarL = new m(0, 0, l9.d.z(0, obj, obj2, mVarS.d), eVar.f10590m);
                break;
            }
            while (!A7.m.a(obj, mVarS.d[i11])) {
                if (i11 == i12) {
                    eVar.getClass();
                    eVar.c(eVar.f10593q + 1);
                    mVarL = new m(0, 0, l9.d.z(0, obj, obj2, mVarS.d), eVar.f10590m);
                    break;
                }
                i11 += i13;
            }
            eVar.o = mVarS.x(i11);
            if (mVarS.c == eVar.f10590m) {
                mVarS.d[i11 + 1] = obj2;
                mVarL = mVarS;
            } else {
                eVar.f10592p++;
                Object[] objArr2 = mVarS.d;
                Object[] objArrCopyOf2 = Arrays.copyOf(objArr2, objArr2.length);
                A7.m.e(objArrCopyOf2, "copyOf(this, size)");
                objArrCopyOf2[i11 + 1] = obj2;
                mVarL = new m(0, 0, objArrCopyOf2, eVar.f10590m);
            }
        } else {
            mVarL = mVarS.l(i7, obj, obj2, i10 + 5, eVar);
        }
        return mVarS == mVarL ? this : r(iT, mVarL, eVar.f10590m);
    }

    public final m m(m mVar, int i7, C0905a c0905a, e eVar) {
        Object[] objArr;
        int i10;
        m mVarJ;
        if (this == mVar) {
            c0905a.f11025a += b();
            return this;
        }
        int i11 = 0;
        if (i7 > 30) {
            C0906b c0906b = eVar.f10590m;
            int i12 = mVar.f10602b;
            Object[] objArr2 = this.d;
            Object[] objArrCopyOf = Arrays.copyOf(objArr2, objArr2.length + mVar.d.length);
            A7.m.e(objArrCopyOf, "copyOf(this, newSize)");
            int length = this.d.length;
            G7.b bVarW = AbstractC2673a.W(AbstractC2673a.b0(0, mVar.d.length), 2);
            int i13 = bVarW.l;
            int i14 = bVarW.f2712m;
            int i15 = bVarW.f2713n;
            if ((i15 > 0 && i13 <= i14) || (i15 < 0 && i14 <= i13)) {
                while (true) {
                    if (c(mVar.d[i13])) {
                        c0905a.f11025a++;
                    } else {
                        Object[] objArr3 = mVar.d;
                        objArrCopyOf[length] = objArr3[i13];
                        objArrCopyOf[length + 1] = objArr3[i13 + 1];
                        length += 2;
                    }
                    if (i13 == i14) {
                        break;
                    }
                    i13 += i15;
                }
            }
            if (length == this.d.length) {
                return this;
            }
            if (length == mVar.d.length) {
                return mVar;
            }
            if (length == objArrCopyOf.length) {
                return new m(0, 0, objArrCopyOf, c0906b);
            }
            Object[] objArrCopyOf2 = Arrays.copyOf(objArrCopyOf, length);
            A7.m.e(objArrCopyOf2, "copyOf(this, newSize)");
            return new m(0, 0, objArrCopyOf2, c0906b);
        }
        int i16 = this.f10602b | mVar.f10602b;
        int i17 = this.f10601a;
        int i18 = mVar.f10601a;
        int i19 = (i17 ^ i18) & (~i16);
        int i20 = i17 & i18;
        int i21 = i19;
        while (i20 != 0) {
            int iLowestOneBit = Integer.lowestOneBit(i20);
            if (A7.m.a(this.d[f(iLowestOneBit)], mVar.d[mVar.f(iLowestOneBit)])) {
                i21 |= iLowestOneBit;
            } else {
                i16 |= iLowestOneBit;
            }
            i20 ^= iLowestOneBit;
        }
        if (!((i16 & i21) == 0)) {
            C0735d.Y("Check failed.");
            throw null;
        }
        m mVar2 = (A7.m.a(this.c, eVar.f10590m) && this.f10601a == i21 && this.f10602b == i16) ? this : new m(i21, i16, new Object[Integer.bitCount(i16) + (Integer.bitCount(i21) * 2)], null);
        int i22 = i16;
        int i23 = 0;
        while (i22 != 0) {
            int iLowestOneBit2 = Integer.lowestOneBit(i22);
            Object[] objArr4 = mVar2.d;
            int length2 = (objArr4.length - 1) - i23;
            if (i(iLowestOneBit2)) {
                mVarJ = s(t(iLowestOneBit2));
                if (mVar.i(iLowestOneBit2)) {
                    mVarJ = mVarJ.m(mVar.s(mVar.t(iLowestOneBit2)), i7 + 5, c0905a, eVar);
                } else if (mVar.h(iLowestOneBit2)) {
                    int iF = mVar.f(iLowestOneBit2);
                    Object obj = mVar.d[iF];
                    Object objX = mVar.x(iF);
                    int i24 = eVar.f10593q;
                    objArr = objArr4;
                    mVarJ = mVarJ.l(obj != null ? obj.hashCode() : i11, obj, objX, i7 + 5, eVar);
                    if (eVar.f10593q == i24) {
                        c0905a.f11025a++;
                    }
                    i10 = iLowestOneBit2;
                }
                objArr = objArr4;
                i10 = iLowestOneBit2;
            } else {
                objArr = objArr4;
                i10 = iLowestOneBit2;
                if (mVar.i(i10)) {
                    mVarJ = mVar.s(mVar.t(i10));
                    if (h(i10)) {
                        int iF2 = f(i10);
                        Object obj2 = this.d[iF2];
                        int i25 = i7 + 5;
                        if (mVarJ.d(obj2 != null ? obj2.hashCode() : 0, i25, obj2)) {
                            c0905a.f11025a++;
                        } else {
                            mVarJ = mVarJ.l(obj2 != null ? obj2.hashCode() : 0, obj2, x(iF2), i25, eVar);
                        }
                    }
                } else {
                    int iF3 = f(i10);
                    Object obj3 = this.d[iF3];
                    Object objX2 = x(iF3);
                    int iF4 = mVar.f(i10);
                    Object obj4 = mVar.d[iF4];
                    mVarJ = j(obj3 != null ? obj3.hashCode() : 0, obj3, objX2, obj4 != null ? obj4.hashCode() : 0, obj4, mVar.x(iF4), i7 + 5, eVar.f10590m);
                }
            }
            objArr[length2] = mVarJ;
            i23++;
            i22 ^= i10;
            i11 = 0;
        }
        int i26 = 0;
        while (i21 != 0) {
            int iLowestOneBit3 = Integer.lowestOneBit(i21);
            int i27 = i26 * 2;
            if (mVar.h(iLowestOneBit3)) {
                int iF5 = mVar.f(iLowestOneBit3);
                Object[] objArr5 = mVar2.d;
                objArr5[i27] = mVar.d[iF5];
                objArr5[i27 + 1] = mVar.x(iF5);
                if (h(iLowestOneBit3)) {
                    c0905a.f11025a++;
                }
            } else {
                int iF6 = f(iLowestOneBit3);
                Object[] objArr6 = mVar2.d;
                objArr6[i27] = this.d[iF6];
                objArr6[i27 + 1] = x(iF6);
            }
            i26++;
            i21 ^= iLowestOneBit3;
        }
        return e(mVar2) ? this : mVar.e(mVar2) ? mVar : mVar2;
    }

    public final m n(int i7, Object obj, int i10, e eVar) {
        m mVarN;
        int iW = 1 << l9.d.W(i7, i10);
        if (h(iW)) {
            int iF = f(iW);
            return A7.m.a(obj, this.d[iF]) ? p(iF, iW, eVar) : this;
        }
        if (!i(iW)) {
            return this;
        }
        int iT = t(iW);
        m mVarS = s(iT);
        if (i10 == 30) {
            G7.b bVarW = AbstractC2673a.W(AbstractC2673a.b0(0, mVarS.d.length), 2);
            int i11 = bVarW.l;
            int i12 = bVarW.f2712m;
            int i13 = bVarW.f2713n;
            if ((i13 <= 0 || i11 > i12) && (i13 >= 0 || i12 > i11)) {
                mVarN = mVarS;
                break;
            }
            while (!A7.m.a(obj, mVarS.d[i11])) {
                if (i11 == i12) {
                    mVarN = mVarS;
                    break;
                }
                i11 += i13;
            }
            mVarN = mVarS.k(i11, eVar);
        } else {
            mVarN = mVarS.n(i7, obj, i10 + 5, eVar);
        }
        return q(mVarS, mVarN, iT, iW, eVar.f10590m);
    }

    public final m o(int i7, Object obj, Object obj2, int i10, e eVar) {
        m mVarO;
        int iW = 1 << l9.d.W(i7, i10);
        if (h(iW)) {
            int iF = f(iW);
            return (A7.m.a(obj, this.d[iF]) && A7.m.a(obj2, x(iF))) ? p(iF, iW, eVar) : this;
        }
        if (!i(iW)) {
            return this;
        }
        int iT = t(iW);
        m mVarS = s(iT);
        if (i10 == 30) {
            G7.b bVarW = AbstractC2673a.W(AbstractC2673a.b0(0, mVarS.d.length), 2);
            int i11 = bVarW.l;
            int i12 = bVarW.f2712m;
            int i13 = bVarW.f2713n;
            if ((i13 <= 0 || i11 > i12) && (i13 >= 0 || i12 > i11)) {
                mVarO = mVarS;
            } else {
                while (true) {
                    if (!A7.m.a(obj, mVarS.d[i11]) || !A7.m.a(obj2, mVarS.x(i11))) {
                        if (i11 == i12) {
                            break;
                        }
                        i11 += i13;
                    } else {
                        mVarO = mVarS.k(i11, eVar);
                        break;
                    }
                }
                mVarO = mVarS;
            }
        } else {
            mVarO = mVarS.o(i7, obj, obj2, i10 + 5, eVar);
        }
        return q(mVarS, mVarO, iT, iW, eVar.f10590m);
    }

    public final m p(int i7, int i10, e eVar) {
        eVar.getClass();
        eVar.c(eVar.f10593q - 1);
        eVar.o = x(i7);
        Object[] objArr = this.d;
        if (objArr.length == 2) {
            return null;
        }
        if (this.c != eVar.f10590m) {
            return new m(i10 ^ this.f10601a, this.f10602b, l9.d.B(i7, objArr), eVar.f10590m);
        }
        this.d = l9.d.B(i7, objArr);
        this.f10601a ^= i10;
        return this;
    }

    public final m q(m mVar, m mVar2, int i7, int i10, C0906b c0906b) {
        C0906b c0906b2 = this.c;
        if (mVar2 != null) {
            return (c0906b2 == c0906b || mVar != mVar2) ? r(i7, mVar2, c0906b) : this;
        }
        Object[] objArr = this.d;
        if (objArr.length == 1) {
            return null;
        }
        if (c0906b2 != c0906b) {
            return new m(this.f10601a, this.f10602b ^ i10, l9.d.C(i7, objArr), c0906b);
        }
        this.d = l9.d.C(i7, objArr);
        this.f10602b ^= i10;
        return this;
    }

    public final m r(int i7, m mVar, C0906b c0906b) {
        Object[] objArr = this.d;
        if (objArr.length == 1 && mVar.d.length == 2 && mVar.f10602b == 0) {
            mVar.f10601a = this.f10602b;
            return mVar;
        }
        if (this.c == c0906b) {
            objArr[i7] = mVar;
            return this;
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        A7.m.e(objArrCopyOf, "copyOf(this, size)");
        objArrCopyOf[i7] = mVar;
        return new m(this.f10601a, this.f10602b, objArrCopyOf, c0906b);
    }

    public final m s(int i7) {
        Object obj = this.d[i7];
        A7.m.d(obj, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode>");
        return (m) obj;
    }

    public final int t(int i7) {
        return (this.d.length - 1) - Integer.bitCount(this.f10602b & (i7 - 1));
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00cb A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final C1.j u(java.lang.Object r12, int r13, java.lang.Object r14, int r15) {
        /*
            Method dump skipped, instruction units count: 250
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: Y.m.u(java.lang.Object, int, java.lang.Object, int):C1.j");
    }

    public final m v(int i7, S s6, int i10) {
        m mVarV;
        int iW = 1 << l9.d.W(i7, i10);
        if (h(iW)) {
            int iF = f(iW);
            if (!A7.m.a(s6, this.d[iF])) {
                return this;
            }
            Object[] objArr = this.d;
            if (objArr.length == 2) {
                return null;
            }
            return new m(this.f10601a ^ iW, this.f10602b, l9.d.B(iF, objArr), null);
        }
        if (!i(iW)) {
            return this;
        }
        int iT = t(iW);
        m mVarS = s(iT);
        if (i10 == 30) {
            G7.b bVarW = AbstractC2673a.W(AbstractC2673a.b0(0, mVarS.d.length), 2);
            int i11 = bVarW.l;
            int i12 = bVarW.f2712m;
            int i13 = bVarW.f2713n;
            if ((i13 <= 0 || i11 > i12) && (i13 >= 0 || i12 > i11)) {
                mVarV = mVarS;
                break;
            }
            while (!A7.m.a(s6, mVarS.d[i11])) {
                if (i11 == i12) {
                    mVarV = mVarS;
                    break;
                }
                i11 += i13;
            }
            Object[] objArr2 = mVarS.d;
            mVarV = objArr2.length == 2 ? null : new m(0, 0, l9.d.B(i11, objArr2), null);
        } else {
            mVarV = mVarS.v(i7, s6, i10 + 5);
        }
        if (mVarV != null) {
            return mVarS != mVarV ? w(iT, iW, mVarV) : this;
        }
        Object[] objArr3 = this.d;
        if (objArr3.length == 1) {
            return null;
        }
        return new m(this.f10601a, this.f10602b ^ iW, l9.d.C(iT, objArr3), null);
    }

    public final m w(int i7, int i10, m mVar) {
        Object[] objArr = mVar.d;
        if (objArr.length != 2 || mVar.f10602b != 0) {
            Object[] objArr2 = this.d;
            Object[] objArrCopyOf = Arrays.copyOf(objArr2, objArr2.length);
            A7.m.e(objArrCopyOf, "copyOf(this, newSize)");
            objArrCopyOf[i7] = mVar;
            return new m(this.f10601a, this.f10602b, objArrCopyOf, null);
        }
        if (this.d.length == 1) {
            mVar.f10601a = this.f10602b;
            return mVar;
        }
        int iF = f(i10);
        Object[] objArr3 = this.d;
        Object obj = objArr[0];
        Object obj2 = objArr[1];
        Object[] objArrCopyOf2 = Arrays.copyOf(objArr3, objArr3.length + 1);
        A7.m.e(objArrCopyOf2, "copyOf(this, newSize)");
        AbstractC1996k.D(i7 + 2, i7 + 1, objArr3.length, objArrCopyOf2, objArrCopyOf2);
        AbstractC1996k.D(iF + 2, iF, i7, objArrCopyOf2, objArrCopyOf2);
        objArrCopyOf2[iF] = obj;
        objArrCopyOf2[iF + 1] = obj2;
        return new m(this.f10601a ^ i10, this.f10602b ^ i10, objArrCopyOf2, null);
    }

    public final Object x(int i7) {
        return this.d[i7 + 1];
    }
}
