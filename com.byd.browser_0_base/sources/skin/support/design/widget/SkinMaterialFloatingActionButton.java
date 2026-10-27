package skin.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import defpackage.mo2;
import defpackage.po2;
import defpackage.ro2;
import defpackage.vd1;

/* JADX INFO: loaded from: classes3.dex */
public class SkinMaterialFloatingActionButton extends FloatingActionButton implements ro2 {
    public int v;
    public int w;
    public final mo2 x;

    public SkinMaterialFloatingActionButton(Context context) {
        this(context, null);
    }

    @Override // defpackage.ro2
    public final void e() {
        int iB = vd1.b(this.w);
        this.w = iB;
        if (iB != 0) {
            setBackgroundTintList(po2.b(getContext(), this.w));
        }
        int iB2 = vd1.b(this.v);
        this.v = iB2;
        if (iB2 != 0) {
            setRippleColor(po2.a(getContext(), this.v));
        }
        mo2 mo2Var = this.x;
        if (mo2Var != null) {
            mo2Var.J();
        }
    }

    public SkinMaterialFloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SkinMaterialFloatingActionButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.v = 0;
        this.w = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.FloatingActionButton, i, R$style.Widget_Design_FloatingActionButton);
        this.w = typedArrayObtainStyledAttributes.getResourceId(R$styleable.FloatingActionButton_backgroundTint, 0);
        this.v = typedArrayObtainStyledAttributes.getResourceId(R$styleable.FloatingActionButton_rippleColor, 0);
        typedArrayObtainStyledAttributes.recycle();
        int iB = vd1.b(this.w);
        this.w = iB;
        if (iB != 0) {
            setBackgroundTintList(po2.b(getContext(), this.w));
        }
        int iB2 = vd1.b(this.v);
        this.v = iB2;
        if (iB2 != 0) {
            setRippleColor(po2.a(getContext(), this.v));
        }
        mo2 mo2Var = new mo2(this);
        this.x = mo2Var;
        mo2Var.K(attributeSet, i);
    }
}
