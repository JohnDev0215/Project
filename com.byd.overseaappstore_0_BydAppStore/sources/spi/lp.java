package spi;

import androidx.annotation.NonNull;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class lp<T> implements wm<T> {
    public final T a;

    public lp(@NonNull T t) {
        Objects.requireNonNull(t, "Argument must not be null");
        this.a = t;
    }

    @Override // spi.wm
    public final int a() {
        return 1;
    }

    @Override // spi.wm
    @NonNull
    public final Class<T> c() {
        return (Class<T>) this.a.getClass();
    }

    @Override // spi.wm
    @NonNull
    public final T get() {
        return this.a;
    }

    @Override // spi.wm
    public final void recycle() {
    }
}
