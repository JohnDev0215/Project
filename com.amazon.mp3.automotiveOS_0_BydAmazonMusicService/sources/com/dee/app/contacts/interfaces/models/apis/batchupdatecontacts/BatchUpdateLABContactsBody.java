package com.dee.app.contacts.interfaces.models.apis.batchupdatecontacts;

import com.dee.app.contacts.interfaces.models.data.ContactForSync;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class BatchUpdateLABContactsBody {
    private String contactSourceType;
    private List<ContactForSync> contactsForSyncList;
    private Boolean isBatchFinalSync;
    private String sourceDeviceId;
    private String sourceDeviceName;

    public BatchUpdateLABContactsBody(String str, String str2, Boolean bool, String str3, List<ContactForSync> list) {
        this.sourceDeviceId = str;
        this.sourceDeviceName = str2;
        this.isBatchFinalSync = bool;
        this.contactsForSyncList = list;
        this.contactSourceType = str3;
    }
}
