package kotlinx.coroutines.internal;

/* JADX INFO: compiled from: OnUndeliveredElement.kt */
/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: compiled from: OnUndeliveredElement.kt */
    static final class a extends kotlin.jvm.internal.m implements r5.l<Throwable, g5.r> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ r5.l f7684e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ Object f7685g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ k5.g f7686h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(r5.l lVar, Object obj, k5.g gVar) {
            super(1);
            this.f7684e = lVar;
            this.f7685g = obj;
            this.f7686h = gVar;
        }

        @Override // r5.l
        public /* bridge */ /* synthetic */ g5.r invoke(Throwable th) {
            invoke2(th);
            return g5.r.f6563a;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Throwable th) {
            v.b(this.f7684e, this.f7685g, this.f7686h);
        }
    }

    public static final <E> r5.l<Throwable, g5.r> a(r5.l<? super E, g5.r> lVar, E e7, k5.g gVar) {
        return new a(lVar, e7, gVar);
    }

    public static final <E> void b(r5.l<? super E, g5.r> lVar, E e7, k5.g gVar) {
        k0 k0VarC = c(lVar, e7, null);
        if (k0VarC != null) {
            kotlinx.coroutines.g0.a(gVar, k0VarC);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <E> k0 c(r5.l<? super E, g5.r> lVar, E e7, k0 k0Var) {
        try {
            lVar.invoke(e7);
        } catch (Throwable th) {
            if (k0Var == null || k0Var.getCause() == th) {
                return new k0("Exception in undelivered element handler for " + e7, th);
            }
            g5.b.a(k0Var, th);
        }
        return k0Var;
    }

    public static /* synthetic */ k0 d(r5.l lVar, Object obj, k0 k0Var, int i7, Object obj2) {
        if ((i7 & 2) != 0) {
            k0Var = null;
        }
        return c(lVar, obj, k0Var);
    }
}
