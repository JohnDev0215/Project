package androidx.databinding;

import androidx.databinding.m;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: compiled from: ObservableArrayList.java */
/* JADX INFO: loaded from: classes.dex */
public class k<T> extends ArrayList<T> implements m<T> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private transient i f2077e = new i();

    private void c(int i7, int i8) {
        i iVar = this.f2077e;
        if (iVar != null) {
            iVar.p(this, i7, i8);
        }
    }

    private void d(int i7, int i8) {
        i iVar = this.f2077e;
        if (iVar != null) {
            iVar.q(this, i7, i8);
        }
    }

    @Override // androidx.databinding.m
    public void a(m.a aVar) {
        i iVar = this.f2077e;
        if (iVar != null) {
            iVar.i(aVar);
        }
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(T t6) {
        super.add(t6);
        c(size() - 1, 1);
        return true;
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

    @Override // androidx.databinding.m
    public void b(m.a aVar) {
        if (this.f2077e == null) {
            this.f2077e = new i();
        }
        this.f2077e.a(aVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        int size = size();
        super.clear();
        if (size != 0) {
            d(0, size);
        }
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public T remove(int i7) {
        T t6 = (T) super.remove(i7);
        d(i7, 1);
        return t6;
    }

    @Override // java.util.ArrayList, java.util.AbstractList
    protected void removeRange(int i7, int i8) {
        super.removeRange(i7, i8);
        d(i7, i8 - i7);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public T set(int i7, T t6) {
        T t7 = (T) super.set(i7, t6);
        i iVar = this.f2077e;
        if (iVar != null) {
            iVar.o(this, i7, 1);
        }
        return t7;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public void add(int i7, T t6) {
        super.add(i7, t6);
        c(i7, 1);
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

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public boolean addAll(int i7, Collection<? extends T> collection) {
        boolean zAddAll = super.addAll(i7, collection);
        if (zAddAll) {
            c(i7, collection.size());
        }
        return zAddAll;
    }
}
