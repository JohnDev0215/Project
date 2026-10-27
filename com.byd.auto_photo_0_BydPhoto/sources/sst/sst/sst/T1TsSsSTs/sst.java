package sst.sst.sst.T1TsSsSTs;

import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.ServiceManager;
import b.a.g.a;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hotfix.IDiLinkUpdateService;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_RIES, DiLink.VERSION_NAME_RIES_5_0})
@ServiceImpl(service = IDiLinkUpdateService.class, singleton = true)
public class sst implements IDiLinkUpdateService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a f3227a;

    public sst() throws RemoteException {
        IBinder service = ServiceManager.getService("byd_updated_service");
        if (service == null) {
            throw new RemoteException("byd_updated_service not found!");
        }
        this.f3227a = a.AbstractBinderC0016a.a(service);
    }

    @Override // com.byd.dilink.hotfix.IDiLinkUpdateService
    public int disableElfFix() {
        return this.f3227a.disableElfFix();
    }

    @Override // com.byd.dilink.hotfix.IDiLinkUpdateService
    public int dpkInstall(String str, ParcelFileDescriptor parcelFileDescriptor) {
        return this.f3227a.dpkInstall(str, parcelFileDescriptor);
    }

    @Override // com.byd.dilink.hotfix.IDiLinkUpdateService
    public List<String> dpkPayload(String str) {
        return this.f3227a.dpkPayload(str);
    }

    @Override // com.byd.dilink.hotfix.IDiLinkUpdateService
    public int dpkStatus(String str) {
        return this.f3227a.dpkStatus(str);
    }

    @Override // com.byd.dilink.hotfix.IDiLinkUpdateService
    public int dpkUninstall(String str) {
        return this.f3227a.dpkUninstall(str);
    }

    @Override // com.byd.dilink.hotfix.IDiLinkUpdateService
    public int dpkUninstallWithReason(String str) {
        return this.f3227a.dpkUninstallWithReason(str);
    }

    @Override // com.byd.dilink.hotfix.IDiLinkUpdateService
    public int enableElfFix() {
        return this.f3227a.enableElfFix();
    }

    @Override // com.byd.dilink.hotfix.IDiLinkUpdateService
    public int hotfixInstall(String str, String str2, ParcelFileDescriptor parcelFileDescriptor) {
        return this.f3227a.hotfixInstall(str, str2, parcelFileDescriptor);
    }

    @Override // com.byd.dilink.hotfix.IDiLinkUpdateService
    public int hotfixUninstall(String str) {
        return this.f3227a.hotfixUninstall(str);
    }

    @Override // com.byd.dilink.hotfix.IDiLinkUpdateService
    public int isElfFeatureEnabled() {
        return this.f3227a.isElfFeatureEnabled();
    }

    @Override // com.byd.dilink.hotfix.IDiLinkUpdateService
    public int syncSettingsFromFileSystem() {
        return this.f3227a.syncSettingsFromFileSystem();
    }
}
