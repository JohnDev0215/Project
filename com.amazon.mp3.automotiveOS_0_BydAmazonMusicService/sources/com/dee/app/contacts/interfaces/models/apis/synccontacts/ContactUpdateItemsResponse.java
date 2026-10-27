package com.dee.app.contacts.interfaces.models.apis.synccontacts;

import com.dee.app.contacts.interfaces.models.data.ContactForSync;
import com.dee.app.contacts.interfaces.models.data.enums.ContactSourceType;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ContactUpdateItemsResponse {
    private ContactSourceType contactSourceType;
    private List<ContactForSync> deleteOnlyItems;
    private List<ContactForSync> nonDeleteItems;

    public ContactUpdateItemsResponse(List<ContactForSync> list, List<ContactForSync> list2, ContactSourceType contactSourceType) {
        this.deleteOnlyItems = list;
        this.nonDeleteItems = list2;
        this.contactSourceType = contactSourceType;
    }

    public List<ContactForSync> getDeleteOnlyItems() {
        return this.deleteOnlyItems;
    }

    public List<ContactForSync> getNonDeleteItems() {
        return this.nonDeleteItems;
    }

    public ContactSourceType getContactSourceType() {
        return this.contactSourceType;
    }
}
