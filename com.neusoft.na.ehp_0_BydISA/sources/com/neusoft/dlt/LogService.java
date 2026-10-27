package com.neusoft.dlt;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import com.neusoft.dlt.service.DltDaemon;
import com.neusoft.dlt.service.LogSinkFile;
import com.neusoft.dlt.service.LogSinkTcpIp;
import com.neusoft.dlt.service.LogSourceTcpIp;
import com.neusoft.dlt.service.LogSourceWebSocket;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import org.json.JSONObject;
import rx.functions.Action1;
import rx.functions.Func1;

/* JADX INFO: loaded from: classes.dex */
public class LogService extends Service {
    public static final String DEFAULT_CHANNEL_ID = "default_low_importance";
    public static final String LOGTAG = "NMALogService";
    public static final int NOTIFY_Status = 8193;
    private boolean mIsRunning = false;
    private DltDaemon mDaemonInstance = null;
    private LogSinkFile mLogSinkFile = null;
    private LogSourceTcpIp mLogSourceTcpIp = null;
    private LogSourceWebSocket mLogSourceWebSocket = null;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private String mErrorMessage = null;
    private Error mError = Error.OK;
    private NotificationManager mNotificationManager = null;

    protected void setNotification(final int i) {
        this.mHandler.post(new Runnable() { // from class: com.neusoft.dlt.LogService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m36lambda$setNotification$0$comneusoftdltLogService(i);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$setNotification$0$com-neusoft-dlt-LogService, reason: not valid java name */
    /* synthetic */ void m36lambda$setNotification$0$comneusoftdltLogService(int i) {
        if (Build.VERSION.SDK_INT >= 26) {
            Notification.Builder contentTitle = new Notification.Builder(this, DEFAULT_CHANNEL_ID).setPriority(-1).setContentTitle(getString(R.string.name));
            if (this.mError != Error.OK) {
                Notification.Builder smallIcon = contentTitle.setSmallIcon(R.drawable.logo_red);
                String string = this.mErrorMessage;
                if (string == null) {
                    string = getString(this.mError.getMsgId());
                }
                smallIcon.setContentText(string);
            } else if (i == 0) {
                contentTitle.setSmallIcon(R.drawable.logo_gray).setContentText("DltDroid is running, log clients: " + i);
            } else {
                contentTitle.setSmallIcon(R.drawable.logo_green).setContentText("DltDroid is running, log clients: " + i);
            }
            startForeground(NOTIFY_Status, contentTitle.build());
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        Log.i(LOGTAG, "onCreate()");
        this.mNotificationManager = (NotificationManager) getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            String string = getString(R.string.default_channel_name);
            String string2 = getString(R.string.default_channel_description);
            NotificationChannel notificationChannel = new NotificationChannel(DEFAULT_CHANNEL_ID, string, 2);
            notificationChannel.setDescription(string2);
            ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Log.i(LOGTAG, "onStartCommand()");
        if (this.mIsRunning) {
            Log.w(LOGTAG, "service is already running!");
            return 3;
        }
        Configuration configuration = new Configuration(this);
        DltOutputOptions dltOutputOptionsInitDltOutputOptions = initDltOutputOptions();
        Error errorCheck = configuration.check();
        this.mError = errorCheck;
        this.mErrorMessage = null;
        if (errorCheck == Error.OK && configuration.getLogSinkFileEnabled()) {
            String logSinkFilePattern = configuration.getLogSinkFilePattern();
            String logSinkFileEcu = configuration.getLogSinkFileEcu();
            if (!dltOutputOptionsInitDltOutputOptions.enableSplit) {
                this.mLogSinkFile = new LogSinkFile(this, dltOutputOptionsInitDltOutputOptions.sinkPath, logSinkFilePattern, logSinkFileEcu, ((long) configuration.getLogSinkFileMaxFileLen()) * 1024 * 1024, 0, false);
            } else {
                this.mLogSinkFile = new LogSinkFile(this, dltOutputOptionsInitDltOutputOptions.sinkPath, logSinkFilePattern, logSinkFileEcu, ((long) dltOutputOptionsInitDltOutputOptions.maxFileSize) * 1024 * 1024, dltOutputOptionsInitDltOutputOptions.maxFileNum, dltOutputOptionsInitDltOutputOptions.enableSplit);
            }
        }
        if (this.mError == Error.OK) {
            DltDaemon dltDaemon = new DltDaemon();
            this.mDaemonInstance = dltDaemon;
            dltDaemon.getStatusObservable().filter(new Func1() { // from class: com.neusoft.dlt.LogService$$ExternalSyntheticLambda3
                @Override // rx.functions.Func1
                public final Object call(Object obj) {
                    return Boolean.valueOf(((DltDaemon.DaemonState) obj) == DltDaemon.DaemonState.ERROR);
                }
            }).subscribe(new Action1() { // from class: com.neusoft.dlt.LogService$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.m34lambda$onStartCommand$2$comneusoftdltLogService((DltDaemon.DaemonState) obj);
                }
            });
            this.mDaemonInstance.getConnectedClientsObservable().subscribe(new Action1() { // from class: com.neusoft.dlt.LogService$$ExternalSyntheticLambda2
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.m35lambda$onStartCommand$3$comneusoftdltLogService((Integer) obj);
                }
            });
            int loggerPort = configuration.getLoggerPort();
            if (loggerPort != 0) {
                LogSourceTcpIp logSourceTcpIp = new LogSourceTcpIp(new InetSocketAddress(loggerPort));
                this.mLogSourceTcpIp = logSourceTcpIp;
                this.mDaemonInstance.addLogSource(logSourceTcpIp);
            }
            this.mDaemonInstance.addLogSink(this.mLogSinkFile);
            if (configuration.getLogSinkTcpIpEnabled()) {
                this.mDaemonInstance.addLogSink(new LogSinkTcpIp(new InetSocketAddress(configuration.getLogSinkTcpIpPort())));
            }
            this.mDaemonInstance.start();
            this.mIsRunning = true;
        }
        if (this.mError != Error.OK) {
            this.mDaemonInstance = null;
            this.mLogSinkFile = null;
            this.mLogSourceTcpIp = null;
            this.mLogSourceWebSocket = null;
            this.mIsRunning = false;
        }
        setNotification(0);
        return 3;
    }

    /* JADX INFO: renamed from: lambda$onStartCommand$2$com-neusoft-dlt-LogService, reason: not valid java name */
    /* synthetic */ void m34lambda$onStartCommand$2$comneusoftdltLogService(DltDaemon.DaemonState daemonState) {
        this.mError = Error.LogSinkError;
    }

    /* JADX INFO: renamed from: lambda$onStartCommand$3$com-neusoft-dlt-LogService, reason: not valid java name */
    /* synthetic */ void m35lambda$onStartCommand$3$comneusoftdltLogService(Integer num) {
        setNotification(num.intValue());
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.i(LOGTAG, "onDestroy()");
        this.mDaemonInstance.stop();
        this.mDaemonInstance = null;
        this.mIsRunning = false;
        this.mNotificationManager.cancel(NOTIFY_Status);
        Process.killProcess(Process.myPid());
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.d(LOGTAG, "onBind()");
        return null;
    }

    private DltOutputOptions initDltOutputOptions() {
        DltOutputOptions dltOutputOptions = new DltOutputOptions();
        try {
            InputStream inputStreamOpen = getResources().getAssets().open("dlt_config.json");
            if (inputStreamOpen != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int i = inputStreamOpen.read();
                    if (i == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(i);
                }
                String string = byteArrayOutputStream.toString();
                JSONObject jSONObject = new JSONObject(string);
                dltOutputOptions.enableSplit = jSONObject.getBoolean("enabled");
                dltOutputOptions.maxFileNum = jSONObject.getInt("number");
                dltOutputOptions.maxFileSize = jSONObject.getInt("size");
                dltOutputOptions.sinkPath = jSONObject.getString("path");
                Log.d(LOGTAG, "configJson:" + string + ",enableSplit=" + dltOutputOptions.enableSplit + ",size=" + dltOutputOptions.maxFileSize + ",number=" + dltOutputOptions.maxFileNum);
            } else {
                Log.d(LOGTAG, "External config doesn't exist,using default config options.");
            }
        } catch (Exception unused) {
            Log.d(LOGTAG, "Did not find external file,using default value.");
        }
        return dltOutputOptions;
    }

    class DltOutputOptions {
        public boolean enableSplit = false;
        public int maxFileNum = 10;
        public int maxFileSize = 500;
        public String sinkPath = "/sdcard/navilog/";

        DltOutputOptions() {
        }
    }
}
