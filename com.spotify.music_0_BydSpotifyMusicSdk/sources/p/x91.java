package p;

import com.spotify.base.java.logging.Logger;
import io.reactivex.rxjava3.core.Single;
import java.util.Locale;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final class x91 implements tb1 {
    public final vv2 a;
    public final ki1 b;
    public final vj c;

    public x91(vv2 vv2Var, ki1 ki1Var, vj vjVar) {
        v03.j(vv2Var, "webApiController");
        v03.j(ki1Var, "mediaUriUtil");
        v03.j(vjVar, "episodeSubtitleHelper");
        this.a = vv2Var;
        this.b = ki1Var;
        this.c = vjVar;
    }

    @Override // p.tb1
    public final Single a(if2 if2Var, String str, Locale locale, ub1 ub1Var, tx1 tx1Var) {
        v03.j(if2Var, "uri");
        v03.j(locale, "locale");
        v03.j(tx1Var, "productState");
        if (!tx1Var.a) {
            Logger.b("Liked episodes requested even though podcasts are disabled.", new Object[0]);
            Single singleJust = Single.just(EmptyList.c);
            v03.i(singleJust, "just(emptyList())");
            return singleJust;
        }
        Integer numValueOf = Integer.valueOf(ub1Var.b);
        if (numValueOf.intValue() <= 0) {
            numValueOf = null;
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : 300;
        rs1 rs1Var = (rs1) this.a.a;
        rs1Var.getClass();
        jh1 jh1VarA = rs1Var.a.a(new nq0(iIntValue));
        int i = v52.a;
        Single map = z90.q(jh1VarA).map(qs1.s);
        v03.i(map, "Rx3Apollo.single(query).… ?: emptyList()\n        }");
        Single map2 = map.map(new t5(if2Var, str, this));
        v03.i(map2, "override fun resolveChil…    }\n            }\n    }");
        return map2;
    }
}
