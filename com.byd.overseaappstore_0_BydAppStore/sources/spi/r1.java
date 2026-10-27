package spi;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.EncodeStrategy;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class r1 implements en<BitmapDrawable> {
    public final w1 a;
    public final en<Bitmap> b;

    public r1(w1 w1Var, en<Bitmap> enVar) {
        this.a = w1Var;
        this.b = enVar;
    }

    @Override // spi.en
    @NonNull
    public final EncodeStrategy a(@NonNull lk lkVar) {
        return this.b.a(lkVar);
    }

    @Override // spi.f8
    public final boolean b(@NonNull Object obj, @NonNull File file, @NonNull lk lkVar) {
        return this.b.b((Bitmap) new y1(((BitmapDrawable) ((wm) obj).get()).getBitmap(), this.a), file, lkVar);
    }
}
