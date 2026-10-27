package org.chromium.chrome.browser.password_manager;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.chromium.chrome.R$id;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordGenerationDialogCustomView extends LinearLayout {
    public TextView e;
    public TextView f;

    public PasswordGenerationDialogCustomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (TextView) findViewById(R$id.generated_password);
        this.f = (TextView) findViewById(R$id.generation_save_explanation);
    }

    public void setGeneratedPassword(String str) {
        this.e.setText(str);
        this.e.setInputType(131217);
    }

    public void setSaveExplanationText(String str) {
        this.f.setText(str);
    }
}
