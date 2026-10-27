package l5;

import g5.m;
import g5.r;
import k5.g;
import kotlin.coroutines.jvm.internal.h;
import kotlin.coroutines.jvm.internal.j;
import kotlin.jvm.internal.v;
import r5.l;
import r5.p;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: IntrinsicsJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: compiled from: IntrinsicsJvm.kt */
    public static final class a extends j {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f7962e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ l f7963g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(k5.d dVar, l lVar) {
            super(dVar);
            this.f7963g = lVar;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        protected Object invokeSuspend(Object obj) throws Throwable {
            int i7 = this.f7962e;
            if (i7 == 0) {
                this.f7962e = 1;
                m.b(obj);
                return ((l) v.b(this.f7963g, 1)).invoke(this);
            }
            if (i7 != 1) {
                throw new IllegalStateException("This coroutine had already completed".toString());
            }
            this.f7962e = 2;
            m.b(obj);
            return obj;
        }
    }

    /* JADX INFO: compiled from: IntrinsicsJvm.kt */
    public static final class b extends kotlin.coroutines.jvm.internal.d {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f7964e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ l f7965g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(k5.d dVar, g gVar, l lVar) {
            super(dVar, gVar);
            this.f7965g = lVar;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        protected Object invokeSuspend(Object obj) throws Throwable {
            int i7 = this.f7964e;
            if (i7 == 0) {
                this.f7964e = 1;
                m.b(obj);
                return ((l) v.b(this.f7965g, 1)).invoke(this);
            }
            if (i7 != 1) {
                throw new IllegalStateException("This coroutine had already completed".toString());
            }
            this.f7964e = 2;
            m.b(obj);
            return obj;
        }
    }

    /* JADX INFO: renamed from: l5.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IntrinsicsJvm.kt */
    public static final class C0117c extends j {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f7966e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ p f7967g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ Object f7968h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0117c(k5.d dVar, p pVar, Object obj) {
            super(dVar);
            this.f7967g = pVar;
            this.f7968h = obj;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        protected Object invokeSuspend(Object obj) throws Throwable {
            int i7 = this.f7966e;
            if (i7 == 0) {
                this.f7966e = 1;
                m.b(obj);
                return ((p) v.b(this.f7967g, 2)).invoke(this.f7968h, this);
            }
            if (i7 != 1) {
                throw new IllegalStateException("This coroutine had already completed".toString());
            }
            this.f7966e = 2;
            m.b(obj);
            return obj;
        }
    }

    /* JADX INFO: compiled from: IntrinsicsJvm.kt */
    public static final class d extends kotlin.coroutines.jvm.internal.d {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f7969e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ p f7970g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ Object f7971h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(k5.d dVar, g gVar, p pVar, Object obj) {
            super(dVar, gVar);
            this.f7970g = pVar;
            this.f7971h = obj;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        protected Object invokeSuspend(Object obj) throws Throwable {
            int i7 = this.f7969e;
            if (i7 == 0) {
                this.f7969e = 1;
                m.b(obj);
                return ((p) v.b(this.f7970g, 2)).invoke(this.f7971h, this);
            }
            if (i7 != 1) {
                throw new IllegalStateException("This coroutine had already completed".toString());
            }
            this.f7969e = 2;
            m.b(obj);
            return obj;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> k5.d<r> a(l<? super k5.d<? super T>, ? extends Object> lVar, k5.d<? super T> completion) {
        kotlin.jvm.internal.l.e(lVar, "<this>");
        kotlin.jvm.internal.l.e(completion, "completion");
        k5.d<?> dVarA = h.a(completion);
        if (lVar instanceof kotlin.coroutines.jvm.internal.a) {
            return ((kotlin.coroutines.jvm.internal.a) lVar).create(dVarA);
        }
        g context = dVarA.getContext();
        return context == k5.h.f7276e ? new a(dVarA, lVar) : new b(dVarA, context, lVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <R, T> k5.d<r> b(p<? super R, ? super k5.d<? super T>, ? extends Object> pVar, R r7, k5.d<? super T> completion) {
        kotlin.jvm.internal.l.e(pVar, "<this>");
        kotlin.jvm.internal.l.e(completion, "completion");
        k5.d<?> dVarA = h.a(completion);
        if (pVar instanceof kotlin.coroutines.jvm.internal.a) {
            return ((kotlin.coroutines.jvm.internal.a) pVar).create(r7, dVarA);
        }
        g context = dVarA.getContext();
        return context == k5.h.f7276e ? new C0117c(dVarA, pVar, r7) : new d(dVarA, context, pVar, r7);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> k5.d<T> c(k5.d<? super T> dVar) {
        k5.d<T> dVar2;
        kotlin.jvm.internal.l.e(dVar, "<this>");
        kotlin.coroutines.jvm.internal.d dVar3 = dVar instanceof kotlin.coroutines.jvm.internal.d ? (kotlin.coroutines.jvm.internal.d) dVar : null;
        return (dVar3 == null || (dVar2 = (k5.d<T>) dVar3.intercepted()) == null) ? dVar : dVar2;
    }
}
