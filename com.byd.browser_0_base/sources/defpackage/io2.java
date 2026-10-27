package defpackage;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.appcompat.R;

/* JADX INFO: compiled from: SkinCompatCompoundButtonHelper.java */
/* JADX INFO: loaded from: classes3.dex */
public final class io2 extends vd1 {
    public final CompoundButton k;
    public int l = 0;
    public int m = 0;

    public io2(CompoundButton compoundButton) {
        this.k = compoundButton;
    }

    public final void J() {
        int iB = vd1.b(this.l);
        this.l = iB;
        CompoundButton compoundButton = this.k;
        if (iB != 0) {
            compoundButton.setButtonDrawable(xo2.a(compoundButton.getContext(), this.l));
        }
        int iB2 = vd1.b(this.m);
        this.m = iB2;
        if (iB2 != 0) {
            compoundButton.setButtonTintList(po2.b(compoundButton.getContext(), this.m));
        }
    }

    public final void K(AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = this.k.getContext().obtainStyledAttributes(attributeSet, R.styleable.CompoundButton, i, 0);
        try {
            int i2 = R.styleable.CompoundButton_android_button;
            if (typedArrayObtainStyledAttributes.hasValue(i2)) {
                this.l = typedArrayObtainStyledAttributes.getResourceId(i2, 0);
            }
            int i3 = R.styleable.CompoundButton_buttonTint;
            if (typedArrayObtainStyledAttributes.hasValue(i3)) {
                this.m = typedArrayObtainStyledAttributes.getResourceId(i3, 0);
            }
            typedArrayObtainStyledAttributes.recycle();
            J();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }
}
