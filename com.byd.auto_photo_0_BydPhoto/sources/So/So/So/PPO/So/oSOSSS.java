package So.So.So.PPO.So;

import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.camera.IDiLinkBydCameraConflictManager;
import com.byd.dilink.hardware.camera.IDiLinkConflictedCallback;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_FSE_RUI, DiLink.VERSION_NAME_FSE_5_0, DiLink.VERSION_NAME_FSE_5_1})
@ServiceImpl(service = IDiLinkBydCameraConflictManager.class, singleton = true)
public class oSOSSS implements IDiLinkBydCameraConflictManager {
    @Override // com.byd.dilink.hardware.camera.IDiLinkBydCameraConflictManager
    public boolean addCameraConflictCallback(int i, @NonNull IDiLinkConflictedCallback iDiLinkConflictedCallback) {
        throw new UnsupportedTargetPlatformException("RSE 没有 addCameraConflictCallback 这个函数！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBydCameraConflictManager
    public boolean removeCameraConflictCallback(int i, @NonNull IDiLinkConflictedCallback iDiLinkConflictedCallback) {
        throw new UnsupportedTargetPlatformException("RSE 没有 removeCameraConflictCallback 这个函数！");
    }
}
