package defpackage;

import defpackage.ci2;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;

/* JADX INFO: compiled from: SmartSelectionProvider.java */
/* JADX INFO: loaded from: classes3.dex */
public final class pp2 implements Runnable {
    public final /* synthetic */ qp2 e;

    public pp2(qp2 qp2Var) {
        this.e = qp2Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ((SelectionPopupControllerImpl.b) this.e.a).a(new ci2.a());
    }
}
