package com.dee.app.contacts.interfaces.models.apis.getcontacts.getcontactsv2;

import com.dee.app.contacts.interfaces.models.data.enums.DedupeMode;
import com.dee.app.contacts.interfaces.models.data.enums.OwnerIdType;
import com.dee.app.contacts.interfaces.models.data.enums.PreferenceLevel;

/* JADX INFO: loaded from: classes5.dex */
public class GetContactsV2QueryConfig {
    private Boolean bulkImportOnly;
    private DedupeMode dedupeMode;
    private Boolean includeBlockStatus;
    private Boolean includeHomeGroupMembers;
    private Boolean includeMergedContacts;
    private Boolean includeNonAlexaEnabledContacts;
    private OwnerIdType ownerIdType;
    private PreferenceLevel preferenceLevel = PreferenceLevel.NONE;

    public GetContactsV2QueryConfig() {
        Boolean bool = Boolean.TRUE;
        this.includeHomeGroupMembers = bool;
        this.includeNonAlexaEnabledContacts = bool;
        this.bulkImportOnly = Boolean.FALSE;
        this.includeBlockStatus = bool;
        this.dedupeMode = DedupeMode.REMOVE_CLOUD_ONLY;
        this.includeMergedContacts = bool;
    }

    public Boolean getIncludeHomeGroupMembers() {
        return this.includeHomeGroupMembers;
    }

    public Boolean getIncludeNonAlexaEnabledContacts() {
        return this.includeNonAlexaEnabledContacts;
    }

    public Boolean getBulkImportOnly() {
        return this.bulkImportOnly;
    }

    public Boolean getIncludeBlockStatus() {
        return this.includeBlockStatus;
    }

    public DedupeMode getDedupeMode() {
        return this.dedupeMode;
    }

    public Boolean getIncludeMergedContacts() {
        return this.includeMergedContacts;
    }

    public OwnerIdType getOwnerIdType() {
        return this.ownerIdType;
    }

    public void setOwnerIdType(OwnerIdType ownerIdType) {
        this.ownerIdType = ownerIdType;
    }
}
