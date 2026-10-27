package org.chromium.chrome.browser.sync;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.signin.base.CoreAccountInfo;

/* JADX INFO: loaded from: classes2.dex */
public class TrustedVaultClient {
    @CalledByNative
    public static void registerNative(long j) {
    }

    @CalledByNative
    public static void unregisterNative(long j) {
    }

    @CalledByNative
    public static void fetchKeys(long j, int i, CoreAccountInfo coreAccountInfo) {
    }

    @CalledByNative
    public static void getIsRecoverabilityDegraded(long j, int i, CoreAccountInfo coreAccountInfo) {
    }

    @CalledByNative
    public static void markLocalKeysAsStale(long j, int i, CoreAccountInfo coreAccountInfo) {
    }

    @CalledByNative
    public static void addTrustedRecoveryMethod(long j, int i, CoreAccountInfo coreAccountInfo, byte[] bArr, int i2) {
    }
}
