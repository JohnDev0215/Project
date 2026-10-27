package com.spotify.automotive.playback;

import com.spotify.player.model.command.options.PreparePlayOptions;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.c00;
import p.e20;
import p.e60;
import p.fz0;
import p.lq2;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "com.spotify.automotive.playback.FullCorePlaybackControllerImpl$playContextWithOptions$1", f = "FullCorePlaybackControllerImpl.kt", l = {266, 271, 276}, m = "invokeSuspend")
final class FullCorePlaybackControllerImpl$playContextWithOptions$1 extends SuspendLambda implements fz0 {
    final /* synthetic */ String $contextUri;
    final /* synthetic */ PreparePlayOptions $preparePlayOptions;
    final /* synthetic */ String $referrer;
    int label;
    final /* synthetic */ c this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FullCorePlaybackControllerImpl$playContextWithOptions$1(c cVar, String str, String str2, PreparePlayOptions preparePlayOptions, c00 c00Var) {
        super(2, c00Var);
        this.this$0 = cVar;
        this.$contextUri = str;
        this.$referrer = str2;
        this.$preparePlayOptions = preparePlayOptions;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        return new FullCorePlaybackControllerImpl$playContextWithOptions$1(this.this$0, this.$contextUri, this.$referrer, this.$preparePlayOptions, c00Var);
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((FullCorePlaybackControllerImpl$playContextWithOptions$1) a((e20) obj, (c00) obj2)).i(lq2.a);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00b4 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object i(java.lang.Object r8) throws java.lang.Throwable {
        /*
            r7 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r1 = r7.label
            p.lq2 r2 = p.lq2.a
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L26
            if (r1 == r5) goto L22
            if (r1 == r4) goto L1e
            if (r1 != r3) goto L16
            kotlin.a.d(r8)
            goto Lb5
        L16:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L1e:
            kotlin.a.d(r8)
            goto L81
        L22:
            kotlin.a.d(r8)
            goto L6a
        L26:
            kotlin.a.d(r8)
            com.spotify.automotive.playback.c r8 = r7.this$0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r6 = "contextUri = "
            r1.<init>(r6)
            java.lang.String r6 = r7.$contextUri
            r1.append(r6)
            java.lang.String r6 = " , referrer = "
            r1.append(r6)
            java.lang.String r6 = r7.$referrer
            r1.append(r6)
            java.lang.String r6 = " , preparePlayOptions = "
            r1.append(r6)
            com.spotify.player.model.command.options.PreparePlayOptions r6 = r7.$preparePlayOptions
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            com.spotify.automotive.playback.c.v(r8, r1)
            com.spotify.automotive.playback.c r8 = r7.this$0
            r7.label = r5
            r8.getClass()
            p.y10 r1 = r7.getContext()
            com.spotify.automotive.playback.FullCorePlaybackControllerImpl$currentConnectState$2 r5 = new com.spotify.automotive.playback.FullCorePlaybackControllerImpl$currentConnectState$2
            r6 = 0
            r5.<init>(r8, r6)
            java.lang.Object r8 = p.vr2.y(r1, r5, r7)
            if (r8 != r0) goto L6a
            return r0
        L6a:
            p.a01 r8 = (p.a01) r8
            if (r8 != 0) goto L6f
            return r2
        L6f:
            r8.toString()
            boolean r8 = r8.a
            if (r8 != 0) goto L81
            com.spotify.automotive.playback.c r8 = r7.this$0
            r7.label = r4
            java.lang.Object r8 = com.spotify.automotive.playback.c.w(r8, r7)
            if (r8 != r0) goto L81
            return r0
        L81:
            com.spotify.automotive.playback.c r8 = r7.this$0
            java.lang.String r1 = r7.$referrer
            r8.getClass()
            com.google.common.base.Optional r1 = com.google.common.base.Optional.b(r1)
            p.v1 r4 = new p.v1
            r4.<init>(r8)
            com.google.common.base.Optional r1 = r1.i(r4)
            com.spotify.player.model.PlayOrigin r8 = r8.e
            java.lang.Object r8 = r1.g(r8)
            java.lang.String r1 = "fun createPlayOrigin(ref… .or(defaultOrigin)\n    }"
            p.v03.i(r8, r1)
            com.spotify.player.model.PlayOrigin r8 = (com.spotify.player.model.PlayOrigin) r8
            com.spotify.automotive.playback.c r1 = r7.this$0
            p.uj r1 = r1.h
            java.lang.String r4 = r7.$contextUri
            com.spotify.player.model.command.options.PreparePlayOptions r5 = r7.$preparePlayOptions
            r7.label = r3
            com.spotify.automotive.playerapi.c r1 = (com.spotify.automotive.playerapi.c) r1
            java.lang.Object r8 = r1.c(r4, r8, r5, r7)
            if (r8 != r0) goto Lb5
            return r0
        Lb5:
            p.wu r8 = (p.wu) r8
            com.spotify.automotive.playback.c r0 = r7.this$0
            r8.toString()
            r0.getClass()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spotify.automotive.playback.FullCorePlaybackControllerImpl$playContextWithOptions$1.i(java.lang.Object):java.lang.Object");
    }
}
