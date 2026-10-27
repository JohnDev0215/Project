package y0;

import android.os.Bundle;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import v.h;
import x0.f;
import x0.k;
import x0.l;
import x0.n;
import x0.o;
import x0.p;

/* JADX INFO: compiled from: LoaderManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends y0.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f9164a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f f9165b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f9166c;

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class a<D> extends k<D> {

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final int f9167l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public final Bundle f9168m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public final z0.a<D> f9169n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public f f9170o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public C0145b<D> f9171p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public z0.a<D> f9172q;

        @Override // androidx.lifecycle.LiveData
        public void f() {
            if (b.f9164a) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            throw null;
        }

        @Override // androidx.lifecycle.LiveData
        public void g() {
            if (b.f9164a) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            throw null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.lifecycle.LiveData
        public void h(l<? super D> lVar) {
            super.h(lVar);
            this.f9170o = null;
        }

        @Override // x0.k, androidx.lifecycle.LiveData
        public void i(D d8) {
            super.i(d8);
            if (this.f9172q != null) {
                throw null;
            }
        }

        public z0.a<D> j(boolean z7) {
            if (b.f9164a) {
                Log.v("LoaderManager", "  Destroying: " + this);
            }
            throw null;
        }

        public void k(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.f9167l);
            printWriter.print(" mArgs=");
            printWriter.println(this.f9168m);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.f9169n);
            String str2 = str + "  ";
            throw null;
        }

        public void l() {
            f fVar = this.f9170o;
            C0145b<D> c0145b = this.f9171p;
            if (fVar == null || c0145b == null) {
                return;
            }
            super.h(c0145b);
            e(fVar, c0145b);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.f9167l);
            sb.append(" : ");
            n0.a.a(this.f9169n, sb);
            sb.append("}}");
            return sb.toString();
        }
    }

    /* JADX INFO: renamed from: y0.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class C0145b<D> implements l<D> {
    }

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class c extends n {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final o.a f9173c = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public h<a> f9174d = new h<>();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f9175e = false;

        /* JADX INFO: compiled from: LoaderManagerImpl.java */
        public static class a implements o.a {
            @Override // x0.o.a
            public <T extends n> T a(Class<T> cls) {
                return new c();
            }
        }

        public static c e(p pVar) {
            return (c) new o(pVar, f9173c).a(c.class);
        }

        @Override // x0.n
        public void c() {
            super.c();
            int iL = this.f9174d.l();
            for (int i8 = 0; i8 < iL; i8++) {
                this.f9174d.m(i8).j(true);
            }
            this.f9174d.b();
        }

        public void d(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.f9174d.l() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i8 = 0; i8 < this.f9174d.l(); i8++) {
                    a aVarM = this.f9174d.m(i8);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.f9174d.i(i8));
                    printWriter.print(": ");
                    printWriter.println(aVarM.toString());
                    aVarM.k(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }

        public void f() {
            int iL = this.f9174d.l();
            for (int i8 = 0; i8 < iL; i8++) {
                this.f9174d.m(i8).l();
            }
        }
    }

    public b(f fVar, p pVar) {
        this.f9165b = fVar;
        this.f9166c = c.e(pVar);
    }

    @Override // y0.a
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.f9166c.d(str, fileDescriptor, printWriter, strArr);
    }

    @Override // y0.a
    public void c() {
        this.f9166c.f();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        n0.a.a(this.f9165b, sb);
        sb.append("}}");
        return sb.toString();
    }
}
