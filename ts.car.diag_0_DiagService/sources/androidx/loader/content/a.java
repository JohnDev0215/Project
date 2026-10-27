package androidx.loader.content;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class a<D> {

    /* JADX INFO: renamed from: androidx.loader.content.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    public interface InterfaceC0026a<D> {
    }

    @MainThread
    public abstract void a();

    @MainThread
    public abstract boolean b();

    @NonNull
    public abstract String c(@Nullable D d);

    @Deprecated
    public abstract void d(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr);

    @MainThread
    public abstract void e();

    @MainThread
    public final void f() {
        throw null;
    }

    @MainThread
    public abstract void g();

    @MainThread
    public abstract void h(@NonNull InterfaceC0026a<D> interfaceC0026a);
}
