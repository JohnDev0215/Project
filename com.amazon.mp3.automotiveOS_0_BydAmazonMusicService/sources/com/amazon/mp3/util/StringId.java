package com.amazon.mp3.util;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;

/* JADX INFO: loaded from: classes4.dex */
public class StringId implements Parcelable {
    public static final Parcelable.Creator<StringId> CREATOR = new Parcelable.Creator<StringId>() { // from class: com.amazon.mp3.util.StringId.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StringId createFromParcel(Parcel in) {
            return new StringId(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StringId[] newArray(int size) {
            return new StringId[size];
        }
    };
    private int mStringId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static String getOrConvertStringId(int stringId) {
        if (stringId <= 0) {
            return null;
        }
        String string = AmazonApplication.getContext().getString(stringId);
        return stringId == R.string.dmusic_download_desc_cirrus_storage_limit_error ? String.format(string, SettingsUtil.getCloudPlayerUrl(AmazonApplication.getContext())) : string;
    }

    public int getStringId() {
        return this.mStringId;
    }

    public void setStringId(int stringId) {
        this.mStringId = stringId;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.mStringId);
    }

    private StringId(Parcel in) {
        this.mStringId = -1;
        this.mStringId = in.readInt();
    }
}
