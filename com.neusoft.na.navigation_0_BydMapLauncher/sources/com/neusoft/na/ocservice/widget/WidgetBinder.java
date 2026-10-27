package com.neusoft.na.ocservice.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Surface;
import com.neusoft.na.navigation.IMotsServiceInterface;
import com.neusoft.na.navigation.widget.IWidgetAidlInterface;
import com.neusoft.na.navigation.widget.IWidgetCallBack;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import com.neusoft.na.ocservice.Broadcast.MotsMaskBroadcastCallBack;
import com.neusoft.na.ocservice.OneCoreService;
import com.neusoft.na.ocservice.util.CheckBoardKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WidgetBinder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u0000 02\u00020\u00012\u00020\u0002:\u00010B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010&\u001a\u00020'H\u0002J\u0010\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020*H\u0016J\b\u0010+\u001a\u00020'H\u0016J\b\u0010,\u001a\u00020'H\u0002J\u0012\u0010-\u001a\u00020'2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0012\u0010.\u001a\u00020'2\b\u0010/\u001a\u0004\u0018\u00010!H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u0007X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\tR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010 \u001a\u0004\u0018\u00010!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u00061"}, d2 = {"Lcom/neusoft/na/ocservice/widget/WidgetBinder;", "Lcom/neusoft/na/navigation/widget/IWidgetAidlInterface$Stub;", "Lcom/neusoft/na/ocservice/Broadcast/MotsMaskBroadcastCallBack;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "callback", "Lcom/neusoft/na/navigation/widget/IWidgetCallBack;", "getCallback", "()Lcom/neusoft/na/navigation/widget/IWidgetCallBack;", "setCallback", "(Lcom/neusoft/na/navigation/widget/IWidgetCallBack;)V", "getContext", "()Landroid/content/Context;", "mMotsBroadcastReceiver", "Lcom/neusoft/na/ocservice/Broadcast/MotsBroadcastReceiver;", "motsMainBinder", "Lcom/neusoft/na/navigation/IMotsServiceInterface;", "getMotsMainBinder", "()Lcom/neusoft/na/navigation/IMotsServiceInterface;", "setMotsMainBinder", "(Lcom/neusoft/na/navigation/IMotsServiceInterface;)V", "motsMainService", "getMotsMainService", "serviceConnection", "Landroid/content/ServiceConnection;", "widgetSurface", "Landroid/view/Surface;", "getWidgetSurface", "()Landroid/view/Surface;", "setWidgetSurface", "(Landroid/view/Surface;)V", "binderMotsMainService", "", "blRunningStatus", "boolean", "", "goneMask", "registerMotsBroadcastReceiver", "setWidgetCallBack", "setWidgetSuface", OneCoreService.PAYLOAD_SURFACE, "Companion", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class WidgetBinder extends IWidgetAidlInterface.Stub implements MotsMaskBroadcastCallBack {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static WidgetBinder instance;
    private String TAG;
    private IWidgetCallBack callback;
    private final Context context;
    private MotsBroadcastReceiver mMotsBroadcastReceiver;
    private IMotsServiceInterface motsMainBinder;
    private final String motsMainService;
    private final ServiceConnection serviceConnection;
    private Surface widgetSurface;

    /* JADX INFO: compiled from: WidgetBinder.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/neusoft/na/ocservice/widget/WidgetBinder$Companion;", "", "()V", "instance", "Lcom/neusoft/na/ocservice/widget/WidgetBinder;", "getInstance", "mContext", "Landroid/content/Context;", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final WidgetBinder getInstance(Context mContext) {
            Intrinsics.checkNotNullParameter(mContext, "mContext");
            if (WidgetBinder.instance == null) {
                Context applicationContext = mContext.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "mContext.applicationContext");
                WidgetBinder.instance = new WidgetBinder(applicationContext);
            }
            return WidgetBinder.instance;
        }
    }

    public WidgetBinder(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.motsMainService = "com.neusoft.na.mots.MotsMainService";
        this.TAG = "XJWidgetBinder";
        this.serviceConnection = new ServiceConnection() { // from class: com.neusoft.na.ocservice.widget.WidgetBinder$serviceConnection$1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) throws RemoteException {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(service, "service");
                Log.i(this.this$0.getTAG(), "onServiceConnected");
                this.this$0.setMotsMainBinder(IMotsServiceInterface.Stub.asInterface(service));
                WidgetBinder widgetBinder = this.this$0;
                widgetBinder.setWidgetSuface(widgetBinder.getWidgetSurface());
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                Intrinsics.checkNotNullParameter(name, "name");
                this.this$0.setMotsMainBinder(null);
                Log.i(this.this$0.getTAG(), "onServiceDisconnected");
            }
        };
        binderMotsMainService();
    }

    public final Context getContext() {
        return this.context;
    }

    public final Surface getWidgetSurface() {
        return this.widgetSurface;
    }

    public final void setWidgetSurface(Surface surface) {
        this.widgetSurface = surface;
    }

    public final String getMotsMainService() {
        return this.motsMainService;
    }

    public final IMotsServiceInterface getMotsMainBinder() {
        return this.motsMainBinder;
    }

    public final void setMotsMainBinder(IMotsServiceInterface iMotsServiceInterface) {
        this.motsMainBinder = iMotsServiceInterface;
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final void setTAG(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.TAG = str;
    }

    public final IWidgetCallBack getCallback() {
        return this.callback;
    }

    public final void setCallback(IWidgetCallBack iWidgetCallBack) {
        this.callback = iWidgetCallBack;
    }

    private final void registerMotsBroadcastReceiver() {
        MotsBroadcastReceiver companion = MotsBroadcastReceiver.INSTANCE.getInstance();
        this.mMotsBroadcastReceiver = companion;
        if (companion != null) {
            companion.setMotsMaskBroadcastCallBack(this);
        }
    }

    private final void binderMotsMainService() {
        Log.i(this.TAG, "startMotsMainService");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.neusoft.na.navigation", this.motsMainService));
        if (!CheckBoardKt.isGreatwallV61()) {
            this.context.startForegroundService(intent);
        } else {
            this.context.startService(intent);
        }
        this.context.bindService(intent, this.serviceConnection, 1);
    }

    @Override // com.neusoft.na.navigation.widget.IWidgetAidlInterface
    public void setWidgetSuface(Surface surface) throws RemoteException {
        Log.i(this.TAG, "setWidgetSuface");
        this.widgetSurface = surface;
        if (this.motsMainBinder != null) {
            Log.i(this.TAG, "setWidgetSuface");
            IMotsServiceInterface iMotsServiceInterface = this.motsMainBinder;
            if (iMotsServiceInterface != null) {
                iMotsServiceInterface.setWidgetSurface(surface);
                return;
            }
            return;
        }
        Log.i(this.TAG, "motsMainBinder==null");
    }

    @Override // com.neusoft.na.navigation.widget.IWidgetAidlInterface
    public void setWidgetCallBack(IWidgetCallBack callback) {
        Log.i(this.TAG, "setWidgetCallBack");
        this.callback = callback;
        registerMotsBroadcastReceiver();
    }

    @Override // com.neusoft.na.ocservice.Broadcast.MotsMaskBroadcastCallBack
    public void goneMask() throws RemoteException {
        Log.i(this.TAG, "goneMask");
        IWidgetCallBack iWidgetCallBack = this.callback;
        if (iWidgetCallBack != null) {
            iWidgetCallBack.maskGone();
        }
    }

    @Override // com.neusoft.na.ocservice.Broadcast.MotsMaskBroadcastCallBack
    public void blRunningStatus(boolean z) throws RemoteException {
        Log.i(this.TAG, "blRunningStatus:" + z);
        IWidgetCallBack iWidgetCallBack = this.callback;
        if (iWidgetCallBack != null) {
            iWidgetCallBack.blRunning(z);
        }
    }
}
