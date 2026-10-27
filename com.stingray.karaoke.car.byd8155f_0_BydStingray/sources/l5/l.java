package l5;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class l extends AbstractSet {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ n f18648m;

    public /* synthetic */ l(n nVar, int i7) {
        this.l = i7;
        this.f18648m = nVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        switch (this.l) {
            case 0:
                this.f18648m.clear();
                break;
            default:
                this.f18648m.clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        m mVarB;
        switch (this.l) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                n nVar = this.f18648m;
                nVar.getClass();
                Object key = entry.getKey();
                m mVar = null;
                if (key != null) {
                    try {
                        mVarB = nVar.b(key, false);
                    } catch (ClassCastException unused) {
                        mVarB = null;
                    }
                    break;
                } else {
                    mVarB = null;
                }
                if (mVarB != null && Objects.equals(mVarB.f18654s, entry.getValue())) {
                    mVar = mVarB;
                }
                return mVar != null;
            default:
                return this.f18648m.containsKey(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        switch (this.l) {
            case 0:
                return new k(this.f18648m, 0);
            default:
                return new k(this.f18648m, 1);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        m mVarB;
        switch (this.l) {
            case 0:
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    n nVar = this.f18648m;
                    nVar.getClass();
                    Object key = entry.getKey();
                    m mVar = null;
                    if (key != null) {
                        try {
                            mVarB = nVar.b(key, false);
                        } catch (ClassCastException unused) {
                            mVarB = null;
                        }
                    } else {
                        mVarB = null;
                    }
                    if (mVarB != null && Objects.equals(mVarB.f18654s, entry.getValue())) {
                        mVar = mVarB;
                    }
                    if (mVar != null) {
                        nVar.d(mVar, true);
                        break;
                    }
                    break;
                }
                break;
            default:
                n nVar2 = this.f18648m;
                nVar2.getClass();
                m mVarB2 = null;
                if (obj != null) {
                    try {
                        mVarB2 = nVar2.b(obj, false);
                        break;
                    } catch (ClassCastException unused2) {
                    }
                }
                if (mVarB2 != null) {
                    nVar2.d(mVarB2, true);
                }
                if (mVarB2 != null) {
                }
                break;
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        switch (this.l) {
        }
        return this.f18648m.o;
    }
}
