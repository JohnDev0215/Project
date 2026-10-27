package defpackage;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: CallbackRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class cb<C, T, A> implements Cloneable {
    public List<C> b = new ArrayList();
    public long c = 0;
    public long[] d;
    public int e;
    public final a<C, T, A> f;

    /* JADX INFO: compiled from: CallbackRegistry.java */
    public static abstract class a<C, T, A> {
        public abstract void a(C c, T t, int i, A a);
    }

    public cb(a<C, T, A> aVar) {
        this.f = aVar;
    }

    public synchronized void a(C c) {
        if (c == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }
        int iLastIndexOf = this.b.lastIndexOf(c);
        if (iLastIndexOf < 0 || c(iLastIndexOf)) {
            this.b.add(c);
        }
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public synchronized cb<C, T, A> clone() {
        cb<C, T, A> cbVar;
        CloneNotSupportedException e;
        try {
            cbVar = (cb) super.clone();
        } catch (CloneNotSupportedException e2) {
            cbVar = null;
            e = e2;
        }
        try {
            cbVar.c = 0L;
            cbVar.d = null;
            cbVar.e = 0;
            cbVar.b = new ArrayList();
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                if (!c(i)) {
                    cbVar.b.add(this.b.get(i));
                }
            }
        } catch (CloneNotSupportedException e3) {
            e = e3;
            e.printStackTrace();
        }
        return cbVar;
    }

    public final boolean c(int i) {
        int i2;
        if (i < 64) {
            return ((1 << i) & this.c) != 0;
        }
        long[] jArr = this.d;
        if (jArr != null && (i2 = (i / 64) - 1) < jArr.length) {
            return ((1 << (i % 64)) & jArr[i2]) != 0;
        }
        return false;
    }

    public synchronized void d(T t, int i, A a2) {
        this.e++;
        g(t, i, a2);
        int i2 = this.e - 1;
        this.e = i2;
        if (i2 == 0) {
            long[] jArr = this.d;
            if (jArr != null) {
                for (int length = jArr.length - 1; length >= 0; length--) {
                    long j = this.d[length];
                    if (j != 0) {
                        j((length + 1) * 64, j);
                        this.d[length] = 0;
                    }
                }
            }
            long j2 = this.c;
            if (j2 != 0) {
                j(0, j2);
                this.c = 0L;
            }
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void e(T t, int i, A a2, int i2, int i3, long j) {
        long j2 = 1;
        while (i2 < i3) {
            if ((j & j2) == 0) {
                this.f.a(this.b.get(i2), t, i, a2);
            }
            j2 <<= 1;
            i2++;
        }
    }

    public final void f(T t, int i, A a2) {
        e(t, i, a2, 0, Math.min(64, this.b.size()), this.c);
    }

    public final void g(T t, int i, A a2) {
        int size = this.b.size();
        int length = this.d == null ? -1 : r0.length - 1;
        h(t, i, a2, length);
        e(t, i, a2, (length + 2) * 64, size, 0L);
    }

    public final void h(T t, int i, A a2, int i2) {
        if (i2 < 0) {
            f(t, i, a2);
            return;
        }
        long j = this.d[i2];
        int i3 = (i2 + 1) * 64;
        int iMin = Math.min(this.b.size(), i3 + 64);
        h(t, i, a2, i2 - 1);
        e(t, i, a2, i3, iMin, j);
    }

    public synchronized void i(C c) {
        if (this.e == 0) {
            this.b.remove(c);
        } else {
            int iLastIndexOf = this.b.lastIndexOf(c);
            if (iLastIndexOf >= 0) {
                k(iLastIndexOf);
            }
        }
    }

    public final void j(int i, long j) {
        long j2 = Long.MIN_VALUE;
        for (int i2 = (i + 64) - 1; i2 >= i; i2--) {
            if ((j & j2) != 0) {
                this.b.remove(i2);
            }
            j2 >>>= 1;
        }
    }

    public final void k(int i) {
        if (i < 64) {
            this.c = (1 << i) | this.c;
            return;
        }
        int i2 = (i / 64) - 1;
        long[] jArr = this.d;
        if (jArr == null) {
            this.d = new long[this.b.size() / 64];
        } else if (jArr.length <= i2) {
            long[] jArr2 = new long[this.b.size() / 64];
            long[] jArr3 = this.d;
            System.arraycopy(jArr3, 0, jArr2, 0, jArr3.length);
            this.d = jArr2;
        }
        long j = 1 << (i % 64);
        long[] jArr4 = this.d;
        jArr4[i2] = j | jArr4[i2];
    }
}
