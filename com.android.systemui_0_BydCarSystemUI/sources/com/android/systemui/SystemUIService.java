package com.android.systemui;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemProperties;
import com.android.systemui.util.SystemUIException;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class SystemUIService extends Service {
    protected final String TAG = "SystemUIService";

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        BydLogHelper.logD("onCreate");
        ((SystemUIApplication) getApplication()).startServicesIfNeeded();
        if (Build.IS_DEBUGGABLE && SystemProperties.getBoolean("debug.crash_sysui", false)) {
            throw new SystemUIException();
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        Dependency.clearDependencies();
    }

    @Override // android.app.Service
    protected void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
        SystemUI[] services = ((SystemUIApplication) getApplication()).getServices();
        int i = 0;
        if (args == null || args.length == 0) {
            int length = services.length;
            while (i < length) {
                SystemUI systemUI = services[i];
                pw.println("dumping service: " + systemUI.getClass().getName());
                systemUI.dump(fd, pw, args);
                i++;
            }
            return;
        }
        String str = args[0];
        int length2 = services.length;
        while (i < length2) {
            SystemUI systemUI2 = services[i];
            if (systemUI2.getClass().getName().endsWith(str)) {
                systemUI2.dump(fd, pw, args);
            }
            i++;
        }
    }
}
