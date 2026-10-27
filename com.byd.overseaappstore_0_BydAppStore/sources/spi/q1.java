package spi;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import androidx.annotation.NonNull;
import java.io.IOException;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class q1<DataType> implements bn<DataType, BitmapDrawable> {
    public final bn<DataType, Bitmap> a;
    public final Resources b;

    public q1(Context context, bn<DataType, Bitmap> bnVar) {
        this(context.getResources(), bnVar);
    }

    public q1(@NonNull Resources resources, @NonNull bn<DataType, Bitmap> bnVar) {
        Objects.requireNonNull(resources, "Argument must not be null");
        this.b = resources;
        Objects.requireNonNull(bnVar, "Argument must not be null");
        this.a = bnVar;
    }

    @Deprecated
    public q1(Resources resources, w1 w1Var, bn<DataType, Bitmap> bnVar) {
        this(resources, bnVar);
    }

    @Override // spi.bn
    public final boolean a(@NonNull DataType datatype, @NonNull lk lkVar) throws IOException {
        return this.a.a(datatype, lkVar);
    }

    @Override // spi.bn
    public final wm<BitmapDrawable> b(@NonNull DataType datatype, int i, int i2, @NonNull lk lkVar) throws IOException {
        return lg.b(this.b, this.a.b(datatype, i, i2, lkVar));
    }
}
