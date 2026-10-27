package defpackage;

import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class wc3 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ ToolbarPhone f;

    public /* synthetic */ wc3(ToolbarPhone toolbarPhone, int i) {
        this.e = i;
        this.f = toolbarPhone;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        ToolbarPhone toolbarPhone = this.f;
        switch (i) {
            case 0:
                toolbarPhone.U = true;
                break;
            default:
                int i2 = ToolbarPhone.I0;
                toolbarPhone.i0();
                toolbarPhone.a0();
                break;
        }
    }
}
