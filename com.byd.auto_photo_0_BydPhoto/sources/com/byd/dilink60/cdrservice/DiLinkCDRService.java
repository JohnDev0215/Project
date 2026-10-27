package com.byd.dilink60.cdrservice;

import android.cdrservice.IBYDCDRService;
import android.cdrservice.IBYDCDRUser;
import android.os.ServiceManager;
import android.util.Log;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.cdrservice.IDiLinkCDRService;
import com.byd.dilink.cdrservice.IDiLinkCDRUser;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkCDRService.class, singleton = true)
public class DiLinkCDRService implements IDiLinkCDRService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private IBYDCDRService f1957a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink_CDRServices f1958b = new DiLink_CDRServices();

    public static class DiLink_CDRServices extends DiLinkCallbackHolders<IDiLinkCDRUser, IBYDCDRUser> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IBYDCDRUser createInnerCallback(final IDiLinkCDRUser iDiLinkCDRUser) {
            return new IBYDCDRUser.Stub() { // from class: com.byd.dilink60.cdrservice.DiLinkCDRService.DiLink_CDRServices.1
                @Keep
                public int setCommand(int i, int i2) {
                    return iDiLinkCDRUser.setCommand(i, i2);
                }
            };
        }
    }

    public DiLinkCDRService() {
        if (ServiceManager.checkService("IBYDCDRService") != null) {
            this.f1957a = IBYDCDRService.Stub.asInterface();
        } else {
            Log.d("DiLinkCDRService", "DiLinkCDriesrvice: ServiceManager.checkService(IBYDCDriesrvice.NAME)==null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(IBYDCDRUser iBYDCDRUser) {
        this.f1957a.registerUser(iBYDCDRUser);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(IBYDCDRUser iBYDCDRUser) {
        this.f1957a.unregisterUser(iBYDCDRUser);
    }

    @Override // com.byd.dilink.cdrservice.IDiLinkCDRService
    public boolean registerUser(@NonNull IDiLinkCDRUser iDiLinkCDRUser) {
        return this.f1958b.addCallback(iDiLinkCDRUser, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.cdrservice.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1962a.a((IBYDCDRUser) obj);
            }
        });
    }

    @Override // com.byd.dilink.cdrservice.IDiLinkCDRService
    public int setCommand(int i, int i2) {
        return this.f1957a.setCommand(i, i2);
    }

    @Override // com.byd.dilink.cdrservice.IDiLinkCDRService
    public boolean unregisterUser(@NonNull IDiLinkCDRUser iDiLinkCDRUser) {
        return this.f1958b.removeCallback(iDiLinkCDRUser, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.cdrservice.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1961a.b((IBYDCDRUser) obj);
            }
        });
    }
}
