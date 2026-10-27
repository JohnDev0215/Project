package com.spotify.automotive.playerapi;

import p.fv0;

/* JADX INFO: loaded from: classes.dex */
public final class b implements fv0 {
    public final /* synthetic */ fv0 c;

    public b(fv0 fv0Var) {
        this.c = fv0Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // p.fv0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(java.lang.Object r5, p.c00 r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.spotify.automotive.playerapi.AutomotivePlayer$getPlaybackSpeed$$inlined$map$1$2$1
            if (r0 == 0) goto L13
            r0 = r6
            com.spotify.automotive.playerapi.AutomotivePlayer$getPlaybackSpeed$$inlined$map$1$2$1 r0 = (com.spotify.automotive.playerapi.AutomotivePlayer$getPlaybackSpeed$$inlined$map$1$2$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.spotify.automotive.playerapi.AutomotivePlayer$getPlaybackSpeed$$inlined$map$1$2$1 r0 = new com.spotify.automotive.playerapi.AutomotivePlayer$getPlaybackSpeed$$inlined$map$1$2$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.a.d(r6)
            goto L53
        L27:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2f:
            kotlin.a.d(r6)
            com.spotify.player.model.PlayerState r5 = (com.spotify.player.model.PlayerState) r5
            com.google.common.base.Optional r5 = r5.s()
            java.lang.Object r5 = r5.f()
            java.lang.Number r5 = (java.lang.Number) r5
            double r5 = r5.doubleValue()
            float r5 = (float) r5
            java.lang.Float r6 = new java.lang.Float
            r6.<init>(r5)
            r0.label = r3
            p.fv0 r5 = r4.c
            java.lang.Object r5 = r5.b(r6, r0)
            if (r5 != r1) goto L53
            return r1
        L53:
            p.lq2 r5 = p.lq2.a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spotify.automotive.playerapi.b.b(java.lang.Object, p.c00):java.lang.Object");
    }
}
