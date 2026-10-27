package com.spotify.automotive.playback;

import com.spotify.base.java.logging.Logger;
import com.spotify.player.model.ContextTrack;
import com.spotify.player.model.PlayerQueue;
import io.reactivex.rxjava3.internal.operators.flowable.h0;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlinx.coroutines.flow.d;
import p.c00;
import p.e20;
import p.e60;
import p.fz0;
import p.ji1;
import p.lq2;
import p.uj;
import p.v03;
import p.wu;
import p.zb0;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "com.spotify.automotive.playback.FullCorePlaybackControllerImpl$skipToQueueItem$1", f = "FullCorePlaybackControllerImpl.kt", l = {299, 306}, m = "invokeSuspend")
final class FullCorePlaybackControllerImpl$skipToQueueItem$1 extends SuspendLambda implements fz0 {
    final /* synthetic */ int $queueId;
    int label;
    final /* synthetic */ c this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FullCorePlaybackControllerImpl$skipToQueueItem$1(c cVar, int i, c00 c00Var) {
        super(2, c00Var);
        this.this$0 = cVar;
        this.$queueId = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        return new FullCorePlaybackControllerImpl$skipToQueueItem$1(this.this$0, this.$queueId, c00Var);
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((FullCorePlaybackControllerImpl$skipToQueueItem$1) a((e20) obj, (c00) obj2)).i(lq2.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        int i = this.label;
        if (i == 0) {
            kotlin.a.d(obj);
            h0 h0Var = ((com.spotify.automotive.playerapi.c) this.this$0.h).c.c;
            v03.i(h0Var, "playerQueueFlowable");
            kotlinx.coroutines.flow.b bVarA = kotlinx.coroutines.rx3.a.a(h0Var.n());
            this.label = 1;
            obj = d.c(bVarA, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                kotlin.a.d(obj);
                c cVar = this.this$0;
                ((wu) obj).toString();
                cVar.getClass();
                return lq2.a;
            }
            kotlin.a.d(obj);
        }
        PlayerQueue playerQueue = (PlayerQueue) obj;
        ji1 ji1Var = ji1.c;
        v03.j(playerQueue, "playerQueue");
        ContextTrack contextTrackA = zb0.q(playerQueue, true, null, null, null).a(this.$queueId);
        if (contextTrackA == null) {
            Logger.b("Can't skip to queue item: no item with ID %d found in queue.", new Integer(this.$queueId));
            return lq2.a;
        }
        c cVar2 = this.this$0;
        String strF = contextTrackA.f();
        v03.i(strF, "track.uri()");
        c.v(cVar2, strF);
        uj ujVar = this.this$0.h;
        String strF2 = contextTrackA.f();
        v03.i(strF2, "track.uri()");
        this.label = 2;
        obj = ((com.spotify.automotive.playerapi.c) ujVar).l(strF2, this);
        if (obj == coroutineSingletons) {
            return coroutineSingletons;
        }
        c cVar3 = this.this$0;
        ((wu) obj).toString();
        cVar3.getClass();
        return lq2.a;
    }
}
