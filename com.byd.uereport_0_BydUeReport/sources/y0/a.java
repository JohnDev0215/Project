package y0;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import x0.f;
import x0.q;

/* JADX INFO: compiled from: LoaderManager.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static <T extends f & q> a b(T t7) {
        return new b(t7, t7.getViewModelStore());
    }

    @Deprecated
    public abstract void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract void c();
}
