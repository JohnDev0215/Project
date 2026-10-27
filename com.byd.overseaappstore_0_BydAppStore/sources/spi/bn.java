package spi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public interface bn<T, Z> {
    boolean a(@NonNull T t, @NonNull lk lkVar) throws IOException;

    @Nullable
    wm<Z> b(@NonNull T t, int i, int i2, @NonNull lk lkVar) throws IOException;
}
