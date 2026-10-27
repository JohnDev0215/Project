package org.chromium.chrome.browser.password_edit_dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.chromium.base.Callback;
import org.chromium.chrome.R$id;

/* JADX INFO: loaded from: classes2.dex */
abstract class PasswordEditDialogView extends LinearLayout {
    public TextView e;

    public PasswordEditDialogView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.e = (TextView) findViewById(R$id.footer);
    }

    void a(String str) {
    }

    public void b(Callback<String> callback) {
    }

    public void c(String str) {
    }
}
