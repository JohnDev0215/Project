package h5;

import java.util.AbstractCollection;

/* JADX INFO: compiled from: AbstractMutableCollection.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class c<E> extends AbstractCollection<E> implements s5.a {
    protected c() {
    }

    public abstract int c();

    @Override // java.util.AbstractCollection, java.util.Collection
    public final /* bridge */ int size() {
        return c();
    }
}
