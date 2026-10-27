package p;

import com.spotify.rcs.resolver.grpc.v0.Configuration;
import com.spotify.rcs.resolver.grpc.v0.ResolveResponse;
import com.spotify.remoteconfig.commons.logging.EventLogger$FetchErrorReason;
import com.spotify.remoteconfig.fetcher.FetchType;
import com.spotify.ucs.proto.v0.UcsResponseWrapper;
import java.util.Map;
import kotlin.Pair;
import kotlin.Triple;
import okhttp3.Response;

/* JADX INFO: loaded from: classes.dex */
public final class we implements io.reactivex.rxjava3.functions.g {
    public final /* synthetic */ int c;
    public final /* synthetic */ com.spotify.remoteconfig.fetcherimpl.a e;
    public final /* synthetic */ FetchType k;

    public /* synthetic */ we(com.spotify.remoteconfig.fetcherimpl.a aVar, int i) {
        FetchType fetchType = FetchType.ASYNC;
        this.c = i;
        this.e = aVar;
        this.k = fetchType;
    }

    @Override // io.reactivex.rxjava3.functions.g
    public final void accept(Object obj) {
        Pair pair;
        Triple triple;
        UcsResponseWrapper.AccountAttributesResponse accountAttributesResponseG;
        Map mapE;
        ResolveResponse resolveResponseL;
        Response response;
        int i = this.c;
        FetchType fetchType = this.k;
        com.spotify.remoteconfig.fetcherimpl.a aVar = this.e;
        switch (i) {
            case 0:
                u42 u42Var = (u42) obj;
                bx bxVar = aVar.g;
                v03.i(u42Var, "it");
                aVar.c.getClass();
                long jCurrentTimeMillis = System.currentTimeMillis() - aVar.h.get();
                bxVar.getClass();
                v03.j(fetchType, "fetchType");
                hk0 hk0VarA = qk2.a();
                Object[] objArr = new Object[2];
                objArr[0] = Long.valueOf(jCurrentTimeMillis);
                Response response2 = u42Var.a;
                objArr[1] = Integer.valueOf((response2 == null || (response = response2.f96p) == null) ? -1 : response.l);
                hk0VarA.getClass();
                hk0.t(objArr);
                hk0 hk0VarA2 = qk2.a();
                Object obj2 = u42Var.b;
                hk0VarA2.getClass();
                hk0.A(obj2);
                if (response2.l != 304) {
                    UcsResponseWrapper ucsResponseWrapper = (UcsResponseWrapper) obj2;
                    if (response2.i() && ucsResponseWrapper != null) {
                        UcsResponseWrapper.ResultCase resultCaseF = ucsResponseWrapper.f();
                        UcsResponseWrapper.ResultCase resultCase = UcsResponseWrapper.ResultCase.ERROR;
                        UcsResponseWrapper.UcsResponse.AccountAttributesResultCase accountAttributesResultCase = UcsResponseWrapper.UcsResponse.AccountAttributesResultCase.ACCOUNT_ATTRIBUTES_ERROR;
                        UcsResponseWrapper.UcsResponse.ResolveResultCase resolveResultCase = UcsResponseWrapper.UcsResponse.ResolveResultCase.RESOLVE_ERROR;
                        ki0 ki0Var = bxVar.b;
                        Boolean boolValueOf = null;
                        if (resultCaseF != resultCase) {
                            UcsResponseWrapper.UcsResponse ucsResponseG = ucsResponseWrapper.g();
                            if ((ucsResponseG != null ? ucsResponseG.k() : null) != resolveResultCase) {
                                UcsResponseWrapper.UcsResponse ucsResponseG2 = ucsResponseWrapper.g();
                                if ((ucsResponseG2 != null ? ucsResponseG2.f() : null) != accountAttributesResultCase) {
                                    UcsResponseWrapper.UcsResponse ucsResponseG3 = ucsResponseWrapper.g();
                                    Configuration configurationF = (ucsResponseG3 == null || (resolveResponseL = ucsResponseG3.l()) == null) ? null : resolveResponseL.f();
                                    String strF = configurationF != null ? configurationF.f() : null;
                                    if (strF == null) {
                                        strF = "";
                                    }
                                    UcsResponseWrapper.UcsResponse ucsResponseG4 = ucsResponseWrapper.g();
                                    Long lValueOf = ucsResponseG4 != null ? Long.valueOf(ucsResponseG4.i()) : null;
                                    int serializedSize = ucsResponseWrapper.getSerializedSize();
                                    UcsResponseWrapper.UcsResponse ucsResponseG5 = ucsResponseWrapper.g();
                                    int size = (ucsResponseG5 == null || (accountAttributesResponseG = ucsResponseG5.g()) == null || (mapE = accountAttributesResponseG.e()) == null) ? -1 : zx1.a(mapE).size();
                                    if (configurationF != null) {
                                        m02 m02VarB = ((eg2) bxVar.a).c.b();
                                        m02 m02Var = m02.c;
                                        boolean zB = v03.b(m02VarB.a(), zb0.u(v01.b(configurationF)).a());
                                        if (zB) {
                                            qk2.a().getClass();
                                            hk0.y(new Object[0]);
                                        }
                                        boolValueOf = Boolean.valueOf(zB);
                                    }
                                    String strName = fetchType.name();
                                    Response response3 = response2.f96p;
                                    int i2 = response3 != null ? response3.l : -1;
                                    ((d51) ki0Var).d(strName, i2, jCurrentTimeMillis, serializedSize, strF, lValueOf, Integer.valueOf(size), "unnecessaryFetch=" + boolValueOf);
                                }
                            }
                        }
                        if (ucsResponseWrapper.f() == resultCase) {
                            UcsResponseWrapper.Error errorE = ucsResponseWrapper.e();
                            pair = new Pair(errorE != null ? l13.p(errorE.getErrorCode()) : null, ucsResponseWrapper.e().getErrorMessage());
                        } else {
                            pair = new Pair(null, null);
                        }
                        EventLogger$FetchErrorReason eventLogger$FetchErrorReason = (EventLogger$FetchErrorReason) pair.a();
                        String str = (String) pair.b();
                        UcsResponseWrapper.UcsResponse ucsResponseG6 = ucsResponseWrapper.g();
                        UcsResponseWrapper.UcsResponse.ResolveResultCase resolveResultCaseK = ucsResponseG6 != null ? ucsResponseG6.k() : null;
                        Triple triple2 = bxVar.c;
                        if (resolveResultCaseK == resolveResultCase) {
                            UcsResponseWrapper.Error errorJ = ucsResponseG6.j();
                            Integer numValueOf = errorJ != null ? Integer.valueOf(errorJ.getErrorCode()) : null;
                            UcsResponseWrapper.Error errorJ2 = ucsResponseG6.j();
                            EventLogger$FetchErrorReason eventLogger$FetchErrorReasonP = errorJ2 != null ? l13.p(errorJ2.getErrorCode()) : null;
                            UcsResponseWrapper.Error errorJ3 = ucsResponseG6.j();
                            triple = new Triple(numValueOf, eventLogger$FetchErrorReasonP, errorJ3 != null ? errorJ3.getErrorMessage() : null);
                        } else {
                            triple = triple2;
                        }
                        Integer num = (Integer) triple.a();
                        EventLogger$FetchErrorReason eventLogger$FetchErrorReason2 = (EventLogger$FetchErrorReason) triple.b();
                        String str2 = (String) triple.c();
                        if ((ucsResponseG6 != null ? ucsResponseG6.f() : null) == accountAttributesResultCase) {
                            UcsResponseWrapper.Error errorE2 = ucsResponseG6.e();
                            Integer numValueOf2 = errorE2 != null ? Integer.valueOf(errorE2.getErrorCode()) : null;
                            UcsResponseWrapper.Error errorE3 = ucsResponseG6.e();
                            EventLogger$FetchErrorReason eventLogger$FetchErrorReasonP2 = errorE3 != null ? l13.p(errorE3.getErrorCode()) : null;
                            UcsResponseWrapper.Error errorE4 = ucsResponseG6.e();
                            triple2 = new Triple(numValueOf2, eventLogger$FetchErrorReasonP2, errorE4 != null ? errorE4.getErrorMessage() : null);
                        }
                        ((d51) ki0Var).c(fetchType.name(), jCurrentTimeMillis, new ji0(eventLogger$FetchErrorReason, str, eventLogger$FetchErrorReason2, num, str2, (EventLogger$FetchErrorReason) triple2.b(), (Integer) triple2.a(), (String) triple2.c()), ucsResponseWrapper.e().getErrorCode());
                    } else {
                        bxVar.b(jCurrentTimeMillis, fetchType, u42Var);
                    }
                } else {
                    ((d51) bxVar.b).d(fetchType.name(), 304, jCurrentTimeMillis, 0, "", 0L, null, "unnecessaryFetch=true");
                }
                break;
            default:
                Throwable th = (Throwable) obj;
                bx bxVar2 = aVar.g;
                v03.i(th, "it");
                aVar.c.getClass();
                bxVar2.c(th, fetchType, System.currentTimeMillis() - aVar.h.get());
                break;
        }
    }
}
