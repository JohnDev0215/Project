package com.dee.app.contacts.interfaces.models.data.server;

import com.dee.app.contacts.interfaces.models.data.ContactEmailAddress;
import com.dee.app.contacts.interfaces.models.data.ContactName;
import com.dee.app.contacts.interfaces.models.data.ContactPhoneNumber;
import com.dee.app.contacts.interfaces.models.data.PreferenceInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ServerContact {

    @JsonProperty("addresses")
    private List<Object> addresses;

    @JsonProperty("alexaEnabled")
    private boolean alexaEnabled;

    @JsonProperty("commsId")
    private List<String> commsIds;

    @JsonProperty("company")
    private String company;

    @JsonProperty("contactPreferenceInfoList")
    private List<PreferenceInfo> contactPreferenceInfoList;

    @JsonProperty("contactSourceType")
    private String contactSourceType;

    @JsonProperty("deviceContactId")
    private String deviceContactId;

    @JsonProperty("emails")
    private List<ContactEmailAddress> emails;

    @JsonProperty("id")
    private String id;

    @JsonProperty("isBlocked")
    private boolean isBlocked;

    @JsonProperty("isBulkImport")
    private boolean isBulkImport;

    @JsonProperty("isChild")
    private boolean isChild;

    @JsonProperty("isHomeGroup")
    private boolean isHomeGroup;

    @JsonProperty("isLinked")
    private boolean isLinked;

    @JsonProperty("isMerged")
    private boolean isMerged;

    @JsonProperty("mergedContactIdentifiers")
    private List<String> mergedContactIdentifiers;

    @JsonProperty("name")
    private ContactName name;

    @JsonProperty("ownerCommsId")
    private String ownerCommsId;

    @JsonProperty("numbers")
    private List<ContactPhoneNumber> phoneNumbers;

    @JsonProperty("referenceCommsId")
    private String referenceCommsId;

    @JsonProperty("referenceContactId")
    private String referenceContactId;

    @JsonProperty("relationship")
    private String relationship;

    @JsonProperty("shouldDisplay")
    private boolean shouldDisplay;

    @JsonProperty("shouldMerge")
    private boolean shouldMerge;

    @JsonProperty("sourceDeviceId")
    private String sourceDeviceId;

    public ContactName getName() {
        return this.name;
    }

    public String getSourceDeviceId() {
        return this.sourceDeviceId;
    }

    public List<ContactPhoneNumber> getPhoneNumbers() {
        return this.phoneNumbers;
    }

    public List<ContactEmailAddress> getEmails() {
        return this.emails;
    }

    public boolean isAlexaEnabled() {
        return this.alexaEnabled;
    }

    public boolean isHomeGroup() {
        return this.isHomeGroup;
    }

    public boolean isBulkImport() {
        return this.isBulkImport;
    }

    public boolean isShouldDisplay() {
        return this.shouldDisplay;
    }

    public String getId() {
        return this.id;
    }

    public String getCompany() {
        return this.company;
    }

    public List<String> getCommsIds() {
        return this.commsIds;
    }

    public String getDeviceContactId() {
        return this.deviceContactId;
    }

    public String getOwnerCommsId() {
        return this.ownerCommsId;
    }

    public boolean isBlocked() {
        return this.isBlocked;
    }

    public boolean isChild() {
        return this.isChild;
    }

    public String getRelationship() {
        return this.relationship;
    }

    public String getReferenceCommsId() {
        return this.referenceCommsId;
    }

    public String getReferenceContactId() {
        return this.referenceContactId;
    }

    public boolean isLinked() {
        return this.isLinked;
    }

    public String getContactSourceType() {
        return this.contactSourceType;
    }

    public boolean isMerged() {
        return this.isMerged;
    }

    public boolean isShouldMerge() {
        return this.shouldMerge;
    }

    public List<Object> getAddresses() {
        return this.addresses;
    }

    public List<String> getMergedContactIdentifiers() {
        return this.mergedContactIdentifiers;
    }

    public List<PreferenceInfo> getContactPreferenceInfoList() {
        return this.contactPreferenceInfoList;
    }
}
