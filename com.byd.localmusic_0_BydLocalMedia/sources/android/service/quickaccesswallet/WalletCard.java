package android.service.quickaccesswallet;

import android.app.PendingIntent;
import android.graphics.drawable.Icon;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class WalletCard implements Parcelable {
    public static final Parcelable.Creator<WalletCard> CREATOR = new Parcelable.Creator<WalletCard>() { // from class: android.service.quickaccesswallet.WalletCard.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WalletCard createFromParcel(Parcel parcel) {
            return WalletCard.readFromParcel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WalletCard[] newArray(int i) {
            return new WalletCard[i];
        }
    };
    private final Icon mCardIcon;
    private final String mCardId;
    private final Icon mCardImage;
    private final CharSequence mCardLabel;
    private final CharSequence mContentDescription;
    private final PendingIntent mPendingIntent;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private WalletCard(Builder builder) {
        this.mCardId = builder.mCardId;
        this.mCardImage = builder.mCardImage;
        this.mContentDescription = builder.mContentDescription;
        this.mPendingIntent = builder.mPendingIntent;
        this.mCardIcon = builder.mCardIcon;
        this.mCardLabel = builder.mCardLabel;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mCardId);
        this.mCardImage.writeToParcel(parcel, i);
        TextUtils.writeToParcel(this.mContentDescription, parcel, i);
        PendingIntent.writePendingIntentOrNullToParcel(this.mPendingIntent, parcel);
        if (this.mCardIcon == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            this.mCardIcon.writeToParcel(parcel, i);
        }
        TextUtils.writeToParcel(this.mCardLabel, parcel, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static WalletCard readFromParcel(Parcel parcel) {
        String string = parcel.readString();
        Icon iconCreateFromParcel = Icon.CREATOR.createFromParcel(parcel);
        CharSequence charSequenceCreateFromParcel = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        PendingIntent pendingIntentOrNullFromParcel = PendingIntent.readPendingIntentOrNullFromParcel(parcel);
        return new Builder(string, iconCreateFromParcel, charSequenceCreateFromParcel, pendingIntentOrNullFromParcel).setCardIcon(parcel.readByte() == 0 ? null : Icon.CREATOR.createFromParcel(parcel)).setCardLabel(TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel)).build();
    }

    public String getCardId() {
        return this.mCardId;
    }

    public Icon getCardImage() {
        return this.mCardImage;
    }

    public CharSequence getContentDescription() {
        return this.mContentDescription;
    }

    public PendingIntent getPendingIntent() {
        return this.mPendingIntent;
    }

    public Icon getCardIcon() {
        return this.mCardIcon;
    }

    public CharSequence getCardLabel() {
        return this.mCardLabel;
    }

    public static final class Builder {
        private Icon mCardIcon;
        private String mCardId;
        private Icon mCardImage;
        private CharSequence mCardLabel;
        private CharSequence mContentDescription;
        private PendingIntent mPendingIntent;

        public Builder(String str, Icon icon, CharSequence charSequence, PendingIntent pendingIntent) {
            this.mCardId = str;
            this.mCardImage = icon;
            this.mContentDescription = charSequence;
            this.mPendingIntent = pendingIntent;
        }

        public Builder setCardIcon(Icon icon) {
            this.mCardIcon = icon;
            return this;
        }

        public Builder setCardLabel(CharSequence charSequence) {
            this.mCardLabel = charSequence;
            return this;
        }

        public WalletCard build() {
            return new WalletCard(this);
        }
    }
}
