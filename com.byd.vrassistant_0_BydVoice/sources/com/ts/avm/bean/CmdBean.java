package com.ts.avm.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class CmdBean implements Parcelable {
    public static final Parcelable.Creator<CmdBean> CREATOR = new Parcelable.Creator<CmdBean>() { // from class: com.ts.avm.bean.CmdBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CmdBean createFromParcel(Parcel parcel) {
            return new CmdBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CmdBean[] newArray(int i) {
            return new CmdBean[i];
        }
    };
    private int flags;
    private int getOrSet;
    private int header;
    private int id;
    private int payLoadSize;
    private int priority;
    private int srcId;
    private int targetId;
    private int transferId;
    private int type;
    private int[] value;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getHeader() {
        return this.header;
    }

    public void setHeader(int i) {
        this.header = i;
    }

    public int getPayLoadSize() {
        return this.payLoadSize;
    }

    public void setPayLoadSize(int i) {
        this.payLoadSize = i;
    }

    public int getSrcId() {
        return this.srcId;
    }

    public void setSrcId(int i) {
        this.srcId = i;
    }

    public int getTargetId() {
        return this.targetId;
    }

    public void setTargetId(int i) {
        this.targetId = i;
    }

    public int getTransferId() {
        return this.transferId;
    }

    public void setTransferId(int i) {
        this.transferId = i;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getGetOrSet() {
        return this.getOrSet;
    }

    public void setGetOrSet(int i) {
        this.getOrSet = i;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int i) {
        this.priority = i;
    }

    public int getFlags() {
        return this.flags;
    }

    public void setFlags(int i) {
        this.flags = i;
    }

    public int[] getValue() {
        int[] iArr = this.value;
        return iArr == null ? new int[4] : iArr;
    }

    public void setValue(int[] iArr) {
        this.value = iArr;
    }

    public CmdBean() {
    }

    protected CmdBean(Parcel parcel) {
        this.header = parcel.readInt();
        this.payLoadSize = parcel.readInt();
        this.srcId = parcel.readInt();
        this.targetId = parcel.readInt();
        this.transferId = parcel.readInt();
        this.id = parcel.readInt();
        this.type = parcel.readInt();
        this.getOrSet = parcel.readInt();
        this.priority = parcel.readInt();
        this.flags = parcel.readInt();
        this.value = parcel.createIntArray();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.header);
        parcel.writeInt(this.payLoadSize);
        parcel.writeInt(this.srcId);
        parcel.writeInt(this.targetId);
        parcel.writeInt(this.transferId);
        parcel.writeInt(this.id);
        parcel.writeInt(this.type);
        parcel.writeInt(this.getOrSet);
        parcel.writeInt(this.priority);
        parcel.writeInt(i);
        parcel.writeIntArray(this.value);
    }
}
