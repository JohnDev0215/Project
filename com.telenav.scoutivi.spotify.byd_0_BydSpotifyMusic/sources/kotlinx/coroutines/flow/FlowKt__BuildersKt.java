package kotlinx.coroutines.flow;

import g5.m;
import g5.r;
import java.util.Iterator;
import k5.d;
import kotlin.coroutines.jvm.internal.f;
import kotlin.coroutines.jvm.internal.l;
import kotlinx.coroutines.channels.a0;
import kotlinx.coroutines.channels.g0;
import kotlinx.coroutines.channels.y;
import kotlinx.coroutines.h0;
import r5.a;
import r5.p;
import v5.c;
import v5.e;

/* JADX INFO: compiled from: Builders.kt */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class FlowKt__BuildersKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__BuildersKt$flowViaChannel$1, reason: invalid class name */
    /* JADX INFO: compiled from: Builders.kt */
    @f(c = "kotlinx.coroutines.flow.FlowKt__BuildersKt$flowViaChannel$1", f = "Builders.kt", l = {216}, m = "invokeSuspend")
    static final class AnonymousClass1<T> extends l implements p<a0<? super T>, d<? super r>, Object> {
        final /* synthetic */ p $block;
        Object L$0;
        int label;
        private a0 p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(p pVar, d dVar) {
            super(2, dVar);
            this.$block = pVar;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final d<r> create(Object obj, d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$block, dVar);
            anonymousClass1.p$ = (a0) obj;
            return anonymousClass1;
        }

        @Override // r5.p
        public final Object invoke(Object obj, d<? super r> dVar) {
            return ((AnonymousClass1) create(obj, dVar)).invokeSuspend(r.f6563a);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objD = l5.d.d();
            int i7 = this.label;
            if (i7 == 0) {
                m.b(obj);
                a0 a0Var = this.p$;
                this.$block.invoke(a0Var, a0Var.getChannel());
                this.L$0 = a0Var;
                this.label = 1;
                if (y.b(a0Var, null, this, 1, null) == objD) {
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

    public static final <T> Flow<T> asFlow(final a<? extends T> aVar) {
        return new Flow<T>() { // from class: kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, d dVar) {
                Object objEmit = flowCollector.emit(aVar.invoke(), dVar);
                return objEmit == l5.d.d() ? objEmit : r.f6563a;
            }
        };
    }

    public static final <T> Flow<T> callbackFlow(p<? super a0<? super T>, ? super d<? super r>, ? extends Object> pVar) {
        return new CallbackFlowBuilder(pVar, null, 0, null, 14, null);
    }

    public static final <T> Flow<T> channelFlow(p<? super a0<? super T>, ? super d<? super r>, ? extends Object> pVar) {
        return new ChannelFlowBuilder(pVar, null, 0, null, 14, null);
    }

    public static final <T> Flow<T> emptyFlow() {
        return EmptyFlow.INSTANCE;
    }

    public static final <T> Flow<T> flow(p<? super FlowCollector<? super T>, ? super d<? super r>, ? extends Object> pVar) {
        return new SafeFlow(pVar);
    }

    public static final <T> Flow<T> flowOf(T... tArr) {
        return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1(tArr);
    }

    public static final <T> Flow<T> flowViaChannel(int i7, p<? super h0, ? super g0<? super T>, r> pVar) {
        return FlowKt__ContextKt.buffer$default(FlowKt.channelFlow(new AnonymousClass1(pVar, null)), i7, null, 2, null);
    }

    public static /* synthetic */ Flow flowViaChannel$default(int i7, p pVar, int i8, Object obj) {
        if ((i8 & 1) != 0) {
            i7 = -2;
        }
        return FlowKt.flowViaChannel(i7, pVar);
    }

    public static final <T> Flow<T> asFlow(r5.l<? super d<? super T>, ? extends Object> lVar) {
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2(lVar);
    }

    public static final <T> Flow<T> flowOf(final T t6) {
        return new Flow<T>() { // from class: kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, d dVar) {
                Object objEmit = flowCollector.emit(t6, dVar);
                return objEmit == l5.d.d() ? objEmit : r.f6563a;
            }
        };
    }

    public static final <T> Flow<T> asFlow(Iterable<? extends T> iterable) {
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3(iterable);
    }

    public static final <T> Flow<T> asFlow(Iterator<? extends T> it) {
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$4(it);
    }

    public static final <T> Flow<T> asFlow(x5.d<? extends T> dVar) {
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$5(dVar);
    }

    public static final <T> Flow<T> asFlow(T[] tArr) {
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6(tArr);
    }

    public static final Flow<Integer> asFlow(int[] iArr) {
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7(iArr);
    }

    public static final Flow<Long> asFlow(long[] jArr) {
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8(jArr);
    }

    public static final Flow<Integer> asFlow(c cVar) {
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9(cVar);
    }

    public static final Flow<Long> asFlow(e eVar) {
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10(eVar);
    }
}
