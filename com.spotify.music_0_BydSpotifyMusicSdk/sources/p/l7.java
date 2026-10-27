package p;

import com.apollographql.apollo3.api.http.HttpMethod;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class l7 {
    public final tq1 a;
    public UUID b;
    public tk0 c;
    public HttpMethod d;
    public List e;
    public Boolean f;
    public Boolean g;
    public Boolean h;
    public Boolean i;

    public l7(tq1 tq1Var) {
        v03.j(tq1Var, "operation");
        this.a = tq1Var;
        UUID uuidRandomUUID = UUID.randomUUID();
        v03.i(uuidRandomUUID, "randomUUID()");
        this.b = uuidRandomUUID;
        this.c = td0.b;
    }

    public final m7 a() {
        return new m7(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i);
    }
}
