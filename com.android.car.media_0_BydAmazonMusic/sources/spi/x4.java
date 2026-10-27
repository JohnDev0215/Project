package spi;

import android.car.utils.Logs;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Size;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.android.car.ui.R$color;
import spi.v4;
import spi.v4.a;

/* JADX INFO: compiled from: ImageViewBinder.java */
/* JADX INFO: loaded from: classes.dex */
public final class x4<T extends v4.a> extends v4<T> {

    @Nullable
    public final ImageView h;
    public final boolean i;
    public T j;
    public boolean k;

    public x4(Size size, @Nullable ImageView imageView) {
        this(v4.b.FOREGROUND, size, imageView, false);
    }

    @Override // spi.v4
    public final void b(Context context) {
        ImageView imageView = this.h;
        if (imageView != null) {
            imageView.setImageBitmap(null);
            this.h.setImageDrawable(null);
            this.h.clearColorFilter();
        } else {
            Logs.e("ImageViewBinder", "prepareForNewBinding mImageView == null !!!");
        }
        super.b(context);
    }

    @Override // spi.v4
    public final void c(@Nullable Drawable drawable) {
        ImageView imageView = this.h;
        if (imageView != null) {
            imageView.setImageDrawable(drawable);
            if (this.i && h2.a(this.h.getContext()).b()) {
                if (!(drawable instanceof BitmapDrawable)) {
                    this.h.clearColorFilter();
                } else {
                    this.h.setColorFilter(this.h.getContext().getColor(R$color.improper_image_refs_tint_color));
                }
            }
        }
    }

    @Override // spi.v4
    public final void d(Context context, @Nullable T t) {
        this.j = t;
        this.k = false;
        if (this.h != null) {
            super.d(context, t);
        }
    }

    public final void e(Context context) {
        this.k = true;
        if (this.h != null) {
            super.d(context, null);
        }
    }

    public x4(v4.b bVar, Size size, @Nullable ImageView imageView, boolean z) {
        super(bVar, size);
        this.h = imageView;
        this.i = z;
    }
}
