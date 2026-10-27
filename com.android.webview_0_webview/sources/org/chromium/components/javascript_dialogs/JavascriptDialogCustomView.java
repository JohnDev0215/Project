package org.chromium.components.javascript_dialogs;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import defpackage.AbstractC0371Um;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class JavascriptDialogCustomView extends LinearLayout {
    public EditText s;
    public CheckBox t;

    public JavascriptDialogCustomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean a() {
        return this.t.isChecked();
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.s = (EditText) findViewById(AbstractC0371Um.Z1);
        this.t = (CheckBox) findViewById(AbstractC0371Um.C4);
    }
}
