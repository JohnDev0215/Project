package com.byd.dilink_DiR.mirror;

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
@Platform(versions = {DiLink.VERSION_NAME_DiR_RUI})
@ServiceImpl(service = IDiLinkMirrorManager.class, singleton = true)
public class DiLinkMirrorManager implements IDiLinkMirrorManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DiLinkDiR_MirrorListeners f2238a = new DiLinkDiR_MirrorListeners();

    public static class DiLinkDiR_MirrorListeners extends DiLinkCallbackHolders<IDiLinkMirrorListener, MirrorManager.Listener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public MirrorManager.Listener createInnerCallback(final IDiLinkMirrorListener iDiLinkMirrorListener) {
            return new MirrorManager.Listener() { // from class: com.byd.dilink_DiR.mirror.DiLinkMirrorManager.DiLinkDiR_MirrorListeners.1
                @Keep
                public void onMirrorStateChanged(MirrorState mirrorState) {
                    iDiLinkMirrorListener.onMirrorStateChanged(new mNTT77n7n.mNTT77n7n.mNTT77n7n.N7nMTTTTM.a(mirrorState));
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void c(Looper looper, MirrorManager.Listener listener) {
        MirrorManager.getInstance().registerListener(listener, looper);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void d(MirrorManager.Listener listener) {
        MirrorManager.getInstance().unregisterListener(listener);
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorManager
    public IDiLinkMirrorState getMirrorState() {
        MirrorState mirrorState = MirrorManager.getInstance().getMirrorState();
        if (mirrorState == null) {
            return null;
        }
        return new mNTT77n7n.mNTT77n7n.mNTT77n7n.N7nMTTTTM.a(mirrorState);
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorManager
    public void registerListener(IDiLinkMirrorListener iDiLinkMirrorListener) {
        registerListener(iDiLinkMirrorListener, null);
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorManager
    public void registerListener(IDiLinkMirrorListener iDiLinkMirrorListener, final Looper looper) {
        this.f2238a.addCallbackIgnoreRemoteException(iDiLinkMirrorListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.mirror.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkMirrorManager.c(looper, (MirrorManager.Listener) obj);
            }
        });
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorManager
    public void unregisterListener(IDiLinkMirrorListener iDiLinkMirrorListener) {
        this.f2238a.removeCallbackIgnoreRemoteException(iDiLinkMirrorListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.mirror.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkMirrorManager.d((MirrorManager.Listener) obj);
            }
        });
    }
}
