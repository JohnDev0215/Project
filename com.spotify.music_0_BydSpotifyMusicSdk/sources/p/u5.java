package p;

import android.content.res.Resources;
import com.spotify.automotive.ubi.Referrer;
import io.reactivex.rxjava3.core.Single;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class u5 implements tb1 {
    public final /* synthetic */ int a;
    public final Resources b;
    public final vv2 c;
    public final ki1 d;
    public final mh1 e;
    public final w40 f;

    public u5(Resources resources, vv2 vv2Var, ki1 ki1Var, mh1 mh1Var, w40 w40Var, int i) {
        this.a = i;
        if (i != 1) {
            v03.j(resources, "resources");
            v03.j(vv2Var, "webApiController");
            v03.j(ki1Var, "mediaUriUtil");
            v03.j(mh1Var, "mediaItemActionTypeResolverFactory");
            v03.j(w40Var, "customBrowseActionHandler");
            this.b = resources;
            this.c = vv2Var;
            this.d = ki1Var;
            this.e = mh1Var;
            this.f = w40Var;
            return;
        }
        v03.j(resources, "resources");
        v03.j(vv2Var, "webApiController");
        v03.j(ki1Var, "mediaUriUtil");
        v03.j(mh1Var, "mediaItemActionTypeResolverFactory");
        v03.j(w40Var, "customBrowseActionHandler");
        this.b = resources;
        this.c = vv2Var;
        this.d = ki1Var;
        this.e = mh1Var;
        this.f = w40Var;
    }

    @Override // p.tb1
    public final Single a(if2 if2Var, String str, Locale locale, ub1 ub1Var, tx1 tx1Var) {
        Referrer referrer;
        Referrer referrer2 = Referrer.LIBRARY;
        int i = this.a;
        vv2 vv2Var = this.c;
        Referrer referrer3 = null;
        int i2 = 0;
        switch (i) {
            case 0:
                v03.j(if2Var, "albumUri");
                v03.j(locale, "locale");
                v03.j(tx1Var, "productState");
                String string = if2Var.toString();
                v03.i(string, "albumUri.toString()");
                Referrer[] referrerArrValues = Referrer.values();
                int length = referrerArrValues.length;
                int i3 = 0;
                while (true) {
                    if (i3 < length) {
                        referrer = referrerArrValues[i3];
                        if (!v03.b(referrer.a(), str)) {
                            i3++;
                        }
                    } else {
                        referrer = null;
                    }
                }
                if (referrer != null) {
                    referrer2 = referrer;
                }
                Integer numValueOf = Integer.valueOf(ub1Var.b);
                Integer num = numValueOf.intValue() > 0 ? numValueOf : null;
                int iIntValue = num != null ? num.intValue() : 100;
                rs1 rs1Var = (rs1) vv2Var.a;
                rs1Var.getClass();
                jh1 jh1VarA = rs1Var.a.a(new vm0(string, iIntValue));
                int i4 = v52.a;
                Single map = z90.q(jh1VarA).map(qs1.k);
                v03.i(map, "Rx3Apollo.single(query).… ?: emptyList()\n        }");
                Single map2 = map.map(new t5(string, referrer2, this, 0));
                v03.i(map2, "override fun resolveChil…    }\n            }\n    }");
                return map2;
            default:
                v03.j(if2Var, "spotifyUri");
                v03.j(locale, "locale");
                v03.j(tx1Var, "productState");
                String string2 = if2Var.toString();
                v03.i(string2, "spotifyUri.toString()");
                Referrer[] referrerArrValues2 = Referrer.values();
                int length2 = referrerArrValues2.length;
                while (true) {
                    if (i2 < length2) {
                        Referrer referrer4 = referrerArrValues2[i2];
                        if (v03.b(referrer4.a(), str)) {
                            referrer3 = referrer4;
                        } else {
                            i2++;
                        }
                    }
                }
                if (referrer3 != null) {
                    referrer2 = referrer3;
                }
                rs1 rs1Var2 = (rs1) vv2Var.a;
                rs1Var2.getClass();
                jh1 jh1VarA2 = rs1Var2.a.a(new un0(string2));
                int i5 = v52.a;
                Single map3 = z90.q(jh1VarA2).map(qs1.m);
                v03.i(map3, "Rx3Apollo.single(query).….artistUnion?.onArtist) }");
                Single map4 = map3.map(new bc(this, string2, referrer2, tx1Var, 0));
                v03.i(map4, "override fun resolveChil…lbums\n            }\n    }");
                return map4;
        }
    }

    @Override // p.tb1
    public final Single b(if2 if2Var, String str, Integer num, Locale locale, tx1 tx1Var) {
        Referrer referrer = Referrer.LIBRARY;
        int i = this.a;
        vv2 vv2Var = this.c;
        Referrer referrer2 = null;
        switch (i) {
            case 0:
                v03.j(if2Var, "albumUri");
                v03.j(locale, "locale");
                v03.j(tx1Var, "productState");
                Referrer[] referrerArrValues = Referrer.values();
                int length = referrerArrValues.length;
                int i2 = 0;
                while (true) {
                    if (i2 < length) {
                        Referrer referrer3 = referrerArrValues[i2];
                        if (v03.b(referrer3.a(), str)) {
                            referrer2 = referrer3;
                        } else {
                            i2++;
                        }
                    }
                }
                if (referrer2 != null) {
                    referrer = referrer2;
                }
                iIntValue = num != null ? num.intValue() : 0;
                ps1 ps1Var = vv2Var.a;
                String string = if2Var.toString();
                v03.i(string, "albumUri.toString()");
                rs1 rs1Var = (rs1) ps1Var;
                rs1Var.getClass();
                jh1 jh1VarA = rs1Var.a.a(new lm0(string));
                int i3 = v52.a;
                Single map = z90.q(jh1VarA).map(qs1.e);
                v03.i(map, "Rx3Apollo.single(query).…mUnion?.onAlbum?.album) }");
                Single map2 = map.map(new com.spotify.automotive.content.loaders.a(referrer, this, iIntValue, tx1Var));
                v03.i(map2, "override fun resolveItem…    )\n            }\n    }");
                return map2;
            default:
                v03.j(if2Var, "spotifyUri");
                v03.j(locale, "locale");
                v03.j(tx1Var, "productState");
                Referrer[] referrerArrValues2 = Referrer.values();
                int length2 = referrerArrValues2.length;
                while (true) {
                    if (iIntValue < length2) {
                        Referrer referrer4 = referrerArrValues2[iIntValue];
                        if (v03.b(referrer4.a(), str)) {
                            referrer2 = referrer4;
                        } else {
                            iIntValue++;
                        }
                    }
                }
                if (referrer2 != null) {
                    referrer = referrer2;
                }
                ps1 ps1Var2 = vv2Var.a;
                String string2 = if2Var.toString();
                v03.i(string2, "spotifyUri.toString()");
                rs1 rs1Var2 = (rs1) ps1Var2;
                rs1Var2.getClass();
                jh1 jh1VarA2 = rs1Var2.a.a(new fn0(string2));
                int i4 = v52.a;
                Single map3 = z90.q(jh1VarA2).map(qs1.l);
                v03.i(map3, "Rx3Apollo.single(query).…nion?.onArtist?.artist) }");
                Single map4 = map3.map(new bc(referrer, num, this, tx1Var));
                v03.i(map4, "override fun resolveItem…    )\n            }\n    }");
                return map4;
        }
    }
}
