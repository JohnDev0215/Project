package h2;

import android.graphics.Bitmap;
import android.widget.ImageView;

/* JADX INFO: compiled from: BitmapImageViewTarget.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends e<Bitmap> {
    public b(ImageView imageView) {
        super(imageView);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // h2.e
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public void r(Bitmap bitmap) {
        ((ImageView) this.f6600e).setImageBitmap(bitmap);
    }
}
