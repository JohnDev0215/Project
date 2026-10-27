package p;

import android.content.res.Resources;
import android.os.Bundle;
import com.spotify.music.R;
import io.reactivex.rxjava3.core.Single;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class n12 implements tb1 {
    public final Resources a;
    public final vv2 b;
    public final ki1 c;
    public final mh1 d;
    public final w40 e;

    public n12(Resources resources, vv2 vv2Var, ki1 ki1Var, mh1 mh1Var, w40 w40Var) {
        v03.j(resources, "resources");
        v03.j(vv2Var, "webApiController");
        v03.j(ki1Var, "mediaUriUtil");
        v03.j(mh1Var, "mediaItemActionTypeResolverFactory");
        v03.j(w40Var, "customBrowseActionHandler");
        this.a = resources;
        this.b = vv2Var;
        this.c = ki1Var;
        this.d = mh1Var;
        this.e = w40Var;
    }

    @Override // p.tb1
    public final Single c(Locale locale, ub1 ub1Var, tx1 tx1Var) {
        v03.j(locale, "locale");
        v03.j(tx1Var, "productState");
        String strC = wb1.c(tx1Var);
        int i = ub1Var.b;
        if (i <= 0) {
            i = 16;
        }
        Single<R> map = this.b.b.g("automotive-recently-played", new wv2(locale, null, strC, null, null, i, 0, 0, null, null, 1978).a()).map(new m12(this, tx1Var));
        v03.i(map, "override fun resolveChil…te) }\n            }\n    }");
        return map;
    }

    @Override // p.tb1
    public final dg1 d() {
        p01 p01Var = new p01(25);
        p01Var.E(2);
        p01Var.D(2);
        Bundle bundle = (Bundle) p01Var.e;
        jh1 jh1Var = new jh1();
        jh1Var.a = bv2.c.c;
        jh1Var.b = this.a.getString(R.string.content_tab_recents);
        jh1Var.f = this.c.a(R.drawable.ic_recently_played);
        jh1Var.h = bundle;
        return new dg1(jh1Var, 1);
    }
}
