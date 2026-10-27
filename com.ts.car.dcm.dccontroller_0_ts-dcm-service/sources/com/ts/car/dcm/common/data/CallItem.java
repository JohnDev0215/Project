package com.ts.car.dcm.common.data;

/* JADX INFO: loaded from: classes.dex */
public class CallItem {
    public String address;
    public int contactId;
    public int index;
    public boolean isWechatVoice;
    public boolean mMultiParty;
    public String name;
    public String number;
    public String photo;
    public String photoFormat;
    public int state;

    public String toString() {
        return "[address=" + LogEncrypt.MacAddressEncrypt(this.address) + ", index=" + this.index + ", name=" + this.name + ", number=" + LogEncrypt.IpAddressEncrypt(this.number) + ", mMultiParty=" + this.mMultiParty + ", photo=" + this.photo + ", contactId=" + this.contactId + ", photoFormat=" + this.photoFormat + ", state=" + this.state + ", isWechatVoice=" + this.isWechatVoice + "]";
    }
}
