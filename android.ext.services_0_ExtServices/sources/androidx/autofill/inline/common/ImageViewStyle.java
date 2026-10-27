package androidx.autofill.inline.common;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public final class ImageViewStyle extends ViewStyle {
    @Override // androidx.autofill.inline.common.ViewStyle, androidx.autofill.inline.common.BundledStyle
    protected String getStyleKey() {
        return "image_view_style";
    }

    public ImageViewStyle(Bundle bundle) {
        super(bundle);
    }

    public void applyStyleOnImageViewIfValid(ImageView imageView) {
        String string;
        ColorStateList colorStateList;
        if (isValid()) {
            super.applyStyleOnViewIfValid(imageView);
            if (this.mBundle.containsKey("image_max_width")) {
                imageView.setMaxWidth(this.mBundle.getInt("image_max_width"));
                imageView.setAdjustViewBounds(true);
            }
            if (this.mBundle.containsKey("image_max_height")) {
                imageView.setMaxHeight(this.mBundle.getInt("image_max_height"));
                imageView.setAdjustViewBounds(true);
            }
            if (this.mBundle.containsKey("image_tint_list") && (colorStateList = (ColorStateList) this.mBundle.getParcelable("image_tint_list")) != null) {
                imageView.setImageTintList(colorStateList);
            }
            if (!this.mBundle.containsKey("image_scale_type") || (string = this.mBundle.getString("image_scale_type")) == null) {
                return;
            }
            try {
                imageView.setScaleType(ImageView.ScaleType.valueOf(string));
            } catch (IllegalArgumentException unused) {
                Log.w("ImageViewStyle", "Cannot recognize the scale type: " + string);
            }
        }
    }
}
