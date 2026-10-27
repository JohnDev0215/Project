package spi;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class u7<T extends Drawable> implements wm<T>, pf {
    public final T a;

    public u7(T t) {
        Objects.requireNonNull(t, "Argument must not be null");
        this.a = t;
    }

    @Override // spi.wm
    @NonNull
    public final Object get() {
        Drawable.ConstantState constantState = this.a.getConstantState();
        return constantState == null ? this.a : constantState.newDrawable();
    }

    public void initialize() {
        Bitmap bitmapB;
        T t = this.a;
        if (t instanceof BitmapDrawable) {
            bitmapB = ((BitmapDrawable) t).getBitmap();
        } else if (!(t instanceof GifDrawable)) {
            return;
        } else {
            bitmapB = ((GifDrawable) t).b();
        }
        bitmapB.prepareToDraw();
    }
}
