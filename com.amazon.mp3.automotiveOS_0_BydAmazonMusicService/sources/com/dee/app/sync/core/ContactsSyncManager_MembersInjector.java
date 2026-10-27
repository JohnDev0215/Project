package com.dee.app.sync.core;

import com.dee.app.sync.api.ContactsSyncApiHandler;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes5.dex */
public final class ContactsSyncManager_MembersInjector implements MembersInjector<ContactsSyncManager> {
    public static void injectContactsSyncApiHandler(ContactsSyncManager contactsSyncManager, ContactsSyncApiHandler contactsSyncApiHandler) {
        contactsSyncManager.contactsSyncApiHandler = contactsSyncApiHandler;
    }
}
