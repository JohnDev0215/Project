package com.spotify.automotive.playback;

import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.a01;
import p.c00;
import p.e20;
import p.e60;
import p.fz0;
import p.lq2;
import p.uj;
import p.vr2;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "com.spotify.automotive.playback.FullCorePlaybackControllerImpl$pauseIfLocal$1", f = "FullCorePlaybackControllerImpl.kt", l = {118, 120}, m = "invokeSuspend")
final class FullCorePlaybackControllerImpl$pauseIfLocal$1 extends SuspendLambda implements fz0 {
    int label;
    final /* synthetic */ c this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FullCorePlaybackControllerImpl$pauseIfLocal$1(c cVar, c00 c00Var) {
        super(2, c00Var);
        this.this$0 = cVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        return new FullCorePlaybackControllerImpl$pauseIfLocal$1(this.this$0, c00Var);
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((FullCorePlaybackControllerImpl$pauseIfLocal$1) a((e20) obj, (c00) obj2)).i(lq2.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) throws Throwable {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        int i = this.label;
        lq2 lq2Var = lq2.a;
        if (i == 0) {
            kotlin.a.d(obj);
            c cVar = this.this$0;
            this.label = 1;
            cVar.getClass();
            obj = vr2.y(getContext(), new FullCorePlaybackControllerImpl$currentConnectState$2(cVar, null), this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                kotlin.a.d(obj);
                return lq2Var;
            }
            kotlin.a.d(obj);
        }
        a01 a01Var = (a01) obj;
        if (a01Var != null && a01Var.d) {
            uj ujVar = this.this$0.h;
            this.label = 2;
            if (((com.spotify.automotive.playerapi.c) ujVar).b("automotive-playbackcontroller", this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        }
        return lq2Var;
    }
}
