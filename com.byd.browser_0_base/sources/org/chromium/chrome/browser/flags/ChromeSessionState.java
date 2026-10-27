package org.chromium.chrome.browser.flags;

import android.os.UserManager;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class ChromeSessionState {
    @CalledByNative
    public static int getMultipleUserProfilesState() {
        return ((UserManager) xz.a.getSystemService("user")).getUserProfiles().size() > 1 ? 2 : 1;
    }
}
