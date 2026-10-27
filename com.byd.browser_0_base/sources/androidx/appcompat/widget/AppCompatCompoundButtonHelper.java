package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.in3;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
class AppCompatCompoundButtonHelper {
    public final CompoundButton a;
    public ColorStateList b = null;
    public PorterDuff.Mode c = null;
    public boolean d = false;
    public boolean e = false;
    public boolean f;

    public AppCompatCompoundButtonHelper(CompoundButton compoundButton) {
        this.a = compoundButton;
    }

    public final void a() {
        CompoundButton compoundButton = this.a;
        Drawable buttonDrawable = compoundButton.getButtonDrawable();
        if (buttonDrawable != null) {
            if (this.d || this.e) {
                Drawable drawableMutate = buttonDrawable.mutate();
                if (this.d) {
                    drawableMutate.setTintList(this.b);
                }
                if (this.e) {
                    drawableMutate.setTintMode(this.c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(compoundButton.getDrawableState());
                }
                compoundButton.setButtonDrawable(drawableMutate);
            }
        }
    }

    public final void b(AttributeSet attributeSet, int i) {
        boolean z;
        int i2;
        int i3;
        CompoundButton compoundButton = this.a;
        Context context = compoundButton.getContext();
        int[] iArr = R.styleable.CompoundButton;
        TintTypedArray tintTypedArrayM = TintTypedArray.m(context, attributeSet, iArr, i);
        Context context2 = compoundButton.getContext();
        TypedArray typedArray = tintTypedArrayM.b;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.n.b(compoundButton, context2, iArr, attributeSet, typedArray, i, 0);
        try {
            int i4 = R.styleable.CompoundButton_buttonCompat;
            if (!tintTypedArrayM.l(i4) || (i3 = tintTypedArrayM.i(i4, 0)) == 0) {
                z = false;
            } else {
                try {
                    compoundButton.setButtonDrawable(AppCompatResources.a(compoundButton.getContext(), i3));
                    z = true;
                } catch (Resources.NotFoundException unused) {
                    z = false;
                }
            }
            if (!z) {
                int i5 = R.styleable.CompoundButton_android_button;
                if (tintTypedArrayM.l(i5) && (i2 = tintTypedArrayM.i(i5, 0)) != 0) {
                    compoundButton.setButtonDrawable(AppCompatResources.a(compoundButton.getContext(), i2));
                }
            }
            int i6 = R.styleable.CompoundButton_buttonTint;
            if (tintTypedArrayM.l(i6)) {
                compoundButton.setButtonTintList(tintTypedArrayM.b(i6));
            }
            int i7 = R.styleable.CompoundButton_buttonTintMode;
            if (tintTypedArrayM.l(i7)) {
                compoundButton.setButtonTintMode(DrawableUtils.c(tintTypedArrayM.h(i7, -1), null));
            }
        } finally {
            tintTypedArrayM.n();
        }
    }
}
