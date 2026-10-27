package So.So.So.oSOSSS.PoOSp;

import So.So.So.PopS8Sp.O8P8.b;
import android.car.Car;
import android.content.pm.IPackageManager;
import android.content.pm.PackageInstaller;
import android.content.pm.ParceledListSlice;
import android.os.ServiceManager;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.am.IDiLinkParceledListSlice;
import com.byd.dilink.content.pm.IDiLinkPackageManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_FSE_RUI, DiLink.VERSION_NAME_FSE_5_0, DiLink.VERSION_NAME_FSE_5_1})
@ServiceImpl(service = IDiLinkPackageManager.class, singleton = true)
public class So implements IDiLinkPackageManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final IPackageManager f154a = IPackageManager.Stub.asInterface(ServiceManager.getService(Car.PACKAGE_SERVICE));

    @Override // com.byd.dilink.content.pm.IDiLinkPackageManager
    public PackageInstaller getPackageInstaller(int i) {
        throw new UnsupportedTargetPlatformException("FSE 不支持该函数");
    }

    @Override // com.byd.dilink.content.pm.IDiLinkPackageManager
    public IDiLinkParceledListSlice<?> getPersistentApplicationsAsUser(int i, int i2) {
        ParceledListSlice persistentApplicationsAsUser = this.f154a.getPersistentApplicationsAsUser(i, i2);
        if (persistentApplicationsAsUser == null) {
            return null;
        }
        return b.a(persistentApplicationsAsUser);
    }

    @Override // com.byd.dilink.content.pm.IDiLinkPackageManager
    public boolean isPackageInstalling(String str) {
        return this.f154a.isPackageInstalling(str);
    }
}
