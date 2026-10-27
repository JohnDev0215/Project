package com.amazon.mp3.prime.upsell.nightwing.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.mp3.automotiveOS.R;

/* JADX INFO: loaded from: classes4.dex */
public class UpsellUIConfig implements Parcelable {
    private String seedName;
    private int upsellDialogBodyId;
    private int upsellDialogComfortingTextId;
    private int upsellDialogTitleId;
    private int upsellNegativeButtonTextId;
    private int upsellPositiveButtonTextId;
    private static final String TAG = UpsellUIConfig.class.getSimpleName();
    public static final Parcelable.Creator<UpsellUIConfig> CREATOR = new Parcelable.Creator<UpsellUIConfig>() { // from class: com.amazon.mp3.prime.upsell.nightwing.model.UpsellUIConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UpsellUIConfig createFromParcel(Parcel in) {
            return new UpsellUIConfig(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UpsellUIConfig[] newArray(int size) {
            return new UpsellUIConfig[size];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UpsellUIConfig() {
    }

    private UpsellUIConfig(Parcel in) {
        this.upsellDialogTitleId = in.readInt();
        this.upsellDialogBodyId = in.readInt();
        this.upsellDialogComfortingTextId = in.readInt();
        this.upsellPositiveButtonTextId = in.readInt();
        this.upsellNegativeButtonTextId = in.readInt();
        this.seedName = in.readString();
    }

    public int getUpsellDialogBodyId() {
        int i = this.upsellDialogBodyId;
        return i == 0 ? R.string.dmusic_hawkfire_account_nightwing_upsell_dialog_message : i;
    }

    public void setUpsellDialogBodyId(int upsellDialogBodyId) {
        this.upsellDialogBodyId = upsellDialogBodyId;
    }

    public int getUpsellDialogComfortingTextId() {
        int i = this.upsellDialogComfortingTextId;
        return i == 0 ? R.string.dmusic_hawkfire_account_nightwing_upsell_dialog_comfort_text : i;
    }

    public void setUpsellDialogComfortingTextId(int upsellDialogComfortingTextId) {
        this.upsellDialogComfortingTextId = upsellDialogComfortingTextId;
    }

    public int getUpsellDialogTitleId() {
        int i = this.upsellDialogTitleId;
        return i == 0 ? R.string.dmusic_hawkfire_account_nightwing_upsell_dialog_title : i;
    }

    public void setUpsellDialogTitleId(int upsellDialogTitleId) {
        this.upsellDialogTitleId = upsellDialogTitleId;
    }

    public int getUpsellPositiveButtonTextId() {
        int i = this.upsellPositiveButtonTextId;
        return i == 0 ? R.string.dmusic_hawkfire_accoun_nightwing_upsell_dialog_positive_btn : i;
    }

    public void setUpsellPositiveButtonTextId(int upsellPositiveButtonTextId) {
        this.upsellPositiveButtonTextId = upsellPositiveButtonTextId;
    }

    public void setUpsellNegativeButtonTextId(int upsellNegativeButtonTextId) {
        this.upsellNegativeButtonTextId = upsellNegativeButtonTextId;
    }

    public void setSeedName(final String seedName) {
        this.seedName = seedName;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.upsellDialogTitleId);
        parcel.writeInt(this.upsellDialogBodyId);
        parcel.writeInt(this.upsellDialogComfortingTextId);
        parcel.writeInt(this.upsellPositiveButtonTextId);
        parcel.writeInt(this.upsellNegativeButtonTextId);
        parcel.writeString(this.seedName);
    }
}
