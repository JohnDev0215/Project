package p;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class i41 implements Iterable, g81 {
    public final /* synthetic */ int c = 1;
    public final Object e;

    public i41(b90 b90Var) {
        this.e = b90Var;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                return new j41((Iterator) ((py0) obj).invoke());
            default:
                return ((jb2) obj).iterator();
        }
    }

    public i41(py0 py0Var) {
        this.e = py0Var;
    }
}
