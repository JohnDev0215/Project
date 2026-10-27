package org.chromium.chrome.browser.device_dialog;

import defpackage.qp;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.permissions.BluetoothScanningPromptAndroidDelegate;

/* JADX INFO: loaded from: classes.dex */
public class ChromeBluetoothScanningPromptAndroidDelegate implements BluetoothScanningPromptAndroidDelegate {
    @CalledByNative
    public static ChromeBluetoothScanningPromptAndroidDelegate create() {
        return new ChromeBluetoothScanningPromptAndroidDelegate();
    }

    @Override // org.chromium.components.permissions.BluetoothScanningPromptAndroidDelegate
    public final qp a() {
        return new qp(Profile.d());
    }
}
