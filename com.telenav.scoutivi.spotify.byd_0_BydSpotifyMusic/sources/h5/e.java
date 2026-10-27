package h5;

import java.util.AbstractSet;

/* JADX INFO: compiled from: AbstractMutableSet.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class e<E> extends AbstractSet<E> implements s5.a {
    protected e() {
    }

    public abstract int c();

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final /* bridge */ int size() {
        return c();
    }
}
