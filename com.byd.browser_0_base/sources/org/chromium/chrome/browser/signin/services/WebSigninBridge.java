package org.chromium.chrome.browser.signin.services;

import J.N;
import defpackage.xr3;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.signin.base.CoreAccountInfo;
import org.chromium.components.signin.base.GoogleServiceAuthError;

/* JADX INFO: loaded from: classes2.dex */
public class WebSigninBridge {

    public interface Listener {
        void a(GoogleServiceAuthError googleServiceAuthError);

        void b();
    }

    public WebSigninBridge(Profile profile, CoreAccountInfo coreAccountInfo, xr3 xr3Var) {
        N.Mbi2nhML(profile, coreAccountInfo, xr3Var);
    }

    @CalledByNative
    public static void onSigninFailed(Listener listener, GoogleServiceAuthError googleServiceAuthError) {
        listener.a(googleServiceAuthError);
    }

    @CalledByNative
    public static void onSigninSucceeded(Listener listener) {
        listener.b();
    }
}
