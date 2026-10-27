package org.chromium.chrome.browser.sharing;

import java.util.ArrayList;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class SharingServiceProxy {
    public static SharingServiceProxy a;
    public static long b;

    public static class a {
    }

    @CalledByNative
    public static void createDeviceInfoAndAppendToList(ArrayList<a> arrayList, String str, String str2, int i, long j) {
        arrayList.add(new a());
    }

    @CalledByNative
    public static void onProxyCreated(long j) {
        b = j;
    }

    @CalledByNative
    public static void onProxyDestroyed() {
        b = 0L;
    }
}
