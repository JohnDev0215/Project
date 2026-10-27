package p;

import android.graphics.drawable.Drawable;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
public final class x40 extends be0 {
    public final /* synthetic */ int e = 1;

    public x40(TextInputLayout textInputLayout) {
        super(textInputLayout, 0);
    }

    @Override // p.be0
    public final void a() {
        TextInputLayout textInputLayout = this.a;
        switch (this.e) {
            case 0:
                textInputLayout.setEndIconDrawable(this.d);
                textInputLayout.setEndIconOnClickListener(null);
                textInputLayout.setEndIconOnLongClickListener(null);
                break;
            default:
                textInputLayout.setEndIconOnClickListener(null);
                textInputLayout.setEndIconDrawable((Drawable) null);
                textInputLayout.setEndIconContentDescription((CharSequence) null);
                break;
        }
    }

    public x40(TextInputLayout textInputLayout, int i) {
        super(textInputLayout, i);
    }
}
