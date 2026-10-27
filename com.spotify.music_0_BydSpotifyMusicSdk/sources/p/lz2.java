package p;

import android.content.res.Resources;
import com.spotify.automotive.pathfinder.type.PodcastMediaType;
import com.spotify.automotive.ubi.Referrer;
import com.spotify.base.java.logging.Logger;
import com.spotify.music.R;
import io.reactivex.rxjava3.core.Single;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final class lz2 implements tb1 {
    public final Resources a;
    public final ps1 b;
    public final ki1 c;
    public final mh1 d;
    public final as2 e;

    public lz2(Resources resources, ps1 ps1Var, ki1 ki1Var, mh1 mh1Var, as2 as2Var) {
        v03.j(resources, "resources");
        v03.j(ps1Var, "pathfinderApi");
        v03.j(ki1Var, "mediaUriUtil");
        v03.j(mh1Var, "mediaItemActionTypeResolverFactory");
        v03.j(as2Var, "usernameProvider");
        this.a = resources;
        this.b = ps1Var;
        this.c = ki1Var;
        this.d = mh1Var;
        this.e = as2Var;
    }

    public static final ArrayList e(lz2 lz2Var, List list, int i, tx1 tx1Var) {
        lz2Var.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ew1 ew1Var = ((gw1) it.next()).a;
            aw1 aw1Var = ew1Var != null ? ew1Var.c : null;
            if (aw1Var != null) {
                arrayList.add(aw1Var);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (((aw1) obj).b != PodcastMediaType.VIDEO) {
                arrayList2.add(obj);
            }
        }
        ArrayList arrayList3 = new ArrayList(lu.W(arrayList2));
        int i2 = 0;
        for (Object obj2 : arrayList2) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                v03.P();
                throw null;
            }
            aw1 aw1Var2 = (aw1) obj2;
            Referrer referrer = Referrer.LIBRARY;
            Integer numValueOf = Integer.valueOf(i2 + i);
            lh1 lh1VarA = lz2Var.d.a(tx1Var);
            v03.j(aw1Var2, "<this>");
            ki1 ki1Var = lz2Var.c;
            v03.j(ki1Var, "mediaUriUtil");
            arrayList3.add(v03.T(aw1Var2, referrer.a(), null, numValueOf, ki1Var, lh1VarA));
            i2 = i3;
        }
        return arrayList3;
    }

    @Override // p.tb1
    public final Single c(Locale locale, ub1 ub1Var, tx1 tx1Var) {
        v03.j(locale, "locale");
        v03.j(tx1Var, "productState");
        if (!tx1Var.a) {
            Logger.b("Saved podcasts requested even though podcasts are disabled.", new Object[0]);
            Single singleJust = Single.just(EmptyList.c);
            v03.i(singleJust, "just(emptyList())");
            return singleJust;
        }
        int i = ub1Var.b;
        if (i <= 0) {
            i = 200;
        }
        rs1 rs1Var = (rs1) this.b;
        rs1Var.getClass();
        jh1 jh1VarA = rs1Var.a.a(new ss0(i));
        int i2 = v52.a;
        Single map = z90.q(jh1VarA).map(qs1.u);
        v03.i(map, "Rx3Apollo.single(query).…0\n            )\n        }");
        Single map2 = map.map(new co(this, 6, tx1Var));
        v03.i(map2, "override fun resolveChil…    }\n            }\n    }");
        return map2;
    }

    @Override // p.tb1
    public final dg1 d() {
        jh1 jh1Var = new jh1();
        jh1Var.a = bv2.i.c;
        jh1Var.b = this.a.getString(R.string.content_podcasts_shows_title);
        jh1Var.f = this.c.a(R.drawable.ic_podcasts);
        return new dg1(jh1Var, 1);
    }
}
