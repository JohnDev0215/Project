package a.o.a;

import a.n.e0;
import a.n.k;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static <T extends k & e0> a b(T t) {
        return new b(t, t.e());
    }

    @Deprecated
    public abstract void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);
}
