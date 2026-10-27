package p;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class o7 {
    public final UUID a;
    public final tq1 b;
    public final pq1 c;
    public final List d;
    public final Map e;
    public final tk0 f;
    public final boolean g;

    public o7(UUID uuid, tq1 tq1Var, pq1 pq1Var, List list, Map map, tk0 tk0Var, boolean z) {
        this.a = uuid;
        this.b = tq1Var;
        this.c = pq1Var;
        this.d = list;
        this.e = map;
        this.f = tk0Var;
        this.g = z;
    }

    public final n7 a() {
        n7 n7Var = new n7(this.b, this.a, this.c);
        n7Var.e = this.d;
        n7Var.f = this.e;
        tk0 tk0Var = this.f;
        v03.j(tk0Var, "executionContext");
        n7Var.d = n7Var.d.d(tk0Var);
        n7Var.g = this.g;
        return n7Var;
    }
}
