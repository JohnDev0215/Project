package com.dee.app.contacts.common.dagger;

import android.content.Context;
import com.dee.app.contacts.common.dagger.modules.AppModule;
import com.dee.app.contacts.common.dagger.modules.CommsCoreModule;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes5.dex */
public final class ContactsCoreDaggerWrapper {
    private static ContactsCoreComponent contactsCoreComponent;

    private ContactsCoreDaggerWrapper() {
    }

    public static ContactsCoreComponent getContactsCoreComponent(@Nonnull CommsCoreModule commsCoreModule, @Nonnull Context context) {
        ContactsCoreComponent contactsCoreComponent2 = contactsCoreComponent;
        return contactsCoreComponent2 != null ? contactsCoreComponent2 : DaggerContactsCoreComponent.builder().commsCoreModule(commsCoreModule).appModule(new AppModule(context)).build();
    }
}
