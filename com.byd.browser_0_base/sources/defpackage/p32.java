package defpackage;

import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: PrefetchEnabledSupplier.java */
/* JADX INFO: loaded from: classes.dex */
public final class p32 implements nw2<Boolean> {
    @Override // defpackage.nw2
    public final Boolean get() {
        hq1.a().getClass();
        return Boolean.valueOf(SharedPreferencesManager.getInstance().readBoolean("Chrome.NTPExploreOfflineCard.HasExploreOfflineContent", false));
    }
}
