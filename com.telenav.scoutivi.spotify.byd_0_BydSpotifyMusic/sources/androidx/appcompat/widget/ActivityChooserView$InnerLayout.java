package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
public class ActivityChooserView$InnerLayout extends LinearLayout {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final int[] f837e = {R.attr.background};

    public ActivityChooserView$InnerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        s0 s0VarU = s0.u(context, attributeSet, f837e);
        setBackgroundDrawable(s0VarU.g(0));
        s0VarU.w();
    }
}
