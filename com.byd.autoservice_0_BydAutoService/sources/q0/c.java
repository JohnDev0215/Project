package q0;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: CallbackRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class c<C, T, A> implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<C> f4554b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f4555c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long[] f4556d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4557e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final a<C, T, A> f4558f;

    /* JADX INFO: compiled from: CallbackRegistry.java */
    public static abstract class a<C, T, A> {
        public abstract void a(C c3, T t3, int i3, A a3);
    }

    public c(a<C, T, A> aVar) {
        this.f4558f = aVar;
    }

    public synchronized void a(C c3) {
        if (c3 == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }
        int iLastIndexOf = this.f4554b.lastIndexOf(c3);
        if (iLastIndexOf < 0 || c(iLastIndexOf)) {
            this.f4554b.add(c3);
        }
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public synchronized c<C, T, A> clone() {
        c<C, T, A> cVar;
        CloneNotSupportedException e3;
        try {
            cVar = (c) super.clone();
            try {
                cVar.f4555c = 0L;
                cVar.f4556d = null;
                cVar.f4557e = 0;
                cVar.f4554b = new ArrayList();
                int size = this.f4554b.size();
                for (int i3 = 0; i3 < size; i3++) {
                    if (!c(i3)) {
                        cVar.f4554b.add(this.f4554b.get(i3));
                    }
                }
            } catch (CloneNotSupportedException e4) {
                e3 = e4;
                e3.printStackTrace();
            }
        } catch (CloneNotSupportedException e5) {
            cVar = null;
            e3 = e5;
        }
        return cVar;
    }

    public final boolean c(int i3) {
        int i4;
        if (i3 < 64) {
            return ((1 << i3) & this.f4555c) != 0;
        }
        long[] jArr = this.f4556d;
        if (jArr != null && (i4 = (i3 / 64) - 1) < jArr.length) {
            return ((1 << (i3 % 64)) & jArr[i4]) != 0;
        }
        return false;
    }

    public synchronized void d(T t3, int i3, A a3) {
        this.f4557e++;
        g(t3, i3, a3);
        int i4 = this.f4557e - 1;
        this.f4557e = i4;
        if (i4 == 0) {
            long[] jArr = this.f4556d;
            if (jArr != null) {
                for (int length = jArr.length - 1; length >= 0; length--) {
                    long j3 = this.f4556d[length];
                    if (j3 != 0) {
                        j((length + 1) * 64, j3);
                        this.f4556d[length] = 0;
                    }
                }
            }
            long j4 = this.f4555c;
            if (j4 != 0) {
                j(0, j4);
                this.f4555c = 0L;
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
    public final void e(T t3, int i3, A a3, int i4, int i5, long j3) {
        long j4 = 1;
        while (i4 < i5) {
            if ((j3 & j4) == 0) {
                this.f4558f.a(this.f4554b.get(i4), t3, i3, a3);
            }
            j4 <<= 1;
            i4++;
        }
    }

    public final void f(T t3, int i3, A a3) {
        e(t3, i3, a3, 0, Math.min(64, this.f4554b.size()), this.f4555c);
    }

    public final void g(T t3, int i3, A a3) {
        int size = this.f4554b.size();
        int length = this.f4556d == null ? -1 : r0.length - 1;
        h(t3, i3, a3, length);
        e(t3, i3, a3, (length + 2) * 64, size, 0L);
    }

    public final void h(T t3, int i3, A a3, int i4) {
        if (i4 < 0) {
            f(t3, i3, a3);
            return;
        }
        long j3 = this.f4556d[i4];
        int i5 = (i4 + 1) * 64;
        int iMin = Math.min(this.f4554b.size(), i5 + 64);
        h(t3, i3, a3, i4 - 1);
        e(t3, i3, a3, i5, iMin, j3);
    }

    public synchronized void i(C c3) {
        if (this.f4557e == 0) {
            this.f4554b.remove(c3);
        } else {
            int iLastIndexOf = this.f4554b.lastIndexOf(c3);
            if (iLastIndexOf >= 0) {
                k(iLastIndexOf);
            }
        }
    }

    public final void j(int i3, long j3) {
        long j4 = Long.MIN_VALUE;
        for (int i4 = (i3 + 64) - 1; i4 >= i3; i4--) {
            if ((j3 & j4) != 0) {
                this.f4554b.remove(i4);
            }
            j4 >>>= 1;
        }
    }

    public final void k(int i3) {
        if (i3 < 64) {
            this.f4555c = (1 << i3) | this.f4555c;
            return;
        }
        int i4 = (i3 / 64) - 1;
        long[] jArr = this.f4556d;
        if (jArr == null) {
            this.f4556d = new long[this.f4554b.size() / 64];
        } else if (jArr.length <= i4) {
            long[] jArr2 = new long[this.f4554b.size() / 64];
            long[] jArr3 = this.f4556d;
            System.arraycopy(jArr3, 0, jArr2, 0, jArr3.length);
            this.f4556d = jArr2;
        }
        long j3 = 1 << (i3 % 64);
        long[] jArr4 = this.f4556d;
        jArr4[i4] = j3 | jArr4[i4];
    }
}
