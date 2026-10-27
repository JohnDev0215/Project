package org.chromium.chrome.browser.password_manager;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordStoreCredential {
    public final GURL a;
    public final String b;
    public final String c;

    public PasswordStoreCredential(GURL gurl, String str, String str2) {
        this.a = gurl;
        this.b = str;
        this.c = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PasswordStoreCredential passwordStoreCredential = (PasswordStoreCredential) obj;
        return this.a.equals(passwordStoreCredential.a) && this.b.equals(passwordStoreCredential.b) && this.c.equals(passwordStoreCredential.c);
    }

    @CalledByNative
    public String getPassword() {
        return this.c;
    }

    @CalledByNative
    public GURL getUrl() {
        return this.a;
    }

    @CalledByNative
    public String getUsername() {
        return this.b;
    }

    public final String toString() {
        return "PasswordStoreCredential{url=" + this.a.j() + ", username=" + this.b + ", password=" + this.c + '}';
    }
}
