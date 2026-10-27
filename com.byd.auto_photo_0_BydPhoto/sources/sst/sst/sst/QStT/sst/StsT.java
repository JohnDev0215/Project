package sst.sst.sst.QStT.sst;

import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_RIES, DiLink.VERSION_NAME_RIES_5_0})
@ServiceImpl(service = IDiLinkBmmCameraInfo.class, singleton = true)
public class StsT implements IDiLinkBmmCameraInfo {
    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public ArrayList<String> getAvailableCameraTypeList() {
        throw new UnsupportedTargetPlatformException("ries 没有 BmmCameraInfo！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public int getCameraId(String str) {
        throw new UnsupportedTargetPlatformException("ries 没有 BmmCameraInfo！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public int getCameraNumbers() {
        throw new UnsupportedTargetPlatformException("ries 没有 BmmCameraInfo！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public String getCameraTag(int i) {
        throw new UnsupportedTargetPlatformException("ries 没有 BmmCameraInfo！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public float getDefaultFps(int i) {
        throw new UnsupportedTargetPlatformException("ries 没有 BmmCameraInfo！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public int getDefaultPreviewHeight(int i) {
        throw new UnsupportedTargetPlatformException("ries 没有 BmmCameraInfo！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public int getDefaultPreviewWidth(int i) {
        throw new UnsupportedTargetPlatformException("ries 没有 BmmCameraInfo！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public String getSensorModule(int i) {
        throw new UnsupportedTargetPlatformException("ries 没有 BmmCameraInfo！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public String getSupplier(int i) {
        throw new UnsupportedTargetPlatformException("ries 没有 BmmCameraInfo！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public ArrayList<String> getValidCameraTag() {
        throw new UnsupportedTargetPlatformException("ries 没有 BmmCameraInfo！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public boolean isValidCamera(int i) {
        throw new UnsupportedTargetPlatformException("ries 没有 BmmCameraInfo！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public boolean isValidCamera(String str) {
        throw new UnsupportedTargetPlatformException("ries 没有 BmmCameraInfo！");
    }
}
