package d5;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class f0 extends M {
    public final transient K o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final transient Object[] f13420p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final transient int f13421q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final transient int f13422r;

    public f0(K k10, Object[] objArr, int i7, int i10) {
        this.o = k10;
        this.f13420p = objArr;
        this.f13421q = i7;
        this.f13422r = i10;
    }

    @Override // d5.AbstractC1075D, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Object value = entry.getValue();
        return value != null && value.equals(this.o.get(key));
    }

    @Override // d5.AbstractC1075D
    public final int d(int i7, Object[] objArr) {
        return a().d(i7, objArr);
    }

    @Override // d5.AbstractC1075D
    public final boolean r() {
        return true;
    }

    @Override // d5.AbstractC1075D
    /* JADX INFO: renamed from: s */
    public final s0 iterator() {
        return a().listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f13422r;
    }

    @Override // d5.M
    public final I w() {
        return new e0(this);
    }
}
