package b.d.a.a.a0;

import a.b.k.l;
import a.b.p.r;
import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import b.d.a.a.b;
import b.d.a.a.j;
import b.d.a.a.z.k;

/* JADX INFO: loaded from: classes.dex */
public class a extends r {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int f1798f = j.Widget_MaterialComponents_CompoundButton_RadioButton;
    public static final int[][] g = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ColorStateList f1799d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1800e;

    /* JADX WARN: Illegal instructions before constructor call */
    public a(Context context, AttributeSet attributeSet) {
        int i = b.radioButtonStyle;
        super(b.d.a.a.i0.a.a.a(context, attributeSet, i, f1798f), attributeSet, i);
        Context context2 = getContext();
        TypedArray typedArrayD = k.d(context2, attributeSet, b.d.a.a.k.MaterialRadioButton, i, f1798f, new int[0]);
        if (typedArrayD.hasValue(b.d.a.a.k.MaterialRadioButton_buttonTint)) {
            setButtonTintList(l.i.y(context2, typedArrayD, b.d.a.a.k.MaterialRadioButton_buttonTint));
        }
        this.f1800e = typedArrayD.getBoolean(b.d.a.a.k.MaterialRadioButton_useMaterialThemeColors, false);
        typedArrayD.recycle();
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.f1799d == null) {
            int iX = l.i.x(this, b.colorControlActivated);
            int iX2 = l.i.x(this, b.colorOnSurface);
            int iX3 = l.i.x(this, b.colorSurface);
            int[] iArr = new int[g.length];
            iArr[0] = l.i.R(iX3, iX, 1.0f);
            iArr[1] = l.i.R(iX3, iX2, 0.54f);
            iArr[2] = l.i.R(iX3, iX2, 0.38f);
            iArr[3] = l.i.R(iX3, iX2, 0.38f);
            this.f1799d = new ColorStateList(g, iArr);
        }
        return this.f1799d;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f1800e && getButtonTintList() == null) {
            setUseMaterialThemeColors(true);
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.f1800e = z;
        setButtonTintList(z ? getMaterialThemeColorsTintList() : null);
    }
}
