package com.amazon.avod.media.framework.platform;

import android.os.Process;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidOwnerChecker {
    private static int getProcessUserId() {
        return Process.myUid() / 100000;
    }
}
