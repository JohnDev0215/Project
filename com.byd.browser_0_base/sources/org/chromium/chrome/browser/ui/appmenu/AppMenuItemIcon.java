package org.chromium.chrome.browser.ui.appmenu;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import org.chromium.ui.widget.ChromeImageView;

/* JADX INFO: loaded from: classes2.dex */
class AppMenuItemIcon extends ChromeImageView implements Checkable {
    public static final int[] i = {R.attr.state_checked};
    public boolean h;

    public AppMenuItemIcon(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.Checkable
    public final boolean isChecked() {
        return this.h;
    }

    @Override // android.widget.ImageView, android.view.View
    public final int[] onCreateDrawableState(int i2) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        if (this.h) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, i);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.Checkable
    public final void setChecked(boolean z) {
        if (z == this.h) {
            return;
        }
        this.h = z;
        refreshDrawableState();
    }

    @Override // android.widget.Checkable
    public final void toggle() {
        setChecked(!this.h);
    }
}
