package org.chromium.components.javascript_dialogs;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
public class JavascriptDialogCustomView extends LinearLayout {
    public EditText e;
    public CheckBox f;

    public JavascriptDialogCustomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public String getPromptText() {
        return this.e.getText().toString();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (EditText) findViewById(R$id.js_modal_dialog_prompt);
        this.f = (CheckBox) findViewById(R$id.suppress_js_modal_dialogs);
    }

    public void setPromptText(String str) {
        if (str == null) {
            return;
        }
        this.e.setVisibility(0);
        if (str.length() > 0) {
            this.e.setText(str);
            this.e.selectAll();
        }
    }

    public void setSuppressCheckBoxVisibility(boolean z) {
        this.f.setVisibility(z ? 0 : 8);
    }
}
