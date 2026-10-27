package b.l.a;

import android.os.Bundle;
import android.util.Log;
import androidx.lifecycle.g;
import androidx.lifecycle.l;
import androidx.lifecycle.m;
import androidx.lifecycle.o;
import androidx.lifecycle.p;
import androidx.lifecycle.q;
import b.d.h;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: LoaderManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
class b extends b.l.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static boolean f2070a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final g f2071b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c f2072c;

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class a<D> extends l<D> {
        private final int k;
        private final Bundle l;
        private final b.l.b.a<D> m;
        private g n;
        private C0045b<D> o;
        private b.l.b.a<D> p;

        @Override // androidx.lifecycle.LiveData
        protected void e() {
            if (b.f2070a) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            throw null;
        }

        @Override // androidx.lifecycle.LiveData
        protected void f() {
            if (b.f2070a) {
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

        b.l.b.a<D> i(boolean z) {
            if (b.f2070a) {
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
            C0045b<D> c0045b = this.o;
            if (gVar == null || c0045b == null) {
                return;
            }
            super.g(c0045b);
            d(gVar, c0045b);
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

    /* JADX INFO: renamed from: b.l.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    static class C0045b<D> implements m<D> {
    }

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    static class c extends o {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static final p.b f2073c = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private h<a> f2074d = new h<>();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f2075e = false;

        /* JADX INFO: compiled from: LoaderManagerImpl.java */
        static class a implements p.b {
            a() {
            }

            @Override // androidx.lifecycle.p.b
            public <T extends o> T a(Class<T> cls) {
                return new c();
            }
        }

        c() {
        }

        static c e(q qVar) {
            return (c) new p(qVar, f2073c).a(c.class);
        }

        @Override // androidx.lifecycle.o
        protected void c() {
            super.c();
            int iL = this.f2074d.l();
            for (int i = 0; i < iL; i++) {
                this.f2074d.m(i).i(true);
            }
            this.f2074d.b();
        }

        public void d(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.f2074d.l() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i = 0; i < this.f2074d.l(); i++) {
                    a aVarM = this.f2074d.m(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.f2074d.i(i));
                    printWriter.print(": ");
                    printWriter.println(aVarM.toString());
                    aVarM.j(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }

        void f() {
            int iL = this.f2074d.l();
            for (int i = 0; i < iL; i++) {
                this.f2074d.m(i).k();
            }
        }
    }

    b(g gVar, q qVar) {
        this.f2071b = gVar;
        this.f2072c = c.e(qVar);
    }

    @Override // b.l.a.a
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.f2072c.d(str, fileDescriptor, printWriter, strArr);
    }

    @Override // b.l.a.a
    public void c() {
        this.f2072c.f();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        b.g.j.a.a(this.f2071b, sb);
        sb.append("}}");
        return sb.toString();
    }
}
