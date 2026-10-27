package E5;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class B extends AbstractSet {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ E f1750m;

    public /* synthetic */ B(E e10, int i7) {
        this.l = i7;
        this.f1750m = e10;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        switch (this.l) {
            case 0:
                this.f1750m.clear();
                break;
            default:
                this.f1750m.clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        D dB;
        Object obj2;
        Object value;
        switch (this.l) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                E e10 = this.f1750m;
                e10.getClass();
                Object key = entry.getKey();
                D d = null;
                if (key != null) {
                    try {
                        dB = e10.b(key, false);
                    } catch (ClassCastException unused) {
                        dB = null;
                    }
                    break;
                } else {
                    dB = null;
                }
                if (dB != null && ((obj2 = dB.f1759s) == (value = entry.getValue()) || (obj2 != null && obj2.equals(value)))) {
                    d = dB;
                }
                return d != null;
            default:
                return this.f1750m.containsKey(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        switch (this.l) {
            case 0:
                return new A(this.f1750m, 0);
            default:
                return new A(this.f1750m, 1);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        D dB;
        Object obj2;
        Object value;
        switch (this.l) {
            case 0:
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    E e10 = this.f1750m;
                    e10.getClass();
                    Object key = entry.getKey();
                    D d = null;
                    if (key != null) {
                        try {
                            dB = e10.b(key, false);
                        } catch (ClassCastException unused) {
                            dB = null;
                        }
                    } else {
                        dB = null;
                    }
                    if (dB != null && ((obj2 = dB.f1759s) == (value = entry.getValue()) || (obj2 != null && obj2.equals(value)))) {
                        d = dB;
                    }
                    if (d != null) {
                        e10.d(d, true);
                        break;
                    }
                    break;
                }
                break;
            default:
                E e11 = this.f1750m;
                e11.getClass();
                D dB2 = null;
                if (obj != null) {
                    try {
                        dB2 = e11.b(obj, false);
                        break;
                    } catch (ClassCastException unused2) {
                    }
                }
                if (dB2 != null) {
                    e11.d(dB2, true);
                }
                if (dB2 != null) {
                }
                break;
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        switch (this.l) {
        }
        return this.f1750m.o;
    }
}
