package a.l.a;

import a.d.h;
import android.os.Bundle;
import android.util.Log;
import androidx.lifecycle.g;
import androidx.lifecycle.l;
import androidx.lifecycle.m;
import androidx.lifecycle.p;
import androidx.lifecycle.q;
import androidx.lifecycle.r;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
class b extends a.l.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static boolean f488a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final g f489b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c f490c;

    public static class a<D> extends l<D> {
        private final int k;
        private final Bundle l;
        private final a.l.b.a<D> m;
        private g n;
        private C0022b<D> o;
        private a.l.b.a<D> p;

        @Override // androidx.lifecycle.LiveData
        protected void e() {
            if (b.f488a) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            throw null;
        }

        @Override // androidx.lifecycle.LiveData
        protected void f() {
            if (b.f488a) {
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

        a.l.b.a<D> i(boolean z) {
            if (b.f488a) {
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
            C0022b<D> c0022b = this.o;
            if (gVar == null || c0022b == null) {
                return;
            }
            super.g(c0022b);
            d(gVar, c0022b);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.k);
            sb.append(" : ");
            a.g.j.a.a(this.m, sb);
            sb.append("}}");
            return sb.toString();
        }
    }

    /* JADX INFO: renamed from: a.l.a.b$b, reason: collision with other inner class name */
    static class C0022b<D> implements m<D> {
    }

    static class c extends p {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static final q.a f491c = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private h<a> f492d = new h<>();
        private boolean e = false;

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

        static c e(r rVar) {
            return (c) new q(rVar, f491c).a(c.class);
        }

        @Override // androidx.lifecycle.p
        protected void c() {
            super.c();
            int iL = this.f492d.l();
            for (int i = 0; i < iL; i++) {
                this.f492d.m(i).i(true);
            }
            this.f492d.b();
        }

        public void d(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.f492d.l() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i = 0; i < this.f492d.l(); i++) {
                    a aVarM = this.f492d.m(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.f492d.i(i));
                    printWriter.print(": ");
                    printWriter.println(aVarM.toString());
                    aVarM.j(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }

        void f() {
            int iL = this.f492d.l();
            for (int i = 0; i < iL; i++) {
                this.f492d.m(i).k();
            }
        }
    }

    b(g gVar, r rVar) {
        this.f489b = gVar;
        this.f490c = c.e(rVar);
    }

    @Override // a.l.a.a
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.f490c.d(str, fileDescriptor, printWriter, strArr);
    }

    @Override // a.l.a.a
    public void c() {
        this.f490c.f();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        a.g.j.a.a(this.f489b, sb);
        sb.append("}}");
        return sb.toString();
    }
}
