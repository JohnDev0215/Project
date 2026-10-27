package p;

import com.spotify.base.java.logging.Logger;
import com.spotify.prefs.esperanto.proto.EsPrefs$SetParams;
import com.spotify.prefs.esperanto.proto.EsPrefs$Value;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import java.util.Map;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class fh0 {
    public final zw1 a;

    public fh0(zw1 zw1Var) {
        v03.j(zw1Var, "prefsClient");
        this.a = zw1Var;
    }

    public static EsPrefs$Value a(long j) {
        lg0 lg0VarH = EsPrefs$Value.h();
        lg0VarH.d(j);
        com.google.protobuf.i iVarBuild = lg0VarH.build();
        v03.i(iVarBuild, "newBuilder().setNumber(number).build()");
        return (EsPrefs$Value) iVarBuild;
    }

    public final Completable b(Map map) {
        ig0 ig0VarF = EsPrefs$SetParams.f();
        ig0VarF.d(map);
        com.google.protobuf.i iVarBuild = ig0VarF.build();
        v03.i(iVarBuild, "newBuilder().putAllEntries(values).build()");
        Single<R> map2 = this.a.callSingle("spotify.prefs.esperanto.proto.Prefs", "Set", (EsPrefs$SetParams) iVarBuild).map(yw1.e);
        v03.i(map2, "callSingle(\"spotify.pref…     }\n                })");
        Completable completableIgnoreElement = map2.ignoreElement();
        v03.i(completableIgnoreElement, "prefsClient\n            …         .ignoreElement()");
        return completableIgnoreElement;
    }

    public final Completable c(float f) {
        Logger.e("EsperantoPrefsApi: setPlaybackSpeed(%f)", Float.valueOf(f));
        return b(he1.C(new Pair("audio.episode.speed", a((long) (f * 100)))));
    }
}
