package p;

import android.content.res.Resources;
import com.spotify.automotive.ubi.Referrer;
import com.spotify.music.R;
import io.reactivex.rxjava3.core.Single;
import java.util.Locale;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final class h82 implements tb1 {
    public final Resources a;
    public final ps1 b;
    public final ki1 c;
    public final mh1 d;
    public final m e;
    public final w40 f;

    public h82(Resources resources, ps1 ps1Var, ki1 ki1Var, mh1 mh1Var, m mVar, w40 w40Var) {
        v03.j(resources, "resources");
        v03.j(ps1Var, "pathfinder");
        v03.j(ki1Var, "mediaUriUtil");
        v03.j(mh1Var, "mediaItemActionTypeResolverFactory");
        v03.j(mVar, "properties");
        v03.j(w40Var, "customBrowseActionHandler");
        this.a = resources;
        this.b = ps1Var;
        this.c = ki1Var;
        this.d = mh1Var;
        this.e = mVar;
        this.f = w40Var;
    }

    @Override // p.tb1
    public final Single a(if2 if2Var, String str, Locale locale, ub1 ub1Var, tx1 tx1Var) {
        v03.j(if2Var, "uri");
        v03.j(locale, "locale");
        v03.j(tx1Var, "productState");
        Referrer referrer = null;
        String str2 = if2Var.l;
        String string = str2 != null ? kotlin.text.b.G0(str2).toString() : null;
        if (string == null || ng2.c0(string)) {
            Single singleJust = Single.just(EmptyList.c);
            v03.i(singleJust, "just(emptyList())");
            return singleJust;
        }
        int i = ub1Var.b;
        if (i <= 0) {
            i = 5;
        }
        Referrer[] referrerArrValues = Referrer.values();
        int length = referrerArrValues.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            Referrer referrer2 = referrerArrValues[i2];
            if (v03.b(referrer2.a(), str)) {
                referrer = referrer2;
                break;
            }
            i2++;
        }
        if (referrer == null) {
            referrer = Referrer.SEARCH;
        }
        boolean z = tx1Var.c || ((com.spotify.remoteconfig.a) this.e.a.get()).h() || tx1Var.f;
        rs1 rs1Var = (rs1) this.b;
        rs1Var.getClass();
        jh1 jh1VarA = rs1Var.a.a(new l92(i, i, string, z));
        int i3 = v52.a;
        Single map = z90.q(jh1VarA).map(qs1.w);
        v03.i(map, "Rx3Apollo.single(query).…able(it.data?.searchV2) }");
        Single map2 = map.map(new com.spotify.automotive.content.loaders.a(this, i, referrer, tx1Var));
        v03.i(map2, "override fun resolveChil… else emptyList() }\n    }");
        return map2;
    }

    public final dg1 e(z5 z5Var, g82 g82Var, tx1 tx1Var, String str) {
        w5 w5Var;
        x5 x5Var;
        m5 m5Var;
        if (z5Var == null || (w5Var = z5Var.a) == null || (x5Var = w5Var.c) == null || (m5Var = x5Var.b) == null) {
            return null;
        }
        Referrer referrer = g82Var.a;
        if (str == null) {
            str = this.a.getString(R.string.content_albums_title);
            v03.i(str, "resources.getString(Stri…ing.content_albums_title)");
        }
        return com.spotify.automotive.content.loaders.extensions.a.a(m5Var, referrer, str, g82Var.a(), this.c, this.d.a(tx1Var), this.a, this.f);
    }

    public final dg1 f(fc fcVar, g82 g82Var, tx1 tx1Var, String str) {
        cc ccVar;
        dc dcVar;
        wb wbVar;
        if (fcVar == null || (ccVar = fcVar.a) == null || (dcVar = ccVar.c) == null || (wbVar = dcVar.b) == null) {
            return null;
        }
        Referrer referrer = g82Var.a;
        Integer numValueOf = Integer.valueOf(g82Var.a());
        ki1 ki1Var = this.c;
        lh1 lh1VarA = this.d.a(tx1Var);
        w40 w40Var = this.f;
        if (str == null) {
            str = this.a.getString(R.string.content_artists_title);
            v03.i(str, "resources.getString(Stri…ng.content_artists_title)");
        }
        return v03.S(wbVar, referrer, numValueOf, ki1Var, lh1VarA, w40Var, str);
    }

    public final dg1 g(me meVar, g82 g82Var, tx1 tx1Var, String str) {
        ke keVar;
        fe feVar;
        if (meVar == null || (keVar = meVar.a) == null || (feVar = keVar.c) == null) {
            return null;
        }
        Referrer referrer = g82Var.a;
        if (str == null) {
            str = this.a.getString(R.string.content_audiobooks_title);
            v03.i(str, "resources.getString(Stri…content_audiobooks_title)");
        }
        return he1.S(feVar, referrer, str, g82Var.a(), this.c, this.d.a(tx1Var), this.a);
    }

    public final dg1 h(df0 df0Var, g82 g82Var, String str) {
        af0 af0Var;
        bf0 bf0Var;
        pe0 pe0Var;
        if (df0Var == null || (af0Var = df0Var.a) == null || (bf0Var = af0Var.c) == null || (pe0Var = bf0Var.b) == null || !pe0Var.g.a) {
            return null;
        }
        String strA = g82Var.a.a();
        int iA = g82Var.a();
        ki1 ki1Var = this.c;
        if (str == null) {
            str = this.a.getString(R.string.content_episodes_title);
            v03.i(str, "resources.getString(Stri…g.content_episodes_title)");
        }
        return f60.Q(pe0Var, null, strA, iA, ki1Var, null, str, null);
    }

    public final dg1 i(av1 av1Var, g82 g82Var, tx1 tx1Var, String str) {
        su1 su1Var;
        xu1 xu1Var;
        if (av1Var == null || (su1Var = av1Var.a) == null || (xu1Var = su1Var.c) == null) {
            return null;
        }
        Referrer referrer = g82Var.a;
        if (str == null) {
            str = this.a.getString(R.string.content_playlists_title);
            v03.i(str, "resources.getString(Stri….content_playlists_title)");
        }
        return ci1.G(xu1Var, referrer, str, g82Var.a(), false, this.c, this.d.a(tx1Var), this.a, this.f);
    }

    public final dg1 j(gw1 gw1Var, g82 g82Var, tx1 tx1Var, String str) {
        ew1 ew1Var;
        aw1 aw1Var;
        if (gw1Var == null || (ew1Var = gw1Var.a) == null || (aw1Var = ew1Var.c) == null) {
            return null;
        }
        Referrer referrer = g82Var.a;
        if (str == null) {
            str = this.a.getString(R.string.content_podcasts_shows_title);
            v03.i(str, "resources.getString(Stri…ent_podcasts_shows_title)");
        }
        Integer numValueOf = Integer.valueOf(g82Var.a());
        lh1 lh1VarA = this.d.a(tx1Var);
        v03.j(referrer, "referrer");
        ki1 ki1Var = this.c;
        v03.j(ki1Var, "mediaUriUtil");
        return v03.T(aw1Var, referrer.a(), str, numValueOf, ki1Var, lh1VarA);
    }

    public final dg1 k(hn2 hn2Var, g82 g82Var, String str) {
        en2 en2Var;
        gn2 gn2Var;
        if (hn2Var == null || (en2Var = hn2Var.a) == null || (gn2Var = en2Var.c) == null) {
            return null;
        }
        um2 um2Var = gn2Var.b;
        if (!um2Var.h.a) {
            return null;
        }
        Referrer referrer = g82Var.a;
        int iA = g82Var.a();
        ki1 ki1Var = this.c;
        if (str == null) {
            str = this.a.getString(R.string.content_songs_title);
            v03.i(str, "resources.getString(Stri…ring.content_songs_title)");
        }
        return com.spotify.automotive.content.loaders.extensions.a.b(um2Var, null, referrer, iA, ki1Var, str, false, false, 96);
    }
}
