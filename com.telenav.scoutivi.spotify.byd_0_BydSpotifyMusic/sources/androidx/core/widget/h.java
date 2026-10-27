package androidx.core.widget;

import android.view.View;
import android.widget.PopupWindow;

/* JADX INFO: compiled from: PopupWindowCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {
    public static void a(PopupWindow popupWindow, boolean z6) {
        popupWindow.setOverlapAnchor(z6);
    }

    public static void b(PopupWindow popupWindow, int i7) {
        popupWindow.setWindowLayoutType(i7);
    }

    public static void c(PopupWindow popupWindow, View view, int i7, int i8, int i9) {
        popupWindow.showAsDropDown(view, i7, i8, i9);
    }
}
