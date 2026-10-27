package com.dee.app.contacts.core;

import com.dee.app.contacts.api.PreferenceApiHandler;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes5.dex */
public final class ContactsPreferenceManager_MembersInjector implements MembersInjector<ContactsPreferenceManager> {
    public static void injectMPreferenceApiHandler(ContactsPreferenceManager contactsPreferenceManager, PreferenceApiHandler preferenceApiHandler) {
        contactsPreferenceManager.mPreferenceApiHandler = preferenceApiHandler;
    }
}
