package com.byd.dilink51_main.mirror;

import android.os.Looper;
import androidx.annotation.Keep;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.mirror.IDiLinkMirrorListener;
import com.byd.dilink.mirror.IDiLinkMirrorManager;
import com.byd.dilink.mirror.IDiLinkMirrorState;
import com.byd.foundation.annotation.ServiceImpl;
import com.byd.mirror.MirrorManager;
import com.byd.mirror.MirrorState;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkMirrorManager.class, singleton = true)
public class DiLinkMirrorManager implements IDiLinkMirrorManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DiLink5_1_MirrorListeners f1810a = new DiLink5_1_MirrorListeners();

    public static class DiLink5_1_MirrorListeners extends DiLinkCallbackHolders<IDiLinkMirrorListener, MirrorManager.Listener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public MirrorManager.Listener createInnerCallback(final IDiLinkMirrorListener iDiLinkMirrorListener) {
            return new MirrorManager.Listener() { // from class: com.byd.dilink51_main.mirror.DiLinkMirrorManager.DiLink5_1_MirrorListeners.1
                @Keep
                public void onMirrorStateChanged(MirrorState mirrorState) {
                    iDiLinkMirrorListener.onMirrorStateChanged(new DDC.DDC.DDC.DcDc2c22Y.a(mirrorState));
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(Looper looper, MirrorManager.Listener listener) {
        MirrorManager.getInstance().registerListener(listener, looper);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(MirrorManager.Listener listener) {
        MirrorManager.getInstance().unregisterListener(listener);
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorManager
    public IDiLinkMirrorState getMirrorState() {
        MirrorState mirrorState = MirrorManager.getInstance().getMirrorState();
        if (mirrorState == null) {
            return null;
        }
        return new DDC.DDC.DDC.DcDc2c22Y.a(mirrorState);
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorManager
    public void registerListener(IDiLinkMirrorListener iDiLinkMirrorListener) {
        registerListener(iDiLinkMirrorListener, null);
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorManager
    public void registerListener(IDiLinkMirrorListener iDiLinkMirrorListener, final Looper looper) {
        this.f1810a.addCallbackIgnoreRemoteException(iDiLinkMirrorListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.mirror.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkMirrorManager.a(looper, (MirrorManager.Listener) obj);
            }
        });
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorManager
    public void unregisterListener(IDiLinkMirrorListener iDiLinkMirrorListener) {
        this.f1810a.removeCallbackIgnoreRemoteException(iDiLinkMirrorListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.mirror.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkMirrorManager.b((MirrorManager.Listener) obj);
            }
        });
    }
}
