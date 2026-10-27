package org.chromium.chrome.browser.findinpage;

/* JADX INFO: compiled from: FindToolbar.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements Runnable {
    public final /* synthetic */ String e;
    public final /* synthetic */ FindToolbar f;

    public a(FindToolbar findToolbar, String str) {
        this.f = findToolbar;
        this.e = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f.f.announceForAccessibility(this.e);
    }
}
