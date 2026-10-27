package com.neusoft.na.ocservice.smartnavisdk;

import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.neusoft.na.common.ServiceBase;
import com.neusoft.na.ocservice.Binder.SmartSDKBinder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SmartSDKService.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0012H\u0016J\b\u0010\u0014\u001a\u00020\u0012H\u0014R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/neusoft/na/ocservice/smartnavisdk/SmartSDKService;", "Lcom/neusoft/na/common/ServiceBase;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "binder", "Lcom/neusoft/na/ocservice/Binder/SmartSDKBinder;", "getBinder", "()Lcom/neusoft/na/ocservice/Binder/SmartSDKBinder;", "setBinder", "(Lcom/neusoft/na/ocservice/Binder/SmartSDKBinder;)V", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "", "onDestroy", "onStart", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SmartSDKService extends ServiceBase {
    private final String TAG;
    private SmartSDKBinder binder;

    @Override // com.neusoft.na.common.ServiceBase
    protected void onStart() {
    }

    public SmartSDKService() {
        super("smartsdk");
        this.TAG = "XJSmartSDKService";
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final SmartSDKBinder getBinder() {
        return this.binder;
    }

    public final void setBinder(SmartSDKBinder smartSDKBinder) {
        this.binder = smartSDKBinder;
    }

    @Override // com.neusoft.na.common.ServiceBase, android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.d(this.TAG, "SmartSDKServiceonCreate");
        if (this.binder == null) {
            this.binder = SmartSDKBinder.INSTANCE.getInstance(this);
        }
    }

    @Override // com.neusoft.na.common.ServiceBase, android.app.Service
    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        Log.d(this.TAG, "SmartSDKServiceononBind");
        if (this.binder == null) {
            this.binder = SmartSDKBinder.INSTANCE.getInstance(this);
        }
        return this.binder;
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.d(this.TAG, "onDestroy");
        super.onDestroy();
    }
}
