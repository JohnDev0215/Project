package com.spotify.automotive.playback;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.a01;
import p.c00;
import p.e20;
import p.e60;
import p.fz0;
import p.lq2;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "com.spotify.automotive.playback.FullCorePlaybackControllerImpl$currentConnectState$2", f = "FullCorePlaybackControllerImpl.kt", l = {}, m = "invokeSuspend")
final class FullCorePlaybackControllerImpl$currentConnectState$2 extends SuspendLambda implements fz0 {
    int label;
    final /* synthetic */ c this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FullCorePlaybackControllerImpl$currentConnectState$2(c cVar, c00 c00Var) {
        super(2, c00Var);
        this.this$0 = cVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        return new FullCorePlaybackControllerImpl$currentConnectState$2(this.this$0, c00Var);
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((FullCorePlaybackControllerImpl$currentConnectState$2) a((e20) obj, (c00) obj2)).i(lq2.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        kotlin.a.d(obj);
        try {
            return (a01) this.this$0.j().blockingFirst();
        } catch (CancellationException e) {
            throw e;
        } catch (Exception e2) {
            c cVar = this.this$0;
            String message = e2.getMessage();
            if (message == null) {
                message = "Unknown error";
            }
            cVar.getClass();
            c.x("getConnectState", message);
            return null;
        }
    }
}
