package p;

import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;
import com.spotify.music.R;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public final class ms1 extends be0 {
    public final lb0 e;
    public final at f;
    public final bt g;

    public ms1(TextInputLayout textInputLayout, int i) {
        super(textInputLayout, i);
        this.e = new lb0(this, 1);
        this.f = new at(this, 2);
        this.g = new bt(this, 2);
    }

    public static boolean d(ms1 ms1Var) {
        EditText editText = ms1Var.a.getEditText();
        return editText != null && (editText.getTransformationMethod() instanceof PasswordTransformationMethod);
    }

    @Override // p.be0
    public final void a() {
        int i = this.d;
        if (i == 0) {
            i = R.drawable.design_password_eye;
        }
        TextInputLayout textInputLayout = this.a;
        textInputLayout.setEndIconDrawable(i);
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(R.string.password_toggle_content_description));
        textInputLayout.setEndIconOnClickListener(new e6(5, this));
        LinkedHashSet linkedHashSet = textInputLayout.n0;
        at atVar = this.f;
        linkedHashSet.add(atVar);
        if (textInputLayout.m != null) {
            atVar.a(textInputLayout);
        }
        textInputLayout.r0.add(this.g);
        EditText editText = textInputLayout.getEditText();
        if (editText != null) {
            if (editText.getInputType() == 16 || editText.getInputType() == 128 || editText.getInputType() == 144 || editText.getInputType() == 224) {
                editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }
}
