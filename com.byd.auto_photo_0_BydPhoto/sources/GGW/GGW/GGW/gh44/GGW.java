package GGW.GGW.GGW.gh44;

import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.ServiceManager;
import b.a.g.a;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hotfix.IDiLinkUpdateService;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkUpdateService.class, singleton = true)
public class GGW implements IDiLinkUpdateService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a f57a;

    public GGW() throws RemoteException {
        IBinder service = ServiceManager.getService("byd_updated_service");
        if (service == null) {
            throw new RemoteException("byd_updated_service not found!");
        }
        this.f57a = a.AbstractBinderC0016a.a(service);
    }

    @Override // com.byd.dilink.hotfix.IDiLinkUpdateService
    public int disableElfFix() {
        return this.f57a.disableElfFix();
    }

    @Override // com.byd.dilink.hotfix.IDiLinkUpdateService
    public int dpkInstall(String str, ParcelFileDescriptor parcelFileDescriptor) {
        return this.f57a.dpkInstall(str, parcelFileDescriptor);
    }

    @Override // com.byd.dilink.hotfix.IDiLinkUpdateService
    public List<String> dpkPayload(String str) {
        return this.f57a.dpkPayload(str);
    }

    @Override // com.byd.dilink.hotfix.IDiLinkUpdateService
    public int dpkStatus(String str) {
        return this.f57a.dpkStatus(str);
    }

    @Override // com.byd.dilink.hotfix.IDiLinkUpdateService
    public int dpkUninstall(String str) {
        return this.f57a.dpkUninstall(str);
    }

    @Override // com.byd.dilink.hotfix.IDiLinkUpdateService
    public int dpkUninstallWithReason(String str) {
        return this.f57a.dpkUninstallWithReason(str);
    }

    @Override // com.byd.dilink.hotfix.IDiLinkUpdateService
    public int enableElfFix() {
        return this.f57a.enableElfFix();
    }

    @Override // com.byd.dilink.hotfix.IDiLinkUpdateService
    public int hotfixInstall(String str, String str2, ParcelFileDescriptor parcelFileDescriptor) {
        return this.f57a.hotfixInstall(str, str2, parcelFileDescriptor);
    }

    @Override // com.byd.dilink.hotfix.IDiLinkUpdateService
    public int hotfixUninstall(String str) {
        return this.f57a.hotfixUninstall(str);
    }

    @Override // com.byd.dilink.hotfix.IDiLinkUpdateService
    public int isElfFeatureEnabled() {
        return this.f57a.isElfFeatureEnabled();
    }

    @Override // com.byd.dilink.hotfix.IDiLinkUpdateService
    public int syncSettingsFromFileSystem() {
        return this.f57a.syncSettingsFromFileSystem();
    }
}
