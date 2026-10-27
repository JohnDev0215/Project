package org.chromium.chrome.browser.feedback;

import org.chromium.chrome.browser.feedback.d;

/* JADX INFO: compiled from: ConnectivityTask.java */
/* JADX INFO: loaded from: classes.dex */
public final class e implements Runnable {
    public final /* synthetic */ d.c e;

    public e(d.c cVar) {
        this.e = cVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        d dVar = d.this;
        d.a aVar = dVar.b;
        dVar.b();
        ((c) aVar).b.run();
    }
}
