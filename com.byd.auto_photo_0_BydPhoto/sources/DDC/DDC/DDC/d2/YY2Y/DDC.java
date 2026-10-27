package DDC.DDC.DDC.d2.YY2Y;

import DDC.DDC.DDC.dcd.CCdDCcD.c;
import android.car.Car;
import android.content.pm.IPackageManager;
import android.content.pm.PackageInstaller;
import android.content.pm.ParceledListSlice;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.am.IDiLinkParceledListSlice;
import com.byd.dilink.content.pm.IDiLinkPackageManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkPackageManager.class, singleton = true)
public class DDC implements IDiLinkPackageManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final IPackageManager f35a;

    public DDC() throws RemoteException {
        IBinder service = ServiceManager.getService(Car.PACKAGE_SERVICE);
        if (service == null) {
            throw new RemoteException("Cannot retrieve package service");
        }
        this.f35a = IPackageManager.Stub.asInterface(service);
    }

    @Override // com.byd.dilink.content.pm.IDiLinkPackageManager
    public PackageInstaller getPackageInstaller(int i) {
        throw new UnsupportedTargetPlatformException("di5.1 不支持该函数");
    }

    @Override // com.byd.dilink.content.pm.IDiLinkPackageManager
    public IDiLinkParceledListSlice<?> getPersistentApplicationsAsUser(int i, int i2) {
        ParceledListSlice persistentApplicationsAsUser = this.f35a.getPersistentApplicationsAsUser(i, i2);
        if (persistentApplicationsAsUser == null) {
            return null;
        }
        return c.a(persistentApplicationsAsUser);
    }

    @Override // com.byd.dilink.content.pm.IDiLinkPackageManager
    public boolean isPackageInstalling(String str) {
        return this.f35a.isPackageInstalling(str);
    }
}
