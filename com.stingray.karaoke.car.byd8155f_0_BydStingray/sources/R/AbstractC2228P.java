package r;

import android.widget.AbsListView;

/* JADX INFO: renamed from: r.P, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2228P {
    public static boolean a(AbsListView absListView) {
        return absListView.isSelectedChildViewEnabled();
    }

    public static void b(AbsListView absListView, boolean z6) {
        absListView.setSelectedChildViewEnabled(z6);
    }
}
