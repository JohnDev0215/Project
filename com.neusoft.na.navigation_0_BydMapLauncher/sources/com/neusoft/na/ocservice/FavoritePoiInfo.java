package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class FavoritePoiInfo implements Parcelable {
    public static final Parcelable.Creator<FavoritePoiInfo> CREATOR = new Parcelable.Creator<FavoritePoiInfo>() { // from class: com.neusoft.na.ocservice.FavoritePoiInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FavoritePoiInfo createFromParcel(Parcel parcel) {
            return new FavoritePoiInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FavoritePoiInfo[] newArray(int i) {
            return new FavoritePoiInfo[i];
        }
    };
    private int favoriteType;
    private PoiInfo poiInfo;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getFavoriteType() {
        return this.favoriteType;
    }

    public void setFavoriteType(int i) {
        this.favoriteType = i;
    }

    public PoiInfo getPoiInfo() {
        return this.poiInfo;
    }

    public void setPoiInfo(PoiInfo poiInfo) {
        this.poiInfo = poiInfo;
    }

    protected FavoritePoiInfo(Parcel parcel) {
        this.favoriteType = -1;
        this.favoriteType = parcel.readInt();
        this.poiInfo = (PoiInfo) parcel.readParcelable(PoiInfo.class.getClassLoader());
    }

    public FavoritePoiInfo() {
        this.favoriteType = -1;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.favoriteType);
        parcel.writeParcelable(this.poiInfo, i);
    }
}
