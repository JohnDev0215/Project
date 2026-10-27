package defpackage;

import android.os.Build;
import java.util.Objects;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;

/* JADX INFO: renamed from: dD, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0649dD implements Runnable {
    public final /* synthetic */ SelectionPopupControllerImpl s;

    public RunnableC0649dD(SelectionPopupControllerImpl selectionPopupControllerImpl) {
        this.s = selectionPopupControllerImpl;
    }

    @Override // java.lang.Runnable
    public void run() {
        long jD = this.s.M() ? C1185mt.d() : 2000L;
        SelectionPopupControllerImpl selectionPopupControllerImpl = this.s;
        selectionPopupControllerImpl.t.postDelayed(selectionPopupControllerImpl.C, jD - 1);
        SelectionPopupControllerImpl selectionPopupControllerImpl2 = this.s;
        Objects.requireNonNull(selectionPopupControllerImpl2);
        if (Build.VERSION.SDK_INT < 23 || !selectionPopupControllerImpl2.g()) {
            return;
        }
        C1185mt.g(selectionPopupControllerImpl2.E, jD);
    }
}
