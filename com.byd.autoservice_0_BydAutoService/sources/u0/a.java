package u0;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import t0.f;
import t0.q;

/* JADX INFO: compiled from: LoaderManager.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static <T extends f & q> a b(T t3) {
        return new b(t3, t3.getViewModelStore());
    }

    @Deprecated
    public abstract void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract void c();
}
