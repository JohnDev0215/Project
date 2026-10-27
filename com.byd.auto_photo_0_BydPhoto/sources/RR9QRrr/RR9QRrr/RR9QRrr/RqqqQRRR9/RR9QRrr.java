package RR9QRrr.RR9QRrr.RR9QRrr.RqqqQRRR9;

import android.content.Context;
import android.location.ILocationManager;
import android.location.LocationManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.location.IDiLinkLocationEnabledListener;
import com.byd.dilink.location.IDiLinkLocationManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkLocationManager.class, singleton = true)
public class RR9QRrr implements IDiLinkLocationManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ILocationManager f118a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final LocationManager f119b;

    public RR9QRrr(Context context) throws RemoteException {
        IBinder service = ServiceManager.getService("location");
        if (service == null) {
            throw new RemoteException("Cannot retrieve Location Manager");
        }
        this.f118a = ILocationManager.Stub.asInterface(service);
        this.f119b = (LocationManager) context.getSystemService("location");
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean addLocationEnabledListener(@NonNull IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener) {
        throw new UnsupportedTargetPlatformException("rcs 不存在addLocationEnabledListener方法");
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean addLocationEnabledListener(@NonNull IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener, String str, @Nullable String str2, String str3) {
        throw new UnsupportedTargetPlatformException("rcs 不存在addLocationEnabledListener方法");
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public long getLocationEnabledDeadTime() {
        throw new UnsupportedTargetPlatformException("rcs不存在getLocationEnabledDeadTime()方法");
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public long getLocationEnabledDeadTimeForUser(int i) {
        throw new UnsupportedTargetPlatformException("rcs不存在getLocationEnabledDeadTimeForUser(int userId)方法");
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public int getLocationEnabledTypeForUser() {
        throw new UnsupportedTargetPlatformException("rcs没有getLocationEnabledTypeForUser()方法");
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public int getLocationEnabledTypeForUser(int i) {
        throw new UnsupportedTargetPlatformException("rcs没有getLocationEnabledTypeForUser(int userId)方法");
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean isAutomotiveGnssSuspended() {
        throw new UnsupportedTargetPlatformException("rcs不存在此接口");
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean isLocationEnabledForUser(UserHandle userHandle) {
        return this.f119b.isLocationEnabledForUser(userHandle);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void removeLocationEnabledListener(@NonNull IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener) {
        throw new UnsupportedTargetPlatformException("rcs不存在此接口");
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void setAutomotiveGnssSuspended(boolean z) {
        throw new UnsupportedTargetPlatformException("rcs不存在此接口");
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void setLocationEnabledTypeForUser(int i) {
        throw new UnsupportedTargetPlatformException("rcs不存在setLocationEnabledTypeForUser(int type)方法");
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void setLocationEnabledTypeForUser(int i, int i2) {
        throw new UnsupportedTargetPlatformException("rcs不存在setLocationEnabledTypeForUser(int type, int userId)方法");
    }
}
