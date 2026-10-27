package org.chromium.chrome.browser.touch_to_fill.data;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class Credential {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final boolean e;
    public final boolean f;
    public final long g;

    public Credential(String str, String str2, String str3, String str4, boolean z, boolean z2, long j) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = z;
        this.f = z2;
        this.g = j;
    }

    @CalledByNative
    public String getOriginUrl() {
        return this.d;
    }

    @CalledByNative
    public String getPassword() {
        return this.b;
    }

    @CalledByNative
    public String getUsername() {
        return this.a;
    }

    @CalledByNative
    public boolean isAffiliationBasedMatch() {
        return this.f;
    }

    @CalledByNative
    public boolean isPublicSuffixMatch() {
        return this.e;
    }

    @CalledByNative
    public long lastUsedMsSinceEpoch() {
        return this.g;
    }
}
