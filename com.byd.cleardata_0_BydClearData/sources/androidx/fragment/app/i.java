package androidx.fragment.app;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final g f1290b = new g();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private g f1291c = null;

    public static abstract class a {
    }

    public interface b {
        void a();
    }

    public abstract n a();

    public abstract void b(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract Fragment c(String str);

    public g d() {
        if (this.f1291c == null) {
            this.f1291c = f1290b;
        }
        return this.f1291c;
    }

    public abstract List<Fragment> e();

    public abstract void f(int i, int i2);

    public abstract boolean g();

    public void h(g gVar) {
        this.f1291c = gVar;
    }
}
