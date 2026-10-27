package c.b.a.a.l0;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import c.b.a.a.j;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: compiled from: PasswordToggleEndIconDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final TextWatcher f4001d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final TextInputLayout.f f4002e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final TextInputLayout.g f4003f;

    /* JADX INFO: compiled from: PasswordToggleEndIconDelegate.java */
    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            h.this.f3986c.setChecked(!r1.g());
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }
    }

    /* JADX INFO: compiled from: PasswordToggleEndIconDelegate.java */
    public class b implements TextInputLayout.f {
        public b() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.f
        public void a(TextInputLayout textInputLayout) {
            EditText editText = textInputLayout.getEditText();
            textInputLayout.setEndIconVisible(true);
            textInputLayout.setEndIconCheckable(true);
            h.this.f3986c.setChecked(!r4.g());
            editText.removeTextChangedListener(h.this.f4001d);
            editText.addTextChangedListener(h.this.f4001d);
        }
    }

    /* JADX INFO: compiled from: PasswordToggleEndIconDelegate.java */
    public class c implements TextInputLayout.g {
        public c() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.g
        public void a(TextInputLayout textInputLayout, int i2) {
            EditText editText = textInputLayout.getEditText();
            if (editText == null || i2 != 1) {
                return;
            }
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editText.removeTextChangedListener(h.this.f4001d);
        }
    }

    /* JADX INFO: compiled from: PasswordToggleEndIconDelegate.java */
    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EditText editText = h.this.f3984a.getEditText();
            if (editText == null) {
                return;
            }
            int selectionEnd = editText.getSelectionEnd();
            if (h.this.g()) {
                editText.setTransformationMethod(null);
            } else {
                editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            if (selectionEnd >= 0) {
                editText.setSelection(selectionEnd);
            }
        }
    }

    public h(TextInputLayout textInputLayout) {
        super(textInputLayout);
        this.f4001d = new a();
        this.f4002e = new b();
        this.f4003f = new c();
    }

    public static boolean h(EditText editText) {
        return editText != null && (editText.getInputType() == 16 || editText.getInputType() == 128 || editText.getInputType() == 144 || editText.getInputType() == 224);
    }

    @Override // c.b.a.a.l0.e
    public void a() {
        this.f3984a.setEndIconDrawable(b.b.l.a.a.d(this.f3985b, c.b.a.a.e.design_password_eye));
        TextInputLayout textInputLayout = this.f3984a;
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(j.password_toggle_content_description));
        this.f3984a.setEndIconOnClickListener(new d());
        this.f3984a.e(this.f4002e);
        this.f3984a.f(this.f4003f);
        EditText editText = this.f3984a.getEditText();
        if (h(editText)) {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    public final boolean g() {
        EditText editText = this.f3984a.getEditText();
        return editText != null && (editText.getTransformationMethod() instanceof PasswordTransformationMethod);
    }
}
