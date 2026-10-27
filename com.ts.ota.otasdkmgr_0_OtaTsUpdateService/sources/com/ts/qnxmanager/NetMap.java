package com.ts.qnxmanager;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NetMap implements Parcelable {
    public static final Parcelable.Creator<NetMap> CREATOR = new Parcelable.Creator<NetMap>() { // from class: com.ts.qnxmanager.NetMap.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetMap createFromParcel(Parcel in) {
            return new NetMap(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetMap[] newArray(int size) {
            return new NetMap[size];
        }
    };
    private ArrayList mIdArrayList;
    private int mNetSeg;

    public NetMap(int netSeg, ArrayList<Integer> idArrayList) {
        this.mNetSeg = netSeg;
        this.mIdArrayList = idArrayList;
    }

    public int getNetSeg() {
        return this.mNetSeg;
    }

    public void setNetSeg(int netSeg) {
        this.mNetSeg = netSeg;
    }

    public List<Integer> getIdArrayList() {
        return this.mIdArrayList;
    }

    public void setIdArrayList(ArrayList<Integer> idArrayList) {
        this.mIdArrayList = idArrayList;
    }

    protected NetMap(Parcel in) {
        this.mNetSeg = in.readInt();
        this.mIdArrayList = in.readArrayList(null);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mNetSeg);
        dest.writeList(this.mIdArrayList);
    }

    public String toString() {
        return "NetMap{netSeg=" + this.mNetSeg + ", idArrayList=" + this.mIdArrayList + '}';
    }
}
