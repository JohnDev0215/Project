package android.net;

import android.Manifest;
import android.annotation.SystemApi;
import android.content.Context;
import android.os.IBinder;
import android.os.ServiceManager;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class NetworkStack {

    @SystemApi
    public static final String PERMISSION_MAINLINE_NETWORK_STACK = "android.permission.MAINLINE_NETWORK_STACK";
    private static volatile IBinder sMockService;

    @SystemApi
    public static IBinder getService() {
        IBinder iBinder = sMockService;
        return iBinder != null ? iBinder : ServiceManager.getService(Context.NETWORK_STACK_SERVICE);
    }

    public static void setServiceForTest(IBinder iBinder) {
        sMockService = iBinder;
    }

    private NetworkStack() {
    }

    public static void checkNetworkStackPermission(Context context) {
        checkNetworkStackPermissionOr(context, new String[0]);
    }

    public static void checkNetworkStackPermissionOr(Context context, String... strArr) {
        ArrayList arrayList = new ArrayList(Arrays.asList(strArr));
        arrayList.add(Manifest.permission.NETWORK_STACK);
        arrayList.add(PERMISSION_MAINLINE_NETWORK_STACK);
        enforceAnyPermissionOf(context, (String[]) arrayList.toArray(new String[0]));
    }

    private static void enforceAnyPermissionOf(Context context, String... strArr) {
        if (!checkAnyPermissionOf(context, strArr)) {
            throw new SecurityException("Requires one of the following permissions: " + String.join(", ", strArr) + ".");
        }
    }

    private static boolean checkAnyPermissionOf(Context context, String... strArr) {
        for (String str : strArr) {
            if (context.checkCallingOrSelfPermission(str) == 0) {
                return true;
            }
        }
        return false;
    }
}
