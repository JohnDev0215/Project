package defpackage;

import android.os.Bundle;
import android.util.Log;
import defpackage.nc;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: LoaderManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class rc extends qc {
    public static boolean a = false;
    public final ec b;
    public final c c;

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class a<D> extends jc<D> {
        public final int k;
        public final Bundle l;
        public final sc<D> m;
        public ec n;
        public b<D> o;
        public sc<D> p;

        @Override // androidx.lifecycle.LiveData
        public void e() {
            if (rc.a) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            throw null;
        }

        @Override // androidx.lifecycle.LiveData
        public void f() {
            if (rc.a) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            throw null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.lifecycle.LiveData
        public void g(kc<? super D> kcVar) {
            super.g(kcVar);
            this.n = null;
        }

        @Override // defpackage.jc, androidx.lifecycle.LiveData
        public void h(D d) {
            super.h(d);
            if (this.p != null) {
                throw null;
            }
        }

        public sc<D> i(boolean z) {
            if (rc.a) {
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
            ec ecVar = this.n;
            b<D> bVar = this.o;
            if (ecVar == null || bVar == null) {
                return;
            }
            super.g(bVar);
            d(ecVar, bVar);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.k);
            sb.append(" : ");
            s8.a(this.m, sb);
            sb.append("}}");
            return sb.toString();
        }
    }

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class b<D> implements kc<D> {
    }

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class c extends mc {
        public static final nc.a c = new a();
        public h4<a> d = new h4<>();
        public boolean e = false;

        /* JADX INFO: compiled from: LoaderManagerImpl.java */
        public static class a implements nc.a {
            @Override // nc.a
            public <T extends mc> T a(Class<T> cls) {
                return new c();
            }
        }

        public static c e(oc ocVar) {
            return (c) new nc(ocVar, c).a(c.class);
        }

        @Override // defpackage.mc
        public void c() {
            super.c();
            int iL = this.d.l();
            for (int i = 0; i < iL; i++) {
                this.d.m(i).i(true);
            }
            this.d.b();
        }

        public void d(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.d.l() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i = 0; i < this.d.l(); i++) {
                    a aVarM = this.d.m(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.d.i(i));
                    printWriter.print(": ");
                    printWriter.println(aVarM.toString());
                    aVarM.j(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }

        public void f() {
            int iL = this.d.l();
            for (int i = 0; i < iL; i++) {
                this.d.m(i).k();
            }
        }
    }

    public rc(ec ecVar, oc ocVar) {
        this.b = ecVar;
        this.c = c.e(ocVar);
    }

    @Override // defpackage.qc
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.c.d(str, fileDescriptor, printWriter, strArr);
    }

    @Override // defpackage.qc
    public void c() {
        this.c.f();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        s8.a(this.b, sb);
        sb.append("}}");
        return sb.toString();
    }
}
