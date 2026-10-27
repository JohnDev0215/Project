package com.ts.ota.otasdkmgr;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class TestInfo implements Parcelable {
    public static final Parcelable.Creator<TestInfo> CREATOR = new Parcelable.Creator<TestInfo>() { // from class: com.ts.ota.otasdkmgr.TestInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TestInfo createFromParcel(Parcel in) {
            return new TestInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TestInfo[] newArray(int size) {
            return new TestInfo[size];
        }
    };
    private ArrayList<String> data;

    protected TestInfo(Parcel in) {
        ArrayList<String> arrayList = new ArrayList<>();
        this.data = arrayList;
        in.readList(arrayList, String.class.getClassLoader());
    }

    public ArrayList<String> getData() {
        return this.data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public TestInfo(ArrayList<String> data) {
        this.data = data;
    }

    public TestInfo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.data);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("TestInfo{");
        sb.append("data=");
        sb.append(this.data);
        sb.append('}');
        return sb.toString();
    }
}
