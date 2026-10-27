package androidx.loader.app;

import androidx.lifecycle.e0;
import androidx.lifecycle.o;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: LoaderManager.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static <T extends o & e0> a b(T t6) {
        return new b(t6, t6.k());
    }

    @Deprecated
    public abstract void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract void c();
}
