package com.dee.app.contacts.interfaces.models.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.autofill.HintConstants;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ContactPhoneNumber implements Parcelable {
    public static final Parcelable.Creator<ContactPhoneNumber> CREATOR = new Parcelable.Creator<ContactPhoneNumber>() { // from class: com.dee.app.contacts.interfaces.models.data.ContactPhoneNumber.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ContactPhoneNumber createFromParcel(Parcel parcel) {
            return new ContactPhoneNumber(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ContactPhoneNumber[] newArray(int i) {
            return new ContactPhoneNumber[i];
        }
    };

    @JsonProperty("contactSource")
    private String contactSource;

    @JsonProperty("eligibilityStatusList")
    private List<String> eligibilityStatusList;

    @JsonProperty("isCOBOEnabled")
    private boolean isCOBOEnabled;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("obfuscatedPhoneNumber")
    private String obfuscatedPhoneNumber;

    @JsonProperty("number")
    private String phoneNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("rawType")
    private String rawType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)
    private PhoneNumberType type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ContactPhoneNumber() {
        this.isCOBOEnabled = false;
        this.eligibilityStatusList = Collections.EMPTY_LIST;
        this.contactSource = "";
    }

    private ContactPhoneNumber(Parcel parcel) {
        this.isCOBOEnabled = false;
        this.eligibilityStatusList = Collections.EMPTY_LIST;
        this.contactSource = "";
        this.phoneNumber = parcel.readString();
        this.rawType = parcel.readString();
        this.type = (PhoneNumberType) parcel.readParcelable(PhoneNumberType.class.getClassLoader());
        this.obfuscatedPhoneNumber = parcel.readString();
        this.isCOBOEnabled = parcel.readInt() != 0;
        this.contactSource = parcel.readString();
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
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
        ContactPhoneNumber contactPhoneNumber = (ContactPhoneNumber) obj;
        return Objects.equal(this.type, contactPhoneNumber.type) && Objects.equal(this.rawType, contactPhoneNumber.rawType) && Objects.equal(this.phoneNumber, contactPhoneNumber.phoneNumber) && Objects.equal(this.obfuscatedPhoneNumber, contactPhoneNumber.obfuscatedPhoneNumber) && Objects.equal(Boolean.valueOf(this.isCOBOEnabled), Boolean.valueOf(contactPhoneNumber.isCOBOEnabled)) && Objects.equal(this.contactSource, contactPhoneNumber.contactSource);
    }

    public int hashCode() {
        return Objects.hashCode(this.type, this.rawType, this.phoneNumber, this.obfuscatedPhoneNumber, Boolean.valueOf(this.isCOBOEnabled), this.contactSource);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).add("rawType", this.rawType).add(HintConstants.AUTOFILL_HINT_PHONE_NUMBER, this.phoneNumber).add("obfuscatedPhoneNumber", this.obfuscatedPhoneNumber).add("isCOBOEnabled", this.isCOBOEnabled).add("contactSource", this.contactSource).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.phoneNumber);
        String str = this.rawType;
        if (str == null) {
            str = "";
        }
        parcel.writeString(str);
        parcel.writeParcelable(this.type, i);
        parcel.writeString(this.obfuscatedPhoneNumber);
        parcel.writeInt(this.isCOBOEnabled ? 1 : 0);
        parcel.writeString(this.contactSource);
    }
}
