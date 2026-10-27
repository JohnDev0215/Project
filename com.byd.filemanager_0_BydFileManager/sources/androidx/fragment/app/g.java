package androidx.fragment.app;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

/* JADX INFO: compiled from: FragmentManager.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final e f1128b = new e();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private e f1129c = null;

    /* JADX INFO: compiled from: FragmentManager.java */
    public static abstract class a {
    }

    /* JADX INFO: compiled from: FragmentManager.java */
    public interface b {
        void a();
    }

    public abstract j a();

    public abstract void b(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract boolean c();

    public abstract Fragment d(String str);

    public e e() {
        if (this.f1129c == null) {
            this.f1129c = f1128b;
        }
        return this.f1129c;
    }

    public abstract List<Fragment> f();

    public abstract void g(int i, int i2);

    public abstract boolean h();

    public void i(e eVar) {
        this.f1129c = eVar;
    }
}
