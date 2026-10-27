package com.byd.dilink_rcs.mirror;

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
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkMirrorManagerService.class, singleton = true)
public class DiLinkMirrorManagerService implements IDiLinkMirrorManagerService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DiLink_rcs_MirrorMirrorcsrvices f2503a = new DiLink_rcs_MirrorMirrorcsrvices();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private IMirrorManagerService f2504b = IMirrorManagerService.Stub.asInterface(ServiceManager.getService("mirror"));

    public static class DiLink_rcs_MirrorMirrorcsrvices extends DiLinkCallbackHolders<IDiLinkMirrorService, IMirrorService> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IMirrorService createInnerCallback(final IDiLinkMirrorService iDiLinkMirrorService) {
            return new IMirrorService.Stub() { // from class: com.byd.dilink_rcs.mirror.DiLinkMirrorManagerService.DiLink_rcs_MirrorMirrorcsrvices.1
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
        this.f2504b.registerMirrorService(iMirrorService);
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorManagerService
    public void registerMirrorService(IDiLinkMirrorService iDiLinkMirrorService) {
        this.f2503a.addCallback(iDiLinkMirrorService, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_rcs.mirror.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2509a.a((IMirrorService) obj);
            }
        });
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorManagerService
    public void setMirrorState(IDiLinkMirrorState iDiLinkMirrorState) {
        this.f2504b.setMirrorState(((RR9QRrr.RR9QRrr.RR9QRrr.RQRrRRRRQ.a) iDiLinkMirrorState).a());
    }
}
