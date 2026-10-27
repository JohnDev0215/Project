package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class FavoriteItem implements Parcelable {
    public static final Parcelable.Creator<FavoriteItem> CREATOR = new Parcelable.Creator<FavoriteItem>() { // from class: com.neusoft.na.ocservice.FavoriteItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FavoriteItem createFromParcel(Parcel parcel) {
            return new FavoriteItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FavoriteItem[] newArray(int i) {
            return new FavoriteItem[i];
        }
    };
    String address;
    CategoryInfo[] categoryInfolist;
    int dataType;
    int favoriteType;
    String fixName;
    GeoPos geoPos;
    String id;
    int mapType;
    String name;
    NameInfo[] nameInfoList;
    GeoPos[] poiAccessPositions;
    String poiDataSource;
    String poiEmail;
    int[] poiLocationid;
    String[] poiPhoneNumbers;
    String poiWebsite;
    String uid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public int getFavoriteType() {
        return this.favoriteType;
    }

    public void setFavoriteType(int i) {
        this.favoriteType = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public GeoPos getGeoPos() {
        return this.geoPos;
    }

    public void setGeoPos(GeoPos geoPos) {
        this.geoPos = geoPos;
    }

    public String[] getPoiPhoneNumbers() {
        return this.poiPhoneNumbers;
    }

    public void setPoiPhoneNumbers(String[] strArr) {
        this.poiPhoneNumbers = strArr;
    }

    public String getPoiWebsite() {
        return this.poiWebsite;
    }

    public void setPoiWebsite(String str) {
        this.poiWebsite = str;
    }

    public String getPoiEmail() {
        return this.poiEmail;
    }

    public void setPoiEmail(String str) {
        this.poiEmail = str;
    }

    public CategoryInfo[] getCategoryInfolist() {
        return this.categoryInfolist;
    }

    public void setCategoryInfolist(CategoryInfo[] categoryInfoArr) {
        this.categoryInfolist = categoryInfoArr;
    }

    public NameInfo[] getNameInfoList() {
        return this.nameInfoList;
    }

    public void setNameInfoList(NameInfo[] nameInfoArr) {
        this.nameInfoList = nameInfoArr;
    }

    public String getPoiDataSource() {
        return this.poiDataSource;
    }

    public void setPoiDataSource(String str) {
        this.poiDataSource = str;
    }

    public GeoPos[] getPoiAccessPositions() {
        return this.poiAccessPositions;
    }

    public void setPoiAccessPositions(GeoPos[] geoPosArr) {
        this.poiAccessPositions = geoPosArr;
    }

    public int[] getPoiLocationid() {
        return this.poiLocationid;
    }

    public void setPoiLocationid(int[] iArr) {
        this.poiLocationid = iArr;
    }

    public String getFixName() {
        return this.fixName;
    }

    public void setFixName(String str) {
        this.fixName = str;
    }

    public int getMapType() {
        return this.mapType;
    }

    public void setMapType(int i) {
        this.mapType = i;
    }

    public int getDataType() {
        return this.dataType;
    }

    public void setDataType(int i) {
        this.dataType = i;
    }

    public FavoriteItem() {
        this.id = "";
        this.favoriteType = -1;
        this.name = "";
        this.uid = "";
        this.address = "";
        this.poiWebsite = "";
        this.poiEmail = "";
        this.poiDataSource = "";
        this.fixName = "";
        this.mapType = -1;
        this.dataType = -1;
    }

    protected FavoriteItem(Parcel parcel) {
        this.id = "";
        this.favoriteType = -1;
        this.name = "";
        this.uid = "";
        this.address = "";
        this.poiWebsite = "";
        this.poiEmail = "";
        this.poiDataSource = "";
        this.fixName = "";
        this.mapType = -1;
        this.dataType = -1;
        this.id = parcel.readString();
        this.favoriteType = parcel.readInt();
        this.name = parcel.readString();
        this.uid = parcel.readString();
        this.address = parcel.readString();
        this.geoPos = (GeoPos) parcel.readParcelable(GeoPos.class.getClassLoader());
        this.poiPhoneNumbers = parcel.createStringArray();
        this.poiWebsite = parcel.readString();
        this.poiEmail = parcel.readString();
        this.categoryInfolist = (CategoryInfo[]) parcel.createTypedArray(CategoryInfo.CREATOR);
        this.nameInfoList = (NameInfo[]) parcel.createTypedArray(NameInfo.CREATOR);
        this.poiDataSource = parcel.readString();
        this.poiAccessPositions = (GeoPos[]) parcel.createTypedArray(GeoPos.CREATOR);
        this.poiLocationid = parcel.createIntArray();
        this.fixName = parcel.readString();
        this.mapType = parcel.readInt();
        this.dataType = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeInt(this.favoriteType);
        parcel.writeString(this.name);
        parcel.writeString(this.uid);
        parcel.writeString(this.address);
        parcel.writeParcelable(this.geoPos, i);
        parcel.writeStringArray(this.poiPhoneNumbers);
        parcel.writeString(this.poiWebsite);
        parcel.writeString(this.poiEmail);
        parcel.writeTypedArray(this.categoryInfolist, i);
        parcel.writeTypedArray(this.nameInfoList, i);
        parcel.writeString(this.poiDataSource);
        parcel.writeTypedArray(this.poiAccessPositions, i);
        parcel.writeIntArray(this.poiLocationid);
        parcel.writeString(this.fixName);
        parcel.writeInt(this.mapType);
        parcel.writeInt(this.dataType);
    }
}
