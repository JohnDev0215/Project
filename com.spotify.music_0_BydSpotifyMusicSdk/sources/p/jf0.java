package p;

import com.spotify.base.java.logging.Logger;
import io.reactivex.rxjava3.core.Observable;

/* JADX INFO: loaded from: classes.dex */
public final class jf0 {
    public final x00 a;
    public final io.reactivex.rxjava3.subjects.c b = new io.reactivex.rxjava3.subjects.c();

    public jf0(x00 x00Var) {
        this.a = x00Var;
    }

    public final Observable a() {
        i10 i10Var = (i10) this.a;
        i10Var.getClass();
        Logger.e("errors()", new Object[0]);
        Observable map = i10Var.b.a().map(f10.e);
        v03.i(map, "playerSubscriptions.erro…(\"error\" to it.error()) }");
        return map.filter(new lj(15)).filter(new lj(16)).map(new ed1(21)).filter(new lj(17)).map(new ed1(22)).mergeWith(this.b);
    }
}
