package defpackage;

import android.util.Log;
import androidx.lifecycle.l;
import androidx.loader.content.Loader;

/* JADX INFO: compiled from: LoaderManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class j91 extends vd1 {

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class a<D> extends zj1<D> implements Loader.c<D> {
        @Override // androidx.lifecycle.LiveData
        public final void f() {
            if (!j91.J(2)) {
                throw null;
            }
            toString();
            throw null;
        }

        @Override // androidx.lifecycle.LiveData
        public final void g() {
            if (!j91.J(2)) {
                throw null;
            }
            toString();
            throw null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.lifecycle.LiveData
        public final void h(bq1<? super D> bq1Var) {
            super.h(bq1Var);
        }

        @Override // defpackage.zj1, androidx.lifecycle.LiveData
        public final void i(D d) {
            super.i(d);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #0 : ");
            throw null;
        }
    }

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class b extends ym3 {
        public static final a d = new a();
        public final nq2<a> c = new nq2<>();

        /* JADX INFO: compiled from: LoaderManagerImpl.java */
        public class a implements l.a {
            @Override // androidx.lifecycle.l.a
            public final <T extends ym3> T a(Class<T> cls) {
                return new b();
            }
        }

        @Override // defpackage.ym3
        public final void a() {
            nq2<a> nq2Var = this.c;
            int i = nq2Var.g;
            if (i > 0) {
                a aVar = (a) nq2Var.f[0];
                if (j91.J(3)) {
                    aVar.toString();
                    throw null;
                }
                aVar.getClass();
                throw null;
            }
            Object[] objArr = nq2Var.f;
            for (int i2 = 0; i2 < i; i2++) {
                objArr[i2] = null;
            }
            nq2Var.g = 0;
        }
    }

    public static boolean J(int i) {
        return Log.isLoggable("LoaderManager", i);
    }
}
