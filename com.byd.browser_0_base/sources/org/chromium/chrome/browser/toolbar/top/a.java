package org.chromium.chrome.browser.toolbar.top;

import android.view.View;
import android.widget.ListPopupWindow;

/* JADX INFO: compiled from: NavigationPopup.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a implements View.OnLayoutChangeListener {
    public final /* synthetic */ b e;

    public a(b bVar) {
        this.e = bVar;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        ListPopupWindow listPopupWindow = this.e.g;
        int width = (listPopupWindow.getAnchorView().getWidth() - listPopupWindow.getWidth()) / 2;
        if (width > 0) {
            listPopupWindow.setHorizontalOffset(width);
        }
        listPopupWindow.show();
    }
}
