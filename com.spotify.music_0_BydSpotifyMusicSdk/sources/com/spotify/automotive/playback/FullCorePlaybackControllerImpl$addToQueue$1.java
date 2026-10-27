package com.spotify.automotive.playback;

import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.c00;
import p.e20;
import p.e60;
import p.fz0;
import p.lq2;
import p.uj;
import p.wu;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "com.spotify.automotive.playback.FullCorePlaybackControllerImpl$addToQueue$1", f = "FullCorePlaybackControllerImpl.kt", l = {284}, m = "invokeSuspend")
final class FullCorePlaybackControllerImpl$addToQueue$1 extends SuspendLambda implements fz0 {
    final /* synthetic */ String $uri;
    int label;
    final /* synthetic */ c this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FullCorePlaybackControllerImpl$addToQueue$1(c cVar, String str, c00 c00Var) {
        super(2, c00Var);
        this.this$0 = cVar;
        this.$uri = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        return new FullCorePlaybackControllerImpl$addToQueue$1(this.this$0, this.$uri, c00Var);
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((FullCorePlaybackControllerImpl$addToQueue$1) a((e20) obj, (c00) obj2)).i(lq2.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        int i = this.label;
        if (i == 0) {
            kotlin.a.d(obj);
            c.v(this.this$0, this.$uri);
            uj ujVar = this.this$0.h;
            String str = this.$uri;
            this.label = 1;
            obj = ((com.spotify.automotive.playerapi.c) ujVar).a(str, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            kotlin.a.d(obj);
        }
        c cVar = this.this$0;
        ((wu) obj).toString();
        cVar.getClass();
        return lq2.a;
    }
}
