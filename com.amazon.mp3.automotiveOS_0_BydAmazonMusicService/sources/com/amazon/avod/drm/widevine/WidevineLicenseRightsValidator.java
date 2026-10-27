package com.amazon.avod.drm.widevine;

import com.amazon.avod.util.DLog;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class WidevineLicenseRightsValidator {
    public static boolean validate(Map<String, Object> map) {
        return validateExpectedKeys(map);
    }

    private static boolean validateExpectedKeys(Map<String, Object> map) {
        if (map.size() == 0) {
            DLog.errorf("Widevine rights map was empty");
            return false;
        }
        if (map.get("PlayAllowed") == null) {
            DLog.errorf("Widevine rights map did not contain the %s key", "PlayAllowed");
            return false;
        }
        if (map.get("PersistAllowed") == null) {
            DLog.errorf("Widevine rights map did not contain the %s key", "PersistAllowed");
            return false;
        }
        if (map.get("RenewAllowed") == null) {
            DLog.errorf("Widevine rights map did not contain the %s key", "RenewAllowed");
            return false;
        }
        if (map.get("LicenseType") == null) {
            DLog.errorf("Widevine rights map did not contain the %s key", "LicenseType");
            return false;
        }
        if (map.get("PlaybackDurationRemaining") == null) {
            DLog.errorf("Widevine rights map did not contain the %s key", "PlaybackDurationRemaining");
            return false;
        }
        if (map.get("LicenseDurationRemaining") == null) {
            DLog.errorf("Widevine rights map did not contain the %s key", "LicenseDurationRemaining");
            return false;
        }
        if (map.get("RenewalServerUrl") == null) {
            DLog.warnf("Widevine rights map did not contain the %s key", "RenewalServerUrl");
        }
        return true;
    }
}
