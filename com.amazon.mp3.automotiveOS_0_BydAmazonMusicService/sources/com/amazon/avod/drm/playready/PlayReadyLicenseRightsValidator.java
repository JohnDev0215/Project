package com.amazon.avod.drm.playready;

import com.amazon.avod.util.DLog;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class PlayReadyLicenseRightsValidator {
    /* JADX WARN: Removed duplicated region for block: B:20:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean validate(java.util.Map<java.lang.String, java.lang.Object> r4) {
        /*
            java.lang.String r0 = "Error"
            java.lang.Object r0 = r4.get(r0)
            r1 = 1
            r2 = 0
            if (r0 != 0) goto Lc
            r0 = r1
            goto Ld
        Lc:
            r0 = r2
        Ld:
            java.lang.String r3 = "Called with an error key in the rights map"
            com.google.common.base.Preconditions.checkState(r0, r3)
            boolean r0 = validateConstraintKeys(r4)     // Catch: java.lang.NumberFormatException -> L3a
            if (r0 == 0) goto L37
            boolean r0 = validateConstraintStreamId(r4)     // Catch: java.lang.NumberFormatException -> L3a
            if (r0 == 0) goto L37
            boolean r0 = validateConstraintCategory(r4)     // Catch: java.lang.NumberFormatException -> L3a
            if (r0 == 0) goto L37
            boolean r0 = validateConstraintVagueness(r4)     // Catch: java.lang.NumberFormatException -> L3a
            if (r0 == 0) goto L37
            boolean r0 = validateConstraintDates(r4)     // Catch: java.lang.NumberFormatException -> L3a
            if (r0 == 0) goto L37
            boolean r4 = validateConstraintCounters(r4)     // Catch: java.lang.NumberFormatException -> L3a
            if (r4 == 0) goto L37
            goto L38
        L37:
            r1 = r2
        L38:
            r2 = r1
            goto L48
        L3a:
            r4 = move-exception
            java.lang.Object[] r0 = new java.lang.Object[r1]
            java.lang.String r4 = r4.getMessage()
            r0[r2] = r4
            java.lang.String r4 = "Validation failed parsing string to a number with %s"
            com.amazon.avod.util.DLog.errorf(r4, r0)
        L48:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.drm.playready.PlayReadyLicenseRightsValidator.validate(java.util.Map):boolean");
    }

    private static boolean validateConstraintKeys(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            if (!key.equals("StreamId") && !key.equals("Category") && !key.equals("Vague") && !key.equals("NumDates") && !key.equals("NumCounts") && !key.startsWith("datetime") && !key.startsWith("Count") && !key.startsWith("Error") && !key.equals("CurrTime")) {
                DLog.warnf("PlayReady's getConstraints() returned unknown key %s = \"%s\"", key, value);
            }
        }
        return true;
    }

    private static boolean validateConstraintStreamId(Map<String, Object> map) {
        Object obj = map.get("StreamId");
        if (obj == null) {
            DLog.warnf("PlayReady's getConstraints() did not return the %s key", "StreamId");
        } else {
            int i = Integer.parseInt(obj.toString(), 16);
            if (i != 0) {
                DLog.errorf("PlayReady's getConstraints() returned %s = %s", "StreamId", Integer.valueOf(i));
                return false;
            }
        }
        return true;
    }

    private static boolean validateConstraintCategory(Map<String, Object> map) {
        Object obj = map.get("Category");
        if (obj == null) {
            DLog.errorf("PlayReady's getConstraints() did not return the %s key", "Category");
            return false;
        }
        PlayReadyLicenseType playReadyLicenseTypeFromNumericString = PlayReadyLicenseType.fromNumericString(obj.toString());
        if (playReadyLicenseTypeFromNumericString.isExpected()) {
            return true;
        }
        DLog.errorf("Unexpected DRM license type: %s", playReadyLicenseTypeFromNumericString);
        return false;
    }

    private static boolean validateConstraintVagueness(Map<String, Object> map) {
        Object obj = map.get("Vague");
        if (obj == null) {
            DLog.warnf("PlayReady's getConstraints() did not return the %s key", "Vague");
        } else {
            int i = Integer.parseInt(obj.toString(), 16);
            if (i != 0) {
                DLog.devf("PlayReady's getConstraints() returned %s = %s ( %s%s%s%s)", "Vague", Integer.valueOf(i), (i & 1) != 0 ? "DRM_ASD_VAGUE " : "", (i & 2) != 0 ? "DRM_ASD_OPL " : "", (i & 4) != 0 ? "DRM_ASD_SAP " : "", (i & 8) != 0 ? "DRM_ASD_MU_EXT_REST " : "");
            }
        }
        return true;
    }

    private static boolean validateConstraintDates(Map<String, Object> map) {
        Object obj = map.get("NumDates");
        if (obj == null) {
            DLog.errorf("PlayReady's getConstraints() did not return the %s key", "NumDates");
            return false;
        }
        int i = Integer.parseInt(obj.toString(), 16);
        if (i >= 0 && i <= 4) {
            return true;
        }
        DLog.errorf("PlayReady's getConstraints() returned %s = %s; expected range 0-4", "NumDates", Integer.valueOf(i));
        return false;
    }

    private static boolean validateConstraintCounters(Map<String, Object> map) {
        Object obj = map.get("NumCounts");
        if (obj == null) {
            DLog.errorf("PlayReady's getConstraints() did not return the %s key", "NumCounts");
            return false;
        }
        int i = Integer.parseInt(obj.toString(), 16);
        if (i >= 0 && i <= 4) {
            return true;
        }
        DLog.errorf("PlayReady's getConstraints() returned %s = %s; expected range 0-4", "NumCounts", Integer.valueOf(i));
        return false;
    }
}
