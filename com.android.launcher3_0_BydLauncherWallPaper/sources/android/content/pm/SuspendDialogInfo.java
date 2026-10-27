package android.content.pm;

import android.annotation.SystemApi;
import android.content.res.ResourceId;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Slog;
import com.android.internal.util.Preconditions;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
public final class SuspendDialogInfo implements Parcelable {
    public static final int BUTTON_ACTION_MORE_DETAILS = 0;
    public static final int BUTTON_ACTION_UNSUSPEND = 1;
    public static final Parcelable.Creator<SuspendDialogInfo> CREATOR = new Parcelable.Creator<SuspendDialogInfo>() { // from class: android.content.pm.SuspendDialogInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SuspendDialogInfo createFromParcel(Parcel parcel) {
            return new SuspendDialogInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SuspendDialogInfo[] newArray(int i) {
            return new SuspendDialogInfo[i];
        }
    };
    private static final String TAG = "SuspendDialogInfo";
    private static final String XML_ATTR_BUTTON_ACTION = "buttonAction";
    private static final String XML_ATTR_BUTTON_TEXT_RES_ID = "buttonTextResId";
    private static final String XML_ATTR_DIALOG_MESSAGE = "dialogMessage";
    private static final String XML_ATTR_DIALOG_MESSAGE_RES_ID = "dialogMessageResId";
    private static final String XML_ATTR_ICON_RES_ID = "iconResId";
    private static final String XML_ATTR_TITLE_RES_ID = "titleResId";
    private final String mDialogMessage;
    private final int mDialogMessageResId;
    private final int mIconResId;
    private final int mNeutralButtonAction;
    private final int mNeutralButtonTextResId;
    private final int mTitleResId;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ButtonAction {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getIconResId() {
        return this.mIconResId;
    }

    public int getTitleResId() {
        return this.mTitleResId;
    }

    public int getDialogMessageResId() {
        return this.mDialogMessageResId;
    }

    public String getDialogMessage() {
        return this.mDialogMessage;
    }

    public int getNeutralButtonTextResId() {
        return this.mNeutralButtonTextResId;
    }

    public int getNeutralButtonAction() {
        return this.mNeutralButtonAction;
    }

    public void saveToXml(XmlSerializer xmlSerializer) throws IOException {
        int i = this.mIconResId;
        if (i != 0) {
            XmlUtils.writeIntAttribute(xmlSerializer, "iconResId", i);
        }
        int i2 = this.mTitleResId;
        if (i2 != 0) {
            XmlUtils.writeIntAttribute(xmlSerializer, XML_ATTR_TITLE_RES_ID, i2);
        }
        int i3 = this.mDialogMessageResId;
        if (i3 != 0) {
            XmlUtils.writeIntAttribute(xmlSerializer, XML_ATTR_DIALOG_MESSAGE_RES_ID, i3);
        } else {
            XmlUtils.writeStringAttribute(xmlSerializer, XML_ATTR_DIALOG_MESSAGE, this.mDialogMessage);
        }
        int i4 = this.mNeutralButtonTextResId;
        if (i4 != 0) {
            XmlUtils.writeIntAttribute(xmlSerializer, XML_ATTR_BUTTON_TEXT_RES_ID, i4);
        }
        XmlUtils.writeIntAttribute(xmlSerializer, XML_ATTR_BUTTON_ACTION, this.mNeutralButtonAction);
    }

    public static SuspendDialogInfo restoreFromXml(XmlPullParser xmlPullParser) {
        Builder builder = new Builder();
        try {
            int intAttribute = XmlUtils.readIntAttribute(xmlPullParser, "iconResId", 0);
            int intAttribute2 = XmlUtils.readIntAttribute(xmlPullParser, XML_ATTR_TITLE_RES_ID, 0);
            int intAttribute3 = XmlUtils.readIntAttribute(xmlPullParser, XML_ATTR_BUTTON_TEXT_RES_ID, 0);
            int intAttribute4 = XmlUtils.readIntAttribute(xmlPullParser, XML_ATTR_BUTTON_ACTION, 0);
            int intAttribute5 = XmlUtils.readIntAttribute(xmlPullParser, XML_ATTR_DIALOG_MESSAGE_RES_ID, 0);
            String stringAttribute = XmlUtils.readStringAttribute(xmlPullParser, XML_ATTR_DIALOG_MESSAGE);
            if (intAttribute != 0) {
                builder.setIcon(intAttribute);
            }
            if (intAttribute2 != 0) {
                builder.setTitle(intAttribute2);
            }
            if (intAttribute3 != 0) {
                builder.setNeutralButtonText(intAttribute3);
            }
            if (intAttribute5 != 0) {
                builder.setMessage(intAttribute5);
            } else if (stringAttribute != null) {
                builder.setMessage(stringAttribute);
            }
            builder.setNeutralButtonAction(intAttribute4);
        } catch (Exception e) {
            Slog.e(TAG, "Exception while parsing from xml. Some fields may default", e);
        }
        return builder.build();
    }

    public int hashCode() {
        return (((((((((this.mIconResId * 31) + this.mTitleResId) * 31) + this.mNeutralButtonTextResId) * 31) + this.mDialogMessageResId) * 31) + Objects.hashCode(this.mDialogMessage)) * 31) + this.mNeutralButtonAction;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SuspendDialogInfo)) {
            return false;
        }
        SuspendDialogInfo suspendDialogInfo = (SuspendDialogInfo) obj;
        return this.mIconResId == suspendDialogInfo.mIconResId && this.mTitleResId == suspendDialogInfo.mTitleResId && this.mDialogMessageResId == suspendDialogInfo.mDialogMessageResId && this.mNeutralButtonTextResId == suspendDialogInfo.mNeutralButtonTextResId && this.mNeutralButtonAction == suspendDialogInfo.mNeutralButtonAction && Objects.equals(this.mDialogMessage, suspendDialogInfo.mDialogMessage);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SuspendDialogInfo: {");
        if (this.mIconResId != 0) {
            sb.append("mIconId = 0x");
            sb.append(Integer.toHexString(this.mIconResId));
            sb.append(" ");
        }
        if (this.mTitleResId != 0) {
            sb.append("mTitleResId = 0x");
            sb.append(Integer.toHexString(this.mTitleResId));
            sb.append(" ");
        }
        if (this.mNeutralButtonTextResId != 0) {
            sb.append("mNeutralButtonTextResId = 0x");
            sb.append(Integer.toHexString(this.mNeutralButtonTextResId));
            sb.append(" ");
        }
        if (this.mDialogMessageResId != 0) {
            sb.append("mDialogMessageResId = 0x");
            sb.append(Integer.toHexString(this.mDialogMessageResId));
            sb.append(" ");
        } else if (this.mDialogMessage != null) {
            sb.append("mDialogMessage = \"");
            sb.append(this.mDialogMessage);
            sb.append("\" ");
        }
        sb.append("mNeutralButtonAction = ");
        sb.append(this.mNeutralButtonAction);
        sb.append("}");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mIconResId);
        parcel.writeInt(this.mTitleResId);
        parcel.writeInt(this.mDialogMessageResId);
        parcel.writeString(this.mDialogMessage);
        parcel.writeInt(this.mNeutralButtonTextResId);
        parcel.writeInt(this.mNeutralButtonAction);
    }

    private SuspendDialogInfo(Parcel parcel) {
        this.mIconResId = parcel.readInt();
        this.mTitleResId = parcel.readInt();
        this.mDialogMessageResId = parcel.readInt();
        this.mDialogMessage = parcel.readString();
        this.mNeutralButtonTextResId = parcel.readInt();
        this.mNeutralButtonAction = parcel.readInt();
    }

    SuspendDialogInfo(Builder builder) {
        this.mIconResId = builder.mIconResId;
        this.mTitleResId = builder.mTitleResId;
        int i = builder.mDialogMessageResId;
        this.mDialogMessageResId = i;
        this.mDialogMessage = i == 0 ? builder.mDialogMessage : null;
        this.mNeutralButtonTextResId = builder.mNeutralButtonTextResId;
        this.mNeutralButtonAction = builder.mNeutralButtonAction;
    }

    public static final class Builder {
        private String mDialogMessage;
        private int mDialogMessageResId = 0;
        private int mTitleResId = 0;
        private int mIconResId = 0;
        private int mNeutralButtonTextResId = 0;
        private int mNeutralButtonAction = 0;

        public Builder setIcon(int i) {
            Preconditions.checkArgument(ResourceId.isValid(i), "Invalid resource id provided");
            this.mIconResId = i;
            return this;
        }

        public Builder setTitle(int i) {
            Preconditions.checkArgument(ResourceId.isValid(i), "Invalid resource id provided");
            this.mTitleResId = i;
            return this;
        }

        public Builder setMessage(String str) {
            Preconditions.checkStringNotEmpty(str, "Message cannot be null or empty");
            this.mDialogMessage = str;
            return this;
        }

        public Builder setMessage(int i) {
            Preconditions.checkArgument(ResourceId.isValid(i), "Invalid resource id provided");
            this.mDialogMessageResId = i;
            return this;
        }

        public Builder setNeutralButtonText(int i) {
            Preconditions.checkArgument(ResourceId.isValid(i), "Invalid resource id provided");
            this.mNeutralButtonTextResId = i;
            return this;
        }

        public Builder setNeutralButtonAction(int i) {
            boolean z = true;
            if (i != 0 && i != 1) {
                z = false;
            }
            Preconditions.checkArgument(z, "Invalid button action");
            this.mNeutralButtonAction = i;
            return this;
        }

        public SuspendDialogInfo build() {
            return new SuspendDialogInfo(this);
        }
    }
}
