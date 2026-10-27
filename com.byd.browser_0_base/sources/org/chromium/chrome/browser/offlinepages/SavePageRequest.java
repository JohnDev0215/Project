package org.chromium.chrome.browser.offlinepages;

import defpackage.ar1;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class SavePageRequest {
    @CalledByNative
    public static SavePageRequest create(int i, long j, String str, String str2, String str3, String str4, int i2) {
        new ar1(str4);
        return new SavePageRequest();
    }
}
