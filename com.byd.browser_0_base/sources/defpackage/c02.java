package defpackage;

import android.content.pm.PackageInfo;
import android.text.TextUtils;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.BuildInfo;

/* JADX INFO: compiled from: PermissionFeedbackSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class c02 implements gm0 {
    @Override // defpackage.gm0
    public final Map<String, String> c() {
        char[] cArr = zt1.a;
        PackageInfo packageInfoC = zt1.c(4096, BuildInfo.a.a.c);
        if (packageInfoC == null || packageInfoC.requestedPermissions == null) {
            return null;
        }
        String strF = "";
        int i = 0;
        String strF2 = "";
        while (true) {
            String[] strArr = packageInfoC.requestedPermissions;
            if (i >= strArr.length) {
                break;
            }
            int i2 = packageInfoC.requestedPermissionsFlags[i];
            String str = strArr[i];
            if ((i2 & 2) != 0) {
                if (!TextUtils.isEmpty(strF)) {
                    strF = gq2.f(strF, ", ");
                }
                strF = gq2.f(strF, str);
            } else {
                if (!TextUtils.isEmpty(strF2)) {
                    strF2 = gq2.f(strF2, ", ");
                }
                strF2 = gq2.f(strF2, str);
            }
            i++;
        }
        Map.Entry[] entryArr = {new AbstractMap.SimpleEntry("Granted Permissions", strF), new AbstractMap.SimpleEntry("Not Granted or Requested Permissions", strF2)};
        HashMap map = new HashMap(2);
        for (int i3 = 0; i3 < 2; i3++) {
            Map.Entry entry = entryArr[i3];
            Object key = entry.getKey();
            Objects.requireNonNull(key);
            Object value = entry.getValue();
            Objects.requireNonNull(value);
            if (map.put(key, value) != null) {
                throw new IllegalArgumentException("duplicate key: " + key);
            }
        }
        return Collections.unmodifiableMap(map);
    }
}
