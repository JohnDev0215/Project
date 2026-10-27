package com.neusoft.na.mots;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import com.neusoft.na.common.ServiceBase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GuardService.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\n\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016J\b\u0010\u0013\u001a\u00020\u0011H\u0014J\"\u0010\u0014\u001a\u00020\u00152\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0015H\u0017R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006\u0019"}, d2 = {"Lcom/neusoft/na/mots/GuardService;", "Lcom/neusoft/na/common/ServiceBase;", "()V", "binder", "Lcom/neusoft/na/mots/GuardBinder;", "getBinder", "()Lcom/neusoft/na/mots/GuardBinder;", "setBinder", "(Lcom/neusoft/na/mots/GuardBinder;)V", "broadcastReceiver", "com/neusoft/na/mots/GuardService$broadcastReceiver$1", "Lcom/neusoft/na/mots/GuardService$broadcastReceiver$1;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "", "onDestroy", "onStart", "onStartCommand", "", "flags", "startId", "Companion", "BYD8155FNav-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class GuardService extends ServiceBase {
    private static final String INTENT_ACTION_RESTART_APP = "com.neusoft.na.navigation.action.restart.app";
    private static final String INTENT_ACTION_STOP = "com.neusoft.na.navigation.action.stop";
    private static final String TAG = "GuardService";
    private GuardBinder binder;
    private final GuardService$broadcastReceiver$1 broadcastReceiver;

    @Override // com.neusoft.na.common.ServiceBase
    protected void onStart() {
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.neusoft.na.mots.GuardService$broadcastReceiver$1] */
    public GuardService() {
        super("Guard");
        this.broadcastReceiver = new BootBroadcastReceiver() { // from class: com.neusoft.na.mots.GuardService$broadcastReceiver$1
            @Override // com.neusoft.na.mots.BootBroadcastReceiver, android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) throws InterruptedException {
                super.onReceive(context, intent);
                Log.i("GuardService", "onReceive --> " + (intent != null ? intent.getAction() : null));
                if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, "com.neusoft.na.navigation.action.restart.app")) {
                    Log.i("GuardService", "do restart app.");
                    GuardBinder binder = this.this$0.getBinder();
                    if (binder != null) {
                        binder.restartApp();
                        return;
                    }
                    return;
                }
                if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, "com.neusoft.na.navigation.action.stop")) {
                    Log.i("GuardService", "stop guard.");
                    GuardBinder binder2 = this.this$0.getBinder();
                    if (binder2 != null) {
                        binder2.onDestroy();
                    }
                }
            }
        };
    }

    public final GuardBinder getBinder() {
        return this.binder;
    }

    public final void setBinder(GuardBinder guardBinder) {
        this.binder = guardBinder;
    }

    @Override // com.neusoft.na.common.ServiceBase, android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        if (this.binder == null) {
            this.binder = new GuardBinder(this);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(INTENT_ACTION_RESTART_APP);
        intentFilter.addAction(INTENT_ACTION_STOP);
        registerReceiver(this.broadcastReceiver, intentFilter);
    }

    @Override // com.neusoft.na.common.ServiceBase, android.app.Service
    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        Log.i(TAG, "onBind");
        if (this.binder == null) {
            this.binder = new GuardBinder(this);
        }
        return this.binder;
    }

    @Override // com.neusoft.na.common.ServiceBase, android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        GuardBinder guardBinder = this.binder;
        if (guardBinder != null) {
            guardBinder.startConditionMonitoring();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
        unregisterReceiver(this.broadcastReceiver);
        GuardBinder guardBinder = this.binder;
        if (guardBinder != null) {
            guardBinder.onDestroy();
        }
        this.binder = null;
    }
}
