package p;

import android.os.Bundle;
import com.spotify.automotive.ubi.Referrer;
import io.reactivex.rxjava3.core.Single;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class un implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ com.spotify.navigation.identifier.a c;
    public final /* synthetic */ Locale e;
    public final /* synthetic */ Bundle k;
    public final /* synthetic */ zn l;
    public final /* synthetic */ String m;
    public final /* synthetic */ Referrer n;

    public un(com.spotify.navigation.identifier.a aVar, Locale locale, Bundle bundle, zn znVar, String str, Referrer referrer) {
        this.c = aVar;
        this.e = locale;
        this.k = bundle;
        this.l = znVar;
        this.m = str;
        this.n = referrer;
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        rn rnVar = (rn) obj;
        v03.j(rnVar, "resolver");
        Single singleA = rnVar.a(this.c, this.e, this.k);
        zn znVar = this.l;
        String str = this.m;
        Referrer referrer = this.n;
        return singleA.doOnSuccess(new tn(znVar, str, referrer, 0)).doOnError(new tn(znVar, str, referrer, 1));
    }
}
