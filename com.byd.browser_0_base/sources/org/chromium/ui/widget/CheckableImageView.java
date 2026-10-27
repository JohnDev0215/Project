package org.chromium.ui.widget;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;

/* JADX INFO: loaded from: classes3.dex */
public class CheckableImageView extends ChromeImageView implements Checkable {
    public static final int[] i = {R.attr.state_checked};
    public boolean h;

    public CheckableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.Checkable
    public final boolean isChecked() {
        return this.h;
    }

    @Override // android.widget.ImageView, android.view.View
    public final int[] onCreateDrawableState(int i2) {
        return !isChecked() ? super.onCreateDrawableState(i2) : View.mergeDrawableStates(super.onCreateDrawableState(i2 + 1), i);
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.h == z) {
            return;
        }
        this.h = z;
        refreshDrawableState();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (drawable == getDrawable()) {
            return;
        }
        super.setImageDrawable(drawable);
        refreshDrawableState();
    }

    @Override // android.widget.Checkable
    public final void toggle() {
        setChecked(!this.h);
    }
}
