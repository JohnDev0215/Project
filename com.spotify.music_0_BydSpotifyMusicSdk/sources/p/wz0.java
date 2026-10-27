package p;

import com.spotify.messages.TimeMeasurementErrorNonAuth;
import com.spotify.messages.TimeMeasurementNonAuth;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class wz0 implements pl2 {
    public final wi0 a;
    public final boolean b;

    public wz0(wi0 wi0Var) {
        v03.j(wi0Var, "_eventPublisher");
        this.a = wi0Var;
        this.b = true;
    }

    @Override // p.pl2
    public final void a(vk2 vk2Var) {
        wi0 wi0Var;
        v03.j(vk2Var, "measurement");
        wi0 wi0Var2 = this.a;
        String str = vk2Var.f;
        Map map = vk2Var.c;
        Map map2 = vk2Var.d;
        String str2 = vk2Var.b;
        UUID uuid = vk2Var.a;
        Set set = vk2Var.e;
        xk2 xk2Var = vk2Var.i;
        if (xk2Var == null) {
            wi0Var = this.b ? wi0Var2 : null;
            if (wi0Var != null) {
                q22 q22VarG = v03.G(set);
                hl2 hl2VarM = TimeMeasurementNonAuth.m();
                hl2VarM.l(uuid.toString());
                hl2VarM.j(str2);
                hl2VarM.d(map2);
                hl2VarM.e(map);
                hl2VarM.h(q22VarG.a);
                hl2VarM.f(q22VarG.b);
                hl2VarM.g(q22VarG.c);
                if (str != null) {
                    hl2VarM.k(str);
                }
                com.google.protobuf.i iVarBuild = hl2VarM.build();
                v03.i(iVarBuild, "builder.build()");
                wi0Var.a((TimeMeasurementNonAuth) iVarBuild);
                return;
            }
            return;
        }
        wi0Var = this.b ? wi0Var2 : null;
        if (wi0Var != null) {
            q22 q22VarG2 = v03.G(set);
            yk2 yk2VarS = TimeMeasurementErrorNonAuth.s();
            yk2VarS.p(uuid.toString());
            yk2VarS.m(str2);
            yk2VarS.d(map2);
            yk2VarS.f(map);
            yk2VarS.l(q22VarG2.a);
            yk2VarS.j(q22VarG2.b);
            yk2VarS.k(q22VarG2.c);
            if (str != null) {
                yk2VarS.o(str);
            }
            Long l = vk2Var.h;
            if (l != null) {
                yk2VarS.r(l.longValue());
            }
            UUID uuid2 = vk2Var.g;
            if (uuid2 != null) {
                yk2VarS.s(uuid2.toString());
            }
            yk2VarS.n(xk2Var.a.a());
            yk2VarS.e(xk2Var.b);
            q22 q22VarG3 = v03.G(xk2Var.c);
            yk2VarS.h(q22VarG3.a);
            yk2VarS.g(q22VarG3.c);
            com.google.protobuf.i iVarBuild2 = yk2VarS.build();
            v03.i(iVarBuild2, "builder.build()");
            wi0Var.a((TimeMeasurementErrorNonAuth) iVarBuild2);
        }
    }
}
