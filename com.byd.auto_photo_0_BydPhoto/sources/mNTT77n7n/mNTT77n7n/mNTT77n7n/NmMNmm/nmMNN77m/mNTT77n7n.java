package mNTT77n7n.mNTT77n7n.mNTT77n7n.nMMnMM.nmMNN77m;

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
import mNTT77n7n.mNTT77n7n.mNTT77n7n.MnmmTM7.N7nMTTTTM.a;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_DiR_RUI})
@ServiceImpl(service = IDiLinkPackageManager.class, singleton = true)
public class mNTT77n7n implements IDiLinkPackageManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final IPackageManager f3165a = IPackageManager.Stub.asInterface(ServiceManager.getService(Car.PACKAGE_SERVICE));

    @Override // com.byd.dilink.content.pm.IDiLinkPackageManager
    public PackageInstaller getPackageInstaller(int i) {
        throw new UnsupportedTargetPlatformException("DiR 不支持该函数");
    }

    @Override // com.byd.dilink.content.pm.IDiLinkPackageManager
    public IDiLinkParceledListSlice<?> getPersistentApplicationsAsUser(int i, int i2) {
        ParceledListSlice persistentApplicationsAsUser = this.f3165a.getPersistentApplicationsAsUser(i, i2);
        if (persistentApplicationsAsUser == null) {
            return null;
        }
        return a.a(persistentApplicationsAsUser);
    }

    @Override // com.byd.dilink.content.pm.IDiLinkPackageManager
    public boolean isPackageInstalling(String str) {
        return this.f3165a.isPackageInstalling(str);
    }
}
