package a.o.a;

import a.b.k.l;
import a.e.i;
import a.n.a0;
import a.n.c0;
import a.n.d0;
import a.n.k;
import a.n.p;
import a.n.q;
import a.n.x;
import a.n.z;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class b extends a.o.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k f1125a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f1126b;

    /* JADX WARN: Unexpected interfaces in signature: [java.lang.Object<D>] */
    public static class a<D> extends p<D> {
        public k k;
        public C0024b<D> l;

        @Override // androidx.lifecycle.LiveData
        public void e() {
            throw null;
        }

        @Override // androidx.lifecycle.LiveData
        public void f() {
            throw null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.lifecycle.LiveData
        public void g(q<? super D> qVar) {
            super.g(qVar);
            this.k = null;
            this.l = null;
        }

        @Override // a.n.p, androidx.lifecycle.LiveData
        public void h(D d2) {
            super.h(d2);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(0);
            sb.append(" : ");
            l.i.e(null, sb);
            sb.append("}}");
            return sb.toString();
        }
    }

    /* JADX INFO: renamed from: a.o.a.b$b, reason: collision with other inner class name */
    public static class C0024b<D> implements q<D> {
    }

    public static class c extends x {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final z f1127d = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public i<a> f1128c = new i<>(10);

        public static class a implements z {
            @Override // a.n.z
            public <T extends x> T a(Class<T> cls) {
                return new c();
            }
        }

        @Override // a.n.x
        public void d() {
            int i = this.f1128c.i();
            i<a> iVar = this.f1128c;
            if (i > 0) {
                iVar.j(0);
                throw null;
            }
            int i2 = iVar.f495d;
            Object[] objArr = iVar.f494c;
            for (int i3 = 0; i3 < i2; i3++) {
                objArr[i3] = null;
            }
            iVar.f495d = 0;
            iVar.f492a = false;
        }
    }

    public b(k kVar, d0 d0Var) {
        this.f1125a = kVar;
        Object obj = c.f1127d;
        String canonicalName = c.class.getCanonicalName();
        if (canonicalName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        String strC = b.a.a.a.a.c("androidx.lifecycle.ViewModelProvider.DefaultKey:", canonicalName);
        x xVarC = d0Var.f1085a.get(strC);
        if (!c.class.isInstance(xVarC)) {
            xVarC = obj instanceof a0 ? ((a0) obj).c(strC, c.class) : ((c.a) obj).a(c.class);
            x xVarPut = d0Var.f1085a.put(strC, xVarC);
            if (xVarPut != null) {
                xVarPut.d();
            }
        } else if (obj instanceof c0) {
            ((c0) obj).b(xVarC);
        }
        this.f1126b = (c) xVarC;
    }

    @Override // a.o.a.a
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        c cVar = this.f1126b;
        if (cVar.f1128c.i() > 0) {
            printWriter.print(str);
            printWriter.println("Loaders:");
            String str2 = str + "    ";
            if (cVar.f1128c.i() <= 0) {
                return;
            }
            a aVarJ = cVar.f1128c.j(0);
            printWriter.print(str);
            printWriter.print("  #");
            printWriter.print(cVar.f1128c.f(0));
            printWriter.print(": ");
            printWriter.println(aVarJ.toString());
            printWriter.print(str2);
            printWriter.print("mId=");
            printWriter.print(0);
            printWriter.print(" mArgs=");
            printWriter.println((Object) null);
            printWriter.print(str2);
            printWriter.print("mLoader=");
            printWriter.println((Object) null);
            throw null;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        l.i.e(this.f1125a, sb);
        sb.append("}}");
        return sb.toString();
    }
}
