package spi;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: Excluder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class s3 implements ob, Cloneable {
    public static final s3 f = new s3();
    public double a = -1.0d;
    public int b = 136;
    public boolean c = true;
    public List<t3> d = Collections.emptyList();
    public List<t3> e = Collections.emptyList();

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: Excluder.java */
    public class a<T> extends nb<T> {
        public nb<T> a;
        public final /* synthetic */ boolean b;
        public final /* synthetic */ boolean c;
        public final /* synthetic */ j4 d;
        public final /* synthetic */ vb e;

        public a(boolean z, boolean z2, j4 j4Var, vb vbVar) {
            this.b = z;
            this.c = z2;
            this.d = j4Var;
            this.e = vbVar;
        }

        @Override // spi.nb
        public final T a(o5 o5Var) throws IOException {
            if (this.b) {
                o5Var.A();
                return null;
            }
            nb<T> nbVarF = this.a;
            if (nbVarF == null) {
                nbVarF = this.d.f(s3.this, this.e);
                this.a = nbVarF;
            }
            return nbVarF.a(o5Var);
        }

        @Override // spi.nb
        public final void b(v5 v5Var, T t) throws IOException {
            if (this.c) {
                v5Var.i();
                return;
            }
            nb<T> nbVarF = this.a;
            if (nbVarF == null) {
                nbVarF = this.d.f(s3.this, this.e);
                this.a = nbVarF;
            }
            nbVarF.b(v5Var, t);
        }
    }

    @Override // spi.ob
    public final <T> nb<T> a(j4 j4Var, vb<T> vbVar) {
        Class<? super T> cls = vbVar.a;
        boolean zB = b(cls);
        boolean z = zB || c(cls, true);
        boolean z2 = zB || c(cls, false);
        if (z || z2) {
            return new a(z2, z, j4Var, vbVar);
        }
        return null;
    }

    public final boolean b(Class<?> cls) {
        if (this.a == -1.0d || f((ea) cls.getAnnotation(ea.class), (cc) cls.getAnnotation(cc.class))) {
            return (!this.c && e(cls)) || d(cls);
        }
        return true;
    }

    public final boolean c(Class<?> cls, boolean z) {
        Iterator<t3> it = (z ? this.d : this.e).iterator();
        while (it.hasNext()) {
            if (it.next().a()) {
                return true;
            }
        }
        return false;
    }

    public final Object clone() {
        try {
            return (s3) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean d(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    public final boolean e(Class<?> cls) {
        if (cls.isMemberClass()) {
            if (!((cls.getModifiers() & 8) != 0)) {
                return true;
            }
        }
        return false;
    }

    public final boolean f(ea eaVar, cc ccVar) {
        if (eaVar == null || eaVar.value() <= this.a) {
            return ccVar == null || (ccVar.value() > this.a ? 1 : (ccVar.value() == this.a ? 0 : -1)) > 0;
        }
        return false;
    }
}
