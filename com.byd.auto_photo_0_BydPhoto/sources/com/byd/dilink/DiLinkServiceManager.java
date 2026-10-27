package com.byd.dilink;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.compat.DiLinkSpiRegisterEntry;
import com.byd.dilink.log.DiLinkSDKLog;
import com.byd.spi.ServiceManager;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class DiLinkServiceManager {
    private static final String TAG = "DiLinkServiceManager";
    private static Context sApplicationContext;

    private static class SingletonHolder {
        public static DiLinkServiceManager instance = new DiLinkServiceManager();

        private SingletonHolder() {
        }
    }

    public static Context getApplicationContext() {
        return sApplicationContext;
    }

    public static void init(Context context) {
        if (sApplicationContext != null) {
            return;
        }
        if (context == null) {
            throw new IllegalArgumentException("context for DiLinkServiceManager is null");
        }
        DiLinkSDKLog.d("DiLinkServiceManager init start!");
        DiLinkSDKLog.d("DILINK_SDK_VERSION: 0.0.1-rc.2");
        if (context.getApplicationContext() != null) {
            sApplicationContext = context.getApplicationContext();
        } else {
            sApplicationContext = context;
        }
        DiLink.getVersionName(context);
        DiLinkSDKLog.d("start load com.byd.spi.register.*");
        DiLinkSpiRegisterEntry.registerAll();
        DiLinkSDKLog.d("invoke DiLinkSpiRegisterEntry.registerAll success!");
        DiLinkSDKLog.d("DiLinkServiceManager init finish!");
    }

    private <T> T searchMatchedInstance(List<T> list) {
        for (T t : list) {
            Platform platform = (Platform) t.getClass().getAnnotation(Platform.class);
            if (platform != null) {
                String[] strArrVersions = platform.versions();
                boolean zIsDecoupled = platform.isDecoupled();
                for (String str : strArrVersions) {
                    if (DiLink.isPlatform(str, zIsDecoupled)) {
                        DiLinkSDKLog.d("Found Matched Instance：UI=" + str + ", isDecoupled=" + zIsDecoupled);
                        return t;
                    }
                }
            }
        }
        DiLinkSDKLog.w("Can Not Found Matched Instance For: " + DiLink.getVersionName(sApplicationContext));
        return null;
    }

    public static DiLinkServiceManager with() {
        return SingletonHolder.instance;
    }

    public <T> T getService(@NonNull Class<T> cls) {
        if (sApplicationContext == null) {
            throw new IllegalStateException("ApplicationContext is NULL, must call DiLinkServiceManager.init first!");
        }
        List<T> services = ServiceManager.with().getServices(sApplicationContext, cls);
        if (services == null) {
            DiLinkSDKLog.e("ServiceManager getServices got NULL: " + cls.getSimpleName());
            return null;
        }
        DiLinkSDKLog.d(cls.getSimpleName() + "实例size: " + services.size());
        return (T) searchMatchedInstance(services);
    }
}
