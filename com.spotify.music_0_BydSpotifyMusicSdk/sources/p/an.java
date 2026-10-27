package p;

import com.spotify.messages.BootstrapRequestNonAuth;

/* JADX INFO: loaded from: classes.dex */
public final class an implements zm {
    public final vi0 a;

    public an(vi0 vi0Var) {
        v03.j(vi0Var, "eventPublisher");
        this.a = vi0Var;
    }

    public final void b(long j, int i, int i2, String str, String str2, String str3, lw1 lw1Var) {
        v03.j(lw1Var, "policyInputs");
        gn gnVarM = BootstrapRequestNonAuth.m();
        gnVarM.h(j);
        gnVarM.l(i);
        gnVarM.f(str);
        gnVarM.j(i2);
        gnVarM.g("legacy");
        gnVarM.d(lw1Var.b());
        if (str2 != null) {
            gnVarM.e(str2);
        }
        if (str3 != null) {
            gnVarM.k(str3);
        }
        byte[] byteArray = ((BootstrapRequestNonAuth) gnVarM.build()).toByteArray();
        v03.i(byteArray, "it.toByteArray()");
        ((jw0) this.a).b("BootstrapRequestNonAuth", null, byteArray);
    }

    public final void c(long j, Integer num, lw1 lw1Var) {
        v03.j(lw1Var, "policyInputs");
        gn gnVarM = BootstrapRequestNonAuth.m();
        gnVarM.h(j);
        gnVarM.f("timeout");
        gnVarM.g("legacy");
        if (num != null) {
            gnVarM.l(num.intValue());
        }
        gnVarM.d(lw1Var.b());
        byte[] byteArray = ((BootstrapRequestNonAuth) gnVarM.build()).toByteArray();
        v03.i(byteArray, "it.toByteArray()");
        ((jw0) this.a).b("BootstrapRequestNonAuth", null, byteArray);
    }
}
