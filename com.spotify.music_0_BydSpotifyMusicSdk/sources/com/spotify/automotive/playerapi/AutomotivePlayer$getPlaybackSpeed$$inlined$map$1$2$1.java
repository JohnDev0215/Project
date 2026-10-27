package com.spotify.automotive.playerapi;

import kotlin.coroutines.jvm.internal.ContinuationImpl;
import p.c00;
import p.e60;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "com.spotify.automotive.playerapi.AutomotivePlayer$getPlaybackSpeed$$inlined$map$1$2", f = "AutomotivePlayer.kt", l = {223}, m = "emit")
public final class AutomotivePlayer$getPlaybackSpeed$$inlined$map$1$2$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ b this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutomotivePlayer$getPlaybackSpeed$$inlined$map$1$2$1(b bVar, c00 c00Var) {
        super(c00Var);
        this.this$0 = bVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.b(null, this);
    }
}
