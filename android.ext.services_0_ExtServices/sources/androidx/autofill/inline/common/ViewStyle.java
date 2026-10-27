package androidx.autofill.inline.common;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class ViewStyle extends BundledStyle {
    @Override // androidx.autofill.inline.common.BundledStyle
    protected String getStyleKey() {
        return "view_style";
    }

    public ViewStyle(Bundle bundle) {
        super(bundle);
    }

    public void applyStyleOnViewIfValid(View view) {
        int[] intArray;
        int[] intArray2;
        Icon icon;
        Drawable drawableLoadDrawable;
        if (isValid()) {
            if (this.mBundle.containsKey("background") && (icon = (Icon) this.mBundle.getParcelable("background")) != null && (drawableLoadDrawable = icon.loadDrawable(view.getContext())) != null) {
                view.setBackground(drawableLoadDrawable);
            }
            if (this.mBundle.containsKey("background_color")) {
                view.setBackgroundColor(this.mBundle.getInt("background_color"));
            }
            if (this.mBundle.containsKey("padding") && (intArray2 = this.mBundle.getIntArray("padding")) != null && intArray2.length == 4) {
                if (view.getLayoutDirection() == 0) {
                    view.setPadding(intArray2[0], intArray2[1], intArray2[2], intArray2[3]);
                } else {
                    view.setPadding(intArray2[2], intArray2[1], intArray2[0], intArray2[3]);
                }
            }
            if (this.mBundle.containsKey("layout_margin") && (intArray = this.mBundle.getIntArray("layout_margin")) != null && intArray.length == 4) {
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                if (layoutParams == null) {
                    layoutParams = new ViewGroup.MarginLayoutParams(-1, -1);
                } else if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                    layoutParams = new ViewGroup.MarginLayoutParams(layoutParams);
                }
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                if (view.getLayoutDirection() == 0) {
                    marginLayoutParams.setMargins(intArray[0], intArray[1], intArray[2], intArray[3]);
                } else {
                    marginLayoutParams.setMargins(intArray[2], intArray[1], intArray[0], intArray[3]);
                }
                view.setLayoutParams(marginLayoutParams);
            }
        }
    }
}
