package defpackage;

import android.content.Intent;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: renamed from: fD, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0760fD implements InterfaceC0492aO {
    public final /* synthetic */ SelectionPopupControllerImpl a;

    public C0760fD(SelectionPopupControllerImpl selectionPopupControllerImpl) {
        this.a = selectionPopupControllerImpl;
    }

    @Override // defpackage.InterfaceC0492aO
    public void a(WindowAndroid windowAndroid, int i, Intent intent) {
        this.a.H(i, intent);
    }
}
