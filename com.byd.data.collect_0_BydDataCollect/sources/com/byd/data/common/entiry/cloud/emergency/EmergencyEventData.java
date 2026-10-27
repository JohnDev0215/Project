package com.byd.data.common.entiry.cloud.emergency;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class EmergencyEventData {
    public short canID;
    public byte endBit;
    public byte endByte;
    public boolean hasSubID;
    public ArrayList<Integer> lsValue;
    public byte startBit;
    public byte startByte;
    public byte subID;
    public int type;

    public EmergencyEventData(short s, byte b2, byte b3, byte b4, byte b5, byte b6, int i2, ArrayList<Integer> arrayList, boolean z) {
        this.canID = s;
        this.subID = b2;
        this.startByte = b3;
        this.startBit = b4;
        this.endByte = b5;
        this.endBit = b6;
        this.type = i2;
        this.lsValue = arrayList;
        this.hasSubID = z;
    }
}
