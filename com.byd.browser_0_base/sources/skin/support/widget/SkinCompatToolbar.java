package skin.support.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.R;
import androidx.appcompat.widget.Toolbar;
import defpackage.go2;
import defpackage.po2;
import defpackage.ro2;
import defpackage.vd1;
import defpackage.xo2;
import skin.support.R$styleable;

/* JADX INFO: loaded from: classes3.dex */
public class SkinCompatToolbar extends Toolbar implements ro2 {
    public int b0;
    public int c0;
    public int d0;
    public final go2 e0;

    public SkinCompatToolbar(Context context) {
        this(context, null);
    }

    @Override // defpackage.ro2
    public final void e() {
        go2 go2Var = this.e0;
        if (go2Var != null) {
            go2Var.J();
        }
        int iB = vd1.b(this.b0);
        this.b0 = iB;
        if (iB != 0) {
            setTitleTextColor(po2.a(getContext(), this.b0));
        }
        int iB2 = vd1.b(this.c0);
        this.c0 = iB2;
        if (iB2 != 0) {
            setSubtitleTextColor(po2.a(getContext(), this.c0));
        }
        x();
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        go2 go2Var = this.e0;
        if (go2Var != null) {
            go2Var.L(i);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(int i) {
        super.setNavigationIcon(i);
        this.d0 = i;
        x();
    }

    public final void x() {
        int iB = vd1.b(this.d0);
        this.d0 = iB;
        if (iB != 0) {
            setNavigationIcon(xo2.a(getContext(), this.d0));
        }
    }

    public SkinCompatToolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.toolbarStyle);
    }

    public SkinCompatToolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b0 = 0;
        this.c0 = 0;
        this.d0 = 0;
        go2 go2Var = new go2(this);
        this.e0 = go2Var;
        go2Var.K(attributeSet, i);
        int[] iArr = R.styleable.Toolbar;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, 0);
        this.d0 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.Toolbar_navigationIcon, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.Toolbar_titleTextAppearance, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.Toolbar_subtitleTextAppearance, 0);
        typedArrayObtainStyledAttributes.recycle();
        if (resourceId != 0) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, R$styleable.SkinTextAppearance);
            this.b0 = typedArrayObtainStyledAttributes2.getResourceId(R$styleable.SkinTextAppearance_android_textColor, 0);
            typedArrayObtainStyledAttributes2.recycle();
        }
        if (resourceId2 != 0) {
            TypedArray typedArrayObtainStyledAttributes3 = context.obtainStyledAttributes(resourceId2, R$styleable.SkinTextAppearance);
            this.c0 = typedArrayObtainStyledAttributes3.getResourceId(R$styleable.SkinTextAppearance_android_textColor, 0);
            typedArrayObtainStyledAttributes3.recycle();
        }
        TypedArray typedArrayObtainStyledAttributes4 = context.obtainStyledAttributes(attributeSet, iArr, i, 0);
        int i2 = R.styleable.Toolbar_titleTextColor;
        if (typedArrayObtainStyledAttributes4.hasValue(i2)) {
            this.b0 = typedArrayObtainStyledAttributes4.getResourceId(i2, 0);
        }
        int i3 = R.styleable.Toolbar_subtitleTextColor;
        if (typedArrayObtainStyledAttributes4.hasValue(i3)) {
            this.c0 = typedArrayObtainStyledAttributes4.getResourceId(i3, 0);
        }
        typedArrayObtainStyledAttributes4.recycle();
        int iB = vd1.b(this.b0);
        this.b0 = iB;
        if (iB != 0) {
            setTitleTextColor(po2.a(getContext(), this.b0));
        }
        int iB2 = vd1.b(this.c0);
        this.c0 = iB2;
        if (iB2 != 0) {
            setSubtitleTextColor(po2.a(getContext(), this.c0));
        }
        x();
    }
}
