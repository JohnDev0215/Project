package DDC.DDC.DDC.DdCcD2c.DDC;

import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.camera.IDiLinkBydCameraConflictManager;
import com.byd.dilink.hardware.camera.IDiLinkConflictedCallback;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkBydCameraConflictManager.class, singleton = true)
public class dcd implements IDiLinkBydCameraConflictManager {
    @Override // com.byd.dilink.hardware.camera.IDiLinkBydCameraConflictManager
    public boolean addCameraConflictCallback(int i, @NonNull IDiLinkConflictedCallback iDiLinkConflictedCallback) {
        throw new UnsupportedTargetPlatformException("5.1主干不存在该方法!");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBydCameraConflictManager
    public boolean removeCameraConflictCallback(int i, @NonNull IDiLinkConflictedCallback iDiLinkConflictedCallback) {
        throw new UnsupportedTargetPlatformException("5.1主干不存在该方法!");
    }
}
