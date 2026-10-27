package defpackage;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import org.chromium.components.permissions.PermissionUtil;
import org.chromium.components.permissions.PermissionsClient;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: renamed from: ky, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1079ky {
    public static boolean a(WindowAndroid windowAndroid, int[] iArr, InterfaceC1023jy interfaceC1023jy, PermissionsClient permissionsClient) {
        if (windowAndroid == null) {
            return false;
        }
        SparseArray sparseArray = new SparseArray();
        for (int i = 0; i < iArr.length; i++) {
            String[] androidPermissionsForContentSetting = PermissionUtil.getAndroidPermissionsForContentSetting(iArr[i]);
            if (androidPermissionsForContentSetting != null) {
                ArrayList arrayList = new ArrayList();
                for (String str : androidPermissionsForContentSetting) {
                    if (!windowAndroid.hasPermission(str)) {
                        arrayList.add(str);
                    }
                }
                if (!arrayList.isEmpty()) {
                    sparseArray.append(iArr[i], (String[]) arrayList.toArray(new String[arrayList.size()]));
                }
            }
        }
        if (sparseArray.size() == 0) {
            return false;
        }
        C0912hy c0912hy = new C0912hy(sparseArray, windowAndroid, permissionsClient, iArr, interfaceC1023jy);
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            Collections.addAll(hashSet, (String[]) sparseArray.valueAt(i2));
        }
        windowAndroid.E((String[]) hashSet.toArray(new String[hashSet.size()]), c0912hy);
        permissionsClient.b();
        return true;
    }
}
