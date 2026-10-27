package com.android.volley.toolbox;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.android.volley.RequestQueue;
import java.io.File;

/* JADX INFO: loaded from: classes5.dex */
public class Volley {
    public static RequestQueue newRequestQueue(Context context, HttpStack httpStack) {
        File file = new File(context.getCacheDir(), "volley");
        try {
            String packageName = context.getPackageName();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            StringBuilder sb = new StringBuilder();
            sb.append(packageName);
            sb.append("/");
            sb.append(packageInfo.versionCode);
        } catch (PackageManager.NameNotFoundException unused) {
        }
        if (httpStack == null) {
            httpStack = new HurlStack();
        }
        RequestQueue requestQueue = new RequestQueue(new DiskBasedCache(file), new BasicNetwork(httpStack));
        requestQueue.start();
        return requestQueue;
    }

    public static RequestQueue newRequestQueue(Context context) {
        return newRequestQueue(context, null);
    }
}
