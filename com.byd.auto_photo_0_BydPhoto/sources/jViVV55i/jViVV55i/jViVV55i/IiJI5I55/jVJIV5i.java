package jViVV55i.jViVV55i.jViVV55i.IiJI5I55;

import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.mirror.IDiLinkMirrorManagerService;
import com.byd.dilink.mirror.IDiLinkMirrorService;
import com.byd.dilink.mirror.IDiLinkMirrorState;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkMirrorManagerService.class, singleton = true)
public class jVJIV5i implements IDiLinkMirrorManagerService {
    public jVJIV5i(Context context) {
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorManagerService
    public void registerMirrorService(IDiLinkMirrorService iDiLinkMirrorService) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 IMirrorManagerService 类！");
    }

    @Override // com.byd.dilink.mirror.IDiLinkMirrorManagerService
    public void setMirrorState(IDiLinkMirrorState iDiLinkMirrorState) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 IMirrorManagerService 类！");
    }
}
