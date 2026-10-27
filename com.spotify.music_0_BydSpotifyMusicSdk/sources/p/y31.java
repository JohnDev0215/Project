package p;

import com.google.common.collect.ImmutableList;

/* JADX INFO: loaded from: classes.dex */
public final class y31 extends l1 {
    public final ImmutableList k;

    public y31(ImmutableList immutableList, int i) {
        super(immutableList.size(), i);
        this.k = immutableList;
    }

    @Override // p.l1
    public final Object b(int i) {
        return this.k.get(i);
    }
}
