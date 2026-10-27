package com.dee.app.contacts.interfaces.models.data;

import com.dee.app.contacts.interfaces.models.data.preference.contactpreference.ContactPreferences;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class Contact {

    @JsonProperty("addresses")
    private List<Object> addresses;

    @JsonProperty("alexaEnabled")
    private boolean alexaEnabled;

    @JsonProperty("commsIds")
    private List<String> commsIds;

    @JsonProperty("company")
    private String company;

    @JsonProperty("contactPreferences")
    @JsonAlias({"contactPreferenceDBMap", "contactPreferences"})
    private ContactPreferences contactPreferences;

    @JsonProperty("contactSourceType")
    private String contactSourceType;

    @JsonProperty("deviceContactId")
    private String deviceContactId;

    @JsonProperty("emails")
    private List<ContactEmailAddress> emails;

    @JsonProperty("id")
    private String id;

    @JsonProperty("isBlocked")
    private boolean isBlocked = false;

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
    @JsonAlias({"numbers", "phoneNumbers"})
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

    public void setAlexaEnabled(boolean z) {
        this.alexaEnabled = z;
    }

    public void setCommsIds(List<String> list) {
        this.commsIds = list;
    }

    public String getDeviceContactId() {
        return this.deviceContactId;
    }

    public void setDeviceContactId(String str) {
        this.deviceContactId = str;
    }

    public ContactName getContactName() {
        return this.name;
    }

    public void setContactName(ContactName contactName) {
        this.name = contactName;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String str) {
        this.company = str;
    }

    public List<ContactPhoneNumber> getPhoneNumbers() {
        return this.phoneNumbers;
    }

    public void setPhoneNumbers(List<ContactPhoneNumber> list) {
        this.phoneNumbers = list;
    }

    public List<ContactEmailAddress> getEmails() {
        return this.emails;
    }

    public void setEmails(List<ContactEmailAddress> list) {
        this.emails = list;
    }

    public String getSourceDeviceId() {
        return this.sourceDeviceId;
    }

    public void setSourceDeviceId(String str) {
        this.sourceDeviceId = str;
    }

    public void setOwnerCommsId(String str) {
        this.ownerCommsId = str;
    }

    public void setHomeGroup(boolean z) {
        this.isHomeGroup = z;
    }

    public void setBlocked(boolean z) {
        this.isBlocked = z;
    }

    public void setBulkImport(boolean z) {
        this.isBulkImport = z;
    }

    public void setShouldDisplay(boolean z) {
        this.shouldDisplay = z;
    }

    public void setIsChild(boolean z) {
        this.isChild = z;
    }

    public String getRelationship() {
        return this.relationship;
    }

    public void setRelationship(String str) {
        this.relationship = str;
    }

    public void setReferenceCommsId(String str) {
        this.referenceCommsId = str;
    }

    public void setReferenceContactId(String str) {
        this.referenceContactId = str;
    }

    public void setIsLinked(boolean z) {
        this.isLinked = z;
    }

    public void setContactSourceType(String str) {
        this.contactSourceType = str;
    }

    public String getContactSourceType() {
        return this.contactSourceType;
    }

    public void setIsMerged(boolean z) {
        this.isMerged = z;
    }

    public void setShouldMerge(boolean z) {
        this.shouldMerge = z;
    }

    public void setAddresses(List<Object> list) {
        this.addresses = list;
    }

    public void setMergedContactIdentifiers(List<String> list) {
        this.mergedContactIdentifiers = list;
    }

    public void setContactPreferences(ContactPreferences contactPreferences) {
        this.contactPreferences = contactPreferences;
    }
}
