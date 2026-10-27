package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.Configuration;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.material.chip.Chip;
import com.google.android.material.internal.i;
import com.google.android.material.textfield.TextInputLayout;
import o2.f;
import o2.h;

/* JADX INFO: loaded from: classes.dex */
class ChipTextInputComboView extends FrameLayout implements Checkable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Chip f4936e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final TextInputLayout f4937g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final EditText f4938h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private TextWatcher f4939i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private TextView f4940j;

    private class b extends i {
        private b() {
        }

        @Override // com.google.android.material.internal.i, android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(editable)) {
                ChipTextInputComboView.this.f4936e.setText(ChipTextInputComboView.this.c("00"));
            } else {
                ChipTextInputComboView.this.f4936e.setText(ChipTextInputComboView.this.c(editable));
            }
        }
    }

    public ChipTextInputComboView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c(CharSequence charSequence) {
        return TimeModel.b(getResources(), charSequence);
    }

    private void d() {
        this.f4938h.setImeHintLocales(getContext().getResources().getConfiguration().getLocales());
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.f4936e.isChecked();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        d();
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z6) {
        this.f4936e.setChecked(z6);
        this.f4938h.setVisibility(z6 ? 0 : 4);
        this.f4936e.setVisibility(z6 ? 8 : 0);
        if (isChecked()) {
            this.f4938h.requestFocus();
            if (TextUtils.isEmpty(this.f4938h.getText())) {
                return;
            }
            EditText editText = this.f4938h;
            editText.setSelection(editText.getText().length());
        }
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.f4936e.setOnClickListener(onClickListener);
    }

    @Override // android.view.View
    public void setTag(int i7, Object obj) {
        this.f4936e.setTag(i7, obj);
    }

    @Override // android.widget.Checkable
    public void toggle() {
        this.f4936e.toggle();
    }

    public ChipTextInputComboView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        Chip chip = (Chip) layoutInflaterFrom.inflate(h.material_time_chip, (ViewGroup) this, false);
        this.f4936e = chip;
        TextInputLayout textInputLayout = (TextInputLayout) layoutInflaterFrom.inflate(h.material_time_input, (ViewGroup) this, false);
        this.f4937g = textInputLayout;
        EditText editText = textInputLayout.getEditText();
        this.f4938h = editText;
        editText.setVisibility(4);
        b bVar = new b();
        this.f4939i = bVar;
        editText.addTextChangedListener(bVar);
        d();
        addView(chip);
        addView(textInputLayout);
        this.f4940j = (TextView) findViewById(f.material_label);
        editText.setSaveEnabled(false);
    }
}
