package com.dee.app.contacts.interfaces.models.data;

import android.util.Log;
import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ContactUploadInfo {
    private String company;
    private String contactSourceType;
    private String deviceContactId;
    private List<EmailAddress> emails;
    private Name name;
    private List<PhoneNumber> numbers;
    private String relationship;
    private String serverContactId;
    private String sourceDeviceId;
    private String LOG_TAG = "KContacts";
    private int MAX_PHONE_NUMBERS_TO_IMPORT = 25;
    private int MAX_EMAILS_TO_IMPORT = 25;

    public void setDeviceContactId(String str) {
        this.deviceContactId = str;
    }

    public void setSourceDeviceId(String str) {
        this.sourceDeviceId = str;
    }

    public Name getName() {
        return this.name;
    }

    public List<PhoneNumber> getNumbers() {
        return this.numbers;
    }

    public List<EmailAddress> getEmails() {
        return this.emails;
    }

    public String getDeviceContactId() {
        return this.deviceContactId;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String str) {
        this.company = str;
    }

    public void setRelationship(String str) {
        this.relationship = str;
    }

    public void setContactName(String str, String str2, String str3) {
        this.name = new Name(this, str, str2, str3);
    }

    public void setContactName(String str, String str2, String str3, String str4, String str5) {
        this.name = new Name(str, str2, str3, str4, null, null, str5);
    }

    public void setPhoneNumbers(List<PhoneNumber> list) {
        this.numbers = list;
    }

    public void setEmailAddresses(List<EmailAddress> list) {
        this.emails = list;
    }

    public void addPhoneNumber(String str, String str2) {
        if (this.numbers == null) {
            this.numbers = new ArrayList();
        }
        if (this.numbers.size() < this.MAX_PHONE_NUMBERS_TO_IMPORT) {
            this.numbers.add(new PhoneNumber(str, str2));
            return;
        }
        Log.w(this.LOG_TAG, "The phonenumber count being imported " + this.numbers.size() + " is greater than the permitted amount");
    }

    public void addEmailAddress(String str, String str2) {
        if (this.emails == null) {
            this.emails = new ArrayList();
        }
        if (this.emails.size() < this.MAX_EMAILS_TO_IMPORT) {
            this.emails.add(new EmailAddress(str, str2));
            return;
        }
        Log.w(this.LOG_TAG, "The email count being imported " + this.emails.size() + " is greater than the permitted amount");
    }

    public void setServerContactId(String str) {
        this.serverContactId = str;
    }

    public void setContactSourceType(String str) {
        this.contactSourceType = str;
    }

    public class Name {
        private String firstName;
        private String lastName;
        private String nickName;
        private String phoneticFirstName;
        private String phoneticLastName;
        private String pronunciationFirstName;
        private String pronunciationLastName;

        public void setFirstName(String str) {
            this.firstName = str;
        }

        public void setLastName(String str) {
            this.lastName = str;
        }

        public void setPhoneticFirstName(String str) {
            this.phoneticFirstName = str;
        }

        public void setPhoneticLastName(String str) {
            this.phoneticLastName = str;
        }

        public void setNickName(String str) {
            this.nickName = str;
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

        public String getPronunciationFirstName() {
            return this.pronunciationFirstName;
        }

        public String getPronunciationLastName() {
            return this.pronunciationLastName;
        }

        public String getNickName() {
            return this.nickName;
        }

        public Name(ContactUploadInfo contactUploadInfo, String str, String str2, String str3) {
            this(str, str2, null, null, null, null, str3);
        }

        public Name(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
            this.firstName = str;
            this.lastName = str2;
            this.phoneticFirstName = str3;
            this.phoneticLastName = str4;
            this.pronunciationFirstName = str5;
            this.pronunciationLastName = str6;
            this.nickName = str7;
        }

        public int hashCode() {
            return Objects.hashCode(this.firstName, this.lastName, this.phoneticFirstName, this.phoneticLastName, this.pronunciationFirstName, this.pronunciationLastName, this.nickName);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Name name = (Name) obj;
            return Objects.equal(this.firstName, name.firstName) && Objects.equal(this.lastName, name.lastName) && Objects.equal(this.phoneticFirstName, name.phoneticFirstName) && Objects.equal(this.phoneticLastName, name.phoneticLastName) && Objects.equal(this.pronunciationFirstName, name.pronunciationFirstName) && Objects.equal(this.pronunciationLastName, name.pronunciationLastName) && Objects.equal(this.nickName, name.nickName);
        }
    }

    public class PhoneNumber {
        private String number;
        private String rawType;

        public String getRawType() {
            return this.rawType;
        }

        public String getNumber() {
            return this.number;
        }

        public void setNumber(String str) {
            this.number = str;
        }

        public PhoneNumber(String str, String str2) {
            this.rawType = str2;
            this.number = str;
        }

        public int hashCode() {
            return Objects.hashCode(this.rawType, this.number);
        }
    }

    public class EmailAddress {
        private String emailAddress;
        private String rawType;

        public String getRawType() {
            return this.rawType;
        }

        public String getEmailAddress() {
            return this.emailAddress;
        }

        public void setEmailAddress(String str) {
            this.emailAddress = str;
        }

        public EmailAddress(String str, String str2) {
            this.rawType = str2;
            this.emailAddress = str;
        }

        public int hashCode() {
            return Objects.hashCode(this.rawType, this.emailAddress);
        }
    }

    public int hashCode() {
        return Objects.hashCode(this.name, this.numbers, this.emails, this.company);
    }
}
