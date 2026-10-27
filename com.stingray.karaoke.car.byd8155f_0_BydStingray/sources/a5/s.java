package a5;

import android.graphics.drawable.Drawable;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;
import com.stingray.drivemodedetector.R;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public final class s extends p {
    public final k d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c f11192e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final d f11193f;

    public s(TextInputLayout textInputLayout) {
        super(textInputLayout);
        this.d = new k(this, 1);
        this.f11192e = new c(this, 2);
        this.f11193f = new d(this, 2);
    }

    public static boolean d(s sVar) {
        EditText editText = sVar.f11170a.getEditText();
        return editText != null && (editText.getTransformationMethod() instanceof PasswordTransformationMethod);
    }

    @Override // a5.p
    public final void a() {
        Drawable drawableK = h6.f.k(this.f11171b, R.drawable.design_password_eye);
        TextInputLayout textInputLayout = this.f11170a;
        textInputLayout.setEndIconDrawable(drawableK);
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(R.string.password_toggle_content_description));
        textInputLayout.setEndIconOnClickListener(new e(this, 2));
        LinkedHashSet linkedHashSet = textInputLayout.f12926p0;
        c cVar = this.f11192e;
        linkedHashSet.add(cVar);
        if (textInputLayout.f12925p != null) {
            cVar.a(textInputLayout);
        }
        textInputLayout.f12934t0.add(this.f11193f);
        EditText editText = textInputLayout.getEditText();
        if (editText != null) {
            if (editText.getInputType() == 16 || editText.getInputType() == 128 || editText.getInputType() == 144 || editText.getInputType() == 224) {
                editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }
}
