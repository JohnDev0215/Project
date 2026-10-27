package org.chromium.components.signin.base;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class CoreAccountId {
    public final String a;

    @CalledByNative
    public CoreAccountId(String str) {
        this.a = str;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof CoreAccountId) {
            return this.a.equals(((CoreAccountId) obj).a);
        }
        return false;
    }

    @CalledByNative
    public String getId() {
        return this.a;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return this.a;
    }
}
