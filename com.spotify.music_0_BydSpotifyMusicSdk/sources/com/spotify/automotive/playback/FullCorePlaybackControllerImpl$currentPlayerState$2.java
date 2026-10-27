package com.spotify.automotive.playback;

import io.reactivex.rxjava3.core.Observable;
import java.util.concurrent.CancellationException;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlinx.coroutines.flow.d;
import p.c00;
import p.e20;
import p.e60;
import p.fz0;
import p.ju1;
import p.lq2;
import p.ny0;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "com.spotify.automotive.playback.FullCorePlaybackControllerImpl$currentPlayerState$2", f = "FullCorePlaybackControllerImpl.kt", l = {336}, m = "invokeSuspend")
final class FullCorePlaybackControllerImpl$currentPlayerState$2 extends SuspendLambda implements fz0 {
    int label;
    final /* synthetic */ c this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FullCorePlaybackControllerImpl$currentPlayerState$2(c cVar, c00 c00Var) {
        super(2, c00Var);
        this.this$0 = cVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        return new FullCorePlaybackControllerImpl$currentPlayerState$2(this.this$0, c00Var);
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((FullCorePlaybackControllerImpl$currentPlayerState$2) a((e20) obj, (c00) obj2)).i(lq2.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        int i = this.label;
        try {
            if (i == 0) {
                kotlin.a.d(obj);
                Observable observableDistinctUntilChanged = ((com.spotify.automotive.playerapi.c) this.this$0.h).b.b().n().distinctUntilChanged();
                v03.i(observableDistinctUntilChanged, "playerSubscriptions.play…  .distinctUntilChanged()");
                ny0 ny0Var = new ny0(0, kotlinx.coroutines.rx3.a.a(observableDistinctUntilChanged));
                this.label = 1;
                obj = d.c(ny0Var, this);
                if (obj == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                kotlin.a.d(obj);
            }
            return (ju1) obj;
        } catch (CancellationException e) {
            throw e;
        } catch (Exception e2) {
            c cVar = this.this$0;
            String message = e2.getMessage();
            if (message == null) {
                message = "Unknown error";
            }
            cVar.getClass();
            c.x("getPlayerState", message);
            return null;
        }
    }
}
