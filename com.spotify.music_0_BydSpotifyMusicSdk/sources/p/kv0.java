package p;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class kv0 extends com.google.common.collect.a {
    public final /* synthetic */ Iterable e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public kv0(Iterable iterable, Iterable iterable2) {
        super(iterable);
        this.e = iterable2;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.e.iterator();
    }
}
