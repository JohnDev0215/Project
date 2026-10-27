package DDC.DDC.DDC.DdCcD2c.DDC;

import android.hardware.BmmCameraInfo;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkBmmCameraInfo.class, singleton = true)
public class YY2Y implements IDiLinkBmmCameraInfo {
    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public ArrayList<String> getAvailableCameraTypeList() {
        return BmmCameraInfo.getAvailableCameraTypeList();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public int getCameraId(String str) {
        return BmmCameraInfo.getCameraId(str);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public int getCameraNumbers() {
        return BmmCameraInfo.getCameraNumbers();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public String getCameraTag(int i) {
        return BmmCameraInfo.getCameraTag(i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public float getDefaultFps(int i) {
        return BmmCameraInfo.getDefaultFps(i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public int getDefaultPreviewHeight(int i) {
        return BmmCameraInfo.getDefaultPreviewHeight(i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public int getDefaultPreviewWidth(int i) {
        return BmmCameraInfo.getDefaultPreviewWidth(i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public String getSensorModule(int i) {
        return BmmCameraInfo.getSensorModule(i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public String getSupplier(int i) {
        return BmmCameraInfo.getSupplier(i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public ArrayList<String> getValidCameraTag() {
        return BmmCameraInfo.getValidCameraTag();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public boolean isValidCamera(int i) {
        return BmmCameraInfo.isValidCamera(i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBmmCameraInfo
    public boolean isValidCamera(@NonNull String str) {
        return BmmCameraInfo.isValidCamera(str);
    }
}
