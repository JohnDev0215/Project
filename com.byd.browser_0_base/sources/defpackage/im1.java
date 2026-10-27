package defpackage;

import android.view.View;
import org.chromium.chrome.browser.ntp.h;

/* JADX INFO: compiled from: NewTabPage.java */
/* JADX INFO: loaded from: classes2.dex */
public final class im1 implements View.OnAttachStateChangeListener {
    public final /* synthetic */ h e;

    public im1(h hVar) {
        this.e = hVar;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        h hVar = this.e;
        hVar.x();
        hVar.getView().removeOnAttachStateChangeListener(this);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
    }
}
