package p;

import android.os.Bundle;
import com.google.common.base.Optional;
import io.reactivex.rxjava3.core.Single;
import java.util.Locale;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final class uc1 implements rn {
    public final kk0 a;

    public uc1(kk0 kk0Var) {
        v03.j(kk0Var, "parentIdsReloader");
        this.a = kk0Var;
    }

    @Override // p.rn
    public final Single a(com.spotify.navigation.identifier.a aVar, Locale locale, Bundle bundle) {
        v03.j(aVar, "viewUri");
        v03.j(locale, "locale");
        v03.j(bundle, "extras");
        this.a.c(aVar.c);
        Single singleJust = Single.just(EmptyList.c);
        v03.i(singleJust, "just(emptyList())");
        return singleJust;
    }

    @Override // p.rn
    public final Single b(if2 if2Var, String str, Locale locale, Bundle bundle) {
        v03.j(if2Var, "spotifyUri");
        v03.j(locale, "locale");
        v03.j(bundle, "extras");
        String string = if2Var.toString();
        v03.i(string, "spotifyUri.toString()");
        this.a.c(string);
        Single singleJust = Single.just(EmptyList.c);
        v03.i(singleJust, "just(emptyList())");
        return singleJust;
    }

    @Override // p.rn
    public final Single c(if2 if2Var, String str, Integer num, Locale locale) {
        v03.j(if2Var, "spotifyUri");
        v03.j(locale, "locale");
        Single singleJust = Single.just(Optional.a());
        v03.i(singleJust, "just(Optional.absent())");
        return singleJust;
    }
}
