package p;

import com.spotify.base.java.logging.Logger;
import com.spotify.bootstrap.v1.proto.Bootstrap$BootstrapResponse;
import com.spotify.bootstrap.v1.proto.Bootstrap$UcsResponseWrapperV0;
import com.spotify.messages.BootstrapRequestNonAuth;
import java.net.SocketTimeoutException;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes.dex */
public final class en implements io.reactivex.rxjava3.functions.b {
    public final /* synthetic */ fn c;

    public en(fn fnVar) {
        this.c = fnVar;
    }

    @Override // io.reactivex.rxjava3.functions.b
    public final void accept(Object obj, Object obj2) {
        u42 u42Var = (u42) obj;
        Throwable th = (Throwable) obj2;
        fn fnVar = this.c;
        fnVar.d.getClass();
        long jCurrentTimeMillis = System.currentTimeMillis() - fnVar.g;
        zm zmVar = fnVar.e;
        nt ntVar = fnVar.a;
        if (th != null) {
            Logger.c(th, "There was an error when calling the bootstrap service.", new Object[0]);
            if (th instanceof SocketTimeoutException) {
                ((an) zmVar).c(jCurrentTimeMillis, null, ntVar.d);
                c7 c7Var = ln1.f;
                if (c7Var != null) {
                    c7Var.c("bootstrap-network-request");
                    c7Var.a("bootstrap-outcome", "timeout");
                    return;
                }
                return;
            }
            ((an) fnVar.e).b(jCurrentTimeMillis, u42Var != null ? u42Var.a.l : -1, -1, "unknown", th.getMessage(), null, ntVar.d);
            c7 c7Var2 = ln1.f;
            if (c7Var2 != null) {
                c7Var2.c("bootstrap-network-request");
                c7Var2.a("bootstrap-outcome", "error");
                return;
            }
            return;
        }
        if (u42Var != null) {
            Bootstrap$BootstrapResponse bootstrap$BootstrapResponse = (Bootstrap$BootstrapResponse) u42Var.b;
            Response response = u42Var.a;
            ResponseBody responseBody = response.o;
            int iB = responseBody != null ? (int) responseBody.b() : -1;
            int i = response.l;
            Logger.e("Bootstrap responded with code=%d and payload size=%d", Integer.valueOf(i), Integer.valueOf(iB));
            if (!response.i()) {
                Logger.b("There was an error returned from Bootstrap. code: %d", Integer.valueOf(i));
                int i2 = response.l;
                if (i2 == 504 || i2 == 408) {
                    ((an) zmVar).c(jCurrentTimeMillis, Integer.valueOf(i2), ntVar.d);
                    c7 c7Var3 = ln1.f;
                    if (c7Var3 != null) {
                        c7Var3.c("bootstrap-network-request");
                        c7Var3.a("bootstrap-outcome", "timeout");
                        return;
                    }
                    return;
                }
                String str = response.k;
                ResponseBody responseBody2 = u42Var.c;
                ((an) zmVar).b(jCurrentTimeMillis, i2, iB, "invalid_bootstrap_payload", str, responseBody2 != null ? responseBody2.q() : null, ntVar.d);
                c7 c7Var4 = ln1.f;
                if (c7Var4 != null) {
                    c7Var4.c("bootstrap-network-request");
                    c7Var4.a("bootstrap-outcome", "error");
                    return;
                }
                return;
            }
            Bootstrap$UcsResponseWrapperV0.ResultCase resultCase = Bootstrap$UcsResponseWrapperV0.ResultCase.SUCCESS;
            if (bootstrap$BootstrapResponse != null && bootstrap$BootstrapResponse.g() && bootstrap$BootstrapResponse.f().g() != resultCase) {
                ((an) fnVar.e).b(jCurrentTimeMillis, response.l, iB, "invalid_ucs_payload", bootstrap$BootstrapResponse.f().f().getMessage(), null, ntVar.d);
                c7 c7Var5 = ln1.f;
                if (c7Var5 != null) {
                    c7Var5.c("bootstrap-network-request");
                    c7Var5.a("bootstrap-outcome", "error");
                    return;
                }
                return;
            }
            if (bootstrap$BootstrapResponse != null && bootstrap$BootstrapResponse.g() && bootstrap$BootstrapResponse.f().g() == resultCase && bootstrap$BootstrapResponse.f().h().g() && bootstrap$BootstrapResponse.f().h().e().hasError()) {
                ((an) fnVar.e).b(jCurrentTimeMillis, response.l, iB, "invalid_ucs_payload", bootstrap$BootstrapResponse.f().h().e().f().getErrorMessage(), null, ntVar.d);
                c7 c7Var6 = ln1.f;
                if (c7Var6 != null) {
                    c7Var6.c("bootstrap-network-request");
                    c7Var6.a("bootstrap-outcome", "error");
                    return;
                }
                return;
            }
            lw1 lw1Var = ntVar.d;
            an anVar = (an) zmVar;
            anVar.getClass();
            v03.j(lw1Var, "policyInputs");
            gn gnVarM = BootstrapRequestNonAuth.m();
            gnVarM.h(jCurrentTimeMillis);
            gnVarM.l(i);
            gnVarM.j(iB);
            gnVarM.g("legacy");
            gnVarM.d(lw1Var.b());
            byte[] byteArray = ((BootstrapRequestNonAuth) gnVarM.build()).toByteArray();
            v03.i(byteArray, "it.toByteArray()");
            ((jw0) anVar.a).b("BootstrapRequestNonAuth", null, byteArray);
            c7 c7Var7 = ln1.f;
            if (c7Var7 != null) {
                c7Var7.c("bootstrap-network-request");
                c7Var7.a("bootstrap-outcome", "success");
            }
        }
    }
}
