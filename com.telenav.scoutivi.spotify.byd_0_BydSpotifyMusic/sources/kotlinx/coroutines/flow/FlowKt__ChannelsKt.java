package kotlinx.coroutines.flow;

import g5.r;
import k5.d;
import kotlinx.coroutines.channels.c0;
import kotlinx.coroutines.channels.g;
import kotlinx.coroutines.flow.internal.ChannelFlowKt;
import kotlinx.coroutines.h0;
import kotlinx.coroutines.k0;

/* JADX INFO: compiled from: Channels.kt */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class FlowKt__ChannelsKt {
    public static final <T> Flow<T> asFlow(final g<T> gVar) {
        return new Flow<T>() { // from class: kotlinx.coroutines.flow.FlowKt__ChannelsKt$asFlow$$inlined$unsafeFlow$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, d dVar) throws Throwable {
                Object objEmitAll = FlowKt.emitAll(flowCollector, gVar.c(), (d<? super r>) dVar);
                return objEmitAll == l5.d.d() ? objEmitAll : r.f6563a;
            }
        };
    }

    public static final <T> g<T> broadcastIn(Flow<? extends T> flow, h0 h0Var, k0 k0Var) {
        return ChannelFlowKt.asChannelFlow(flow).broadcastImpl(h0Var, k0Var);
    }

    public static /* synthetic */ g broadcastIn$default(Flow flow, h0 h0Var, k0 k0Var, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            k0Var = k0.LAZY;
        }
        return FlowKt.broadcastIn(flow, h0Var, k0Var);
    }

    public static final <T> Flow<T> consumeAsFlow(c0<? extends T> c0Var) {
        return new ChannelAsFlow(c0Var, true, null, 0, null, 28, null);
    }

    public static final <T> Object emitAll(FlowCollector<? super T> flowCollector, c0<? extends T> c0Var, d<? super r> dVar) throws Throwable {
        Object objEmitAllImpl$FlowKt__ChannelsKt = emitAllImpl$FlowKt__ChannelsKt(flowCollector, c0Var, true, dVar);
        return objEmitAllImpl$FlowKt__ChannelsKt == l5.d.d() ? objEmitAllImpl$FlowKt__ChannelsKt : r.f6563a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0075 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0083 A[Catch: all -> 0x005d, TRY_LEAVE, TryCatch #1 {all -> 0x005d, blocks: (B:13:0x0036, B:27:0x007d, B:29:0x0083, B:35:0x0091, B:36:0x0092, B:18:0x0059), top: B:48:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0092 A[Catch: all -> 0x005d, TRY_LEAVE, TryCatch #1 {all -> 0x005d, blocks: (B:13:0x0036, B:27:0x007d, B:29:0x0083, B:35:0x0091, B:36:0x0092, B:18:0x0059), top: B:48:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r10v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v2, types: [kotlinx.coroutines.channels.c0] */
    /* JADX WARN: Type inference failed for: r10v3, types: [java.lang.Object, kotlinx.coroutines.channels.c0] */
    /* JADX WARN: Type inference failed for: r10v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r9v0, types: [kotlinx.coroutines.channels.c0<? extends T>] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v15, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v16, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v21 */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v23 */
    /* JADX WARN: Type inference failed for: r9v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v4, types: [java.lang.Object, kotlinx.coroutines.channels.c0] */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00a6 -> B:14:0x0039). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static final /* synthetic */ <T> java.lang.Object emitAllImpl$FlowKt__ChannelsKt(kotlinx.coroutines.flow.FlowCollector<? super T> r8, kotlinx.coroutines.channels.c0<? extends T> r9, boolean r10, k5.d<? super g5.r> r11) throws java.lang.Throwable {
        /*
            boolean r0 = r11 instanceof kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1
            if (r0 == 0) goto L13
            r0 = r11
            kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1 r0 = (kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1 r0 = new kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = l5.b.d()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L5f
            if (r2 == r4) goto L47
            if (r2 != r3) goto L3f
            java.lang.Object r8 = r0.L$2
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            boolean r9 = r0.Z$0
            java.lang.Object r10 = r0.L$1
            kotlinx.coroutines.channels.c0 r10 = (kotlinx.coroutines.channels.c0) r10
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
            g5.m.b(r11)     // Catch: java.lang.Throwable -> L5d
        L39:
            r11 = r8
            r8 = r2
            r6 = r10
            r10 = r9
            r9 = r6
            goto L63
        L3f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L47:
            java.lang.Object r8 = r0.L$3
            kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
            java.lang.Object r8 = r0.L$2
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            boolean r9 = r0.Z$0
            java.lang.Object r10 = r0.L$1
            kotlinx.coroutines.channels.c0 r10 = (kotlinx.coroutines.channels.c0) r10
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
            g5.m.b(r11)     // Catch: java.lang.Throwable -> L5d
            goto L7d
        L5d:
            r8 = move-exception
            goto Lad
        L5f:
            g5.m.b(r11)
            r11 = 0
        L63:
            r0.L$0 = r8     // Catch: java.lang.Throwable -> La9
            r0.L$1 = r9     // Catch: java.lang.Throwable -> La9
            r0.Z$0 = r10     // Catch: java.lang.Throwable -> La9
            r0.L$2 = r11     // Catch: java.lang.Throwable -> La9
            r0.L$3 = r8     // Catch: java.lang.Throwable -> La9
            r0.label = r4     // Catch: java.lang.Throwable -> La9
            java.lang.Object r2 = r9.mo1receiveOrClosedZYPwvRU(r0)     // Catch: java.lang.Throwable -> La9
            if (r2 != r1) goto L76
            return r1
        L76:
            r6 = r2
            r2 = r8
            r8 = r11
            r11 = r6
            r7 = r10
            r10 = r9
            r9 = r7
        L7d:
            boolean r5 = kotlinx.coroutines.channels.j0.g(r11)     // Catch: java.lang.Throwable -> L5d
            if (r5 == 0) goto L92
            java.lang.Throwable r11 = kotlinx.coroutines.channels.j0.d(r11)     // Catch: java.lang.Throwable -> L5d
            if (r11 != 0) goto L91
            if (r9 == 0) goto L8e
            kotlinx.coroutines.channels.p.a(r10, r8)
        L8e:
            g5.r r8 = g5.r.f6563a
            return r8
        L91:
            throw r11     // Catch: java.lang.Throwable -> L5d
        L92:
            java.lang.Object r5 = kotlinx.coroutines.channels.j0.e(r11)     // Catch: java.lang.Throwable -> L5d
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L5d
            r0.L$1 = r10     // Catch: java.lang.Throwable -> L5d
            r0.Z$0 = r9     // Catch: java.lang.Throwable -> L5d
            r0.L$2 = r8     // Catch: java.lang.Throwable -> L5d
            r0.L$3 = r11     // Catch: java.lang.Throwable -> L5d
            r0.label = r3     // Catch: java.lang.Throwable -> L5d
            java.lang.Object r11 = r2.emit(r5, r0)     // Catch: java.lang.Throwable -> L5d
            if (r11 != r1) goto L39
            return r1
        La9:
            r8 = move-exception
            r6 = r10
            r10 = r9
            r9 = r6
        Lad:
            throw r8     // Catch: java.lang.Throwable -> Lae
        Lae:
            r11 = move-exception
            if (r9 == 0) goto Lb4
            kotlinx.coroutines.channels.p.a(r10, r8)
        Lb4:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ChannelsKt.emitAllImpl$FlowKt__ChannelsKt(kotlinx.coroutines.flow.FlowCollector, kotlinx.coroutines.channels.c0, boolean, k5.d):java.lang.Object");
    }

    public static final <T> c0<T> produceIn(Flow<? extends T> flow, h0 h0Var) {
        return ChannelFlowKt.asChannelFlow(flow).produceImpl(h0Var);
    }

    public static final <T> Flow<T> receiveAsFlow(c0<? extends T> c0Var) {
        return new ChannelAsFlow(c0Var, false, null, 0, null, 28, null);
    }
}
