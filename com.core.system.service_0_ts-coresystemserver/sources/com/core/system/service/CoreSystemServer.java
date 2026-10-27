package com.core.system.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.ts.car.sdk.core.Logger;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class CoreSystemServer extends Service {
    private static final int ARGS_LENGTH_SWITCH_LOG = 2;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        CoreSystemBinderServer.getInstance().init(getApplicationContext());
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return CoreSystemBinderServer.getInstance();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.debug("onStartCommand intent is " + intent);
        if (intent != null && intent.hasExtra("DEBUG")) {
            String debug = intent.getStringExtra("DEBUG");
            Logger.debug("onStartCommand debug is " + debug);
            if ("open_debug".equals(debug)) {
                Logger.changeLogSwitchState(true);
                Logger.debug("onStartCommand ! open debug " + intent);
            } else if ("close_debug".equals(debug)) {
                Logger.changeLogSwitchState(false);
                Logger.debug("onStartCommand ! close debug " + intent);
            }
        }
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        Logger.debug("onDestroy !");
    }

    @Override // android.app.Service
    public void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        writer.println("CORE_SYSTEM_SERVICE SERVER (dumpsys activity service com.core.system.service.CoreSystemServer)");
        writer.println("DEBUG_LOG true");
        writer.println();
        openOrCloseLogSwitch(args);
        CoreSystemBinderServer.getInstance().dumpMsg(fd, writer, args);
    }

    private void openOrCloseLogSwitch(String[] args) {
        if (args == null || args.length != 2) {
            Logger.debug("not log switch dump");
            return;
        }
        if (!TextUtils.equals("switchlog", args[0])) {
            Logger.debug("not log switch dump, arg0:" + args[0]);
            return;
        }
        boolean logSwitchState = false;
        try {
            logSwitchState = Boolean.parseBoolean(args[1]);
        } catch (Throwable ex) {
            Logger.warning("logSwitch ", ex);
        }
        Logger.warning("Logger switch changed to: " + logSwitchState);
        Logger.changeLogSwitchState(logSwitchState);
    }
}
