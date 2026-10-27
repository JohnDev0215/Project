package com.byd.dilink30.hardware.sensor;

import android.content.Context;
import android.hardware.SensorPrivacyManager;
import androidx.annotation.Keep;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.hardware.sensor.IDiLinkOnSensorPrivacyListener;
import com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkSensorPrivacyManager.class, singleton = true)
public class DiLinkSensorPrivacyManager implements IDiLinkSensorPrivacyManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private SensorPrivacyManager f1208a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink3_0_OnSensorPrivacyListeners f1209b = new DiLink3_0_OnSensorPrivacyListeners();

    public static class DiLink3_0_OnSensorPrivacyListeners extends DiLinkCallbackHolders<IDiLinkOnSensorPrivacyListener, SensorPrivacyManager.OnSensorPrivacyChangedListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public SensorPrivacyManager.OnSensorPrivacyChangedListener createInnerCallback(final IDiLinkOnSensorPrivacyListener iDiLinkOnSensorPrivacyListener) {
            return new SensorPrivacyManager.OnSensorPrivacyChangedListener() { // from class: com.byd.dilink30.hardware.sensor.DiLinkSensorPrivacyManager.DiLink3_0_OnSensorPrivacyListeners.1
                @Keep
                public void onSensorPrivacyChanged(int i, boolean z) {
                    iDiLinkOnSensorPrivacyListener.onSensorPrivacyChanged(i, z);
                }
            };
        }
    }

    public DiLinkSensorPrivacyManager(Context context) {
        this.f1208a = SensorPrivacyManager.getInstance(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, SensorPrivacyManager.OnSensorPrivacyChangedListener onSensorPrivacyChangedListener) {
        this.f1208a.removeSensorPrivacyListener(i, onSensorPrivacyChangedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(int i, SensorPrivacyManager.OnSensorPrivacyChangedListener onSensorPrivacyChangedListener) {
        this.f1208a.addSensorPrivacyListener(i, onSensorPrivacyChangedListener);
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public void addSensorPrivacyListener(Context context, final int i, IDiLinkOnSensorPrivacyListener iDiLinkOnSensorPrivacyListener) {
        this.f1209b.addCallbackIgnoreRemoteException(iDiLinkOnSensorPrivacyListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink30.hardware.sensor.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1212a.b(i, (SensorPrivacyManager.OnSensorPrivacyChangedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public List<Integer> getAvailableSensorIds(Context context) {
        return this.f1208a.getAvailableSensorIds();
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public long getSensorPrivacyDeadTime(Context context, int i) {
        return this.f1208a.getSensorPrivacyDeadTime(i);
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public long getSensorStateForPeriod(Context context, int i) {
        return this.f1208a.getSensorStateForPeriod(i);
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public boolean isSensorPrivacyEnabled(Context context, int i) {
        return this.f1208a.isSensorPrivacyEnabled(i);
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public void removeSensorPrivacyListener(Context context, final int i, IDiLinkOnSensorPrivacyListener iDiLinkOnSensorPrivacyListener) {
        this.f1209b.removeCallbackIgnoreRemoteException(iDiLinkOnSensorPrivacyListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink30.hardware.sensor.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1214a.a(i, (SensorPrivacyManager.OnSensorPrivacyChangedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public void setSensorPrivacy(Context context, int i, int i2, boolean z, long j) {
        this.f1208a.setSensorPrivacy(i, i2, z, j);
    }
}
