package spi;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class t7 extends mf<Drawable> {
    public t7(ImageView imageView) {
        super(imageView);
    }

    @Deprecated
    public t7(ImageView imageView, boolean z) {
        super(imageView, z);
    }

    @Override // spi.mf
    public final void k(@Nullable Drawable drawable) {
        ((ImageView) this.b).setImageDrawable(drawable);
    }
}
