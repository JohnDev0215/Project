package androidx.databinding;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: CallbackRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class c<C, T, A> implements Cloneable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private List<C> f2065e = new ArrayList();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private long f2066g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private long[] f2067h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f2068i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final a<C, T, A> f2069j;

    /* JADX INFO: compiled from: CallbackRegistry.java */
    public static abstract class a<C, T, A> {
        public abstract void a(C c7, T t6, int i7, A a7);
    }

    public c(a<C, T, A> aVar) {
        this.f2069j = aVar;
    }

    private boolean c(int i7) {
        int i8;
        if (i7 < 64) {
            return ((1 << i7) & this.f2066g) != 0;
        }
        long[] jArr = this.f2067h;
        if (jArr != null && (i8 = (i7 / 64) - 1) < jArr.length) {
            return ((1 << (i7 % 64)) & jArr[i8]) != 0;
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
    private void e(T t6, int i7, A a7, int i8, int i9, long j7) {
        long j8 = 1;
        while (i8 < i9) {
            if ((j7 & j8) == 0) {
                this.f2069j.a(this.f2065e.get(i8), t6, i7, a7);
            }
            j8 <<= 1;
            i8++;
        }
    }

    private void f(T t6, int i7, A a7) {
        e(t6, i7, a7, 0, Math.min(64, this.f2065e.size()), this.f2066g);
    }

    private void g(T t6, int i7, A a7) {
        int size = this.f2065e.size();
        int length = this.f2067h == null ? -1 : r0.length - 1;
        h(t6, i7, a7, length);
        e(t6, i7, a7, (length + 2) * 64, size, 0L);
    }

    private void h(T t6, int i7, A a7, int i8) {
        if (i8 < 0) {
            f(t6, i7, a7);
            return;
        }
        long j7 = this.f2067h[i8];
        int i9 = (i8 + 1) * 64;
        int iMin = Math.min(this.f2065e.size(), i9 + 64);
        h(t6, i7, a7, i8 - 1);
        e(t6, i7, a7, i9, iMin, j7);
    }

    private void j(int i7, long j7) {
        long j8 = Long.MIN_VALUE;
        for (int i8 = (i7 + 64) - 1; i8 >= i7; i8--) {
            if ((j7 & j8) != 0) {
                this.f2065e.remove(i8);
            }
            j8 >>>= 1;
        }
    }

    private void k(int i7) {
        if (i7 < 64) {
            this.f2066g = (1 << i7) | this.f2066g;
            return;
        }
        int i8 = (i7 / 64) - 1;
        long[] jArr = this.f2067h;
        if (jArr == null) {
            this.f2067h = new long[this.f2065e.size() / 64];
        } else if (jArr.length <= i8) {
            long[] jArr2 = new long[this.f2065e.size() / 64];
            long[] jArr3 = this.f2067h;
            System.arraycopy(jArr3, 0, jArr2, 0, jArr3.length);
            this.f2067h = jArr2;
        }
        long j7 = 1 << (i7 % 64);
        long[] jArr4 = this.f2067h;
        jArr4[i8] = j7 | jArr4[i8];
    }

    public synchronized void a(C c7) {
        if (c7 == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }
        int iLastIndexOf = this.f2065e.lastIndexOf(c7);
        if (iLastIndexOf < 0 || c(iLastIndexOf)) {
            this.f2065e.add(c7);
        }
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public synchronized c<C, T, A> clone() {
        c<C, T, A> cVar;
        CloneNotSupportedException e7;
        try {
            cVar = (c) super.clone();
        } catch (CloneNotSupportedException e8) {
            cVar = null;
            e7 = e8;
        }
        try {
            cVar.f2066g = 0L;
            cVar.f2067h = null;
            cVar.f2068i = 0;
            cVar.f2065e = new ArrayList();
            int size = this.f2065e.size();
            for (int i7 = 0; i7 < size; i7++) {
                if (!c(i7)) {
                    cVar.f2065e.add(this.f2065e.get(i7));
                }
            }
        } catch (CloneNotSupportedException e9) {
            e7 = e9;
            e7.printStackTrace();
        }
        return cVar;
    }

    public synchronized void d(T t6, int i7, A a7) {
        this.f2068i++;
        g(t6, i7, a7);
        int i8 = this.f2068i - 1;
        this.f2068i = i8;
        if (i8 == 0) {
            long[] jArr = this.f2067h;
            if (jArr != null) {
                for (int length = jArr.length - 1; length >= 0; length--) {
                    long j7 = this.f2067h[length];
                    if (j7 != 0) {
                        j((length + 1) * 64, j7);
                        this.f2067h[length] = 0;
                    }
                }
            }
            long j8 = this.f2066g;
            if (j8 != 0) {
                j(0, j8);
                this.f2066g = 0L;
            }
        }
    }

    public synchronized void i(C c7) {
        if (this.f2068i == 0) {
            this.f2065e.remove(c7);
        } else {
            int iLastIndexOf = this.f2065e.lastIndexOf(c7);
            if (iLastIndexOf >= 0) {
                k(iLastIndexOf);
            }
        }
    }
}
