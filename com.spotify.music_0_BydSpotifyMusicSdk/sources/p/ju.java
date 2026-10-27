package p;

import com.spotify.common.uri.SpotifyUri$Kind;
import io.reactivex.rxjava3.core.Completable;

/* JADX INFO: loaded from: classes.dex */
public final class ju {
    public final ku a;

    public ju(ku kuVar) {
        this.a = kuVar;
    }

    public final Completable a(if2 if2Var, boolean z) {
        int iOrdinal = if2Var.e.ordinal();
        ku kuVar = this.a;
        if (iOrdinal == 1) {
            kuVar.getClass();
            String strC = ku.c(SpotifyUri$Kind.ARTIST, if2Var);
            vv2 vv2Var = kuVar.a;
            return z ? vv2Var.b.o(strC) : vv2Var.b.l(strC);
        }
        if (iOrdinal == 3) {
            kuVar.getClass();
            String strC2 = ku.c(SpotifyUri$Kind.ALBUM, if2Var);
            vv2 vv2Var2 = kuVar.a;
            return z ? vv2Var2.b.q(strC2) : vv2Var2.b.t(strC2);
        }
        if (iOrdinal == 5) {
            kuVar.getClass();
            String strC3 = ku.c(SpotifyUri$Kind.TRACK, if2Var);
            vv2 vv2Var3 = kuVar.a;
            return z ? vv2Var3.b.p(strC3) : vv2Var3.b.u(strC3);
        }
        if (iOrdinal == 9 || iOrdinal == 10) {
            kuVar.getClass();
            String strB = ku.b(if2Var);
            vv2 vv2Var4 = kuVar.a;
            return z ? vv2Var4.b.e(strB) : vv2Var4.b.n(strB);
        }
        if (iOrdinal == 59) {
            kuVar.getClass();
            String strC4 = ku.c(SpotifyUri$Kind.SHOW, if2Var);
            vv2 vv2Var5 = kuVar.a;
            return z ? vv2Var5.b.h(strC4) : vv2Var5.b.w(strC4);
        }
        if (iOrdinal != 60) {
            throw new IllegalArgumentException(String.format("Got unsupported URI of type %s.", if2Var.e));
        }
        kuVar.getClass();
        String strC5 = ku.c(SpotifyUri$Kind.EPISODE, if2Var);
        vv2 vv2Var6 = kuVar.a;
        return z ? vv2Var6.b.k(strC5) : vv2Var6.b.r(strC5);
    }
}
