package com.amazon.music.connect.contacts;

import android.content.Context;
import com.amazon.music.connect.contacts.providers.ContactsAuthenticationProvider;
import com.amazon.music.connect.contacts.providers.ContactsMetricsProvider;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.dee.app.contacts.common.dagger.ContactsCoreComponent;
import com.dee.app.contacts.common.exceptions.InvalidInputException;
import com.dee.app.contacts.core.ContactsDaggerWrapper;
import com.dee.app.contacts.dagger.ContactsApiComponent;
import com.dee.app.contacts.dagger.ContactsApiDaggerWrapper;
import com.dee.app.sync.dagger.ContactsSyncComponent;
import com.dee.app.sync.dagger.ContactsSyncDaggerWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContactsSDKWrapper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u001c\u0010\u0007\u001a\n \u0006*\u0004\u0018\u00010\u00050\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\bR\u001f\u0010\n\u001a\n \u0006*\u0004\u0018\u00010\t0\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001f\u0010\u000f\u001a\n \u0006*\u0004\u0018\u00010\u000e0\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/connect/contacts/ContactsSDKWrapper;", "", "", "TAG", "Ljava/lang/String;", "Lcom/dee/app/contacts/common/dagger/ContactsCoreComponent;", "kotlin.jvm.PlatformType", "contactsCoreComponent", "Lcom/dee/app/contacts/common/dagger/ContactsCoreComponent;", "Lcom/dee/app/sync/dagger/ContactsSyncComponent;", "contactsSyncComponent", "Lcom/dee/app/sync/dagger/ContactsSyncComponent;", "getContactsSyncComponent", "()Lcom/dee/app/sync/dagger/ContactsSyncComponent;", "Lcom/dee/app/contacts/dagger/ContactsApiComponent;", "contactsApiComponent", "Lcom/dee/app/contacts/dagger/ContactsApiComponent;", "getContactsApiComponent", "()Lcom/dee/app/contacts/dagger/ContactsApiComponent;", "Landroid/content/Context;", "context", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "authenticationProvider", "Lcom/amazon/music/platform/providers/MetricsProvider;", "metricsProvider", "<init>", "(Landroid/content/Context;Lcom/amazon/music/platform/providers/AuthenticationProvider;Lcom/amazon/music/platform/providers/MetricsProvider;)V", "DMMConnect_release"}, k = 1, mv = {1, 6, 0})
public final class ContactsSDKWrapper {
    private final String TAG;
    private final ContactsApiComponent contactsApiComponent;
    private final ContactsCoreComponent contactsCoreComponent;
    private final ContactsSyncComponent contactsSyncComponent;

    public ContactsSDKWrapper(Context context, AuthenticationProvider authenticationProvider, MetricsProvider metricsProvider) throws InvalidInputException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(authenticationProvider, "authenticationProvider");
        Intrinsics.checkNotNullParameter(metricsProvider, "metricsProvider");
        String simpleName = ContactsSDKWrapper.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "ContactsSDKWrapper::class.java.simpleName");
        this.TAG = simpleName;
        ContactsCoreComponent contactsCoreComponentInitialise = ContactsDaggerWrapper.initialise(context, null, new ContactsAuthenticationProvider(authenticationProvider), new ContactsMetricsProvider(metricsProvider));
        this.contactsCoreComponent = contactsCoreComponentInitialise;
        this.contactsSyncComponent = ContactsSyncDaggerWrapper.getContactsSyncComponent(contactsCoreComponentInitialise);
        this.contactsApiComponent = ContactsApiDaggerWrapper.getContactsApiComponent(contactsCoreComponentInitialise);
    }

    public final ContactsSyncComponent getContactsSyncComponent() {
        return this.contactsSyncComponent;
    }

    public final ContactsApiComponent getContactsApiComponent() {
        return this.contactsApiComponent;
    }
}
