package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import defpackage.AbstractC0678dn;
import defpackage.C1082l0;
import defpackage.T;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ActionMenuItemView extends C1082l0 implements View.OnClickListener, T {
    public boolean v;
    public int w;
    public int x;

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        Resources resources = context.getResources();
        this.v = c();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0678dn.c, 0, 0);
        this.w = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        float f = resources.getDisplayMetrics().density;
        setOnClickListener(this);
        this.x = -1;
        setSaveEnabled(false);
    }

    public boolean b() {
        return !TextUtils.isEmpty(getText());
    }

    public final boolean c() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        return i >= 480 || (i >= 640 && configuration.screenHeightDp >= 480) || configuration.orientation == 2;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // android.widget.TextView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.v = c();
        TextUtils.isEmpty(null);
        setText((CharSequence) null);
        throw null;
    }

    @Override // defpackage.C1082l0, android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        if (b() && (i3 = this.x) >= 0) {
            super.setPadding(i3, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int measuredWidth = getMeasuredWidth();
        int iMin = mode == Integer.MIN_VALUE ? Math.min(size, this.w) : this.w;
        if (mode == 1073741824 || this.w <= 0 || measuredWidth >= iMin) {
            return;
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(iMin, 1073741824), i2);
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        throw null;
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        this.x = i;
        super.setPadding(i, i2, i3, i4);
    }
}
