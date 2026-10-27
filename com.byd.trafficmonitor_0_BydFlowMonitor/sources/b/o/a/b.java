package b.o.a;

import android.os.Bundle;
import android.util.Log;
import b.e.h;
import b.n.g;
import b.n.l;
import b.n.m;
import b.n.q;
import b.n.r;
import b.n.s;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: LoaderManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends b.o.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f2978a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g f2979b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f2980c;

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class a<D> extends l<D> {
        public final int l;
        public final Bundle m;
        public final b.o.b.a<D> n;
        public g o;
        public C0054b<D> p;
        public b.o.b.a<D> q;

        @Override // androidx.lifecycle.LiveData
        public void g() {
            if (b.f2978a) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            throw null;
        }

        @Override // androidx.lifecycle.LiveData
        public void h() {
            if (b.f2978a) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            throw null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.lifecycle.LiveData
        public void i(m<? super D> mVar) {
            super.i(mVar);
            this.o = null;
        }

        @Override // b.n.l, androidx.lifecycle.LiveData
        public void j(D d2) {
            super.j(d2);
            if (this.q != null) {
                throw null;
            }
        }

        public b.o.b.a<D> k(boolean z) {
            if (b.f2978a) {
                Log.v("LoaderManager", "  Destroying: " + this);
            }
            throw null;
        }

        public void l(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.l);
            printWriter.print(" mArgs=");
            printWriter.println(this.m);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.n);
            String str2 = str + "  ";
            throw null;
        }

        public void m() {
            g gVar = this.o;
            C0054b<D> c0054b = this.p;
            if (gVar == null || c0054b == null) {
                return;
            }
            super.i(c0054b);
            e(gVar, c0054b);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.l);
            sb.append(" : ");
            b.h.l.a.a(this.n, sb);
            sb.append("}}");
            return sb.toString();
        }
    }

    /* JADX INFO: renamed from: b.o.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class C0054b<D> implements m<D> {
    }

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class c extends q {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final r.b f2981c = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public h<a> f2982d = new h<>();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f2983e = false;

        /* JADX INFO: compiled from: LoaderManagerImpl.java */
        public static class a implements r.b {
            @Override // b.n.r.b
            public <T extends q> T a(Class<T> cls) {
                return new c();
            }
        }

        public static c g(s sVar) {
            return (c) new r(sVar, f2981c).a(c.class);
        }

        @Override // b.n.q
        public void d() {
            super.d();
            int iJ = this.f2982d.j();
            for (int i2 = 0; i2 < iJ; i2++) {
                this.f2982d.k(i2).k(true);
            }
            this.f2982d.b();
        }

        public void f(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.f2982d.j() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i2 = 0; i2 < this.f2982d.j(); i2++) {
                    a aVarK = this.f2982d.k(i2);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.f2982d.h(i2));
                    printWriter.print(": ");
                    printWriter.println(aVarK.toString());
                    aVarK.l(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }

        public void h() {
            int iJ = this.f2982d.j();
            for (int i2 = 0; i2 < iJ; i2++) {
                this.f2982d.k(i2).m();
            }
        }
    }

    public b(g gVar, s sVar) {
        this.f2979b = gVar;
        this.f2980c = c.g(sVar);
    }

    @Override // b.o.a.a
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.f2980c.f(str, fileDescriptor, printWriter, strArr);
    }

    @Override // b.o.a.a
    public void c() {
        this.f2980c.h();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        b.h.l.a.a(this.f2979b, sb);
        sb.append("}}");
        return sb.toString();
    }
}
