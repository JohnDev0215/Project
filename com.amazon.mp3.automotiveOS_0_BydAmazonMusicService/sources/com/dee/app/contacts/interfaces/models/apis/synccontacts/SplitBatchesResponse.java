package com.dee.app.contacts.interfaces.models.apis.synccontacts;

import com.dee.app.contacts.interfaces.models.data.ContactForSync;
import com.dee.app.contacts.interfaces.models.data.enums.ContactSourceType;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class SplitBatchesResponse {
    private ContactSourceType contactSourceType;
    private List<List<ContactForSync>> initialBatches;
    private List<List<ContactForSync>> lastBatch;

    public SplitBatchesResponse(List<List<ContactForSync>> list, List<List<ContactForSync>> list2, ContactSourceType contactSourceType) {
        this.initialBatches = list;
        this.lastBatch = list2;
        this.contactSourceType = contactSourceType;
    }

    public List<List<ContactForSync>> getInitialBatches() {
        return this.initialBatches;
    }

    public List<List<ContactForSync>> getLastBatch() {
        return this.lastBatch;
    }
}
