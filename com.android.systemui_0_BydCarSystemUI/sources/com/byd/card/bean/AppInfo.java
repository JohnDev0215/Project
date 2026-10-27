package com.byd.card.bean;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import com.byd.card.utils.BitmapUtils;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class AppInfo implements Parcelable, AppOrShortcutItem {
    public static final Parcelable.Creator<AppInfo> CREATOR = new Parcelable.Creator<AppInfo>() { // from class: com.byd.card.bean.AppInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AppInfo createFromParcel(Parcel in) {
            return new AppInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AppInfo[] newArray(int size) {
            return new AppInfo[size];
        }
    };
    public String activity;
    public int appId;
    public boolean checked;
    public String className;
    public int count;
    public Drawable icon;
    public byte[] icons;
    private boolean isForbidden;
    public String name;
    public String packageName;
    public String pinyin;
    public int recent;
    public long recentTime;
    public int resizeMode;
    public int taskId;
    public int versionCode;
    public String versionName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AppInfo() {
        this.taskId = -1;
    }

    public AppInfo(String packageName, String className) {
        this("", null, packageName, className, "", -1, -1, -1L, null);
    }

    public AppInfo(String name, Drawable icon, String packageName, String className, String versionName, int versionCode, int recent, long recentTime, byte[] icons) {
        this.taskId = -1;
        this.name = name;
        this.icon = icon;
        this.packageName = packageName;
        this.className = className;
        this.versionName = versionName;
        this.versionCode = versionCode;
        this.recent = recent;
        this.recentTime = recentTime;
        this.icons = icons;
    }

    @Override // com.byd.card.bean.AppOrShortcutItem
    public boolean isForbidden() {
        return this.isForbidden;
    }

    @Override // com.byd.card.bean.AppOrShortcutItem
    public void setForbidden(boolean forbidden) {
        this.isForbidden = forbidden;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return this.pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getActivity() {
        return this.activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public int getRecent() {
        return this.recent;
    }

    public void setRecent(int recent) {
        this.recent = recent;
    }

    @Override // com.byd.card.bean.AppOrShortcutItem
    public long getRecentTime() {
        return this.recentTime;
    }

    public void setRecentTime(long recentTime) {
        this.recentTime = recentTime;
    }

    public byte[] getIcons() {
        return this.icons;
    }

    public void setIcons(byte[] icons) {
        this.icons = icons;
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getResizeMode() {
        return this.resizeMode;
    }

    public void setResizeMode(int count) {
        this.resizeMode = count;
    }

    public int getTaskId() {
        return this.taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public static Parcelable.Creator<AppInfo> getCREATOR() {
        return CREATOR;
    }

    @Override // com.byd.card.bean.AppOrShortcutItem
    public String getItemIdentify() {
        return this.packageName;
    }

    @Override // com.byd.card.bean.AppOrShortcutItem
    public void setItemChecked(boolean checked) {
        this.checked = checked;
    }

    @Override // com.byd.card.bean.AppOrShortcutItem
    public boolean isItemChecked() {
        return this.checked;
    }

    @Override // com.byd.card.bean.AppOrShortcutItem
    public String getItemName() {
        return this.name;
    }

    @Override // com.byd.card.bean.AppOrShortcutItem
    public Drawable getItemDrawable() {
        Drawable drawableAdapterMultiTheme = BitmapUtils.adapterMultiTheme(this.packageName, getIcons());
        return drawableAdapterMultiTheme == null ? BitmapUtils.bytes2Drawable(getIcons()) : drawableAdapterMultiTheme;
    }

    protected AppInfo(Parcel in) {
        this.taskId = -1;
        this.name = in.readString();
        this.pinyin = in.readString();
        this.packageName = in.readString();
        this.className = in.readString();
        this.versionName = in.readString();
        this.versionCode = in.readInt();
        this.recent = in.readInt();
        this.recentTime = in.readLong();
        this.icons = in.createByteArray();
        this.count = in.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.pinyin);
        parcel.writeString(this.packageName);
        parcel.writeString(this.className);
        parcel.writeString(this.versionName);
        parcel.writeInt(this.versionCode);
        parcel.writeInt(this.recent);
        parcel.writeLong(this.recentTime);
        parcel.writeByteArray(this.icons);
        parcel.writeInt(this.count);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof AppInfo) {
            return Objects.equals(getPackageName(), ((AppInfo) o).getPackageName());
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(getPackageName());
    }

    public String toString() {
        return "AppInfo{name='" + this.name + "', packageName='" + this.packageName + "', className='" + this.className + "', activity='" + this.activity + "', taskId='" + this.taskId + "', resizeMode='" + this.resizeMode + "'}";
    }
}
