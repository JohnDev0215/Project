package kotlinx.coroutines.internal;

import java.util.Objects;
import k5.g;
import kotlinx.coroutines.g2;

/* JADX INFO: compiled from: ThreadContext.kt */
/* JADX INFO: loaded from: classes.dex */
public final class g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final c0 f7631a = new c0("ZERO");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final r5.p<Object, g.b, Object> f7632b = a.f7636e;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final r5.p<g2<?>, g.b, g2<?>> f7633c = b.f7637e;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final r5.p<j0, g.b, j0> f7634d = d.f7639e;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final r5.p<j0, g.b, j0> f7635e = c.f7638e;

    /* JADX INFO: compiled from: ThreadContext.kt */
    static final class a extends kotlin.jvm.internal.m implements r5.p<Object, g.b, Object> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final a f7636e = new a();

        a() {
            super(2);
        }

        @Override // r5.p
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Object invoke(Object obj, g.b bVar) {
            if (!(bVar instanceof g2)) {
                return obj;
            }
            if (!(obj instanceof Integer)) {
                obj = null;
            }
            Integer num = (Integer) obj;
            int iIntValue = num != null ? num.intValue() : 1;
            return iIntValue == 0 ? bVar : Integer.valueOf(iIntValue + 1);
        }
    }

    /* JADX INFO: compiled from: ThreadContext.kt */
    static final class b extends kotlin.jvm.internal.m implements r5.p<g2<?>, g.b, g2<?>> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final b f7637e = new b();

        b() {
            super(2);
        }

        @Override // r5.p
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final g2<?> invoke(g2<?> g2Var, g.b bVar) {
            if (g2Var != null) {
                return g2Var;
            }
            if (!(bVar instanceof g2)) {
                bVar = null;
            }
            return (g2) bVar;
        }
    }

    /* JADX INFO: compiled from: ThreadContext.kt */
    static final class c extends kotlin.jvm.internal.m implements r5.p<j0, g.b, j0> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final c f7638e = new c();

        c() {
            super(2);
        }

        @Override // r5.p
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final j0 invoke(j0 j0Var, g.b bVar) {
            if (bVar instanceof g2) {
                ((g2) bVar).m(j0Var.b(), j0Var.d());
            }
            return j0Var;
        }
    }

    /* JADX INFO: compiled from: ThreadContext.kt */
    static final class d extends kotlin.jvm.internal.m implements r5.p<j0, g.b, j0> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final d f7639e = new d();

        d() {
            super(2);
        }

        @Override // r5.p
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final j0 invoke(j0 j0Var, g.b bVar) {
            if (bVar instanceof g2) {
                j0Var.a(((g2) bVar).U(j0Var.b()));
            }
            return j0Var;
        }
    }

    public static final void a(k5.g gVar, Object obj) {
        if (obj == f7631a) {
            return;
        }
        if (obj instanceof j0) {
            ((j0) obj).c();
            gVar.fold(obj, f7635e);
        } else {
            Object objFold = gVar.fold(null, f7633c);
            Objects.requireNonNull(objFold, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
            ((g2) objFold).m(gVar, obj);
        }
    }

    public static final Object b(k5.g gVar) {
        Object objFold = gVar.fold(0, f7632b);
        kotlin.jvm.internal.l.c(objFold);
        return objFold;
    }

    public static final Object c(k5.g gVar, Object obj) {
        if (obj == null) {
            obj = b(gVar);
        }
        if (obj == 0) {
            return f7631a;
        }
        if (obj instanceof Integer) {
            return gVar.fold(new j0(gVar, ((Number) obj).intValue()), f7634d);
        }
        Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
        return ((g2) obj).U(gVar);
    }
}
