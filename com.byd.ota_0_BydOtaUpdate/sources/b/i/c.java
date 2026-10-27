package b.i;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: CallbackRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class c<C, T, A> implements Cloneable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List<C> f1863c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f1864d = 0;
    public long[] s;
    public int x;
    public final a<C, T, A> y;

    /* JADX INFO: compiled from: CallbackRegistry.java */
    public static abstract class a<C, T, A> {
        public abstract void a(C c2, T t, int i2, A a2);
    }

    public c(a<C, T, A> aVar) {
        this.y = aVar;
    }

    public synchronized void a(C c2) {
        if (c2 == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }
        int iLastIndexOf = this.f1863c.lastIndexOf(c2);
        if (iLastIndexOf < 0 || e(iLastIndexOf)) {
            this.f1863c.add(c2);
        }
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public synchronized c<C, T, A> clone() {
        c<C, T, A> cVar;
        CloneNotSupportedException e2;
        try {
            cVar = (c) super.clone();
            try {
                cVar.f1864d = 0L;
                cVar.s = null;
                cVar.x = 0;
                cVar.f1863c = new ArrayList();
                int size = this.f1863c.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (!e(i2)) {
                        cVar.f1863c.add(this.f1863c.get(i2));
                    }
                }
            } catch (CloneNotSupportedException e3) {
                e2 = e3;
                e2.printStackTrace();
            }
        } catch (CloneNotSupportedException e4) {
            cVar = null;
            e2 = e4;
        }
        return cVar;
    }

    public final boolean e(int i2) {
        int i3;
        if (i2 < 64) {
            return ((1 << i2) & this.f1864d) != 0;
        }
        long[] jArr = this.s;
        if (jArr != null && (i3 = (i2 / 64) - 1) < jArr.length) {
            return ((1 << (i2 % 64)) & jArr[i3]) != 0;
        }
        return false;
    }

    public synchronized void f(T t, int i2, A a2) {
        this.x++;
        i(t, i2, a2);
        int i3 = this.x - 1;
        this.x = i3;
        if (i3 == 0) {
            long[] jArr = this.s;
            if (jArr != null) {
                for (int length = jArr.length - 1; length >= 0; length--) {
                    long j = this.s[length];
                    if (j != 0) {
                        l((length + 1) * 64, j);
                        this.s[length] = 0;
                    }
                }
            }
            long j2 = this.f1864d;
            if (j2 != 0) {
                l(0, j2);
                this.f1864d = 0L;
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
    public final void g(T t, int i2, A a2, int i3, int i4, long j) {
        long j2 = 1;
        while (i3 < i4) {
            if ((j & j2) == 0) {
                this.y.a(this.f1863c.get(i3), t, i2, a2);
            }
            j2 <<= 1;
            i3++;
        }
    }

    public final void h(T t, int i2, A a2) {
        g(t, i2, a2, 0, Math.min(64, this.f1863c.size()), this.f1864d);
    }

    public final void i(T t, int i2, A a2) {
        int size = this.f1863c.size();
        int length = this.s == null ? -1 : r0.length - 1;
        j(t, i2, a2, length);
        g(t, i2, a2, (length + 2) * 64, size, 0L);
    }

    public final void j(T t, int i2, A a2, int i3) {
        if (i3 < 0) {
            h(t, i2, a2);
            return;
        }
        long j = this.s[i3];
        int i4 = (i3 + 1) * 64;
        int iMin = Math.min(this.f1863c.size(), i4 + 64);
        j(t, i2, a2, i3 - 1);
        g(t, i2, a2, i4, iMin, j);
    }

    public synchronized void k(C c2) {
        if (this.x == 0) {
            this.f1863c.remove(c2);
        } else {
            int iLastIndexOf = this.f1863c.lastIndexOf(c2);
            if (iLastIndexOf >= 0) {
                m(iLastIndexOf);
            }
        }
    }

    public final void l(int i2, long j) {
        long j2 = Long.MIN_VALUE;
        for (int i3 = (i2 + 64) - 1; i3 >= i2; i3--) {
            if ((j & j2) != 0) {
                this.f1863c.remove(i3);
            }
            j2 >>>= 1;
        }
    }

    public final void m(int i2) {
        if (i2 < 64) {
            this.f1864d = (1 << i2) | this.f1864d;
            return;
        }
        int i3 = (i2 / 64) - 1;
        long[] jArr = this.s;
        if (jArr == null) {
            this.s = new long[this.f1863c.size() / 64];
        } else if (jArr.length <= i3) {
            long[] jArr2 = new long[this.f1863c.size() / 64];
            long[] jArr3 = this.s;
            System.arraycopy(jArr3, 0, jArr2, 0, jArr3.length);
            this.s = jArr2;
        }
        long j = 1 << (i2 % 64);
        long[] jArr4 = this.s;
        jArr4[i3] = j | jArr4[i3];
    }
}
