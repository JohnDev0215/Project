package defpackage;

import org.chromium.chrome.browser.customtabs.CustomTabActivity;

/* JADX INFO: compiled from: CustomTabLaunchCauseMetrics.java */
/* JADX INFO: loaded from: classes.dex */
public final class x30 extends s51 {
    public final CustomTabActivity k;

    public x30(CustomTabActivity customTabActivity) {
        super(customTabActivity);
        this.k = customTabActivity;
    }

    @Override // defpackage.s51
    public final int a() {
        return this.k.x2() == 2 ? 2 : 1;
    }
}
