package BA1aabBX.BA1aabBX.BA1aabBX.B1abXa1b.BA1aabBX;

import BA1aabBX.BA1aabBX.BA1aabBX.B1abXa1b.BXA.b;
import android.hardware.NormalCamera;
import android.util.Log;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.camera.IDiLinkNormalCamera;
import com.byd.dilink.hardware.camera.IDiLinkNormalCameraManager;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender;
import com.byd.dilink50.hardware.camera.DiLinkNormalCamera;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_0})
@ServiceImpl(service = IDiLinkNormalCameraManager.class, singleton = true)
public class aba1Aab1b implements IDiLinkNormalCameraManager {
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
        if (!(iDiLinkWatermarkRender instanceof b)) {
            throw new IllegalArgumentException("render must be instanceof DiLinkWatermarkRender");
        }
        NormalCamera normalCameraOpen = NormalCamera.open(i, ((b) iDiLinkWatermarkRender).a());
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
        if (!(iDiLinkWatermarkRender instanceof b)) {
            throw new IllegalArgumentException("render must be instanceof DiLinkWatermarkRender");
        }
        NormalCamera normalCameraOpen = NormalCamera.open(i, ((b) iDiLinkWatermarkRender).a(), i2);
        if (normalCameraOpen != null) {
            return new DiLinkNormalCamera(normalCameraOpen);
        }
        Log.e("DiLinkNormalCameraManager", "IDiLinkNormalCameraManager.open return null!");
        return null;
    }
}
