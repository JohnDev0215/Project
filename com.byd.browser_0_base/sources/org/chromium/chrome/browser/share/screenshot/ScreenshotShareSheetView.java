package org.chromium.chrome.browser.share.screenshot;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import org.chromium.base.Callback;

/* JADX INFO: loaded from: classes2.dex */
class ScreenshotShareSheetView extends FrameLayout {
    public static final /* synthetic */ int e = 0;

    public ScreenshotShareSheetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void a(final Integer num, int i, final Callback<Integer> callback) {
        findViewById(i).setOnClickListener(new View.OnClickListener() { // from class: org.chromium.chrome.browser.share.screenshot.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i2 = ScreenshotShareSheetView.e;
                callback.onResult(num);
            }
        });
    }
}
