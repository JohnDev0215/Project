package b.k;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: CallbackRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class c<C, T, A> implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<C> f2744b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f2745c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long[] f2746d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2747e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final a<C, T, A> f2748f;

    /* JADX INFO: compiled from: CallbackRegistry.java */
    public static abstract class a<C, T, A> {
        public abstract void a(C c2, T t, int i2, A a2);
    }

    public c(a<C, T, A> aVar) {
        this.f2748f = aVar;
    }

    public synchronized void a(C c2) {
        if (c2 == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }
        int iLastIndexOf = this.f2744b.lastIndexOf(c2);
        if (iLastIndexOf < 0 || c(iLastIndexOf)) {
            this.f2744b.add(c2);
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
            cVar.f2745c = 0L;
            cVar.f2746d = null;
            cVar.f2747e = 0;
            cVar.f2744b = new ArrayList();
            int size = this.f2744b.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (!c(i2)) {
                    cVar.f2744b.add(this.f2744b.get(i2));
                }
            }
        } catch (CloneNotSupportedException e4) {
            e2 = e4;
            e2.printStackTrace();
        }
        return cVar;
    }

    public final boolean c(int i2) {
        int i3;
        if (i2 < 64) {
            return ((1 << i2) & this.f2745c) != 0;
        }
        long[] jArr = this.f2746d;
        if (jArr != null && (i3 = (i2 / 64) - 1) < jArr.length) {
            return ((1 << (i2 % 64)) & jArr[i3]) != 0;
        }
        return false;
    }

    public synchronized void d(T t, int i2, A a2) {
        this.f2747e++;
        g(t, i2, a2);
        int i3 = this.f2747e - 1;
        this.f2747e = i3;
        if (i3 == 0) {
            long[] jArr = this.f2746d;
            if (jArr != null) {
                for (int length = jArr.length - 1; length >= 0; length--) {
                    long j = this.f2746d[length];
                    if (j != 0) {
                        j((length + 1) * 64, j);
                        this.f2746d[length] = 0;
                    }
                }
            }
            long j2 = this.f2745c;
            if (j2 != 0) {
                j(0, j2);
                this.f2745c = 0L;
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
    public final void e(T t, int i2, A a2, int i3, int i4, long j) {
        long j2 = 1;
        while (i3 < i4) {
            if ((j & j2) == 0) {
                this.f2748f.a(this.f2744b.get(i3), t, i2, a2);
            }
            j2 <<= 1;
            i3++;
        }
    }

    public final void f(T t, int i2, A a2) {
        e(t, i2, a2, 0, Math.min(64, this.f2744b.size()), this.f2745c);
    }

    public final void g(T t, int i2, A a2) {
        int size = this.f2744b.size();
        int length = this.f2746d == null ? -1 : r0.length - 1;
        h(t, i2, a2, length);
        e(t, i2, a2, (length + 2) * 64, size, 0L);
    }

    public final void h(T t, int i2, A a2, int i3) {
        if (i3 < 0) {
            f(t, i2, a2);
            return;
        }
        long j = this.f2746d[i3];
        int i4 = (i3 + 1) * 64;
        int iMin = Math.min(this.f2744b.size(), i4 + 64);
        h(t, i2, a2, i3 - 1);
        e(t, i2, a2, i4, iMin, j);
    }

    public synchronized void i(C c2) {
        if (this.f2747e == 0) {
            this.f2744b.remove(c2);
        } else {
            int iLastIndexOf = this.f2744b.lastIndexOf(c2);
            if (iLastIndexOf >= 0) {
                k(iLastIndexOf);
            }
        }
    }

    public final void j(int i2, long j) {
        long j2 = Long.MIN_VALUE;
        for (int i3 = (i2 + 64) - 1; i3 >= i2; i3--) {
            if ((j & j2) != 0) {
                this.f2744b.remove(i3);
            }
            j2 >>>= 1;
        }
    }

    public final void k(int i2) {
        if (i2 < 64) {
            this.f2745c = (1 << i2) | this.f2745c;
            return;
        }
        int i3 = (i2 / 64) - 1;
        long[] jArr = this.f2746d;
        if (jArr == null) {
            this.f2746d = new long[this.f2744b.size() / 64];
        } else if (jArr.length <= i3) {
            long[] jArr2 = new long[this.f2744b.size() / 64];
            long[] jArr3 = this.f2746d;
            System.arraycopy(jArr3, 0, jArr2, 0, jArr3.length);
            this.f2746d = jArr2;
        }
        long j = 1 << (i2 % 64);
        long[] jArr4 = this.f2746d;
        jArr4[i3] = j | jArr4[i3];
    }
}
