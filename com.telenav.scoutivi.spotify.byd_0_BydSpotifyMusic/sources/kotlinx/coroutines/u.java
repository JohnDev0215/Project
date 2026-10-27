package kotlinx.coroutines;

/* JADX INFO: compiled from: CompletableDeferred.kt */
/* JADX INFO: loaded from: classes.dex */
final class u<T> extends s1 implements t<T>, kotlinx.coroutines.selects.d<T> {

    /* JADX INFO: compiled from: CompletableDeferred.kt */
    @kotlin.coroutines.jvm.internal.f(c = "kotlinx.coroutines.CompletableDeferredImpl", f = "CompletableDeferred.kt", l = {86}, m = "await")
    static final class a extends kotlin.coroutines.jvm.internal.d {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        /* synthetic */ Object f7833e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        int f7834g;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        Object f7836i;

        a(k5.d dVar) {
            super(dVar);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) {
            this.f7833e = obj;
            this.f7834g |= Integer.MIN_VALUE;
            return u.this.V(this);
        }
    }

    public u(l1 l1Var) {
        super(true);
        initParentJobInternal$kotlinx_coroutines_core(l1Var);
    }

    @Override // kotlinx.coroutines.t
    public boolean G(Throwable th) {
        return makeCompleting$kotlinx_coroutines_core(new y(th, false, 2, null));
    }

    @Override // kotlinx.coroutines.t
    public boolean K(T t6) {
        return makeCompleting$kotlinx_coroutines_core(t6);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // kotlinx.coroutines.p0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object V(k5.d<? super T> r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof kotlinx.coroutines.u.a
            if (r0 == 0) goto L13
            r0 = r5
            kotlinx.coroutines.u$a r0 = (kotlinx.coroutines.u.a) r0
            int r1 = r0.f7834g
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f7834g = r1
            goto L18
        L13:
            kotlinx.coroutines.u$a r0 = new kotlinx.coroutines.u$a
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.f7833e
            java.lang.Object r1 = l5.b.d()
            int r2 = r0.f7834g
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r0 = r0.f7836i
            kotlinx.coroutines.u r0 = (kotlinx.coroutines.u) r0
            g5.m.b(r5)
            goto L43
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L35:
            g5.m.b(r5)
            r0.f7836i = r4
            r0.f7834g = r3
            java.lang.Object r5 = r4.awaitInternal$kotlinx_coroutines_core(r0)
            if (r5 != r1) goto L43
            return r1
        L43:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.u.V(k5.d):java.lang.Object");
    }

    @Override // kotlinx.coroutines.selects.d
    public <R> void b(kotlinx.coroutines.selects.f<? super R> fVar, r5.p<? super T, ? super k5.d<? super R>, ? extends Object> pVar) {
        registerSelectClause1Internal$kotlinx_coroutines_core(fVar, pVar);
    }

    @Override // kotlinx.coroutines.s1
    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return true;
    }
}
