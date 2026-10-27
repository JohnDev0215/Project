package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import p.v4;

/* JADX INFO: loaded from: classes.dex */
public class ActivityChooserView$InnerLayout extends LinearLayout {
    public static final int[] c = {R.attr.background};

    public ActivityChooserView$InnerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        v4 v4Var = new v4(context, context.obtainStyledAttributes(attributeSet, c));
        setBackgroundDrawable(v4Var.t(0));
        v4Var.L();
    }
}
