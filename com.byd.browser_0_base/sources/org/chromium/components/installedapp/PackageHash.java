package org.chromium.components.installedapp;

import android.util.SparseArray;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.BrowserContextHandle;

/* JADX INFO: loaded from: classes2.dex */
public class PackageHash {
    public static SparseArray<byte[]> a;

    @CalledByNative
    public static void onCookiesDeleted(BrowserContextHandle browserContextHandle) {
        SparseArray<byte[]> sparseArray = a;
        if (sparseArray != null) {
            sparseArray.delete(browserContextHandle.hashCode());
        }
    }
}
