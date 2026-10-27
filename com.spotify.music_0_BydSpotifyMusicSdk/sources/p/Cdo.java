package p;

import io.reactivex.rxjava3.core.Single;
import java.util.Locale;
import kotlin.collections.EmptyList;

/* JADX INFO: renamed from: p.do, reason: invalid class name */
/* JADX INFO: loaded from: classes.dex */
public final class Cdo extends bo {
    public final vv2 b;
    public final ki1 c;
    public final mh1 d;
    public final w40 e;

    public Cdo(vv2 vv2Var, ki1 ki1Var, mh1 mh1Var, w40 w40Var) {
        v03.j(vv2Var, "webApiController");
        v03.j(ki1Var, "mediaUriUtil");
        v03.j(mh1Var, "mediaItemActionTypeResolverFactory");
        v03.j(w40Var, "customBrowseActionHandler");
        this.b = vv2Var;
        this.c = ki1Var;
        this.d = mh1Var;
        this.e = w40Var;
    }

    @Override // p.tb1
    public final Single a(if2 if2Var, String str, Locale locale, ub1 ub1Var, tx1 tx1Var) {
        v03.j(if2Var, "spotifyUri");
        v03.j(locale, "locale");
        v03.j(tx1Var, "productState");
        String str2 = if2Var.l;
        if (str2 == null) {
            Single singleJust = Single.just(EmptyList.c);
            v03.i(singleJust, "just(emptyList())");
            return singleJust;
        }
        String strC = wb1.c(tx1Var);
        int i = ub1Var.b;
        if (i <= 0) {
            i = 24;
        }
        Single<R> map = this.b.b.g(str2, new wv2(locale, null, strC, null, null, i, 0, 20, null, null, 1722).a()).map(new co(this, 0, tx1Var));
        v03.i(map, "override fun resolveChil…Items\n            }\n    }");
        return map;
    }
}
