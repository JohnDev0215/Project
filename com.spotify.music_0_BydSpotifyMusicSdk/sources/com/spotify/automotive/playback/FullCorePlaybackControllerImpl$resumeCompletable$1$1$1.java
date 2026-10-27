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
@e60(c = "com.spotify.automotive.playback.FullCorePlaybackControllerImpl$resumeCompletable$1$1$1", f = "FullCorePlaybackControllerImpl.kt", l = {97, 101}, m = "invokeSuspend")
final class FullCorePlaybackControllerImpl$resumeCompletable$1$1$1 extends SuspendLambda implements fz0 {
    final /* synthetic */ boolean $localPlaybackEnabled;
    int label;
    final /* synthetic */ c this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FullCorePlaybackControllerImpl$resumeCompletable$1$1$1(c cVar, c00 c00Var, boolean z) {
        super(2, c00Var);
        this.$localPlaybackEnabled = z;
        this.this$0 = cVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        return new FullCorePlaybackControllerImpl$resumeCompletable$1$1$1(this.this$0, c00Var, this.$localPlaybackEnabled);
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((FullCorePlaybackControllerImpl$resumeCompletable$1$1$1) a((e20) obj, (c00) obj2)).i(lq2.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        int i = this.label;
        if (i == 0) {
            kotlin.a.d(obj);
            if (this.$localPlaybackEnabled) {
                c.v(this.this$0, "local Playback enabled");
                uj ujVar = this.this$0.h;
                this.label = 1;
                obj = ((com.spotify.automotive.playerapi.c) ujVar).e("automotive-playbackcontroller", this);
                if (obj == coroutineSingletons) {
                    return coroutineSingletons;
                }
                c cVar = this.this$0;
                ((wu) obj).toString();
                cVar.getClass();
            } else {
                c.v(this.this$0, "pulling back the playback");
                c cVar2 = this.this$0;
                this.label = 2;
                if (c.w(cVar2, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
        } else if (i == 1) {
            kotlin.a.d(obj);
            c cVar3 = this.this$0;
            ((wu) obj).toString();
            cVar3.getClass();
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            kotlin.a.d(obj);
        }
        return lq2.a;
    }
}
