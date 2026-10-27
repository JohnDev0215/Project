package defpackage;

import android.content.ComponentName;
import defpackage.ok2;
import defpackage.uk2;
import org.chromium.components.browser_ui.share.a;

/* JADX INFO: compiled from: ShareServiceImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pk2 implements a.b {
    public final /* synthetic */ ok2.a a;

    public pk2(uk2.f fVar) {
        this.a = fVar;
    }

    @Override // org.chromium.components.browser_ui.share.a.b
    public final void a(ComponentName componentName) {
        oj2.U(0, 3, "WebShare.ShareOutcome");
        ((uk2.f) this.a).c(0);
    }

    @Override // org.chromium.components.browser_ui.share.a.b
    public final void onCancel() {
        oj2.U(2, 3, "WebShare.ShareOutcome");
        ((uk2.f) this.a).c(3);
    }
}
