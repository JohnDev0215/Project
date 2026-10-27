package eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.FE3EEEEZ.eZZfeFZFF;

import android.hardware.NormalCamera;
import android.util.Log;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.camera.IDiLinkNormalCamera;
import com.byd.dilink.hardware.camera.IDiLinkNormalCameraManager;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender;
import com.byd.dilink51.hardware.camera.DiLinkNormalCamera;
import com.byd.foundation.annotation.ServiceImpl;
import eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.FE3EEEEZ.e3E3ZeZ3E.a;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkNormalCameraManager.class, singleton = true)
public class ZZeeef implements IDiLinkNormalCameraManager {
    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCameraManager
    public IDiLinkNormalCamera open(int i) {
        Log.d("DiLinkNormalCameraManager", "IDiLinkNormalCameraManager.open cameraId=" + i);
        NormalCamera normalCameraOpen = NormalCamera.open(i);
        if (normalCameraOpen != null) {
            return new DiLinkNormalCamera(normalCameraOpen);
        }
        Log.e("DiLinkNormalCameraManager", "IDiLinkNormalCameraManager.open return null!");
        return null;
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCameraManager
    public IDiLinkNormalCamera open(int i, int i2) {
        Log.d("DiLinkNormalCameraManager", "IDiLinkNormalCameraManager.open cameraId=" + i);
        NormalCamera normalCameraOpen = NormalCamera.open(i, i2);
        if (normalCameraOpen != null) {
            return new DiLinkNormalCamera(normalCameraOpen);
        }
        Log.e("DiLinkNormalCameraManager", "IDiLinkNormalCameraManager.open return null!");
        return null;
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCameraManager
    public IDiLinkNormalCamera open(int i, IDiLinkWatermarkRender iDiLinkWatermarkRender) {
        Log.d("DiLinkNormalCameraManager", "IDiLinkNormalCameraManager.open cameraId=" + i);
        if (iDiLinkWatermarkRender == null) {
            throw new IllegalArgumentException("render can't be null");
        }
        if (!(iDiLinkWatermarkRender instanceof a)) {
            throw new IllegalArgumentException("render must be instanceof DiLinkWatermarkRender");
        }
        NormalCamera normalCameraOpen = NormalCamera.open(i, ((a) iDiLinkWatermarkRender).a());
        if (normalCameraOpen != null) {
            return new DiLinkNormalCamera(normalCameraOpen);
        }
        Log.e("DiLinkNormalCameraManager", "IDiLinkNormalCameraManager.open return null!");
        return null;
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCameraManager
    public IDiLinkNormalCamera open(int i, IDiLinkWatermarkRender iDiLinkWatermarkRender, int i2) {
        Log.d("DiLinkNormalCameraManager", "IDiLinkNormalCameraManager.open cameraId=" + i);
        if (iDiLinkWatermarkRender == null) {
            throw new IllegalArgumentException("render can't be null");
        }
        if (!(iDiLinkWatermarkRender instanceof a)) {
            throw new IllegalArgumentException("render must be instanceof DiLinkWatermarkRender");
        }
        NormalCamera normalCameraOpen = NormalCamera.open(i, ((a) iDiLinkWatermarkRender).a(), i2);
        if (normalCameraOpen != null) {
            return new DiLinkNormalCamera(normalCameraOpen);
        }
        Log.e("DiLinkNormalCameraManager", "IDiLinkNormalCameraManager.open return null!");
        return null;
    }
}
