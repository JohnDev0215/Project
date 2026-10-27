package org.chromium.chrome.browser.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import defpackage.fa1;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class PlatformUtil {
    @CalledByNative
    public static void launchExternalProtocol(String str) {
        Context context = xz.a;
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.setFlags(268435456);
        intent.addCategory("android.intent.category.BROWSABLE");
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            fa1.c("PlatformUtil", "cannot find activity to launch %s", str, e);
        }
    }
}
