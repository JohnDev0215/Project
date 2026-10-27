package p;

import android.content.res.Resources;
import android.os.Bundle;
import com.spotify.music.R;
import io.reactivex.rxjava3.core.Single;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public final class b21 implements tb1 {
    public final m a;
    public final Resources b;
    public final vv2 c;
    public final ki1 d;
    public final mh1 e;
    public final u6 f;
    public final w40 g;
    public final SimpleDateFormat h;
    public final String i;

    public b21(m mVar, Resources resources, vv2 vv2Var, ki1 ki1Var, mh1 mh1Var, u6 u6Var, w40 w40Var) {
        v03.j(mVar, "properties");
        v03.j(resources, "resources");
        v03.j(vv2Var, "webApiController");
        v03.j(ki1Var, "mediaUriUtil");
        v03.j(mh1Var, "mediaItemActionTypeResolverFactory");
        v03.j(u6Var, "clock");
        v03.j(w40Var, "customBrowseActionHandler");
        this.a = mVar;
        this.b = resources;
        this.c = vv2Var;
        this.d = ki1Var;
        this.e = mh1Var;
        this.f = u6Var;
        this.g = w40Var;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        this.h = simpleDateFormat;
        this.i = ((com.spotify.remoteconfig.a) mVar.a.get()).g() ? "personalized-recommendations" : "automotive-home";
    }

    @Override // p.tb1
    public final Single c(Locale locale, ub1 ub1Var, tx1 tx1Var) {
        v03.j(locale, "locale");
        v03.j(tx1Var, "productState");
        int i = ub1Var.b;
        if (i <= 0) {
            i = 12;
        }
        int i2 = ub1Var.a;
        if (i2 <= 0) {
            i2 = 6;
        }
        yv2 yv2Var = this.c.b;
        String strC = wb1.c(tx1Var);
        SimpleDateFormat simpleDateFormat = this.h;
        this.f.getClass();
        Single<R> map = yv2Var.g(this.i, new wv2(locale, null, strC, !((com.spotify.remoteconfig.a) this.a.a.get()).g() ? "podcast:true" : null, null, i, 0, i2, null, simpleDateFormat.format(Calendar.getInstance().getTime()), 690).a()).map(new a21(this, i, i2, tx1Var));
        v03.i(map, "override fun resolveChil…    )\n            }\n    }");
        return map;
    }

    @Override // p.tb1
    public final dg1 d() {
        p01 p01Var = new p01(25);
        p01Var.E(2);
        p01Var.D(2);
        Bundle bundle = (Bundle) p01Var.e;
        jh1 jh1Var = new jh1();
        jh1Var.a = bv2.b.c;
        jh1Var.b = this.b.getString(R.string.content_tab_home);
        jh1Var.f = this.d.a(R.drawable.ic_home);
        jh1Var.h = bundle;
        return new dg1(jh1Var, 1);
    }
}
