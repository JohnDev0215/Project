package defpackage;

import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.ui.base.EventForwarder;

/* JADX INFO: compiled from: JoystickHandler.java */
/* JADX INFO: loaded from: classes3.dex */
public final class u31 implements kx0, lk3 {
    public final EventForwarder e;
    public boolean f = true;

    public u31(WebContentsImpl webContentsImpl) {
        this.e = webContentsImpl.H();
        ImeAdapterImpl.b(webContentsImpl).m.add(this);
    }

    @Override // defpackage.kx0
    public final void q(boolean z, boolean z2) {
        this.f = !z;
    }
}
