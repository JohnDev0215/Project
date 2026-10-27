package com.ecarx.xui.adaptapi.navigation.dr;

import com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo;

/* JADX INFO: loaded from: classes2.dex */
public class Acc3dInfo implements IDrAPInfo.IAcc3d {
    private int axis;
    private int interval;
    private float pitch;
    private float roll;
    private long time;
    private float yaw;

    public void setAxis(int i) {
        this.axis = i;
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

    public void setInterval(int i) {
        this.interval = i;
    }

    public void setTime(long j) {
        this.time = j;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo.IAcc3d
    public int getAxis() {
        return this.axis;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo.IAcc3d
    public float getValueYaw() {
        return this.yaw;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo.IAcc3d
    public float getValuePitch() {
        return this.pitch;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo.IAcc3d
    public float getValueRoll() {
        return this.roll;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo.IAcc3d
    public int getInterval() {
        return this.interval;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo.IAcc3d
    public long getTickTime() {
        return this.time;
    }
}
