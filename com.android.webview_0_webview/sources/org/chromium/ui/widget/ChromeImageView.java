package org.chromium.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import defpackage.C0915i0;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ChromeImageView extends C0915i0 {
    public ChromeImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // defpackage.C0915i0, android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        Drawable drawable;
        if (Build.VERSION.SDK_INT < 24 && (drawable = getDrawable()) != null && drawable.isStateful() && drawable.setState(getDrawableState())) {
            invalidateDrawable(drawable);
        }
        super.drawableStateChanged();
    }
}
