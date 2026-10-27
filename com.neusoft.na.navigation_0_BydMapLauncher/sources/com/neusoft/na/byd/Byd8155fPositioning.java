package com.neusoft.na.byd;

import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.speed.BYDAutoSpeedDevice;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.neusoft.de.nts.OneCoreTest.communication.transports.websocketclient.OneCoreClientEndpoint;
import com.neusoft.de.nts.OneCoreTest.communication.utils.WSContextUtil;
import com.neusoft.na.common.IOptionalComponent;
import com.neusoft.na.ocservice.MotsService;
import com.neusoft.na.sens.OneCoreSenRegister;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogging;
import rx.Observable;
import rx.functions.Action1;

/* JADX INFO: compiled from: Byd8155fPositioning.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 62\u00020\u0001:\u00016B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010(\u001a\u00020)H\u0002J\u0010\u0010*\u001a\u00020)2\u0006\u0010\u0002\u001a\u00020+H\u0002J\b\u0010,\u001a\u00020)H\u0002J\b\u0010-\u001a\u00020)H\u0002J\u0010\u0010.\u001a\u00020)2\u0006\u0010/\u001a\u00020\u001aH\u0002J\b\u00100\u001a\u00020)H\u0016J\b\u00101\u001a\u00020)H\u0002J\b\u00102\u001a\u00020)H\u0016J\b\u00103\u001a\u00020)H\u0002J\u0010\u00104\u001a\u00020)2\u0006\u0010/\u001a\u00020\u001aH\u0002J\b\u00105\u001a\u00020)H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lcom/neusoft/na/byd/Byd8155fPositioning;", "Lcom/neusoft/na/common/IOptionalComponent;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "bydAutoGearboxDevice", "Landroid/hardware/bydauto/gearbox/BYDAutoGearboxDevice;", "getBydAutoGearboxDevice", "()Landroid/hardware/bydauto/gearbox/BYDAutoGearboxDevice;", "setBydAutoGearboxDevice", "(Landroid/hardware/bydauto/gearbox/BYDAutoGearboxDevice;)V", "bydAutoSpeedDevice", "Landroid/hardware/bydauto/speed/BYDAutoSpeedDevice;", "getBydAutoSpeedDevice", "()Landroid/hardware/bydauto/speed/BYDAutoSpeedDevice;", "setBydAutoSpeedDevice", "(Landroid/hardware/bydauto/speed/BYDAutoSpeedDevice;)V", "enableGetSpeed", "", "handler", "Landroid/os/Handler;", "isBrakingState", "isRunning", "mCarSpeed", "", "mGearValue", "", "mLastDecimalTime", "", "mValueAccelerator", "", "mValueGyro", "registerOneCoreSensor", "Lcom/neusoft/na/sens/OneCoreSenRegister;", "threadPool", "Ljava/util/concurrent/ExecutorService;", NotificationCompat.CATEGORY_TRANSPORT, "Lcom/neusoft/de/nts/OneCoreTest/communication/transports/websocketclient/OneCoreClientEndpoint;", "initBYDAutoDevice", "", "onPosConnected", "Lcom/neusoft/de/nts/OneCoreTest/communication/utils/WSContextUtil;", "onPosDisconnect", "registerDataNotifyListener", "sendBrakeStatusToBl", "speed", "start", "startSpeedTriggerThread", "stop", "stopSpeedTriggerThread", "transferSpeed", "unregisterDataNotifyListener", "Companion", "byd8155f_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class Byd8155fPositioning implements IOptionalComponent {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String POSITIONING_ENDPOINT = "ws://127.0.0.1:22532";
    private final String TAG;
    private BYDAutoGearboxDevice bydAutoGearboxDevice;
    private BYDAutoSpeedDevice bydAutoSpeedDevice;
    private final Context context;
    private boolean enableGetSpeed;
    private final Handler handler;
    private boolean isBrakingState;
    private boolean isRunning;
    private float mCarSpeed;
    private int mGearValue;
    private long mLastDecimalTime;
    private float[] mValueAccelerator;
    private float[] mValueGyro;
    private OneCoreSenRegister registerOneCoreSensor;
    private ExecutorService threadPool;
    private OneCoreClientEndpoint transport;

    /* JADX INFO: Access modifiers changed from: private */
    public final void onPosConnected(WSContextUtil context) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onPosDisconnect() {
    }

    /* JADX INFO: compiled from: Byd8155fPositioning.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/neusoft/na/byd/Byd8155fPositioning$Companion;", "Lmu/KLogging;", "()V", "POSITIONING_ENDPOINT", "", "byd8155f_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion extends KLogging {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public Byd8155fPositioning(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.TAG = "Byd8155fPositioning";
        this.handler = new Handler(context.getMainLooper());
        this.isBrakingState = true;
        this.enableGetSpeed = true;
        this.mGearValue = 1;
        this.registerOneCoreSensor = OneCoreSenRegister.getInstance();
        this.threadPool = Executors.newSingleThreadExecutor();
        OneCoreClientEndpoint oneCoreClientEndpointBuild = new OneCoreClientEndpoint.Builder().connectTimeout(2000L).retryOnConnectionFailure(true).build();
        this.transport = oneCoreClientEndpointBuild;
        if (oneCoreClientEndpointBuild != null) {
            Observable<OneCoreClientEndpoint.ConnectionState> connectionObservable = oneCoreClientEndpointBuild.getConnectionObservable();
            final Byd8155fPositioning$1$1 byd8155fPositioning$1$1 = new Byd8155fPositioning$1$1(this);
            connectionObservable.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fPositioning$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Byd8155fPositioning.lambda$1$lambda$0(byd8155fPositioning$1$1, obj);
                }
            });
        }
        registerDataNotifyListener();
    }

    public final BYDAutoSpeedDevice getBydAutoSpeedDevice() {
        return this.bydAutoSpeedDevice;
    }

    public final void setBydAutoSpeedDevice(BYDAutoSpeedDevice bYDAutoSpeedDevice) {
        this.bydAutoSpeedDevice = bYDAutoSpeedDevice;
    }

    public final BYDAutoGearboxDevice getBydAutoGearboxDevice() {
        return this.bydAutoGearboxDevice;
    }

    public final void setBydAutoGearboxDevice(BYDAutoGearboxDevice bYDAutoGearboxDevice) {
        this.bydAutoGearboxDevice = bYDAutoGearboxDevice;
    }

    static final void lambda$1$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    private final void startSpeedTriggerThread() {
        new Thread(new Runnable() { // from class: com.neusoft.na.byd.Byd8155fPositioning$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                Byd8155fPositioning.startSpeedTriggerThread$lambda$2(this.f$0);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startSpeedTriggerThread$lambda$2(Byd8155fPositioning this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.d(this$0.TAG, "Get speed in thread");
        while (this$0.enableGetSpeed) {
            try {
                INSTANCE.getLogger().debug("Thread mCarSpeed == " + this$0.mCarSpeed + ", mGearValue == " + this$0.mGearValue + ",elapsedTime == " + SystemClock.uptimeMillis());
                this$0.transferSpeed(this$0.mCarSpeed * this$0.mGearValue);
                OneCoreSenRegister oneCoreSenRegister = this$0.registerOneCoreSensor;
                if (oneCoreSenRegister != null) {
                    oneCoreSenRegister.setNaviSpeedSensor(this$0.mCarSpeed * this$0.mGearValue, SystemClock.uptimeMillis());
                }
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private final void stopSpeedTriggerThread() {
        this.enableGetSpeed = false;
    }

    private final void registerDataNotifyListener() {
        try {
            INSTANCE.getLogger().debug("registerDataNotifyListener gearValue=" + this.mGearValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void unregisterDataNotifyListener() {
        try {
            INSTANCE.getLogger().debug("unregister DataNotifyListener.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void transferSpeed(float speed) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime - this.mLastDecimalTime > 999) {
            this.mLastDecimalTime = jElapsedRealtime;
            sendBrakeStatusToBl(speed);
        }
    }

    private final void sendBrakeStatusToBl(float speed) {
        if (this.isBrakingState) {
            if (Math.abs(speed) > 5.0f) {
                this.isBrakingState = false;
                Intent intent = new Intent();
                intent.setAction(MotsService.ACTION_BRAKING_CHANGED);
                intent.putExtra("BrakingState", this.isBrakingState);
                this.context.sendBroadcast(intent);
                return;
            }
            return;
        }
        if (Math.abs(speed) < 4.0f) {
            this.isBrakingState = true;
            Intent intent2 = new Intent();
            intent2.setAction(MotsService.ACTION_BRAKING_CHANGED);
            intent2.putExtra("BrakingState", this.isBrakingState);
            this.context.sendBroadcast(intent2);
            INSTANCE.getLogger().debug("brakingStateChanged ,isBrakingState = " + this.isBrakingState + '.');
        }
    }

    @Override // com.neusoft.na.common.IOptionalComponent
    public void start() {
        if (this.isRunning) {
            return;
        }
        this.isRunning = true;
        Log.d(this.TAG, "start");
        try {
            startSpeedTriggerThread();
            INSTANCE.getLogger().debug("register DataNotifyListener.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void initBYDAutoDevice() {
        /*
            r4 = this;
            java.lang.String r0 = r4.TAG
            java.lang.String r1 = "initBYDAutoDevice"
            android.util.Log.d(r0, r1)
            android.content.Context r0 = r4.context
            android.hardware.bydauto.gearbox.BYDAutoGearboxDevice r0 = android.hardware.bydauto.gearbox.BYDAutoGearboxDevice.getInstance(r0)
            r4.bydAutoGearboxDevice = r0
            java.lang.String r0 = r4.TAG
            java.lang.String r1 = "BYDAutoGearboxDevice instance."
            android.util.Log.d(r0, r1)
            android.hardware.bydauto.gearbox.BYDAutoGearboxDevice r0 = r4.bydAutoGearboxDevice
            r1 = 0
            if (r0 == 0) goto L24
            int r0 = r0.getGearboxType()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            goto L25
        L24:
            r0 = r1
        L25:
            r2 = -1
            r3 = 1
            if (r0 != 0) goto L2a
            goto L46
        L2a:
            int r0 = r0.intValue()
            if (r0 != 0) goto L46
            android.hardware.bydauto.gearbox.BYDAutoGearboxDevice r0 = r4.bydAutoGearboxDevice
            if (r0 == 0) goto L3c
            int r0 = r0.getGearboxAutoModeType()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
        L3c:
            if (r1 != 0) goto L3f
            goto L5d
        L3f:
            int r0 = r1.intValue()
            if (r0 != r3) goto L5d
            goto L5e
        L46:
            android.hardware.bydauto.gearbox.BYDAutoGearboxDevice r0 = r4.bydAutoGearboxDevice
            if (r0 == 0) goto L52
            int r0 = r0.getGearboxAutoModeType()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
        L52:
            if (r1 != 0) goto L55
            goto L5d
        L55:
            int r0 = r1.intValue()
            r1 = 2
            if (r0 != r1) goto L5d
            goto L5e
        L5d:
            r2 = r3
        L5e:
            r4.mGearValue = r2
            android.content.Context r0 = r4.context
            android.hardware.bydauto.speed.BYDAutoSpeedDevice r0 = android.hardware.bydauto.speed.BYDAutoSpeedDevice.getInstance(r0)
            r4.bydAutoSpeedDevice = r0
            java.lang.String r0 = r4.TAG
            java.lang.String r1 = "BYDAutoSpeedDevice instance."
            android.util.Log.d(r0, r1)
            android.hardware.bydauto.speed.BYDAutoSpeedDevice r0 = r4.bydAutoSpeedDevice
            if (r0 == 0) goto L79
            double r0 = r0.getCurrentSpeed()
            float r0 = (float) r0
            goto L7a
        L79:
            r0 = 0
        L7a:
            r4.mCarSpeed = r0
            android.hardware.bydauto.speed.BYDAutoSpeedDevice r0 = r4.bydAutoSpeedDevice
            if (r0 == 0) goto L8a
            com.neusoft.na.byd.Byd8155fPositioning$initBYDAutoDevice$1 r1 = new com.neusoft.na.byd.Byd8155fPositioning$initBYDAutoDevice$1
            r1.<init>()
            android.hardware.bydauto.speed.AbsBYDAutoSpeedListener r1 = (android.hardware.bydauto.speed.AbsBYDAutoSpeedListener) r1
            r0.registerListener(r1)
        L8a:
            java.lang.String r0 = r4.TAG
            java.lang.String r1 = "initBYDAutoDevice end."
            android.util.Log.d(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.byd.Byd8155fPositioning.initBYDAutoDevice():void");
    }

    @Override // com.neusoft.na.common.IOptionalComponent
    public void stop() {
        if (this.isRunning) {
            this.isRunning = false;
            Log.d(this.TAG, "stop");
            unregisterDataNotifyListener();
            stopSpeedTriggerThread();
        }
    }
}
