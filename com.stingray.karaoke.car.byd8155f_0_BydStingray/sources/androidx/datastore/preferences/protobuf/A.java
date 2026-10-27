package androidx.datastore.preferences.protobuf;

import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
public final class A extends AbstractC0939b implements B, RandomAccess {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f11780m;

    static {
        new A(10).l = false;
    }

    public A(int i7) {
        this(new ArrayList(i7));
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i7, Object obj) {
        a();
        this.f11780m.add(i7, (String) obj);
        ((AbstractList) this).modCount++;
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0939b, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        return addAll(this.f11780m.size(), collection);
    }

    @Override // androidx.datastore.preferences.protobuf.B
    public final B b() {
        return this.l ? new m0(this) : this;
    }

    @Override // androidx.datastore.preferences.protobuf.B
    public final List c() {
        return Collections.unmodifiableList(this.f11780m);
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0939b, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        a();
        this.f11780m.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i7) {
        String str;
        ArrayList arrayList = this.f11780m;
        Object obj = arrayList.get(i7);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof C0944g) {
            C0944g c0944g = (C0944g) obj;
            c0944g.getClass();
            Charset charset = AbstractC0959w.f11890a;
            if (c0944g.size() == 0) {
                str = "";
            } else {
                str = new String(c0944g.f11845m, c0944g.q(), c0944g.size(), charset);
            }
            int iQ = c0944g.q();
            if (u0.f11889a.e(iQ, c0944g.size() + iQ, c0944g.f11845m) == 0) {
                arrayList.set(i7, str);
            }
        } else {
            byte[] bArr = (byte[]) obj;
            str = new String(bArr, AbstractC0959w.f11890a);
            d0 d0Var = u0.f11889a;
            if (u0.f11889a.e(0, bArr.length, bArr) == 0) {
                arrayList.set(i7, str);
            }
        }
        return str;
    }

    @Override // androidx.datastore.preferences.protobuf.InterfaceC0958v
    public final InterfaceC0958v h(int i7) {
        ArrayList arrayList = this.f11780m;
        if (i7 < arrayList.size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList2 = new ArrayList(i7);
        arrayList2.addAll(arrayList);
        return new A(arrayList2);
    }

    @Override // androidx.datastore.preferences.protobuf.B
    public final void j(C0944g c0944g) {
        a();
        this.f11780m.add(c0944g);
        ((AbstractList) this).modCount++;
    }

    @Override // androidx.datastore.preferences.protobuf.B
    public final Object n(int i7) {
        return this.f11780m.get(i7);
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0939b, java.util.AbstractList, java.util.List
    public final Object remove(int i7) {
        String str;
        a();
        Object objRemove = this.f11780m.remove(i7);
        ((AbstractList) this).modCount++;
        if (objRemove instanceof String) {
            return (String) objRemove;
        }
        if (objRemove instanceof C0944g) {
            C0944g c0944g = (C0944g) objRemove;
            c0944g.getClass();
            Charset charset = AbstractC0959w.f11890a;
            if (c0944g.size() != 0) {
                return new String(c0944g.f11845m, c0944g.q(), c0944g.size(), charset);
            }
            str = "";
        } else {
            str = new String((byte[]) objRemove, AbstractC0959w.f11890a);
        }
        return str;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i7, Object obj) {
        a();
        Object obj2 = this.f11780m.set(i7, (String) obj);
        if (obj2 instanceof String) {
            return (String) obj2;
        }
        if (!(obj2 instanceof C0944g)) {
            return new String((byte[]) obj2, AbstractC0959w.f11890a);
        }
        C0944g c0944g = (C0944g) obj2;
        c0944g.getClass();
        Charset charset = AbstractC0959w.f11890a;
        if (c0944g.size() == 0) {
            return "";
        }
        return new String(c0944g.f11845m, c0944g.q(), c0944g.size(), charset);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f11780m.size();
    }

    public A(ArrayList arrayList) {
        this.f11780m = arrayList;
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0939b, java.util.AbstractList, java.util.List
    public final boolean addAll(int i7, Collection collection) {
        a();
        if (collection instanceof B) {
            collection = ((B) collection).c();
        }
        boolean zAddAll = this.f11780m.addAll(i7, collection);
        ((AbstractList) this).modCount++;
        return zAddAll;
    }
}
