package b.m.a;

import android.os.Bundle;
import android.util.Log;
import androidx.lifecycle.g;
import androidx.lifecycle.l;
import androidx.lifecycle.m;
import androidx.lifecycle.p;
import androidx.lifecycle.q;
import androidx.lifecycle.r;
import b.d.h;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: LoaderManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
class b extends b.m.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static boolean f2796a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final g f2797b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c f2798c;

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class a<D> extends l<D> {
        private final int k;
        private final Bundle l;
        private final b.m.b.a<D> m;
        private g n;
        private C0053b<D> o;
        private b.m.b.a<D> p;

        @Override // androidx.lifecycle.LiveData
        protected void e() {
            if (b.f2796a) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            throw null;
        }

        @Override // androidx.lifecycle.LiveData
        protected void f() {
            if (b.f2796a) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            throw null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.lifecycle.LiveData
        public void g(m<? super D> mVar) {
            super.g(mVar);
            this.n = null;
        }

        @Override // androidx.lifecycle.l, androidx.lifecycle.LiveData
        public void h(D d2) {
            super.h(d2);
            if (this.p != null) {
                throw null;
            }
        }

        b.m.b.a<D> i(boolean z) {
            if (b.f2796a) {
                Log.v("LoaderManager", "  Destroying: " + this);
            }
            throw null;
        }

        public void j(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.k);
            printWriter.print(" mArgs=");
            printWriter.println(this.l);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.m);
            String str2 = str + "  ";
            throw null;
        }

        void k() {
            g gVar = this.n;
            C0053b<D> c0053b = this.o;
            if (gVar == null || c0053b == null) {
                return;
            }
            super.g(c0053b);
            d(gVar, c0053b);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.k);
            sb.append(" : ");
            b.g.j.a.a(this.m, sb);
            sb.append("}}");
            return sb.toString();
        }
    }

    /* JADX INFO: renamed from: b.m.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    static class C0053b<D> implements m<D> {
    }

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    static class c extends p {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static final q.a f2799c = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private h<a> f2800d = new h<>();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f2801e = false;

        /* JADX INFO: compiled from: LoaderManagerImpl.java */
        static class a implements q.a {
            a() {
            }

            @Override // androidx.lifecycle.q.a
            public <T extends p> T a(Class<T> cls) {
                return new c();
            }
        }

        c() {
        }

        static c f(r rVar) {
            return (c) new q(rVar, f2799c).a(c.class);
        }

        @Override // androidx.lifecycle.p
        protected void d() {
            super.d();
            int iO = this.f2800d.o();
            for (int i2 = 0; i2 < iO; i2++) {
                this.f2800d.p(i2).i(true);
            }
            this.f2800d.c();
        }

        public void e(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.f2800d.o() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i2 = 0; i2 < this.f2800d.o(); i2++) {
                    a aVarP = this.f2800d.p(i2);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.f2800d.k(i2));
                    printWriter.print(": ");
                    printWriter.println(aVarP.toString());
                    aVarP.j(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }

        void g() {
            int iO = this.f2800d.o();
            for (int i2 = 0; i2 < iO; i2++) {
                this.f2800d.p(i2).k();
            }
        }
    }

    b(g gVar, r rVar) {
        this.f2797b = gVar;
        this.f2798c = c.f(rVar);
    }

    @Override // b.m.a.a
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.f2798c.e(str, fileDescriptor, printWriter, strArr);
    }

    @Override // b.m.a.a
    public void c() {
        this.f2798c.g();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        b.g.j.a.a(this.f2797b, sb);
        sb.append("}}");
        return sb.toString();
    }
}
