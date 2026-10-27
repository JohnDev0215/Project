package h5;

import java.util.AbstractList;

/* JADX INFO: compiled from: AbstractMutableList.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class d<E> extends AbstractList<E> implements s5.b {
    protected d() {
    }

    public abstract int c();

    public abstract E d(int i7);

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ E remove(int i7) {
        return d(i7);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return c();
    }
}
