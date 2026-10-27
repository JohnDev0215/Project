package kotlinx.coroutines.flow;

import g5.r;
import k5.d;
import k5.g;
import k5.h;
import kotlin.coroutines.jvm.internal.f;
import kotlinx.coroutines.channels.a0;
import kotlinx.coroutines.channels.k;
import kotlinx.coroutines.flow.internal.ChannelFlow;
import r5.p;

/* JADX INFO: compiled from: Builders.kt */
/* JADX INFO: loaded from: classes.dex */
final class CallbackFlowBuilder<T> extends ChannelFlowBuilder<T> {
    private final p<a0<? super T>, d<? super r>, Object> block;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.CallbackFlowBuilder$collectTo$1, reason: invalid class name */
    /* JADX INFO: compiled from: Builders.kt */
    @f(c = "kotlinx.coroutines.flow.CallbackFlowBuilder", f = "Builders.kt", l = {358}, m = "collectTo")
    static final class AnonymousClass1 extends kotlin.coroutines.jvm.internal.d {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(d dVar) {
            super(dVar);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CallbackFlowBuilder.this.collectTo(null, this);
        }
    }

    public /* synthetic */ CallbackFlowBuilder(p pVar, g gVar, int i7, k kVar, int i8, kotlin.jvm.internal.g gVar2) {
        this(pVar, (i8 & 2) != 0 ? h.f7276e : gVar, (i8 & 4) != 0 ? -2 : i7, (i8 & 8) != 0 ? k.SUSPEND : kVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // kotlinx.coroutines.flow.ChannelFlowBuilder, kotlinx.coroutines.flow.internal.ChannelFlow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.Object collectTo(kotlinx.coroutines.channels.a0<? super T> r5, k5.d<? super g5.r> r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.flow.CallbackFlowBuilder.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.flow.CallbackFlowBuilder$collectTo$1 r0 = (kotlinx.coroutines.flow.CallbackFlowBuilder.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.CallbackFlowBuilder$collectTo$1 r0 = new kotlinx.coroutines.flow.CallbackFlowBuilder$collectTo$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = l5.b.d()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r5 = r0.L$1
            kotlinx.coroutines.channels.a0 r5 = (kotlinx.coroutines.channels.a0) r5
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.flow.CallbackFlowBuilder r0 = (kotlinx.coroutines.flow.CallbackFlowBuilder) r0
            g5.m.b(r6)
            goto L49
        L31:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L39:
            g5.m.b(r6)
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r6 = super.collectTo(r5, r0)
            if (r6 != r1) goto L49
            return r1
        L49:
            boolean r5 = r5.isClosedForSend()
            if (r5 == 0) goto L52
            g5.r r5 = g5.r.f6563a
            return r5
        L52:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "'awaitClose { yourCallbackOrListener.cancel() }' should be used in the end of callbackFlow block.\nOtherwise, a callback/listener may leak in case of external cancellation.\nSee callbackFlow API documentation for the details."
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.CallbackFlowBuilder.collectTo(kotlinx.coroutines.channels.a0, k5.d):java.lang.Object");
    }

    @Override // kotlinx.coroutines.flow.ChannelFlowBuilder, kotlinx.coroutines.flow.internal.ChannelFlow
    protected ChannelFlow<T> create(g gVar, int i7, k kVar) {
        return new CallbackFlowBuilder(this.block, gVar, i7, kVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CallbackFlowBuilder(p<? super a0<? super T>, ? super d<? super r>, ? extends Object> pVar, g gVar, int i7, k kVar) {
        super(pVar, gVar, i7, kVar);
        this.block = pVar;
    }
}
