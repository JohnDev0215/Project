package p;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.Call;

/* JADX INFO: loaded from: classes.dex */
public final class zz0 implements s81 {
    public final s81 a;
    public final String b;
    public xz0 c;

    public zz0(s81 s81Var, String str) {
        v03.j(s81Var, "httpTransport");
        v03.j(str, "baseUrl");
        this.a = s81Var;
        this.b = str;
    }

    @Override // p.s81
    public final Observable a() {
        Observable map = this.a.a().map(new yz0(this));
        v03.i(map, "override fun getObservab…)\n            }\n        }");
        return map;
    }

    public final tz0 b(Call.Factory factory) {
        xz0 xz0Var = this.c;
        if (xz0Var != null && v03.b(factory, xz0Var.a)) {
            return xz0Var.b;
        }
        d52 d52Var = new d52();
        d52Var.b = factory;
        d52Var.c(this.b);
        d52Var.b(b11.c());
        Object objB = d52Var.e().b(tz0.class);
        v03.i(objB, "Builder()\n        .callF…te(GabitoApi::class.java)");
        tz0 tz0Var = (tz0) objB;
        this.c = new xz0(factory, tz0Var);
        return tz0Var;
    }

    @Override // p.s81
    public final Object getLast() {
        Call.Factory factory = (Call.Factory) this.a.getLast();
        if (factory == null) {
            return null;
        }
        return b(factory);
    }
}
