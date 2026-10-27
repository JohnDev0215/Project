package b.j.a;

import androidx.fragment.app.Fragment;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

/* JADX INFO: compiled from: FragmentManager.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final d f1870c = new d();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public d f1871d = null;

    /* JADX INFO: compiled from: FragmentManager.java */
    public static abstract class a {
    }

    /* JADX INFO: compiled from: FragmentManager.java */
    public interface b {
        void a();
    }

    public abstract i a();

    public abstract void b(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract boolean c();

    public abstract Fragment d(String str);

    public d e() {
        if (this.f1871d == null) {
            this.f1871d = f1870c;
        }
        return this.f1871d;
    }

    public abstract List<Fragment> f();

    public abstract boolean g();

    public void h(d dVar) {
        this.f1871d = dVar;
    }
}
