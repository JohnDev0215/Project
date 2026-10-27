package p;

import com.google.protobuf.ByteString;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
public final class a91 extends a2 implements b91, RandomAccess {
    public final ArrayList e;

    static {
        new a91(10).c = false;
    }

    public a91(int i) {
        this(new ArrayList(i));
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        a();
        this.e.add(i, (String) obj);
        ((AbstractList) this).modCount++;
    }

    @Override // p.a2, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        return addAll(this.e.size(), collection);
    }

    @Override // p.a2, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        a();
        this.e.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // p.v51
    public final v51 f(int i) {
        ArrayList arrayList = this.e;
        if (i < arrayList.size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList2 = new ArrayList(i);
        arrayList2.addAll(arrayList);
        return new a91(arrayList2);
    }

    @Override // p.b91
    public final void g(ByteString byteString) {
        a();
        this.e.add(byteString);
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        String str;
        ArrayList arrayList = this.e;
        Object obj = arrayList.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            str = byteString.s();
            if (byteString.i()) {
                arrayList.set(i, str);
            }
        } else {
            byte[] bArr = (byte[]) obj;
            str = new String(bArr, w51.a);
            if (com.google.protobuf.r.a.x(0, bArr.length, bArr) == 0) {
                arrayList.set(i, str);
            }
        }
        return str;
    }

    @Override // p.b91
    public final b91 j() {
        return this.c ? new rq2(this) : this;
    }

    @Override // p.b91
    public final Object k(int i) {
        return this.e.get(i);
    }

    @Override // p.b91
    public final List l() {
        return Collections.unmodifiableList(this.e);
    }

    @Override // p.a2, java.util.AbstractList, java.util.List
    public final Object remove(int i) {
        a();
        Object objRemove = this.e.remove(i);
        ((AbstractList) this).modCount++;
        return objRemove instanceof String ? (String) objRemove : objRemove instanceof ByteString ? ((ByteString) objRemove).s() : new String((byte[]) objRemove, w51.a);
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        a();
        Object obj2 = this.e.set(i, (String) obj);
        return obj2 instanceof String ? (String) obj2 : obj2 instanceof ByteString ? ((ByteString) obj2).s() : new String((byte[]) obj2, w51.a);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.e.size();
    }

    public a91(ArrayList arrayList) {
        this.e = arrayList;
    }

    @Override // p.a2, java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        a();
        if (collection instanceof b91) {
            collection = ((b91) collection).l();
        }
        boolean zAddAll = this.e.addAll(i, collection);
        ((AbstractList) this).modCount++;
        return zAddAll;
    }
}
