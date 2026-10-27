package com.byd.share;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class ShareItem implements Parcelable {
    public static final int CODE_DEV_BUSY = -1;
    public static final Parcelable.Creator<ShareItem> CREATOR = new a();
    String appName;
    int code;
    int dataType;
    int fromId;
    ArrayList<String> paths;
    ArrayList<Integer> toIdList;

    class a implements Parcelable.Creator<ShareItem> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ShareItem createFromParcel(Parcel parcel) {
            return new ShareItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ShareItem[] newArray(int i) {
            return new ShareItem[i];
        }
    }

    public ShareItem() {
    }

    public ShareItem(int i, ArrayList<Integer> arrayList, ArrayList<String> arrayList2, int i2, String str) {
        this.fromId = i;
        this.toIdList = arrayList;
        this.paths = arrayList2;
        this.dataType = i2;
        this.appName = str;
    }

    protected ShareItem(Parcel parcel) {
        this.fromId = parcel.readInt();
        this.paths = parcel.createStringArrayList();
        this.dataType = parcel.readInt();
        this.appName = parcel.readString();
        this.code = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAppName() {
        return this.appName;
    }

    public int getCode() {
        return this.code;
    }

    public int getDataType() {
        return this.dataType;
    }

    public int getFromId() {
        return this.fromId;
    }

    public ArrayList<String> getPaths() {
        return this.paths;
    }

    public ArrayList<Integer> getToIdList() {
        return this.toIdList;
    }

    public boolean isMoreThanOneItem() {
        ArrayList<String> arrayList = this.paths;
        return arrayList != null && arrayList.size() > 1;
    }

    public ShareItem setAppName(String str) {
        this.appName = str;
        return this;
    }

    public ShareItem setCode(int i) {
        this.code = i;
        return this;
    }

    public ShareItem setDataType(int i) {
        this.dataType = i;
        return this;
    }

    public ShareItem setFromId(int i) {
        this.fromId = i;
        return this;
    }

    public ShareItem setPaths(ArrayList<String> arrayList) {
        this.paths = arrayList;
        return this;
    }

    public ShareItem setToIdList(ArrayList<Integer> arrayList) {
        this.toIdList = arrayList;
        return this;
    }

    public ShareItem setToIds(int... iArr) {
        this.toIdList = new ArrayList<>(iArr.length);
        for (int i : iArr) {
            this.toIdList.add(Integer.valueOf(i));
        }
        return this;
    }

    public String toString() {
        return "ShareItem{fromId=" + this.fromId + ", code=" + this.code + ", toIdList=" + this.toIdList + ", paths=" + this.paths + ", dataType=" + this.dataType + ", appName='" + this.appName + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.fromId);
        parcel.writeStringList(this.paths);
        parcel.writeInt(this.dataType);
        parcel.writeString(this.appName);
        parcel.writeInt(this.code);
    }
}
