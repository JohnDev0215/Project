package RR9QRrr.RR9QRrr.RR9QRrr.RRrr9r9.r9R9RrrQr;

import android.content.Context;
import android.hardware.SensorPrivacyManager;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.sensor.IDiLinkOnSensorPrivacyListener;
import com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkSensorPrivacyManager.class, singleton = true)
public class RR9QRrr implements IDiLinkSensorPrivacyManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private SensorPrivacyManager f116a;

    public RR9QRrr(Context context) {
        this.f116a = SensorPrivacyManager.getInstance(context);
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public void addSensorPrivacyListener(Context context, int i, IDiLinkOnSensorPrivacyListener iDiLinkOnSensorPrivacyListener) {
        throw new UnsupportedTargetPlatformException("rcs不存在addSensorPrivacyListener(Context context, int sensor, IDiLinkOnSensorPrivacyListener listener)方法");
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public List<Integer> getAvailableSensorIds(Context context) {
        throw new UnsupportedTargetPlatformException("rcs不存在getAvailableSensorIds方法");
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public long getSensorPrivacyDeadTime(Context context, int i) {
        throw new UnsupportedTargetPlatformException("rcs不存在getSensorPrivacyDeadTime(Context context, int sensor)方法");
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public long getSensorStateForPeriod(Context context, int i) {
        throw new UnsupportedTargetPlatformException("rcs不存在getSensorStateForPeriod(Context context, int sensor)方法");
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public boolean isSensorPrivacyEnabled(Context context, int i) {
        throw new UnsupportedTargetPlatformException("rcs不存在isSensorPrivacyEnabled(Context context, int sensor)方法");
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public void removeSensorPrivacyListener(Context context, int i, IDiLinkOnSensorPrivacyListener iDiLinkOnSensorPrivacyListener) {
        throw new UnsupportedTargetPlatformException("rcs不存在removeSensorPrivacyListener(Context context, int sensor, IDiLinkOnSensorPrivacyListener listener)方法");
    }

    @Override // com.byd.dilink.hardware.sensor.IDiLinkSensorPrivacyManager
    public void setSensorPrivacy(Context context, int i, int i2, boolean z, long j) {
        throw new UnsupportedTargetPlatformException("rcs不存在setSensorPrivacy(Context context, int source, int sensor, boolean enable, long periodType)");
    }
}
