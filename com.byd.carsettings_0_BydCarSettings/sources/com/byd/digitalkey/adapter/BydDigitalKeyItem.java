package com.byd.digitalkey.adapter;

import android.hardware.bydauto.digital.tsimpl.DigitalKeyBean;
import androidx.annotation.NonNull;
import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class BydDigitalKeyItem extends DigitalKeyBean implements Serializable {
    @Override // android.hardware.bydauto.digital.tsimpl.DigitalKeyBean
    public byte getAccountTag() {
        return super.getAccountTag();
    }

    @Override // android.hardware.bydauto.digital.tsimpl.DigitalKeyBean
    public String getDeviceModelStr() {
        return super.getDeviceModelStr();
    }

    @Override // android.hardware.bydauto.digital.tsimpl.DigitalKeyBean
    public String getExpireTime() {
        return super.getExpireTime();
    }

    @Override // android.hardware.bydauto.digital.tsimpl.DigitalKeyBean
    public String getKeyId() {
        return super.getKeyId();
    }

    @Override // android.hardware.bydauto.digital.tsimpl.DigitalKeyBean
    public byte[] getKeyIdArray() {
        return super.getKeyIdArray();
    }

    @Override // android.hardware.bydauto.digital.tsimpl.DigitalKeyBean
    public byte getKeyLoadType() {
        return super.getKeyLoadType();
    }

    @Override // android.hardware.bydauto.digital.tsimpl.DigitalKeyBean
    public String getKeyNameStr() {
        return super.getKeyNameStr();
    }

    @Override // android.hardware.bydauto.digital.tsimpl.DigitalKeyBean
    public String getKeyNo() {
        return super.getKeyNo();
    }

    @Override // android.hardware.bydauto.digital.tsimpl.DigitalKeyBean
    public byte getKeyPermission() {
        return super.getKeyPermission();
    }

    @Override // android.hardware.bydauto.digital.tsimpl.DigitalKeyBean
    public byte getKeyState() {
        return super.getKeyState();
    }

    @Override // android.hardware.bydauto.digital.tsimpl.DigitalKeyBean
    public byte getKeyType() {
        return super.getKeyType();
    }

    @Override // android.hardware.bydauto.digital.tsimpl.DigitalKeyBean
    public String getUserPhone() {
        return super.getUserPhone();
    }

    @Override // android.hardware.bydauto.digital.tsimpl.DigitalKeyBean
    public byte getUserPro() {
        return super.getUserPro();
    }

    @Override // android.hardware.bydauto.digital.tsimpl.DigitalKeyBean
    public byte getUwbTag() {
        return super.getUwbTag();
    }

    public boolean hasCarOwnerKey() {
        return getUserPro() == 2;
    }

    @NonNull
    public String toString() {
        return "用户属性:" + ((int) getUserPro()) + ",用户账号:" + getUserPhone() + ",钥匙类型:" + ((int) getKeyType()) + ",钥匙编号:" + getKeyNo() + ",钥匙ID:" + Arrays.toString(getKeyIdArray()) + ",钥匙状态:" + ((int) getKeyState()) + ",钥匙名称:" + getKeyNameStr() + ",到期时间:" + getExpireTime() + ",设备型号：" + getDeviceModelStr();
    }
}
