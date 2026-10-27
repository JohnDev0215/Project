package kotlinx.coroutines.flow;

import com.spotify.connect.esperanto.proto.ConnectMessages$StateResponse;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import p.e60;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "kotlinx.coroutines.flow.FlowKt__ChannelsKt", f = "Channels.kt", l = {ConnectMessages$StateResponse.Device.SUPPORTS_DJ_FIELD_NUMBER, ConnectMessages$StateResponse.Device.LIBRARY_VERSION_FIELD_NUMBER}, m = "emitAllImpl$FlowKt__ChannelsKt")
final class FlowKt__ChannelsKt$emitAllImpl$1<T> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return d.b(null, null, false, this);
    }
}
