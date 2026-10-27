package p;

import com.google.common.base.Optional;
import com.spotify.base.java.logging.Logger;
import com.spotify.player.esperanto.proto.EsSetRepeatingContext$SetRepeatingContextRequest;
import com.spotify.player.esperanto.proto.EsSetRepeatingTrack$SetRepeatingTrackRequest;
import com.spotify.player.model.Context;
import com.spotify.player.model.ContextTrack;
import com.spotify.player.model.PlayOrigin;
import com.spotify.player.model.PlayerQueue;
import com.spotify.player.model.command.PlayCommand;
import com.spotify.player.model.command.SetRepeatingContextCommand;
import com.spotify.player.model.command.SetRepeatingTrackCommand;
import com.spotify.player.model.command.SkipToNextTrackCommand;
import com.spotify.player.model.command.SkipToPrevTrackCommand;
import com.spotify.player.model.command.options.CommandOptions;
import com.spotify.player.model.command.options.LoggingParams;
import com.spotify.player.model.command.options.PlayOptions;
import com.spotify.player.model.command.options.PreparePlayOptions;
import com.spotify.player.model.command.options.SkipToPrevTrackOptions;
import com.spotify.prefs.esperanto.proto.EsPrefs$SubParams;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.Arrays;
import java.util.Objects;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class ia1 extends z1 {
    public final x00 h;
    public io.reactivex.rxjava3.internal.operators.observable.m3 i;
    public io.reactivex.rxjava3.internal.operators.observable.m3 j;
    public io.reactivex.rxjava3.internal.operators.observable.m3 k;
    public io.reactivex.rxjava3.internal.operators.observable.m3 l;

    public ia1(x00 x00Var, zh0 zh0Var, m mVar, vx1 vx1Var, PlayOrigin playOrigin, u6 u6Var) {
        super(zh0Var, mVar, vx1Var, playOrigin, u6Var);
        this.h = x00Var;
    }

    @Override // p.zc1
    public final void a() {
        this.g.d();
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
    }

    @Override // p.st1
    public final void b(String str, PreparePlayOptions preparePlayOptions, String str2) {
        PreparePlayOptions.Builder builderO;
        if (str == null || str.isEmpty()) {
            Logger.g("playItem called with an empty URI.", new Object[0]);
            return;
        }
        Object objG = Optional.b(str2).i(new v1(this)).g(this.e);
        v03.i(objG, "fun createPlayOrigin(ref… .or(defaultOrigin)\n    }");
        PlayOrigin playOrigin = (PlayOrigin) objG;
        i10 i10Var = (i10) this.h;
        Completable completableSwitchMapCompletable = i10Var.b().take(1L).switchMapCompletable(new ea1(this, 2));
        i10Var.getClass();
        Context.Builder builderA = Context.a(str);
        StringBuilder sb = new StringBuilder("context://");
        sb.append(str);
        i10Var.j.getClass();
        String str3 = String.format("?clientid=%s", Arrays.copyOf(new Object[]{"1fd85409eb8e441fb783f60caf2fdaab"}, 1));
        v03.i(str3, "format(format, *args)");
        sb.append(str3);
        Context contextA = builderA.e(sb.toString()).a();
        v03.i(contextA, "builder(contextUri)\n    …\n                .build()");
        PlayOptions playOptionsA = PlayOptions.a().a();
        v03.i(playOptionsA, "builder.build()");
        if (preparePlayOptions == null) {
            builderO = PreparePlayOptions.c();
            v03.i(builderO, "{\n                Prepar…s.builder()\n            }");
        } else {
            builderO = preparePlayOptions.o();
            v03.i(builderO, "preparePlayOptions.toBuilder()");
        }
        PreparePlayOptions preparePlayOptionsC = builderO.c();
        v03.i(preparePlayOptionsC, "builder.build()");
        PlayCommand.Builder builderA2 = PlayCommand.a(contextA, playOrigin);
        builderA2.d(playOptionsA);
        builderA2.c(preparePlayOptionsC);
        LoggingParams.Builder builderA3 = LoggingParams.a();
        i10Var.k.getClass();
        builderA3.b(Long.valueOf(System.currentTimeMillis()));
        builderA2.b(builderA3.a());
        PlayCommand playCommandA = builderA2.a();
        v03.i(playCommandA, "builder(\n               …\n                .build()");
        completableSwitchMapCompletable.b(new io.reactivex.rxjava3.internal.operators.completable.e(5, i10Var.e.a(playCommandA))).subscribe(new fa1(), new ed1(23), this.g);
    }

    @Override // p.st1
    public final void c(boolean z) {
        i10 i10Var = (i10) this.h;
        Completable completableIgnoreElement = ((sh0) i10Var.d).b(z).map(new h10(i10Var, 7)).ignoreElement();
        v03.i(completableIgnoreElement, "override fun setShuffle(…   .ignoreElement()\n    }");
        completableIgnoreElement.subscribe(new fa1(), new lj(4), this.g);
    }

    @Override // p.st1
    public final void d(String str) {
        Observable map = t().take(1L).map(new ga1(str, 0));
        x00 x00Var = this.h;
        Objects.requireNonNull(x00Var);
        map.switchMapCompletable(new dd1(11, x00Var)).subscribe(new ot(11), new lj(5), this.g);
    }

    @Override // p.st1
    public final Observable e() {
        if (this.l == null) {
            i10 i10Var = (i10) this.h;
            i10Var.getClass();
            Logger.e("playerState()", new Object[0]);
            io.reactivex.rxjava3.observables.a aVarReplay = i10Var.b.b().n().subscribeOn(io.reactivex.rxjava3.schedulers.e.b).distinctUntilChanged().map(new ed1(23)).replay(1);
            aVarReplay.getClass();
            this.l = new io.reactivex.rxjava3.internal.operators.observable.m3(aVarReplay);
        }
        return this.l;
    }

    @Override // p.st1
    public final Observable f() {
        if (this.j == null) {
            i10 i10Var = (i10) this.h;
            i10Var.getClass();
            Logger.e("playbackSpeed()", new Object[0]);
            kg0 kg0VarF = EsPrefs$SubParams.f();
            kg0VarF.d();
            EsPrefs$SubParams esPrefs$SubParams = (EsPrefs$SubParams) kg0VarF.build();
            v03.i(esPrefs$SubParams, "params");
            zw1 zw1Var = i10Var.g;
            zw1Var.getClass();
            Observable<R> map = zw1Var.callStream("spotify.prefs.esperanto.proto.Prefs", "Sub", esPrefs$SubParams).map(yw1.k);
            v03.i(map, "callStream(\"spotify.pref…     }\n                })");
            Observable map2 = map.map(new g10("audio.episode.speed", 0));
            v03.i(map2, "key: String): Observable….getEntriesOrThrow(key) }");
            Observable map3 = map2.map(f10.k);
            v03.i(map3, "observePreference(\"audio…map { it.number.toInt() }");
            io.reactivex.rxjava3.observables.a aVarReplay = map3.subscribeOn(io.reactivex.rxjava3.schedulers.e.b).distinctUntilChanged().replay(1);
            aVarReplay.getClass();
            this.j = new io.reactivex.rxjava3.internal.operators.observable.m3(aVarReplay);
        }
        return this.j;
    }

    @Override // p.st1
    public final void g() {
        j().take(1L).switchMapCompletable(new ea1(this, 0)).subscribe(new ot(5), new ed1(22), this.g);
    }

    @Override // p.st1
    public final void h() {
        ((i10) this.h).d().subscribe(new ot(6), new ed1(24), this.g);
    }

    @Override // p.st1
    public final void i() {
        e().take(1L).subscribe(new ea1(this, 1), new lj(2), new ot(10), this.g);
    }

    @Override // p.st1
    public final Observable j() {
        if (this.i == null) {
            io.reactivex.rxjava3.observables.a aVarReplay = ((i10) this.h).b().subscribeOn(io.reactivex.rxjava3.schedulers.e.b).distinctUntilChanged().map(new ed1(24)).replay(1);
            aVarReplay.getClass();
            this.i = new io.reactivex.rxjava3.internal.operators.observable.m3(aVarReplay);
        }
        return this.i;
    }

    @Override // p.st1
    public final void k(int i) {
        boolean z;
        boolean z2 = false;
        if (i != 0) {
            z = true;
            if (i == 1) {
                z2 = true;
            } else if (i != 2) {
                Logger.g("Repeat called with unsupported repeatMode = %d.", Integer.valueOf(i));
                return;
            } else {
                z = false;
                z2 = true;
            }
        } else {
            z = false;
        }
        i10 i10Var = (i10) this.h;
        i10Var.getClass();
        SetRepeatingContextCommand setRepeatingContextCommandA = SetRepeatingContextCommand.a(z2);
        sh0 sh0Var = (sh0) i10Var.d;
        sh0Var.getClass();
        xg0 xg0VarH = EsSetRepeatingContext$SetRepeatingContextRequest.h();
        xg0VarH.f(setRepeatingContextCommandA.d());
        if (setRepeatingContextCommandA.c().d()) {
            Object objC = setRepeatingContextCommandA.c().c();
            v03.i(objC, "command.options().get()");
            xg0VarH.e(ci1.n((CommandOptions) objC));
        }
        Optional optionalB = setRepeatingContextCommandA.b();
        v03.i(optionalB, "command.loggingParams()");
        LoggingParams loggingParamsA = sh0Var.b.a(optionalB);
        v03.i(loggingParamsA, "loggingParamsFactory.dec…(command.loggingParams())");
        xg0VarH.d(ux2.p(loggingParamsA));
        com.google.protobuf.i iVarBuild = xg0VarH.build();
        v03.i(iVarBuild, "requestBuilder.build()");
        vz vzVar = sh0Var.a;
        vzVar.getClass();
        Single<R> map = vzVar.callSingle("spotify.player.esperanto.proto.ContextPlayer", "SetRepeatingContext", (EsSetRepeatingContext$SetRepeatingContextRequest) iVarBuild).map(uz.t);
        v03.i(map, "callSingle(\"spotify.play…     }\n                })");
        Single map2 = map.map(ph0.c);
        v03.i(map2, "playerClient.SetRepeatin…::commandResultFromProto)");
        Completable completableIgnoreElement = map2.map(new h10(i10Var, 5)).ignoreElement();
        v03.i(completableIgnoreElement, "override fun setRepeatCo…   .ignoreElement()\n    }");
        i10Var.getClass();
        SetRepeatingTrackCommand setRepeatingTrackCommandA = SetRepeatingTrackCommand.a(z);
        sh0 sh0Var2 = (sh0) i10Var.d;
        sh0Var2.getClass();
        yg0 yg0VarH = EsSetRepeatingTrack$SetRepeatingTrackRequest.h();
        yg0VarH.f(setRepeatingTrackCommandA.d());
        if (setRepeatingTrackCommandA.c().d()) {
            Object objC2 = setRepeatingTrackCommandA.c().c();
            v03.i(objC2, "command.options().get()");
            yg0VarH.e(ci1.n((CommandOptions) objC2));
        }
        Optional optionalB2 = setRepeatingTrackCommandA.b();
        v03.i(optionalB2, "command.loggingParams()");
        LoggingParams loggingParamsA2 = sh0Var2.b.a(optionalB2);
        v03.i(loggingParamsA2, "loggingParamsFactory.dec…(command.loggingParams())");
        yg0VarH.d(ux2.p(loggingParamsA2));
        com.google.protobuf.i iVarBuild2 = yg0VarH.build();
        v03.i(iVarBuild2, "requestBuilder.build()");
        vz vzVar2 = sh0Var2.a;
        vzVar2.getClass();
        Single<R> map3 = vzVar2.callSingle("spotify.player.esperanto.proto.ContextPlayer", "SetRepeatingTrack", (EsSetRepeatingTrack$SetRepeatingTrackRequest) iVarBuild2).map(uz.u);
        v03.i(map3, "callSingle(\"spotify.play…     }\n                })");
        Single map4 = map3.map(qh0.c);
        v03.i(map4, "playerClient.SetRepeatin…::commandResultFromProto)");
        Completable completableIgnoreElement2 = map4.map(new h10(i10Var, 6)).ignoreElement();
        v03.i(completableIgnoreElement2, "override fun setRepeatTr…   .ignoreElement()\n    }");
        completableIgnoreElement.b(completableIgnoreElement2).subscribe(new fa1(), new ed1(27), this.g);
    }

    @Override // p.st1
    public final Completable l() {
        return j().take(1L).switchMapCompletable(new ea1(this, 1));
    }

    @Override // p.st1
    public final void m() {
        i10 i10Var = (i10) this.h;
        i10Var.getClass();
        SkipToPrevTrackCommand.Builder builderA = SkipToPrevTrackCommand.a();
        SkipToPrevTrackOptions.Builder builderB = SkipToPrevTrackOptions.b();
        builderB.a(Boolean.TRUE);
        builderA.b(builderB.b());
        Completable completableIgnoreElement = ((nh0) i10Var.c).a(new du1(builderA.a())).map(new h10(i10Var, 10)).ignoreElement();
        v03.i(completableIgnoreElement, "override fun skipToPrevi…   .ignoreElement()\n    }");
        completableIgnoreElement.subscribe(new ot(9), new lj(0), this.g);
    }

    @Override // p.st1
    public final void n(final int i) {
        t().take(1L).switchMapCompletable(new io.reactivex.rxjava3.functions.o() { // from class: p.ha1
            @Override // io.reactivex.rxjava3.functions.o
            public final Object apply(Object obj) {
                PlayerQueue playerQueue = (PlayerQueue) obj;
                ia1 ia1Var = this.c;
                ia1Var.getClass();
                ji1 ji1Var = ji1.c;
                v03.j(playerQueue, "playerQueue");
                ji1 ji1VarQ = zb0.q(playerQueue, true, null, null, null);
                int i2 = i;
                ContextTrack contextTrackA = ji1VarQ.a(i2);
                if (contextTrackA == null) {
                    Logger.b("Can't skip to queue item: no item with ID %d found in queue.", Integer.valueOf(i2));
                    return io.reactivex.rxjava3.internal.operators.completable.d.c;
                }
                i10 i10Var = (i10) ia1Var.h;
                i10Var.getClass();
                SkipToNextTrackCommand.Builder builderA = SkipToNextTrackCommand.a();
                builderA.b(contextTrackA);
                Completable completableIgnoreElement = ((nh0) i10Var.c).a(new cu1(builderA.a())).map(new h10(i10Var, 8)).ignoreElement();
                v03.i(completableIgnoreElement, "override fun skipToFutur…   .ignoreElement()\n    }");
                return completableIgnoreElement;
            }
        }).subscribe(new fa1(), new ed1(29), this.g);
    }

    @Override // p.st1
    public final void next() {
        i10 i10Var = (i10) this.h;
        Completable completableIgnoreElement = ((nh0) i10Var.c).a(new bu1()).map(new h10(i10Var, 9)).ignoreElement();
        v03.i(completableIgnoreElement, "override fun skipToNext(…   .ignoreElement()\n    }");
        completableIgnoreElement.subscribe(new ot(7), new ed1(25), this.g);
    }

    @Override // p.st1
    public final void o(int i) {
        int iMax = Math.max(i, 0);
        i10 i10Var = (i10) this.h;
        i10Var.getClass();
        Completable completableIgnoreElement = ((nh0) i10Var.c).a(new au1(iMax)).map(new h10(i10Var, 3)).ignoreElement();
        v03.i(completableIgnoreElement, "override fun seek(positi…   .ignoreElement()\n    }");
        completableIgnoreElement.subscribe(new fa1(), new lj(3), this.g);
    }

    @Override // p.st1
    public final void p(String str) {
        i10 i10Var = (i10) this.h;
        i10Var.getClass();
        v03.j(str, "uri");
        Completable completableIgnoreElement = i10Var.f.a(ContextTrack.a(str).a()).map(new h10(i10Var, 1)).ignoreElement();
        v03.i(completableIgnoreElement, "override fun queueSong(u…   .ignoreElement()\n    }");
        completableIgnoreElement.subscribe(new fa1(), new ed1(26), this.g);
    }

    @Override // p.st1
    public final void q() {
        e().take(1L).subscribe(new ea1(this, 0), new ed1(28), new ot(8), this.g);
    }

    @Override // p.st1
    public final void r(int i) {
        i10 i10Var = (i10) this.h;
        i10Var.getClass();
        Logger.e("setPlaybackSpeed(%s)", Integer.valueOf(i));
        i10Var.e(he1.C(new Pair("audio.episode.speed", i10.c(i)))).subscribe(new fa1(), new lj(1), this.g);
    }

    @Override // p.st1
    public final Observable t() {
        if (this.k == null) {
            i10 i10Var = (i10) this.h;
            i10Var.getClass();
            Logger.e("playerQueue()", new Object[0]);
            io.reactivex.rxjava3.internal.operators.flowable.h0 h0Var = i10Var.f.c;
            v03.i(h0Var, "playerQueueFlowable");
            io.reactivex.rxjava3.observables.a aVarReplay = h0Var.n().subscribeOn(io.reactivex.rxjava3.schedulers.e.c).distinctUntilChanged().replay(1);
            aVarReplay.getClass();
            this.k = new io.reactivex.rxjava3.internal.operators.observable.m3(aVarReplay);
        }
        return this.k;
    }
}
