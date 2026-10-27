package com.spotify.automotive.playback;

import com.google.protobuf.i;
import com.spotify.automotive.playerapi.RepeatMode;
import com.spotify.base.java.logging.Logger;
import com.spotify.connect.esperanto.proto.ConnectMessages$StateRequest;
import com.spotify.player.model.PlayOrigin;
import com.spotify.player.model.command.options.PreparePlayOptions;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.internal.operators.flowable.h0;
import io.reactivex.rxjava3.internal.operators.observable.m3;
import java.util.Iterator;
import kotlinx.coroutines.g;
import p.e20;
import p.m;
import p.my0;
import p.ny0;
import p.ox;
import p.p61;
import p.px;
import p.qx;
import p.u6;
import p.uj;
import p.ux1;
import p.v03;
import p.vr2;
import p.vx1;
import p.w1;
import p.z1;
import p.zh0;
import p.zu;

/* JADX INFO: loaded from: classes.dex */
public final class c extends z1 {
    public final uj h;
    public final qx i;
    public final e20 j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(zh0 zh0Var, m mVar, vx1 vx1Var, PlayOrigin playOrigin, u6 u6Var, uj ujVar, qx qxVar, e20 e20Var) {
        super(zh0Var, mVar, vx1Var, playOrigin, u6Var);
        v03.j(zh0Var, "playerSubscriptions");
        v03.j(mVar, "properties");
        v03.j(vx1Var, "productStateController");
        v03.j(playOrigin, "defaultOrigin");
        v03.j(u6Var, "clock");
        v03.j(ujVar, "player");
        v03.j(qxVar, "connectServiceClient");
        v03.j(e20Var, "coroutineScope");
        this.h = ujVar;
        this.i = qxVar;
        this.j = e20Var;
    }

