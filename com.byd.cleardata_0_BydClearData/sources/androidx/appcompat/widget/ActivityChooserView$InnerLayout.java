package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
public class ActivityChooserView$InnerLayout extends LinearLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f855b = {R.attr.background};

    public ActivityChooserView$InnerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        v0 v0VarU = v0.u(context, attributeSet, f855b);
        setBackgroundDrawable(v0VarU.g(0));
        v0VarU.w();
    }
}
