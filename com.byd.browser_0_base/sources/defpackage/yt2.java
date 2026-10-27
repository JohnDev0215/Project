package defpackage;

import org.chromium.chrome.browser.omnibox.status.StatusView;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class yt2 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ StatusView f;

    public /* synthetic */ yt2(StatusView statusView, int i) {
        this.e = i;
        this.f = statusView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        StatusView statusView = this.f;
        switch (i) {
            case 0:
                int i2 = StatusView.E;
                statusView.getClass();
                ao3.e(statusView, "StatusView.setStatusIconShown Runnable");
                break;
            default:
                int i3 = StatusView.E;
                statusView.q = false;
                statusView.b();
                break;
        }
    }
}
