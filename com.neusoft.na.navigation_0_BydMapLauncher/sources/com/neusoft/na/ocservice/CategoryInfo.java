package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class CategoryInfo implements Parcelable {
    public static final Parcelable.Creator<CategoryInfo> CREATOR = new Parcelable.Creator<CategoryInfo>() { // from class: com.neusoft.na.ocservice.CategoryInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CategoryInfo createFromParcel(Parcel parcel) {
            return new CategoryInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CategoryInfo[] newArray(int i) {
            return new CategoryInfo[i];
        }
    };
    int categoryId;
    int categoryLevel;
    String categoryName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CategoryInfo() {
        this.categoryName = "";
        this.categoryId = -1;
        this.categoryLevel = -1;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String str) {
        this.categoryName = str;
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(int i) {
        this.categoryId = i;
    }

    public int getCategoryLevel() {
        return this.categoryLevel;
    }

    public void setCategoryLevel(int i) {
        this.categoryLevel = i;
    }

    protected CategoryInfo(Parcel parcel) {
        this.categoryName = "";
        this.categoryId = -1;
        this.categoryLevel = -1;
        this.categoryName = parcel.readString();
        this.categoryId = parcel.readInt();
        this.categoryLevel = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.categoryName);
        parcel.writeInt(this.categoryId);
        parcel.writeInt(this.categoryLevel);
    }
}
