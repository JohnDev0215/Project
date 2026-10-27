package p;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class n7 {
    public final tq1 a;
    public UUID b;
    public final pq1 c;
    public tk0 d;
    public List e;
    public Map f;
    public boolean g;

    public n7(tq1 tq1Var, UUID uuid, pq1 pq1Var) {
        v03.j(tq1Var, "operation");
        v03.j(uuid, "requestUuid");
        this.a = tq1Var;
        this.b = uuid;
        this.c = pq1Var;
        this.d = td0.b;
    }

    public final o7 a() {
        tq1 tq1Var = this.a;
        UUID uuid = this.b;
        pq1 pq1Var = this.c;
        tk0 tk0Var = this.d;
        Map mapH0 = this.f;
        if (mapH0 == null) {
            mapH0 = kotlin.collections.c.h0();
        }
        return new o7(uuid, tq1Var, pq1Var, this.e, mapH0, tk0Var, this.g);
    }
}
