package p;

import com.spotify.player.esperanto.proto.EsResponseWithReasons$ResponseWithReasons;

/* JADX INFO: loaded from: classes.dex */
public abstract class yu {
    public static final wu a(EsResponseWithReasons$ResponseWithReasons esResponseWithReasons$ResponseWithReasons) {
        v03.j(esResponseWithReasons$ResponseWithReasons, "response");
        return xu.a[esResponseWithReasons$ResponseWithReasons.e().ordinal()] == 1 ? new vu() : new uu(esResponseWithReasons$ResponseWithReasons.f());
    }
}
