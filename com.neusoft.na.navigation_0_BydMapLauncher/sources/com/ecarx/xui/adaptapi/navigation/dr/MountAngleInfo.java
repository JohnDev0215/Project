package com.ecarx.xui.adaptapi.navigation.dr;

import android.os.SystemProperties;
import com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo;
import com.neusoft.optimus.utility.log.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class MountAngleInfo implements IDrAPInfo.IMountAngle {
    public static final String TAG = "MountAngleInfo";
    private boolean hasMountAngle = false;
    private float pitch;
    private float roll;
    private float yaw;

    public void setHasMountAngle(boolean z) {
        this.hasMountAngle = z;
    }

    public void setYaw(float f) {
        this.yaw = f;
    }

    public void setPitch(float f) {
        this.pitch = f;
    }

    public void setRoll(float f) {
        this.roll = f;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo.IMountAngle
    public boolean hasMountAngle() {
        getMountAngle();
        return this.hasMountAngle;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo.IMountAngle
    public float getYawMountAngle() {
        getMountAngle();
        return this.yaw;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo.IMountAngle
    public float getPitchMountAngle() {
        getMountAngle();
        return this.pitch;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo.IMountAngle
    public float getRollMountAngle() {
        getMountAngle();
        return this.roll;
    }

    private void getMountAngle() {
        String vehicleType = getVehicleType();
        if (vehicleType.contains("VF12")) {
            setMountAngle(0.0d, 23.2d, 0.0d);
        } else if (vehicleType.contains("FE5AB")) {
            setMountAngle(0.0d, 52.0d, 0.0d);
        }
    }

    private void setMountAngle(double d, double d2, double d3) {
        setHasMountAngle(true);
        setYaw((float) d);
        setPitch((float) d2);
        setRoll((float) d3);
        LogTool.d(TAG, "setMountAngle yawMountAngle = " + d + "pitchMountAngle = " + d2 + "rollMountAngle = " + d3);
    }

    public String getVehicleType() {
        String str = SystemProperties.get("ro.product.device");
        if (str == null || str.isEmpty()) {
            return "车型null";
        }
        LogTool.d(TAG, "车型:" + str);
        return str;
    }
}
