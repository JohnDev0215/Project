package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes2.dex */
public class CheckBoxWithDescription extends RelativeLayout implements View.OnClickListener {
    public CheckBox e;
    public TextView f;
    public TextView g;

    public CheckBoxWithDescription(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R$layout.checkbox_with_description, (ViewGroup) this, true);
        this.e = getCheckBoxView();
        this.f = getPrimaryTextView();
        this.g = getDescriptionTextView();
        setOnClickListener(this);
        setFocusable(true);
    }

    private CheckBox getCheckBoxView() {
        return (CheckBox) findViewById(R$id.checkbox);
    }

    public CharSequence getDescriptionText() {
        return this.g.getText();
    }

    public TextView getDescriptionTextView() {
        return (TextView) findViewById(R$id.description);
    }

    public CharSequence getPrimaryText() {
        return this.f.getText();
    }

    public TextView getPrimaryTextView() {
        return (TextView) findViewById(R$id.primary);
    }

    public void onClick(View view) {
        setChecked(!this.e.isChecked());
    }

    public void setChecked(boolean z) {
        this.e.setChecked(z);
    }

    public void setDescriptionText(CharSequence charSequence) {
        this.g.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            ((RelativeLayout.LayoutParams) this.f.getLayoutParams()).addRule(15);
            this.g.setVisibility(8);
        } else {
            ((RelativeLayout.LayoutParams) this.f.getLayoutParams()).removeRule(15);
            this.g.setVisibility(0);
        }
    }

    public void setPrimaryText(CharSequence charSequence) {
        this.f.setText(charSequence);
    }
}
