package lkl6Kl.lkl6Kl.lkl6Kl.kk6k;

import android.os.Looper;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.mirror.IDiLinkMirrorListener;
import com.byd.dilink.mirror.IDiLinkMirrorManager;
import com.byd.dilink.mirror.IDiLinkMirrorState;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_4_0})
@ServiceImpl(service = IDiLinkMirrorManager.class, singleton = true)
public class lkl6Kl implements IDiLinkMirrorManager {
    @Override // com.byd.dilink.mirror.IDiLinkMirrorManager
    public IDiLinkMirrorState getMirrorState() {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 MirrorManager 类！");
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorManager
    public void registerListener(IDiLinkMirrorListener iDiLinkMirrorListener) {
        registerListener(iDiLinkMirrorListener, null);
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorManager
    public void registerListener(IDiLinkMirrorListener iDiLinkMirrorListener, Looper looper) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 MirrorManager 类！");
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorManager
    public void unregisterListener(IDiLinkMirrorListener iDiLinkMirrorListener) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 MirrorManager 类！");
    }
}
