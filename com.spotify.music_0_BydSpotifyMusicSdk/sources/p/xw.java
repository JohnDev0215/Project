package p;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class xw {
    public final cg2 a;
    public m02 b;
    public final AtomicBoolean c = new AtomicBoolean(false);
    public Long d;

    public xw(cg2 cg2Var) {
        this.a = cg2Var;
    }

    public final synchronized m02 a(String str) {
        m02 m02VarU;
        try {
            if (!this.c.get()) {
                qk2.a().getClass();
                hk0.y(new Object[0]);
            }
            m02VarU = this.b;
            if (m02VarU == null) {
                m02 m02VarB = ((eg2) this.a).c.b();
                m02 m02VarB2 = ((eg2) this.a).d.b();
                v03.j(m02VarB, "fetchedConfig");
                v03.j(m02VarB2, "overriddenConfig");
                Map map = m02VarB.b;
                v03.j(map, "<this>");
                Map map2 = m02VarB2.b;
                v03.j(map2, "map");
                LinkedHashMap linkedHashMap = new LinkedHashMap(map);
                linkedHashMap.putAll(map2);
                w01 w01VarA = w01.a(m02VarB.a, ou.n0(linkedHashMap.values()), null, 27);
                m02 m02Var = m02.c;
                m02VarU = zb0.u(w01VarA);
                this.b = m02VarU;
                this.d = Long.valueOf(m02VarU.a.d);
            }
        } catch (Throwable th) {
            throw th;
        }
        return m02VarU;
    }
}
