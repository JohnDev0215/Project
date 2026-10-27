package com.neusoft.na.mots;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.neusoft.na.mots.MotsMainBinder;
import com.neusoft.na.ocservice.util.CheckBoardKt;
import com.neusoft.nts.common.IGuardInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MotsMainBinder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\t"}, d2 = {"com/neusoft/na/mots/MotsMainBinder$serviceConnection$1", "Landroid/content/ServiceConnection;", "onServiceConnected", "", "name", "Landroid/content/ComponentName;", NotificationCompat.CATEGORY_SERVICE, "Landroid/os/IBinder;", "onServiceDisconnected", "BYD8155FNav-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class MotsMainBinder$serviceConnection$1 implements ServiceConnection {
    final /* synthetic */ MotsMainBinder this$0;

    MotsMainBinder$serviceConnection$1(MotsMainBinder motsMainBinder) {
        this.this$0 = motsMainBinder;
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName name) {
        Log.i(MotsMainBinder.TAG, "GuardService onServiceDisconnected");
        this.this$0.binder = null;
        this.this$0.startGuardService();
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.i(MotsMainBinder.TAG, "GuardService onServiceConnected");
        try {
            this.this$0.binder = IGuardInterface.Stub.asInterface(service);
            if (this.this$0.activeStatus == MotsMainBinder.Companion.ActivityStatus.Resume) {
                IGuardInterface iGuardInterface = this.this$0.binder;
                if (iGuardInterface != null) {
                    iGuardInterface.active(true);
                }
            } else {
                IGuardInterface iGuardInterface2 = this.this$0.binder;
                if (iGuardInterface2 != null) {
                    iGuardInterface2.active(false);
                }
            }
        } catch (Exception e) {
            Log.i(MotsMainBinder.TAG, "GuardService  binder Exception" + e.getMessage());
            if (CheckBoardKt.isGreatwallV61()) {
                this.this$0.getContext().unbindService(this);
                this.this$0.getContext().stopService(new Intent(this.this$0.getContext(), (Class<?>) GuardService.class));
                Handler handler = new Handler(Looper.getMainLooper());
                final MotsMainBinder motsMainBinder = this.this$0;
                handler.postDelayed(new Runnable() { // from class: com.neusoft.na.mots.MotsMainBinder$serviceConnection$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        MotsMainBinder$serviceConnection$1.onServiceConnected$lambda$0(motsMainBinder);
                    }
                }, 1000L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServiceConnected$lambda$0(MotsMainBinder this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.i(MotsMainBinder.TAG, "GuardService rebind");
        this$0.startGuardService();
    }
}
