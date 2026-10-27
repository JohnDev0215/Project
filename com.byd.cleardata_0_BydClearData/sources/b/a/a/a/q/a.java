package b.a.a.a.q;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.widget.c;
import b.a.a.a.b;
import b.a.a.a.k;
import b.a.a.a.l;
import com.google.android.material.internal.i;

/* JADX INFO: loaded from: classes.dex */
public class a extends AppCompatCheckBox {
    private static final int e = k.q;
    private static final int[][] f = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};
    private ColorStateList g;
    private boolean h;

    public a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.f1645d);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public a(Context context, AttributeSet attributeSet, int i) {
        int i2 = e;
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i, i2), attributeSet, i);
        Context context2 = getContext();
        TypedArray typedArrayH = i.h(context2, attributeSet, l.n2, i, i2, new int[0]);
        int i3 = l.o2;
        if (typedArrayH.hasValue(i3)) {
            c.c(this, b.a.a.a.y.c.a(context2, typedArrayH, i3));
        }
        this.h = typedArrayH.getBoolean(l.p2, false);
        typedArrayH.recycle();
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.g == null) {
            int[][] iArr = f;
            int[] iArr2 = new int[iArr.length];
            int iC = b.a.a.a.s.a.c(this, b.g);
            int iC2 = b.a.a.a.s.a.c(this, b.l);
            int iC3 = b.a.a.a.s.a.c(this, b.i);
            iArr2[0] = b.a.a.a.s.a.f(iC2, iC, 1.0f);
            iArr2[1] = b.a.a.a.s.a.f(iC2, iC3, 0.54f);
            iArr2[2] = b.a.a.a.s.a.f(iC2, iC3, 0.38f);
            iArr2[3] = b.a.a.a.s.a.f(iC2, iC3, 0.38f);
            this.g = new ColorStateList(iArr, iArr2);
        }
        return this.g;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.h && c.b(this) == null) {
            setUseMaterialThemeColors(true);
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.h = z;
        c.c(this, z ? getMaterialThemeColorsTintList() : null);
    }
}
