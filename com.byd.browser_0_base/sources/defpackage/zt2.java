package defpackage;

import org.chromium.chrome.browser.omnibox.status.StatusView;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class zt2 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ StatusView f;

    public /* synthetic */ zt2(StatusView statusView, int i) {
        this.e = i;
        this.f = statusView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        StatusView statusView = this.f;
        switch (i) {
            case 0:
                statusView.j.setVisibility(8);
                statusView.h.setAlpha(1.0f);
                statusView.p = false;
                statusView.b();
                statusView.f();
                break;
            default:
                int i2 = StatusView.E;
                statusView.q = false;
                statusView.b();
                break;
        }
    }
}
