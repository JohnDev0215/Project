package jViVV55i.jViVV55i.jViVV55i.IiJI5I55;

import android.os.Looper;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.mirror.IDiLinkMirrorListener;
import com.byd.dilink.mirror.IDiLinkMirrorManager;
import com.byd.dilink.mirror.IDiLinkMirrorState;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkMirrorManager.class, singleton = true)
public class jViVV55i implements IDiLinkMirrorManager {
    @Override // com.byd.dilink.mirror.IDiLinkMirrorManager
    public IDiLinkMirrorState getMirrorState() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 MirrorManager 类！");
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorManager
    public void registerListener(IDiLinkMirrorListener iDiLinkMirrorListener) {
        registerListener(iDiLinkMirrorListener, null);
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorManager
    public void registerListener(IDiLinkMirrorListener iDiLinkMirrorListener, Looper looper) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 MirrorManager 类！");
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorManager
    public void unregisterListener(IDiLinkMirrorListener iDiLinkMirrorListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 MirrorManager 类！");
    }
}
