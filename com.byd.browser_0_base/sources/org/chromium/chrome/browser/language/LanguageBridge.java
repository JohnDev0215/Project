package org.chromium.chrome.browser.language;

import android.text.TextUtils;
import defpackage.oj2;
import java.util.ArrayList;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class LanguageBridge {
    @CalledByNative
    public static String[] getULPLanguagesFromDevice(String str) {
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        boolean z = str != null;
        Object obj = ThreadUtils.a;
        oj2.U(1, 4, "LanguageUsage.ULP.Initiation.Status");
        if (z) {
            oj2.U(1, 4, "LanguageUsage.ULP.Initiation.Status.SignedIn");
        } else {
            oj2.U(1, 4, "LanguageUsage.ULP.Initiation.Status.DefaultAccount");
        }
        ArrayList arrayList = new ArrayList();
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
