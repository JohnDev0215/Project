package spi;

import android.content.Context;
import androidx.annotation.NonNull;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class du<T> implements qt<T> {
    public static final qt<?> b = new du();

    private du() {
    }

    @Override // spi.ig
    public final void a(@NonNull MessageDigest messageDigest) {
    }

    @Override // spi.qt
    @NonNull
    public final wm<T> b(@NonNull Context context, @NonNull wm<T> wmVar, int i, int i2) {
        return wmVar;
    }
}
