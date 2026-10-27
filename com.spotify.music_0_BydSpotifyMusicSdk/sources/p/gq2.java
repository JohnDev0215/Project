package p;

import com.spotify.rcs.resolver.grpc.v0.Configuration;
import com.spotify.rcs.resolver.grpc.v0.ResolveResponse;
import com.spotify.remoteconfig.fetcher.FetchType;
import okhttp3.Response;

/* JADX INFO: loaded from: classes.dex */
public final class gq2 implements io.reactivex.rxjava3.functions.g {
    public final /* synthetic */ int c;
    public final /* synthetic */ hq2 e;
    public final /* synthetic */ FetchType k;

    public /* synthetic */ gq2(hq2 hq2Var, int i) {
        FetchType fetchType = FetchType.ASYNC;
        this.c = i;
        this.e = hq2Var;
        this.k = fetchType;
    }

    @Override // io.reactivex.rxjava3.functions.g
    public final void accept(Object obj) {
        Response response;
        int i = this.c;
        FetchType fetchType = this.k;
        hq2 hq2Var = this.e;
        switch (i) {
            case 0:
                u42 u42Var = (u42) obj;
                bx bxVar = hq2Var.g;
                v03.i(u42Var, "it");
                hq2Var.c.getClass();
                long jCurrentTimeMillis = System.currentTimeMillis() - hq2Var.f.get();
                bxVar.getClass();
                v03.j(fetchType, "fetchType");
                hk0 hk0VarA = qk2.a();
                Object[] objArr = new Object[2];
                objArr[0] = Long.valueOf(jCurrentTimeMillis);
                Response response2 = u42Var.a;
                objArr[1] = Integer.valueOf((response2 == null || (response = response2.f96p) == null) ? -1 : response.l);
                hk0VarA.getClass();
                hk0.t(objArr);
                ResolveResponse resolveResponse = (ResolveResponse) u42Var.b;
                if (!response2.i() || resolveResponse == null || resolveResponse.f() == null) {
                    bxVar.a(jCurrentTimeMillis, fetchType, u42Var);
                } else {
                    m02 m02VarB = ((eg2) bxVar.a).c.b();
                    m02 m02Var = m02.c;
                    Configuration configurationF = resolveResponse.f();
                    v03.i(configurationF, "responseBody.configuration");
                    boolean zB = v03.b(m02VarB.a(), zb0.u(v01.b(configurationF)).a());
                    if (zB) {
                        qk2.a().getClass();
                        hk0.y(new Object[0]);
                    }
                    ki0 ki0Var = bxVar.b;
                    String strName = fetchType.name();
                    Response response3 = response2.f96p;
                    int i2 = response3 != null ? response3.l : -1;
                    int serializedSize = resolveResponse.getSerializedSize();
                    String strF = resolveResponse.f().f();
                    v03.i(strF, "responseBody.configurati…configurationAssignmentId");
                    ((d51) ki0Var).d(strName, i2, jCurrentTimeMillis, serializedSize, strF, Long.valueOf(resolveResponse.f().h()), null, "unnecessaryFetch=" + zB);
                }
                break;
            default:
                Throwable th = (Throwable) obj;
                bx bxVar2 = hq2Var.g;
                v03.i(th, "it");
                hq2Var.c.getClass();
                bxVar2.c(th, fetchType, System.currentTimeMillis() - hq2Var.f.get());
                break;
        }
    }
}
