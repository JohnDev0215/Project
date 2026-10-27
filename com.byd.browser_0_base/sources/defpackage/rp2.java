package defpackage;

import android.content.Context;
import android.util.DisplayMetrics;
import androidx.recyclerview.widget.LinearSmoothScroller;

/* JADX INFO: compiled from: SmoothCalendarLayoutManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class rp2 extends LinearSmoothScroller {
    public rp2(Context context) {
        super(context);
    }

    @Override // androidx.recyclerview.widget.LinearSmoothScroller
    public final float f(DisplayMetrics displayMetrics) {
        return 100.0f / displayMetrics.densityDpi;
    }
}
