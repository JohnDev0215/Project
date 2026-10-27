package spi;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class s1 implements in<Bitmap, BitmapDrawable> {
    public final Resources a;

    public s1(@NonNull Context context) {
        this(context.getResources());
    }

    public s1(@NonNull Resources resources) {
        Objects.requireNonNull(resources, "Argument must not be null");
        this.a = resources;
    }

    @Deprecated
    public s1(@NonNull Resources resources, w1 w1Var) {
        this(resources);
    }

    @Override // spi.in
    @Nullable
    public final wm<BitmapDrawable> a(@NonNull wm<Bitmap> wmVar, @NonNull lk lkVar) {
        return lg.b(this.a, wmVar);
    }
}
