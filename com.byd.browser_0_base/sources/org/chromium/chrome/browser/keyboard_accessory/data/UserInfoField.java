package org.chromium.chrome.browser.keyboard_accessory.data;

import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public final class UserInfoField {
    public String a;
    public final String b;
    public final String c;
    public final String d;
    public final boolean e;
    public final Callback<UserInfoField> f;

    public UserInfoField() {
        throw null;
    }

    public UserInfoField(String str, String str2, String str3, String str4, boolean z, Callback callback) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = z;
        this.f = callback;
    }

    @CalledByNative
    public String getA11yDescription() {
        return this.c;
    }

    @CalledByNative
    public String getDisplayText() {
        return this.a;
    }

    @CalledByNative
    public String getId() {
        return this.d;
    }

    @CalledByNative
    public String getTextToFill() {
        return this.b;
    }

    @CalledByNative
    public boolean isObfuscated() {
        return this.e;
    }

    @CalledByNative
    public boolean isSelectable() {
        return this.f != null;
    }
}
