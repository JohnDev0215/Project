package defpackage;

import defpackage.m00;

/* JADX INFO: compiled from: ThreadContext.kt */
/* JADX INFO: loaded from: classes.dex */
public final class v83 {
    public static final a41 a = new a41(2, "NO_THREAD_ELEMENTS");

    /* JADX INFO: compiled from: ThreadContext.kt */
    public static final class a extends z41 implements nr0<Object, m00.b, Object> {
        public static final a f = new a();

        public a() {
            super(2);
        }

        @Override // defpackage.nr0
        public final Object d(Object obj, m00.b bVar) {
            m00.b bVar2 = bVar;
            if (!(bVar2 instanceof u83)) {
                return obj;
            }
            Integer num = obj instanceof Integer ? (Integer) obj : null;
            int iIntValue = num == null ? 1 : num.intValue();
            return iIntValue == 0 ? bVar2 : Integer.valueOf(iIntValue + 1);
        }
    }

    /* JADX INFO: compiled from: ThreadContext.kt */
    public static final class b extends z41 implements nr0<u83<?>, m00.b, u83<?>> {
        public static final b f = new b();

        public b() {
            super(2);
        }

        @Override // defpackage.nr0
        public final u83<?> d(u83<?> u83Var, m00.b bVar) {
            u83<?> u83Var2 = u83Var;
            m00.b bVar2 = bVar;
            if (u83Var2 != null) {
                return u83Var2;
            }
            if (bVar2 instanceof u83) {
                return (u83) bVar2;
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: ThreadContext.kt */
    public static final class c extends z41 implements nr0<z83, m00.b, z83> {
        public static final c f = new c();

        public c() {
            super(2);
        }

        @Override // defpackage.nr0
        public final z83 d(z83 z83Var, m00.b bVar) {
            z83 z83Var2 = z83Var;
            m00.b bVar2 = bVar;
            if (bVar2 instanceof u83) {
                u83<Object> u83Var = (u83) bVar2;
                String strT0 = u83Var.T0(z83Var2.a);
                int i = z83Var2.d;
                z83Var2.b[i] = strT0;
                z83Var2.d = i + 1;
                z83Var2.c[i] = u83Var;
            }
            return z83Var2;
        }
    }

    public static final void a(m00 m00Var, Object obj) {
        if (obj == a) {
            return;
        }
        if (!(obj instanceof z83)) {
            Object objFold = m00Var.fold(null, b.f);
            if (objFold == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
            }
            ((u83) objFold).H0(obj);
            return;
        }
        z83 z83Var = (z83) obj;
        u83<Object>[] u83VarArr = z83Var.c;
        int length = u83VarArr.length - 1;
        if (length < 0) {
            return;
        }
        while (true) {
            int i = length - 1;
            u83<Object> u83Var = u83VarArr[length];
            i21.b(u83Var);
            u83Var.H0(z83Var.b[length]);
            if (i < 0) {
                return;
            } else {
                length = i;
            }
        }
    }

    public static final Object b(m00 m00Var) {
        Object objFold = m00Var.fold(0, a.f);
        i21.b(objFold);
        return objFold;
    }

    public static final Object c(m00 m00Var, Object obj) {
        if (obj == null) {
            obj = b(m00Var);
        }
        return obj == 0 ? a : obj instanceof Integer ? m00Var.fold(new z83(m00Var, ((Number) obj).intValue()), c.f) : ((u83) obj).T0(m00Var);
    }
}
