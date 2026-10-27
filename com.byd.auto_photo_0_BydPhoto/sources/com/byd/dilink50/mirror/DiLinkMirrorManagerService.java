package com.byd.dilink50.mirror;

import android.content.Context;
import android.content.Intent;
import android.os.ServiceManager;
import androidx.annotation.Keep;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.mirror.IDiLinkMirrorManagerService;
import com.byd.dilink.mirror.IDiLinkMirrorService;
import com.byd.dilink.mirror.IDiLinkMirrorState;
import com.byd.foundation.annotation.ServiceImpl;
import com.byd.mirror.IMirrorManagerService;
import com.byd.mirror.IMirrorService;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_0})
@ServiceImpl(service = IDiLinkMirrorManagerService.class, singleton = true)
public class DiLinkMirrorManagerService implements IDiLinkMirrorManagerService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DiLink5_0_MirrorMirrorServices f1432a = new DiLink5_0_MirrorMirrorServices();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private IMirrorManagerService f1433b = IMirrorManagerService.Stub.asInterface(ServiceManager.getService("mirror"));

    public static class DiLink5_0_MirrorMirrorServices extends DiLinkCallbackHolders<IDiLinkMirrorService, IMirrorService> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IMirrorService createInnerCallback(final IDiLinkMirrorService iDiLinkMirrorService) {
            return new IMirrorService.Stub() { // from class: com.byd.dilink50.mirror.DiLinkMirrorManagerService.DiLink5_0_MirrorMirrorServices.1
                @Keep
                public void cancelStartActivityForMirror(String str, Intent intent) {
                    iDiLinkMirrorService.cancelStartActivityForMirror(str, intent);
                }

                @Keep
                public void startMirrorAppIfNeeded(String str) {
                    iDiLinkMirrorService.startMirrorAppIfNeeded(str);
                }
            };
        }
    }

    public DiLinkMirrorManagerService(Context context) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(IMirrorService iMirrorService) {
        this.f1433b.registerMirrorService(iMirrorService);
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorManagerService
    public void registerMirrorService(IDiLinkMirrorService iDiLinkMirrorService) {
        this.f1432a.addCallback(iDiLinkMirrorService, new IDiLinkCallbackHandler() { // from class: com.byd.dilink50.mirror.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1438a.a((IMirrorService) obj);
            }
        });
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorManagerService
    public void setMirrorState(IDiLinkMirrorState iDiLinkMirrorState) {
        this.f1433b.setMirrorState(((BA1aabBX.BA1aabBX.BA1aabBX.Xb1BBBXB.a) iDiLinkMirrorState).a());
    }
}
