package p;

import android.content.res.Resources;
import android.os.Bundle;
import com.spotify.music.R;
import io.reactivex.rxjava3.core.Single;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class ao extends bo {
    public final Resources b;
    public final vv2 c;
    public final ki1 d;

    public ao(Resources resources, vv2 vv2Var, ki1 ki1Var) {
        v03.j(resources, "resources");
        v03.j(vv2Var, "webApiController");
        v03.j(ki1Var, "mediaUriUtil");
        this.b = resources;
        this.c = vv2Var;
        this.d = ki1Var;
    }

    @Override // p.tb1
    public final Single c(Locale locale, ub1 ub1Var, tx1 tx1Var) {
        v03.j(locale, "locale");
        v03.j(tx1Var, "productState");
        int i = ub1Var.b;
        if (i <= 0) {
            i = 24;
        }
        Single<R> map = this.c.b.g("automotive-browse", new wv2(locale, null, null, null, null, i, 0, 0, "web", null, 1470).a()).map(new ux1(4, this));
        v03.i(map, "override fun resolveChil…Items\n            }\n    }");
        return map;
    }

    @Override // p.tb1
    public final dg1 d() {
        p01 p01Var = new p01(25);
        p01Var.E(2);
        p01Var.D(2);
        Bundle bundle = (Bundle) p01Var.e;
        jh1 jh1Var = new jh1();
        jh1Var.a = bv2.d.c;
        jh1Var.b = this.b.getString(R.string.content_tab_browse);
        jh1Var.f = this.d.a(R.drawable.ic_browse);
        jh1Var.h = bundle;
        return new dg1(jh1Var, 1);
    }
}
