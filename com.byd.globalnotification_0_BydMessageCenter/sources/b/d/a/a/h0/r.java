package b.d.a.a.h0;

import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
public class r extends m {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final TextWatcher f1945d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final TextInputLayout.f f1946e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final TextInputLayout.g f1947f;

    public class a extends b.d.a.a.z.j {
        public a() {
        }

        @Override // b.d.a.a.z.j, android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            r.this.f1929c.setChecked(!r.e(r0));
        }
    }

    public class b implements TextInputLayout.f {
        public b() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.f
        public void a(TextInputLayout textInputLayout) {
            EditText editText = textInputLayout.getEditText();
            textInputLayout.setEndIconVisible(true);
            textInputLayout.setEndIconCheckable(true);
            r.this.f1929c.setChecked(!r.e(r4));
            editText.removeTextChangedListener(r.this.f1945d);
            editText.addTextChangedListener(r.this.f1945d);
        }
    }

    public class c implements TextInputLayout.g {

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ EditText f1951a;

            public a(EditText editText) {
                this.f1951a = editText;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f1951a.removeTextChangedListener(r.this.f1945d);
            }
        }

        public c() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.g
        public void a(TextInputLayout textInputLayout, int i) {
            EditText editText = textInputLayout.getEditText();
            if (editText == null || i != 1) {
                return;
            }
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editText.post(new a(editText));
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EditText editText = r.this.f1927a.getEditText();
            if (editText == null) {
                return;
            }
            int selectionEnd = editText.getSelectionEnd();
            editText.setTransformationMethod(r.e(r.this) ? null : PasswordTransformationMethod.getInstance());
            if (selectionEnd >= 0) {
                editText.setSelection(selectionEnd);
            }
            r.this.f1927a.o();
        }
    }

    public r(TextInputLayout textInputLayout) {
        super(textInputLayout);
        this.f1945d = new a();
        this.f1946e = new b();
        this.f1947f = new c();
    }

    public static boolean e(r rVar) {
        EditText editText = rVar.f1927a.getEditText();
        return editText != null && (editText.getTransformationMethod() instanceof PasswordTransformationMethod);
    }

    @Override // b.d.a.a.h0.m
    public void a() {
        this.f1927a.setEndIconDrawable(a.b.l.a.a.b(this.f1928b, b.d.a.a.e.design_password_eye));
        TextInputLayout textInputLayout = this.f1927a;
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(b.d.a.a.i.password_toggle_content_description));
        this.f1927a.setEndIconOnClickListener(new d());
        this.f1927a.a(this.f1946e);
        this.f1927a.f0.add(this.f1947f);
        EditText editText = this.f1927a.getEditText();
        if (editText != null && (editText.getInputType() == 16 || editText.getInputType() == 128 || editText.getInputType() == 144 || editText.getInputType() == 224)) {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }
}
