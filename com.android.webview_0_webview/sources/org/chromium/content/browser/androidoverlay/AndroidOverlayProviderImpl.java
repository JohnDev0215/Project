package org.chromium.content.browser.androidoverlay;

import android.os.Handler;
import android.os.HandlerThread;
import defpackage.AbstractC0599cJ;
import defpackage.AbstractC1815yI;
import defpackage.C0934iJ;
import defpackage.C1044kI;
import defpackage.C1211nI;
import defpackage.InterfaceC0709eI;
import defpackage.InterfaceC0765fI;
import defpackage.InterfaceC1266oI;
import defpackage.RunnableC1642vA;
import defpackage.WJ;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AndroidOverlayProviderImpl implements InterfaceC1266oI {
    public HandlerThread s;
    public Handler t;
    public int u;
    public Runnable v = new RunnableC1642vA(this);

    public static boolean areOverlaysSupported() {
        return true;
    }

    public static void x(AndroidOverlayProviderImpl androidOverlayProviderImpl) {
        Objects.requireNonNull(androidOverlayProviderImpl);
        ThreadUtils.b();
        androidOverlayProviderImpl.u--;
    }

    @Override // defpackage.LI
    public void I(WJ wj) {
    }

    @Override // defpackage.InterfaceC1266oI
    public void b(C0934iJ c0934iJ, InterfaceC0765fI interfaceC0765fI, C1211nI c1211nI) {
        ThreadUtils.b();
        if (this.u >= 1) {
            C1044kI c1044kI = (C1044kI) interfaceC0765fI;
            c1044kI.x();
            c1044kI.close();
            return;
        }
        if (this.s == null) {
            HandlerThread handlerThread = new HandlerThread("AndroidOverlayThread");
            this.s = handlerThread;
            handlerThread.start();
            this.t = new Handler(this.s.getLooper());
        }
        this.u++;
        DialogOverlayImpl dialogOverlayImpl = new DialogOverlayImpl(interfaceC0765fI, c1211nI, this.t, this.v, false);
        AbstractC0599cJ abstractC0599cJ = InterfaceC0709eI.j;
        AbstractC1815yI.a.b(dialogOverlayImpl, c0934iJ);
    }

    @Override // defpackage.InterfaceC0822gJ, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }
}
