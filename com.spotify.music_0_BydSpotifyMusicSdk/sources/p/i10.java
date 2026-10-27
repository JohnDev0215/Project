package p;

import com.spotify.base.java.logging.Logger;
import com.spotify.connect.esperanto.proto.ConnectMessages$StateRequest;
import com.spotify.connectivity.connectiontype.ConnectivityManager;
import com.spotify.player.model.PlayOrigin;
import com.spotify.playerlimited.playerimpl.services.SessionEndpoint;
import com.spotify.prefs.esperanto.proto.EsPrefs$SetParams;
import com.spotify.prefs.esperanto.proto.EsPrefs$Value;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class i10 implements x00 {
    public final SessionEndpoint a;
    public final zh0 b;
    public final eu1 c;
    public final fu1 d;
    public final jh0 e;
    public final wh0 f;
    public final zw1 g;
    public final ConnectivityManager h;
    public final xx1 i;
    public final ut j;
    public final u6 k;
    public final hh0 l;

    public i10(SessionEndpoint sessionEndpoint, p72 p72Var, zh0 zh0Var, eu1 eu1Var, fu1 fu1Var, jh0 jh0Var, wh0 wh0Var, zw1 zw1Var, dg2 dg2Var, ConnectivityManager connectivityManager, da1 da1Var, xx1 xx1Var, ut utVar, u6 u6Var, PlayOrigin playOrigin, ih0 ih0Var, hh0 hh0Var) {
        v03.j(sessionEndpoint, "sessionEndpoint");
        v03.j(p72Var, "scrobbleClient");
        v03.j(zh0Var, "playerSubscriptions");
        v03.j(eu1Var, "playerControls");
        v03.j(fu1Var, "playerOptions");
        v03.j(jh0Var, "player");
        v03.j(wh0Var, "playerQueueInteractor");
        v03.j(zw1Var, "prefsClient");
        v03.j(dg2Var, "storageClient");
        v03.j(connectivityManager, "connectivityManager");
        v03.j(da1Var, "limitedOffliningEndpoint");
        v03.j(xx1Var, "productStateEndpoint");
        v03.j(utVar, "clientInfo");
        v03.j(u6Var, "clock");
        v03.j(playOrigin, "playOrigin");
        v03.j(ih0Var, "esperantoOfflineEndpoint");
        v03.j(hh0Var, "esperantoConnectEndpoint");
        this.a = sessionEndpoint;
        this.b = zh0Var;
        this.c = eu1Var;
        this.d = fu1Var;
        this.e = jh0Var;
        this.f = wh0Var;
        this.g = zw1Var;
        this.h = connectivityManager;
        this.i = xx1Var;
        this.j = utVar;
        this.k = u6Var;
        this.l = hh0Var;
        Logger.e("CoreNativePlayer created", new Object[0]);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void a(p.i10 r5, p.wu r6, java.lang.String... r7) {
        /*
            r5.getClass()
            r6.getClass()
            boolean r5 = r6 instanceof p.uu
            if (r5 == 0) goto L3b
            int r5 = r7.length
            r0 = 0
            r1 = r0
            r2 = r1
        Le:
            java.lang.String r3 = "result.asFailure().reasons()"
            if (r1 >= r5) goto L2b
            r4 = r7[r1]
            if (r2 != 0) goto L27
            r2 = r6
            p.uu r2 = (p.uu) r2
            java.lang.String r2 = r2.a
            p.v03.i(r2, r3)
            boolean r2 = kotlin.text.b.i0(r2, r4)
            if (r2 == 0) goto L25
            goto L27
        L25:
            r2 = r0
            goto L28
        L27:
            r2 = 1
        L28:
            int r1 = r1 + 1
            goto Le
        L2b:
            if (r2 == 0) goto L2e
            goto L3b
        L2e:
            com.spotify.playerlimited.player.PlayerException r5 = new com.spotify.playerlimited.player.PlayerException
            p.uu r6 = (p.uu) r6
            java.lang.String r6 = r6.a
            p.v03.i(r6, r3)
            r5.<init>(r6)
            throw r5
        L3b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p.i10.a(p.i10, p.wu, java.lang.String[]):void");
    }

    public static EsPrefs$Value c(int i) {
        lg0 lg0VarH = EsPrefs$Value.h();
        lg0VarH.d(i);
        com.google.protobuf.i iVarBuild = lg0VarH.build();
        v03.i(iVarBuild, "newBuilder().setNumber(number.toLong()).build()");
        return (EsPrefs$Value) iVarBuild;
    }

    public final Observable b() {
        hh0 hh0Var = this.l;
        hh0Var.getClass();
        ox oxVarG = ConnectMessages$StateRequest.g();
        oxVarG.e();
        oxVarG.d();
        com.google.protobuf.i iVarBuild = oxVarG.build();
        v03.i(iVarBuild, "newBuilder().apply {\n   …rue\n            }.build()");
        Observable<R> map = hh0Var.a.callStream("spotify.connect.esperanto.proto.ConnectService", "State", (ConnectMessages$StateRequest) iVarBuild).map(px.k);
        v03.i(map, "callStream(\"spotify.conn…     }\n                })");
        Observable map2 = map.map(gh0.c);
        v03.i(map2, "state.map {\n            …)\n            }\n        }");
        Observable observableDistinctUntilChanged = map2.distinctUntilChanged();
        v03.i(observableDistinctUntilChanged, "esperantoConnectEndpoint…().distinctUntilChanged()");
        return observableDistinctUntilChanged;
    }

    public final Completable d() {
        Completable completableIgnoreElement = ((nh0) this.c).a(new yt1("automotive-playbackcontroller")).map(new h10(this, 0)).ignoreElement();
        v03.i(completableIgnoreElement, "override fun pause(featu…   .ignoreElement()\n    }");
        return completableIgnoreElement;
    }

    public final Completable e(Map map) {
        ig0 ig0VarF = EsPrefs$SetParams.f();
        ig0VarF.d(map);
        com.google.protobuf.i iVarBuild = ig0VarF.build();
        v03.i(iVarBuild, "newBuilder().putAllEntries(values).build()");
        Single<R> map2 = this.g.callSingle("spotify.prefs.esperanto.proto.Prefs", "Set", (EsPrefs$SetParams) iVarBuild).map(yw1.e);
        v03.i(map2, "callSingle(\"spotify.pref…     }\n                })");
        Completable completableIgnoreElement = map2.ignoreElement();
        v03.i(completableIgnoreElement, "prefsClient\n            …         .ignoreElement()");
        return completableIgnoreElement;
    }
}
