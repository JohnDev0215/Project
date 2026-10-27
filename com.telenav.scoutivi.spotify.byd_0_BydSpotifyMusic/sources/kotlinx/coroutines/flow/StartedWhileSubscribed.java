package kotlinx.coroutines.flow;

import g5.m;
import g5.r;
import h5.o;
import h5.x;
import java.util.List;
import k5.d;
import kotlin.coroutines.jvm.internal.b;
import kotlin.coroutines.jvm.internal.f;
import kotlin.coroutines.jvm.internal.l;
import r5.p;
import r5.q;

/* JADX INFO: compiled from: SharingStarted.kt */
/* JADX INFO: loaded from: classes.dex */
final class StartedWhileSubscribed implements SharingStarted {
    private final long replayExpiration;
    private final long stopTimeout;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.StartedWhileSubscribed$command$1, reason: invalid class name */
    /* JADX INFO: compiled from: SharingStarted.kt */
    @f(c = "kotlinx.coroutines.flow.StartedWhileSubscribed$command$1", f = "SharingStarted.kt", l = {181, 183, 185, 186, 188}, m = "invokeSuspend")
    static final class AnonymousClass1 extends l implements q<FlowCollector<? super SharingCommand>, Integer, d<? super r>, Object> {
        int I$0;
        Object L$0;
        int label;
        private FlowCollector p$;
        private int p$0;

        AnonymousClass1(d dVar) {
            super(3, dVar);
        }

        public final d<r> create(FlowCollector<? super SharingCommand> flowCollector, int i7, d<? super r> dVar) {
            AnonymousClass1 anonymousClass1 = StartedWhileSubscribed.this.new AnonymousClass1(dVar);
            anonymousClass1.p$ = flowCollector;
            anonymousClass1.p$0 = i7;
            return anonymousClass1;
        }

