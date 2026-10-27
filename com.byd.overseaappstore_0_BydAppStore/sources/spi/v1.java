package spi;

import android.graphics.Bitmap;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public final class v1 extends mf<Bitmap> {
    public v1(ImageView imageView) {
        super(imageView);
    }

    @Deprecated
    public v1(ImageView imageView, boolean z) {
        super(imageView, z);
    }

    @Override // spi.mf
    public final void k(Bitmap bitmap) {
        ((ImageView) this.b).setImageBitmap(bitmap);
    }
}
