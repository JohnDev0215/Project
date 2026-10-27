package spi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: compiled from: ObjectTypeAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c8 extends nb<Object> {
    public static final a b = new a();
    public final j4 a;

    /* JADX INFO: compiled from: ObjectTypeAdapter.java */
    public static class a implements ob {
        @Override // spi.ob
        public final <T> nb<T> a(j4 j4Var, vb<T> vbVar) {
            if (vbVar.a == Object.class) {
                return new c8(j4Var);
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: ObjectTypeAdapter.java */
    public static /* synthetic */ class b {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[s5.values().length];
            a = iArr;
            try {
                iArr[s5.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[s5.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[s5.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[s5.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[s5.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[s5.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public c8(j4 j4Var) {
        this.a = j4Var;
    }

    @Override // spi.nb
    public final Object a(o5 o5Var) throws IOException {
        switch (b.a[o5Var.v().ordinal()]) {
            case 1:
                ArrayList arrayList = new ArrayList();
                o5Var.a();
                while (o5Var.i()) {
                    arrayList.add(a(o5Var));
                }
                o5Var.e();
                return arrayList;
            case 2:
                y5 y5Var = new y5();
                o5Var.b();
                while (o5Var.i()) {
                    y5Var.put(o5Var.p(), a(o5Var));
                }
                o5Var.f();
                return y5Var;
            case 3:
                return o5Var.t();
            case 4:
                return Double.valueOf(o5Var.m());
            case 5:
                return Boolean.valueOf(o5Var.l());
            case 6:
                o5Var.r();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    @Override // spi.nb
    public final void b(v5 v5Var, Object obj) throws IOException {
        if (obj == null) {
            v5Var.i();
            return;
        }
        j4 j4Var = this.a;
        Class<?> cls = obj.getClass();
        Objects.requireNonNull(j4Var);
        nb nbVarE = j4Var.e(new vb(cls));
        if (!(nbVarE instanceof c8)) {
            nbVarE.b(v5Var, obj);
        } else {
            v5Var.c();
            v5Var.f();
        }
    }
}
