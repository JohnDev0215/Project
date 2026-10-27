package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.AbstractC0245Nm;
import defpackage.AbstractC0371Um;
import defpackage.AbstractC0678dn;
import defpackage.AbstractC1251o3;
import defpackage.L0;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements AbsListView.SelectionBoundsAdjuster {
    public TextView s;
    public ImageView t;
    public ImageView u;
    public Drawable v;
    public int w;
    public Context x;
    public boolean y;
    public Drawable z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int i = AbstractC0245Nm.Z4;
        L0 l0L = L0.l(getContext(), attributeSet, AbstractC0678dn.g0, i, 0);
        this.v = l0L.f(7);
        this.w = l0L.i(3, -1);
        this.y = l0L.a(0, false);
        this.x = context;
        this.z = l0L.f(1);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{R.attr.divider}, AbstractC0245Nm.M2, 0);
        typedArrayObtainStyledAttributes.hasValue(0);
        l0L.b.recycle();
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.u;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.u.getLayoutParams();
        rect.top = this.u.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin + rect.top;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        AbstractC1251o3.C(this, this.v);
        TextView textView = (TextView) findViewById(AbstractC0371Um.S4);
        this.s = textView;
        int i = this.w;
        if (i != -1) {
            textView.setTextAppearance(this.x, i);
        }
        ImageView imageView = (ImageView) findViewById(AbstractC0371Um.z4);
        this.t = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.z);
        }
        this.u = (ImageView) findViewById(AbstractC0371Um.K1);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }
}
