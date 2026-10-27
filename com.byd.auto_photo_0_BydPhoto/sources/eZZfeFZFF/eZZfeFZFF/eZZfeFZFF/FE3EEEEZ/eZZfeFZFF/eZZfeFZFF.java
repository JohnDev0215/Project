package eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.FE3EEEEZ.eZZfeFZFF;

import android.hardware.AVMCamera;
import android.util.Log;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.camera.IDiLinkAVMCamera;
import com.byd.dilink.hardware.camera.IDiLinkAVMCameraManager;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender;
import com.byd.dilink51.hardware.camera.DiLinkAVMCamera;
import com.byd.foundation.annotation.ServiceImpl;
import eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.FE3EEEEZ.e3E3ZeZ3E.a;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkAVMCameraManager.class, singleton = true)
public class eZZfeFZFF implements IDiLinkAVMCameraManager {
    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCameraManager
    public IDiLinkAVMCamera open(int i) {
        Log.d("DiLinkAVMCameraManager", "IDiLinkAVMCameraManager.open cameraId=" + i);
        AVMCamera aVMCameraOpen = AVMCamera.open(i);
        if (aVMCameraOpen != null) {
            return new DiLinkAVMCamera(aVMCameraOpen);
        }
        Log.e("DiLinkAVMCameraManager", "IDiLinkAVMCameraManager.open return null!");
        return null;
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCameraManager
    public IDiLinkAVMCamera open(int i, int i2) {
        Log.d("DiLinkAVMCameraManager", "IDiLinkAVMCameraManager.open cameraId=" + i);
        AVMCamera aVMCameraOpen = AVMCamera.open(i, i2);
        if (aVMCameraOpen != null) {
            return new DiLinkAVMCamera(aVMCameraOpen);
        }
        Log.e("DiLinkAVMCameraManager", "IDiLinkAVMCameraManager.open return null!");
        return null;
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCameraManager
    public IDiLinkAVMCamera open(int i, IDiLinkWatermarkRender iDiLinkWatermarkRender) {
        Log.d("DiLinkAVMCameraManager", "IDiLinkAVMCameraManager.open cameraId=" + i);
        if (iDiLinkWatermarkRender == null) {
            throw new IllegalArgumentException("render can't be null");
        }
        if (!(iDiLinkWatermarkRender instanceof a)) {
            throw new IllegalArgumentException("render must be instanceof DiLinkWatermarkRender");
        }
        AVMCamera aVMCameraOpen = AVMCamera.open(i, ((a) iDiLinkWatermarkRender).a());
        if (aVMCameraOpen != null) {
            return new DiLinkAVMCamera(aVMCameraOpen);
        }
        Log.e("DiLinkAVMCameraManager", "IDiLinkAVMCameraManager.open return null!");
        return null;
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCameraManager
    public IDiLinkAVMCamera open(int i, IDiLinkWatermarkRender iDiLinkWatermarkRender, int i2) {
        Log.d("DiLinkAVMCameraManager", "IDiLinkAVMCameraManager.open cameraId=" + i);
        if (iDiLinkWatermarkRender == null) {
            throw new IllegalArgumentException("render can't be null");
        }
        if (!(iDiLinkWatermarkRender instanceof a)) {
            throw new IllegalArgumentException("render must be instanceof DiLinkWatermarkRender");
        }
        AVMCamera aVMCameraOpen = AVMCamera.open(i, ((a) iDiLinkWatermarkRender).a(), i2);
        if (aVMCameraOpen != null) {
            return new DiLinkAVMCamera(aVMCameraOpen);
        }
        Log.e("DiLinkAVMCameraManager", "IDiLinkAVMCameraManager.open return null!");
        return null;
    }
}
