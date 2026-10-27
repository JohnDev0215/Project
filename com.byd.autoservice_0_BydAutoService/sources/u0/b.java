package u0;

import android.os.Bundle;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import s.h;
import t0.f;
import t0.k;
import t0.l;
import t0.n;
import t0.o;
import t0.p;

/* JADX INFO: compiled from: LoaderManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends u0.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f5336a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f f5337b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f5338c;

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class a<D> extends k<D> {

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public final int f5339k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final Bundle f5340l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public final v0.a<D> f5341m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public f f5342n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public C0066b<D> f5343o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public v0.a<D> f5344p;

        @Override // androidx.lifecycle.LiveData
        public void e() {
            if (b.f5336a) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            throw null;
        }

        @Override // androidx.lifecycle.LiveData
        public void f() {
            if (b.f5336a) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            throw null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.lifecycle.LiveData
        public void g(l<? super D> lVar) {
            super.g(lVar);
            this.f5342n = null;
        }

        @Override // t0.k, androidx.lifecycle.LiveData
        public void h(D d3) {
            super.h(d3);
            if (this.f5344p != null) {
                throw null;
            }
        }

        public v0.a<D> i(boolean z3) {
            if (b.f5336a) {
                Log.v("LoaderManager", "  Destroying: " + this);
            }
            throw null;
        }

        public void j(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.f5339k);
            printWriter.print(" mArgs=");
            printWriter.println(this.f5340l);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.f5341m);
            String str2 = str + "  ";
            throw null;
        }

        public void k() {
            f fVar = this.f5342n;
            C0066b<D> c0066b = this.f5343o;
            if (fVar == null || c0066b == null) {
                return;
            }
            super.g(c0066b);
            d(fVar, c0066b);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.f5339k);
            sb.append(" : ");
            k0.a.a(this.f5341m, sb);
            sb.append("}}");
            return sb.toString();
        }
    }

    /* JADX INFO: renamed from: u0.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class C0066b<D> implements l<D> {
    }

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class c extends n {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final o.a f5345c = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public h<a> f5346d = new h<>();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f5347e = false;

        /* JADX INFO: compiled from: LoaderManagerImpl.java */
        public static class a implements o.a {
            @Override // t0.o.a
            public <T extends n> T a(Class<T> cls) {
                return new c();
            }
        }

        public static c e(p pVar) {
            return (c) new o(pVar, f5345c).a(c.class);
        }

        @Override // t0.n
        public void c() {
            super.c();
            int iL = this.f5346d.l();
            for (int i3 = 0; i3 < iL; i3++) {
                this.f5346d.m(i3).i(true);
            }
            this.f5346d.b();
        }

        public void d(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.f5346d.l() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i3 = 0; i3 < this.f5346d.l(); i3++) {
                    a aVarM = this.f5346d.m(i3);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.f5346d.i(i3));
                    printWriter.print(": ");
                    printWriter.println(aVarM.toString());
                    aVarM.j(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }

        public void f() {
            int iL = this.f5346d.l();
            for (int i3 = 0; i3 < iL; i3++) {
                this.f5346d.m(i3).k();
            }
        }
    }

    public b(f fVar, p pVar) {
        this.f5337b = fVar;
        this.f5338c = c.e(pVar);
    }

    @Override // u0.a
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.f5338c.d(str, fileDescriptor, printWriter, strArr);
    }

    @Override // u0.a
    public void c() {
        this.f5338c.f();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        k0.a.a(this.f5337b, sb);
        sb.append("}}");
        return sb.toString();
    }
}
