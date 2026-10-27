package org.chromium.components.browser_ui.bottomsheet;

import android.view.View;

/* JADX INFO: compiled from: BottomSheet.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c implements View.OnLayoutChangeListener {
    public final /* synthetic */ BottomSheet e;

    public c(BottomSheet bottomSheet) {
        this.e = bottomSheet;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i4 - i2 == i8 - i6 && i3 - i == i7 - i5) {
            return;
        }
        BottomSheet bottomSheet = this.e;
        if (!bottomSheet.k.d) {
            if (bottomSheet.l != null) {
                return;
            }
        }
        bottomSheet.s(bottomSheet.q, 0, false);
    }
}
