package defpackage;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.R$drawable;
import com.google.android.material.R$string;
import com.google.android.material.textfield.a;

/* JADX INFO: compiled from: PasswordToggleEndIconDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class cy1 extends sh0 {
    public final int e;
    public EditText f;
    public final by1 g;

    public cy1(a aVar, int i) {
        super(aVar);
        this.e = R$drawable.design_password_eye;
        this.g = new by1(0, this);
        if (i != 0) {
            this.e = i;
        }
    }

    @Override // defpackage.sh0
    public final void b() {
        q();
    }

    @Override // defpackage.sh0
    public final int c() {
        return R$string.password_toggle_content_description;
    }

    @Override // defpackage.sh0
    public final int d() {
        return this.e;
    }

    @Override // defpackage.sh0
    public final View.OnClickListener f() {
        return this.g;
    }

    @Override // defpackage.sh0
    public final boolean k() {
        return true;
    }

    @Override // defpackage.sh0
    public final boolean l() {
        EditText editText = this.f;
        return !(editText != null && (editText.getTransformationMethod() instanceof PasswordTransformationMethod));
    }

    @Override // defpackage.sh0
    public final void m(EditText editText) {
        this.f = editText;
        q();
    }

    @Override // defpackage.sh0
    public final void r() {
        EditText editText = this.f;
        if (editText != null && (editText.getInputType() == 16 || editText.getInputType() == 128 || editText.getInputType() == 144 || editText.getInputType() == 224)) {
            this.f.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    @Override // defpackage.sh0
    public final void s() {
        EditText editText = this.f;
        if (editText != null) {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }
}
