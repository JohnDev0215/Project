package skin.support.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import androidx.cardview.R$color;
import androidx.cardview.R$style;
import androidx.cardview.R$styleable;
import androidx.cardview.widget.CardView;
import defpackage.po2;
import defpackage.ro2;
import defpackage.vd1;

/* JADX INFO: loaded from: classes3.dex */
public class SkinCompatCardView extends CardView implements ro2 {
    public static final int[] n = {R.attr.colorBackground};
    public int l;
    public int m;

    public SkinCompatCardView(Context context) {
        this(context, null);
    }

    @Override // defpackage.ro2
    public final void e() {
        g();
    }

    public final void g() {
        this.m = vd1.b(this.m);
        int iB = vd1.b(this.l);
        this.l = iB;
        if (this.m != 0) {
            setCardBackgroundColor(po2.b(getContext(), this.m));
        } else if (iB != 0) {
            float[] fArr = new float[3];
            Color.colorToHSV(po2.a(getContext(), this.l), fArr);
            setCardBackgroundColor(ColorStateList.valueOf(fArr[2] > 0.5f ? getResources().getColor(R$color.cardview_light_background) : getResources().getColor(R$color.cardview_dark_background)));
        }
    }

    public SkinCompatCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SkinCompatCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = 0;
        this.m = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CardView, i, R$style.CardView);
        int i2 = R$styleable.CardView_cardBackgroundColor;
        if (typedArrayObtainStyledAttributes.hasValue(i2)) {
            this.m = typedArrayObtainStyledAttributes.getResourceId(i2, 0);
        } else {
            TypedArray typedArrayObtainStyledAttributes2 = getContext().obtainStyledAttributes(n);
            this.l = typedArrayObtainStyledAttributes2.getResourceId(0, 0);
            typedArrayObtainStyledAttributes2.recycle();
        }
        typedArrayObtainStyledAttributes.recycle();
        g();
    }
}
