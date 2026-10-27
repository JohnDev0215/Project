package com.byd.dilink_fse.cdrservice;

import android.cdrservice.IBYDCDRService;
import android.cdrservice.IBYDCDRUser;
import android.os.ServiceManager;
import android.util.Log;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.cdrservice.IDiLinkCDRService;
import com.byd.dilink.cdrservice.IDiLinkCDRUser;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_FSE_RUI, DiLink.VERSION_NAME_FSE_5_0, DiLink.VERSION_NAME_FSE_5_1})
@ServiceImpl(service = IDiLinkCDRService.class, singleton = true)
public class DiLinkCDRService implements IDiLinkCDRService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private IBYDCDRService f2362a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink_CDRServices f2363b = new DiLink_CDRServices();

    public static class DiLink_CDRServices extends DiLinkCallbackHolders<IDiLinkCDRUser, IBYDCDRUser> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IBYDCDRUser createInnerCallback(final IDiLinkCDRUser iDiLinkCDRUser) {
            return new IBYDCDRUser.Stub() { // from class: com.byd.dilink_fse.cdrservice.DiLinkCDRService.DiLink_CDRServices.1
                @Keep
                public int setCommand(int i, int i2) {
                    return iDiLinkCDRUser.setCommand(i, i2);
                }
            };
        }
    }

    public DiLinkCDRService() {
        if (ServiceManager.checkService("IBYDCDRService") != null) {
            this.f2362a = IBYDCDRService.Stub.asInterface();
        } else {
            Log.d("DiLinkCDRService", "DiLinkCDriesrvice: ServiceManager.checkService(IBYDCDriesrvice.NAME)==null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(IBYDCDRUser iBYDCDRUser) {
        this.f2362a.unregisterUser(iBYDCDRUser);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(IBYDCDRUser iBYDCDRUser) {
        this.f2362a.registerUser(iBYDCDRUser);
    }

    @Override // com.byd.dilink.cdrservice.IDiLinkCDRService
    public boolean registerUser(@NonNull IDiLinkCDRUser iDiLinkCDRUser) {
        return this.f2363b.addCallback(iDiLinkCDRUser, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_fse.cdrservice.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2366a.b((IBYDCDRUser) obj);
            }
        });
    }

    @Override // com.byd.dilink.cdrservice.IDiLinkCDRService
    public int setCommand(int i, int i2) {
        return this.f2362a.setCommand(i, i2);
    }

    @Override // com.byd.dilink.cdrservice.IDiLinkCDRService
    public boolean unregisterUser(@NonNull IDiLinkCDRUser iDiLinkCDRUser) {
        return this.f2363b.removeCallback(iDiLinkCDRUser, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_fse.cdrservice.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2367a.a((IBYDCDRUser) obj);
            }
        });
    }
}
