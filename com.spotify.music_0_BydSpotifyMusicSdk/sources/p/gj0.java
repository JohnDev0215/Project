package p;

import com.spotify.eventsender.eventsender.MissingTransportException;
import com.spotify.eventsender.gabo.PublishEventsResponse;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class gj0 implements nk0, pz0 {
    public final /* synthetic */ int a;
    public final /* synthetic */ uz0 b;

    public /* synthetic */ gj0(uz0 uz0Var, int i) {
        this.a = i;
        this.b = uz0Var;
    }

    @Override // p.pz0
    public final FunctionReferenceImpl a() {
        switch (this.a) {
        }
        return new FunctionReferenceImpl(1, this.b, uz0.class, "publishNonAuth", "publishNonAuth(Ljava/util/List;)Lcom/spotify/eventsender/eventsender/EventsResponse;", 0);
    }

    public final v03 b(List list) throws MissingTransportException {
        int i = this.a;
        uz0 uz0Var = this.b;
        switch (i) {
            case 1:
                v03.j(list, "p0");
                return uz0Var.d(list);
            case 2:
                v03.j(list, "p0");
                uz0Var.getClass();
                tz0 tz0Var = (tz0) uz0Var.a.getLast();
                if (tz0Var == null) {
                    throw new MissingTransportException();
                }
                u42 u42VarB = tz0Var.b(uz0Var.a(list)).b();
                int i2 = u42VarB.a.l;
                PublishEventsResponse publishEventsResponse = (PublishEventsResponse) u42VarB.b;
                if (i2 != 200 || publishEventsResponse == null) {
                    return new lk0(u4.e("Request failed with error code: ", i2));
                }
                int size = list.size();
                v51 v51VarE = publishEventsResponse.e();
                v03.i(v51VarE, "responseBody.errorList");
                Set setC = uz0.c(size, v51VarE);
                v51 v51VarE2 = publishEventsResponse.e();
                v03.i(v51VarE2, "responseBody.errorList");
                return new mk0(setC, uz0.b(v51VarE2));
            case 3:
                v03.j(list, "p0");
                return uz0Var.d(list);
            default:
                v03.j(list, "p0");
                return uz0Var.d(list);
        }
    }

    public final boolean equals(Object obj) {
        switch (this.a) {
            case 1:
                if ((obj instanceof nk0) && (obj instanceof pz0)) {
                    break;
                }
                break;
            case 2:
                if ((obj instanceof nk0) && (obj instanceof pz0)) {
                    break;
                }
                break;
            case 3:
                if ((obj instanceof nk0) && (obj instanceof pz0)) {
                    break;
                }
                break;
            default:
                if ((obj instanceof nk0) && (obj instanceof pz0)) {
                    break;
                }
                break;
        }
        return v03.b(a(), ((pz0) obj).a());
    }

    public final int hashCode() {
        switch (this.a) {
        }
        return a().hashCode();
    }
}
