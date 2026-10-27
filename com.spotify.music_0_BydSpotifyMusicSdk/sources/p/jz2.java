package p;

import android.content.res.Resources;
import android.os.Bundle;
import com.spotify.music.R;
import io.reactivex.rxjava3.core.Single;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class jz2 implements tb1 {
    public final Resources a;
    public final xb1 b;
    public final ki1 c;
    public final m d;

    public jz2(Resources resources, xb1 xb1Var, ki1 ki1Var, m mVar) {
        this.a = resources;
        this.b = xb1Var;
        this.c = ki1Var;
        this.d = mVar;
    }

    @Override // p.tb1
    public final Single c(Locale locale, ub1 ub1Var, tx1 tx1Var) {
        he1.e(4, "initialArraySize");
        ArrayList arrayList = new ArrayList(4);
        com.spotify.navigation.identifier.a aVar = bv2.f;
        xb1 xb1Var = this.b;
        Collections.addAll(arrayList, xb1Var.b(aVar).d(), xb1Var.b(bv2.g).d(), xb1Var.b(bv2.h).d());
        if (tx1Var.a) {
            arrayList.add(xb1Var.b(bv2.i).d());
            if (((com.spotify.remoteconfig.a) this.d.a.get()).b()) {
                arrayList.add(xb1Var.b(bv2.j).d());
            }
        }
        return Single.just(arrayList);
    }

    @Override // p.tb1
    public final dg1 d() {
        p01 p01Var = new p01(25);
        p01Var.E(1);
        p01Var.D(3);
        Bundle bundle = (Bundle) p01Var.e;
        jh1 jh1Var = new jh1();
        jh1Var.a = bv2.e.c;
        jh1Var.b = this.a.getString(R.string.content_tab_library);
        jh1Var.f = this.c.a(R.drawable.ic_your_library);
        jh1Var.h = bundle;
        return new dg1(jh1Var, 1);
    }
}
