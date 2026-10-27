package org.chromium.chrome.browser.tab.state;

import J.N;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: loaded from: classes2.dex */
public class LevelDBPersistedDataStorage {
    public long a;
    public final String b = "";

    public LevelDBPersistedDataStorage(Profile profile) {
        N.MkxB9Tdj(this, profile);
    }

    public final String a(String str) {
        return String.format("%s_%s", this.b, str);
    }

    @CalledByNative
    public final void setNativePtr(long j) {
        this.a = j;
    }
}
