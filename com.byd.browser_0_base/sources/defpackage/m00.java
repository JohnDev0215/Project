package defpackage;

import defpackage.d00;

/* JADX INFO: compiled from: CoroutineContext.kt */
/* JADX INFO: loaded from: classes.dex */
public interface m00 {

    /* JADX INFO: compiled from: CoroutineContext.kt */
    public static final class a {

        /* JADX INFO: renamed from: m00$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: CoroutineContext.kt */
        public static final class C0066a extends z41 implements nr0<m00, b, m00> {
            public static final C0066a f = new C0066a();

            public C0066a() {
                super(2);
            }

            @Override // defpackage.nr0
            public final m00 d(m00 m00Var, b bVar) {
                hv hvVar;
                m00 m00Var2 = m00Var;
                b bVar2 = bVar;
                i21.e(m00Var2, "acc");
                i21.e(bVar2, "element");
                m00 m00VarMinusKey = m00Var2.minusKey(bVar2.getKey());
                dh0 dh0Var = dh0.e;
                if (m00VarMinusKey == dh0Var) {
                    return bVar2;
                }
                int i = d00.a;
                d00.a aVar = d00.a.e;
                d00 d00Var = (d00) m00VarMinusKey.get(aVar);
                if (d00Var == null) {
                    hvVar = new hv(bVar2, m00VarMinusKey);
                } else {
                    m00 m00VarMinusKey2 = m00VarMinusKey.minusKey(aVar);
                    if (m00VarMinusKey2 == dh0Var) {
                        return new hv(d00Var, bVar2);
                    }
                    hvVar = new hv(d00Var, new hv(bVar2, m00VarMinusKey2));
                }
                return hvVar;
            }
        }

        public static m00 a(m00 m00Var, m00 m00Var2) {
            i21.e(m00Var2, "context");
            return m00Var2 == dh0.e ? m00Var : (m00) m00Var2.fold(m00Var, C0066a.f);
        }
    }

    /* JADX INFO: compiled from: CoroutineContext.kt */
    public interface b extends m00 {

        /* JADX INFO: compiled from: CoroutineContext.kt */
        public static final class a {
            /* JADX WARN: Multi-variable type inference failed */
            public static <E extends b> E a(b bVar, c<E> cVar) {
                i21.e(cVar, "key");
                if (i21.a(bVar.getKey(), cVar)) {
                    return bVar;
                }
                return null;
            }

            public static m00 b(b bVar, c<?> cVar) {
                i21.e(cVar, "key");
                return i21.a(bVar.getKey(), cVar) ? dh0.e : bVar;
            }
        }

        @Override // defpackage.m00
        <E extends b> E get(c<E> cVar);

        c<?> getKey();
    }

    /* JADX INFO: compiled from: CoroutineContext.kt */
    public interface c<E extends b> {
    }

    <R> R fold(R r, nr0<? super R, ? super b, ? extends R> nr0Var);

    <E extends b> E get(c<E> cVar);

    m00 minusKey(c<?> cVar);

    m00 plus(m00 m00Var);
}
