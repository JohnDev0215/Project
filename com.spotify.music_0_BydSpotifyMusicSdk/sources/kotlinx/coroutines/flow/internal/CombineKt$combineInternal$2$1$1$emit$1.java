package kotlinx.coroutines.flow.internal;

import com.spotify.connect.esperanto.proto.ConnectMessages$StateResponse;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import p.c00;
import p.e60;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1$1", f = "Combine.kt", l = {ConnectMessages$StateResponse.Device.VOLUME_STEPS_FIELD_NUMBER, ConnectMessages$StateResponse.Device.CAPABILITIES_FIELD_NUMBER}, m = "emit")
final class CombineKt$combineInternal$2$1$1$emit$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ c this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CombineKt$combineInternal$2$1$1$emit$1(c cVar, c00 c00Var) {
        super(c00Var);
        this.this$0 = cVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.b(null, this);
    }
}
