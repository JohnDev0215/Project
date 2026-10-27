package com.android.internal.statusbar;

import android.graphics.drawable.Icon;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.UserHandle;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes4.dex */
public class StatusBarIcon implements Parcelable {
    public static final Parcelable.Creator<StatusBarIcon> CREATOR = new Parcelable.Creator<StatusBarIcon>() { // from class: com.android.internal.statusbar.StatusBarIcon.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StatusBarIcon createFromParcel(Parcel parcel) {
            return new StatusBarIcon(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StatusBarIcon[] newArray(int i) {
            return new StatusBarIcon[i];
        }
    };
    public CharSequence contentDescription;
    public Icon icon;
    public int iconLevel;
    public int number;
    public String pkg;
    public UserHandle user;
    public boolean visible;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public StatusBarIcon(UserHandle userHandle, String str, Icon icon, int i, int i2, CharSequence charSequence) {
        this.visible = true;
        if (icon.getType() == 2 && TextUtils.isEmpty(icon.getResPackage())) {
            icon = Icon.createWithResource(str, icon.getResId());
        }
        this.pkg = str;
        this.user = userHandle;
        this.icon = icon;
        this.iconLevel = i;
        this.number = i2;
        this.contentDescription = charSequence;
    }

    public StatusBarIcon(String str, UserHandle userHandle, int i, int i2, int i3, CharSequence charSequence) {
        this(userHandle, str, Icon.createWithResource(str, i), i2, i3, charSequence);
    }

    public String toString() {
        return "StatusBarIcon(icon=" + this.icon + (this.iconLevel != 0 ? " level=" + this.iconLevel : "") + (this.visible ? " visible" : "") + " user=" + this.user.getIdentifier() + (this.number != 0 ? " num=" + this.number : "") + " )";
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public StatusBarIcon m75clone() {
        StatusBarIcon statusBarIcon = new StatusBarIcon(this.user, this.pkg, this.icon, this.iconLevel, this.number, this.contentDescription);
        statusBarIcon.visible = this.visible;
        return statusBarIcon;
    }

    public StatusBarIcon(Parcel parcel) {
        this.visible = true;
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.icon = (Icon) parcel.readParcelable(null);
        this.pkg = parcel.readString();
        this.user = (UserHandle) parcel.readParcelable(null);
        this.iconLevel = parcel.readInt();
        this.visible = parcel.readInt() != 0;
        this.number = parcel.readInt();
        this.contentDescription = parcel.readCharSequence();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.icon, 0);
        parcel.writeString(this.pkg);
        parcel.writeParcelable(this.user, 0);
        parcel.writeInt(this.iconLevel);
        parcel.writeInt(this.visible ? 1 : 0);
        parcel.writeInt(this.number);
        parcel.writeCharSequence(this.contentDescription);
    }
}
