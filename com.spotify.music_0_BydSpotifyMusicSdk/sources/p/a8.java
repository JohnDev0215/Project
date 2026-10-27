package p;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;

/* JADX INFO: loaded from: classes.dex */
public final class a8 {
    public final CompoundButton a;
    public ColorStateList b = null;
    public PorterDuff.Mode c = null;
    public boolean d = false;
    public boolean e = false;
    public boolean f;

    public a8(CompoundButton compoundButton) {
        this.a = compoundButton;
    }

    public final void a() {
        CompoundButton compoundButton = this.a;
        Drawable drawableA = jw.a(compoundButton);
        if (drawableA != null) {
            if (this.d || this.e) {
                Drawable drawableMutate = drawableA.mutate();
                if (this.d) {
                    ya0.h(drawableMutate, this.b);
                }
                if (this.e) {
                    ya0.i(drawableMutate, this.c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(compoundButton.getDrawableState());
                }
                compoundButton.setButtonDrawable(drawableMutate);
            }
        }
    }

    public final void b(AttributeSet attributeSet, int i) {
        int iY;
        int iY2;
        CompoundButton compoundButton = this.a;
        Context context = compoundButton.getContext();
        int[] iArr = yz1.m;
        v4 v4VarJ = v4.J(context, attributeSet, iArr, i);
        zt2.j(compoundButton, compoundButton.getContext(), iArr, attributeSet, (TypedArray) v4VarJ.k, i);
        try {
            if (v4VarJ.G(1) && (iY2 = v4VarJ.y(1, 0)) != 0) {
                try {
                    compoundButton.setButtonDrawable(l13.f(compoundButton.getContext(), iY2));
                } catch (Resources.NotFoundException unused) {
                    if (v4VarJ.G(0)) {
                        compoundButton.setButtonDrawable(l13.f(compoundButton.getContext(), iY));
                    }
                }
            } else if (v4VarJ.G(0) && (iY = v4VarJ.y(0, 0)) != 0) {
                compoundButton.setButtonDrawable(l13.f(compoundButton.getContext(), iY));
            }
            if (v4VarJ.G(2)) {
                iw.c(compoundButton, v4VarJ.q(2));
            }
            if (v4VarJ.G(3)) {
                iw.d(compoundButton, cb0.b(v4VarJ.w(3, -1), null));
            }
        } finally {
            v4VarJ.L();
        }
    }
}
