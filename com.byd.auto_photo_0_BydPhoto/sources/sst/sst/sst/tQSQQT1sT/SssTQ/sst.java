package sst.sst.sst.tQSQQT1sT.SssTQ;

import android.car.Car;
import android.content.Context;
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
import sst.sst.sst.StsT.t1sT.b;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_RIES, DiLink.VERSION_NAME_RIES_5_0})
@ServiceImpl(service = IDiLinkPackageManager.class, singleton = true)
public class sst implements IDiLinkPackageManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final IPackageManager f3237a = IPackageManager.Stub.asInterface(ServiceManager.getService(Car.PACKAGE_SERVICE));

    public sst(Context context) {
    }

    @Override // com.byd.dilink.content.pm.IDiLinkPackageManager
    public PackageInstaller getPackageInstaller(int i) {
        throw new UnsupportedTargetPlatformException("ries 不支持该函数");
    }

    @Override // com.byd.dilink.content.pm.IDiLinkPackageManager
    public IDiLinkParceledListSlice<?> getPersistentApplicationsAsUser(int i, int i2) {
        ParceledListSlice persistentApplicationsAsUser = this.f3237a.getPersistentApplicationsAsUser(i, i2);
        if (persistentApplicationsAsUser == null) {
            return null;
        }
        return b.a(persistentApplicationsAsUser);
    }

    @Override // com.byd.dilink.content.pm.IDiLinkPackageManager
    public boolean isPackageInstalling(String str) {
        return this.f3237a.isPackageInstalling(str);
    }
}
