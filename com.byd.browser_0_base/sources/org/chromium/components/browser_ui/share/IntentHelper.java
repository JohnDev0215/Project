package org.chromium.components.browser_ui.share;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.TextUtils;
import defpackage.xz;
import java.io.File;
import org.chromium.base.ContentUriUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public abstract class IntentHelper {
    @CalledByNative
    public static void sendEmail(String str, String str2, String str3, String str4, String str5) {
        Uri uriFromFile;
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("message/rfc822");
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("android.intent.extra.EMAIL", new String[]{str});
        }
        intent.putExtra("android.intent.extra.SUBJECT", str2);
        intent.putExtra("android.intent.extra.TEXT", Html.fromHtml(str3));
        if (!TextUtils.isEmpty(str5)) {
            File file = new File(str5);
            try {
                uriFromFile = ContentUriUtils.b(file);
            } catch (IllegalArgumentException unused) {
                uriFromFile = Uri.fromFile(file);
            }
            intent.putExtra("android.intent.extra.STREAM", uriFromFile);
        }
        try {
            Intent intentCreateChooser = Intent.createChooser(intent, str4);
            intentCreateChooser.addFlags(268435456);
            xz.a.startActivity(intentCreateChooser);
        } catch (ActivityNotFoundException unused2) {
        }
    }
}
