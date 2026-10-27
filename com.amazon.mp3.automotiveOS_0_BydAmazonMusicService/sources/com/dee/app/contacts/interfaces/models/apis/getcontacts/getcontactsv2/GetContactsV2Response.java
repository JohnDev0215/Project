package com.dee.app.contacts.interfaces.models.apis.getcontacts.getcontactsv2;

import com.dee.app.contacts.interfaces.models.data.Contact;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class GetContactsV2Response {
    List<Contact> contacts;

    public GetContactsV2Response(List<Contact> list) {
        this.contacts = list;
    }
}
