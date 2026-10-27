package org.chromium.chrome.browser.password_manager;

import J.N;
import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordStoreBridge {
    public final b<a> a;

    public interface a {
        void a();

        void d();
    }

    public PasswordStoreBridge() {
        N.MgZAkbOC(this);
        this.a = new b<>();
    }

    @CalledByNative
    public static PasswordStoreCredential createPasswordStoreCredential(GURL gurl, String str, String str2) {
        return new PasswordStoreCredential(gurl, str, str2);
    }

    @CalledByNative
    public static void insertCredential(PasswordStoreCredential[] passwordStoreCredentialArr, int i, GURL gurl, String str, String str2) {
        passwordStoreCredentialArr[i] = new PasswordStoreCredential(gurl, str, str2);
    }

    @CalledByNative
    public final void onEditCredential(PasswordStoreCredential passwordStoreCredential) {
        Iterator<a> it = this.a.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).a();
            }
        }
    }

    @CalledByNative
    public final void passwordListAvailable(int i) {
        Iterator<a> it = this.a.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).d();
            }
        }
    }
}
