package jViVV55i.jViVV55i.jViVV55i.jj.jVJIV5i;

import android.car.Car;
import android.content.Context;
import android.content.pm.IPackageManager;
import android.content.pm.PackageInstaller;
import android.os.ServiceManager;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.am.IDiLinkParceledListSlice;
import com.byd.dilink.content.pm.IDiLinkPackageManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkPackageManager.class, singleton = true)
public class jViVV55i implements IDiLinkPackageManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final IPackageManager f3066a = IPackageManager.Stub.asInterface(ServiceManager.getService(Car.PACKAGE_SERVICE));

    public jViVV55i(Context context) {
    }

    @Override // com.byd.dilink.content.pm.IDiLinkPackageManager
    public PackageInstaller getPackageInstaller(int i) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口");
    }

    @Override // com.byd.dilink.content.pm.IDiLinkPackageManager
    public IDiLinkParceledListSlice<?> getPersistentApplicationsAsUser(int i, int i2) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口");
    }

    @Override // com.byd.dilink.content.pm.IDiLinkPackageManager
    public boolean isPackageInstalling(String str) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口");
    }
}