    public static final void v(c cVar, String str) {
        cVar.getClass();
        str.getClass();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object w(com.spotify.automotive.playback.c r6, p.c00 r7) {
        /*
            r6.getClass()
            boolean r0 = r7 instanceof com.spotify.automotive.playback.FullCorePlaybackControllerImpl$pullPlayback$1
            if (r0 == 0) goto L16
            r0 = r7
            com.spotify.automotive.playback.FullCorePlaybackControllerImpl$pullPlayback$1 r0 = (com.spotify.automotive.playback.FullCorePlaybackControllerImpl$pullPlayback$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.spotify.automotive.playback.FullCorePlaybackControllerImpl$pullPlayback$1 r0 = new com.spotify.automotive.playback.FullCorePlaybackControllerImpl$pullPlayback$1
            r0.<init>(r6, r7)
        L1b:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3a
            if (r2 != r3) goto L32
            java.lang.Object r6 = r0.L$0
            com.spotify.automotive.playback.c r6 = (com.spotify.automotive.playback.c) r6
            kotlin.a.d(r7)     // Catch: java.lang.Exception -> L2e java.util.concurrent.CancellationException -> L30
            goto L7c
        L2e:
            r7 = move-exception
            goto L6c
        L30:
            r6 = move-exception
            goto L7f
        L32:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3a:
            kotlin.a.d(r7)
            p.qx r7 = r6.i     // Catch: java.lang.Exception -> L2e java.util.concurrent.CancellationException -> L30
            p.nx r2 = com.spotify.connect.esperanto.proto.ConnectMessages$PullRequest.e()     // Catch: java.lang.Exception -> L2e java.util.concurrent.CancellationException -> L30
            com.google.protobuf.i r2 = r2.build()     // Catch: java.lang.Exception -> L2e java.util.concurrent.CancellationException -> L30
            java.lang.String r4 = "newBuilder().build()"
            p.v03.i(r2, r4)     // Catch: java.lang.Exception -> L2e java.util.concurrent.CancellationException -> L30
            com.spotify.connect.esperanto.proto.ConnectMessages$PullRequest r2 = (com.spotify.connect.esperanto.proto.ConnectMessages$PullRequest) r2     // Catch: java.lang.Exception -> L2e java.util.concurrent.CancellationException -> L30
            java.lang.String r4 = "spotify.connect.esperanto.proto.ConnectService"
            java.lang.String r5 = "Pull"
            io.reactivex.rxjava3.core.Single r7 = r7.callSingle(r4, r5, r2)     // Catch: java.lang.Exception -> L2e java.util.concurrent.CancellationException -> L30
            p.px r2 = p.px.e     // Catch: java.lang.Exception -> L2e java.util.concurrent.CancellationException -> L30
            io.reactivex.rxjava3.core.Single r7 = r7.map(r2)     // Catch: java.lang.Exception -> L2e java.util.concurrent.CancellationException -> L30
            java.lang.String r2 = "callSingle(\"spotify.conn…     }\n                })"
            p.v03.i(r7, r2)     // Catch: java.lang.Exception -> L2e java.util.concurrent.CancellationException -> L30
            r0.L$0 = r6     // Catch: java.lang.Exception -> L2e java.util.concurrent.CancellationException -> L30
            r0.label = r3     // Catch: java.lang.Exception -> L2e java.util.concurrent.CancellationException -> L30
            java.lang.Object r6 = kotlinx.coroutines.rx3.a.c(r7, r0)     // Catch: java.lang.Exception -> L2e java.util.concurrent.CancellationException -> L30
            if (r6 != r1) goto L7c
            goto L7e
        L6c:
            java.lang.String r7 = r7.getMessage()
            if (r7 != 0) goto L74
            java.lang.String r7 = "Unknown error"
        L74:
            r6.getClass()
            java.lang.String r6 = "pullPlayback"
            x(r6, r7)
        L7c:
            p.lq2 r1 = p.lq2.a
        L7e:
            return r1
        L7f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spotify.automotive.playback.c.w(com.spotify.automotive.playback.c, p.c00):java.lang.Object");
    }

    public static void x(String str, String str2) {
        StringBuilder sb = new StringBuilder("Error performing [");
        sb.append(str);
        sb.append(']');
        sb.append(str2.length() == 0 ? "" : " with: ".concat(str2));
        Logger.b(sb.toString(), new Object[0]);
    }

    @Override // p.zc1
    public final void a() {
        this.g.d();
        Iterator it = ((g) zu.h0(this.j.j())).C().iterator();
        while (it.hasNext()) {
            ((p61) it.next()).i(null);
        }
    }

    @Override // p.st1
    public final void b(String str, PreparePlayOptions preparePlayOptions, String str2) {
        if (str == null || str.length() == 0) {
            Logger.b("play Item called with an empty URI.", new Object[0]);
        } else {
            vr2.l(this.j, null, null, new FullCorePlaybackControllerImpl$playContextWithOptions$1(this, str, str2, preparePlayOptions, null), 3);
        }
    }

    @Override // p.st1
    public final void c(boolean z) {
        vr2.l(this.j, null, null, new FullCorePlaybackControllerImpl$shuffle$1(this, null, z), 3);
    }

    @Override // p.st1
    public final void d(String str) {
        v03.j(str, "uri");
        vr2.l(this.j, null, null, new FullCorePlaybackControllerImpl$removeFromQueue$1(this, str, null), 3);
    }

    @Override // p.st1
    public final Observable e() {
        Observable observableDistinctUntilChanged = ((com.spotify.automotive.playerapi.c) this.h).b.b().n().distinctUntilChanged();
        v03.i(observableDistinctUntilChanged, "playerSubscriptions.play…  .distinctUntilChanged()");
        io.reactivex.rxjava3.observables.a aVarReplay = Observable.create(new kotlinx.coroutines.rx3.b(this.j.j(), new ny0(0, kotlinx.coroutines.rx3.a.a(observableDistinctUntilChanged)))).replay(1);
        aVarReplay.getClass();
        return new m3(aVarReplay);
    }

    @Override // p.st1
    public final Observable f() {
        Observable observableDistinctUntilChanged = ((com.spotify.automotive.playerapi.c) this.h).b.b().n().distinctUntilChanged();
        v03.i(observableDistinctUntilChanged, "playerSubscriptions.play…  .distinctUntilChanged()");
        io.reactivex.rxjava3.observables.a aVarReplay = Observable.create(new kotlinx.coroutines.rx3.b(this.j.j(), new ny0(1, new ny0(2, kotlinx.coroutines.rx3.a.a(observableDistinctUntilChanged))))).doOnNext(w1.o).map(w1.k).replay(1);
        aVarReplay.getClass();
        return new m3(aVarReplay);
    }

    @Override // p.st1
    public final void g() {
        vr2.l(this.j, null, null, new FullCorePlaybackControllerImpl$pauseIfLocal$1(this, null), 3);
    }

    @Override // p.st1
    public final void h() {
        vr2.l(this.j, null, null, new FullCorePlaybackControllerImpl$pause$1(this, null), 3);
    }

    @Override // p.st1
    public final void i() {
        vr2.l(this.j, null, null, new FullCorePlaybackControllerImpl$seekBackward$1(this, null), 3);
    }

    @Override // p.st1
    public final Observable j() {
        ox oxVarG = ConnectMessages$StateRequest.g();
        oxVarG.e();
        oxVarG.d();
        i iVarBuild = oxVarG.build();
        v03.i(iVarBuild, "newBuilder().apply {\n   …rue\n            }.build()");
        Observable<R> map = this.i.callStream("spotify.connect.esperanto.proto.ConnectService", "State", (ConnectMessages$StateRequest) iVarBuild).map(px.k);
        v03.i(map, "callStream(\"spotify.conn…     }\n                })");
        io.reactivex.rxjava3.observables.a aVarReplay = map.map(my0.c).replay(1);
        aVarReplay.getClass();
        return new m3(aVarReplay);
    }

    @Override // p.st1
    public final void k(int i) {
        RepeatMode repeatMode;
        if (i != -1) {
            if (i == 0) {
                repeatMode = RepeatMode.c;
            } else if (i == 1) {
                repeatMode = RepeatMode.k;
            } else if (i == 2) {
                repeatMode = RepeatMode.e;
            } else if (i != 3) {
                Logger.g("Repeat called with unsupported repeatMode = %d.", Integer.valueOf(i));
                return;
            }
            vr2.l(this.j, null, null, new FullCorePlaybackControllerImpl$repeat$1(this, i, repeatMode, null), 3);
            return;
        }
        Logger.g("Repeat called with unsupported repeatMode = %d.", Integer.valueOf(i));
    }

    @Override // p.st1
    public final Completable l() {
        Completable completableSwitchMapCompletable = j().take(1L).switchMapCompletable(new ux1(6, this));
        v03.i(completableSwitchMapCompletable, "override fun resumeCompl…    }\n            }\n    }");
        return completableSwitchMapCompletable;
    }

    @Override // p.st1
    public final void m() {
        vr2.l(this.j, null, null, new FullCorePlaybackControllerImpl$prev$1(this, null), 3);
    }

    @Override // p.st1
    public final void n(int i) {
        vr2.l(this.j, null, null, new FullCorePlaybackControllerImpl$skipToQueueItem$1(this, i, null), 3);
    }

    @Override // p.st1
    public final void next() {
        vr2.l(this.j, null, null, new FullCorePlaybackControllerImpl$next$1(this, null), 3);
    }

    @Override // p.st1
    public final void o(int i) {
        if (i < 0) {
            i = 0;
        }
        vr2.l(this.j, null, null, new FullCorePlaybackControllerImpl$seekTo$1(this, i, null), 3);
    }

    @Override // p.st1
    public final void p(String str) {
        v03.j(str, "uri");
        vr2.l(this.j, null, null, new FullCorePlaybackControllerImpl$addToQueue$1(this, str, null), 3);
    }

    @Override // p.st1
    public final void q() {
        vr2.l(this.j, null, null, new FullCorePlaybackControllerImpl$seekForward$1(this, null), 3);
    }

    @Override // p.st1
    public final void r(int i) {
        vr2.l(this.j, null, null, new FullCorePlaybackControllerImpl$setPlaybackSpeed$1(this, i, null), 3);
    }

    @Override // p.st1
    public final Observable t() {
        h0 h0Var = ((com.spotify.automotive.playerapi.c) this.h).c.c;
        v03.i(h0Var, "playerQueueFlowable");
        io.reactivex.rxjava3.observables.a aVarReplay = Observable.create(new kotlinx.coroutines.rx3.b(this.j.j(), kotlinx.coroutines.rx3.a.a(h0Var.n()))).replay(1);
        aVarReplay.getClass();
        return new m3(aVarReplay);
    }
}
