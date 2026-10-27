package org.chromium.components.messages;

import defpackage.vg1;
import defpackage.xg1;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes3.dex */
public class MessageDispatcherBridge {
    @CalledByNative
    public static void dismissMessage(MessageWrapper messageWrapper, WindowAndroid windowAndroid, int i) {
        vg1 vg1VarA = xg1.a(windowAndroid);
        if (vg1VarA == null) {
            return;
        }
        vg1VarA.a(i, messageWrapper.f);
    }

    @CalledByNative
    public static boolean enqueueMessage(MessageWrapper messageWrapper, WebContents webContents, int i, boolean z) {
        vg1 vg1VarA = xg1.a(webContents.V());
        if (vg1VarA == null) {
            return false;
        }
        vg1VarA.b(messageWrapper.f, webContents, i, z);
        return true;
    }

    @CalledByNative
    public static boolean enqueueWindowScopedMessage(MessageWrapper messageWrapper, WindowAndroid windowAndroid, boolean z) {
        vg1 vg1VarA = xg1.a(windowAndroid);
        if (vg1VarA == null) {
            return false;
        }
        vg1VarA.c(messageWrapper.f, z);
        return true;
    }
}
