package p;

import com.google.protobuf.ByteString;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
public final class rq2 extends AbstractList implements b91, RandomAccess {
    public final b91 c;

    public rq2(b91 b91Var) {
        this.c = b91Var;
    }

    @Override // p.b91
    public final void g(ByteString byteString) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        return (String) this.c.get(i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new qq2(this);
    }

    @Override // p.b91
    public final b91 j() {
        return this;
    }

    @Override // p.b91
    public final Object k(int i) {
        return this.c.k(i);
    }

    @Override // p.b91
    public final List l() {
        return this.c.l();
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        return new pq2(this, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.c.size();
    }
}
