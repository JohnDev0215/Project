package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class FadingShadowView extends View {
    public float s;

    public FadingShadowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.s = 1.0f;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
