package com.byd.dilink_fse.hardware.sensor;

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
@Platform(versions = {DiLink.VERSION_NAME_FSE_RUI, DiLink.VERSION_NAME_FSE_5_0, DiLink.VERSION_NAME_FSE_5_1})
@ServiceImpl(service = IDiLinkSensorPrivacyManager.class, singleton = true)
public class DiLinkSensorPrivacyManager implements IDiLinkSensorPrivacyManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private SensorPrivacyManager f2368a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f2369b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private DiLink_fse_OnSensorPrivacyListeners f2370c = new DiLink_fse_OnSensorPrivacyListeners();

    public static class DiLink_fse_OnSensorPrivacyListeners extends DiLinkCallbackHolders<IDiLinkOnSensorPrivacyListener, SensorPrivacyManager.OnSensorPrivacyChangedListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public SensorPrivacyManager.OnSensorPrivacyChangedListener createInnerCallback(final IDiLinkOnSensorPrivacyListener iDiLinkOnSensorPrivacyListener) {
            return new SensorPrivacyManager.OnSensorPrivacyChangedListener() { // from class: com.byd.dilink_fse.hardware.sensor.DiLinkSensorPrivacyManager.DiLink_fse_OnSensorPrivacyListeners.1
                @Keep
                public void onSensorPrivacyChanged(int i, boolean z) {
                    iDiLinkOnSensorPrivacyListener.onSensorPrivacyChanged(i, z);
                }
            };
        }
    }

    public DiLinkSensorPrivacyManager(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f2369b = applicationContext;
        this.f2368a = (SensorPrivacyManager) applicationContext.getSystemService(SensorPrivacyManager.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(Context context, int i, SensorPrivacyManager.OnSensorPrivacyChangedListener onSensorPrivacyChangedListener) {
        SensorPrivacyManager.getInstance(context).removeSensorPrivacyListener(i, onSensorPrivacyChangedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(Context context, int i, SensorPrivacyManager.OnSensorPrivacyChangedListener onSensorPrivacyChangedListener) {
        SensorPrivacyManager.getInstance(context).addSensorPrivacyListener(i, onSensorPrivacyChangedListener);
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public void addSensorPrivacyListener(final Context context, final int i, IDiLinkOnSensorPrivacyListener iDiLinkOnSensorPrivacyListener) {
        this.f2370c.addCallbackIgnoreRemoteException(iDiLinkOnSensorPrivacyListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_fse.hardware.sensor.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkSensorPrivacyManager.b(context, i, (SensorPrivacyManager.OnSensorPrivacyChangedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public List<Integer> getAvailableSensorIds(Context context) {
        return SensorPrivacyManager.getInstance(this.f2369b).getAvailableSensorIds();
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public long getSensorPrivacyDeadTime(Context context, int i) {
        return SensorPrivacyManager.getInstance(this.f2369b).getSensorPrivacyDeadTime(i);
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public long getSensorStateForPeriod(Context context, int i) {
        return SensorPrivacyManager.getInstance(this.f2369b).getSensorStateForPeriod(i);
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public boolean isSensorPrivacyEnabled(Context context, int i) {
        return SensorPrivacyManager.getInstance(context).isSensorPrivacyEnabled(i);
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public void removeSensorPrivacyListener(final Context context, final int i, IDiLinkOnSensorPrivacyListener iDiLinkOnSensorPrivacyListener) {
        this.f2370c.removeCallbackIgnoreRemoteException(iDiLinkOnSensorPrivacyListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_fse.hardware.sensor.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkSensorPrivacyManager.a(context, i, (SensorPrivacyManager.OnSensorPrivacyChangedListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public void setSensorPrivacy(Context context, int i, int i2, boolean z, long j) {
        SensorPrivacyManager.getInstance(this.f2369b).setSensorPrivacy(i, i2, z, j);
    }
}
