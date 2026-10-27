package a.k;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class c<C, T, A> implements Cloneable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<C> f909a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f910b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long[] f911c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f912d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a<C, T, A> f913e;

    public static abstract class a<C, T, A> {
        public abstract void a(C c2, T t, int i, A a2);
    }

    public c(a<C, T, A> aVar) {
        this.f913e = aVar;
    }

    public synchronized void a(C c2) {
        if (c2 == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }
        int iLastIndexOf = this.f909a.lastIndexOf(c2);
        if (iLastIndexOf < 0 || b(iLastIndexOf)) {
            this.f909a.add(c2);
        }
    }

    public final boolean b(int i) {
        int i2;
        if (i < 64) {
            return (this.f910b & (1 << i)) != 0;
        }
        long[] jArr = this.f911c;
        if (jArr != null && (i2 = (i / 64) - 1) < jArr.length) {
            return ((1 << (i % 64)) & jArr[i2]) != 0;
        }
        return false;
    }

    public synchronized void c(T t, int i, A a2) {
        this.f912d++;
        int size = this.f909a.size();
        int length = this.f911c == null ? -1 : r0.length - 1;
        e(t, i, a2, length);
        d(t, i, a2, (length + 2) * 64, size, 0L);
        int i2 = this.f912d - 1;
        this.f912d = i2;
        if (i2 == 0) {
            if (this.f911c != null) {
                for (int length2 = this.f911c.length - 1; length2 >= 0; length2--) {
                    long j = this.f911c[length2];
                    if (j != 0) {
                        g((length2 + 1) * 64, j);
                        this.f911c[length2] = 0;
                    }
                }
            }
            if (this.f910b != 0) {
                g(0, this.f910b);
                this.f910b = 0L;
            }
        }
    }

    public Object clone() {
        c cVar;
        CloneNotSupportedException e2;
        synchronized (this) {
            try {
                cVar = (c) super.clone();
            } catch (CloneNotSupportedException e3) {
                cVar = null;
                e2 = e3;
            }
            try {
                cVar.f910b = 0L;
                cVar.f911c = null;
                cVar.f912d = 0;
                cVar.f909a = new ArrayList();
                int size = this.f909a.size();
                for (int i = 0; i < size; i++) {
                    if (!b(i)) {
                        cVar.f909a.add(this.f909a.get(i));
                    }
                }
            } catch (CloneNotSupportedException e4) {
                e2 = e4;
                e2.printStackTrace();
            }
        }
        return cVar;
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
    public final void d(T t, int i, A a2, int i2, int i3, long j) {
        long j2 = 1;
        while (i2 < i3) {
            if ((j & j2) == 0) {
                this.f913e.a(this.f909a.get(i2), t, i, a2);
            }
            j2 <<= 1;
            i2++;
        }
    }

    public final void e(T t, int i, A a2, int i2) {
        if (i2 < 0) {
            d(t, i, a2, 0, Math.min(64, this.f909a.size()), this.f910b);
            return;
        }
        long j = this.f911c[i2];
        int i3 = (i2 + 1) * 64;
        int iMin = Math.min(this.f909a.size(), i3 + 64);
        e(t, i, a2, i2 - 1);
        d(t, i, a2, i3, iMin, j);
    }

    public synchronized void f(C c2) {
        if (this.f912d == 0) {
            this.f909a.remove(c2);
        } else {
            int iLastIndexOf = this.f909a.lastIndexOf(c2);
            if (iLastIndexOf >= 0) {
                h(iLastIndexOf);
            }
        }
    }

    public final void g(int i, long j) {
        long j2 = Long.MIN_VALUE;
        for (int i2 = (i + 64) - 1; i2 >= i; i2--) {
            if ((j & j2) != 0) {
                this.f909a.remove(i2);
            }
            j2 >>>= 1;
        }
    }

    public final void h(int i) {
        if (i < 64) {
            this.f910b = (1 << i) | this.f910b;
            return;
        }
        int i2 = (i / 64) - 1;
        long[] jArr = this.f911c;
        if (jArr == null) {
            this.f911c = new long[this.f909a.size() / 64];
        } else if (jArr.length <= i2) {
            long[] jArr2 = new long[this.f909a.size() / 64];
            long[] jArr3 = this.f911c;
            System.arraycopy(jArr3, 0, jArr2, 0, jArr3.length);
            this.f911c = jArr2;
        }
        long[] jArr4 = this.f911c;
        jArr4[i2] = (1 << (i % 64)) | jArr4[i2];
    }
}
