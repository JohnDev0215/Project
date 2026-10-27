package com.dee.app.contacts.dagger;

import com.dee.app.contacts.core.ContactsPreferenceManager;
import com.dee.app.contacts.core.CustomerIdentityManager;

/* JADX INFO: loaded from: classes5.dex */
public interface ContactsApiComponent {
    CustomerIdentityManager getCustomerIdentityManager();

    ContactsPreferenceManager getPreferenceManager();
}
