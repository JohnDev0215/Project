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
import b.d.a.a.h;
import b.d.a.a.z.j;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
public class ChipTextInputComboView extends FrameLayout implements Checkable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Chip f2481a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TextInputLayout f2482b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final EditText f2483c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextWatcher f2484d;

    public class b extends j {
        public b(a aVar) {
        }

        @Override // b.d.a.a.z.j, android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            boolean zIsEmpty = TextUtils.isEmpty(editable);
            ChipTextInputComboView chipTextInputComboView = ChipTextInputComboView.this;
            if (zIsEmpty) {
                chipTextInputComboView.f2481a.setText(ChipTextInputComboView.a(chipTextInputComboView, "00"));
            } else {
                chipTextInputComboView.f2481a.setText(ChipTextInputComboView.a(chipTextInputComboView, editable));
            }
        }
    }

    public ChipTextInputComboView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        this.f2481a = (Chip) layoutInflaterFrom.inflate(h.material_time_chip, (ViewGroup) this, false);
        TextInputLayout textInputLayout = (TextInputLayout) layoutInflaterFrom.inflate(h.material_time_input, (ViewGroup) this, false);
        this.f2482b = textInputLayout;
        EditText editText = textInputLayout.getEditText();
        this.f2483c = editText;
        editText.setVisibility(4);
        b bVar = new b(null);
        this.f2484d = bVar;
        this.f2483c.addTextChangedListener(bVar);
        b();
        addView(this.f2481a);
        addView(this.f2482b);
        this.f2483c.setSaveEnabled(false);
    }

    public static String a(ChipTextInputComboView chipTextInputComboView, CharSequence charSequence) {
        return String.format(chipTextInputComboView.getResources().getConfiguration().locale, "%02d", Integer.valueOf(Integer.parseInt(String.valueOf(charSequence))));
    }

    public final void b() {
        this.f2483c.setImeHintLocales(getContext().getResources().getConfiguration().getLocales());
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.f2481a.isChecked();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        b();
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        this.f2481a.setChecked(z);
        this.f2483c.setVisibility(z ? 0 : 4);
        this.f2481a.setVisibility(z ? 8 : 0);
        if (isChecked()) {
            this.f2483c.requestFocus();
            if (TextUtils.isEmpty(this.f2483c.getText())) {
                return;
            }
            EditText editText = this.f2483c;
            editText.setSelection(editText.getText().length());
        }
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.f2481a.setOnClickListener(onClickListener);
    }

    @Override // android.view.View
    public void setTag(int i, Object obj) {
        this.f2481a.setTag(i, obj);
    }

    @Override // android.widget.Checkable
    public void toggle() {
        this.f2481a.toggle();
    }
}
