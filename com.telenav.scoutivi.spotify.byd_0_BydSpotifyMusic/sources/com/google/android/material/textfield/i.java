package com.google.android.material.textfield;

import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;
import o2.j;

/* JADX INFO: compiled from: PasswordToggleEndIconDelegate.java */
/* JADX INFO: loaded from: classes.dex */
class i extends e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final TextWatcher f4927d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final TextInputLayout.f f4928e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final TextInputLayout.g f4929f;

    /* JADX INFO: compiled from: PasswordToggleEndIconDelegate.java */
    class a extends com.google.android.material.internal.i {
        a() {
        }

        @Override // com.google.android.material.internal.i, android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
            i.this.f4896c.setChecked(!r1.g());
        }
    }

    /* JADX INFO: compiled from: PasswordToggleEndIconDelegate.java */
    class b implements TextInputLayout.f {
        b() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.f
        public void a(TextInputLayout textInputLayout) {
            EditText editText = textInputLayout.getEditText();
            textInputLayout.setEndIconVisible(true);
            textInputLayout.setEndIconCheckable(true);
            i.this.f4896c.setChecked(!r4.g());
            editText.removeTextChangedListener(i.this.f4927d);
            editText.addTextChangedListener(i.this.f4927d);
        }
    }

    /* JADX INFO: compiled from: PasswordToggleEndIconDelegate.java */
    class c implements TextInputLayout.g {

        /* JADX INFO: compiled from: PasswordToggleEndIconDelegate.java */
        class a implements Runnable {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ EditText f4933e;

            a(EditText editText) {
                this.f4933e = editText;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f4933e.removeTextChangedListener(i.this.f4927d);
            }
        }

        c() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.g
        public void a(TextInputLayout textInputLayout, int i7) {
            EditText editText = textInputLayout.getEditText();
            if (editText == null || i7 != 1) {
                return;
            }
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editText.post(new a(editText));
        }
    }

    /* JADX INFO: compiled from: PasswordToggleEndIconDelegate.java */
    class d implements View.OnClickListener {
        d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EditText editText = i.this.f4894a.getEditText();
            if (editText == null) {
                return;
            }
            int selectionEnd = editText.getSelectionEnd();
            if (i.this.g()) {
                editText.setTransformationMethod(null);
            } else {
                editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            if (selectionEnd >= 0) {
                editText.setSelection(selectionEnd);
            }
            i.this.f4894a.V();
        }
    }

    i(TextInputLayout textInputLayout) {
        super(textInputLayout);
        this.f4927d = new a();
        this.f4928e = new b();
        this.f4929f = new c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        EditText editText = this.f4894a.getEditText();
        return editText != null && (editText.getTransformationMethod() instanceof PasswordTransformationMethod);
    }

    private static boolean h(EditText editText) {
        return editText != null && (editText.getInputType() == 16 || editText.getInputType() == 128 || editText.getInputType() == 144 || editText.getInputType() == 224);
    }

    @Override // com.google.android.material.textfield.e
    void a() {
        this.f4894a.setEndIconDrawable(f.a.b(this.f4895b, o2.e.design_password_eye));
        TextInputLayout textInputLayout = this.f4894a;
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(j.password_toggle_content_description));
        this.f4894a.setEndIconOnClickListener(new d());
        this.f4894a.e(this.f4928e);
        this.f4894a.f(this.f4929f);
        EditText editText = this.f4894a.getEditText();
        if (h(editText)) {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }
}
