package r0;

import androidx.fragment.app.Fragment;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

/* JADX INFO: compiled from: FragmentManager.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final e f4684b = new e();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public e f4685c = null;

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
        if (this.f4685c == null) {
            this.f4685c = f4684b;
        }
        return this.f4685c;
    }

    public abstract List<Fragment> f();

    public abstract boolean g();

    public void h(e eVar) {
        this.f4685c = eVar;
    }
}
