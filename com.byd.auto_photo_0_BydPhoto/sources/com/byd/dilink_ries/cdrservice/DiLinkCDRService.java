package com.byd.dilink_ries.cdrservice;

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
@Platform(versions = {DiLink.VERSION_NAME_RIES, DiLink.VERSION_NAME_RIES_5_0})
@ServiceImpl(service = IDiLinkCDRService.class, singleton = true)
public class DiLinkCDRService implements IDiLinkCDRService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private IBYDCDRService f2578a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink_ries_CDriesrvices f2579b = new DiLink_ries_CDriesrvices();

    public static class DiLink_ries_CDriesrvices extends DiLinkCallbackHolders<IDiLinkCDRUser, IBYDCDRUser> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IBYDCDRUser createInnerCallback(final IDiLinkCDRUser iDiLinkCDRUser) {
            return new IBYDCDRUser.Stub() { // from class: com.byd.dilink_ries.cdrservice.DiLinkCDRService.DiLink_ries_CDriesrvices.1
                @Keep
                public int setCommand(int i, int i2) {
                    return iDiLinkCDRUser.setCommand(i, i2);
                }
            };
        }
    }

    public DiLinkCDRService() {
        if (ServiceManager.checkService("IBYDCDRService") != null) {
            this.f2578a = IBYDCDRService.Stub.asInterface();
        } else {
            Log.d("DiLinkCDriesrvice", "DiLinkCDriesrvice: ServiceManager.checkService(IBYDCDriesrvice.NAME)==null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(IBYDCDRUser iBYDCDRUser) {
        this.f2578a.unregisterUser(iBYDCDRUser);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(IBYDCDRUser iBYDCDRUser) {
        this.f2578a.registerUser(iBYDCDRUser);
    }

    @Override // com.byd.dilink.cdrservice.IDiLinkCDRService
    public boolean registerUser(@NonNull IDiLinkCDRUser iDiLinkCDRUser) {
        return this.f2579b.addCallback(iDiLinkCDRUser, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_ries.cdrservice.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2582a.d((IBYDCDRUser) obj);
            }
        });
    }

    @Override // com.byd.dilink.cdrservice.IDiLinkCDRService
    public int setCommand(int i, int i2) {
        return this.f2578a.setCommand(i, i2);
    }

    @Override // com.byd.dilink.cdrservice.IDiLinkCDRService
    public boolean unregisterUser(@NonNull IDiLinkCDRUser iDiLinkCDRUser) {
        return this.f2579b.removeCallback(iDiLinkCDRUser, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_ries.cdrservice.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2583a.a((IBYDCDRUser) obj);
            }
        });
    }
}
