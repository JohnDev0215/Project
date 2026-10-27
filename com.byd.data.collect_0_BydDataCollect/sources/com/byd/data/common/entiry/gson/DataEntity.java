package com.byd.data.common.entiry.gson;

import com.google.gson.u.c;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class DataEntity implements Serializable {
    private byte arrayIndex;
    public byte bitEnd;
    public byte bitStart;
    private byte byteSize;

    @c("canId")
    public short canId;
    public byte endIndex;
    public byte startIndex;
    public byte subId;

    private void i(short s, int i2, int i3) {
        this.canId = s;
        this.startIndex = (byte) i2;
        this.endIndex = (byte) i3;
    }

    public byte a() {
        return this.arrayIndex;
    }

    public byte b() {
        return this.bitStart;
    }

    public byte d() {
        return this.byteSize;
    }

    public byte e() {
        return this.startIndex;
    }

    public void g(short s, int i2, int i3, int i4, int i5) {
        this.canId = s;
        this.startIndex = (byte) i2;
        this.bitStart = (byte) i3;
        this.endIndex = (byte) i4;
        this.bitEnd = (byte) i5;
    }

    public void h(int i2) {
        byte b2 = (byte) i2;
        this.startIndex = b2;
        this.endIndex = b2;
    }

    public void j(short s, int i2, boolean z) {
        i(s, i2, z ? i2 + 1 : i2);
    }

    public void k(byte b2) {
        this.arrayIndex = (byte) (this.arrayIndex + b2);
    }
}
