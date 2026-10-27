package com.spotify.automotive.playerapi;

import p.eu1;
import p.fh0;
import p.fu1;
import p.jh0;
import p.u6;
import p.uj;
import p.ut;
import p.v03;
import p.wh0;
import p.zh0;

/* JADX INFO: loaded from: classes.dex */
public final class c implements uj {
    public final jh0 a;
    public final zh0 b;
    public final wh0 c;
    public final eu1 d;
    public final fu1 e;
    public final ut f;
    public final fh0 g;
    public final u6 h;

    public c(jh0 jh0Var, zh0 zh0Var, wh0 wh0Var, eu1 eu1Var, fu1 fu1Var, ut utVar, fh0 fh0Var, u6 u6Var) {
        v03.j(jh0Var, "esperantoPlayer");
        v03.j(zh0Var, "playerSubscriptions");
        v03.j(wh0Var, "playerQueueInteractor");
        v03.j(eu1Var, "playerControls");
        v03.j(fu1Var, "playerOptions");
        v03.j(utVar, "clientInfo");
        v03.j(fh0Var, "esperantoApi");
        v03.j(u6Var, "clock");
        this.a = jh0Var;
        this.b = zh0Var;
        this.c = wh0Var;
        this.d = eu1Var;
        this.e = fu1Var;
        this.f = utVar;
        this.g = fh0Var;
        this.h = u6Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(java.lang.String r5, p.c00 r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.spotify.automotive.playerapi.AutomotivePlayer$addToQueue$1
            if (r0 == 0) goto L13
            r0 = r6
            com.spotify.automotive.playerapi.AutomotivePlayer$addToQueue$1 r0 = (com.spotify.automotive.playerapi.AutomotivePlayer$addToQueue$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.spotify.automotive.playerapi.AutomotivePlayer$addToQueue$1 r0 = new com.spotify.automotive.playerapi.AutomotivePlayer$addToQueue$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.a.d(r6)
            goto L49
        L27:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2f:
            kotlin.a.d(r6)
            com.spotify.player.model.ContextTrack$Builder r5 = com.spotify.player.model.ContextTrack.a(r5)
            com.spotify.player.model.ContextTrack r5 = r5.a()
            p.wh0 r6 = r4.c
            io.reactivex.rxjava3.core.Single r5 = r6.a(r5)
            r0.label = r3
            java.lang.Object r6 = kotlinx.coroutines.rx3.a.c(r5, r0)
            if (r6 != r1) goto L49
            return r1
        L49:
            java.lang.String r5 = "playerQueueInteractor\n  …ri))\n            .await()"
            p.v03.i(r6, r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spotify.automotive.playerapi.c.a(java.lang.String, p.c00):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(java.lang.String r5, p.c00 r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.spotify.automotive.playerapi.AutomotivePlayer$pause$1
            if (r0 == 0) goto L13
            r0 = r6
            com.spotify.automotive.playerapi.AutomotivePlayer$pause$1 r0 = (com.spotify.automotive.playerapi.AutomotivePlayer$pause$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.spotify.automotive.playerapi.AutomotivePlayer$pause$1 r0 = new com.spotify.automotive.playerapi.AutomotivePlayer$pause$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.a.d(r6)
            goto L48
        L27:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2f:
            kotlin.a.d(r6)
            p.yt1 r6 = new p.yt1
            r6.<init>(r5)
            p.eu1 r5 = r4.d
            p.nh0 r5 = (p.nh0) r5
            io.reactivex.rxjava3.core.Single r5 = r5.a(r6)
            r0.label = r3
            java.lang.Object r6 = kotlinx.coroutines.rx3.a.c(r5, r0)
            if (r6 != r1) goto L48
            return r1
        L48:
            java.lang.String r5 = "playerControls.execute(P…ed))\n            .await()"
            p.v03.i(r6, r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spotify.automotive.playerapi.c.b(java.lang.String, p.c00):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object c(java.lang.String r7, com.spotify.player.model.PlayOrigin r8, com.spotify.player.model.command.options.PreparePlayOptions r9, p.c00 r10) {
        /*
            Method dump skipped, instruction units count: 221
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spotify.automotive.playerapi.c.c(java.lang.String, com.spotify.player.model.PlayOrigin, com.spotify.player.model.command.options.PreparePlayOptions, p.c00):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object d(java.lang.String r10, p.c00 r11) {
        /*
            Method dump skipped, instruction units count: 204
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spotify.automotive.playerapi.c.d(java.lang.String, p.c00):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object e(java.lang.String r5, p.c00 r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.spotify.automotive.playerapi.AutomotivePlayer$resume$1
            if (r0 == 0) goto L13
            r0 = r6
            com.spotify.automotive.playerapi.AutomotivePlayer$resume$1 r0 = (com.spotify.automotive.playerapi.AutomotivePlayer$resume$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.spotify.automotive.playerapi.AutomotivePlayer$resume$1 r0 = new com.spotify.automotive.playerapi.AutomotivePlayer$resume$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.a.d(r6)
            goto L48
        L27:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2f:
            kotlin.a.d(r6)
            p.zt1 r6 = new p.zt1
            r6.<init>(r5)
            p.eu1 r5 = r4.d
            p.nh0 r5 = (p.nh0) r5
            io.reactivex.rxjava3.core.Single r5 = r5.a(r6)
            r0.label = r3
            java.lang.Object r6 = kotlinx.coroutines.rx3.a.c(r5, r0)
            if (r6 != r1) goto L48
            return r1
        L48:
            java.lang.String r5 = "playerControls.execute(P…ed))\n            .await()"
            p.v03.i(r6, r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spotify.automotive.playerapi.c.e(java.lang.String, p.c00):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object f(int r5, p.c00 r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.spotify.automotive.playerapi.AutomotivePlayer$seekTo$1
            if (r0 == 0) goto L13
            r0 = r6
            com.spotify.automotive.playerapi.AutomotivePlayer$seekTo$1 r0 = (com.spotify.automotive.playerapi.AutomotivePlayer$seekTo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.spotify.automotive.playerapi.AutomotivePlayer$seekTo$1 r0 = new com.spotify.automotive.playerapi.AutomotivePlayer$seekTo$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.a.d(r6)
            goto L49
        L27:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2f:
            kotlin.a.d(r6)
            long r5 = (long) r5
            p.au1 r2 = new p.au1
            r2.<init>(r5)
            p.eu1 r5 = r4.d
            p.nh0 r5 = (p.nh0) r5
            io.reactivex.rxjava3.core.Single r5 = r5.a(r2)
            r0.label = r3
            java.lang.Object r6 = kotlinx.coroutines.rx3.a.c(r5, r0)
            if (r6 != r1) goto L49
            return r1
        L49:
            java.lang.String r5 = "playerControls.execute(P…()))\n            .await()"
            p.v03.i(r6, r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spotify.automotive.playerapi.c.f(int, p.c00):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object g(float r5, p.c00 r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.spotify.automotive.playerapi.AutomotivePlayer$setPlaybackSpeed$1
            if (r0 == 0) goto L13
            r0 = r6
            com.spotify.automotive.playerapi.AutomotivePlayer$setPlaybackSpeed$1 r0 = (com.spotify.automotive.playerapi.AutomotivePlayer$setPlaybackSpeed$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.spotify.automotive.playerapi.AutomotivePlayer$setPlaybackSpeed$1 r0 = new com.spotify.automotive.playerapi.AutomotivePlayer$setPlaybackSpeed$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            kotlin.a.d(r6)     // Catch: java.lang.Exception -> L27 java.util.concurrent.CancellationException -> L29
            goto L45
        L27:
            r5 = move-exception
            goto L4b
        L29:
            r5 = move-exception
            goto L5a
        L2b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L33:
            kotlin.a.d(r6)
            p.fh0 r6 = r4.g     // Catch: java.lang.Exception -> L27 java.util.concurrent.CancellationException -> L29
            io.reactivex.rxjava3.core.Completable r5 = r6.c(r5)     // Catch: java.lang.Exception -> L27 java.util.concurrent.CancellationException -> L29
            r0.label = r3     // Catch: java.lang.Exception -> L27 java.util.concurrent.CancellationException -> L29
            java.lang.Object r5 = kotlinx.coroutines.rx3.a.b(r5, r0)     // Catch: java.lang.Exception -> L27 java.util.concurrent.CancellationException -> L29
            if (r5 != r1) goto L45
            return r1
        L45:
            p.vu r5 = new p.vu     // Catch: java.lang.Exception -> L27 java.util.concurrent.CancellationException -> L29
            r5.<init>()     // Catch: java.lang.Exception -> L27 java.util.concurrent.CancellationException -> L29
            goto L59
        L4b:
            java.lang.String r5 = r5.getMessage()
            if (r5 != 0) goto L53
            java.lang.String r5 = "Unknown error"
        L53:
            p.uu r6 = new p.uu
            r6.<init>(r5)
            r5 = r6
        L59:
            return r5
        L5a:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spotify.automotive.playerapi.c.g(float, p.c00):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object h(com.spotify.automotive.playerapi.RepeatMode r6, p.c00 r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.spotify.automotive.playerapi.AutomotivePlayer$setRepeatMode$1
            if (r0 == 0) goto L13
            r0 = r7
            com.spotify.automotive.playerapi.AutomotivePlayer$setRepeatMode$1 r0 = (com.spotify.automotive.playerapi.AutomotivePlayer$setRepeatMode$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.spotify.automotive.playerapi.AutomotivePlayer$setRepeatMode$1 r0 = new com.spotify.automotive.playerapi.AutomotivePlayer$setRepeatMode$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.a.d(r7)
            goto L7a
        L27:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2f:
            kotlin.a.d(r7)
            int r6 = r6.ordinal()
            r7 = 2
            if (r6 == 0) goto L49
            if (r6 == r3) goto L46
            if (r6 != r7) goto L40
            com.spotify.player.bindings.options.RepeatMode r6 = com.spotify.player.bindings.options.RepeatMode.k
            goto L4b
        L40:
            kotlin.NoWhenBranchMatchedException r6 = new kotlin.NoWhenBranchMatchedException
            r6.<init>()
            throw r6
        L46:
            com.spotify.player.bindings.options.RepeatMode r6 = com.spotify.player.bindings.options.RepeatMode.e
            goto L4b
        L49:
            com.spotify.player.bindings.options.RepeatMode r6 = com.spotify.player.bindings.options.RepeatMode.c
        L4b:
            p.fu1 r2 = r5.e
            p.sh0 r2 = (p.sh0) r2
            r2.getClass()
            int r6 = r6.ordinal()
            r4 = 0
            if (r6 == 0) goto L6d
            if (r6 == r3) goto L68
            if (r6 != r7) goto L62
            io.reactivex.rxjava3.core.Single r6 = r2.a(r3, r3)
            goto L71
        L62:
            kotlin.NoWhenBranchMatchedException r6 = new kotlin.NoWhenBranchMatchedException
            r6.<init>()
            throw r6
        L68:
            io.reactivex.rxjava3.core.Single r6 = r2.a(r3, r4)
            goto L71
        L6d:
            io.reactivex.rxjava3.core.Single r6 = r2.a(r4, r4)
        L71:
            r0.label = r3
            java.lang.Object r7 = kotlinx.coroutines.rx3.a.c(r6, r0)
            if (r7 != r1) goto L7a
            return r1
        L7a:
            java.lang.String r6 = "playerOptions.setRepeatM…      }\n        ).await()"
            p.v03.i(r7, r6)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spotify.automotive.playerapi.c.h(com.spotify.automotive.playerapi.RepeatMode, p.c00):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object i(com.spotify.automotive.playerapi.ShuffleMode r5, p.c00 r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.spotify.automotive.playerapi.AutomotivePlayer$setShuffleMode$1
            if (r0 == 0) goto L13
            r0 = r6
            com.spotify.automotive.playerapi.AutomotivePlayer$setShuffleMode$1 r0 = (com.spotify.automotive.playerapi.AutomotivePlayer$setShuffleMode$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.spotify.automotive.playerapi.AutomotivePlayer$setShuffleMode$1 r0 = new com.spotify.automotive.playerapi.AutomotivePlayer$setShuffleMode$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.a.d(r6)
            goto L5b
        L27:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2f:
            kotlin.a.d(r6)
            com.spotify.automotive.playerapi.ShuffleMode r6 = com.spotify.automotive.playerapi.ShuffleMode.k
            if (r5 != r6) goto L3e
            p.uu r5 = new p.uu
            java.lang.String r6 = "Smart shuffle is not supported."
            r5.<init>(r6)
            return r5
        L3e:
            com.spotify.automotive.playerapi.ShuffleMode r6 = com.spotify.automotive.playerapi.ShuffleMode.e
            p.fu1 r2 = r4.e
            if (r5 != r6) goto L4b
            p.sh0 r2 = (p.sh0) r2
            io.reactivex.rxjava3.core.Single r5 = r2.b(r3)
            goto L52
        L4b:
            r5 = 0
            p.sh0 r2 = (p.sh0) r2
            io.reactivex.rxjava3.core.Single r5 = r2.b(r5)
        L52:
            r0.label = r3
            java.lang.Object r6 = kotlinx.coroutines.rx3.a.c(r5, r0)
            if (r6 != r1) goto L5b
            return r1
        L5b:
            java.lang.String r5 = "if (mode == ShuffleMode.…(false)\n        }.await()"
            p.v03.i(r6, r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spotify.automotive.playerapi.c.i(com.spotify.automotive.playerapi.ShuffleMode, p.c00):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object j(p.c00 r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.spotify.automotive.playerapi.AutomotivePlayer$skipNext$1
            if (r0 == 0) goto L13
            r0 = r5
            com.spotify.automotive.playerapi.AutomotivePlayer$skipNext$1 r0 = (com.spotify.automotive.playerapi.AutomotivePlayer$skipNext$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.spotify.automotive.playerapi.AutomotivePlayer$skipNext$1 r0 = new com.spotify.automotive.playerapi.AutomotivePlayer$skipNext$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.a.d(r5)
            goto L48
        L27:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2f:
            kotlin.a.d(r5)
            p.bu1 r5 = new p.bu1
            r5.<init>()
            p.eu1 r2 = r4.d
            p.nh0 r2 = (p.nh0) r2
            io.reactivex.rxjava3.core.Single r5 = r2.a(r5)
            r0.label = r3
            java.lang.Object r5 = kotlinx.coroutines.rx3.a.c(r5, r0)
            if (r5 != r1) goto L48
            return r1
        L48:
            java.lang.String r0 = "playerControls.execute(P…k())\n            .await()"
            p.v03.i(r5, r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spotify.automotive.playerapi.c.j(p.c00):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object k(p.c00 r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.spotify.automotive.playerapi.AutomotivePlayer$skipPrevious$1
            if (r0 == 0) goto L13
            r0 = r6
            com.spotify.automotive.playerapi.AutomotivePlayer$skipPrevious$1 r0 = (com.spotify.automotive.playerapi.AutomotivePlayer$skipPrevious$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.spotify.automotive.playerapi.AutomotivePlayer$skipPrevious$1 r0 = new com.spotify.automotive.playerapi.AutomotivePlayer$skipPrevious$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.a.d(r6)
            goto L60
        L27:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L2f:
            kotlin.a.d(r6)
            com.spotify.player.model.command.SkipToPrevTrackCommand$Builder r6 = com.spotify.player.model.command.SkipToPrevTrackCommand.a()
            com.spotify.player.model.command.options.SkipToPrevTrackOptions$Builder r2 = com.spotify.player.model.command.options.SkipToPrevTrackOptions.b()
            java.lang.Boolean r4 = java.lang.Boolean.TRUE
            r2.a(r4)
            com.spotify.player.model.command.options.SkipToPrevTrackOptions r2 = r2.b()
            r6.b(r2)
            com.spotify.player.model.command.SkipToPrevTrackCommand r6 = r6.a()
            p.du1 r2 = new p.du1
            r2.<init>(r6)
            p.eu1 r6 = r5.d
            p.nh0 r6 = (p.nh0) r6
            io.reactivex.rxjava3.core.Single r6 = r6.a(r2)
            r0.label = r3
            java.lang.Object r6 = kotlinx.coroutines.rx3.a.c(r6, r0)
            if (r6 != r1) goto L60
            return r1
        L60:
            java.lang.String r0 = "playerControls\n         …nd))\n            .await()"
            p.v03.i(r6, r0)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spotify.automotive.playerapi.c.k(p.c00):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object l(java.lang.String r8, p.c00 r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.spotify.automotive.playerapi.AutomotivePlayer$skipToQueueItem$1
            if (r0 == 0) goto L13
            r0 = r9
            com.spotify.automotive.playerapi.AutomotivePlayer$skipToQueueItem$1 r0 = (com.spotify.automotive.playerapi.AutomotivePlayer$skipToQueueItem$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.spotify.automotive.playerapi.AutomotivePlayer$skipToQueueItem$1 r0 = new com.spotify.automotive.playerapi.AutomotivePlayer$skipToQueueItem$1
            r0.<init>(r7, r9)
        L18:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L3f
            if (r2 == r4) goto L33
            if (r2 != r3) goto L2b
            kotlin.a.d(r9)
            goto Lc1
        L2b:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L33:
            java.lang.Object r8 = r0.L$1
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r2 = r0.L$0
            com.spotify.automotive.playerapi.c r2 = (com.spotify.automotive.playerapi.c) r2
            kotlin.a.d(r9)
            goto L61
        L3f:
            kotlin.a.d(r9)
            p.wh0 r9 = r7.c
            io.reactivex.rxjava3.internal.operators.flowable.h0 r9 = r9.c
            java.lang.String r2 = "playerQueueFlowable"
            p.v03.i(r9, r2)
            io.reactivex.rxjava3.core.Observable r9 = r9.n()
            kotlinx.coroutines.flow.b r9 = kotlinx.coroutines.rx3.a.a(r9)
            r0.L$0 = r7
            r0.L$1 = r8
            r0.label = r4
            java.lang.Object r9 = kotlinx.coroutines.flow.d.c(r9, r0)
            if (r9 != r1) goto L60
            return r1
        L60:
            r2 = r7
        L61:
            com.spotify.player.model.PlayerQueue r9 = (com.spotify.player.model.PlayerQueue) r9
            com.google.common.collect.ImmutableList r9 = r9.b()
            java.lang.String r4 = "getQueue().first().nextTracks()"
            p.v03.i(r9, r4)
            java.util.Iterator r9 = r9.iterator()
        L70:
            boolean r4 = r9.hasNext()
            r5 = 0
            if (r4 == 0) goto L89
            java.lang.Object r4 = r9.next()
            r6 = r4
            com.spotify.player.model.ContextTrack r6 = (com.spotify.player.model.ContextTrack) r6
            java.lang.String r6 = r6.f()
            boolean r6 = p.v03.b(r6, r8)
            if (r6 == 0) goto L70
            goto L8a
        L89:
            r4 = r5
        L8a:
            com.spotify.player.model.ContextTrack r4 = (com.spotify.player.model.ContextTrack) r4
            if (r4 != 0) goto L9c
            java.lang.String r9 = "No track with URI "
            java.lang.String r0 = " found in queue."
            java.lang.String r8 = p.u4.j(r9, r8, r0)
            p.uu r9 = new p.uu
            r9.<init>(r8)
            return r9
        L9c:
            com.spotify.player.model.command.SkipToNextTrackCommand$Builder r8 = com.spotify.player.model.command.SkipToNextTrackCommand.a()
            r8.b(r4)
            com.spotify.player.model.command.SkipToNextTrackCommand r8 = r8.a()
            p.eu1 r9 = r2.d
            p.cu1 r2 = new p.cu1
            r2.<init>(r8)
            p.nh0 r9 = (p.nh0) r9
            io.reactivex.rxjava3.core.Single r8 = r9.a(r2)
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r9 = kotlinx.coroutines.rx3.a.c(r8, r0)
            if (r9 != r1) goto Lc1
            return r1
        Lc1:
            java.lang.String r8 = "playerControls.execute(P…nd))\n            .await()"
            p.v03.i(r9, r8)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spotify.automotive.playerapi.c.l(java.lang.String, p.c00):java.lang.Object");
    }
}
