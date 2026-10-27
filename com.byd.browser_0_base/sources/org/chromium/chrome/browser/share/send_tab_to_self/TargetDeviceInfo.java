package org.chromium.chrome.browser.share.send_tab_to_self;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class TargetDeviceInfo {
    public final String a;
    public final int b;
    public final String c;
    public final long d;

    public TargetDeviceInfo(int i, String str, String str2, long j) {
        this.c = str;
        this.a = str2;
        this.b = i;
        this.d = j;
    }

    @CalledByNative
    public static TargetDeviceInfo build(String str, String str2, int i, long j) {
        return new TargetDeviceInfo(i, str, str2, j);
    }
}
