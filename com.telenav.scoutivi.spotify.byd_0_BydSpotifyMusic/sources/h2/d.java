package h2;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/* JADX INFO: compiled from: DrawableImageViewTarget.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends e<Drawable> {
    public d(ImageView imageView) {
        super(imageView);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // h2.e
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public void r(Drawable drawable) {
        ((ImageView) this.f6600e).setImageDrawable(drawable);
    }
}
