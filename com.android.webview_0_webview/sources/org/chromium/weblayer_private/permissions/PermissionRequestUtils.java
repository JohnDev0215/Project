package org.chromium.weblayer_private.permissions;

import J.N;
import defpackage.AbstractC1079ky;
import defpackage.C1110lS;
import org.chromium.components.permissions.PermissionsClient;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class PermissionRequestUtils {
    public static void requestPermission(WindowAndroid windowAndroid, long j, int i) {
        if (AbstractC1079ky.a(windowAndroid, new int[]{i}, new C1110lS(j), new PermissionsClient())) {
            return;
        }
        N.Ms$UzmXN(j, false);
    }
}
