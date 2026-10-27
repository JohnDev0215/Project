package u0;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: CallbackRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class c<C, T, A> implements Cloneable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public List<C> f8121d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f8122e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long[] f8123f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f8124g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final a<C, T, A> f8125h;

    /* JADX INFO: compiled from: CallbackRegistry.java */
    public static abstract class a<C, T, A> {
        public abstract void a(C c8, T t7, int i8, A a8);
    }

    public c(a<C, T, A> aVar) {
        this.f8125h = aVar;
    }

    public synchronized void a(C c8) {
        if (c8 == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }
        int iLastIndexOf = this.f8121d.lastIndexOf(c8);
        if (iLastIndexOf < 0 || c(iLastIndexOf)) {
            this.f8121d.add(c8);
        }
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public synchronized c<C, T, A> clone() {
        c<C, T, A> cVar;
        CloneNotSupportedException e8;
        try {
            cVar = (c) super.clone();
        } catch (CloneNotSupportedException e9) {
            cVar = null;
            e8 = e9;
        }
        try {
            cVar.f8122e = 0L;
            cVar.f8123f = null;
            cVar.f8124g = 0;
            cVar.f8121d = new ArrayList();
            int size = this.f8121d.size();
            for (int i8 = 0; i8 < size; i8++) {
                if (!c(i8)) {
                    cVar.f8121d.add(this.f8121d.get(i8));
                }
            }
        } catch (CloneNotSupportedException e10) {
            e8 = e10;
            e8.printStackTrace();
        }
        return cVar;
    }

    public final boolean c(int i8) {
        int i9;
        if (i8 < 64) {
            return ((1 << i8) & this.f8122e) != 0;
        }
        long[] jArr = this.f8123f;
        if (jArr != null && (i9 = (i8 / 64) - 1) < jArr.length) {
            return ((1 << (i8 % 64)) & jArr[i9]) != 0;
        }
        return false;
    }

    public synchronized void d(T t7, int i8, A a8) {
        this.f8124g++;
        g(t7, i8, a8);
        int i9 = this.f8124g - 1;
        this.f8124g = i9;
        if (i9 == 0) {
            long[] jArr = this.f8123f;
            if (jArr != null) {
                for (int length = jArr.length - 1; length >= 0; length--) {
                    long j8 = this.f8123f[length];
                    if (j8 != 0) {
                        j((length + 1) * 64, j8);
                        this.f8123f[length] = 0;
                    }
                }
            }
            long j9 = this.f8122e;
            if (j9 != 0) {
                j(0, j9);
                this.f8122e = 0L;
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
    public final void e(T t7, int i8, A a8, int i9, int i10, long j8) {
        long j9 = 1;
        while (i9 < i10) {
            if ((j8 & j9) == 0) {
                this.f8125h.a(this.f8121d.get(i9), t7, i8, a8);
            }
            j9 <<= 1;
            i9++;
        }
    }

    public final void f(T t7, int i8, A a8) {
        e(t7, i8, a8, 0, Math.min(64, this.f8121d.size()), this.f8122e);
    }

    public final void g(T t7, int i8, A a8) {
        int size = this.f8121d.size();
        int length = this.f8123f == null ? -1 : r0.length - 1;
        h(t7, i8, a8, length);
        e(t7, i8, a8, (length + 2) * 64, size, 0L);
    }

    public final void h(T t7, int i8, A a8, int i9) {
        if (i9 < 0) {
            f(t7, i8, a8);
            return;
        }
        long j8 = this.f8123f[i9];
        int i10 = (i9 + 1) * 64;
        int iMin = Math.min(this.f8121d.size(), i10 + 64);
        h(t7, i8, a8, i9 - 1);
        e(t7, i8, a8, i10, iMin, j8);
    }

    public synchronized void i(C c8) {
        if (this.f8124g == 0) {
            this.f8121d.remove(c8);
        } else {
            int iLastIndexOf = this.f8121d.lastIndexOf(c8);
            if (iLastIndexOf >= 0) {
                k(iLastIndexOf);
            }
        }
    }

    public final void j(int i8, long j8) {
        long j9 = Long.MIN_VALUE;
        for (int i9 = (i8 + 64) - 1; i9 >= i8; i9--) {
            if ((j8 & j9) != 0) {
                this.f8121d.remove(i9);
            }
            j9 >>>= 1;
        }
    }

    public final void k(int i8) {
        if (i8 < 64) {
            this.f8122e = (1 << i8) | this.f8122e;
            return;
        }
        int i9 = (i8 / 64) - 1;
        long[] jArr = this.f8123f;
        if (jArr == null) {
            this.f8123f = new long[this.f8121d.size() / 64];
        } else if (jArr.length <= i9) {
            long[] jArr2 = new long[this.f8121d.size() / 64];
            long[] jArr3 = this.f8123f;
            System.arraycopy(jArr3, 0, jArr2, 0, jArr3.length);
            this.f8123f = jArr2;
        }
        long j8 = 1 << (i8 % 64);
        long[] jArr4 = this.f8123f;
        jArr4[i9] = j8 | jArr4[i9];
    }
}
