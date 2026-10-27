package com.ts.avm.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class CmdBean implements Parcelable {
    public static final Parcelable.Creator<CmdBean> CREATOR = new Parcelable.Creator<CmdBean>() { // from class: com.ts.avm.bean.CmdBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CmdBean createFromParcel(Parcel in) {
            return new CmdBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CmdBean[] newArray(int size) {
            return new CmdBean[size];
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

    public int getHeader() {
        return this.header;
    }

    public void setHeader(int header) {
        this.header = header;
    }

    public int getPayLoadSize() {
        return this.payLoadSize;
    }

    public void setPayLoadSize(int payLoadSize) {
        this.payLoadSize = payLoadSize;
    }

    public int getSrcId() {
        return this.srcId;
    }

    public void setSrcId(int srcId) {
        this.srcId = srcId;
    }

    public int getTargetId() {
        return this.targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public int getTransferId() {
        return this.transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGetOrSet() {
        return this.getOrSet;
    }

    public void setGetOrSet(int getOrSet) {
        this.getOrSet = getOrSet;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getFlags() {
        return this.flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int[] getValue() {
        int[] iArr = this.value;
        if (iArr == null) {
            return new int[4];
        }
        return iArr;
    }

    public void setValue(int[] value) {
        this.value = value;
    }

    public CmdBean() {
    }

    protected CmdBean(Parcel in) {
        this.header = in.readInt();
        this.payLoadSize = in.readInt();
        this.srcId = in.readInt();
        this.targetId = in.readInt();
        this.transferId = in.readInt();
        this.id = in.readInt();
        this.type = in.readInt();
        this.getOrSet = in.readInt();
        this.priority = in.readInt();
        this.flags = in.readInt();
        this.value = in.createIntArray();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.header);
        dest.writeInt(this.payLoadSize);
        dest.writeInt(this.srcId);
        dest.writeInt(this.targetId);
        dest.writeInt(this.transferId);
        dest.writeInt(this.id);
        dest.writeInt(this.type);
        dest.writeInt(this.getOrSet);
        dest.writeInt(this.priority);
        dest.writeInt(flags);
        dest.writeIntArray(this.value);
    }
}
