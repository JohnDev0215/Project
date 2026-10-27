package p;

import android.content.res.Resources;
import com.spotify.webapi.service.models.Search;
import io.reactivex.rxjava3.core.Single;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class pu1 implements tb1 {
    public static final ou1 g = new ou1();
    public final Resources a;
    public final vv2 b;
    public final ki1 c;
    public final vj d;
    public final mh1 e;
    public final w40 f;

    public pu1(Resources resources, vv2 vv2Var, ki1 ki1Var, vj vjVar, mh1 mh1Var, w40 w40Var) {
        v03.j(resources, "resources");
        v03.j(vv2Var, "webApiController");
        v03.j(ki1Var, "mediaUriUtil");
        v03.j(vjVar, "episodeSubtitleHelper");
        v03.j(mh1Var, "mediaItemActionTypeResolverFactory");
        v03.j(w40Var, "customBrowseActionHandler");
        this.a = resources;
        this.b = vv2Var;
        this.c = ki1Var;
        this.d = vjVar;
        this.e = mh1Var;
        this.f = w40Var;
    }

    @Override // p.tb1
    public final Single a(if2 if2Var, String str, Locale locale, ub1 ub1Var, tx1 tx1Var) {
        v03.j(if2Var, "spotifyUri");
        v03.j(locale, "locale");
        v03.j(tx1Var, "productState");
        Single map = e(if2Var, locale, true).flatMap(new bc(ub1Var, this, if2Var, locale, 2)).map(new co(this, 1, str));
        v03.i(map, "override fun resolveChil…ist()\n            }\n    }");
        return map;
    }

    @Override // p.tb1
    public final Single b(if2 if2Var, String str, Integer num, Locale locale, tx1 tx1Var) {
        v03.j(if2Var, "spotifyUri");
        v03.j(locale, "locale");
        v03.j(tx1Var, "productState");
        Single map = e(if2Var, locale, false).map(new bc(str, num, this, tx1Var, 3));
        v03.i(map, "override fun resolveItem…    )\n            }\n    }");
        return map;
    }

    public final Single e(if2 if2Var, Locale locale, boolean z) {
        wv2 wv2Var = new wv2(locale, "from_token", null, null, z ? Search.Type.EPISODE : null, 0, 0, 0, null, null, 2028);
        yv2 yv2Var = this.b.b;
        String str = if2Var.l;
        v03.h(str);
        return yv2Var.i(str, wv2Var.a());
    }
}
