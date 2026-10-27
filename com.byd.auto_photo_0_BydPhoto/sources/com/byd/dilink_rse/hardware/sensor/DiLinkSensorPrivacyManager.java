package com.byd.dilink_rse.hardware.sensor;

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

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_RSE_5_0, DiLink.VERSION_NAME_RSE_RUI})
@ServiceImpl(service = IDiLinkSensorPrivacyManager.class, singleton = true)
public class DiLinkSensorPrivacyManager implements IDiLinkSensorPrivacyManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private SensorPrivacyManager f2713a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink_rse_OnSensorPrivacyListeners f2714b = new DiLink_rse_OnSensorPrivacyListeners();

    public static class DiLink_rse_OnSensorPrivacyListeners extends DiLinkCallbackHolders<IDiLinkOnSensorPrivacyListener, SensorPrivacyManager.OnSensorPrivacyChangedListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public SensorPrivacyManager.OnSensorPrivacyChangedListener createInnerCallback(final IDiLinkOnSensorPrivacyListener iDiLinkOnSensorPrivacyListener) {
            return new SensorPrivacyManager.OnSensorPrivacyChangedListener() { // from class: com.byd.dilink_rse.hardware.sensor.DiLinkSensorPrivacyManager.DiLink_rse_OnSensorPrivacyListeners.1
                @Keep
                public void onSensorPrivacyChanged(int i, boolean z) {
                    iDiLinkOnSensorPrivacyListener.onSensorPrivacyChanged(i, z);
                }
            };
        }
    }

    public DiLinkSensorPrivacyManager(Context context) {
        this.f2713a = SensorPrivacyManager.getInstance(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, SensorPrivacyManager.OnSensorPrivacyChangedListener onSensorPrivacyChangedListener) {
        this.f2713a.addSensorPrivacyListener(i, onSensorPrivacyChangedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(int i, SensorPrivacyManager.OnSensorPrivacyChangedListener onSensorPrivacyChangedListener) {
        this.f2713a.removeSensorPrivacyListener(i, onSensorPrivacyChangedListener);
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public void addSensorPrivacyListener(Context context, final int i, IDiLinkOnSensorPrivacyListener iDiLinkOnSensorPrivacyListener) {
        this.f2714b.addCallbackIgnoreRemoteException(iDiLinkOnSensorPrivacyListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_rse.hardware.sensor.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2717a.a(i, (SensorPrivacyManager.OnSensorPrivacyChangedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public List<Integer> getAvailableSensorIds(Context context) {
        return this.f2713a.getAvailableSensorIds();
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public long getSensorPrivacyDeadTime(Context context, int i) {
        return this.f2713a.getSensorPrivacyDeadTime(i);
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public long getSensorStateForPeriod(Context context, int i) {
        return this.f2713a.getSensorStateForPeriod(i);
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public boolean isSensorPrivacyEnabled(Context context, int i) {
        return this.f2713a.isSensorPrivacyEnabled(i);
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public void removeSensorPrivacyListener(Context context, final int i, IDiLinkOnSensorPrivacyListener iDiLinkOnSensorPrivacyListener) {
        this.f2714b.removeCallbackIgnoreRemoteException(iDiLinkOnSensorPrivacyListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_rse.hardware.sensor.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2719a.d(i, (SensorPrivacyManager.OnSensorPrivacyChangedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public void setSensorPrivacy(Context context, int i, int i2, boolean z, long j) {
        this.f2713a.setSensorPrivacy(i, i2, z, j);
    }
}
