package com.dee.app.contacts.common.errors;

import com.dee.app.contacts.common.exceptions.ContactsException;

/* JADX INFO: loaded from: classes5.dex */
public class ContactsError extends Error {
    public ContactsError() throws ContactsException {
        throw new ContactsException("Error occurred during Contacts flows");
    }

    public ContactsError(String str) throws ContactsException {
        throw new ContactsException(str);
    }
}
