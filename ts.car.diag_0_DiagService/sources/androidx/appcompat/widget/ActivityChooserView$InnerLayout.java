package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.annotation.RestrictTo;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class ActivityChooserView$InnerLayout extends LinearLayout {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int[] f254c = {R.attr.background};

    public ActivityChooserView$InnerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        U uT = U.t(context, attributeSet, f254c);
        setBackgroundDrawable(uT.f(0));
        uT.v();
    }
}
