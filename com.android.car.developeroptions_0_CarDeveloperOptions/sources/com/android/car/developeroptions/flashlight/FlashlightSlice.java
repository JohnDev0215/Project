package com.android.car.developeroptions.flashlight;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.provider.Settings;
import android.util.Log;
import com.android.car.developeroptions.slices.CustomSliceRegistry;
import com.android.car.developeroptions.slices.CustomSliceable;

/* JADX INFO: loaded from: classes.dex */
public class FlashlightSlice implements CustomSliceable {
    private final Context mContext;

    public FlashlightSlice(Context context) {
        this.mContext = context;
    }

    @Override // com.android.car.developeroptions.slices.CustomSliceable
    public void onNotifyChange(Intent intent) {
        try {
            String cameraId = getCameraId(this.mContext);
            if (cameraId != null) {
                ((CameraManager) this.mContext.getSystemService(CameraManager.class)).setTorchMode(cameraId, intent.getBooleanExtra("android.app.slice.extra.TOGGLE_STATE", isFlashlightEnabled(this.mContext)));
            }
        } catch (CameraAccessException e) {
            Log.e("FlashlightSlice", "Camera couldn't set torch mode.", e);
        }
        this.mContext.getContentResolver().notifyChange(CustomSliceRegistry.FLASHLIGHT_SLICE_URI, null);
    }

    private static String getCameraId(Context context) throws CameraAccessException {
        CameraManager cameraManager = (CameraManager) context.getSystemService(CameraManager.class);
        for (String str : cameraManager.getCameraIdList()) {
            CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(str);
            Boolean bool = (Boolean) cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
            Integer num = (Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
            if (bool != null && bool.booleanValue() && num != null && num.intValue() == 1) {
                return str;
            }
        }
        return null;
    }

    private static boolean isFlashlightEnabled(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), "flashlight_enabled", 0) == 1;
    }
}
