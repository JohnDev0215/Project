package b.k.a;

import android.os.Bundle;
import android.util.Log;
import androidx.lifecycle.g;
import androidx.lifecycle.m;
import androidx.lifecycle.n;
import androidx.lifecycle.q;
import androidx.lifecycle.r;
import androidx.lifecycle.s;
import b.d.h;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: LoaderManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
class b extends b.k.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static boolean f2408a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final g f2409b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c f2410c;

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class a<D> extends m<D> {
        private final int k;
        private final Bundle l;
        private final b.k.b.a<D> m;
        private g n;
        private C0060b<D> o;
        private b.k.b.a<D> p;

        @Override // androidx.lifecycle.LiveData
        protected void h() {
            if (b.f2408a) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            throw null;
        }

        @Override // androidx.lifecycle.LiveData
        protected void i() {
            if (b.f2408a) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            throw null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.lifecycle.LiveData
        public void j(n<? super D> nVar) {
            super.j(nVar);
            this.n = null;
        }

        @Override // androidx.lifecycle.m, androidx.lifecycle.LiveData
        public void k(D d2) {
            super.k(d2);
            if (this.p != null) {
                throw null;
            }
        }

        b.k.b.a<D> l(boolean z) {
            if (b.f2408a) {
                Log.v("LoaderManager", "  Destroying: " + this);
            }
            throw null;
        }

        public void m(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
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

        void n() {
            g gVar = this.n;
            C0060b<D> c0060b = this.o;
            if (gVar == null || c0060b == null) {
                return;
            }
            super.j(c0060b);
            f(gVar, c0060b);
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

    /* JADX INFO: renamed from: b.k.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    static class C0060b<D> implements n<D> {
    }

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    static class c extends q {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static final r.b f2411c = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private h<a> f2412d = new h<>();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f2413e = false;

        /* JADX INFO: compiled from: LoaderManagerImpl.java */
        static class a implements r.b {
            a() {
            }

            @Override // androidx.lifecycle.r.b
            public <T extends q> T a(Class<T> cls) {
                return new c();
            }
        }

        c() {
        }

        static c e(s sVar) {
            return (c) new r(sVar, f2411c).a(c.class);
        }

        @Override // androidx.lifecycle.q
        protected void c() {
            super.c();
            int iL = this.f2412d.l();
            for (int i = 0; i < iL; i++) {
                this.f2412d.m(i).l(true);
            }
            this.f2412d.b();
        }

        public void d(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.f2412d.l() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i = 0; i < this.f2412d.l(); i++) {
                    a aVarM = this.f2412d.m(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.f2412d.i(i));
                    printWriter.print(": ");
                    printWriter.println(aVarM.toString());
                    aVarM.m(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }

        void f() {
            int iL = this.f2412d.l();
            for (int i = 0; i < iL; i++) {
                this.f2412d.m(i).n();
            }
        }
    }

    b(g gVar, s sVar) {
        this.f2409b = gVar;
        this.f2410c = c.e(sVar);
    }

    @Override // b.k.a.a
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.f2410c.d(str, fileDescriptor, printWriter, strArr);
    }

    @Override // b.k.a.a
    public void c() {
        this.f2410c.f();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        b.g.j.a.a(this.f2409b, sb);
        sb.append("}}");
        return sb.toString();
    }
}
