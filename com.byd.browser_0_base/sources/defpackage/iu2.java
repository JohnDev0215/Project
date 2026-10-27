package defpackage;

import java.io.File;
import org.chromium.chrome.browser.download.b;

/* JADX INFO: compiled from: StorageSummaryProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class iu2 extends i9<ma0> {
    public final /* synthetic */ ju2 h;

    public iu2(ju2 ju2Var) {
        this.h = ju2Var;
    }

    @Override // defpackage.i9
    public final ma0 b() {
        File fileB = b.b();
        if (fileB == null) {
            return null;
        }
        return new ma0("", fileB.getAbsolutePath(), fileB.getUsableSpace(), fileB.getTotalSpace(), 0);
    }

    @Override // defpackage.i9
    public final void l(ma0 ma0Var) {
        ju2 ju2Var = this.h;
        ju2Var.g = ma0Var;
        ju2Var.f();
    }
}
