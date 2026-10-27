package android.telephony.mbms;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.telecom.Logging.Session;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MbmsUtils {
    private static final String LOG_TAG = "MbmsUtils";

    public static boolean isContainedIn(File file, File file2) {
        try {
            return file2.getCanonicalPath().startsWith(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Failed to resolve canonical paths: " + e);
        }
    }

    public static ComponentName toComponentName(ComponentInfo componentInfo) {
        return new ComponentName(componentInfo.packageName, componentInfo.name);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.content.ComponentName getOverrideServiceName(android.content.Context r4, java.lang.String r5) {
        /*
            int r0 = r5.hashCode()
            r1 = -1374878107(0xffffffffae0d0665, float:-3.2065368E-11)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L2a
            r1 = -407466459(0xffffffffe7b68e25, float:-1.7241856E24)
            if (r0 == r1) goto L20
            r1 = 1752202112(0x68707b80, float:4.5425845E24)
            if (r0 == r1) goto L16
            goto L34
        L16:
            java.lang.String r0 = "android.telephony.action.EmbmsGroupCall"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L34
            r5 = r2
            goto L35
        L20:
            java.lang.String r0 = "android.telephony.action.EmbmsDownload"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L34
            r5 = 0
            goto L35
        L2a:
            java.lang.String r0 = "android.telephony.action.EmbmsStreaming"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L34
            r5 = r3
            goto L35
        L34:
            r5 = -1
        L35:
            r0 = 0
            if (r5 == 0) goto L46
            if (r5 == r3) goto L42
            if (r5 == r2) goto L3e
            r5 = r0
            goto L49
        L3e:
            java.lang.String r5 = "mbms-group-call-service-override"
            goto L49
        L42:
            java.lang.String r5 = "mbms-streaming-service-override"
            goto L49
        L46:
            java.lang.String r5 = "mbms-download-service-override"
        L49:
            if (r5 != 0) goto L4c
            return r0
        L4c:
            android.content.pm.PackageManager r1 = r4.getPackageManager()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6d
            java.lang.String r4 = r4.getPackageName()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6d
            r2 = 128(0x80, float:1.8E-43)
            android.content.pm.ApplicationInfo r4 = r1.getApplicationInfo(r4, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6d
            android.os.Bundle r1 = r4.metaData
            if (r1 != 0) goto L5f
            return r0
        L5f:
            android.os.Bundle r4 = r4.metaData
            java.lang.String r4 = r4.getString(r5)
            if (r4 != 0) goto L68
            return r0
        L68:
            android.content.ComponentName r4 = android.content.ComponentName.unflattenFromString(r4)
            return r4
        L6d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.telephony.mbms.MbmsUtils.getOverrideServiceName(android.content.Context, java.lang.String):android.content.ComponentName");
    }

    public static android.content.pm.ServiceInfo getMiddlewareServiceInfo(Context context, String str) {
        List<ResolveInfo> listQueryIntentServices;
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent();
        intent.setAction(str);
        ComponentName overrideServiceName = getOverrideServiceName(context, str);
        if (overrideServiceName == null) {
            listQueryIntentServices = packageManager.queryIntentServices(intent, 1048576);
        } else {
            intent.setComponent(overrideServiceName);
            listQueryIntentServices = packageManager.queryIntentServices(intent, 131072);
        }
        if (listQueryIntentServices == null || listQueryIntentServices.size() == 0) {
            Log.w(LOG_TAG, "No MBMS services found, cannot get service info");
            return null;
        }
        if (listQueryIntentServices.size() > 1) {
            Log.w(LOG_TAG, "More than one MBMS service found, cannot get unique service");
            return null;
        }
        return listQueryIntentServices.get(0).serviceInfo;
    }

    public static int startBinding(Context context, String str, ServiceConnection serviceConnection) {
        Intent intent = new Intent();
        android.content.pm.ServiceInfo middlewareServiceInfo = getMiddlewareServiceInfo(context, str);
        if (middlewareServiceInfo == null) {
            return 1;
        }
        intent.setComponent(toComponentName(middlewareServiceInfo));
        context.bindService(intent, serviceConnection, 1);
        return 0;
    }

    public static File getEmbmsTempFileDirForService(Context context, String str) {
        return new File(MbmsTempFileProvider.getEmbmsTempFileDir(context), str.replaceAll("[^a-zA-Z0-9_]", Session.SESSION_SEPARATION_CHAR_CHILD));
    }
}
