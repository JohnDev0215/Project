package skin.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import defpackage.go2;
import defpackage.ro2;
import defpackage.vd1;
import defpackage.xo2;

/* JADX INFO: loaded from: classes3.dex */
public class SkinMaterialCollapsingToolbarLayout extends CollapsingToolbarLayout implements ro2 {
    public int I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f96J;
    public final go2 K;

    public SkinMaterialCollapsingToolbarLayout(Context context) {
        this(context, null);
    }

    @Override // defpackage.ro2
    public final void e() {
        Drawable drawableA;
        Drawable drawableA2;
        int iB = vd1.b(this.I);
        this.I = iB;
        if (iB != 0 && (drawableA2 = xo2.a(getContext(), this.I)) != null) {
            setContentScrim(drawableA2);
        }
        int iB2 = vd1.b(this.f96J);
        this.f96J = iB2;
        if (iB2 != 0 && (drawableA = xo2.a(getContext(), this.f96J)) != null) {
            setStatusBarScrim(drawableA);
        }
        go2 go2Var = this.K;
        if (go2Var != null) {
            go2Var.J();
        }
    }

    public SkinMaterialCollapsingToolbarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SkinMaterialCollapsingToolbarLayout(Context context, AttributeSet attributeSet, int i) {
        Drawable drawableA;
        Drawable drawableA2;
        super(context, attributeSet, i);
        this.I = 0;
        this.f96J = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CollapsingToolbarLayout, i, R$style.Widget_Design_CollapsingToolbar);
        this.I = typedArrayObtainStyledAttributes.getResourceId(R$styleable.CollapsingToolbarLayout_contentScrim, 0);
        this.f96J = typedArrayObtainStyledAttributes.getResourceId(R$styleable.CollapsingToolbarLayout_statusBarScrim, 0);
        typedArrayObtainStyledAttributes.recycle();
        int iB = vd1.b(this.I);
        this.I = iB;
        if (iB != 0 && (drawableA2 = xo2.a(getContext(), this.I)) != null) {
            setContentScrim(drawableA2);
        }
        int iB2 = vd1.b(this.f96J);
        this.f96J = iB2;
        if (iB2 != 0 && (drawableA = xo2.a(getContext(), this.f96J)) != null) {
            setStatusBarScrim(drawableA);
        }
        go2 go2Var = new go2(this);
        this.K = go2Var;
        go2Var.K(attributeSet, 0);
    }
}
