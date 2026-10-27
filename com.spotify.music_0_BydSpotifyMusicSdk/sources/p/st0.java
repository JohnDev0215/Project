package p;

import com.spotify.rcs.resolver.grpc.v0.Configuration;
import com.spotify.ucs.proto.v0.UcsResponseWrapper;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Single;
import java.util.LinkedHashMap;
import java.util.Map;
import okhttp3.Response;

/* JADX INFO: loaded from: classes.dex */
public final class st0 implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ boolean c;
    public final /* synthetic */ com.spotify.remoteconfig.fetcherimpl.b e;
    public final /* synthetic */ ut0 k;

    public st0(boolean z, com.spotify.remoteconfig.fetcherimpl.b bVar, ut0 ut0Var) {
        this.c = z;
        this.e = bVar;
        this.k = ut0Var;
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        CompletableSource eVar;
        CompletableSource completableSourceA;
        u42 u42Var = (u42) obj;
        UcsResponseWrapper ucsResponseWrapper = (UcsResponseWrapper) u42Var.b;
        Response response = u42Var.a;
        int i = response.l;
        dt0 dt0Var = dt0.j;
        if (i == 304) {
            return Single.just(dt0Var);
        }
        boolean zI = response.i();
        dt0 dt0Var2 = dt0.i;
        if (!zI || ucsResponseWrapper == null || ucsResponseWrapper.f() != UcsResponseWrapper.ResultCase.SUCCESS) {
            return Single.just(dt0Var2);
        }
        int i2 = 1;
        boolean z = this.c || this.e.a();
        CompletableSource[] completableSourceArr = new CompletableSource[2];
        UcsResponseWrapper.UcsResponse ucsResponseG = ucsResponseWrapper.g();
        v03.i(ucsResponseG, "body.success");
        ut0 ut0Var = this.k;
        ut0Var.getClass();
        if (ucsResponseG.f() == UcsResponseWrapper.UcsResponse.AccountAttributesResultCase.ACCOUNT_ATTRIBUTES_SUCCESS) {
            Map mapE = ucsResponseG.g().e();
            v03.i(mapE, "ucsResponse.accountAttri…cess.accountAttributesMap");
            LinkedHashMap linkedHashMapA = zx1.a(mapE);
            if (z) {
                linkedHashMapA.isEmpty();
            }
            if (!linkedHashMapA.isEmpty()) {
                qk2.a().getClass();
                hk0.t(new Object[0]);
                eVar = new io.reactivex.rxjava3.internal.operators.completable.e(2, new tt0(ut0Var, ucsResponseG));
            } else {
                eVar = io.reactivex.rxjava3.internal.operators.completable.d.c;
            }
            v03.i(eVar, "{\n            val produc…)\n            }\n        }");
        } else {
            eVar = io.reactivex.rxjava3.internal.operators.completable.d.c;
            v03.i(eVar, "{\n            Completable.complete()\n        }");
        }
        completableSourceArr[0] = eVar;
        UcsResponseWrapper.UcsResponse ucsResponseG2 = ucsResponseWrapper.g();
        v03.i(ucsResponseG2, "body.success");
        String strA = response.n.a("etag");
        if (strA == null) {
            strA = "";
        }
        if (ucsResponseG2.m() && ucsResponseG2.k() == UcsResponseWrapper.UcsResponse.ResolveResultCase.RESOLVE_SUCCESS && ucsResponseG2.l().h() && ucsResponseG2.l().f() != null) {
            Configuration configurationF = ucsResponseG2.l().f();
            v03.i(configurationF, "ucsResponse.resolveSuccess.configuration");
            completableSourceA = ut0Var.a(configurationF, strA);
        } else {
            hk0 hk0VarA = qk2.a();
            ucsResponseG2.j().getErrorCode();
            ucsResponseG2.j().getErrorMessage();
            hk0VarA.getClass();
            hk0.B(new Object[0]);
            completableSourceA = io.reactivex.rxjava3.internal.operators.completable.d.c;
            v03.i(completableSourceA, "{\n            Timber.tag…able.complete()\n        }");
        }
        completableSourceArr[1] = completableSourceA;
        io.reactivex.rxjava3.internal.operators.completable.c cVar = new io.reactivex.rxjava3.internal.operators.completable.c(completableSourceArr, i2);
        UcsResponseWrapper.UcsResponse ucsResponseG3 = ucsResponseWrapper.g();
        v03.i(ucsResponseG3, "body.success");
        if (ucsResponseG3.k() == UcsResponseWrapper.UcsResponse.ResolveResultCase.RESOLVE_ERROR || ucsResponseG3.f() == UcsResponseWrapper.UcsResponse.AccountAttributesResultCase.ACCOUNT_ATTRIBUTES_ERROR) {
            dt0Var = dt0Var2;
        }
        return cVar.e(dt0Var);
    }
}
