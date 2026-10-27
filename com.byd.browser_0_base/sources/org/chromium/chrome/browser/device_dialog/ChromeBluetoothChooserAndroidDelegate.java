package org.chromium.chrome.browser.device_dialog;

import defpackage.qp;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.permissions.BluetoothChooserAndroidDelegate;

/* JADX INFO: loaded from: classes.dex */
public class ChromeBluetoothChooserAndroidDelegate implements BluetoothChooserAndroidDelegate {
    @CalledByNative
    public static ChromeBluetoothChooserAndroidDelegate create() {
        return new ChromeBluetoothChooserAndroidDelegate();
    }

    @Override // org.chromium.components.permissions.BluetoothChooserAndroidDelegate
    public final qp a() {
        return new qp(Profile.d());
    }
}
