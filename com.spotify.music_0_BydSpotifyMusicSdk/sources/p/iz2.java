package p;

import android.content.res.Resources;
import com.spotify.music.R;
import io.reactivex.rxjava3.core.Single;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class iz2 implements tb1 {
    public final Resources a;
    public final ps1 b;
    public final ki1 c;
    public final mh1 d;

    public iz2(Resources resources, ps1 ps1Var, ki1 ki1Var, mh1 mh1Var) {
        v03.j(resources, "resources");
        v03.j(ps1Var, "pathfinderApi");
        v03.j(ki1Var, "mediaUriUtil");
        v03.j(mh1Var, "mediaItemActionTypeResolverFactory");
        this.a = resources;
        this.b = ps1Var;
        this.c = ki1Var;
        this.d = mh1Var;
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
        jh1 jh1VarA = rs1Var.a.a(new wp0(i));
        int i2 = v52.a;
        Single map = z90.q(jh1VarA).map(qs1.r);
        v03.i(map, "Rx3Apollo.single(query).… ?: emptyList()\n        }");
        Single map2 = map.map(new co(this, 4, tx1Var));
        v03.i(map2, "override fun resolveChil…it, productState) }\n    }");
        return map2;
    }

    @Override // p.tb1
    public final dg1 d() {
        jh1 jh1Var = new jh1();
        jh1Var.a = bv2.j.c;
        jh1Var.b = this.a.getString(R.string.content_audiobooks_title);
        jh1Var.f = this.c.a(R.drawable.ic_audiobook);
        return new dg1(jh1Var, 1);
    }
}
