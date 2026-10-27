package org.chromium.chrome.browser.ui.messages.infobar;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class SimpleConfirmInfoBarBuilder {

    public interface Listener {
        boolean a();

        void b();

        boolean c();
    }

    @CalledByNative
    public static boolean onInfoBarButtonClicked(Listener listener, boolean z) {
        if (listener == null) {
            return false;
        }
        return listener.a();
    }

    @CalledByNative
    public static void onInfoBarDismissed(Listener listener) {
        if (listener != null) {
            listener.b();
        }
    }

    @CalledByNative
    public static boolean onInfoBarLinkClicked(Listener listener) {
        if (listener == null) {
            return false;
        }
        return listener.c();
    }
}
