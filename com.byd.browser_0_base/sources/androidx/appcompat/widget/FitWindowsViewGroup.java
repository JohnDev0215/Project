package androidx.appcompat.widget;

import android.graphics.Rect;

/* JADX INFO: loaded from: classes.dex */
public interface FitWindowsViewGroup {

    public interface OnFitSystemWindowsListener {
        void a(Rect rect);
    }

    void setOnFitSystemWindowsListener(OnFitSystemWindowsListener onFitSystemWindowsListener);
}
