package eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.ZZeeef.e3E3ZeZ3E;

import android.content.Context;
import android.content.pm.BydPackageManager;
import android.content.pm.PackageInstaller;
import android.content.pm.ParceledListSlice;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.am.IDiLinkParceledListSlice;
import com.byd.dilink.content.pm.IDiLinkPackageManager;
import com.byd.foundation.annotation.ServiceImpl;
import eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.EEeZFfE.E3FZEeef.a;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkPackageManager.class, singleton = true)
public class eZZfeFZFF implements IDiLinkPackageManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f3044a;

    public eZZfeFZFF(Context context) {
        this.f3044a = context.getApplicationContext();
    }

    @Override // com.byd.dilink.content.pm.IDiLinkPackageManager
    public PackageInstaller getPackageInstaller(int i) {
        return BydPackageManager.getInstance(this.f3044a).getPackageInstaller(i);
    }

    @Override // com.byd.dilink.content.pm.IDiLinkPackageManager
    public IDiLinkParceledListSlice<?> getPersistentApplicationsAsUser(int i, int i2) {
        ParceledListSlice persistentApplicationsAsUser = BydPackageManager.getInstance(this.f3044a).getPersistentApplicationsAsUser(i, i2);
        if (persistentApplicationsAsUser == null) {
            return null;
        }
        return a.a(persistentApplicationsAsUser);
    }

    @Override // com.byd.dilink.content.pm.IDiLinkPackageManager
    public boolean isPackageInstalling(String str) {
        return BydPackageManager.getInstance(this.f3044a).isPackageInstalling(str);
    }
}
