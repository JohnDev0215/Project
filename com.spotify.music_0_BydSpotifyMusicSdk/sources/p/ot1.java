package p;

import com.spotify.automotive.ubi.Referrer;
import com.spotify.webapi.service.models.Entity;
import com.spotify.webapi.service.models.Pager;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ot1 {
    public final st1 a;
    public final as2 b;
    public final vv2 c;

    public ot1(st1 st1Var, as2 as2Var, vv2 vv2Var) {
        this.a = st1Var;
        this.b = as2Var;
        this.c = vv2Var;
    }

    public static List a(Pager pager) {
        Collection collection;
        return (pager == null || (collection = pager.items) == null) ? Collections.emptyList() : he1.D(new g61(collection, new lj(1), 0));
    }

    public final void b(List list) {
        ((z1) this.a).s(null, ((Entity) list.iterator().next()).uri(), null, Referrer.SEARCH.a());
    }
}
