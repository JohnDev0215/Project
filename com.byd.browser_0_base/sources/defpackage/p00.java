package defpackage;

import defpackage.d00;
import defpackage.m00;

/* JADX INFO: compiled from: CoroutineDispatcher.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class p00 extends AbstractC0199n implements d00 {
    public static final a e = new a();

    /* JADX INFO: compiled from: CoroutineDispatcher.kt */
    public static final class a extends AbstractC0201o<d00, p00> {

        /* JADX INFO: renamed from: p00$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: CoroutineDispatcher.kt */
        public static final class C0162a extends z41 implements jr0<m00.b, p00> {
            public static final C0162a f = new C0162a();

            public C0162a() {
                super(1);
            }

            @Override // defpackage.jr0
            public final p00 c(m00.b bVar) {
                m00.b bVar2 = bVar;
                if (bVar2 instanceof p00) {
                    return (p00) bVar2;
                }
                return null;
            }
        }

        public a() {
            super(d00.a.e, C0162a.f);
        }
    }

    public p00() {
        super(d00.a.e);
    }

    @Override // defpackage.d00
    public final void b1(b00<?> b00Var) {
        ((ta0) b00Var).i();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    @Override // defpackage.AbstractC0199n, m00.b, defpackage.m00
    public final <E extends m00.b> E get(m00.c<E> cVar) {
        ?? r3;
        i21.e(cVar, "key");
        if (cVar instanceof AbstractC0201o) {
            AbstractC0201o abstractC0201o = (AbstractC0201o) cVar;
            m00.c<?> key = getKey();
            i21.e(key, "key");
            if (!(key == abstractC0201o || abstractC0201o.f == key)) {
                return null;
            }
            m00.b bVarA = abstractC0201o.a(this);
            boolean z = bVarA instanceof m00.b;
            r3 = bVarA;
            if (!z) {
                return null;
            }
        } else {
            this = this;
            if (d00.a.e != cVar) {
                r3 = 0;
            }
        }
        return (E) r3;
    }

    public abstract void h1(m00 m00Var, Runnable runnable);

    public boolean i1() {
        return !(this instanceof fi3);
    }

    @Override // defpackage.AbstractC0199n, defpackage.m00
    public final m00 minusKey(m00.c<?> cVar) {
        i21.e(cVar, "key");
        boolean z = cVar instanceof AbstractC0201o;
        dh0 dh0Var = dh0.e;
        if (z) {
            AbstractC0201o abstractC0201o = (AbstractC0201o) cVar;
            m00.c<?> key = getKey();
            i21.e(key, "key");
            if (!(key == abstractC0201o || abstractC0201o.f == key) || abstractC0201o.a(this) == null) {
                return this;
            }
        } else if (d00.a.e != cVar) {
            return this;
        }
        return dh0Var;
    }

    @Override // defpackage.d00
    public final ta0 q(c00 c00Var) {
        return new ta0(this, c00Var);
    }

    public String toString() {
        return getClass().getSimpleName() + '@' + d70.d(this);
    }
}
