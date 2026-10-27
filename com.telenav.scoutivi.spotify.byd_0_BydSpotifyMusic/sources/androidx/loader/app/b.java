package androidx.loader.app;

import android.os.Bundle;
import android.util.Log;
import androidx.lifecycle.a0;
import androidx.lifecycle.b0;
import androidx.lifecycle.d0;
import androidx.lifecycle.o;
import androidx.lifecycle.t;
import androidx.lifecycle.u;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import n.h;

/* JADX INFO: compiled from: LoaderManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
class b extends androidx.loader.app.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static boolean f2589c = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final o f2590a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final C0023b f2591b;

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class a<D> extends t<D> {

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private final int f2592l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private final Bundle f2593m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        private o f2594n;

        @Override // androidx.lifecycle.LiveData
        protected void h() {
            if (b.f2589c) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            throw null;
        }

        @Override // androidx.lifecycle.LiveData
        protected void i() {
            if (b.f2589c) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            throw null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.lifecycle.LiveData
        public void k(u<? super D> uVar) {
            super.k(uVar);
            this.f2594n = null;
        }

        @Override // androidx.lifecycle.t, androidx.lifecycle.LiveData
        public void m(D d7) {
            super.m(d7);
        }

        u0.a<D> n(boolean z6) {
            if (b.f2589c) {
                Log.v("LoaderManager", "  Destroying: " + this);
            }
            throw null;
        }

        public void o(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.f2592l);
            printWriter.print(" mArgs=");
            printWriter.println(this.f2593m);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println((Object) null);
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("  ");
            throw null;
        }

        void p() {
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.f2592l);
            sb.append(" : ");
            h0.b.a(null, sb);
            sb.append("}}");
            return sb.toString();
        }
    }

    /* JADX INFO: renamed from: androidx.loader.app.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    static class C0023b extends a0 {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static final b0.b f2595c = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private h<a> f2596a = new h<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f2597b = false;

        /* JADX INFO: renamed from: androidx.loader.app.b$b$a */
        /* JADX INFO: compiled from: LoaderManagerImpl.java */
        static class a implements b0.b {
            a() {
            }

            @Override // androidx.lifecycle.b0.b
            public <T extends a0> T a(Class<T> cls) {
                return new C0023b();
            }
        }

        C0023b() {
        }

        static C0023b i(d0 d0Var) {
            return (C0023b) new b0(d0Var, f2595c).a(C0023b.class);
        }

        public void h(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.f2596a.k() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i7 = 0; i7 < this.f2596a.k(); i7++) {
                    a aVarL = this.f2596a.l(i7);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.f2596a.h(i7));
                    printWriter.print(": ");
                    printWriter.println(aVarL.toString());
                    aVarL.o(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }

        void j() {
            int iK = this.f2596a.k();
            for (int i7 = 0; i7 < iK; i7++) {
                this.f2596a.l(i7).p();
            }
        }

        @Override // androidx.lifecycle.a0
        protected void onCleared() {
            super.onCleared();
            int iK = this.f2596a.k();
            for (int i7 = 0; i7 < iK; i7++) {
                this.f2596a.l(i7).n(true);
            }
            this.f2596a.b();
        }
    }

    b(o oVar, d0 d0Var) {
        this.f2590a = oVar;
        this.f2591b = C0023b.i(d0Var);
    }

    @Override // androidx.loader.app.a
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.f2591b.h(str, fileDescriptor, printWriter, strArr);
    }

    @Override // androidx.loader.app.a
    public void c() {
        this.f2591b.j();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        h0.b.a(this.f2590a, sb);
        sb.append("}}");
        return sb.toString();
    }
}
