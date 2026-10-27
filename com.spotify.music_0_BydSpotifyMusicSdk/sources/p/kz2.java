package p;

import android.content.res.Resources;
import android.os.Bundle;
import com.spotify.music.R;
import io.reactivex.rxjava3.core.Single;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class kz2 implements tb1 {
    public final Resources a;
    public final ps1 b;
    public final ki1 c;
    public final as2 d;
    public final mh1 e;
    public final w40 f;

    public kz2(Resources resources, ps1 ps1Var, ki1 ki1Var, as2 as2Var, mh1 mh1Var, w40 w40Var) {
        v03.j(resources, "resources");
        v03.j(ps1Var, "pathfinderApi");
        v03.j(ki1Var, "mediaUriUtil");
        v03.j(as2Var, "usernameProvider");
        v03.j(mh1Var, "mediaItemActionTypeResolverFactory");
        v03.j(w40Var, "customBrowseActionHandler");
        this.a = resources;
        this.b = ps1Var;
        this.c = ki1Var;
        this.d = as2Var;
        this.e = mh1Var;
        this.f = w40Var;
    }

    @Override // p.tb1
    public final Single c(Locale locale, ub1 ub1Var, tx1 tx1Var) {
        v03.j(locale, "locale");
        v03.j(tx1Var, "productState");
        int i = ub1Var.b;
        if (i <= 0) {
            i = 200;
        }
        rs1 rs1Var = (rs1) this.b;
        rs1Var.getClass();
        jh1 jh1VarA = rs1Var.a.a(new xr0(i));
        int i2 = v52.a;
        Single map = z90.q(jh1VarA).map(qs1.t);
        v03.i(map, "Rx3Apollo.single(query).…0\n            )\n        }");
        Single map2 = map.map(new co(this, 5, tx1Var));
        v03.i(map2, "override fun resolveChil…ylistItems)\n            }");
        return map2;
    }

    @Override // p.tb1
    public final dg1 d() {
        p01 p01Var = new p01(25);
        p01Var.E(1);
        Bundle bundle = (Bundle) p01Var.e;
        jh1 jh1Var = new jh1();
        jh1Var.a = bv2.f.c;
        jh1Var.b = this.a.getString(R.string.content_playlists_title);
        jh1Var.f = this.c.a(R.drawable.ic_playlists);
        jh1Var.h = bundle;
        return new dg1(jh1Var, 1);
    }
}
