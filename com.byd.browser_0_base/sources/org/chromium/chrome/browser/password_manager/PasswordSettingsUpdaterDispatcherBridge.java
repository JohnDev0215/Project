package org.chromium.chrome.browser.password_manager;

import android.os.SystemClock;
import defpackage.ux1;
import defpackage.vd1;
import java.util.Optional;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.signin.b;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordSettingsUpdaterDispatcherBridge {
    public final PasswordSettingsUpdaterReceiverBridge a;

    public PasswordSettingsUpdaterDispatcherBridge(PasswordSettingsUpdaterReceiverBridge passwordSettingsUpdaterReceiverBridge) {
        Object obj = ThreadUtils.a;
        this.a = passwordSettingsUpdaterReceiverBridge;
    }

    public static void a(String str) {
        if (str == null) {
            Optional.empty();
        } else {
            Optional.of(b.c(str));
        }
    }

    @CalledByNative
    public static boolean canCreateAccessor() {
        if (vd1.f == null) {
            vd1.f = new ux1();
        }
        vd1.f.getClass();
        return false;
    }

    @CalledByNative
    public static PasswordSettingsUpdaterDispatcherBridge create(PasswordSettingsUpdaterReceiverBridge passwordSettingsUpdaterReceiverBridge) {
        if (vd1.f == null) {
            vd1.f = new ux1();
        }
        vd1.f.getClass();
        return new PasswordSettingsUpdaterDispatcherBridge(passwordSettingsUpdaterReceiverBridge);
    }

    @CalledByNative
    public void getSettingValue(String str, int i) {
        Object obj = ThreadUtils.a;
        SystemClock.elapsedRealtime();
        if (i == 0) {
            a(str);
            throw null;
        }
        if (i != 1) {
            return;
        }
        a(str);
        throw null;
    }

    @CalledByNative
    public void setSettingValue(String str, int i, boolean z) {
        Object obj = ThreadUtils.a;
        SystemClock.elapsedRealtime();
        if (i == 0) {
            a(str);
            throw null;
        }
        if (i != 1) {
            return;
        }
        a(str);
        throw null;
    }
}
