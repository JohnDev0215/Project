package coil.intercept;

import com.spotify.connect.esperanto.proto.ConnectMessages$StateResponse;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import p.c00;
import p.e60;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "coil.intercept.RealInterceptorChain", f = "RealInterceptorChain.kt", l = {ConnectMessages$StateResponse.Device.VOLUME_STEPS_FIELD_NUMBER}, m = "proceed")
final class RealInterceptorChain$proceed$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ b this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RealInterceptorChain$proceed$1(b bVar, c00 c00Var) {
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
