package coil.intercept;

import java.util.List;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.br1;
import p.c00;
import p.ce0;
import p.e20;
import p.e60;
import p.eu;
import p.fz0;
import p.ii0;
import p.lq2;
import p.q31;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "coil.intercept.EngineInterceptor$transform$3", f = "EngineInterceptor.kt", l = {242}, m = "invokeSuspend")
final class EngineInterceptor$transform$3 extends SuspendLambda implements fz0 {
    final /* synthetic */ ii0 $eventListener;
    final /* synthetic */ br1 $options;
    final /* synthetic */ q31 $request;
    final /* synthetic */ ce0 $result;
    final /* synthetic */ List<eu> $transformations;
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ a this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EngineInterceptor$transform$3(a aVar, ce0 ce0Var, br1 br1Var, List list, ii0 ii0Var, q31 q31Var, c00 c00Var) {
        super(2, c00Var);
        this.this$0 = aVar;
        this.$result = ce0Var;
        this.$options = br1Var;
        this.$transformations = list;
        this.$eventListener = ii0Var;
        this.$request = q31Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        EngineInterceptor$transform$3 engineInterceptor$transform$3 = new EngineInterceptor$transform$3(this.this$0, this.$result, this.$options, this.$transformations, this.$eventListener, this.$request, c00Var);
        engineInterceptor$transform$3.L$0 = obj;
        return engineInterceptor$transform$3;
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((EngineInterceptor$transform$3) a((e20) obj, (c00) obj2)).i(lq2.a);
    }

    /* JADX WARN: Path cross not found for [B:16:0x0055, B:10:0x003d], limit reached: 32 */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00cb  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x00bd -> B:30:0x00c0). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object i(java.lang.Object r14) {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.intercept.EngineInterceptor$transform$3.i(java.lang.Object):java.lang.Object");
    }
}
