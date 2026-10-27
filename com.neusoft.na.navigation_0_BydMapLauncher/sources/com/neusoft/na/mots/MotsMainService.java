package com.neusoft.na.mots;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.neusoft.na.ocservice.util.CheckBoardKt;
import com.neusoft.na.ocservice.util.NotificationHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: MotsMainService.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0017J\b\u0010\u000b\u001a\u00020\nH\u0016J\"\u0010\f\u001a\u00020\r2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0017R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/neusoft/na/mots/MotsMainService;", "Landroid/app/Service;", "()V", "motsBinder", "Lcom/neusoft/na/mots/MotsMainBinder;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "", "onDestroy", "onStartCommand", "", "flags", "startId", "Companion", "BYD8155FNav-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class MotsMainService extends Service {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int MOTS_STATUS_LOADING = 1;
    public static final int MOTS_STATUS_NO = -1;
    public static final int MOTS_STATUS_WEBVIEW = 2;
    public static final int MOTS_STATUS_XWALKVIEW = 3;
    public static final String TAG = "XJMotsMainService";
    private static Context context;
    private MotsMainBinder motsBinder;

    /* JADX INFO: compiled from: MotsMainService.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/neusoft/na/mots/MotsMainService$Companion;", "", "()V", "MOTS_STATUS_LOADING", "", "MOTS_STATUS_NO", "MOTS_STATUS_WEBVIEW", "MOTS_STATUS_XWALKVIEW", "TAG", "", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "BYD8155FNav-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Context getContext() {
            return MotsMainService.context;
        }

        public final void setContext(Context context) {
            MotsMainService.context = context;
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        MotsMainService motsMainService = this;
        context = motsMainService;
        if (this.motsBinder == null) {
            this.motsBinder = new MotsMainBinder(motsMainService);
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (CheckBoardKt.isGreatwallV61()) {
            return super.onStartCommand(intent, flags, startId);
        }
        MotsMainService motsMainService = this;
        NotificationCompat.Builder ongoing = new NotificationHelper(motsMainService).create(NotificationHelper.Channel.OneCore).getBuilder().setSmallIcon(com.neusoft.na.navigation.R.drawable.ic_navigation_white_24dp).setContentTitle(getString(com.neusoft.na.navigation.R.string.app_name)).setContentText("Navigation running").setPriority(-1).setOngoing(true);
        Intent intent2 = new Intent();
        intent2.setClassName(getPackageName(), "com.neusoft.na.mots.MainActivity");
        intent2.setFlags(536870912);
        startForeground(1961, ongoing.setContentIntent(PendingIntent.getActivity(motsMainService, 0, intent2, 67108864)).build());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        if (this.motsBinder == null) {
            this.motsBinder = new MotsMainBinder(this);
        }
        return this.motsBinder;
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        MotsMainBinder motsMainBinder = this.motsBinder;
        if (motsMainBinder != null) {
            motsMainBinder.onDestroy();
        }
        this.motsBinder = null;
        super.onDestroy();
    }
}
