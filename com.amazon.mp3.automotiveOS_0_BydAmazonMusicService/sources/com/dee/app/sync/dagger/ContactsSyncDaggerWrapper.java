package com.dee.app.sync.dagger;

import com.dee.app.contacts.common.dagger.ContactsCoreComponent;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes5.dex */
public final class ContactsSyncDaggerWrapper {
    private static ContactsSyncComponent contactsSyncComponent;

    private ContactsSyncDaggerWrapper() {
    }

    public static ContactsSyncComponent getContactsSyncComponent(@Nonnull ContactsCoreComponent contactsCoreComponent) {
        ContactsSyncComponent contactsSyncComponent2 = contactsSyncComponent;
        if (contactsSyncComponent2 != null) {
            return contactsSyncComponent2;
        }
        ContactsSyncComponent contactsSyncComponentBuild = DaggerContactsSyncComponent.builder().contactsCoreComponent(contactsCoreComponent).build();
        contactsSyncComponent = contactsSyncComponentBuild;
        return contactsSyncComponentBuild;
    }
}
