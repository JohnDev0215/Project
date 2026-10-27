package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class e70 implements k7 {
    public final List a;
    public final int b;

    public e70(int i, List list) {
        v03.j(list, "interceptors");
        this.a = list;
        this.b = i;
    }

    public final dv0 a(m7 m7Var) {
        v03.j(m7Var, "request");
        List list = this.a;
        int size = list.size();
        int i = this.b;
        if (i < size) {
            return ((j7) list.get(i)).a(m7Var, new e70(i + 1, list));
        }
        throw new IllegalStateException("Check failed.".toString());
    }
}
