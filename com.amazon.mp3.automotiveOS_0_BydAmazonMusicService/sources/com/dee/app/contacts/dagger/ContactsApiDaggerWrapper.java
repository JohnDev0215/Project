package com.dee.app.contacts.dagger;

import com.dee.app.contacts.common.dagger.ContactsCoreComponent;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes5.dex */
public final class ContactsApiDaggerWrapper {
    private static ContactsApiComponent contactsApiComponent;

    private ContactsApiDaggerWrapper() {
    }

    public static ContactsApiComponent getContactsApiComponent(@Nonnull ContactsCoreComponent contactsCoreComponent) {
        ContactsApiComponent contactsApiComponentBuild = DaggerContactsApiComponent.builder().contactsCoreComponent(contactsCoreComponent).build();
        contactsApiComponent = contactsApiComponentBuild;
        return contactsApiComponentBuild;
    }
}
