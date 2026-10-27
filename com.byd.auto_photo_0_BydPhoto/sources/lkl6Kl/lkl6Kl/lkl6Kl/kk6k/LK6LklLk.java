package lkl6Kl.lkl6Kl.lkl6Kl.kk6k;

import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.mirror.IDiLinkMirrorManagerService;
import com.byd.dilink.mirror.IDiLinkMirrorService;
import com.byd.dilink.mirror.IDiLinkMirrorState;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_4_0})
@ServiceImpl(service = IDiLinkMirrorManagerService.class, singleton = true)
public class LK6LklLk implements IDiLinkMirrorManagerService {
    public LK6LklLk(Context context) {
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorManagerService
    public void registerMirrorService(IDiLinkMirrorService iDiLinkMirrorService) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 IMirrorManagerService 类！");
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorManagerService
    public void setMirrorState(IDiLinkMirrorState iDiLinkMirrorState) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 IMirrorManagerService 类！");
    }
}
