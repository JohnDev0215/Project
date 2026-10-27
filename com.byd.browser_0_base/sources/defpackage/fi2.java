package defpackage;

import android.view.ViewConfiguration;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;

/* JADX INFO: compiled from: SelectionPopupControllerImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class fi2 implements Runnable {
    public final /* synthetic */ SelectionPopupControllerImpl e;

    public fi2(SelectionPopupControllerImpl selectionPopupControllerImpl) {
        this.e = selectionPopupControllerImpl;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z = SelectionPopupControllerImpl.M;
        SelectionPopupControllerImpl selectionPopupControllerImpl = this.e;
        selectionPopupControllerImpl.getClass();
        long defaultActionModeHideDuration = ViewConfiguration.getDefaultActionModeHideDuration();
        selectionPopupControllerImpl.f.postDelayed(selectionPopupControllerImpl.o, defaultActionModeHideDuration - 1);
        if (selectionPopupControllerImpl.m()) {
            selectionPopupControllerImpl.q.hide(defaultActionModeHideDuration);
        }
    }
}
