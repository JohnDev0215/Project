package org.chromium.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import defpackage.AbstractC0245Nm;
import defpackage.C0803g0;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ChromeImageButton extends C0803g0 {
    public ChromeImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, AbstractC0245Nm.l4);
    }

    @Override // defpackage.C0803g0, android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        Drawable drawable;
        if (Build.VERSION.SDK_INT < 24 && (drawable = getDrawable()) != null && drawable.isStateful() && drawable.setState(getDrawableState())) {
            invalidateDrawable(drawable);
        }
        super.drawableStateChanged();
    }
}
