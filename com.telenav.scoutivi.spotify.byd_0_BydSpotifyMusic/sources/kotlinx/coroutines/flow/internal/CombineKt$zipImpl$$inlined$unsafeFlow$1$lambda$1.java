package kotlinx.coroutines.flow.internal;

import g5.m;
import g5.r;
import k5.d;
import k5.g;
import kotlin.coroutines.jvm.internal.l;
import kotlinx.coroutines.channels.a0;
import kotlinx.coroutines.channels.c0;
import kotlinx.coroutines.channels.g0;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.h0;
import kotlinx.coroutines.w;
import r5.p;

/* JADX INFO: compiled from: Combine.kt */
/* JADX INFO: loaded from: classes.dex */
final class CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 extends l implements p<h0, d<? super r>, Object> {
    final /* synthetic */ FlowCollector $this_unsafeFlow;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    private h0 p$;
    final /* synthetic */ CombineKt$zipImpl$$inlined$unsafeFlow$1 this$0;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: Combine.kt */
    static final class AnonymousClass1 extends l implements p<a0<? super Object>, d<? super r>, Object> {
        Object L$0;
        Object L$1;
        int label;
        private a0 p$;

        AnonymousClass1(d dVar) {
            super(2, dVar);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final d<r> create(Object obj, d<?> dVar) {
            AnonymousClass1 anonymousClass1 = CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.this.new AnonymousClass1(dVar);
            anonymousClass1.p$ = (a0) obj;
            return anonymousClass1;
        }

        @Override // r5.p
        public final Object invoke(a0<? super Object> a0Var, d<? super r> dVar) {
            return ((AnonymousClass1) create(a0Var, dVar)).invokeSuspend(r.f6563a);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objD = l5.d.d();
            int i7 = this.label;
            if (i7 == 0) {
                m.b(obj);
                final a0 a0Var = this.p$;
                Flow flow = CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.this.this$0.$flow2$inlined;
                Object obj2 = new FlowCollector<T2>() { // from class: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$.inlined.unsafeFlow.1.lambda.1.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(Object obj3, d dVar) {
                        g0 channel = a0Var.getChannel();
                        if (obj3 == null) {
                            obj3 = NullSurrogateKt.NULL;
                        }
                        Object objSend = channel.send(obj3, dVar);
                        return objSend == l5.d.d() ? objSend : r.f6563a;
                    }
                };
                this.L$0 = a0Var;
                this.L$1 = flow;
                this.label = 1;
                if (flow.collect(obj2, this) == objD) {
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

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: Combine.kt */
    static final class AnonymousClass2 extends kotlin.jvm.internal.m implements r5.l<Throwable, r> {
        final /* synthetic */ w $collectJob;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(w wVar) {
            super(1);
            this.$collectJob = wVar;
        }

        @Override // r5.l
        public /* bridge */ /* synthetic */ r invoke(Throwable th) {
            invoke2(th);
            return r.f6563a;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Throwable th) {
            if (this.$collectJob.isActive()) {
                this.$collectJob.cancel(new AbortFlowException(CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.this.$this_unsafeFlow));
            }
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1$3, reason: invalid class name */
    /* JADX INFO: compiled from: Combine.kt */
    static final class AnonymousClass3 extends l implements p<r, d<? super r>, Object> {
        final /* synthetic */ Object $cnt;
        final /* synthetic */ g $scopeContext;
        final /* synthetic */ c0 $second;
        Object L$0;
        Object L$1;
        int label;
        private r p$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(g gVar, Object obj, c0 c0Var, d dVar) {
            super(2, dVar);
            this.$scopeContext = gVar;
            this.$cnt = obj;
            this.$second = c0Var;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final d<r> create(Object obj, d<?> dVar) {
            AnonymousClass3 anonymousClass3 = CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.this.new AnonymousClass3(this.$scopeContext, this.$cnt, this.$second, dVar);
            anonymousClass3.p$0 = (r) obj;
            return anonymousClass3;
        }

        @Override // r5.p
        public final Object invoke(r rVar, d<? super r> dVar) {
            return ((AnonymousClass3) create(rVar, dVar)).invokeSuspend(r.f6563a);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objD = l5.d.d();
            int i7 = this.label;
            if (i7 == 0) {
                m.b(obj);
                r rVar = this.p$0;
                Flow flow = CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.this.this$0.$flow$inlined;
                Object obj2 = new FlowCollector<T1>() { // from class: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$.inlined.unsafeFlow.1.lambda.1.3.1

                    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1$3$1$1, reason: invalid class name and collision with other inner class name */
                    /* JADX INFO: compiled from: Combine.kt */
                    static final class C01091 extends l implements p<r, d<? super r>, Object> {
                        final /* synthetic */ Object $value;
                        Object L$0;
                        Object L$1;
                        Object L$2;
                        int label;
                        private r p$0;
                        final /* synthetic */ AnonymousClass1 this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C01091(Object obj, d dVar, AnonymousClass1 anonymousClass1) {
                            super(2, dVar);
                            this.$value = obj;
                            this.this$0 = anonymousClass1;
                        }

                        @Override // kotlin.coroutines.jvm.internal.a
                        public final d<r> create(Object obj, d<?> dVar) {
                            C01091 c01091 = new C01091(this.$value, dVar, this.this$0);
                            c01091.p$0 = (r) obj;
                            return c01091;
                        }

                        @Override // r5.p
                        public final Object invoke(r rVar, d<? super r> dVar) {
                            return ((C01091) create(rVar, dVar)).invokeSuspend(r.f6563a);
                        }

                        /* JADX WARN: Removed duplicated region for block: B:27:0x0094 A[RETURN] */
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
                                r2 = 3
                                r3 = 2
                                r4 = 1
                                if (r1 == 0) goto L39
                                if (r1 == r4) goto L30
                                if (r1 == r3) goto L22
                                if (r1 != r2) goto L1a
                                java.lang.Object r0 = r8.L$0
                                g5.r r0 = (g5.r) r0
                                g5.m.b(r9)
                                goto L95
                            L1a:
                                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                                r9.<init>(r0)
                                throw r9
                            L22:
                                java.lang.Object r1 = r8.L$2
                                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                                java.lang.Object r3 = r8.L$1
                                java.lang.Object r4 = r8.L$0
                                g5.r r4 = (g5.r) r4
                                g5.m.b(r9)
                                goto L88
                            L30:
                                java.lang.Object r1 = r8.L$0
                                g5.r r1 = (g5.r) r1
                                g5.m.b(r9)
                                r4 = r1
                                goto L51
                            L39:
                                g5.m.b(r9)
                                g5.r r9 = r8.p$0
                                kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1$3$1 r1 = r8.this$0
                                kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1$3 r1 = kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.AnonymousClass3.this
                                kotlinx.coroutines.channels.c0 r1 = r1.$second
                                r8.L$0 = r9
                                r8.label = r4
                                java.lang.Object r1 = kotlinx.coroutines.channels.p.b(r1, r8)
                                if (r1 != r0) goto L4f
                                return r0
                            L4f:
                                r4 = r9
                                r9 = r1
                            L51:
                                if (r9 == 0) goto L98
                                kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1$3$1 r1 = r8.this$0
                                kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1$3 r1 = kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.AnonymousClass3.this
                                kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 r1 = kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.this
                                kotlinx.coroutines.flow.FlowCollector r5 = r1.$this_unsafeFlow
                                kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1 r1 = r1.this$0
                                r5.q r1 = r1.$transform$inlined
                                java.lang.Object r6 = r8.$value
                                kotlinx.coroutines.internal.c0 r7 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
                                if (r9 != r7) goto L67
                                r7 = 0
                                goto L68
                            L67:
                                r7 = r9
                            L68:
                                r8.L$0 = r4
                                r8.L$1 = r9
                                r8.L$2 = r5
                                r8.label = r3
                                r3 = 6
                                kotlin.jvm.internal.k.a(r3)
                                kotlin.jvm.internal.k.a(r3)
                                java.lang.Object r1 = r1.invoke(r6, r7, r8)
                                r3 = 7
                                kotlin.jvm.internal.k.a(r3)
                                kotlin.jvm.internal.k.a(r3)
                                if (r1 != r0) goto L85
                                return r0
                            L85:
                                r3 = r9
                                r9 = r1
                                r1 = r5
                            L88:
                                r8.L$0 = r4
                                r8.L$1 = r3
                                r8.label = r2
                                java.lang.Object r9 = r1.emit(r9, r8)
                                if (r9 != r0) goto L95
                                return r0
                            L95:
                                g5.r r9 = g5.r.f6563a
                                return r9
                            L98:
                                kotlinx.coroutines.flow.internal.AbortFlowException r9 = new kotlinx.coroutines.flow.internal.AbortFlowException
                                kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1$3$1 r0 = r8.this$0
                                kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1$3 r0 = kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.AnonymousClass3.this
                                kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 r0 = kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.this
                                kotlinx.coroutines.flow.FlowCollector r0 = r0.$this_unsafeFlow
                                r9.<init>(r0)
                                throw r9
                            */
                            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.AnonymousClass3.AnonymousClass1.C01091.invokeSuspend(java.lang.Object):java.lang.Object");
                        }
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(Object obj3, d dVar) {
                        AnonymousClass3 anonymousClass3 = AnonymousClass3.this;
                        g gVar = anonymousClass3.$scopeContext;
                        r rVar2 = r.f6563a;
                        Object objWithContextUndispatched = ChannelFlowKt.withContextUndispatched(gVar, rVar2, anonymousClass3.$cnt, new C01091(obj3, null, this), dVar);
                        return objWithContextUndispatched == l5.d.d() ? objWithContextUndispatched : rVar2;
                    }
                };
                this.L$0 = rVar;
                this.L$1 = flow;
                this.label = 1;
                if (flow.collect(obj2, this) == objD) {
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1(FlowCollector flowCollector, d dVar, CombineKt$zipImpl$$inlined$unsafeFlow$1 combineKt$zipImpl$$inlined$unsafeFlow$1) {
        super(2, dVar);
        this.$this_unsafeFlow = flowCollector;
        this.this$0 = combineKt$zipImpl$$inlined$unsafeFlow$1;
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final d<r> create(Object obj, d<?> dVar) {
        CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 = new CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1(this.$this_unsafeFlow, dVar, this.this$0);
        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.p$ = (h0) obj;
        return combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1;
    }

    @Override // r5.p
    public final Object invoke(h0 h0Var, d<? super r> dVar) {
        return ((CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1) create(h0Var, dVar)).invokeSuspend(r.f6563a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00a2 A[PHI: r1
  0x00a2: PHI (r1v13 kotlinx.coroutines.channels.c0) = (r1v12 kotlinx.coroutines.channels.c0), (r1v14 kotlinx.coroutines.channels.c0) binds: [B:28:0x00b4, B:20:0x00a0] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1, types: [kotlinx.coroutines.channels.c0] */
    /* JADX WARN: Type inference failed for: r1v5 */
    @Override // kotlin.coroutines.jvm.internal.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r21) throws java.lang.Throwable {
        /*
            r20 = this;
            r8 = r20
            java.lang.Object r0 = l5.b.d()
            int r1 = r8.label
            r9 = 1
            r10 = 0
            if (r1 == 0) goto L32
            if (r1 != r9) goto L2a
            java.lang.Object r0 = r8.L$3
            k5.g r0 = (k5.g) r0
            java.lang.Object r0 = r8.L$2
            kotlinx.coroutines.w r0 = (kotlinx.coroutines.w) r0
            java.lang.Object r0 = r8.L$1
            r1 = r0
            kotlinx.coroutines.channels.c0 r1 = (kotlinx.coroutines.channels.c0) r1
            java.lang.Object r0 = r8.L$0
            kotlinx.coroutines.h0 r0 = (kotlinx.coroutines.h0) r0
            g5.m.b(r21)     // Catch: java.lang.Throwable -> L24 kotlinx.coroutines.flow.internal.AbortFlowException -> L27
            goto L9c
        L24:
            r0 = move-exception
            goto Lba
        L27:
            r0 = move-exception
            goto Lab
        L2a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L32:
            g5.m.b(r21)
            kotlinx.coroutines.h0 r11 = r8.p$
            r3 = 0
            r4 = 0
            kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1$1 r5 = new kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1$1
            r5.<init>(r10)
            r6 = 3
            r7 = 0
            r2 = r11
            kotlinx.coroutines.channels.c0 r12 = kotlinx.coroutines.channels.y.f(r2, r3, r4, r5, r6, r7)
            kotlinx.coroutines.w r7 = kotlinx.coroutines.p1.b(r10, r9, r10)
            java.lang.String r1 = "null cannot be cast to non-null type kotlinx.coroutines.channels.SendChannel<*>"
            java.util.Objects.requireNonNull(r12, r1)
            r1 = r12
            kotlinx.coroutines.channels.g0 r1 = (kotlinx.coroutines.channels.g0) r1
            kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1$2 r2 = new kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1$2
            r2.<init>(r7)
            r1.invokeOnClose(r2)
            k5.g r13 = r11.getCoroutineContext()     // Catch: java.lang.Throwable -> La6 kotlinx.coroutines.flow.internal.AbortFlowException -> La9
            java.lang.Object r14 = kotlinx.coroutines.internal.g0.b(r13)     // Catch: java.lang.Throwable -> La6 kotlinx.coroutines.flow.internal.AbortFlowException -> La9
            k5.g r1 = r11.getCoroutineContext()     // Catch: java.lang.Throwable -> La6 kotlinx.coroutines.flow.internal.AbortFlowException -> La9
            k5.g r15 = r1.plus(r7)     // Catch: java.lang.Throwable -> La6 kotlinx.coroutines.flow.internal.AbortFlowException -> La9
            g5.r r16 = g5.r.f6563a     // Catch: java.lang.Throwable -> La6 kotlinx.coroutines.flow.internal.AbortFlowException -> La9
            r17 = 0
            kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1$3 r18 = new kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1$3     // Catch: java.lang.Throwable -> La6 kotlinx.coroutines.flow.internal.AbortFlowException -> La9
            r6 = 0
            r1 = r18
            r2 = r20
            r3 = r13
            r4 = r14
            r5 = r12
            r1.<init>(r3, r4, r5, r6)     // Catch: java.lang.Throwable -> La6 kotlinx.coroutines.flow.internal.AbortFlowException -> La9
            r6 = 4
            r19 = 0
            r8.L$0 = r11     // Catch: java.lang.Throwable -> La6 kotlinx.coroutines.flow.internal.AbortFlowException -> La9
            r8.L$1 = r12     // Catch: java.lang.Throwable -> La6 kotlinx.coroutines.flow.internal.AbortFlowException -> La9
            r8.L$2 = r7     // Catch: java.lang.Throwable -> La6 kotlinx.coroutines.flow.internal.AbortFlowException -> La9
            r8.L$3 = r13     // Catch: java.lang.Throwable -> La6 kotlinx.coroutines.flow.internal.AbortFlowException -> La9
            r8.L$4 = r14     // Catch: java.lang.Throwable -> La6 kotlinx.coroutines.flow.internal.AbortFlowException -> La9
            r8.label = r9     // Catch: java.lang.Throwable -> La6 kotlinx.coroutines.flow.internal.AbortFlowException -> La9
            r1 = r15
            r2 = r16
            r3 = r17
            r4 = r18
            r5 = r20
            r7 = r19
            java.lang.Object r1 = kotlinx.coroutines.flow.internal.ChannelFlowKt.withContextUndispatched$default(r1, r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> La6 kotlinx.coroutines.flow.internal.AbortFlowException -> La9
            if (r1 != r0) goto L9b
            return r0
        L9b:
            r1 = r12
        L9c:
            boolean r0 = r1.isClosedForReceive()
            if (r0 != 0) goto Lb7
        La2:
            kotlinx.coroutines.channels.c0.a.a(r1, r10, r9, r10)
            goto Lb7
        La6:
            r0 = move-exception
            r1 = r12
            goto Lba
        La9:
            r0 = move-exception
            r1 = r12
        Lab:
            kotlinx.coroutines.flow.FlowCollector r2 = r8.$this_unsafeFlow     // Catch: java.lang.Throwable -> L24
            kotlinx.coroutines.flow.internal.FlowExceptions_commonKt.checkOwnership(r0, r2)     // Catch: java.lang.Throwable -> L24
            boolean r0 = r1.isClosedForReceive()
            if (r0 != 0) goto Lb7
            goto La2
        Lb7:
            g5.r r0 = g5.r.f6563a
            return r0
        Lba:
            boolean r2 = r1.isClosedForReceive()
            if (r2 != 0) goto Lc3
            kotlinx.coroutines.channels.c0.a.a(r1, r10, r9, r10)
        Lc3:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
