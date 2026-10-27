package com.neusoft.na.dltdroid;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.ecarx.xui.adaptapi.audio.audiofx.IHarmanEqualizer;
import com.neusoft.na.dltdroid.service.DaemonState;
import com.neusoft.na.dltdroid.service.DltDaemon;
import com.neusoft.na.dltdroid.service.LogSinkFile;
import com.neusoft.na.dltdroid.service.LogSinkTcpIp;
import com.neusoft.na.dltdroid.service.LogSourceTcpIp;
import com.neusoft.na.dltdroid.service.LogSourceWebSocket;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashSet;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONObject;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/* JADX INFO: compiled from: LogService.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0093\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n*\u0001&\u0018\u0000 =2\u00020\u0001:\u0002=>B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010(\u001a\u00020\u00102\u0006\u0010)\u001a\u00020*H\u0002J\b\u0010+\u001a\u00020,H\u0002J\u0012\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010/\u001a\u000200H\u0016J\b\u00101\u001a\u000202H\u0016J\b\u00103\u001a\u000202H\u0016J \u00104\u001a\u0002052\u0006\u0010/\u001a\u0002002\u0006\u00106\u001a\u0002052\u0006\u00107\u001a\u000205H\u0016J\u0010\u00108\u001a\u00020\u00102\u0006\u0010/\u001a\u000200H\u0016J\b\u00109\u001a\u000202H\u0002J\u0010\u0010:\u001a\u0002022\u0006\u0010;\u001a\u000205H\u0004J\b\u0010<\u001a\u00020\u0012H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0015\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0017`\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u000e\u001a\u0004\b\u001d\u0010\u001eR\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\"\u001a\u0012\u0012\u0004\u0012\u00020\u001c0#j\b\u0012\u0004\u0012\u00020\u001c`$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0004\n\u0002\u0010'¨\u0006?"}, d2 = {"Lcom/neusoft/na/dltdroid/LogService;", "Landroid/app/Service;", "()V", "mDaemonInstance", "Lcom/neusoft/na/dltdroid/service/DltDaemon;", "mError", "Lcom/neusoft/na/dltdroid/Error;", "mErrorMessage", "", "mHandler", "Landroid/os/Handler;", "getMHandler", "()Landroid/os/Handler;", "mHandler$delegate", "Lkotlin/Lazy;", "mIsRunning", "", "mLastStatusBundle", "Landroid/os/Bundle;", "mLogSinkFile", "Lcom/neusoft/na/dltdroid/service/LogSinkFile;", "mLogSourceTcpIpSet", "Ljava/util/HashSet;", "Lcom/neusoft/na/dltdroid/service/LogSourceTcpIp;", "Lkotlin/collections/HashSet;", "mLogSourceWebSocket", "Lcom/neusoft/na/dltdroid/service/LogSourceWebSocket;", "mMessenger", "Landroid/os/Messenger;", "getMMessenger", "()Landroid/os/Messenger;", "mMessenger$delegate", "mNotificationManager", "Landroid/app/NotificationManager;", "mRegLogStateClients", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "mStatusTimer", "com/neusoft/na/dltdroid/LogService$mStatusTimer$1", "Lcom/neusoft/na/dltdroid/LogService$mStatusTimer$1;", "handleIncomingMessage", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "initDltOutputOptions", "Lcom/neusoft/na/dltdroid/LogService$DltOutputOptions;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "", "onDestroy", "onStartCommand", "", "flags", "startId", "onUnbind", "sendLogState", "setNotification", "nrOfClients", "updateStatusBundle", "Companion", "DltOutputOptions", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class LogService extends Service {
    private DltDaemon mDaemonInstance;
    private String mErrorMessage;
    private boolean mIsRunning;
    private Bundle mLastStatusBundle;
    private LogSinkFile mLogSinkFile;
    private LogSourceWebSocket mLogSourceWebSocket;
    private NotificationManager mNotificationManager;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String LOGTAG = "LogService";
    private static final int MSG_Reg_LogState = 4101;
    private static final int MSG_Clr_LogState = 4102;
    private static final int MSG_Inf_LogState = 4103;
    private static final int MSG_Req_ServiceState = 4104;
    private static final int MSG_Res_ServiceState = 4104;
    private static final int MSG_Err_Unsupported = 8192;
    private static final String MSGKEY_Str_Error = "errormsg";
    private static final String MSGKEY_Int_ErrCode = "error";
    private static final String MSGKEY_Int_LoggerPort = "logger.port";
    private static final String MSGKEY_Int_LoggerWsPort = "logger.wsport";
    private static final String MSGKEY_Str_LogPath = "logsink.file.path";
    private static final String MSGKEY_Lng_DataLogged = "datalogged";
    private static final String MSGKEY_Int_ClientCount = "clientcount";
    private static final String MSGKEY_Lng_Bandwidth = "bandwidth";
    private static final String MSGKEY_Lng_Timestamp = "timestamp";
    private static final int NOTIFY_Status = IHarmanEqualizer.HARMAN_SETTING_CLARIFI;
    private static final String DEFAULT_CHANNEL_ID = "default_low_importance";
    private final HashSet<LogSourceTcpIp> mLogSourceTcpIpSet = new HashSet<>();

    /* JADX INFO: renamed from: mHandler$delegate, reason: from kotlin metadata */
    private final Lazy mHandler = LazyKt.lazy(new Function0<Handler>() { // from class: com.neusoft.na.dltdroid.LogService$mHandler$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Handler invoke() {
            return new Handler(this.this$0.getMainLooper());
        }
    });
    private final ArrayList<Messenger> mRegLogStateClients = new ArrayList<>();
    private final LogService$mStatusTimer$1 mStatusTimer = new LogService$mStatusTimer$1(this);
    private Error mError = Error.OK;

    /* JADX INFO: renamed from: mMessenger$delegate, reason: from kotlin metadata */
    private final Lazy mMessenger = LazyKt.lazy(new Function0<Messenger>() { // from class: com.neusoft.na.dltdroid.LogService$mMessenger$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Messenger invoke() {
            Looper mainLooper = this.this$0.getMainLooper();
            final LogService logService = this.this$0;
            return new Messenger(new Handler(mainLooper) { // from class: com.neusoft.na.dltdroid.LogService$mMessenger$2.1
                @Override // android.os.Handler
                public void handleMessage(Message msg) {
                    Intrinsics.checkNotNullParameter(msg, "msg");
                    if (logService.handleIncomingMessage(msg)) {
                        return;
                    }
                    super.handleMessage(msg);
                }
            });
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public final Handler getMHandler() {
        return (Handler) this.mHandler.getValue();
    }

    private final Messenger getMMessenger() {
        return (Messenger) this.mMessenger.getValue();
    }

    private final Bundle updateStatusBundle() {
        Bundle bundle = new Bundle();
        long jUptimeMillis = SystemClock.uptimeMillis();
        String str = MSGKEY_Lng_Timestamp;
        bundle.putLong(str, jUptimeMillis);
        DltDaemon dltDaemon = this.mDaemonInstance;
        if (dltDaemon != null) {
            bundle.putInt(MSGKEY_Int_ClientCount, dltDaemon.getNrOfClients());
            long bytesLogged = dltDaemon.getBytesLogged();
            String str2 = MSGKEY_Lng_DataLogged;
            bundle.putLong(str2, bytesLogged);
            Bundle bundle2 = this.mLastStatusBundle;
            Intrinsics.checkNotNull(bundle2);
            long j = jUptimeMillis - bundle2.getLong(str);
            Bundle bundle3 = this.mLastStatusBundle;
            Intrinsics.checkNotNull(bundle3);
            bundle.putLong(MSGKEY_Lng_Bandwidth, j != 0 ? ((bytesLogged - bundle3.getLong(str2)) * ((long) 1000)) / j : 0L);
        }
        this.mLastStatusBundle = bundle;
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendLogState() {
        Bundle bundleUpdateStatusBundle = updateStatusBundle();
        try {
            for (Messenger messenger : this.mRegLogStateClients) {
                Message messageObtain = Message.obtain((Handler) null, MSG_Inf_LogState);
                messageObtain.setData(bundleUpdateStatusBundle);
                messenger.send(messageObtain);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean handleIncomingMessage(Message msg) {
        Error error = Error.OK;
        int i = msg.what;
        Message messageObtain = null;
        if (i == MSG_Req_ServiceState) {
            Log.i(LOGTAG, "received MSG_Req_ServiceState");
            messageObtain = Message.obtain((Handler) null, MSG_Res_ServiceState);
            Bundle bundle = new Bundle();
            Intrinsics.checkNotNull(messageObtain);
            messageObtain.setData(bundle);
        } else if (i == MSG_Reg_LogState) {
            Messenger messenger = msg.replyTo;
            if (messenger != null) {
                if (this.mRegLogStateClients.contains(messenger)) {
                    Log.i(LOGTAG, "client is already registered");
                } else {
                    this.mRegLogStateClients.add(messenger);
                }
                messageObtain = Message.obtain((Handler) null, MSG_Inf_LogState);
                Intrinsics.checkNotNull(messageObtain);
                messageObtain.setData(this.mLastStatusBundle);
            } else {
                Log.e(LOGTAG, "no client provided for Reg_LogState!");
            }
        } else if (i == MSG_Clr_LogState) {
            Messenger messenger2 = msg.replyTo;
            if (messenger2 != null) {
                if (this.mRegLogStateClients.contains(messenger2)) {
                    this.mRegLogStateClients.remove(messenger2);
                } else {
                    Log.i(LOGTAG, "client is not registered");
                }
            } else {
                Log.e(LOGTAG, "no client provided for Reg_LogState!");
            }
        } else {
            messageObtain = Message.obtain((Handler) null, MSG_Err_Unsupported);
        }
        if (messageObtain == null) {
            return true;
        }
        if (messageObtain.getData() == null) {
            messageObtain.setData(new Bundle());
        }
        messageObtain.arg1 = error.getErrorCode();
        if (msg.replyTo != null) {
            try {
                msg.replyTo.send(messageObtain);
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
                return true;
            }
        }
        Log.e(LOGTAG, "failed to send response!");
        return true;
    }

    protected final void setNotification(final int nrOfClients) {
        getMHandler().post(new Runnable() { // from class: com.neusoft.na.dltdroid.LogService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                LogService.setNotification$lambda$1(this.f$0, nrOfClients);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setNotification$lambda$1(LogService this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(this$0.getBaseContext(), (Class<?>) MainActivity.class);
        intent.addFlags(805306368);
        NotificationCompat.Builder contentIntent = new NotificationCompat.Builder(this$0, DEFAULT_CHANNEL_ID).setPriority(-1).setContentTitle(this$0.getString(R.string.app_name)).setContentIntent(PendingIntent.getActivity(this$0.getBaseContext(), 0, intent, 0));
        if (this$0.mError != Error.OK) {
            NotificationCompat.Builder smallIcon = contentIntent.setSmallIcon(R.drawable.logo_red);
            String string = this$0.mErrorMessage;
            if (string == null) {
                string = this$0.getString(this$0.mError.getMsgId());
            }
            smallIcon.setContentText(string);
        } else if (i == 0) {
            contentIntent.setSmallIcon(R.drawable.logo_gray).setContentText("DltDroid is running, log clients: " + i);
        } else {
            contentIntent.setSmallIcon(R.drawable.logo_green).setContentText("DltDroid is running, log clients: " + i);
        }
        this$0.startForeground(NOTIFY_Status, contentIntent.build());
    }

    @Override // android.app.Service
    public void onCreate() {
        Log.i(LOGTAG, "onCreate()");
        Object systemService = getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        this.mNotificationManager = (NotificationManager) systemService;
        Bundle bundle = new Bundle();
        this.mLastStatusBundle = bundle;
        Intrinsics.checkNotNull(bundle);
        bundle.putLong(MSGKEY_Lng_Timestamp, SystemClock.uptimeMillis());
        String string = getString(R.string.default_channel_name);
        String string2 = getString(R.string.default_channel_description);
        NotificationChannel notificationChannel = new NotificationChannel(DEFAULT_CHANNEL_ID, string, 2);
        notificationChannel.setDescription(string2);
        ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogSinkFile logSinkFile;
        Intrinsics.checkNotNullParameter(intent, "intent");
        String str = LOGTAG;
        Log.i(str, "onStartCommand()");
        if (this.mIsRunning) {
            Log.w(str, "service is already running!");
            return 3;
        }
        LogService logService = this;
        Configuration configuration = new Configuration(logService);
        DltOutputOptions dltOutputOptionsInitDltOutputOptions = initDltOutputOptions();
        Error errorCheck = configuration.check();
        this.mError = errorCheck;
        this.mErrorMessage = null;
        if (errorCheck == Error.OK && configuration.getLogSinkFileEnabled()) {
            String logSinkFilePattern = configuration.getLogSinkFilePattern();
            String logSinkFileEcu = configuration.getLogSinkFileEcu();
            if (!dltOutputOptionsInitDltOutputOptions.getEnableSplit()) {
                logSinkFile = new LogSinkFile(logService, configuration.getLogSinkFilePath(), logSinkFilePattern, logSinkFileEcu, 1024 * ((long) configuration.getLogSinkFileMaxFileLen()) * 1024, 0, false);
            } else {
                logSinkFile = new LogSinkFile(logService, configuration.getLogSinkFilePath(), logSinkFilePattern, logSinkFileEcu, 1024 * dltOutputOptionsInitDltOutputOptions.getMaxFileSize() * 1024, dltOutputOptionsInitDltOutputOptions.getMaxFileNum(), dltOutputOptionsInitDltOutputOptions.getEnableSplit());
            }
            this.mLogSinkFile = logSinkFile;
        }
        if (this.mError == Error.OK) {
            DltDaemon dltDaemon = new DltDaemon();
            this.mDaemonInstance = dltDaemon;
            Observable<DaemonState> statusObservable = dltDaemon.getStatusObservable();
            final LogService$onStartCommand$1$1 logService$onStartCommand$1$1 = new Function1<DaemonState, Boolean>() { // from class: com.neusoft.na.dltdroid.LogService$onStartCommand$1$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(DaemonState daemonState) {
                    return Boolean.valueOf(daemonState == DaemonState.ERROR);
                }
            };
            Observable<DaemonState> observableFilter = statusObservable.filter(new Func1() { // from class: com.neusoft.na.dltdroid.LogService$$ExternalSyntheticLambda1
                @Override // rx.functions.Func1
                public final Object call(Object obj) {
                    return LogService.onStartCommand$lambda$9$lambda$2(logService$onStartCommand$1$1, obj);
                }
            });
            final LogService$onStartCommand$1$2 logService$onStartCommand$1$2 = new LogService$onStartCommand$1$2(this);
            observableFilter.subscribe(new Action1() { // from class: com.neusoft.na.dltdroid.LogService$$ExternalSyntheticLambda2
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    LogService.onStartCommand$lambda$9$lambda$3(logService$onStartCommand$1$2, obj);
                }
            });
            Observable<Integer> connectedClientsObservable = dltDaemon.getConnectedClientsObservable();
            final Function1<Integer, Unit> function1 = new Function1<Integer, Unit>() { // from class: com.neusoft.na.dltdroid.LogService$onStartCommand$1$3
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke2(num);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Integer value) {
                    LogService logService2 = this.this$0;
                    Intrinsics.checkNotNullExpressionValue(value, "value");
                    logService2.setNotification(value.intValue());
                }
            };
            connectedClientsObservable.subscribe(new Action1() { // from class: com.neusoft.na.dltdroid.LogService$$ExternalSyntheticLambda3
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    LogService.onStartCommand$lambda$9$lambda$4(function1, obj);
                }
            });
            int loggerPort = configuration.getLoggerPort();
            if (loggerPort != 0) {
                LogSourceTcpIp logSourceTcpIp = new LogSourceTcpIp(new InetSocketAddress(loggerPort));
                dltDaemon.addLogSource(logSourceTcpIp);
                this.mLogSourceTcpIpSet.add(logSourceTcpIp);
            }
            int loggerPort4717 = configuration.getLoggerPort4717();
            if (loggerPort4717 != 0) {
                LogSourceTcpIp logSourceTcpIp2 = new LogSourceTcpIp(new InetSocketAddress(loggerPort4717));
                Log.d(str, "onStartCommand tcpIpPort4717: " + logSourceTcpIp2);
                dltDaemon.addLogSource(logSourceTcpIp2);
                this.mLogSourceTcpIpSet.add(logSourceTcpIp2);
            }
            int loggerWsPort = configuration.getLoggerWsPort();
            if (loggerWsPort != 0) {
                LogSourceWebSocket logSourceWebSocket = new LogSourceWebSocket(new InetSocketAddress(loggerWsPort));
                dltDaemon.addLogSource(logSourceWebSocket);
                this.mLogSourceWebSocket = logSourceWebSocket;
            }
            LogSinkFile logSinkFile2 = this.mLogSinkFile;
            if (logSinkFile2 != null) {
                dltDaemon.addLogSink(logSinkFile2);
            }
            if (configuration.getLogSinkTcpIpEnabled()) {
                dltDaemon.addLogSink(new LogSinkTcpIp(new InetSocketAddress(configuration.getLogSinkTcpIpPort())));
            }
            try {
                dltDaemon.start();
                this.mStatusTimer.setInterval(1000L);
                this.mIsRunning = true;
            } catch (IOException e) {
                e.printStackTrace();
                this.mError = Error.PortAlreadyInUse;
                this.mErrorMessage = "failed to start Socket Server!";
            }
        }
        if (this.mError != Error.OK) {
            this.mDaemonInstance = null;
            this.mLogSinkFile = null;
            this.mLogSourceTcpIpSet.clear();
            this.mLogSourceWebSocket = null;
            this.mIsRunning = false;
        }
        setNotification(0);
        return 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean onStartCommand$lambda$9$lambda$2(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        return (Boolean) tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onStartCommand$lambda$9$lambda$3(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onStartCommand$lambda$9$lambda$4(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        String str = LOGTAG;
        Log.i(str, "onUnbind()");
        this.mRegLogStateClients.clear();
        Log.i(str, "keep logger in background");
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.i(LOGTAG, "onDestroy()");
        NotificationManager notificationManager = this.mNotificationManager;
        Intrinsics.checkNotNull(notificationManager);
        notificationManager.cancel(NOTIFY_Status);
        Process.killProcess(Process.myPid());
        DltDaemon dltDaemon = this.mDaemonInstance;
        if (dltDaemon != null) {
            dltDaemon.stop();
        }
        this.mDaemonInstance = null;
        this.mStatusTimer.setInterval(0L);
        this.mIsRunning = false;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        Log.i(LOGTAG, "onBind()");
        return getMMessenger().getBinder();
    }

    /* JADX INFO: compiled from: LogService.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u000f\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0014\u0010\u0013\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0014\u0010\u0015\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0014\u0010\u0017\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0014\u0010\u0019\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006R\u0014\u0010\u001b\u001a\u00020\u001cX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u001cX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001eR\u0014\u0010!\u001a\u00020\u001cX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001eR\u0014\u0010#\u001a\u00020\u001cX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001eR\u0014\u0010%\u001a\u00020\u001cX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001eR\u0014\u0010'\u001a\u00020\u001cX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001eR\u0014\u0010)\u001a\u00020\u001cX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001e¨\u0006+"}, d2 = {"Lcom/neusoft/na/dltdroid/LogService$Companion;", "", "()V", "DEFAULT_CHANNEL_ID", "", "getDEFAULT_CHANNEL_ID", "()Ljava/lang/String;", "LOGTAG", "getLOGTAG", "MSGKEY_Int_ClientCount", "getMSGKEY_Int_ClientCount", "MSGKEY_Int_ErrCode", "getMSGKEY_Int_ErrCode", "MSGKEY_Int_LoggerPort", "getMSGKEY_Int_LoggerPort", "MSGKEY_Int_LoggerWsPort", "getMSGKEY_Int_LoggerWsPort", "MSGKEY_Lng_Bandwidth", "getMSGKEY_Lng_Bandwidth", "MSGKEY_Lng_DataLogged", "getMSGKEY_Lng_DataLogged", "MSGKEY_Lng_Timestamp", "getMSGKEY_Lng_Timestamp", "MSGKEY_Str_Error", "getMSGKEY_Str_Error", "MSGKEY_Str_LogPath", "getMSGKEY_Str_LogPath", "MSG_Clr_LogState", "", "getMSG_Clr_LogState", "()I", "MSG_Err_Unsupported", "getMSG_Err_Unsupported", "MSG_Inf_LogState", "getMSG_Inf_LogState", "MSG_Reg_LogState", "getMSG_Reg_LogState", "MSG_Req_ServiceState", "getMSG_Req_ServiceState", "MSG_Res_ServiceState", "getMSG_Res_ServiceState", "NOTIFY_Status", "getNOTIFY_Status", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getLOGTAG() {
            return LogService.LOGTAG;
        }

        public final int getMSG_Reg_LogState() {
            return LogService.MSG_Reg_LogState;
        }

        public final int getMSG_Clr_LogState() {
            return LogService.MSG_Clr_LogState;
        }

        public final int getMSG_Inf_LogState() {
            return LogService.MSG_Inf_LogState;
        }

        public final int getMSG_Req_ServiceState() {
            return LogService.MSG_Req_ServiceState;
        }

        public final int getMSG_Res_ServiceState() {
            return LogService.MSG_Res_ServiceState;
        }

        public final int getMSG_Err_Unsupported() {
            return LogService.MSG_Err_Unsupported;
        }

        public final String getMSGKEY_Str_Error() {
            return LogService.MSGKEY_Str_Error;
        }

        public final String getMSGKEY_Int_ErrCode() {
            return LogService.MSGKEY_Int_ErrCode;
        }

        public final String getMSGKEY_Int_LoggerPort() {
            return LogService.MSGKEY_Int_LoggerPort;
        }

        public final String getMSGKEY_Int_LoggerWsPort() {
            return LogService.MSGKEY_Int_LoggerWsPort;
        }

        public final String getMSGKEY_Str_LogPath() {
            return LogService.MSGKEY_Str_LogPath;
        }

        public final String getMSGKEY_Lng_DataLogged() {
            return LogService.MSGKEY_Lng_DataLogged;
        }

        public final String getMSGKEY_Int_ClientCount() {
            return LogService.MSGKEY_Int_ClientCount;
        }

        public final String getMSGKEY_Lng_Bandwidth() {
            return LogService.MSGKEY_Lng_Bandwidth;
        }

        public final String getMSGKEY_Lng_Timestamp() {
            return LogService.MSGKEY_Lng_Timestamp;
        }

        public final int getNOTIFY_Status() {
            return LogService.NOTIFY_Status;
        }

        public final String getDEFAULT_CHANNEL_ID() {
            return LogService.DEFAULT_CHANNEL_ID;
        }
    }

    private final DltOutputOptions initDltOutputOptions() {
        FileInputStream fileInputStream;
        DltOutputOptions dltOutputOptions = new DltOutputOptions();
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "naviexternconfig/mots_dlt_config.json");
            JSONObject jSONObject = null;
            if (file.exists() && file.isFile()) {
                fileInputStream = new FileInputStream(file);
                Log.i(LOGTAG, "read external config.");
            } else {
                Log.i(LOGTAG, "Did not find external file,using default value.");
                fileInputStream = null;
            }
            if (fileInputStream != null) {
                Reader inputStreamReader = new InputStreamReader(fileInputStream, Charsets.UTF_8);
                BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                try {
                    String text = TextStreamsKt.readText(bufferedReader);
                    CloseableKt.closeFinally(bufferedReader, null);
                    JSONObject jSONObject2 = new JSONObject(text);
                    if (StringsKt.contains$default((CharSequence) text, (CharSequence) "byd8155f", false, 2, (Object) null)) {
                        jSONObject = jSONObject2.getJSONObject("byd8155f");
                    } else {
                        Log.i(LOGTAG, "config file didn't find PROJECT_TYPE:byd8155f");
                    }
                    if (jSONObject != null) {
                        try {
                            dltOutputOptions.setEnableSplit(jSONObject.getBoolean("enabled"));
                            dltOutputOptions.setMaxFileNum(jSONObject.getInt("number"));
                            dltOutputOptions.setMaxFileSize(jSONObject.getLong("size"));
                            Log.i(LOGTAG, "configJson:" + text + ",enableSplit=" + dltOutputOptions.getEnableSplit() + "size=" + dltOutputOptions.getMaxFileSize() + ",number=" + dltOutputOptions.getMaxFileNum());
                        } catch (Exception e) {
                            Log.i(LOGTAG, e.getMessage());
                        }
                    }
                } finally {
                }
            } else {
                Log.i(LOGTAG, "External config doesn't exist,using default config options.");
            }
        } catch (Exception e2) {
            Log.i(LOGTAG, "using default config options.");
            e2.printStackTrace();
        }
        return dltOutputOptions;
    }

    /* JADX INFO: compiled from: LogService.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/neusoft/na/dltdroid/LogService$DltOutputOptions;", "", "()V", "enableSplit", "", "getEnableSplit", "()Z", "setEnableSplit", "(Z)V", "maxFileNum", "", "getMaxFileNum", "()I", "setMaxFileNum", "(I)V", "maxFileSize", "", "getMaxFileSize", "()J", "setMaxFileSize", "(J)V", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class DltOutputOptions {
        private boolean enableSplit;
        private int maxFileNum = 10;
        private long maxFileSize = 500;

        public final boolean getEnableSplit() {
            return this.enableSplit;
        }

        public final void setEnableSplit(boolean z) {
            this.enableSplit = z;
        }

        public final int getMaxFileNum() {
            return this.maxFileNum;
        }

        public final void setMaxFileNum(int i) {
            this.maxFileNum = i;
        }

        public final long getMaxFileSize() {
            return this.maxFileSize;
        }

        public final void setMaxFileSize(long j) {
            this.maxFileSize = j;
        }
    }
}
