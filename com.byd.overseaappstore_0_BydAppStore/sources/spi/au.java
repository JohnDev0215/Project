package spi;

import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class au implements bn<Drawable, Drawable> {
    @Override // spi.bn
    public final /* bridge */ /* synthetic */ boolean a(@NonNull Drawable drawable, @NonNull lk lkVar) throws IOException {
        return true;
    }

    @Override // spi.bn
    @Nullable
    public final wm<Drawable> b(@NonNull Drawable drawable, int i, int i2, @NonNull lk lkVar) throws IOException {
        return tj.b(drawable);
    }
}
