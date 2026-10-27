package defpackage;

import androidx.fragment.app.Fragment;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

/* JADX INFO: compiled from: FragmentManager.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class nb {
    public static final lb b = new lb();
    public lb c = null;

    /* JADX INFO: compiled from: FragmentManager.java */
    public static abstract class a {
    }

    /* JADX INFO: compiled from: FragmentManager.java */
    public interface b {
        void a();
    }

    public abstract qb a();

    public abstract void b(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract boolean c();

    public abstract Fragment d(String str);

    public lb e() {
        if (this.c == null) {
            this.c = b;
        }
        return this.c;
    }

    public abstract List<Fragment> f();

    public abstract boolean g();

    public void h(lb lbVar) {
        this.c = lbVar;
    }
}
