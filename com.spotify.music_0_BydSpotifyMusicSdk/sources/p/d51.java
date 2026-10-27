package p;

import com.spotify.messages.ConfigurationFetched;
import com.spotify.messages.ConfigurationFetchedNonAuth;
import com.spotify.remoteconfig.commons.logging.EventLogger$FetchErrorReason;

/* JADX INFO: loaded from: classes.dex */
public final class d51 implements ki0 {
    public final /* synthetic */ int a;
    public final ui0 b;
    public final xa c;
    public final lw1 d;

    public d51(ui0 ui0Var, xa xaVar, lw1 lw1Var, int i) {
        this.a = i;
        if (i != 1) {
            v03.j(xaVar, "appMetadata");
            v03.j(lw1Var, "policyInputs");
            this.b = ui0Var;
            this.c = xaVar;
            this.d = lw1Var;
            return;
        }
        v03.j(xaVar, "appMetadata");
        v03.j(lw1Var, "policyInputs");
        this.b = ui0Var;
        this.c = xaVar;
        this.d = lw1Var;
    }

    public final void c(String str, long j, ji0 ji0Var, int i) {
        String str2 = ji0Var.h;
        Integer num = ji0Var.g;
        EventLogger$FetchErrorReason eventLogger$FetchErrorReason = ji0Var.f;
        String str3 = ji0Var.e;
        Integer num2 = ji0Var.d;
        EventLogger$FetchErrorReason eventLogger$FetchErrorReason2 = ji0Var.c;
        String str4 = ji0Var.b;
        EventLogger$FetchErrorReason eventLogger$FetchErrorReason3 = ji0Var.a;
        int i2 = this.a;
        ui0 ui0Var = this.b;
        switch (i2) {
            case 0:
                v03.j(str, "fetchType");
                zw zwVarE = e(str, j);
                zwVarE.z(i);
                if (eventLogger$FetchErrorReason3 != null) {
                    zwVarE.l(eventLogger$FetchErrorReason3.a());
                }
                if (str4 != null) {
                    zwVarE.h(str4);
                }
                if (eventLogger$FetchErrorReason2 != null) {
                    zwVarE.n(eventLogger$FetchErrorReason2.a());
                }
                if (num2 != null) {
                    zwVarE.g(num2.intValue());
                }
                if (str3 != null) {
                    zwVarE.k(str3);
                }
                if (eventLogger$FetchErrorReason != null) {
                    zwVarE.m(eventLogger$FetchErrorReason.a());
                }
                if (num != null) {
                    zwVarE.f(num.intValue());
                }
                if (str2 != null) {
                    zwVarE.j(str2);
                }
                byte[] byteArray = ((ConfigurationFetched) zwVarE.build()).toByteArray();
                v03.i(byteArray, "toByteArray()");
                ((jw0) ((kj0) ui0Var).a).b("ConfigurationFetched", null, byteArray);
                break;
            default:
                v03.j(str, "fetchType");
                cx cxVarF = f(str, j);
                cxVarF.y(i);
                if (eventLogger$FetchErrorReason3 != null) {
                    cxVarF.l(eventLogger$FetchErrorReason3.a());
                }
                if (str4 != null) {
                    cxVarF.h(str4);
                }
                if (eventLogger$FetchErrorReason2 != null) {
                    cxVarF.n(eventLogger$FetchErrorReason2.a());
                }
                if (num2 != null) {
                    cxVarF.g(num2.intValue());
                }
                if (str3 != null) {
                    cxVarF.k(str3);
                }
                if (eventLogger$FetchErrorReason != null) {
                    cxVarF.m(eventLogger$FetchErrorReason.a());
                }
                if (num != null) {
                    cxVarF.f(num.intValue());
                }
                if (str2 != null) {
                    cxVarF.j(str2);
                }
                byte[] byteArray2 = ((ConfigurationFetchedNonAuth) cxVarF.build()).toByteArray();
                v03.i(byteArray2, "toByteArray()");
                ((jw0) ((kj0) ui0Var).a).b("ConfigurationFetchedNonAuth", null, byteArray2);
                break;
        }
    }

    public final void d(String str, int i, long j, int i2, String str2, Long l, Integer num, String str3) {
        int i3 = this.a;
        ui0 ui0Var = this.b;
        switch (i3) {
            case 0:
                v03.j(str, "fetchType");
                long jLongValue = (l == null || l.longValue() == 0) ? -1L : l.longValue();
                zw zwVarE = e(str, j);
                zwVarE.e(str2);
                zwVarE.s(i2);
                zwVarE.z(i);
                zwVarE.p(jLongValue);
                if (num != null) {
                    zwVarE.u(num.intValue());
                }
                if (str3 != null) {
                    zwVarE.y(str3);
                }
                byte[] byteArray = ((ConfigurationFetched) zwVarE.build()).toByteArray();
                v03.i(byteArray, "toByteArray()");
                ((jw0) ((kj0) ui0Var).a).b("ConfigurationFetched", null, byteArray);
                break;
            default:
                v03.j(str, "fetchType");
                long jLongValue2 = (l == null || l.longValue() == 0) ? -1L : l.longValue();
                cx cxVarF = f(str, j);
                cxVarF.e(str2);
                cxVarF.s(i2);
                cxVarF.y(i);
                cxVarF.p(jLongValue2);
                if (str3 != null) {
                    cxVarF.x(str3);
                }
                byte[] byteArray2 = ((ConfigurationFetchedNonAuth) cxVarF.build()).toByteArray();
                v03.i(byteArray2, "toByteArray()");
                ((jw0) ((kj0) ui0Var).a).b("ConfigurationFetchedNonAuth", null, byteArray2);
                break;
        }
    }

    public final zw e(String str, long j) {
        zw zwVarZ = ConfigurationFetched.z();
        zwVarZ.o(str);
        zwVarZ.d(this.d.b());
        zwVarZ.r(j);
        this.c.getClass();
        zwVarZ.t();
        zwVarZ.w();
        zwVarZ.x();
        zwVarZ.v();
        zwVarZ.y("N/A");
        return zwVarZ;
    }

    public final cx f(String str, long j) {
        cx cxVarY = ConfigurationFetchedNonAuth.y();
        cxVarY.o(str);
        cxVarY.d(this.d.b());
        cxVarY.r(j);
        this.c.getClass();
        cxVarY.t();
        cxVarY.v();
        cxVarY.w();
        cxVarY.u();
        cxVarY.x("N/A");
        return cxVarY;
    }
}
