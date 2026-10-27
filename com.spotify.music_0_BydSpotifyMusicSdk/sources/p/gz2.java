package p;

import android.content.res.Resources;
import android.os.Bundle;
import com.spotify.music.R;
import io.reactivex.rxjava3.core.Single;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class gz2 implements tb1 {
    public final Resources a;
    public final vv2 b;
    public final ki1 c;
    public final mh1 d;
    public final w40 e;

    public gz2(Resources resources, vv2 vv2Var, ki1 ki1Var, mh1 mh1Var, w40 w40Var) {
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
        Integer numValueOf = Integer.valueOf(ub1Var.b);
        if (numValueOf.intValue() <= 0) {
            numValueOf = null;
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : 200;
        rs1 rs1Var = (rs1) this.b.a;
        rs1Var.getClass();
        jh1 jh1VarA = rs1Var.a.a(new oo0(iIntValue));
        int i = v52.a;
        Single map = z90.q(jh1VarA).map(qs1.f174p);
        v03.i(map, "Rx3Apollo.single(query).… ?: emptyList()\n        }");
        Single map2 = map.map(new co(this, 2, tx1Var));
        v03.i(map2, "override fun resolveChil…    }\n            }\n    }");
        return map2;
    }

    @Override // p.tb1
    public final dg1 d() {
        p01 p01Var = new p01(25);
        p01Var.E(1);
        Bundle bundle = (Bundle) p01Var.e;
        jh1 jh1Var = new jh1();
        jh1Var.a = bv2.h.c;
        jh1Var.b = this.a.getString(R.string.content_albums_title);
        jh1Var.f = this.c.a(R.drawable.ic_albums);
        jh1Var.h = bundle;
        return new dg1(jh1Var, 1);
    }
}
