package p;

import com.apollographql.apollo3.api.http.HttpMethod;
import java.util.List;
import java.util.UUID;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final class m7 {
    public final tq1 a;
    public final UUID b;
    public final tk0 c;
    public final HttpMethod d;
    public final List e;
    public final Boolean f;
    public final Boolean g;
    public final Boolean h;
    public final Boolean i;

    public m7(tq1 tq1Var, UUID uuid, tk0 tk0Var, HttpMethod httpMethod, List list, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4) {
        this.a = tq1Var;
        this.b = uuid;
        this.c = tk0Var;
        this.d = httpMethod;
        this.e = list;
        this.f = bool;
        this.g = bool2;
        this.h = bool3;
        this.i = bool4;
    }

    public final l7 a() {
        tq1 tq1Var = this.a;
        v03.j(tq1Var, "operation");
        l7 l7Var = new l7(tq1Var);
        UUID uuid = this.b;
        v03.j(uuid, "requestUuid");
        l7Var.b = uuid;
        tk0 tk0Var = this.c;
        v03.j(tk0Var, "executionContext");
        l7Var.c = tk0Var;
        l7Var.d = this.d;
        l7Var.e = this.e;
        l7Var.f = this.f;
        l7Var.g = this.g;
        l7Var.h = this.h;
        Boolean bool = this.i;
        l7Var.i = bool;
        if (bool != null) {
            String string = bool.toString();
            v03.j(string, "value");
            List list = l7Var.e;
            if (list == null) {
                list = EmptyList.c;
            }
            l7Var.e = ou.j0(list, new g21("X-APOLLO-CAN-BE-BATCHED", string));
        }
        return l7Var;
    }
}
