package org.chromium.chrome.browser.supervised_user;

import android.app.Activity;
import defpackage.nr1;
import defpackage.rh3;
import defpackage.wb0;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.b;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public class ChildAccountService {
    @CalledByNative
    public static void reauthenticateChildAccount(WindowAndroid windowAndroid, String str, long j) {
        Object obj = ThreadUtils.a;
        Activity activity = (Activity) windowAndroid.k().get();
        if (activity == null) {
            PostTask.c(rh3.a, new nr1(1, j));
        } else {
            AccountManagerFacadeProvider.getInstance().f(b.c(str), activity, new wb0(1, j));
        }
    }
}
