package l5;

import E5.y;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class n extends AbstractMap implements Serializable {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final y f18656t = new y(8);
    public final Comparator l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f18657m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public m f18658n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f18659p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final m f18660q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public l f18661r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public l f18662s;

    public n(boolean z6) {
        y yVar = f18656t;
        this.o = 0;
        this.f18659p = 0;
        this.l = yVar;
        this.f18657m = z6;
        this.f18660q = new m(z6);
    }

    public final m b(Object obj, boolean z6) {
        int iCompareTo;
        m mVar;
        m mVar2 = this.f18658n;
        y yVar = f18656t;
        Comparator comparator = this.l;
        if (mVar2 != null) {
            Comparable comparable = comparator == yVar ? (Comparable) obj : null;
            while (true) {
                Object obj2 = mVar2.f18652q;
                iCompareTo = comparable != null ? comparable.compareTo(obj2) : comparator.compare(obj, obj2);
                if (iCompareTo == 0) {
                    return mVar2;
                }
                m mVar3 = iCompareTo < 0 ? mVar2.f18649m : mVar2.f18650n;
                if (mVar3 == null) {
                    break;
                }
                mVar2 = mVar3;
            }
        } else {
            iCompareTo = 0;
        }
        if (!z6) {
            return null;
        }
        m mVar4 = this.f18660q;
        if (mVar2 != null) {
            mVar = new m(this.f18657m, mVar2, obj, mVar4, mVar4.f18651p);
            if (iCompareTo < 0) {
                mVar2.f18649m = mVar;
            } else {
                mVar2.f18650n = mVar;
            }
            c(mVar2, true);
        } else {
            if (comparator == yVar && !(obj instanceof Comparable)) {
                throw new ClassCastException(obj.getClass().getName().concat(" is not Comparable"));
            }
            mVar = new m(this.f18657m, mVar2, obj, mVar4, mVar4.f18651p);
            this.f18658n = mVar;
        }
        this.o++;
        this.f18659p++;
        return mVar;
    }

    public final void c(m mVar, boolean z6) {
        while (mVar != null) {
            m mVar2 = mVar.f18649m;
            m mVar3 = mVar.f18650n;
            int i7 = mVar2 != null ? mVar2.f18655t : 0;
            int i10 = mVar3 != null ? mVar3.f18655t : 0;
            int i11 = i7 - i10;
            if (i11 == -2) {
                m mVar4 = mVar3.f18649m;
                m mVar5 = mVar3.f18650n;
                int i12 = (mVar4 != null ? mVar4.f18655t : 0) - (mVar5 != null ? mVar5.f18655t : 0);
                if (i12 == -1 || (i12 == 0 && !z6)) {
                    f(mVar);
                } else {
                    g(mVar3);
                    f(mVar);
                }
                if (z6) {
                    return;
                }
            } else if (i11 == 2) {
                m mVar6 = mVar2.f18649m;
                m mVar7 = mVar2.f18650n;
                int i13 = (mVar6 != null ? mVar6.f18655t : 0) - (mVar7 != null ? mVar7.f18655t : 0);
                if (i13 == 1 || (i13 == 0 && !z6)) {
                    g(mVar);
                } else {
                    f(mVar2);
                    g(mVar);
                }
                if (z6) {
                    return;
                }
            } else if (i11 == 0) {
                mVar.f18655t = i7 + 1;
                if (z6) {
                    return;
                }
            } else {
                mVar.f18655t = Math.max(i7, i10) + 1;
                if (!z6) {
                    return;
                }
            }
            mVar = mVar.l;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        this.f18658n = null;
        this.o = 0;
        this.f18659p++;
        m mVar = this.f18660q;
        mVar.f18651p = mVar;
        mVar.o = mVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        m mVarB = null;
        if (obj != null) {
            try {
                mVarB = b(obj, false);
            } catch (ClassCastException unused) {
            }
        }
        return mVarB != null;
    }

    public final void d(m mVar, boolean z6) {
        m mVar2;
        m mVar3;
        int i7;
        if (z6) {
            m mVar4 = mVar.f18651p;
            mVar4.o = mVar.o;
            mVar.o.f18651p = mVar4;
        }
        m mVar5 = mVar.f18649m;
        m mVar6 = mVar.f18650n;
        m mVar7 = mVar.l;
        int i10 = 0;
        if (mVar5 == null || mVar6 == null) {
            if (mVar5 != null) {
                e(mVar, mVar5);
                mVar.f18649m = null;
            } else if (mVar6 != null) {
                e(mVar, mVar6);
                mVar.f18650n = null;
            } else {
                e(mVar, null);
            }
            c(mVar7, false);
            this.o--;
            this.f18659p++;
            return;
        }
        if (mVar5.f18655t > mVar6.f18655t) {
            m mVar8 = mVar5.f18650n;
            while (true) {
                m mVar9 = mVar8;
                mVar3 = mVar5;
                mVar5 = mVar9;
                if (mVar5 == null) {
                    break;
                } else {
                    mVar8 = mVar5.f18650n;
                }
            }
        } else {
            m mVar10 = mVar6.f18649m;
            while (true) {
                mVar2 = mVar6;
                mVar6 = mVar10;
                if (mVar6 == null) {
                    break;
                } else {
                    mVar10 = mVar6.f18649m;
                }
            }
            mVar3 = mVar2;
        }
        d(mVar3, false);
        m mVar11 = mVar.f18649m;
        if (mVar11 != null) {
            i7 = mVar11.f18655t;
            mVar3.f18649m = mVar11;
            mVar11.l = mVar3;
            mVar.f18649m = null;
        } else {
            i7 = 0;
        }
        m mVar12 = mVar.f18650n;
        if (mVar12 != null) {
            i10 = mVar12.f18655t;
            mVar3.f18650n = mVar12;
            mVar12.l = mVar3;
            mVar.f18650n = null;
        }
        mVar3.f18655t = Math.max(i7, i10) + 1;
        e(mVar, mVar3);
    }

    public final void e(m mVar, m mVar2) {
        m mVar3 = mVar.l;
        mVar.l = null;
        if (mVar2 != null) {
            mVar2.l = mVar3;
        }
        if (mVar3 == null) {
            this.f18658n = mVar2;
        } else if (mVar3.f18649m == mVar) {
            mVar3.f18649m = mVar2;
        } else {
            mVar3.f18650n = mVar2;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        l lVar = this.f18661r;
        if (lVar != null) {
            return lVar;
        }
        l lVar2 = new l(this, 0);
        this.f18661r = lVar2;
        return lVar2;
    }

    public final void f(m mVar) {
        m mVar2 = mVar.f18649m;
        m mVar3 = mVar.f18650n;
        m mVar4 = mVar3.f18649m;
        m mVar5 = mVar3.f18650n;
        mVar.f18650n = mVar4;
        if (mVar4 != null) {
            mVar4.l = mVar;
        }
        e(mVar, mVar3);
        mVar3.f18649m = mVar;
        mVar.l = mVar3;
        int iMax = Math.max(mVar2 != null ? mVar2.f18655t : 0, mVar4 != null ? mVar4.f18655t : 0) + 1;
        mVar.f18655t = iMax;
        mVar3.f18655t = Math.max(iMax, mVar5 != null ? mVar5.f18655t : 0) + 1;
    }

    public final void g(m mVar) {
        m mVar2 = mVar.f18649m;
        m mVar3 = mVar.f18650n;
        m mVar4 = mVar2.f18649m;
        m mVar5 = mVar2.f18650n;
        mVar.f18649m = mVar5;
        if (mVar5 != null) {
            mVar5.l = mVar;
        }
        e(mVar, mVar2);
        mVar2.f18650n = mVar;
        mVar.l = mVar2;
        int iMax = Math.max(mVar3 != null ? mVar3.f18655t : 0, mVar5 != null ? mVar5.f18655t : 0) + 1;
        mVar.f18655t = iMax;
        mVar2.f18655t = Math.max(iMax, mVar4 != null ? mVar4.f18655t : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        m mVarB;
        if (obj != null) {
            try {
                mVarB = b(obj, false);
            } catch (ClassCastException unused) {
                mVarB = null;
            }
        } else {
            mVarB = null;
        }
        if (mVarB != null) {
            return mVarB.f18654s;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        l lVar = this.f18662s;
        if (lVar != null) {
            return lVar;
        }
        l lVar2 = new l(this, 1);
        this.f18662s = lVar2;
        return lVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException("key == null");
        }
        if (obj2 == null && !this.f18657m) {
            throw new NullPointerException("value == null");
        }
        m mVarB = b(obj, true);
        Object obj3 = mVarB.f18654s;
        mVarB.f18654s = obj2;
        return obj3;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        m mVarB;
        if (obj != null) {
            try {
                mVarB = b(obj, false);
            } catch (ClassCastException unused) {
                mVarB = null;
            }
        } else {
            mVarB = null;
        }
        if (mVarB != null) {
            d(mVarB, true);
        }
        if (mVarB != null) {
            return mVarB.f18654s;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.o;
    }
}
