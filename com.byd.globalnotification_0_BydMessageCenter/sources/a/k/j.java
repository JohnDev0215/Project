package a.k;

import a.k.n;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public class j<T> extends ArrayList<T> implements n<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public transient h f923a = new h();

    @Override // a.k.n
    public void a(n.a aVar) {
        h hVar = this.f923a;
        if (hVar != null) {
            hVar.f(aVar);
        }
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public void add(int i, T t) {
        super.add(i, t);
        c(i, 1);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(T t) {
        super.add(t);
        c(size() - 1, 1);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public boolean addAll(int i, Collection<? extends T> collection) {
        boolean zAddAll = super.addAll(i, collection);
        if (zAddAll) {
            c(i, collection.size());
        }
        return zAddAll;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends T> collection) {
        int size = size();
        boolean zAddAll = super.addAll(collection);
        if (zAddAll) {
            c(size, size() - size);
        }
        return zAddAll;
    }

    @Override // a.k.n
    public void b(n.a aVar) {
        if (this.f923a == null) {
            this.f923a = new h();
        }
        this.f923a.a(aVar);
    }

    public final void c(int i, int i2) {
        h hVar = this.f923a;
        if (hVar != null) {
            hVar.j(this, 2, h.i(i, 0, i2));
        }
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        int size = size();
        super.clear();
        if (size != 0) {
            d(0, size);
        }
    }

    public final void d(int i, int i2) {
        h hVar = this.f923a;
        if (hVar != null) {
            hVar.j(this, 4, h.i(i, 0, i2));
        }
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public T remove(int i) {
        T t = (T) super.remove(i);
        d(i, 1);
        return t;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf < 0) {
            return false;
        }
        remove(iIndexOf);
        return true;
    }

    @Override // java.util.ArrayList, java.util.AbstractList
    public void removeRange(int i, int i2) {
        super.removeRange(i, i2);
        d(i, i2 - i);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public T set(int i, T t) {
        T t2 = (T) super.set(i, t);
        h hVar = this.f923a;
        if (hVar != null) {
            hVar.j(this, 1, h.i(i, 0, 1));
        }
        return t2;
    }
}
