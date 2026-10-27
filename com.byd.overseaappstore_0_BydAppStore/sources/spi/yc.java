package spi;

import android.content.Context;
import androidx.annotation.NonNull;
import spi.rm;

/* JADX INFO: loaded from: classes.dex */
public final class yc implements rm.b {
    @Override // spi.rm.b
    @NonNull
    public final qm a(@NonNull com.bumptech.glide.a aVar, @NonNull sg sgVar, @NonNull sm smVar, @NonNull Context context) {
        return new pd(aVar, sgVar, smVar, context);
    }
}
