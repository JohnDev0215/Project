package com.neusoft.na.common;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.neusoft.na.common.ApplicationStatus;
import com.neusoft.nts.common.R;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogging;

/* JADX INFO: compiled from: ServiceBase.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\b&\u0018\u0000 /2\u00020\u0001:\u0001/B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0004J\b\u0010\r\u001a\u00020\u0006H\u0004J\b\u0010\u000e\u001a\u00020\fH\u0004J\b\u0010\u000f\u001a\u00020\fH\u0014J\b\u0010\u0010\u001a\u00020\fH\u0004J\b\u0010\u0011\u001a\u00020\fH\u0004J\b\u0010\u0012\u001a\u00020\fH\u0002J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0003H\u0004J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0014J\b\u0010\u0019\u001a\u00020\fH\u0004J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u001fH\u0004J\b\u0010!\u001a\u00020\u001fH$J\"\u0010\"\u001a\u00020#2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#H\u0016J\u0010\u0010&\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010'\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020#H\u0004J\u0010\u0010)\u001a\u00020\u001f2\u0006\u0010*\u001a\u00020\tH\u0002J\b\u0010+\u001a\u00020\u001fH\u0005J\u0010\u0010,\u001a\u00020\u001f2\u0006\u0010*\u001a\u00020\tH\u0002J\u0010\u0010-\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020\u0006H\u0004R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/neusoft/na/common/ServiceBase;", "Landroid/app/Service;", "mServiceName", "", "(Ljava/lang/String;)V", "mApplicationStatus", "Lcom/neusoft/na/common/ApplicationStatus;", "mClients", "", "Landroid/os/Messenger;", "mMessenger", "getAppCacheDir", "Ljava/io/File;", "getApplicationStatus", "getAssetsInstallDir", "getNaviexternconfigDir", "getPrivateCacheDir", "getPrivateTempDir", "getServiceCacheDir", "getSharedLibraryPath", "libName", "handleIncomingMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "initPrivateTempDir", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "", "onPlaySoundEffect", "onStart", "onStartCommand", "", "flags", "startId", "onUnbind", "ondayNightModeChanged", ServiceBase.PAYLOAD_MODE, "registerClient", "messenger", "startForeground", "unregisterClient", "updateApplicationStatus", "status", "Companion", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public abstract class ServiceBase extends Service {
    public static final int MSG_REQ_REGISTER_CLIENT = 65536;
    public static final int MSG_REQ_START = 131072;
    public static final int MSG_REQ_UNREGISTER_CLIENT = 65537;
    public static final int MSG_SIG_APPLICATION_STATUS = 196608;
    public static final int MSG_SIG_DAY_NIGHT_MODE = 327680;
    public static final int MSG_SIG_PLAY_SOUND_EFFECT = 262144;
    public static final String PAYLOAD_CONFIG = "config";
    public static final String PAYLOAD_MODE = "mode";
    public static final String PAYLOAD_STATUS = "status";
    private static int notificationChannelId;
    private ApplicationStatus mApplicationStatus;
    private final Set<Messenger> mClients;
    private final Messenger mMessenger;
    private final String mServiceName;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static String TAG = "XJServiceBase";

    protected final String getSharedLibraryPath(String libName) {
        Intrinsics.checkNotNullParameter(libName, "libName");
        return libName;
    }

    protected abstract void onStart();

    public ServiceBase(String mServiceName) {
        Intrinsics.checkNotNullParameter(mServiceName, "mServiceName");
        this.mServiceName = mServiceName;
        this.mApplicationStatus = new ApplicationStatus(ApplicationStatus.Status.UNINITIALIZED, 0);
        this.mClients = new HashSet();
        this.mMessenger = new Messenger(new Handler(new Handler.Callback() { // from class: com.neusoft.na.common.ServiceBase$$ExternalSyntheticLambda0
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return ServiceBase.mMessenger$lambda$1(this.f$0, message);
            }
        }));
    }

    /* JADX INFO: compiled from: ServiceBase.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\r\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0080T¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/neusoft/na/common/ServiceBase$Companion;", "Lmu/KLogging;", "()V", "MSG_REQ_REGISTER_CLIENT", "", "MSG_REQ_START", "MSG_REQ_UNREGISTER_CLIENT", "MSG_SIG_APPLICATION_STATUS", "MSG_SIG_DAY_NIGHT_MODE", "MSG_SIG_PLAY_SOUND_EFFECT", "PAYLOAD_CONFIG", "", "PAYLOAD_MODE", "PAYLOAD_STATUS", "TAG", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "notificationChannelId", "getNotificationChannelId", "()I", "setNotificationChannelId", "(I)V", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion extends KLogging {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return ServiceBase.TAG;
        }

        public final void setTAG(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            ServiceBase.TAG = str;
        }

        public final int getNotificationChannelId() {
            return ServiceBase.notificationChannelId;
        }

        public final void setNotificationChannelId(int i) {
            ServiceBase.notificationChannelId = i;
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate " + this.mServiceName);
        startForeground();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand " + this.mServiceName);
        return super.onStartCommand(intent, flags, startId);
    }

    protected final synchronized void startForeground() {
        int iMyPid = Process.myPid() * 10;
        int i = notificationChannelId + 1;
        notificationChannelId = i;
        int i2 = iMyPid + i;
        Log.i(TAG, this.mServiceName + " startForeground --> id == " + i2 + ", notificationChannelId == " + notificationChannelId);
        String str = this.mServiceName + "Channel";
        Object systemService = getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager notificationManager = (NotificationManager) systemService;
        if (notificationManager.getNotificationChannel(str) == null) {
            notificationManager.createNotificationChannel(new NotificationChannel(str, getString(R.string.app_name), 2));
        }
        NotificationCompat.Builder ongoing = new NotificationCompat.Builder(this, str).setSmallIcon(R.drawable.ic_navigation_white_24dp).setContentTitle(getString(R.string.app_name)).setContentText("Navigation running").setPriority(-1).setOngoing(true);
        ServiceBase serviceBase = this;
        Intent intent = new Intent();
        intent.setClassName(getPackageName(), "com.neusoft.na.mots.MainActivity");
        intent.setFlags(536870912);
        Notification notificationBuild = ongoing.setContentIntent(PendingIntent.getActivity(this, 0, intent, 67108864)).build();
        Intrinsics.checkNotNullExpressionValue(notificationBuild, "Builder(this, channelId)…  })\n            .build()");
        startForeground(i2, notificationBuild);
        Log.i(TAG, "startForeground done " + this.mServiceName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean mMessenger$lambda$1(ServiceBase this$0, Message it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        return this$0.handleIncomingMessage(it);
    }

    private final File getServiceCacheDir() {
        return new File(getAppCacheDir(), this.mServiceName);
    }

    protected final File getAppCacheDir() {
        if ((getApplicationInfo().flags & 262144) != 0) {
            File externalCacheDir = getExternalCacheDir();
            Intrinsics.checkNotNull(externalCacheDir);
            Intrinsics.checkNotNullExpressionValue(externalCacheDir, "{\n         externalCacheDir!!\n      }");
            return externalCacheDir;
        }
        File cacheDir = getCacheDir();
        Intrinsics.checkNotNullExpressionValue(cacheDir, "{\n         cacheDir\n      }");
        return cacheDir;
    }

    protected final File getPrivateCacheDir() {
        return new File(getServiceCacheDir(), "cache");
    }

    protected final File getPrivateTempDir() {
        return new File(getServiceCacheDir(), "tmp");
    }

    protected final File getAssetsInstallDir() {
        return new File(getFilesDir(), "install");
    }

    protected File getNaviexternconfigDir() {
        return new File("/sdcard/naviexternconfig");
    }

    protected final File initPrivateTempDir() {
        File privateTempDir = getPrivateTempDir();
        if (privateTempDir.exists()) {
            FilesKt.deleteRecursively(privateTempDir);
        }
        privateTempDir.mkdirs();
        return privateTempDir;
    }

    protected boolean handleIncomingMessage(Message msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        int i = msg.what;
        if (i == 65536) {
            INSTANCE.getLogger().debug(this.mServiceName + ": received MSG_REQ_REGISTER_CLIENT");
            Messenger messenger = msg.replyTo;
            Intrinsics.checkNotNullExpressionValue(messenger, "msg.replyTo");
            registerClient(messenger);
        } else if (i == 65537) {
            INSTANCE.getLogger().debug(this.mServiceName + ": received MSG_REQ_UNREGISTER_CLIENT");
            Messenger messenger2 = msg.replyTo;
            Intrinsics.checkNotNullExpressionValue(messenger2, "msg.replyTo");
            unregisterClient(messenger2);
        } else if (i == 131072) {
            INSTANCE.getLogger().debug(this.mServiceName + ": received MSG_REQ_START");
            onStart();
            return true;
        }
        return false;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        Log.i(TAG, "onBind");
        INSTANCE.getLogger().debug("ServiceBase." + this.mServiceName + ".onBind()");
        return this.mMessenger.getBinder();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onUnbind(intent);
        return false;
    }

    private final synchronized void registerClient(final Messenger messenger) {
        if (this.mClients.add(messenger)) {
            Message messageObtain = Message.obtain((Handler) null, MSG_SIG_APPLICATION_STATUS);
            Bundle bundle = new Bundle();
            bundle.putParcelable("status", this.mApplicationStatus);
            messageObtain.setData(bundle);
            messenger.send(messageObtain);
            try {
                messenger.getBinder().linkToDeath(new IBinder.DeathRecipient() { // from class: com.neusoft.na.common.ServiceBase$$ExternalSyntheticLambda1
                    @Override // android.os.IBinder.DeathRecipient
                    public final void binderDied() {
                        ServiceBase.registerClient$lambda$3(this.f$0, messenger);
                    }
                }, 0);
            } catch (DeadObjectException unused) {
                Log.w(TAG, "here have a DeadObjectException");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void registerClient$lambda$3(ServiceBase this$0, Messenger messenger) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(messenger, "$messenger");
        this$0.unregisterClient(messenger);
    }

    private final synchronized void unregisterClient(Messenger messenger) {
        this.mClients.remove(messenger);
    }

    protected final synchronized void updateApplicationStatus(ApplicationStatus status) {
        Intrinsics.checkNotNullParameter(status, "status");
        if (!Intrinsics.areEqual(status, this.mApplicationStatus)) {
            this.mApplicationStatus = status;
            if (!this.mClients.isEmpty()) {
                for (Messenger messenger : this.mClients) {
                    Message messageObtain = Message.obtain((Handler) null, MSG_SIG_APPLICATION_STATUS);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("status", this.mApplicationStatus);
                    messageObtain.setData(bundle);
                    messenger.send(messageObtain);
                }
            }
        }
    }

    protected final synchronized void onPlaySoundEffect() {
        if (!this.mClients.isEmpty()) {
            for (Messenger messenger : this.mClients) {
                Message messageObtain = Message.obtain((Handler) null, 262144);
                messageObtain.setData(new Bundle());
                messenger.send(messageObtain);
            }
        }
    }

    protected final synchronized void ondayNightModeChanged(int mode) {
        if (!this.mClients.isEmpty()) {
            for (Messenger messenger : this.mClients) {
                Message messageObtain = Message.obtain((Handler) null, MSG_SIG_DAY_NIGHT_MODE);
                Bundle bundle = new Bundle();
                bundle.putInt(PAYLOAD_MODE, mode);
                messageObtain.setData(bundle);
                messenger.send(messageObtain);
            }
        }
    }

    /* JADX INFO: renamed from: getApplicationStatus, reason: from getter */
    protected final ApplicationStatus getMApplicationStatus() {
        return this.mApplicationStatus;
    }
}
