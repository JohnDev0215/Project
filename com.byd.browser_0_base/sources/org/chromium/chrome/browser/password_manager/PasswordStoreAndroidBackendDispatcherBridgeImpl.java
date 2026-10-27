package org.chromium.chrome.browser.password_manager;

import defpackage.qm0;
import defpackage.rh3;
import defpackage.vd1;
import defpackage.yx1;
import java.util.Optional;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;
import org.chromium.components.signin.b;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordStoreAndroidBackendDispatcherBridgeImpl {
    public final PasswordStoreAndroidBackendReceiverBridgeImpl a;

    public PasswordStoreAndroidBackendDispatcherBridgeImpl(PasswordStoreAndroidBackendReceiverBridgeImpl passwordStoreAndroidBackendReceiverBridgeImpl) {
        this.a = passwordStoreAndroidBackendReceiverBridgeImpl;
    }

    public static void a(String str) {
        if (str == null) {
            Optional.empty();
        } else {
            Optional.of(b.c(str));
        }
    }

    @CalledByNative
    public static boolean canCreateBackend() {
        if (vd1.g == null) {
            vd1.g = new yx1();
        }
        vd1.g.getClass();
        return false;
    }

    @CalledByNative
    public static PasswordStoreAndroidBackendDispatcherBridgeImpl create(PasswordStoreAndroidBackendReceiverBridgeImpl passwordStoreAndroidBackendReceiverBridgeImpl) {
        if (vd1.g == null) {
            vd1.g = new yx1();
        }
        vd1.g.getClass();
        return new PasswordStoreAndroidBackendDispatcherBridgeImpl(passwordStoreAndroidBackendReceiverBridgeImpl);
    }

    @CalledByNative
    public void addLogin(int i, byte[] bArr, String str) {
        a(str);
        throw null;
    }

    @CalledByNative
    public void getAllLogins(int i, String str) {
        a(str);
        throw null;
    }

    @CalledByNative
    public void getAutofillableLogins(int i, String str) {
        a(str);
        throw null;
    }

    @CalledByNative
    public void getLoginsForSignonRealm(int i, String str, String str2) {
        a(str2);
        throw null;
    }

    @CalledByNative
    public void removeLogin(int i, byte[] bArr, String str) {
        a(str);
        throw null;
    }

    @CalledByNative
    public final void showErrorUi() {
        PostTask.c(rh3.c, new qm0(20, this));
    }

    @CalledByNative
    public void updateLogin(int i, byte[] bArr, String str) {
        a(str);
        throw null;
    }
}
