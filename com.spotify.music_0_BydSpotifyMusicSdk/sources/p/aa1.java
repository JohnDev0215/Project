package p;

import com.spotify.automotive.ubi.Referrer;
import io.reactivex.rxjava3.core.Single;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class aa1 implements tb1 {
    public final vv2 a;
    public final ki1 b;

    public aa1(vv2 vv2Var, ki1 ki1Var) {
        v03.j(vv2Var, "webApiController");
        v03.j(ki1Var, "mediaUriUtil");
        this.a = vv2Var;
        this.b = ki1Var;
    }

    @Override // p.tb1
    public final Single a(if2 if2Var, String str, Locale locale, ub1 ub1Var, tx1 tx1Var) {
        Referrer referrer;
        v03.j(if2Var, "uri");
        v03.j(locale, "locale");
        v03.j(tx1Var, "productState");
        Referrer[] referrerArrValues = Referrer.values();
        int length = referrerArrValues.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                referrer = null;
                break;
            }
            referrer = referrerArrValues[i];
            if (v03.b(referrer.a(), str)) {
                break;
            }
            i++;
        }
        if (referrer == null) {
            referrer = Referrer.LIBRARY;
        }
        Integer numValueOf = Integer.valueOf(ub1Var.b);
        Integer num = numValueOf.intValue() > 0 ? numValueOf : null;
        int iIntValue = num != null ? num.intValue() : 400;
        rs1 rs1Var = (rs1) this.a.a;
        rs1Var.getClass();
        jh1 jh1VarA = rs1Var.a.a(new er0(iIntValue));
        int i2 = v52.a;
        Single map = z90.q(jh1VarA).map(qs1.v);
        v03.i(map, "Rx3Apollo.single(query).… ?: emptyList()\n        }");
        Single map2 = map.map(new t5(if2Var, referrer, this, 2));
        v03.i(map2, "override fun resolveChil…BLE }\n            }\n    }");
        return map2;
    }
}
