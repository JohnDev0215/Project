package com.byd.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public class FileBean implements Comparable<FileBean>, Parcelable {
    public static final Parcelable.Creator<FileBean> CREATOR = new Parcelable.Creator<FileBean>() { // from class: com.byd.bean.FileBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FileBean createFromParcel(Parcel parcel) {
            return new FileBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FileBean[] newArray(int i) {
            return new FileBean[i];
        }
    };
    public static final int TYPE_BURSTSHOOT_IMG = 3;
    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_VIDEO = 2;
    private String address;
    private String addressLocale;
    private int collected;
    private long date;
    private boolean editAddItem;
    private boolean isDvrFile;
    private boolean isSelect;
    private long lastModifiedTime;
    private double lat;
    private double lng;
    private int longPic;
    private String name;
    private int num;
    private String path;
    private int sourceType;
    private String thumbPath;
    private int type;
    private long videoDuration;

    protected FileBean(Parcel parcel) {
        this.videoDuration = -1L;
        this.name = parcel.readString();
        this.path = parcel.readString();
        this.thumbPath = parcel.readString();
        this.type = parcel.readInt();
        this.lastModifiedTime = parcel.readLong();
        this.lat = parcel.readDouble();
        this.lng = parcel.readDouble();
        this.date = parcel.readLong();
        this.sourceType = parcel.readInt();
        this.address = parcel.readString();
        this.addressLocale = parcel.readString();
        this.num = parcel.readInt();
        this.isDvrFile = parcel.readByte() == 1;
        this.collected = parcel.readInt();
        this.videoDuration = parcel.readLong();
        this.longPic = parcel.readInt();
        this.editAddItem = parcel.readByte() == 1;
    }

    public FileBean(String str, String str2, int i, int i2, long j) {
        this(str, str2, i, i2, j, 0.0d, 0.0d, false);
    }

    public FileBean(String str, String str2, int i, int i2, long j, double d, double d2, boolean z) {
        this.videoDuration = -1L;
        this.name = str;
        this.path = str2;
        this.sourceType = i;
        this.type = i2;
        this.lat = d;
        this.lng = d2;
        this.isDvrFile = z;
        setLastModifiedTime(j);
    }

    @Override // java.lang.Comparable
    public int compareTo(FileBean fileBean) {
        return this.lastModifiedTime - fileBean.lastModifiedTime > 0 ? -1 : 1;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return obj instanceof FileBean ? this.path.equals(((FileBean) obj).path) : super.equals(obj);
    }

    public String getAddress() {
        return this.address;
    }

    public String getAddressLocale() {
        return this.addressLocale;
    }

    public int getCollected() {
        return this.collected;
    }

    public long getDate() {
        return this.date;
    }

    public long getLastModifiedTime() {
        return this.lastModifiedTime;
    }

    public double getLat() {
        return this.lat;
    }

    public double getLng() {
        return this.lng;
    }

    public int getLongPic() {
        return this.longPic;
    }

    public String getName() {
        if (this.name == null) {
            this.name = "";
        }
        return this.name;
    }

    public int getNum() {
        return this.num;
    }

    public String getPath() {
        return this.path;
    }

    public int getSourceType() {
        return this.sourceType;
    }

    public String getThumbPath() {
        return this.thumbPath;
    }

    public int getType() {
        return this.type;
    }

    public long getVideoDuration() {
        return this.videoDuration;
    }

    public int hashCode() {
        return this.path.hashCode();
    }

    public boolean isCollected() {
        return this.collected == 1;
    }

    public boolean isDvrFile() {
        return this.isDvrFile;
    }

    public boolean isEditAddItem() {
        return this.editAddItem;
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setAddressLocale(String str) {
        this.addressLocale = str;
    }

    public void setCollected(int i) {
        this.collected = i;
    }

    public void setDvrFile(boolean z) {
        this.isDvrFile = z;
    }

    public void setEditAddItem(boolean z) {
        this.editAddItem = z;
    }

    public void setLastModifiedTime(long j) {
        this.lastModifiedTime = j;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        this.date = calendar.getTimeInMillis();
    }

    public void setLat(double d) {
        this.lat = d;
    }

    public void setLng(double d) {
        this.lng = d;
    }

    public void setLongPic(int i) {
        this.longPic = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNum(int i) {
        this.num = i;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public void setSelect(boolean z) {
        this.isSelect = z;
    }

    public void setSourceType(int i) {
        this.sourceType = i;
    }

    public void setThumbPath(String str) {
        this.thumbPath = str;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setVideoDuration(long j) {
        this.videoDuration = j;
    }

    public String toString() {
        return "FileBean{name='" + this.name + "', path='" + this.path + "', thumbPath='" + this.thumbPath + "', type=" + this.type + ", sourceType=" + this.sourceType + ", lastModifiedTime=" + this.lastModifiedTime + ", lat=" + this.lat + ", lng=" + this.lng + ", address='" + this.address + "', addressLocale='" + this.addressLocale + "', date=" + this.date + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.path);
        parcel.writeString(this.thumbPath);
        parcel.writeInt(this.type);
        parcel.writeLong(this.lastModifiedTime);
        parcel.writeDouble(this.lat);
        parcel.writeDouble(this.lng);
        parcel.writeLong(this.date);
        parcel.writeInt(this.sourceType);
        parcel.writeString(this.address);
        parcel.writeString(this.addressLocale);
        parcel.writeInt(this.num);
        parcel.writeByte(this.isDvrFile ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.collected);
        parcel.writeLong(this.videoDuration);
        parcel.writeInt(this.longPic);
        parcel.writeByte(this.editAddItem ? (byte) 1 : (byte) 0);
    }
}
