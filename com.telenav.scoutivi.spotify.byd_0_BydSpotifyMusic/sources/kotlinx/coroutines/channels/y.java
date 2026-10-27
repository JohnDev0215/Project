package kotlinx.coroutines.channels;

import g5.l;
import kotlinx.coroutines.k0;

/* JADX INFO: compiled from: Produce.kt */
/* JADX INFO: loaded from: classes.dex */
public final class y {

    /* JADX INFO: compiled from: Produce.kt */
    @kotlin.coroutines.jvm.internal.f(c = "kotlinx.coroutines.channels.ProduceKt", f = "Produce.kt", l = {157}, m = "awaitClose")
    static final class a extends kotlin.coroutines.jvm.internal.d {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        /* synthetic */ Object f7592e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        int f7593g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        Object f7594h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        Object f7595i;

        a(k5.d dVar) {
            super(dVar);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) {
            this.f7592e = obj;
            this.f7593g |= Integer.MIN_VALUE;
            return y.a(null, null, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: compiled from: Produce.kt */
    public static final class b extends kotlin.jvm.internal.m implements r5.a<g5.r> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final b f7596e = new b();

        b() {
            super(0);
        }

        public final void a() {
        }

        @Override // r5.a
        public /* bridge */ /* synthetic */ g5.r invoke() {
            a();
            return g5.r.f6563a;
        }
    }

    /* JADX INFO: compiled from: Produce.kt */
    static final class c extends kotlin.jvm.internal.m implements r5.l<Throwable, g5.r> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ kotlinx.coroutines.i f7597e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(kotlinx.coroutines.i iVar) {
            super(1);
            this.f7597e = iVar;
        }

        @Override // r5.l
        public /* bridge */ /* synthetic */ g5.r invoke(Throwable th) {
            invoke2(th);
            return g5.r.f6563a;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Throwable th) {
            kotlinx.coroutines.i iVar = this.f7597e;
            g5.r rVar = g5.r.f6563a;
            l.a aVar = g5.l.f6557e;
            iVar.resumeWith(g5.l.a(rVar));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object a(kotlinx.coroutines.channels.a0<?> r4, r5.a<g5.r> r5, k5.d<? super g5.r> r6) throws java.lang.Throwable {
        /*
            boolean r0 = r6 instanceof kotlinx.coroutines.channels.y.a
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.channels.y$a r0 = (kotlinx.coroutines.channels.y.a) r0
            int r1 = r0.f7593g
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f7593g = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.y$a r0 = new kotlinx.coroutines.channels.y$a
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.f7592e
            java.lang.Object r1 = l5.b.d()
            int r2 = r0.f7593g
            r3 = 1
            if (r2 == 0) goto L3c
            if (r2 != r3) goto L34
            java.lang.Object r4 = r0.f7595i
            r5 = r4
            r5.a r5 = (r5.a) r5
            java.lang.Object r4 = r0.f7594h
            kotlinx.coroutines.channels.a0 r4 = (kotlinx.coroutines.channels.a0) r4
            g5.m.b(r6)     // Catch: java.lang.Throwable -> L32
            goto L7c
        L32:
            r4 = move-exception
            goto L82
        L34:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3c:
            g5.m.b(r6)
            k5.g r6 = r0.getContext()
            kotlinx.coroutines.l1$b r2 = kotlinx.coroutines.l1.f7703d
            k5.g$b r6 = r6.get(r2)
            kotlinx.coroutines.l1 r6 = (kotlinx.coroutines.l1) r6
            if (r6 != r4) goto L4f
            r6 = r3
            goto L50
        L4f:
            r6 = 0
        L50:
            if (r6 == 0) goto L86
            r0.f7594h = r4     // Catch: java.lang.Throwable -> L32
            r0.f7595i = r5     // Catch: java.lang.Throwable -> L32
            r0.f7593g = r3     // Catch: java.lang.Throwable -> L32
            kotlinx.coroutines.k r6 = new kotlinx.coroutines.k     // Catch: java.lang.Throwable -> L32
            k5.d r2 = l5.b.c(r0)     // Catch: java.lang.Throwable -> L32
            r6.<init>(r2, r3)     // Catch: java.lang.Throwable -> L32
            r6.A()     // Catch: java.lang.Throwable -> L32
            kotlinx.coroutines.channels.y$c r2 = new kotlinx.coroutines.channels.y$c     // Catch: java.lang.Throwable -> L32
            r2.<init>(r6)     // Catch: java.lang.Throwable -> L32
            r4.invokeOnClose(r2)     // Catch: java.lang.Throwable -> L32
            java.lang.Object r4 = r6.y()     // Catch: java.lang.Throwable -> L32
            java.lang.Object r6 = l5.b.d()     // Catch: java.lang.Throwable -> L32
            if (r4 != r6) goto L79
            kotlin.coroutines.jvm.internal.h.c(r0)     // Catch: java.lang.Throwable -> L32
        L79:
            if (r4 != r1) goto L7c
            return r1
        L7c:
            r5.invoke()
            g5.r r4 = g5.r.f6563a
            return r4
        L82:
            r5.invoke()
            throw r4
        L86:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "awaitClose() can only be invoked from the producer context"
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.y.a(kotlinx.coroutines.channels.a0, r5.a, k5.d):java.lang.Object");
    }

    public static /* synthetic */ Object b(a0 a0Var, r5.a aVar, k5.d dVar, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            aVar = b.f7596e;
        }
        return a(a0Var, aVar, dVar);
    }

    public static final <E> c0<E> c(kotlinx.coroutines.h0 h0Var, k5.g gVar, int i7, k kVar, k0 k0Var, r5.l<? super Throwable, g5.r> lVar, r5.p<? super a0<? super E>, ? super k5.d<? super g5.r>, ? extends Object> pVar) {
        z zVar = new z(kotlinx.coroutines.d0.c(h0Var, gVar), o.b(i7, kVar, null, 4, null));
        if (lVar != null) {
            zVar.invokeOnCompletion(lVar);
        }
        zVar.start(k0Var, zVar, pVar);
        return zVar;
    }

    public static final <E> c0<E> d(kotlinx.coroutines.h0 h0Var, k5.g gVar, int i7, r5.p<? super a0<? super E>, ? super k5.d<? super g5.r>, ? extends Object> pVar) {
        return c(h0Var, gVar, i7, k.SUSPEND, k0.DEFAULT, null, pVar);
    }

    public static /* synthetic */ c0 e(kotlinx.coroutines.h0 h0Var, k5.g gVar, int i7, k kVar, k0 k0Var, r5.l lVar, r5.p pVar, int i8, Object obj) {
        if ((i8 & 1) != 0) {
            gVar = k5.h.f7276e;
        }
        k5.g gVar2 = gVar;
        if ((i8 & 2) != 0) {
            i7 = 0;
        }
        int i9 = i7;
        if ((i8 & 4) != 0) {
            kVar = k.SUSPEND;
        }
        k kVar2 = kVar;
        if ((i8 & 8) != 0) {
            k0Var = k0.DEFAULT;
        }
        k0 k0Var2 = k0Var;
        if ((i8 & 16) != 0) {
            lVar = null;
        }
        return c(h0Var, gVar2, i9, kVar2, k0Var2, lVar, pVar);
    }

    public static /* synthetic */ c0 f(kotlinx.coroutines.h0 h0Var, k5.g gVar, int i7, r5.p pVar, int i8, Object obj) {
        if ((i8 & 1) != 0) {
            gVar = k5.h.f7276e;
        }
        if ((i8 & 2) != 0) {
            i7 = 0;
        }
        return d(h0Var, gVar, i7, pVar);
    }
}
