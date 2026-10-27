package org.chromium.components.signin.base;

import android.accounts.Account;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.signin.b;

/* JADX INFO: loaded from: classes3.dex */
public class CoreAccountInfo {
    public final CoreAccountId a;
    public final String b;
    public final String c;

    @CalledByNative
    public CoreAccountInfo(CoreAccountId coreAccountId, String str, String str2) {
        this.a = coreAccountId;
        this.b = str;
        this.c = str2;
    }

    public static Account a(CoreAccountInfo coreAccountInfo) {
        if (coreAccountInfo == null) {
            return null;
        }
        return b.c(coreAccountInfo.getEmail());
    }

    public static String b(CoreAccountInfo coreAccountInfo) {
        if (coreAccountInfo == null) {
            return null;
        }
        return coreAccountInfo.getEmail();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof CoreAccountInfo)) {
            return false;
        }
        CoreAccountInfo coreAccountInfo = (CoreAccountInfo) obj;
        return this.a.equals(coreAccountInfo.a) && this.b.equals(coreAccountInfo.b) && this.c.equals(coreAccountInfo.c);
    }

    @CalledByNative
    public String getEmail() {
        return this.b;
    }

    @CalledByNative
    public String getGaiaId() {
        return this.c;
    }

    @CalledByNative
    public CoreAccountId getId() {
        return this.a;
    }

    public final int hashCode() {
        return this.c.hashCode() + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return String.format("CoreAccountInfo{id[%s], name[%s]}", getId(), getEmail());
    }
}
