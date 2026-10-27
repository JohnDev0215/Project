package com.neusoft.na.byd;

import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.speed.AbsBYDAutoSpeedListener;
import android.hardware.bydauto.speed.BYDAutoSpeedDevice;
import android.util.Log;
import cerence.ark.assistant.library.JsonKey;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Byd8155fSystemAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\fH\u0002J\b\u0010\u0010\u001a\u00020\u000eH\u0002J\b\u0010\u0011\u001a\u00020\u000eH\u0002J\u0006\u0010\u0012\u001a\u00020\u000eR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/neusoft/na/byd/Byd8155fSystemAdapter;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "bydAutoGearboxDevice", "Landroid/hardware/bydauto/gearbox/BYDAutoGearboxDevice;", "bydAutoSpeedDevice", "Landroid/hardware/bydauto/speed/BYDAutoSpeedDevice;", "carSpeed", "", "gearValue", "", "gearboxChanged", "", MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE, "initBYDAutoDevice", "sendCarSpeedDataToLocationHandler", "start", "Companion", "byd8155f_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class Byd8155fSystemAdapter {
    private static final String ACTION_CAR_SPEED_CHANGED = "com.neusoft.na.navigation.car.speed.changed";
    private static final String TAG = "Byd8155fSystemAdapter";
    private BYDAutoGearboxDevice bydAutoGearboxDevice;
    private BYDAutoSpeedDevice bydAutoSpeedDevice;
    private double carSpeed;
    private final Context context;
    private int gearValue;

    public Byd8155fSystemAdapter(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.gearValue = 1;
    }

    public final void start() throws InterruptedException {
        Log.i(TAG, "[start]");
        initBYDAutoDevice();
        sendCarSpeedDataToLocationHandler();
    }

    private final void initBYDAutoDevice() throws InterruptedException {
        Log.d(TAG, "initBYDAutoDevice");
        this.bydAutoGearboxDevice = BYDAutoGearboxDevice.getInstance(this.context);
        Log.d(TAG, "BYDAutoGearboxDevice instance.");
        this.bydAutoSpeedDevice = BYDAutoSpeedDevice.getInstance(this.context);
        Log.d(TAG, "BYDAutoSpeedDevice instance.");
        Thread.sleep(300L);
        BYDAutoGearboxDevice bYDAutoGearboxDevice = this.bydAutoGearboxDevice;
        if (bYDAutoGearboxDevice != null) {
            gearboxChanged(bYDAutoGearboxDevice.getGear());
            bYDAutoGearboxDevice.registerListener(new AbsBYDAutoGearboxListener() { // from class: com.neusoft.na.byd.Byd8155fSystemAdapter$initBYDAutoDevice$1$1
                @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
                public void onGearChanged(int value) {
                    super.onGearChanged(value);
                    Log.i("Byd8155fSystemAdapter", "onGearChanged --> value == " + value);
                    this.this$0.gearboxChanged(value);
                }
            });
        }
        BYDAutoSpeedDevice bYDAutoSpeedDevice = this.bydAutoSpeedDevice;
        this.carSpeed = bYDAutoSpeedDevice != null ? bYDAutoSpeedDevice.getCurrentSpeed() : 0.0d;
        BYDAutoSpeedDevice bYDAutoSpeedDevice2 = this.bydAutoSpeedDevice;
        if (bYDAutoSpeedDevice2 != null) {
            bYDAutoSpeedDevice2.registerListener(new AbsBYDAutoSpeedListener() { // from class: com.neusoft.na.byd.Byd8155fSystemAdapter.initBYDAutoDevice.2
                @Override // android.hardware.bydauto.speed.AbsBYDAutoSpeedListener
                public void onSpeedChanged(double value) {
                    super.onSpeedChanged(value);
                    Byd8155fSystemAdapter.this.carSpeed = value;
                    Byd8155fSystemAdapter.this.sendCarSpeedDataToLocationHandler();
                }
            });
        }
        Log.d(TAG, "initBYDAutoDevice end.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void gearboxChanged(int value) {
        Log.i(TAG, "[switchGearboxChange] --> value == " + value);
        this.gearValue = value == 2 ? -1 : 1;
        Log.i(TAG, "gearValue == " + this.gearValue);
        sendCarSpeedDataToLocationHandler();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendCarSpeedDataToLocationHandler() {
        Log.i(TAG, "[sendCarSpeedData] --> carSpeed == " + this.carSpeed + ", gearValue == " + this.gearValue);
        Intent intent = new Intent("com.neusoft.na.navigation.car.speed.changed");
        double d = (this.carSpeed / 3.6d) * ((double) this.gearValue);
        intent.putExtra(JsonKey.RESULTS_DATA, d);
        Log.i(TAG, "send carGearSpeed == " + d);
        this.context.sendBroadcast(intent);
    }
}
