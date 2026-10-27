package k5;

import g5.r;
import java.io.Serializable;
import k5.g;
import kotlin.jvm.internal.l;
import kotlin.jvm.internal.m;
import r5.p;

/* JADX INFO: compiled from: CoroutineContextImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public final class c implements g, Serializable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final g f7266e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final g.b f7267g;

    /* JADX INFO: compiled from: CoroutineContextImpl.kt */
    private static final class a implements Serializable {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final C0099a f7268g = new C0099a(null);
        private static final long serialVersionUID = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final g[] f7269e;

        /* JADX INFO: renamed from: k5.c$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: CoroutineContextImpl.kt */
        public static final class C0099a {
            private C0099a() {
            }

            public /* synthetic */ C0099a(kotlin.jvm.internal.g gVar) {
                this();
            }
        }

        public a(g[] elements) {
            l.e(elements, "elements");
            this.f7269e = elements;
        }

        private final Object readResolve() {
            g[] gVarArr = this.f7269e;
            g gVarPlus = h.f7276e;
            for (g gVar : gVarArr) {
                gVarPlus = gVarPlus.plus(gVar);
            }
            return gVarPlus;
        }
    }

    /* JADX INFO: compiled from: CoroutineContextImpl.kt */
    static final class b extends m implements p<String, g.b, String> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final b f7270e = new b();

        b() {
            super(2);
        }

        @Override // r5.p
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(String acc, g.b element) {
            l.e(acc, "acc");
            l.e(element, "element");
            if (acc.length() == 0) {
                return element.toString();
            }
            return acc + ", " + element;
        }
    }

    /* JADX INFO: renamed from: k5.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CoroutineContextImpl.kt */
    static final class C0100c extends m implements p<r, g.b, r> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ g[] f7271e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ kotlin.jvm.internal.p f7272g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0100c(g[] gVarArr, kotlin.jvm.internal.p pVar) {
            super(2);
            this.f7271e = gVarArr;
            this.f7272g = pVar;
        }

        public final void a(r rVar, g.b element) {
            l.e(rVar, "<anonymous parameter 0>");
            l.e(element, "element");
            g[] gVarArr = this.f7271e;
            kotlin.jvm.internal.p pVar = this.f7272g;
            int i7 = pVar.f7506e;
            pVar.f7506e = i7 + 1;
            gVarArr[i7] = element;
        }

        @Override // r5.p
        public /* bridge */ /* synthetic */ r invoke(r rVar, g.b bVar) {
            a(rVar, bVar);
            return r.f6563a;
        }
    }

    public c(g left, g.b element) {
        l.e(left, "left");
        l.e(element, "element");
        this.f7266e = left;
        this.f7267g = element;
    }

    private final boolean a(g.b bVar) {
        return l.a(get(bVar.getKey()), bVar);
    }

    private final boolean b(c cVar) {
        while (a(cVar.f7267g)) {
            g gVar = cVar.f7266e;
            if (!(gVar instanceof c)) {
                return a((g.b) gVar);
            }
            cVar = (c) gVar;
        }
        return false;
    }

    private final int c() {
        int i7 = 2;
        c cVar = this;
        while (true) {
            g gVar = cVar.f7266e;
            cVar = gVar instanceof c ? (c) gVar : null;
            if (cVar == null) {
                return i7;
            }
            i7++;
        }
    }

    private final Object writeReplace() {
        int iC = c();
        g[] gVarArr = new g[iC];
        kotlin.jvm.internal.p pVar = new kotlin.jvm.internal.p();
        fold(r.f6563a, new C0100c(gVarArr, pVar));
        if (pVar.f7506e == iC) {
            return new a(gVarArr);
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof c) {
                c cVar = (c) obj;
                if (cVar.c() != c() || !cVar.b(this)) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // k5.g
    public <R> R fold(R r7, p<? super R, ? super g.b, ? extends R> operation) {
        l.e(operation, "operation");
        return operation.invoke((Object) this.f7266e.fold(r7, operation), this.f7267g);
    }

    @Override // k5.g
    public <E extends g.b> E get(g.c<E> key) {
        l.e(key, "key");
        c cVar = this;
        while (true) {
            E e7 = (E) cVar.f7267g.get(key);
            if (e7 != null) {
                return e7;
            }
            g gVar = cVar.f7266e;
            if (!(gVar instanceof c)) {
                return (E) gVar.get(key);
            }
            cVar = (c) gVar;
        }
    }

    public int hashCode() {
        return this.f7266e.hashCode() + this.f7267g.hashCode();
    }

    @Override // k5.g
    public g minusKey(g.c<?> key) {
        l.e(key, "key");
        if (this.f7267g.get(key) != null) {
            return this.f7266e;
        }
        g gVarMinusKey = this.f7266e.minusKey(key);
        return gVarMinusKey == this.f7266e ? this : gVarMinusKey == h.f7276e ? this.f7267g : new c(gVarMinusKey, this.f7267g);
    }

    @Override // k5.g
    public g plus(g gVar) {
        return g.a.a(this, gVar);
    }

    public String toString() {
        return '[' + ((String) fold("", b.f7270e)) + ']';
    }
}
