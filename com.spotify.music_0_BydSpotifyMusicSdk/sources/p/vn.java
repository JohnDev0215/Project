package p;

import android.os.Bundle;
import com.spotify.automotive.ubi.Referrer;
import io.reactivex.rxjava3.core.Single;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class vn implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ if2 c;
    public final /* synthetic */ String e;
    public final /* synthetic */ Locale k;
    public final /* synthetic */ Bundle l;
    public final /* synthetic */ zn m;
    public final /* synthetic */ String n;
    public final /* synthetic */ Referrer o;

    public vn(if2 if2Var, String str, Locale locale, Bundle bundle, zn znVar, String str2, Referrer referrer) {
        this.c = if2Var;
        this.e = str;
        this.k = locale;
        this.l = bundle;
        this.m = znVar;
        this.n = str2;
        this.o = referrer;
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        rn rnVar = (rn) obj;
        v03.j(rnVar, "resolver");
        Single singleB = rnVar.b(this.c, this.e, this.k, this.l);
        zn znVar = this.m;
        String str = this.n;
        Referrer referrer = this.o;
        return singleB.doOnSuccess(new tn(znVar, str, referrer, 2)).doOnError(new tn(znVar, str, referrer, 3));
    }
}
