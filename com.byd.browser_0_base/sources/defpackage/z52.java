package defpackage;

import org.chromium.chrome.browser.AppHooks;

/* JADX INFO: compiled from: ProcessInitializationHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class z52 implements Runnable {
    @Override // java.lang.Runnable
    public final void run() {
        if (ac2.a == null) {
            AppHooks.get().getClass();
            ac2.a = new ac2();
        }
    }
}
