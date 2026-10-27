package defpackage;

import android.view.View;
import org.chromium.components.webapps.R$string;
import org.chromium.components.webapps.bottomsheet.PwaBottomSheetController;
import org.chromium.components.webapps.bottomsheet.a;

/* JADX INFO: compiled from: PwaInstallBottomSheetContent.java */
/* JADX INFO: loaded from: classes3.dex */
public final class u72 implements ii {
    public final a e;
    public final y3 f;
    public int g = 1;

    public u72(a aVar, PwaBottomSheetController pwaBottomSheetController) {
        this.e = aVar;
        this.f = pwaBottomSheetController;
    }

    @Override // defpackage.ii
    public final View d() {
        return this.e.c;
    }

    @Override // defpackage.ii
    public final void destroy() {
        this.f.c();
    }

    @Override // defpackage.ii
    public final int e() {
        return this.g;
    }

    @Override // defpackage.ii
    public final View g() {
        return this.e.b;
    }

    @Override // defpackage.ii
    public final int h() {
        return R$string.pwa_install_bottom_sheet_accessibility;
    }

    @Override // defpackage.ii
    public final int i() {
        return R$string.pwa_install_bottom_sheet_accessibility;
    }

    @Override // defpackage.ii
    public final int m() {
        return 0;
    }

    @Override // defpackage.ii
    public final int p() {
        return R$string.pwa_install_bottom_sheet_accessibility;
    }

    @Override // defpackage.ii
    public final int r() {
        return R$string.pwa_install_bottom_sheet_accessibility;
    }

    @Override // defpackage.ii
    public final float s() {
        return -1.0f;
    }

    @Override // defpackage.ii
    public final boolean u() {
        return true;
    }
}
