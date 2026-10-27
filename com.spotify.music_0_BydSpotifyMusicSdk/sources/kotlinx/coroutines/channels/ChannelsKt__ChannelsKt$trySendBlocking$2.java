package kotlinx.coroutines.channels;

import com.spotify.connect.esperanto.proto.ConnectMessages$StateResponse;
import kotlin.Result;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.br;
import p.c00;
import p.dr;
import p.e20;
import p.e60;
import p.fz0;
import p.hb2;
import p.lq2;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "kotlinx.coroutines.channels.ChannelsKt__ChannelsKt$trySendBlocking$2", f = "Channels.kt", l = {ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_TYPE_FIELD_NUMBER}, m = "invokeSuspend")
final class ChannelsKt__ChannelsKt$trySendBlocking$2 extends SuspendLambda implements fz0 {
    final /* synthetic */ Object $element;
    final /* synthetic */ hb2 $this_trySendBlocking;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelsKt__ChannelsKt$trySendBlocking$2(hb2 hb2Var, Object obj, c00 c00Var) {
        super(2, c00Var);
        this.$this_trySendBlocking = hb2Var;
        this.$element = obj;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        ChannelsKt__ChannelsKt$trySendBlocking$2 channelsKt__ChannelsKt$trySendBlocking$2 = new ChannelsKt__ChannelsKt$trySendBlocking$2(this.$this_trySendBlocking, this.$element, c00Var);
        channelsKt__ChannelsKt$trySendBlocking$2.L$0 = obj;
        return channelsKt__ChannelsKt$trySendBlocking$2;
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((ChannelsKt__ChannelsKt$trySendBlocking$2) a((e20) obj, (c00) obj2)).i(lq2.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        Object objB;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        int i = this.label;
        Object brVar = lq2.a;
        try {
            if (i == 0) {
                kotlin.a.d(obj);
                hb2 hb2Var = this.$this_trySendBlocking;
                Object obj2 = this.$element;
                this.label = 1;
                if (hb2Var.l(obj2, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                kotlin.a.d(obj);
            }
            objB = brVar;
        } catch (Throwable th) {
            objB = kotlin.a.b(th);
        }
        if (!(!(objB instanceof Result.Failure))) {
            brVar = new br(Result.a(objB));
        }
        return new dr(brVar);
    }
}
