package com.byd.dilink51.hardware.sensor;

import android.content.Context;
import android.hardware.BydSensorPrivacyManager;
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
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkSensorPrivacyManager.class, singleton = true)
public class DiLinkSensorPrivacyManager implements IDiLinkSensorPrivacyManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private SensorPrivacyManager f1568a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink5_1_Decouple_OnSensorPrivacyListeners f1569b = new DiLink5_1_Decouple_OnSensorPrivacyListeners();

    public static class DiLink5_1_Decouple_OnSensorPrivacyListeners extends DiLinkCallbackHolders<IDiLinkOnSensorPrivacyListener, SensorPrivacyManager.OnSensorPrivacyChangedListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public SensorPrivacyManager.OnSensorPrivacyChangedListener createInnerCallback(final IDiLinkOnSensorPrivacyListener iDiLinkOnSensorPrivacyListener) {
            return new SensorPrivacyManager.OnSensorPrivacyChangedListener() { // from class: com.byd.dilink51.hardware.sensor.DiLinkSensorPrivacyManager.DiLink5_1_Decouple_OnSensorPrivacyListeners.1
                @Keep
                public void onSensorPrivacyChanged(int i, boolean z) {
                    iDiLinkOnSensorPrivacyListener.onSensorPrivacyChanged(i, z);
                }
            };
        }
    }

    public DiLinkSensorPrivacyManager(Context context) {
        this.f1568a = (SensorPrivacyManager) context.getSystemService(SensorPrivacyManager.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, SensorPrivacyManager.OnSensorPrivacyChangedListener onSensorPrivacyChangedListener) {
        this.f1568a.removeSensorPrivacyListener(i, onSensorPrivacyChangedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(int i, SensorPrivacyManager.OnSensorPrivacyChangedListener onSensorPrivacyChangedListener) {
        this.f1568a.addSensorPrivacyListener(i, onSensorPrivacyChangedListener);
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public void addSensorPrivacyListener(Context context, final int i, IDiLinkOnSensorPrivacyListener iDiLinkOnSensorPrivacyListener) {
        this.f1569b.addCallbackIgnoreRemoteException(iDiLinkOnSensorPrivacyListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.hardware.sensor.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1574a.b(i, (SensorPrivacyManager.OnSensorPrivacyChangedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public List<Integer> getAvailableSensorIds(Context context) {
        return BydSensorPrivacyManager.getInstance(context).getAvailableSensorIds();
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public long getSensorPrivacyDeadTime(Context context, int i) {
        return BydSensorPrivacyManager.getInstance(context).getSensorPrivacyDeadTime(i);
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public long getSensorStateForPeriod(Context context, int i) {
        return BydSensorPrivacyManager.getInstance(context).getSensorStateForPeriod(i);
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public boolean isSensorPrivacyEnabled(Context context, int i) {
        return this.f1568a.isSensorPrivacyEnabled(i);
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public void removeSensorPrivacyListener(Context context, final int i, IDiLinkOnSensorPrivacyListener iDiLinkOnSensorPrivacyListener) {
        this.f1569b.removeCallbackIgnoreRemoteException(iDiLinkOnSensorPrivacyListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.hardware.sensor.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1572a.a(i, (SensorPrivacyManager.OnSensorPrivacyChangedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public void setSensorPrivacy(Context context, int i, int i2, boolean z, long j) {
        BydSensorPrivacyManager.getInstance(context).setSensorPrivacy(i, i2, z, j);
    }
}
