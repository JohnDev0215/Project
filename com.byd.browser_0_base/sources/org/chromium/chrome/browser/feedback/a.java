package org.chromium.chrome.browser.feedback;

import org.chromium.chrome.browser.feedback.ConnectivityChecker;
import org.chromium.chrome.browser.feedback.d;

/* JADX INFO: compiled from: ConnectivityChecker.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements Runnable {
    public final /* synthetic */ ConnectivityChecker.a e;
    public final /* synthetic */ int f = 4;

    public a(d.c cVar) {
        this.e = cVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.e.a(this.f);
    }
}
