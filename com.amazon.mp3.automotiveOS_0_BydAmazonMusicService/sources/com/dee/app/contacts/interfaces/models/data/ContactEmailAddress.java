package com.dee.app.contacts.interfaces.models.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.autofill.HintConstants;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.dee.app.contacts.interfaces.models.data.enums.EmailAddressType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/* JADX INFO: loaded from: classes5.dex */
public class ContactEmailAddress implements Parcelable {
    public static final Parcelable.Creator<ContactEmailAddress> CREATOR = new Parcelable.Creator<ContactEmailAddress>() { // from class: com.dee.app.contacts.interfaces.models.data.ContactEmailAddress.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ContactEmailAddress createFromParcel(Parcel parcel) {
            return new ContactEmailAddress(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ContactEmailAddress[] newArray(int i) {
            return new ContactEmailAddress[i];
        }
    };

    @JsonProperty(HintConstants.AUTOFILL_HINT_EMAIL_ADDRESS)
    private String emailAddress;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("rawType")
    private String rawType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)
    private EmailAddressType type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ContactEmailAddress() {
    }

    private ContactEmailAddress(Parcel parcel) {
        this.emailAddress = parcel.readString();
        this.rawType = parcel.readString();
        this.type = (EmailAddressType) parcel.readParcelable(EmailAddressType.class.getClassLoader());
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public String getRawType() {
        return this.rawType;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ContactEmailAddress contactEmailAddress = (ContactEmailAddress) obj;
        return Objects.equal(this.type, contactEmailAddress.type) && Objects.equal(this.rawType, contactEmailAddress.rawType) && Objects.equal(this.emailAddress, contactEmailAddress.emailAddress);
    }

    public int hashCode() {
        return Objects.hashCode(this.type, this.rawType, this.emailAddress);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).add("rawType", this.rawType).add(HintConstants.AUTOFILL_HINT_EMAIL_ADDRESS, this.emailAddress).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.emailAddress);
        parcel.writeString(this.rawType);
        parcel.writeParcelable(this.type, i);
    }
}
