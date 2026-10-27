package kotlinx.coroutines.flow;

import g5.r;
import k5.d;
import k5.g;
import kotlinx.coroutines.f;
import kotlinx.coroutines.h0;
import kotlinx.coroutines.t;
import kotlinx.coroutines.v;
import r5.p;

/* JADX INFO: compiled from: Share.kt */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class FlowKt__ShareKt {
    public static final <T> SharedFlow<T> asSharedFlow(MutableSharedFlow<T> mutableSharedFlow) {
        return new ReadonlySharedFlow(mutableSharedFlow);
    }

    public static final <T> StateFlow<T> asStateFlow(MutableStateFlow<T> mutableStateFlow) {
        return new ReadonlyStateFlow(mutableStateFlow);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final <T> kotlinx.coroutines.flow.SharingConfig<T> configureSharing$FlowKt__ShareKt(kotlinx.coroutines.flow.Flow<? extends T> r7, int r8) {
        /*
            kotlinx.coroutines.channels.l$a r0 = kotlinx.coroutines.channels.l.f7574f
            int r0 = r0.a()
            int r0 = v5.f.b(r8, r0)
            int r0 = r0 - r8
            boolean r1 = r7 instanceof kotlinx.coroutines.flow.internal.ChannelFlow
            if (r1 == 0) goto L3d
            r1 = r7
            kotlinx.coroutines.flow.internal.ChannelFlow r1 = (kotlinx.coroutines.flow.internal.ChannelFlow) r1
            kotlinx.coroutines.flow.Flow r2 = r1.dropChannelOperators()
            if (r2 == 0) goto L3d
            kotlinx.coroutines.flow.SharingConfig r7 = new kotlinx.coroutines.flow.SharingConfig
            int r3 = r1.capacity
            r4 = -3
            r5 = 0
            if (r3 == r4) goto L27
            r4 = -2
            if (r3 == r4) goto L27
            if (r3 == 0) goto L27
            r0 = r3
            goto L35
        L27:
            kotlinx.coroutines.channels.k r4 = r1.onBufferOverflow
            kotlinx.coroutines.channels.k r6 = kotlinx.coroutines.channels.k.SUSPEND
            if (r4 != r6) goto L30
            if (r3 != 0) goto L35
            goto L34
        L30:
            if (r8 != 0) goto L34
            r0 = 1
            goto L35
        L34:
            r0 = r5
        L35:
            kotlinx.coroutines.channels.k r8 = r1.onBufferOverflow
            k5.g r1 = r1.context
            r7.<init>(r2, r0, r8, r1)
            return r7
        L3d:
            kotlinx.coroutines.flow.SharingConfig r8 = new kotlinx.coroutines.flow.SharingConfig
            kotlinx.coroutines.channels.k r1 = kotlinx.coroutines.channels.k.SUSPEND
            k5.h r2 = k5.h.f7276e
            r8.<init>(r7, r0, r1, r2)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ShareKt.configureSharing$FlowKt__ShareKt(kotlinx.coroutines.flow.Flow, int):kotlinx.coroutines.flow.SharingConfig");
    }

    private static final <T> void launchSharing$FlowKt__ShareKt(h0 h0Var, g gVar, Flow<? extends T> flow, MutableSharedFlow<T> mutableSharedFlow, SharingStarted sharingStarted, T t6) {
        f.b(h0Var, gVar, null, new FlowKt__ShareKt$launchSharing$1(sharingStarted, flow, mutableSharedFlow, t6, null), 2, null);
    }

    private static final <T> void launchSharingDeferred$FlowKt__ShareKt(h0 h0Var, g gVar, Flow<? extends T> flow, t<StateFlow<T>> tVar) {
        f.b(h0Var, gVar, null, new FlowKt__ShareKt$launchSharingDeferred$1(flow, tVar, null), 2, null);
    }

    public static final <T> SharedFlow<T> onSubscription(SharedFlow<? extends T> sharedFlow, p<? super FlowCollector<? super T>, ? super d<? super r>, ? extends Object> pVar) {
        return new SubscribedSharedFlow(sharedFlow, pVar);
    }

    public static final <T> SharedFlow<T> shareIn(Flow<? extends T> flow, h0 h0Var, SharingStarted sharingStarted, int i7) {
        SharingConfig sharingConfigConfigureSharing$FlowKt__ShareKt = configureSharing$FlowKt__ShareKt(flow, i7);
        MutableSharedFlow MutableSharedFlow = SharedFlowKt.MutableSharedFlow(i7, sharingConfigConfigureSharing$FlowKt__ShareKt.extraBufferCapacity, sharingConfigConfigureSharing$FlowKt__ShareKt.onBufferOverflow);
        launchSharing$FlowKt__ShareKt(h0Var, sharingConfigConfigureSharing$FlowKt__ShareKt.context, sharingConfigConfigureSharing$FlowKt__ShareKt.upstream, MutableSharedFlow, sharingStarted, SharedFlowKt.NO_VALUE);
        return FlowKt.asSharedFlow(MutableSharedFlow);
    }

    public static /* synthetic */ SharedFlow shareIn$default(Flow flow, h0 h0Var, SharingStarted sharingStarted, int i7, int i8, Object obj) {
        if ((i8 & 4) != 0) {
            i7 = 0;
        }
        return FlowKt.shareIn(flow, h0Var, sharingStarted, i7);
    }

    public static final <T> StateFlow<T> stateIn(Flow<? extends T> flow, h0 h0Var, SharingStarted sharingStarted, T t6) {
        SharingConfig sharingConfigConfigureSharing$FlowKt__ShareKt = configureSharing$FlowKt__ShareKt(flow, 1);
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(t6);
        launchSharing$FlowKt__ShareKt(h0Var, sharingConfigConfigureSharing$FlowKt__ShareKt.context, sharingConfigConfigureSharing$FlowKt__ShareKt.upstream, MutableStateFlow, sharingStarted, t6);
        return FlowKt.asStateFlow(MutableStateFlow);
    }

    public static final <T> Object stateIn(Flow<? extends T> flow, h0 h0Var, d<? super StateFlow<? extends T>> dVar) {
        SharingConfig sharingConfigConfigureSharing$FlowKt__ShareKt = configureSharing$FlowKt__ShareKt(flow, 1);
        t tVarB = v.b(null, 1, null);
        launchSharingDeferred$FlowKt__ShareKt(h0Var, sharingConfigConfigureSharing$FlowKt__ShareKt.context, sharingConfigConfigureSharing$FlowKt__ShareKt.upstream, tVarB);
        return tVarB.V(dVar);
    }
}
