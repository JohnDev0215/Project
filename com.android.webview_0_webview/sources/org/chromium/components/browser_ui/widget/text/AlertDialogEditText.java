package org.chromium.components.browser_ui.widget.text;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import defpackage.AbstractC0691e0;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AlertDialogEditText extends AbstractC0691e0 {
    public AlertDialogEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        if (Build.VERSION.SDK_INT >= 24) {
            return;
        }
        int inputType = getInputType() & 4095;
        if (!(inputType == 129 || inputType == 225 || inputType == 18) || TextUtils.isEmpty(getHint())) {
            return;
        }
        setContentDescription(getHint());
    }
}
