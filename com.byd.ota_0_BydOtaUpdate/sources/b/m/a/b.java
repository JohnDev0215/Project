package b.m.a;

import android.os.Bundle;
import android.util.Log;
import b.d.h;
import b.l.f;
import b.l.k;
import b.l.l;
import b.l.n;
import b.l.o;
import b.l.p;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: LoaderManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends b.m.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f1989a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f f1990b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f1991c;

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class a<D> extends k<D> {
        public final int k;
        public final Bundle l;
        public final b.m.b.a<D> m;
        public f n;
        public C0037b<D> o;
        public b.m.b.a<D> p;

        @Override // androidx.lifecycle.LiveData
        public void e() {
            if (b.f1989a) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            throw null;
        }

        @Override // androidx.lifecycle.LiveData
        public void f() {
            if (b.f1989a) {
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

        @Override // b.l.k, androidx.lifecycle.LiveData
        public void h(D d2) {
            super.h(d2);
            if (this.p != null) {
                throw null;
            }
        }

        public b.m.b.a<D> i(boolean z) {
            if (b.f1989a) {
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

        public void k() {
            f fVar = this.n;
            C0037b<D> c0037b = this.o;
            if (fVar == null || c0037b == null) {
                return;
            }
            super.g(c0037b);
            d(fVar, c0037b);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.k);
            sb.append(" : ");
            b.f.l.a.a(this.m, sb);
            sb.append("}}");
            return sb.toString();
        }
    }

    /* JADX INFO: renamed from: b.m.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class C0037b<D> implements l<D> {
    }

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class c extends n {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final o.a f1992c = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public h<a> f1993d = new h<>();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1994e = false;

        /* JADX INFO: compiled from: LoaderManagerImpl.java */
        public static class a implements o.a {
            @Override // b.l.o.a
            public <T extends n> T a(Class<T> cls) {
                return new c();
            }
        }

        public static c e(p pVar) {
            return (c) new o(pVar, f1992c).a(c.class);
        }

        @Override // b.l.n
        public void c() {
            super.c();
            int iL = this.f1993d.l();
            for (int i2 = 0; i2 < iL; i2++) {
                this.f1993d.m(i2).i(true);
            }
            this.f1993d.b();
        }

        public void d(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.f1993d.l() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i2 = 0; i2 < this.f1993d.l(); i2++) {
                    a aVarM = this.f1993d.m(i2);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.f1993d.i(i2));
                    printWriter.print(": ");
                    printWriter.println(aVarM.toString());
                    aVarM.j(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }

        public void f() {
            int iL = this.f1993d.l();
            for (int i2 = 0; i2 < iL; i2++) {
                this.f1993d.m(i2).k();
            }
        }
    }

    public b(f fVar, p pVar) {
        this.f1990b = fVar;
        this.f1991c = c.e(pVar);
    }

    @Override // b.m.a.a
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.f1991c.d(str, fileDescriptor, printWriter, strArr);
    }

    @Override // b.m.a.a
    public void c() {
        this.f1991c.f();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        b.f.l.a.a(this.f1990b, sb);
        sb.append("}}");
        return sb.toString();
    }
}
