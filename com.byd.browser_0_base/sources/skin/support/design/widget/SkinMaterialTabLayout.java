package skin.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.tabs.TabLayout;
import defpackage.po2;
import defpackage.ro2;
import defpackage.vd1;

/* JADX INFO: loaded from: classes3.dex */
public class SkinMaterialTabLayout extends TabLayout implements ro2 {
    public int a0;
    public int b0;
    public int c0;

    public SkinMaterialTabLayout(Context context) {
        this(context, null);
    }

    @Override // defpackage.ro2
    public final void e() {
        int iB = vd1.b(this.a0);
        this.a0 = iB;
        if (iB != 0) {
            setSelectedTabIndicatorColor(po2.a(getContext(), this.a0));
        }
        int iB2 = vd1.b(this.b0);
        this.b0 = iB2;
        if (iB2 != 0) {
            setTabTextColors(po2.b(getContext(), this.b0));
        }
        int iB3 = vd1.b(this.c0);
        this.c0 = iB3;
        if (iB3 != 0) {
            int iA = po2.a(getContext(), this.c0);
            if (getTabTextColors() != null) {
                setTabTextColors(new ColorStateList(new int[][]{HorizontalScrollView.SELECTED_STATE_SET, HorizontalScrollView.EMPTY_STATE_SET}, new int[]{iA, getTabTextColors().getDefaultColor()}));
            }
        }
    }

    public SkinMaterialTabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SkinMaterialTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a0 = 0;
        this.b0 = 0;
        this.c0 = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.TabLayout, i, 0);
        this.a0 = typedArrayObtainStyledAttributes.getResourceId(R$styleable.TabLayout_tabIndicatorColor, 0);
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(typedArrayObtainStyledAttributes.getResourceId(R$styleable.TabLayout_tabTextAppearance, R$style.TextAppearance_Design_Tab), skin.support.R$styleable.SkinTextAppearance);
        try {
            this.b0 = typedArrayObtainStyledAttributes2.getResourceId(skin.support.R$styleable.SkinTextAppearance_android_textColor, 0);
            typedArrayObtainStyledAttributes2.recycle();
            int i2 = R$styleable.TabLayout_tabTextColor;
            if (typedArrayObtainStyledAttributes.hasValue(i2)) {
                this.b0 = typedArrayObtainStyledAttributes.getResourceId(i2, 0);
            }
            int i3 = R$styleable.TabLayout_tabSelectedTextColor;
            if (typedArrayObtainStyledAttributes.hasValue(i3)) {
                this.c0 = typedArrayObtainStyledAttributes.getResourceId(i3, 0);
            }
            typedArrayObtainStyledAttributes.recycle();
            e();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes2.recycle();
            throw th;
        }
    }
}
