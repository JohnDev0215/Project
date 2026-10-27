package androidx.loader.a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.f;
import androidx.lifecycle.q;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public abstract class a {
    @NonNull
    public static <T extends f & q> a b(@NonNull T t) {
        return new b(t, t.h());
    }

    @Deprecated
    public abstract void a(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr);

    public abstract void c();
}
