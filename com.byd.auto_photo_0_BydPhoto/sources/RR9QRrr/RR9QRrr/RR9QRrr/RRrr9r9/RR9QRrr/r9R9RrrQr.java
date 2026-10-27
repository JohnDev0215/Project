package RR9QRrr.RR9QRrr.RR9QRrr.RRrr9r9.RR9QRrr;

import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkBmmCameraInfo.class, singleton = true)
public class r9R9RrrQr implements IDiLinkBmmCameraInfo {
    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public ArrayList<String> getAvailableCameraTypeList() {
        throw new UnsupportedTargetPlatformException("RCS 没有 BmmCameraInfo 这个类！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public int getCameraId(String str) {
        throw new UnsupportedTargetPlatformException("RCS 没有 BmmCameraInfo 这个类！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public int getCameraNumbers() {
        throw new UnsupportedTargetPlatformException("RCS 没有 BmmCameraInfo 这个类！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public String getCameraTag(int i) {
        throw new UnsupportedTargetPlatformException("RCS 没有 BmmCameraInfo 这个类！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public float getDefaultFps(int i) {
        throw new UnsupportedTargetPlatformException("RCS 没有 BmmCameraInfo 这个类！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public int getDefaultPreviewHeight(int i) {
        throw new UnsupportedTargetPlatformException("RCS 没有 BmmCameraInfo 这个类！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public int getDefaultPreviewWidth(int i) {
        throw new UnsupportedTargetPlatformException("RCS 没有 BmmCameraInfo 这个类！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public String getSensorModule(int i) {
        throw new UnsupportedTargetPlatformException("RCS 没有 BmmCameraInfo 这个类！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public String getSupplier(int i) {
        throw new UnsupportedTargetPlatformException("RCS 没有 BmmCameraInfo 这个类！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public ArrayList<String> getValidCameraTag() {
        throw new UnsupportedTargetPlatformException("RCS 没有 BmmCameraInfo 这个类！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public boolean isValidCamera(int i) {
        throw new UnsupportedTargetPlatformException("RCS 没有 BmmCameraInfo 这个类！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public boolean isValidCamera(@NonNull String str) {
        throw new UnsupportedTargetPlatformException("RCS 没有 BmmCameraInfo 这个类！");
    }
}
