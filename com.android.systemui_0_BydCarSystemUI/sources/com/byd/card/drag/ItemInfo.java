package com.byd.card.drag;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.ref.SoftReference;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ItemInfo implements Parcelable {
    public static final Parcelable.Creator<ItemInfo> CREATOR = new Parcelable.Creator<ItemInfo>() { // from class: com.byd.card.drag.ItemInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ItemInfo createFromParcel(Parcel in) {
            return new ItemInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ItemInfo[] newArray(int size) {
            return new ItemInfo[size];
        }
    };
    public static final String TYPE_ADD_ICON = "-1";
    public static final int TYPE_APP = 1;
    public static final String TYPE_APP_ICON = "1";
    public static final int TYPE_FOLDER = 2;
    public static final int TYPE_SHORTCUT = 3;
    public String activity;
    public Bitmap buffer;
    public int folderPosition;
    protected Bitmap icon;
    public byte[] iconBytes;
    protected SoftReference<Bitmap> iconRef;
    private int id;
    private String imgUrl;
    private String index;
    private boolean jiggle;
    public String mPackageName;
    public Bitmap notification;
    public String pinyin;
    public int position;
    public String title;
    protected boolean titleMeasured;
    public int type;
    public int versionCode;
    public String versionName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setIconListener() {
    }

    protected ItemInfo(Parcel in) {
        this.index = "1";
        this.jiggle = false;
        this.titleMeasured = false;
        this.icon = (Bitmap) in.readParcelable(Bitmap.class.getClassLoader());
        this.title = in.readString();
        this.pinyin = in.readString();
        this.type = in.readInt();
        this.notification = (Bitmap) in.readParcelable(Bitmap.class.getClassLoader());
        this.buffer = (Bitmap) in.readParcelable(Bitmap.class.getClassLoader());
        this.jiggle = in.readByte() != 0;
        this.titleMeasured = in.readByte() != 0;
        this.imgUrl = in.readString();
        this.mPackageName = in.readString();
        this.activity = in.readString();
        this.versionCode = in.readInt();
        this.versionName = in.readString();
        this.iconBytes = in.createByteArray();
        this.position = in.readInt();
        this.folderPosition = in.readInt();
    }

    public ItemInfo() {
        this.index = "1";
        this.jiggle = false;
        this.titleMeasured = false;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void clearIcon() {
        this.icon = null;
        this.iconRef = null;
    }

    public void destroy() {
        this.icon = null;
        this.notification = null;
        this.buffer = null;
    }

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
        this.iconRef = new SoftReference<>(icon);
        setIconListener();
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.titleMeasured = false;
    }

    public String getPinyin() {
        return this.pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public SoftReference<Bitmap> getIconRef() {
        return this.iconRef;
    }

    public void setIconRef(SoftReference<Bitmap> iconRef) {
        this.iconRef = iconRef;
    }

    public void jiggle() {
        setJiggle(true);
    }

    public void unJiggle() {
        setJiggle(false);
    }

    public boolean isJiggle() {
        return this.jiggle;
    }

    public void setJiggle(boolean jiggle) {
        this.jiggle = jiggle;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public void setPackageName(String packageName) {
        this.mPackageName = packageName;
    }

    public String getActivity() {
        return this.activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public byte[] getIconBytes() {
        return this.iconBytes;
    }

    public void setIconBytes(byte[] iconsBytes) {
        this.iconBytes = iconsBytes;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getFolderPosition() {
        return this.folderPosition;
    }

    public void setFolderPosition(int folderPosition) {
        this.folderPosition = folderPosition;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeParcelable(this.icon, i);
        parcel.writeString(this.title);
        parcel.writeString(this.pinyin);
        parcel.writeInt(this.type);
        parcel.writeParcelable(this.notification, i);
        parcel.writeParcelable(this.buffer, i);
        parcel.writeByte(this.jiggle ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.titleMeasured ? (byte) 1 : (byte) 0);
        parcel.writeString(this.imgUrl);
        parcel.writeString(this.mPackageName);
        parcel.writeString(this.activity);
        parcel.writeInt(this.versionCode);
        parcel.writeString(this.versionName);
        parcel.writeByteArray(this.iconBytes);
        parcel.writeInt(this.position);
        parcel.writeInt(this.folderPosition);
    }

    public String toString() {
        return "ItemInfo{id=" + this.id + ", index='" + this.index + "', icon=" + this.icon + ", title='" + this.title + "', pinyin='" + this.pinyin + "', type=" + this.type + ", iconRef=" + this.iconRef + ", notification=" + this.notification + ", buffer=" + this.buffer + ", jiggle=" + this.jiggle + ", titleMeasured=" + this.titleMeasured + ", imgUrl='" + this.imgUrl + "', mPackageName='" + this.mPackageName + "', activity='" + this.activity + "', versionName='" + this.versionName + "', versionCode=" + this.versionCode + ", iconBytes=" + Arrays.toString(this.iconBytes) + ", position=" + this.position + ", folderPosition=" + this.folderPosition + '}';
    }
}
