package skin.support.design.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import com.google.android.material.R$style;
import com.google.android.material.navigation.NavigationView;
import defpackage.go2;
import defpackage.po2;
import defpackage.ro2;
import defpackage.uo2;
import defpackage.vd1;
import defpackage.wo2;
import defpackage.xo2;
import skin.support.R$styleable;

/* JADX INFO: loaded from: classes3.dex */
public class SkinMaterialNavigationView extends NavigationView implements ro2 {
    public static final int[] D = {R.attr.state_checked};
    public static final int[] E = {-16842910};
    public int A;
    public int B;
    public final go2 C;
    public int y;
    public int z;

    public SkinMaterialNavigationView(Context context) {
        this(context, null);
    }

    private ColorStateList b(int i) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateListB = po2.b(getContext(), typedValue.resourceId);
        int iA = po2.a(getContext(), this.A);
        int defaultColor = colorStateListB.getDefaultColor();
        int[] iArr = E;
        return new ColorStateList(new int[][]{iArr, D, FrameLayout.EMPTY_STATE_SET}, new int[]{colorStateListB.getColorForState(iArr, defaultColor), iA, defaultColor});
    }

    public final void d() {
        Drawable drawableA;
        int iB = vd1.b(this.y);
        this.y = iB;
        if (iB == 0 || (drawableA = xo2.a(getContext(), this.y)) == null) {
            return;
        }
        setItemBackground(drawableA);
    }

    @Override // defpackage.ro2
    public final void e() {
        go2 go2Var = this.C;
        if (go2Var != null) {
            go2Var.J();
        }
        f();
        g();
        d();
    }

    public final void f() {
        int iB = vd1.b(this.B);
        this.B = iB;
        if (iB != 0) {
            setItemIconTintList(po2.b(getContext(), this.B));
            return;
        }
        int iB2 = vd1.b(this.A);
        this.A = iB2;
        if (iB2 != 0) {
            setItemIconTintList(b(R.attr.textColorSecondary));
        }
    }

    public final void g() {
        int iB = vd1.b(this.z);
        this.z = iB;
        if (iB != 0) {
            setItemTextColor(po2.b(getContext(), this.z));
            return;
        }
        int iB2 = vd1.b(this.A);
        this.A = iB2;
        if (iB2 != 0) {
            setItemTextColor(b(R.attr.textColorPrimary));
        }
    }

    @Override // com.google.android.material.navigation.NavigationView
    public void setItemBackgroundResource(int i) {
        super.setItemBackgroundResource(i);
        this.y = i;
        d();
    }

    @Override // com.google.android.material.navigation.NavigationView
    public void setItemTextAppearance(int i) {
        super.setItemTextAppearance(i);
        if (i != 0) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(i, R$styleable.SkinTextAppearance);
            int i2 = R$styleable.SkinTextAppearance_android_textColor;
            if (typedArrayObtainStyledAttributes.hasValue(i2)) {
                this.z = typedArrayObtainStyledAttributes.getResourceId(i2, 0);
            }
            typedArrayObtainStyledAttributes.recycle();
            g();
        }
    }

    public SkinMaterialNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SkinMaterialNavigationView(Context context, AttributeSet attributeSet, int i) {
        int resourceId;
        super(context, attributeSet, i);
        this.y = 0;
        this.z = 0;
        this.A = 0;
        this.B = 0;
        go2 go2Var = new go2(this);
        this.C = go2Var;
        go2Var.K(attributeSet, 0);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.google.android.material.R$styleable.NavigationView, i, R$style.Widget_Design_NavigationView);
        int i2 = com.google.android.material.R$styleable.NavigationView_itemIconTint;
        if (typedArrayObtainStyledAttributes.hasValue(i2)) {
            this.B = typedArrayObtainStyledAttributes.getResourceId(i2, 0);
        } else {
            this.A = uo2.a(context, wo2.a);
        }
        int i3 = com.google.android.material.R$styleable.NavigationView_itemTextAppearance;
        if (typedArrayObtainStyledAttributes.hasValue(i3) && (resourceId = typedArrayObtainStyledAttributes.getResourceId(i3, 0)) != 0) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, R$styleable.SkinTextAppearance);
            int i4 = R$styleable.SkinTextAppearance_android_textColor;
            if (typedArrayObtainStyledAttributes2.hasValue(i4)) {
                this.z = typedArrayObtainStyledAttributes2.getResourceId(i4, 0);
            }
            typedArrayObtainStyledAttributes2.recycle();
        }
        int i5 = com.google.android.material.R$styleable.NavigationView_itemTextColor;
        if (typedArrayObtainStyledAttributes.hasValue(i5)) {
            this.z = typedArrayObtainStyledAttributes.getResourceId(i5, 0);
        } else {
            this.A = uo2.a(context, wo2.a);
        }
        if (this.z == 0) {
            this.z = uo2.a(context, new int[]{R.attr.textColorPrimary});
        }
        this.y = typedArrayObtainStyledAttributes.getResourceId(com.google.android.material.R$styleable.NavigationView_itemBackground, 0);
        typedArrayObtainStyledAttributes.recycle();
        f();
        g();
        d();
    }
}
