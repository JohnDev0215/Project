package org.chromium.ui.widget;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageButton;

/* JADX INFO: loaded from: classes3.dex */
public class ChromeImageButton extends AppCompatImageButton {
    public ChromeImageButton(Context context) {
        super(context);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        if (getImageTintList() == null || getImageTintMode() != PorterDuff.Mode.SRC_ATOP) {
            return;
        }
        setImageTintMode(PorterDuff.Mode.SRC_IN);
    }

    public ChromeImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ChromeImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
