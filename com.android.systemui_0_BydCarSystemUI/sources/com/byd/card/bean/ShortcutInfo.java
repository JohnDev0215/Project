package com.byd.card.bean;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import com.byd.card.utils.AppUtils;
import com.byd.card.utils.BitmapUtils;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ShortcutInfo implements Parcelable, AppOrShortcutItem {
    public static final Parcelable.Creator<ShortcutInfo> CREATOR = new Parcelable.Creator<ShortcutInfo>() { // from class: com.byd.card.bean.ShortcutInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShortcutInfo createFromParcel(Parcel in) {
            return new ShortcutInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShortcutInfo[] newArray(int size) {
            return new ShortcutInfo[size];
        }
    };
    public boolean checked;
    public int count;
    public boolean forbidden;
    public byte[] icon;
    public String identify;
    public String intent;
    public String origin;
    public int pGearFlag;
    public String pinyin;
    public int recent;
    public long recentTime;
    public String reserve1;
    public String reserve2;
    public String reserve3;
    public int shortcutId;
    public String title;

    public static class Column {
        public static final String COLUMN_COUNT = "count";
        public static final String COLUMN_ICON = "icon";
        public static final String COLUMN_IDENTIFY = "identify";
        public static final String COLUMN_INTENT = "intent";
        public static final String COLUMN_ORIGIN = "origin";
        public static final String COLUMN_PINYIN = "pinyin";
        public static final String COLUMN_P_GEAR_FLAG = "pGearFlag";
        public static final String COLUMN_RECENT = "recent";
        public static final String COLUMN_RECENT_TIME = "recentTime";
        public static final String COLUMN_RESERVE1 = "reserve1";
        public static final String COLUMN_RESERVE2 = "reserve2";
        public static final String COLUMN_RESERVE3 = "reserve3";
        public static final String COLUMN_SHORTCUT_ID = "shortcutId";
        public static final String COLUMN_TITLE = "title";
        public static final String SHORTCUT_CONNECTOR = "##";
        public static final String TABLE_NAME = "shortcut_info";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.byd.card.bean.AppOrShortcutItem
    public String getItemIdentify() {
        return this.origin + Column.SHORTCUT_CONNECTOR + this.identify;
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
        return this.title;
    }

    @Override // com.byd.card.bean.AppOrShortcutItem
    public Drawable getItemDrawable() {
        return BitmapUtils.bytes2Drawable(this.icon);
    }

    @Override // com.byd.card.bean.AppOrShortcutItem
    public boolean isForbidden() {
        return this.forbidden;
    }

    @Override // com.byd.card.bean.AppOrShortcutItem
    public void setForbidden(boolean forbidden) {
        this.forbidden = forbidden;
    }

    public ShortcutInfo(String identify, String title, String origin, byte[] icon, String intent, int pGearFlag, String reserve1, String reserve2, String reserve3) {
        this.identify = identify;
        this.title = title;
        this.origin = origin;
        this.pinyin = AppUtils.getPinYin(title);
        this.icon = icon;
        this.intent = intent;
        this.pGearFlag = pGearFlag;
        this.recent = 0;
        this.recentTime = 0L;
        this.count = 0;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
        this.reserve3 = reserve3;
    }

    public static Parcelable.Creator<ShortcutInfo> getCREATOR() {
        return CREATOR;
    }

    protected ShortcutInfo(Parcel in) {
        this.identify = in.readString();
        this.title = in.readString();
        this.origin = in.readString();
        this.pinyin = in.readString();
        this.icon = in.createByteArray();
        this.intent = in.readString();
        this.pGearFlag = in.readInt();
        this.recent = in.readInt();
        this.recentTime = in.readLong();
        this.count = in.readInt();
        this.reserve1 = in.readString();
        this.reserve2 = in.readString();
        this.reserve3 = in.readString();
    }

    public int getShortcutId() {
        return this.shortcutId;
    }

    public void setShortcutId(int shortcutId) {
        this.shortcutId = shortcutId;
    }

    public String getIdentify() {
        return this.identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPinyin() {
        return this.pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public byte[] getIcon() {
        return this.icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public String getIntent() {
        return this.intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public int getPGearFlag() {
        return this.pGearFlag;
    }

    public void setPGearFlag(int pGearFlag) {
        this.pGearFlag = pGearFlag;
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

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getReserve1() {
        return this.reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public String getReserve2() {
        return this.reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    public String getReserve3() {
        return this.reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identify);
        parcel.writeString(this.title);
        parcel.writeString(this.origin);
        parcel.writeString(this.pinyin);
        parcel.writeByteArray(this.icon);
        parcel.writeString(this.intent);
        parcel.writeInt(this.pGearFlag);
        parcel.writeInt(this.recent);
        parcel.writeLong(this.recentTime);
        parcel.writeInt(this.count);
        parcel.writeString(this.reserve1);
        parcel.writeString(this.reserve2);
        parcel.writeString(this.reserve3);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof ShortcutInfo) {
            return Objects.equals(getItemIdentify(), ((ShortcutInfo) o).getItemIdentify());
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(getItemIdentify());
    }

    public String toString() {
        return "ShortcutInfo{shortcutId=" + this.shortcutId + ", identify='" + this.identify + "', title='" + this.title + "', origin='" + this.origin + "', pinyin='" + this.pinyin + "', intent='" + this.intent + "', pGearFlag=" + this.pGearFlag + ", recent=" + this.recent + ", recentTime=" + this.recentTime + ", count=" + this.count + ", reserve1='" + this.reserve1 + "', reserve2='" + this.reserve2 + "', reserve3='" + this.reserve3 + "', checked=" + this.checked + ", forbidden=" + this.forbidden + '}';
    }
}