        @Override // r5.q
        public final Object invoke(FlowCollector<? super SharingCommand> flowCollector, Integer num, d<? super r> dVar) {
            return ((AnonymousClass1) create(flowCollector, num.intValue(), dVar)).invokeSuspend(r.f6563a);
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x007f  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00a1 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00a2  */
        @Override // kotlin.coroutines.jvm.internal.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) throws java.lang.Throwable {
            /*
                r10 = this;
                java.lang.Object r0 = l5.b.d()
                int r1 = r10.label
                r2 = 5
                r3 = 4
                r4 = 3
                r5 = 2
                r6 = 1
                if (r1 == 0) goto L47
                if (r1 == r6) goto L3f
                if (r1 == r5) goto L35
                if (r1 == r4) goto L2b
                if (r1 == r3) goto L20
                if (r1 != r2) goto L18
                goto L3f
            L18:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L20:
                int r1 = r10.I$0
                java.lang.Object r3 = r10.L$0
                kotlinx.coroutines.flow.FlowCollector r3 = (kotlinx.coroutines.flow.FlowCollector) r3
                g5.m.b(r11)
                goto La3
            L2b:
                int r1 = r10.I$0
                java.lang.Object r4 = r10.L$0
                kotlinx.coroutines.flow.FlowCollector r4 = (kotlinx.coroutines.flow.FlowCollector) r4
                g5.m.b(r11)
                goto L8f
            L35:
                int r1 = r10.I$0
                java.lang.Object r5 = r10.L$0
                kotlinx.coroutines.flow.FlowCollector r5 = (kotlinx.coroutines.flow.FlowCollector) r5
                g5.m.b(r11)
                goto L73
            L3f:
                java.lang.Object r0 = r10.L$0
                kotlinx.coroutines.flow.FlowCollector r0 = (kotlinx.coroutines.flow.FlowCollector) r0
                g5.m.b(r11)
                goto Lb3
            L47:
                g5.m.b(r11)
                kotlinx.coroutines.flow.FlowCollector r11 = r10.p$
                int r1 = r10.p$0
                if (r1 <= 0) goto L5f
                kotlinx.coroutines.flow.SharingCommand r2 = kotlinx.coroutines.flow.SharingCommand.START
                r10.L$0 = r11
                r10.I$0 = r1
                r10.label = r6
                java.lang.Object r11 = r11.emit(r2, r10)
                if (r11 != r0) goto Lb3
                return r0
            L5f:
                kotlinx.coroutines.flow.StartedWhileSubscribed r6 = kotlinx.coroutines.flow.StartedWhileSubscribed.this
                long r6 = kotlinx.coroutines.flow.StartedWhileSubscribed.access$getStopTimeout$p(r6)
                r10.L$0 = r11
                r10.I$0 = r1
                r10.label = r5
                java.lang.Object r5 = kotlinx.coroutines.r0.a(r6, r10)
                if (r5 != r0) goto L72
                return r0
            L72:
                r5 = r11
            L73:
                kotlinx.coroutines.flow.StartedWhileSubscribed r11 = kotlinx.coroutines.flow.StartedWhileSubscribed.this
                long r6 = kotlinx.coroutines.flow.StartedWhileSubscribed.access$getReplayExpiration$p(r11)
                r8 = 0
                int r11 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r11 <= 0) goto La4
                kotlinx.coroutines.flow.SharingCommand r11 = kotlinx.coroutines.flow.SharingCommand.STOP
                r10.L$0 = r5
                r10.I$0 = r1
                r10.label = r4
                java.lang.Object r11 = r5.emit(r11, r10)
                if (r11 != r0) goto L8e
                return r0
            L8e:
                r4 = r5
            L8f:
                kotlinx.coroutines.flow.StartedWhileSubscribed r11 = kotlinx.coroutines.flow.StartedWhileSubscribed.this
                long r5 = kotlinx.coroutines.flow.StartedWhileSubscribed.access$getReplayExpiration$p(r11)
                r10.L$0 = r4
                r10.I$0 = r1
                r10.label = r3
                java.lang.Object r11 = kotlinx.coroutines.r0.a(r5, r10)
                if (r11 != r0) goto La2
                return r0
            La2:
                r3 = r4
            La3:
                r5 = r3
            La4:
                kotlinx.coroutines.flow.SharingCommand r11 = kotlinx.coroutines.flow.SharingCommand.STOP_AND_RESET_REPLAY_CACHE
                r10.L$0 = r5
                r10.I$0 = r1
                r10.label = r2
                java.lang.Object r11 = r5.emit(r11, r10)
                if (r11 != r0) goto Lb3
                return r0
            Lb3:
                g5.r r11 = g5.r.f6563a
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.StartedWhileSubscribed.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.StartedWhileSubscribed$command$2, reason: invalid class name */
    /* JADX INFO: compiled from: SharingStarted.kt */
    @f(c = "kotlinx.coroutines.flow.StartedWhileSubscribed$command$2", f = "SharingStarted.kt", l = {}, m = "invokeSuspend")
    static final class AnonymousClass2 extends l implements p<SharingCommand, d<? super Boolean>, Object> {
        int label;
        private SharingCommand p$0;

        AnonymousClass2(d dVar) {
            super(2, dVar);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final d<r> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(dVar);
            anonymousClass2.p$0 = (SharingCommand) obj;
            return anonymousClass2;
        }

        @Override // r5.p
        public final Object invoke(SharingCommand sharingCommand, d<? super Boolean> dVar) {
            return ((AnonymousClass2) create(sharingCommand, dVar)).invokeSuspend(r.f6563a);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            l5.d.d();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            m.b(obj);
            return b.a(this.p$0 != SharingCommand.START);
        }
    }

    public StartedWhileSubscribed(long j7, long j8) {
        this.stopTimeout = j7;
        this.replayExpiration = j8;
        if (!(j7 >= 0)) {
            throw new IllegalArgumentException(("stopTimeout(" + j7 + " ms) cannot be negative").toString());
        }
        if (j8 >= 0) {
            return;
        }
        throw new IllegalArgumentException(("replayExpiration(" + j8 + " ms) cannot be negative").toString());
    }

    @Override // kotlinx.coroutines.flow.SharingStarted
    public Flow<SharingCommand> command(StateFlow<Integer> stateFlow) {
        return FlowKt.distinctUntilChanged(FlowKt.dropWhile(FlowKt.transformLatest(stateFlow, new AnonymousClass1(null)), new AnonymousClass2(null)));
    }

    public boolean equals(Object obj) {
        if (obj instanceof StartedWhileSubscribed) {
            StartedWhileSubscribed startedWhileSubscribed = (StartedWhileSubscribed) obj;
            if (this.stopTimeout == startedWhileSubscribed.stopTimeout && this.replayExpiration == startedWhileSubscribed.replayExpiration) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (Long.valueOf(this.stopTimeout).hashCode() * 31) + Long.valueOf(this.replayExpiration).hashCode();
    }

    public String toString() {
        List listC = o.c(2);
        if (this.stopTimeout > 0) {
            listC.add("stopTimeout=" + this.stopTimeout + "ms");
        }
        if (this.replayExpiration < Long.MAX_VALUE) {
            listC.add("replayExpiration=" + this.replayExpiration + "ms");
        }
        return "SharingStarted.WhileSubscribed(" + x.A(o.a(listC), null, null, null, 0, null, null, 63, null) + ')';
    }
}
