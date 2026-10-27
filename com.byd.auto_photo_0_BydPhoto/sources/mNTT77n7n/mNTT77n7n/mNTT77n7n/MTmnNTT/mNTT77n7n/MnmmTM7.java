package mNTT77n7n.mNTT77n7n.mNTT77n7n.MTmnNTT.mNTT77n7n;

import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.camera.IDiLinkBydCameraConflictManager;
import com.byd.dilink.hardware.camera.IDiLinkConflictedCallback;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_DiR_RUI})
@ServiceImpl(service = IDiLinkBydCameraConflictManager.class, singleton = true)
public class MnmmTM7 implements IDiLinkBydCameraConflictManager {
    @Override // com.byd.dilink.hardware.camera.IDiLinkBydCameraConflictManager
    public boolean addCameraConflictCallback(int i, @NonNull IDiLinkConflictedCallback iDiLinkConflictedCallback) {
        throw new UnsupportedTargetPlatformException("DiR不存在此接口！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBydCameraConflictManager
    public boolean removeCameraConflictCallback(int i, @NonNull IDiLinkConflictedCallback iDiLinkConflictedCallback) {
        throw new UnsupportedTargetPlatformException("DiR不存在此接口！");
    }
}
