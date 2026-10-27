package p;

import android.content.res.Resources;
import io.reactivex.rxjava3.core.Single;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class id2 implements tb1 {
    public final ps1 a;
    public final ki1 b;
    public final mh1 c;
    public final vj d;
    public final Resources e;

    public id2(ps1 ps1Var, ki1 ki1Var, mh1 mh1Var, vj vjVar, Resources resources) {
        v03.j(ps1Var, "pathfinderApi");
        v03.j(ki1Var, "mediaUriUtil");
        v03.j(mh1Var, "mediaItemActionTypeResolverFactory");
        v03.j(vjVar, "podcastEpisodeSubtitle");
        v03.j(resources, "resources");
        this.a = ps1Var;
        this.b = ki1Var;
        this.c = mh1Var;
        this.d = vjVar;
        this.e = resources;
    }

    @Override // p.tb1
    public final Single a(if2 if2Var, String str, Locale locale, ub1 ub1Var, tx1 tx1Var) {
        v03.j(if2Var, "uri");
        v03.j(locale, "locale");
        v03.j(tx1Var, "productState");
        Integer numValueOf = Integer.valueOf(ub1Var.b);
        if (numValueOf.intValue() <= 0) {
            numValueOf = null;
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : 300;
        String string = if2Var.toString();
        v03.i(string, "uri.toString()");
        rs1 rs1Var = (rs1) this.a;
        rs1Var.getClass();
        jh1 jh1VarA = rs1Var.a.a(new mt0(string, iIntValue));
        int i = v52.a;
        Single map = z90.q(jh1VarA).map(qs1.n);
        v03.i(map, "Rx3Apollo.single(query).…t.data?.podcastUnionV2) }");
        Single map2 = map.map(new bc(this, if2Var, str, tx1Var));
        v03.i(map2, "override fun resolveChil…ist()\n            }\n    }");
        return map2;
    }

    @Override // p.tb1
    public final Single b(if2 if2Var, String str, Integer num, Locale locale, tx1 tx1Var) {
        v03.j(if2Var, "uri");
        v03.j(locale, "locale");
        v03.j(tx1Var, "productState");
        String string = if2Var.toString();
        v03.i(string, "uri.toString()");
        rs1 rs1Var = (rs1) this.a;
        rs1Var.getClass();
        jh1 jh1VarA = rs1Var.a.a(new ht0(string));
        int i = v52.a;
        Single map = z90.q(jh1VarA).map(qs1.o);
        v03.i(map, "Rx3Apollo.single(query).…t.data?.podcastUnionV2) }");
        Single map2 = map.map(new bc(str, num, this, tx1Var, 5));
        v03.i(map2, "override fun resolveItem…al.absent()\n            }");
        return map2;
    }
}
