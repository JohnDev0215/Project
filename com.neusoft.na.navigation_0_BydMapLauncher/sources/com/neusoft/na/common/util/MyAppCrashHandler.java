package com.neusoft.na.common.util;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqNaviAppExitEventInfo;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqNaviAppStartupEventInfo;
import api.structs.com.neusoft.common.AppExitInfo;
import api.structs.com.neusoft.common.AppStartupInfo;
import api.structs.com.neusoft.common.ExitMode;
import api.structs.com.neusoft.common.MessageType;
import api.structs.com.neusoft.common.StartupMode;
import com.bydux.api.common.tools.util.ListUtils;
import com.neusoft.de.nts.OneCoreTest.communication.transports.websocketclient.OneCoreClientEndpoint;
import com.neusoft.de.nts.OneCoreTest.communication.utils.OneCoreWSContextUtil;
import com.neusoft.na.common.ClientEndpointManger;
import com.neusoft.na.common.ClientEndpointMangerCallback;
import java.lang.Thread;

/* JADX INFO: loaded from: classes2.dex */
public class MyAppCrashHandler implements Thread.UncaughtExceptionHandler, ClientEndpointMangerCallback {
    private static final String TAG = "MyAppCrashHandler";
    private Context context;
    private boolean isServerConnect;
    private CommonSysApiInverseRequests requestsCommon;

    public MyAppCrashHandler(Context context) {
        this.context = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
        ClientEndpointManger companion = ClientEndpointManger.INSTANCE.getInstance(context);
        companion.setClientEndpointMangerCallback(this);
        if (companion.getConnectionState() == OneCoreClientEndpoint.ConnectionState.CONNECTED) {
            onServerConnect(companion.getWsContext());
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        Log.i(TAG, "uncaughtException: " + th.getMessage());
        setNaviAppExit(this.requestsCommon, 4);
        th.printStackTrace();
    }

    @Override // com.neusoft.na.common.ClientEndpointMangerCallback
    public void onServerConnect(OneCoreWSContextUtil oneCoreWSContextUtil) {
        Log.i(TAG, "onServerConnect " + this.isServerConnect);
        if (this.isServerConnect) {
            return;
        }
        this.isServerConnect = true;
        CommonSysApiInverseRequests commonSysApiInverseRequests = new CommonSysApiInverseRequests(oneCoreWSContextUtil);
        this.requestsCommon = commonSysApiInverseRequests;
        setNaviAppStartup(this.context, commonSysApiInverseRequests, 2);
    }

    @Override // com.neusoft.na.common.ClientEndpointMangerCallback
    public void onServerDisconnect() {
        Log.i(TAG, "onServerDisconnect");
        this.isServerConnect = false;
    }

    public static void setNaviAppStartup(Context context, CommonSysApiInverseRequests commonSysApiInverseRequests, int i) {
        Log.i(TAG, "setNaviAppStartup: " + i + ListUtils.DEFAULT_JOIN_SEPARATOR + commonSysApiInverseRequests);
        AppStartupInfo appStartupInfo = new AppStartupInfo();
        appStartupInfo.setIVIVer(String.valueOf(Build.VERSION.SDK_INT));
        appStartupInfo.setMode(StartupMode.getByValue(Integer.valueOf(i)));
        appStartupInfo.setCarModelID(String.valueOf(SystemUtils.INSTANCE.getSystemPropertiesIntValue(context, "persist.sys.AutoType")));
        commonSysApiInverseRequests.fireNaviAppStartupEventInfo(new ReqNaviAppStartupEventInfo(MessageType.NAVI_STARTUP, appStartupInfo));
    }

    public static void setNaviAppExit(CommonSysApiInverseRequests commonSysApiInverseRequests, int i) {
        Log.i(TAG, "setNaviAppExit: " + i + ListUtils.DEFAULT_JOIN_SEPARATOR + commonSysApiInverseRequests);
        if (commonSysApiInverseRequests == null) {
            return;
        }
        commonSysApiInverseRequests.fireNaviAppExitEventInfo(new ReqNaviAppExitEventInfo(MessageType.NAVI_EXIT, new AppExitInfo(String.valueOf(System.currentTimeMillis()), ExitMode.getByValue(Integer.valueOf(i)))));
    }
}
