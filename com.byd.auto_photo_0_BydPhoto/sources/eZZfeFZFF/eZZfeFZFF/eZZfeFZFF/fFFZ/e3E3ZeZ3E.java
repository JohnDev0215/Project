package eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.fFFZ;

import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.mirror.IDiLinkMirrorState;
import com.byd.dilink.mirror.IDiLinkMirrorStateManager;
import com.byd.foundation.annotation.ServiceImpl;
import com.byd.mirror.MirrorState;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkMirrorStateManager.class, singleton = true)
public class e3E3ZeZ3E implements IDiLinkMirrorStateManager {
    @Override // com.byd.dilink.mirror.IDiLinkMirrorStateManager
    public IDiLinkMirrorState create(int i, String str, String[] strArr) {
        return new a(new MirrorState(i, str, strArr));
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorStateManager
    public IDiLinkMirrorState create(int i, String[] strArr) {
        return new a(new MirrorState(i, strArr));
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorStateManager
    public IDiLinkMirrorState create(boolean z, int i, String str, String[] strArr) {
        return new a(new MirrorState(z, i, str, strArr));
    }
}
