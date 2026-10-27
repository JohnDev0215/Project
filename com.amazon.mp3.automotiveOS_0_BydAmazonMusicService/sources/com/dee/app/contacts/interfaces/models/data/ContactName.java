package com.dee.app.contacts.interfaces.models.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/* JADX INFO: loaded from: classes5.dex */
public class ContactName implements Parcelable {

    @JsonIgnore
    public static final Parcelable.Creator<ContactName> CREATOR = new Parcelable.Creator<ContactName>() { // from class: com.dee.app.contacts.interfaces.models.data.ContactName.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ContactName createFromParcel(Parcel parcel) {
            return new ContactName(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ContactName[] newArray(int i) {
            return new ContactName[i];
        }
    };

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("nickName")
    private String nickName;

    @JsonProperty("phoneticFirstName")
    private String phoneticFirstName;
    private String phoneticFirstNameSortFormat;

    @JsonProperty("phoneticLastName")
    private String phoneticLastName;
    private String phoneticLastNameSortFormat;

    @JsonProperty("pronunciationFirstName")
    private String pronunciationFirstName;

    @JsonProperty("pronunciationLastName")
    private String pronunciationLastName;

    @JsonProperty("sourceNickName")
    private String sourceNickName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ContactName() {
    }

    @JsonIgnore
    public ContactName(Parcel parcel) {
        this.firstName = parcel.readString();
        this.lastName = parcel.readString();
        this.phoneticFirstName = parcel.readString();
        this.phoneticLastName = parcel.readString();
        this.pronunciationFirstName = parcel.readString();
        this.pronunciationLastName = parcel.readString();
        this.nickName = parcel.readString();
        this.sourceNickName = parcel.readString();
        this.phoneticFirstNameSortFormat = parcel.readString();
        this.phoneticLastNameSortFormat = parcel.readString();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPhoneticFirstName() {
        return this.phoneticFirstName;
    }

    public String getPhoneticLastName() {
        return this.phoneticLastName;
    }

    public String getNickName() {
        return this.nickName;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ContactName contactName = (ContactName) obj;
        return Objects.equal(this.firstName, contactName.firstName) && Objects.equal(this.lastName, contactName.lastName) && Objects.equal(this.phoneticFirstName, contactName.phoneticFirstName) && Objects.equal(this.phoneticLastName, contactName.phoneticLastName) && Objects.equal(this.pronunciationFirstName, contactName.pronunciationFirstName) && Objects.equal(this.pronunciationLastName, contactName.pronunciationLastName) && Objects.equal(this.nickName, contactName.nickName) && Objects.equal(this.sourceNickName, contactName.sourceNickName);
    }

    public int hashCode() {
        return Objects.hashCode(this.firstName, this.lastName, this.phoneticFirstName, this.phoneticLastName, this.pronunciationFirstName, this.pronunciationLastName, this.nickName, this.sourceNickName);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("first name : ", this.firstName).add("last name : ", this.lastName).add("phonetic first name : ", this.phoneticFirstName).add("phonetic last name : ", this.phoneticLastName).add("pronunciation first name : ", this.pronunciationFirstName).add("pronunciation last name : ", this.pronunciationLastName).add("nick name : ", this.nickName).add("sourceNickName: ", this.sourceNickName).add("phonetic first name sorting format : ", this.phoneticFirstNameSortFormat).add("phonetic last nme sorting format : ", this.phoneticLastNameSortFormat).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.firstName);
        parcel.writeString(this.lastName);
        parcel.writeString(this.phoneticFirstName);
        parcel.writeString(this.phoneticLastName);
        parcel.writeString(this.pronunciationFirstName);
        parcel.writeString(this.pronunciationLastName);
        parcel.writeString(this.nickName);
        parcel.writeString(this.sourceNickName);
        parcel.writeString(this.phoneticFirstNameSortFormat);
        parcel.writeString(this.phoneticLastNameSortFormat);
    }
}
