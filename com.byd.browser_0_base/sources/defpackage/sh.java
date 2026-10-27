package defpackage;

import J.N;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: BooleanCachedFieldTrialParameter.java */
/* JADX INFO: loaded from: classes.dex */
public final class sh extends rk {
    public final boolean c;

    public sh(String str, String str2, boolean z) {
        super(str, str2);
        this.c = z;
    }

    @Override // defpackage.rk
    public final void a() {
        sk skVar = bq.a;
        SharedPreferencesManager.getInstance().l(b(), N.M6bsIDpc(this.a, this.b, this.c));
    }

    public final boolean c() {
        return CachedFeatureFlags.b(b(), this.c);
    }
}
