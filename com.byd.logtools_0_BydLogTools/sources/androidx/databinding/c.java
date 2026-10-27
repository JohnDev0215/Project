package androidx.databinding;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: CallbackRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class c<C, T, A> implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<C> f1426b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f1427c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long[] f1428d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1429e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final a<C, T, A> f1430f;

    /* JADX INFO: compiled from: CallbackRegistry.java */
    public static abstract class a<C, T, A> {
        public abstract void a(C c2, T t, int i, A a2);
    }

    public c(a<C, T, A> aVar) {
        this.f1430f = aVar;
    }

    private boolean c(int i) {
        int i2;
        if (i < 64) {
            return ((1 << i) & this.f1427c) != 0;
        }
        long[] jArr = this.f1428d;
        if (jArr != null && (i2 = (i / 64) - 1) < jArr.length) {
            return ((1 << (i % 64)) & jArr[i2]) != 0;
        }
        return false;
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
    private void f(T t, int i, A a2, int i2, int i3, long j) {
        long j2 = 1;
        while (i2 < i3) {
            if ((j & j2) == 0) {
                this.f1430f.a(this.f1426b.get(i2), t, i, a2);
            }
            j2 <<= 1;
            i2++;
        }
    }

    private void g(T t, int i, A a2) {
        f(t, i, a2, 0, Math.min(64, this.f1426b.size()), this.f1427c);
    }

    private void h(T t, int i, A a2) {
        int size = this.f1426b.size();
        int length = this.f1428d == null ? -1 : r0.length - 1;
        i(t, i, a2, length);
        f(t, i, a2, (length + 2) * 64, size, 0L);
    }

    private void i(T t, int i, A a2, int i2) {
        if (i2 < 0) {
            g(t, i, a2);
            return;
        }
        long j = this.f1428d[i2];
        int i3 = (i2 + 1) * 64;
        int iMin = Math.min(this.f1426b.size(), i3 + 64);
        i(t, i, a2, i2 - 1);
        f(t, i, a2, i3, iMin, j);
    }

    private void k(int i, long j) {
        long j2 = Long.MIN_VALUE;
        for (int i2 = (i + 64) - 1; i2 >= i; i2--) {
            if ((j & j2) != 0) {
                this.f1426b.remove(i2);
            }
            j2 >>>= 1;
        }
    }

    private void l(int i) {
        if (i < 64) {
            this.f1427c = (1 << i) | this.f1427c;
            return;
        }
        int i2 = (i / 64) - 1;
        long[] jArr = this.f1428d;
        if (jArr == null) {
            this.f1428d = new long[this.f1426b.size() / 64];
        } else if (jArr.length <= i2) {
            long[] jArr2 = new long[this.f1426b.size() / 64];
            long[] jArr3 = this.f1428d;
            System.arraycopy(jArr3, 0, jArr2, 0, jArr3.length);
            this.f1428d = jArr2;
        }
        long j = 1 << (i % 64);
        long[] jArr4 = this.f1428d;
        jArr4[i2] = j | jArr4[i2];
    }

    public synchronized void a(C c2) {
        if (c2 == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }
        int iLastIndexOf = this.f1426b.lastIndexOf(c2);
        if (iLastIndexOf < 0 || c(iLastIndexOf)) {
            this.f1426b.add(c2);
        }
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public synchronized c<C, T, A> clone() {
        c<C, T, A> cVar;
        CloneNotSupportedException e2;
        try {
            cVar = (c) super.clone();
        } catch (CloneNotSupportedException e3) {
            cVar = null;
            e2 = e3;
        }
        try {
            cVar.f1427c = 0L;
            cVar.f1428d = null;
            cVar.f1429e = 0;
            cVar.f1426b = new ArrayList();
            int size = this.f1426b.size();
            for (int i = 0; i < size; i++) {
                if (!c(i)) {
                    cVar.f1426b.add(this.f1426b.get(i));
                }
            }
        } catch (CloneNotSupportedException e4) {
            e2 = e4;
            e2.printStackTrace();
        }
        return cVar;
    }

    public synchronized void d(T t, int i, A a2) {
        this.f1429e++;
        h(t, i, a2);
        int i2 = this.f1429e - 1;
        this.f1429e = i2;
        if (i2 == 0) {
            long[] jArr = this.f1428d;
            if (jArr != null) {
                for (int length = jArr.length - 1; length >= 0; length--) {
                    long j = this.f1428d[length];
                    if (j != 0) {
                        k((length + 1) * 64, j);
                        this.f1428d[length] = 0;
                    }
                }
            }
            long j2 = this.f1427c;
            if (j2 != 0) {
                k(0, j2);
                this.f1427c = 0L;
            }
        }
    }

    public synchronized void j(C c2) {
        if (this.f1429e == 0) {
            this.f1426b.remove(c2);
        } else {
            int iLastIndexOf = this.f1426b.lastIndexOf(c2);
            if (iLastIndexOf >= 0) {
                l(iLastIndexOf);
            }
        }
    }
}
