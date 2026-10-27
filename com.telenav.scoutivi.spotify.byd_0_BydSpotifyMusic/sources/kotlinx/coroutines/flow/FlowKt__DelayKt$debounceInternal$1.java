package kotlinx.coroutines.flow;

import g5.r;
import k5.d;
import kotlin.coroutines.jvm.internal.f;
import kotlin.coroutines.jvm.internal.l;
import kotlinx.coroutines.h0;
import r5.q;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: Delay.kt */
/* JADX INFO: loaded from: classes.dex */
@f(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1", f = "Delay.kt", l = {354, 358}, m = "invokeSuspend")
final class FlowKt__DelayKt$debounceInternal$1<T> extends l implements q<h0, FlowCollector<? super T>, d<? super r>, Object> {
    final /* synthetic */ Flow $this_debounceInternal;
    final /* synthetic */ r5.l $timeoutMillisSelector;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    private h0 p$;
    private FlowCollector p$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FlowKt__DelayKt$debounceInternal$1(Flow flow, r5.l lVar, d dVar) {
        super(3, dVar);
        this.$this_debounceInternal = flow;
        this.$timeoutMillisSelector = lVar;
    }

    public final d<r> create(h0 h0Var, FlowCollector<? super T> flowCollector, d<? super r> dVar) {
        FlowKt__DelayKt$debounceInternal$1 flowKt__DelayKt$debounceInternal$1 = new FlowKt__DelayKt$debounceInternal$1(this.$this_debounceInternal, this.$timeoutMillisSelector, dVar);
        flowKt__DelayKt$debounceInternal$1.p$ = h0Var;
        flowKt__DelayKt$debounceInternal$1.p$0 = flowCollector;
        return flowKt__DelayKt$debounceInternal$1;
    }

    @Override // r5.q
    public final Object invoke(h0 h0Var, Object obj, d<? super r> dVar) {
        return ((FlowKt__DelayKt$debounceInternal$1) create(h0Var, (FlowCollector) obj, dVar)).invokeSuspend(r.f6563a);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:13|(5:15|(1:17)|18|(1:20)(1:21)|(2:33|34)(2:23|(5:25|(1:27)|28|(1:30)|31)))|32|35|76|36|(8:72|38|39|68|40|41|74|42)(1:47)|48|49|70|50|59|(1:61)|(1:63)(4:64|65|11|(2:66|67)(0))) */
    /* JADX WARN: Can't wrap try/catch for region: R(8:(1:72)|38|39|68|40|41|74|42) */
    /* JADX WARN: Can't wrap try/catch for region: R(8:72|38|39|68|40|41|74|42) */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0116, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0117, code lost:
    
        r5 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x013a, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x013c, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x013d, code lost:
    
        r5 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x013f, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0140, code lost:
    
        r5 = r10;
        r18 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0143, code lost:
    
        r5.U(r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0155 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00fa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:64:0x0156 -> B:65:0x015d). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 357
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
