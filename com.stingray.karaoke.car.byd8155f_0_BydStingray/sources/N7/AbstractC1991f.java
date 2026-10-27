package n7;

import java.util.AbstractList;
import java.util.List;

/* JADX INFO: renamed from: n7.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1991f extends AbstractList implements List, B7.c {
    public abstract int d();

    public abstract Object l(int i7);

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ Object remove(int i7) {
        return l(i7);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return d();
    }
}
