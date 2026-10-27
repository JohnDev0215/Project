package skin.support.design.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import defpackage.go2;
import defpackage.po2;
import defpackage.ro2;
import defpackage.vd1;

/* JADX INFO: loaded from: classes3.dex */
public class SkinMaterialBottomNavigationView extends BottomNavigationView implements ro2 {
    public static final int[] o = {-16842910};
    public static final int[] p = {R.attr.state_checked};
    public final go2 k;
    public int l;
    public int m;
    public int n;

    public SkinMaterialBottomNavigationView(Context context) {
        this(context, null);
    }

    public final void b() {
        int iB = vd1.b(this.m);
        this.m = iB;
        if (iB != 0) {
            setItemIconTintList(po2.b(getContext(), this.m));
            return;
        }
        int iB2 = vd1.b(this.n);
        this.n = iB2;
        if (iB2 != 0) {
            setItemIconTintList(d(R.attr.textColorSecondary));
        }
    }

    public final void c() {
        int iB = vd1.b(this.l);
        this.l = iB;
        if (iB != 0) {
            setItemTextColor(po2.b(getContext(), this.l));
            return;
        }
        int iB2 = vd1.b(this.n);
        this.n = iB2;
        if (iB2 != 0) {
            setItemTextColor(d(R.attr.textColorSecondary));
        }
    }

    public final ColorStateList d(int i) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(R.attr.textColorSecondary, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateListB = po2.b(getContext(), typedValue.resourceId);
        int iA = po2.a(getContext(), this.n);
        int defaultColor = colorStateListB.getDefaultColor();
        int[] iArr = o;
        return new ColorStateList(new int[][]{iArr, p, FrameLayout.EMPTY_STATE_SET}, new int[]{colorStateListB.getColorForState(iArr, defaultColor), iA, defaultColor});
    }

    @Override // defpackage.ro2
    public final void e() {
        go2 go2Var = this.k;
        if (go2Var != null) {
            go2Var.J();
        }
        b();
        c();
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        go2 go2Var = this.k;
        if (go2Var != null) {
            go2Var.L(i);
        }
    }

    public SkinMaterialBottomNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SkinMaterialBottomNavigationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = 0;
        this.m = 0;
        this.n = 0;
        go2 go2Var = new go2(this);
        this.k = go2Var;
        go2Var.K(attributeSet, i);
        context.obtainStyledAttributes(attributeSet, R$styleable.BottomNavigationView, i, R$style.Widget_Design_BottomNavigationView).recycle();
        b();
        c();
    }
}
