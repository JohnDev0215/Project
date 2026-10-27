package a3;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.q;
import androidx.core.widget.c;
import com.google.android.material.internal.j;
import o2.b;
import o2.k;
import o2.l;

/* JADX INFO: compiled from: MaterialRadioButton.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends q {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final int f21k = k.Widget_MaterialComponents_CompoundButton_RadioButton;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final int[][] f22l = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private ColorStateList f23i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private boolean f24j;

    public a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.radioButtonStyle);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.f23i == null) {
            int iC = u2.a.c(this, b.colorControlActivated);
            int iC2 = u2.a.c(this, b.colorOnSurface);
            int iC3 = u2.a.c(this, b.colorSurface);
            int[][] iArr = f22l;
            int[] iArr2 = new int[iArr.length];
            iArr2[0] = u2.a.f(iC3, iC, 1.0f);
            iArr2[1] = u2.a.f(iC3, iC2, 0.54f);
            iArr2[2] = u2.a.f(iC3, iC2, 0.38f);
            iArr2[3] = u2.a.f(iC3, iC2, 0.38f);
            this.f23i = new ColorStateList(iArr, iArr2);
        }
        return this.f23i;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f24j && c.b(this) == null) {
            setUseMaterialThemeColors(true);
        }
    }

    public void setUseMaterialThemeColors(boolean z6) {
        this.f24j = z6;
        if (z6) {
            c.c(this, getMaterialThemeColorsTintList());
        } else {
            c.c(this, null);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public a(Context context, AttributeSet attributeSet, int i7) {
        int i8 = f21k;
        super(f3.a.c(context, attributeSet, i7, i8), attributeSet, i7);
        Context context2 = getContext();
        TypedArray typedArrayH = j.h(context2, attributeSet, l.MaterialRadioButton, i7, i8, new int[0]);
        int i9 = l.MaterialRadioButton_buttonTint;
        if (typedArrayH.hasValue(i9)) {
            c.c(this, b3.c.a(context2, typedArrayH, i9));
        }
        this.f24j = typedArrayH.getBoolean(l.MaterialRadioButton_useMaterialThemeColors, false);
        typedArrayH.recycle();
    }
}
