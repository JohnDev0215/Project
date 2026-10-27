package defpackage;

import org.chromium.chrome.browser.AppHooks;

/* JADX INFO: compiled from: ProcessInitializationHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class x52 implements Runnable {
    @Override // java.lang.Runnable
    public final void run() {
        AppHooks.get().getClass();
    }
}
