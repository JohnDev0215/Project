package com.ecarx.xui.adaptapi.navigation.dr;

import com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo;

/* JADX INFO: loaded from: classes2.dex */
public class GyroInfo implements IDrAPInfo.IGyro {
    private int axis;
    private int interval;
    private float pitch;
    private float roll;
    private float tem;
    private long time;
    private float yaw;

    public void setTime(long j) {
        this.time = j;
    }

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

    public void setTem(float f) {
        this.tem = f;
    }

    public void setInterval(int i) {
        this.interval = i;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo.IGyro
    public int getAxis() {
        return this.axis;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo.IGyro
    public float getValueYaw() {
        return this.yaw;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo.IGyro
    public float getValuePitch() {
        return this.pitch;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo.IGyro
    public float getValueRoll() {
        return this.roll;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo.IGyro
    public float getTemperature() {
        return this.tem;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo.IGyro
    public int getInterval() {
        return this.interval;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo.IGyro
    public long getTickTime() {
        return this.time;
    }
}
