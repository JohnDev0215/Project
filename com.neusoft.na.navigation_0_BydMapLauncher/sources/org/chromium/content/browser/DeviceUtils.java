package org.chromium.content.browser;

import android.content.Context;
import org.chromium.base.CommandLine;
import org.chromium.content.common.ContentSwitches;
import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: loaded from: classes3.dex */
public class DeviceUtils {
    public static void addDeviceSpecificUserAgentSwitch(Context context) {
        if (DeviceFormFactor.isTablet(context)) {
            return;
        }
        CommandLine.getInstance().appendSwitch(ContentSwitches.USE_MOBILE_UA);
    }
}
