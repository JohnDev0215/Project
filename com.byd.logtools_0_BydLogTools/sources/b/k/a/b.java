package b.k.a;

import android.os.Bundle;
import android.util.Log;
import androidx.lifecycle.f;
import androidx.lifecycle.k;
import androidx.lifecycle.l;
import androidx.lifecycle.n;
import androidx.lifecycle.o;
import androidx.lifecycle.p;
import b.d.h;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: LoaderManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
class b extends b.k.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static boolean f2636a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final f f2637b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c f2638c;

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class a<D> extends k<D> {
        private final int k;
        private final Bundle l;
        private final b.k.b.a<D> m;
        private f n;
        private C0047b<D> o;
        private b.k.b.a<D> p;

        @Override // androidx.lifecycle.LiveData
        protected void e() {
            if (b.f2636a) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            throw null;
        }

        @Override // androidx.lifecycle.LiveData
        protected void f() {
            if (b.f2636a) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            throw null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.lifecycle.LiveData
        public void g(l<? super D> lVar) {
            super.g(lVar);
            this.n = null;
        }

        @Override // androidx.lifecycle.k, androidx.lifecycle.LiveData
        public void h(D d2) {
            super.h(d2);
            if (this.p != null) {
                throw null;
            }
        }

        b.k.b.a<D> i(boolean z) {
            if (b.f2636a) {
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
            f fVar = this.n;
            C0047b<D> c0047b = this.o;
            if (fVar == null || c0047b == null) {
                return;
            }
            super.g(c0047b);
            d(fVar, c0047b);
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
    static class C0047b<D> implements l<D> {
    }

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    static class c extends n {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static final o.a f2639c = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private h<a> f2640d = new h<>();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f2641e = false;

        /* JADX INFO: compiled from: LoaderManagerImpl.java */
        static class a implements o.a {
            a() {
            }

            @Override // androidx.lifecycle.o.a
            public <T extends n> T a(Class<T> cls) {
                return new c();
            }
        }

        c() {
        }

        static c e(p pVar) {
            return (c) new o(pVar, f2639c).a(c.class);
        }

        @Override // androidx.lifecycle.n
        protected void c() {
            super.c();
            int iM = this.f2640d.m();
            for (int i = 0; i < iM; i++) {
                this.f2640d.n(i).i(true);
            }
            this.f2640d.b();
        }

        public void d(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.f2640d.m() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i = 0; i < this.f2640d.m(); i++) {
                    a aVarN = this.f2640d.n(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.f2640d.j(i));
                    printWriter.print(": ");
                    printWriter.println(aVarN.toString());
                    aVarN.j(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }

        void f() {
            int iM = this.f2640d.m();
            for (int i = 0; i < iM; i++) {
                this.f2640d.n(i).k();
            }
        }
    }

    b(f fVar, p pVar) {
        this.f2637b = fVar;
        this.f2638c = c.e(pVar);
    }

    @Override // b.k.a.a
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.f2638c.d(str, fileDescriptor, printWriter, strArr);
    }

    @Override // b.k.a.a
    public void c() {
        this.f2638c.f();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        b.g.j.a.a(this.f2637b, sb);
        sb.append("}}");
        return sb.toString();
    }
}
