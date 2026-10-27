package org.chromium.chrome.browser.media;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import defpackage.oe1;
import defpackage.oj2;
import defpackage.q11;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class MediaLauncherActivity extends Activity {
    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        String mimeTypeFromExtension;
        int i;
        super.onCreate(bundle);
        Uri data = q11.u(getIntent()).getData();
        boolean z = false;
        if (data.getScheme().equals("content")) {
            mimeTypeFromExtension = getContentResolver().getType(data);
        } else {
            String string = data.toString();
            int length = string.length();
            int iLastIndexOf = string.lastIndexOf(35, length);
            if (iLastIndexOf >= 0) {
                length = iLastIndexOf;
            }
            int iLastIndexOf2 = string.lastIndexOf(63, length);
            if (iLastIndexOf2 >= 0) {
                length = iLastIndexOf2;
            }
            int iLastIndexOf3 = string.lastIndexOf(46, length);
            if (iLastIndexOf3 >= 0) {
                length = iLastIndexOf3;
            }
            mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(string.substring(0, length).replaceAll("['$!]", "") + string.substring(length)).toLowerCase(Locale.ROOT));
        }
        if (!TextUtils.isEmpty(mimeTypeFromExtension)) {
            String[] strArrSplit = mimeTypeFromExtension.toLowerCase(Locale.getDefault()).split("/");
            i = 2;
            if (strArrSplit.length == 2) {
                String str = strArrSplit[0];
                str.getClass();
                switch (str) {
                    case "audio":
                        i = 0;
                        break;
                    case "image":
                        i = 1;
                        break;
                    case "video":
                        break;
                    default:
                        i = 3;
                        break;
                }
            } else {
                i = 3;
            }
        } else {
            i = 3;
        }
        oj2.U(i, 4, "MediaLauncherActivity.MediaType");
        if (i == 3) {
            finish();
            return;
        }
        Intent intentB = oe1.b(data, data, mimeTypeFromExtension, false, this);
        intentB.addFlags(1);
        intentB.putExtra("org.chromium.chrome.browser.customtabs.EXTRA_BROWSER_LAUNCH_SOURCE", 3);
        try {
            startActivity(intentB);
            z = true;
        } catch (SecurityException e) {
            Log.w("cr_MediaLauncher", "Cannot open content URI: " + data.toString(), e);
        }
        oj2.O("MediaLauncherActivity.LaunchResult", z);
        finish();
    }
}
