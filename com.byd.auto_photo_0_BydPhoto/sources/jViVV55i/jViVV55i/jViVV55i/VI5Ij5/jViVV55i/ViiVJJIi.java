package jViVV55i.jViVV55i.jViVV55i.VI5Ij5.jViVV55i;

import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkBmmCameraInfo.class, singleton = true)
public class ViiVJJIi implements IDiLinkBmmCameraInfo {
    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public ArrayList<String> getAvailableCameraTypeList() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 BmmCameraInfo！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public int getCameraId(String str) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 BmmCameraInfo！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public int getCameraNumbers() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 BmmCameraInfo！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public String getCameraTag(int i) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 BmmCameraInfo！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public float getDefaultFps(int i) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 BmmCameraInfo！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public int getDefaultPreviewHeight(int i) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 BmmCameraInfo！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public int getDefaultPreviewWidth(int i) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 BmmCameraInfo！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public String getSensorModule(int i) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 BmmCameraInfo！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public String getSupplier(int i) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 BmmCameraInfo！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public ArrayList<String> getValidCameraTag() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 BmmCameraInfo！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public boolean isValidCamera(int i) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 BmmCameraInfo！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public boolean isValidCamera(@NonNull String str) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 BmmCameraInfo！");
    }
}
