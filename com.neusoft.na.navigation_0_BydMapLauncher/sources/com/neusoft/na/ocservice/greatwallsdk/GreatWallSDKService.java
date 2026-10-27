package com.neusoft.na.ocservice.greatwallsdk;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.neusoft.na.ocservice.Binder.GreatWallSDKBinder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GreatWallSDKService.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0012H\u0016J\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/neusoft/na/ocservice/greatwallsdk/GreatWallSDKService;", "Landroid/app/Service;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "binder", "Lcom/neusoft/na/ocservice/Binder/GreatWallSDKBinder;", "getBinder", "()Lcom/neusoft/na/ocservice/Binder/GreatWallSDKBinder;", "setBinder", "(Lcom/neusoft/na/ocservice/Binder/GreatWallSDKBinder;)V", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "", "onDestroy", "onUnbind", "", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class GreatWallSDKService extends Service {
    private final String TAG = "XJGreatWallSDKService";
    private GreatWallSDKBinder binder;

    public final String getTAG() {
        return this.TAG;
    }

    public final GreatWallSDKBinder getBinder() {
        return this.binder;
    }

    public final void setBinder(GreatWallSDKBinder greatWallSDKBinder) {
        this.binder = greatWallSDKBinder;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.i(this.TAG, "onCreate");
        if (this.binder == null) {
            this.binder = GreatWallSDKBinder.INSTANCE.getInstance(this);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        Log.i(this.TAG, "GreatWallSDKService");
        if (this.binder == null) {
            this.binder = GreatWallSDKBinder.INSTANCE.getInstance(this);
        }
        return this.binder;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        Log.i(this.TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.i(this.TAG, "onDestroy");
        super.onDestroy();
    }
}
