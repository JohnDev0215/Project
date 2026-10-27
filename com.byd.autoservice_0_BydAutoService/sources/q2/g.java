package q2;

import android.graphics.drawable.Drawable;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: compiled from: NoEndIconDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends e {
    public g(TextInputLayout textInputLayout) {
        super(textInputLayout);
    }

    @Override // q2.e
    public void a() {
        this.f4605a.setEndIconOnClickListener(null);
        this.f4605a.setEndIconDrawable((Drawable) null);
        this.f4605a.setEndIconContentDescription((CharSequence) null);
    }
}
