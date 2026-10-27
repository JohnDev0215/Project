package kotlinx.coroutines.flow;

import g5.m;
import g5.r;
import k5.d;
import kotlin.coroutines.jvm.internal.b;
import kotlin.coroutines.jvm.internal.f;
import kotlin.coroutines.jvm.internal.l;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.h0;
import r5.p;

/* JADX INFO: compiled from: Share.kt */
/* JADX INFO: loaded from: classes.dex */
@f(c = "kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1", f = "Share.kt", l = {206, 210, 211, 217}, m = "invokeSuspend")
final class FlowKt__ShareKt$launchSharing$1 extends l implements p<h0, d<? super r>, Object> {
    final /* synthetic */ Object $initialValue;
    final /* synthetic */ MutableSharedFlow $shared;
    final /* synthetic */ SharingStarted $started;
    final /* synthetic */ Flow $upstream;
    Object L$0;
    int label;
    private h0 p$;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: Share.kt */
    @f(c = "kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$1", f = "Share.kt", l = {}, m = "invokeSuspend")
    static final class AnonymousClass1 extends l implements p<Integer, d<? super Boolean>, Object> {
        int label;
        private int p$0;

        AnonymousClass1(d dVar) {
            super(2, dVar);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final d<r> create(Object obj, d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(dVar);
            Number number = (Number) obj;
            number.intValue();
            anonymousClass1.p$0 = number.intValue();
            return anonymousClass1;
        }

        @Override // r5.p
        public final Object invoke(Integer num, d<? super Boolean> dVar) {
            return ((AnonymousClass1) create(num, dVar)).invokeSuspend(r.f6563a);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            l5.d.d();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            m.b(obj);
            return b.a(this.p$0 > 0);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: Share.kt */
    @f(c = "kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$2", f = "Share.kt", l = {219}, m = "invokeSuspend")
    static final class AnonymousClass2 extends l implements p<SharingCommand, d<? super r>, Object> {
        Object L$0;
        int label;
        private SharingCommand p$0;

        AnonymousClass2(d dVar) {
            super(2, dVar);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final d<r> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = FlowKt__ShareKt$launchSharing$1.this.new AnonymousClass2(dVar);
            anonymousClass2.p$0 = (SharingCommand) obj;
            return anonymousClass2;
        }

        @Override // r5.p
        public final Object invoke(SharingCommand sharingCommand, d<? super r> dVar) {
            return ((AnonymousClass2) create(sharingCommand, dVar)).invokeSuspend(r.f6563a);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objD = l5.d.d();
            int i7 = this.label;
            if (i7 == 0) {
                m.b(obj);
                SharingCommand sharingCommand = this.p$0;
                int i8 = FlowKt.WhenMappings.$EnumSwitchMapping$0[sharingCommand.ordinal()];
                if (i8 == 1) {
                    FlowKt__ShareKt$launchSharing$1 flowKt__ShareKt$launchSharing$1 = FlowKt__ShareKt$launchSharing$1.this;
                    Flow flow = flowKt__ShareKt$launchSharing$1.$upstream;
                    MutableSharedFlow mutableSharedFlow = flowKt__ShareKt$launchSharing$1.$shared;
                    this.L$0 = sharingCommand;
                    this.label = 1;
                    if (flow.collect(mutableSharedFlow, this) == objD) {
                        return objD;
                    }
                } else if (i8 == 3) {
                    FlowKt__ShareKt$launchSharing$1 flowKt__ShareKt$launchSharing$12 = FlowKt__ShareKt$launchSharing$1.this;
                    Object obj2 = flowKt__ShareKt$launchSharing$12.$initialValue;
                    if (obj2 == SharedFlowKt.NO_VALUE) {
                        flowKt__ShareKt$launchSharing$12.$shared.resetReplayCache();
                    } else {
                        flowKt__ShareKt$launchSharing$12.$shared.tryEmit(obj2);
                    }
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FlowKt__ShareKt$launchSharing$1(SharingStarted sharingStarted, Flow flow, MutableSharedFlow mutableSharedFlow, Object obj, d dVar) {
        super(2, dVar);
        this.$started = sharingStarted;
        this.$upstream = flow;
        this.$shared = mutableSharedFlow;
        this.$initialValue = obj;
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final d<r> create(Object obj, d<?> dVar) {
        FlowKt__ShareKt$launchSharing$1 flowKt__ShareKt$launchSharing$1 = new FlowKt__ShareKt$launchSharing$1(this.$started, this.$upstream, this.$shared, this.$initialValue, dVar);
        flowKt__ShareKt$launchSharing$1.p$ = (h0) obj;
        return flowKt__ShareKt$launchSharing$1;
    }

    @Override // r5.p
    public final Object invoke(h0 h0Var, d<? super r> dVar) {
        return ((FlowKt__ShareKt$launchSharing$1) create(h0Var, dVar)).invokeSuspend(r.f6563a);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0078 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) throws java.lang.Throwable {
        /*
            r8 = this;
            java.lang.Object r0 = l5.b.d()
            int r1 = r8.label
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L2d
            if (r1 == r5) goto L25
            if (r1 == r4) goto L1d
            if (r1 == r3) goto L25
            if (r1 != r2) goto L15
            goto L25
        L15:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L1d:
            java.lang.Object r1 = r8.L$0
            kotlinx.coroutines.h0 r1 = (kotlinx.coroutines.h0) r1
            g5.m.b(r9)
            goto L6a
        L25:
            java.lang.Object r0 = r8.L$0
            kotlinx.coroutines.h0 r0 = (kotlinx.coroutines.h0) r0
            g5.m.b(r9)
            goto L99
        L2d:
            g5.m.b(r9)
            kotlinx.coroutines.h0 r1 = r8.p$
            kotlinx.coroutines.flow.SharingStarted r9 = r8.$started
            kotlinx.coroutines.flow.SharingStarted$Companion r6 = kotlinx.coroutines.flow.SharingStarted.Companion
            kotlinx.coroutines.flow.SharingStarted r7 = r6.getEagerly()
            if (r9 != r7) goto L4b
            kotlinx.coroutines.flow.Flow r9 = r8.$upstream
            kotlinx.coroutines.flow.MutableSharedFlow r2 = r8.$shared
            r8.L$0 = r1
            r8.label = r5
            java.lang.Object r9 = r9.collect(r2, r8)
            if (r9 != r0) goto L99
            return r0
        L4b:
            kotlinx.coroutines.flow.SharingStarted r9 = r8.$started
            kotlinx.coroutines.flow.SharingStarted r5 = r6.getLazily()
            r6 = 0
            if (r9 != r5) goto L79
            kotlinx.coroutines.flow.MutableSharedFlow r9 = r8.$shared
            kotlinx.coroutines.flow.StateFlow r9 = r9.getSubscriptionCount()
            kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$1 r2 = new kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$1
            r2.<init>(r6)
            r8.L$0 = r1
            r8.label = r4
            java.lang.Object r9 = kotlinx.coroutines.flow.FlowKt.first(r9, r2, r8)
            if (r9 != r0) goto L6a
            return r0
        L6a:
            kotlinx.coroutines.flow.Flow r9 = r8.$upstream
            kotlinx.coroutines.flow.MutableSharedFlow r2 = r8.$shared
            r8.L$0 = r1
            r8.label = r3
            java.lang.Object r9 = r9.collect(r2, r8)
            if (r9 != r0) goto L99
            return r0
        L79:
            kotlinx.coroutines.flow.SharingStarted r9 = r8.$started
            kotlinx.coroutines.flow.MutableSharedFlow r3 = r8.$shared
            kotlinx.coroutines.flow.StateFlow r3 = r3.getSubscriptionCount()
            kotlinx.coroutines.flow.Flow r9 = r9.command(r3)
            kotlinx.coroutines.flow.Flow r9 = kotlinx.coroutines.flow.FlowKt.distinctUntilChanged(r9)
            kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$2 r3 = new kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$2
            r3.<init>(r6)
            r8.L$0 = r1
            r8.label = r2
            java.lang.Object r9 = kotlinx.coroutines.flow.FlowKt.collectLatest(r9, r3, r8)
            if (r9 != r0) goto L99
            return r0
        L99:
            g5.r r9 = g5.r.f6563a
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
