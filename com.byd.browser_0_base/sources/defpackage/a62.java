package defpackage;

import J.N;
import org.chromium.chrome.browser.DevToolsServer;

/* JADX INFO: compiled from: ProcessInitializationHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class a62 implements Runnable {
    public final /* synthetic */ b62 e;

    public a62(b62 b62Var) {
        this.e = b62Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DevToolsServer devToolsServer = new DevToolsServer();
        this.e.getClass();
        N.M0ZKpN7w(devToolsServer, devToolsServer.a, true, true);
    }
}
