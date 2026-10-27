package kotlinx.coroutines.flow.internal;

import g5.j;
import g5.m;
import g5.r;
import h5.x;
import java.util.ArrayList;
import k5.d;
import k5.g;
import k5.h;
import kotlin.coroutines.jvm.internal.f;
import kotlin.coroutines.jvm.internal.l;
import kotlinx.coroutines.channels.a0;
import kotlinx.coroutines.channels.c0;
import kotlinx.coroutines.channels.k;
import kotlinx.coroutines.channels.y;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.h0;
import kotlinx.coroutines.i0;
import kotlinx.coroutines.k0;
import kotlinx.coroutines.m0;
import r5.p;

/* JADX INFO: compiled from: ChannelFlow.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ChannelFlow<T> implements FusibleFlow<T> {
    public final int capacity;
    public final g context;
    public final k onBufferOverflow;

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[k.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[k.SUSPEND.ordinal()] = 1;
            iArr[k.DROP_OLDEST.ordinal()] = 2;
            iArr[k.DROP_LATEST.ordinal()] = 3;
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.ChannelFlow$collect$2, reason: invalid class name */
    /* JADX INFO: compiled from: ChannelFlow.kt */
    @f(c = "kotlinx.coroutines.flow.internal.ChannelFlow$collect$2", f = "ChannelFlow.kt", l = {135}, m = "invokeSuspend")
    static final class AnonymousClass2 extends l implements p<h0, d<? super r>, Object> {
        final /* synthetic */ FlowCollector $collector;
        Object L$0;
        int label;
        private h0 p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(FlowCollector flowCollector, d dVar) {
            super(2, dVar);
            this.$collector = flowCollector;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final d<r> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$collector, dVar);
            anonymousClass2.p$ = (h0) obj;
            return anonymousClass2;
        }

        @Override // r5.p
        public final Object invoke(h0 h0Var, d<? super r> dVar) {
            return ((AnonymousClass2) create(h0Var, dVar)).invokeSuspend(r.f6563a);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objD = l5.d.d();
            int i7 = this.label;
            if (i7 == 0) {
                m.b(obj);
                h0 h0Var = this.p$;
                FlowCollector flowCollector = this.$collector;
                c0<T> c0VarProduceImpl = ChannelFlow.this.produceImpl(h0Var);
                this.L$0 = h0Var;
                this.label = 1;
                if (FlowKt.emitAll(flowCollector, c0VarProduceImpl, this) == objD) {
                    return objD;
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                m.b(obj);
            }
            return r.f6563a;
        }
    }

    public ChannelFlow(g gVar, int i7, k kVar) {
        this.context = gVar;
        this.capacity = i7;
        this.onBufferOverflow = kVar;
    }

    static /* synthetic */ Object collect$suspendImpl(ChannelFlow channelFlow, FlowCollector flowCollector, d dVar) {
        Object objA = i0.a(new AnonymousClass2(flowCollector, null), dVar);
        return objA == l5.d.d() ? objA : r.f6563a;
    }

    private final int getProduceCapacity() {
        int i7 = this.capacity;
        if (i7 == -3) {
            return -2;
        }
        return i7;
    }

    protected String additionalToStringProps() {
        return null;
    }

    public kotlinx.coroutines.channels.g<T> broadcastImpl(h0 h0Var, k0 k0Var) {
        int produceCapacity;
        int i7 = WhenMappings.$EnumSwitchMapping$0[this.onBufferOverflow.ordinal()];
        if (i7 == 1) {
            produceCapacity = getProduceCapacity();
        } else {
            if (i7 != 2) {
                if (i7 != 3) {
                    throw new j();
                }
                throw new IllegalArgumentException("Broadcast channel does not support BufferOverflow.DROP_LATEST");
            }
            produceCapacity = -1;
        }
        return kotlinx.coroutines.channels.j.b(h0Var, this.context, produceCapacity, k0Var, null, getCollectToFun$kotlinx_coroutines_core(), 8, null);
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super T> flowCollector, d<? super r> dVar) {
        return collect$suspendImpl(this, flowCollector, dVar);
    }

    protected abstract Object collectTo(a0<? super T> a0Var, d<? super r> dVar);

    protected abstract ChannelFlow<T> create(g gVar, int i7, k kVar);

    public Flow<T> dropChannelOperators() {
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0013 A[PHI: r4
  0x0013: PHI (r4v5 int) = (r4v2 int), (r4v2 int), (r4v4 int) binds: [B:8:0x0011, B:13:0x0019, B:16:0x001d] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public kotlinx.coroutines.flow.Flow<T> fuse(k5.g r2, int r3, kotlinx.coroutines.channels.k r4) {
        /*
            r1 = this;
            k5.g r0 = r1.context
            k5.g r2 = r2.plus(r0)
            kotlinx.coroutines.channels.k r0 = kotlinx.coroutines.channels.k.SUSPEND
            if (r4 == r0) goto Lb
            goto L25
        Lb:
            int r4 = r1.capacity
            r0 = -3
            if (r4 != r0) goto L11
            goto L23
        L11:
            if (r3 != r0) goto L15
        L13:
            r3 = r4
            goto L23
        L15:
            r0 = -2
            if (r4 != r0) goto L19
            goto L23
        L19:
            if (r3 != r0) goto L1c
            goto L13
        L1c:
            int r4 = r4 + r3
            if (r4 < 0) goto L20
            goto L13
        L20:
            r3 = 2147483647(0x7fffffff, float:NaN)
        L23:
            kotlinx.coroutines.channels.k r4 = r1.onBufferOverflow
        L25:
            k5.g r0 = r1.context
            boolean r0 = kotlin.jvm.internal.l.a(r2, r0)
            if (r0 == 0) goto L36
            int r0 = r1.capacity
            if (r3 != r0) goto L36
            kotlinx.coroutines.channels.k r0 = r1.onBufferOverflow
            if (r4 != r0) goto L36
            return r1
        L36:
            kotlinx.coroutines.flow.internal.ChannelFlow r2 = r1.create(r2, r3, r4)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.ChannelFlow.fuse(k5.g, int, kotlinx.coroutines.channels.k):kotlinx.coroutines.flow.Flow");
    }

    public final p<a0<? super T>, d<? super r>, Object> getCollectToFun$kotlinx_coroutines_core() {
        return new ChannelFlow$collectToFun$1(this, null);
    }

    public c0<T> produceImpl(h0 h0Var) {
        return y.e(h0Var, this.context, getProduceCapacity(), this.onBufferOverflow, k0.ATOMIC, null, getCollectToFun$kotlinx_coroutines_core(), 16, null);
    }

    public String toString() {
        ArrayList arrayList = new ArrayList(4);
        String strAdditionalToStringProps = additionalToStringProps();
        if (strAdditionalToStringProps != null) {
            arrayList.add(strAdditionalToStringProps);
        }
        if (this.context != h.f7276e) {
            arrayList.add("context=" + this.context);
        }
        if (this.capacity != -3) {
            arrayList.add("capacity=" + this.capacity);
        }
        if (this.onBufferOverflow != k.SUSPEND) {
            arrayList.add("onBufferOverflow=" + this.onBufferOverflow);
        }
        return m0.a(this) + '[' + x.A(arrayList, ", ", null, null, 0, null, null, 62, null) + ']';
    }
}
