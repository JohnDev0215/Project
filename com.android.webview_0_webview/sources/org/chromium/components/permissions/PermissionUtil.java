package org.chromium.components.permissions;

import java.util.Arrays;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class PermissionUtil {
    public static final String[] a = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    public static final String[] b = {"android.permission.CAMERA"};
    public static final String[] c = {"android.permission.RECORD_AUDIO"};
    public static final String[] d = new String[0];

    public static String[] getAndroidPermissionsForContentSetting(int i) {
        if (i == 5) {
            String[] strArr = a;
            return (String[]) Arrays.copyOf(strArr, strArr.length);
        }
        if (i != 58) {
            if (i == 9) {
                String[] strArr2 = c;
                return (String[]) Arrays.copyOf(strArr2, strArr2.length);
            }
            if (i != 10) {
                return d;
            }
        }
        String[] strArr3 = b;
        return (String[]) Arrays.copyOf(strArr3, strArr3.length);
    }
}
