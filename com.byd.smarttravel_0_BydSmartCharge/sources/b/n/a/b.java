package b.n.a;

import android.os.Bundle;
import android.util.Log;
import b.e.h;
import b.m.f;
import b.m.k;
import b.m.l;
import b.m.n;
import b.m.o;
import b.m.p;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: LoaderManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends b.n.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f1325a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f f1326b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f1327c;

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class a<D> extends k<D> {
        public final int k;
        public final Bundle l;
        public final b.n.b.a<D> m;
        public f n;
        public C0033b<D> o;
        public b.n.b.a<D> p;

        @Override // androidx.lifecycle.LiveData
        public void e() {
            if (b.f1325a) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            throw null;
        }

        @Override // androidx.lifecycle.LiveData
        public void f() {
            if (b.f1325a) {
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

        @Override // b.m.k, androidx.lifecycle.LiveData
        public void h(D d2) {
            super.h(d2);
            if (this.p != null) {
                throw null;
            }
        }

        public b.n.b.a<D> i(boolean z) {
            if (b.f1325a) {
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
            C0033b<D> c0033b = this.o;
            if (fVar == null || c0033b == null) {
                return;
            }
            super.g(c0033b);
            d(fVar, c0033b);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.k);
            sb.append(" : ");
            b.g.i.a.a(this.m, sb);
            sb.append("}}");
            return sb.toString();
        }
    }

    /* JADX INFO: renamed from: b.n.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class C0033b<D> implements l<D> {
    }

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class c extends n {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final o.a f1328c = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public h<a> f1329d = new h<>();
        public boolean e = false;

        /* JADX INFO: compiled from: LoaderManagerImpl.java */
        public static class a implements o.a {
            @Override // b.m.o.a
            public <T extends n> T a(Class<T> cls) {
                return new c();
            }
        }

        public static c e(p pVar) {
            return (c) new o(pVar, f1328c).a(c.class);
        }

        @Override // b.m.n
        public void c() {
            super.c();
            int iL = this.f1329d.l();
            for (int i = 0; i < iL; i++) {
                this.f1329d.m(i).i(true);
            }
            this.f1329d.b();
        }

        public void d(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.f1329d.l() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i = 0; i < this.f1329d.l(); i++) {
                    a aVarM = this.f1329d.m(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.f1329d.i(i));
                    printWriter.print(": ");
                    printWriter.println(aVarM.toString());
                    aVarM.j(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }

        public void f() {
            int iL = this.f1329d.l();
            for (int i = 0; i < iL; i++) {
                this.f1329d.m(i).k();
            }
        }
    }

    public b(f fVar, p pVar) {
        this.f1326b = fVar;
        this.f1327c = c.e(pVar);
    }

    @Override // b.n.a.a
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.f1327c.d(str, fileDescriptor, printWriter, strArr);
    }

    @Override // b.n.a.a
    public void c() {
        this.f1327c.f();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        b.g.i.a.a(this.f1326b, sb);
        sb.append("}}");
        return sb.toString();
    }
}
