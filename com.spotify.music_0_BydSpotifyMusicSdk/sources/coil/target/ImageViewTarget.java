package coil.target;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public class ImageViewTarget extends GenericViewTarget<ImageView> {
    public final ImageView e;

    public ImageViewTarget(ImageView imageView) {
        this.e = imageView;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ImageViewTarget) {
            if (v03.b(this.e, ((ImageViewTarget) obj).e)) {
                return true;
            }
        }
        return false;
    }

    @Override // coil.target.GenericViewTarget
    public final Drawable h() {
        return this.e.getDrawable();
    }

    public final int hashCode() {
        return this.e.hashCode();
    }

    @Override // coil.target.GenericViewTarget
    public final ImageView i() {
        return this.e;
    }

    @Override // coil.target.GenericViewTarget
    public final void j(Drawable drawable) {
        this.e.setImageDrawable(drawable);
    }
}
