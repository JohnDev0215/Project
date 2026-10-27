package androidx.loader.a;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.f;
import androidx.lifecycle.k;
import androidx.lifecycle.l;
import androidx.lifecycle.n;
import androidx.lifecycle.o;
import androidx.lifecycle.p;
import androidx.loader.content.a;
import b.a.h;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
class b extends androidx.loader.a.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static boolean f781c = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NonNull
    private final f f782a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @NonNull
    private final c f783b;

    /* JADX INFO: compiled from: caradapter-bug */
    public static class a<D> extends k<D> implements a.InterfaceC0026a<D> {
        private final int k;

        @Nullable
        private final Bundle l;

        @NonNull
        private final androidx.loader.content.a<D> m;
        private f n;
        private C0025b<D> o;
        private androidx.loader.content.a<D> p;

        @Override // androidx.lifecycle.LiveData
        protected void i() {
            if (b.d(2)) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            this.m.f();
            throw null;
        }

        @Override // androidx.lifecycle.LiveData
        protected void j() {
            if (b.d(2)) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            this.m.g();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.lifecycle.LiveData
        public void k(@NonNull l<? super D> lVar) {
            super.k(lVar);
            this.n = null;
            this.o = null;
        }

        @Override // androidx.lifecycle.k, androidx.lifecycle.LiveData
        public void l(@Nullable D d) {
            super.l(d);
            androidx.loader.content.a<D> aVar = this.p;
            if (aVar != null) {
                aVar.e();
                this.p = null;
            }
        }

        @MainThread
        androidx.loader.content.a<D> m(boolean z) {
            if (b.d(3)) {
                Log.d("LoaderManager", "  Destroying: " + this);
            }
            this.m.b();
            this.m.a();
            C0025b<D> c0025b = this.o;
            if (c0025b != null) {
                k(c0025b);
                if (z) {
                    c0025b.d();
                }
            }
            this.m.h(this);
            if ((c0025b == null || c0025b.c()) && !z) {
                return this.m;
            }
            this.m.e();
            return this.p;
        }

        public void n(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.k);
            printWriter.print(" mArgs=");
            printWriter.println(this.l);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.m);
            this.m.d(str + "  ", fileDescriptor, printWriter, strArr);
            if (this.o != null) {
                printWriter.print(str);
                printWriter.print("mCallbacks=");
                printWriter.println(this.o);
                this.o.b(str + "  ", printWriter);
            }
            printWriter.print(str);
            printWriter.print("mData=");
            printWriter.println(o().c(e()));
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.println(f());
        }

        @NonNull
        androidx.loader.content.a<D> o() {
            return this.m;
        }

        void p() {
            f fVar = this.n;
            C0025b<D> c0025b = this.o;
            if (fVar == null || c0025b == null) {
                return;
            }
            super.k(c0025b);
            g(fVar, c0025b);
        }

        @NonNull
        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.k);
            sb.append(" : ");
            sb.append(this.m.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.m)));
            sb.append("}}");
            return sb.toString();
        }
    }

    /* JADX INFO: renamed from: androidx.loader.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    static class C0025b<D> implements l<D> {
        public abstract void b(@NonNull String str, @NonNull PrintWriter printWriter);

        abstract boolean c();

        @MainThread
        abstract void d();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class c extends n {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static final o.a f784c = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private h<a> f785b = new h<>();

        /* JADX INFO: compiled from: caradapter-bug */
        class a implements o.a {
            a() {
            }

            @Override // androidx.lifecycle.o.a
            @NonNull
            public <T extends n> T a(@NonNull Class<T> cls) {
                return new c();
            }
        }

        c() {
        }

        @NonNull
        static c f(p pVar) {
            return (c) new o(pVar, f784c).a(c.class);
        }

        @Override // androidx.lifecycle.n
        protected void d() {
            super.d();
            int i = this.f785b.i();
            for (int i2 = 0; i2 < i; i2++) {
                this.f785b.j(i2).m(true);
            }
            this.f785b.b();
        }

        public void e(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
            if (this.f785b.i() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i = 0; i < this.f785b.i(); i++) {
                    a aVarJ = this.f785b.j(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.f785b.g(i));
                    printWriter.print(": ");
                    printWriter.println(aVarJ.toString());
                    aVarJ.n(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }

        void g() {
            int i = this.f785b.i();
            for (int i2 = 0; i2 < i; i2++) {
                this.f785b.j(i2).p();
            }
        }
    }

    b(@NonNull f fVar, @NonNull p pVar) {
        this.f782a = fVar;
        this.f783b = c.f(pVar);
    }

    static boolean d(int i) {
        return f781c || Log.isLoggable("LoaderManager", i);
    }

    @Override // androidx.loader.a.a
    @Deprecated
    public void a(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
        this.f783b.e(str, fileDescriptor, printWriter, strArr);
    }

    @Override // androidx.loader.a.a
    public void c() {
        this.f783b.g();
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        sb.append(this.f782a.getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this.f782a)));
        sb.append("}}");
        return sb.toString();
    }
}
