package skin.support.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView;
import defpackage.go2;
import defpackage.ro2;
import defpackage.to2;
import defpackage.vd1;
import defpackage.xo2;

/* JADX INFO: loaded from: classes3.dex */
public class SkinCompatMultiAutoCompleteTextView extends AppCompatMultiAutoCompleteTextView implements ro2 {
    public static final int[] l = {R.attr.popupBackground};
    public int i;
    public final to2 j;
    public final go2 k;

    public SkinCompatMultiAutoCompleteTextView(Context context) {
        this(context, null);
    }

    public final void a() {
        Drawable drawableA;
        int iB = vd1.b(this.i);
        this.i = iB;
        if (iB == 0 || (drawableA = xo2.a(getContext(), this.i)) == null) {
            return;
        }
        setDropDownBackgroundDrawable(drawableA);
    }

    @Override // defpackage.ro2
    public final void e() {
        go2 go2Var = this.k;
        if (go2Var != null) {
            go2Var.J();
        }
        to2 to2Var = this.j;
        if (to2Var != null) {
            to2Var.L();
        }
        a();
    }

    @Override // androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView, android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        go2 go2Var = this.k;
        if (go2Var != null) {
            go2Var.L(i);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(i, i2, i3, i4);
        to2 to2Var = this.j;
        if (to2Var != null) {
            to2Var.P(i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        super.setCompoundDrawablesWithIntrinsicBounds(i, i2, i3, i4);
        to2 to2Var = this.j;
        if (to2Var != null) {
            to2Var.N(i, i2, i3, i4);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView, android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i) {
        super.setDropDownBackgroundResource(i);
        this.i = i;
        a();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i) {
        setTextAppearance(getContext(), i);
    }

    public SkinCompatMultiAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, androidx.appcompat.R.attr.editTextStyle);
    }

    @Override // androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView, android.widget.TextView
    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        to2 to2Var = this.j;
        if (to2Var != null) {
            to2Var.O(context, i);
        }
    }

    public SkinCompatMultiAutoCompleteTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, l, i, 0);
        if (typedArrayObtainStyledAttributes.hasValue(0)) {
            this.i = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        }
        typedArrayObtainStyledAttributes.recycle();
        a();
        go2 go2Var = new go2(this);
        this.k = go2Var;
        go2Var.K(attributeSet, i);
        to2 to2Var = new to2(this);
        this.j = to2Var;
        to2Var.M(attributeSet, i);
    }
}
