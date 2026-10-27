package defpackage;

import org.chromium.chrome.browser.flags.CachedFeatureFlags;

/* JADX INFO: compiled from: CachedFlag.java */
/* JADX INFO: loaded from: classes.dex */
public final class sk extends ln0 {
    public final boolean c;

    public sk(String str, boolean z) {
        super(str);
        this.c = z;
    }

    public final boolean a() {
        return CachedFeatureFlags.isEnabled(this.a, this.c);
    }
}
