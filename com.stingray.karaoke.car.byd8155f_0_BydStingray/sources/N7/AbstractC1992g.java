package n7;

import java.util.AbstractSet;
import java.util.Set;

/* JADX INFO: renamed from: n7.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1992g extends AbstractSet implements Set, B7.f {
    public abstract int d();

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final /* bridge */ int size() {
        return d();
    }
}
