package com.dee.app.sync.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commsnetworking.api.INetworkingClient;
import com.dee.app.contacts.common.constants.ContactConfig;
import com.dee.app.contacts.common.dagger.ContactsCoreComponent;
import com.dee.app.contacts.common.dagger.modules.ContactsCommonModule;
import com.dee.app.contacts.common.network.gateway.ContactsSyncGatewayService;
import com.dee.app.contacts.common.utils.ActivityLifecycleUtil;
import com.dee.app.contacts.common.utils.ContactsMetricsRecorder;
import com.dee.app.contacts.common.utils.NetworkUtils;
import com.dee.app.contacts.common.utils.PermissionsUtils;
import com.dee.app.contacts.common.utils.SyncUtils;
import com.dee.app.contacts.common.utils.UserAgent;
import com.dee.app.sync.api.ContactsSyncApiHandler;
import com.dee.app.sync.api.sync.CanSyncContactsApi;
import com.dee.app.sync.api.sync.ContactsDownSyncApi;
import com.dee.app.sync.api.sync.ContactsUpSyncApi;
import com.dee.app.sync.api.sync.GetContactByContactIdApi;
import com.dee.app.sync.api.sync.GetMasterDeviceIdApi;
import com.dee.app.sync.core.ContactsSyncManager;
import com.dee.app.sync.core.ContactsSyncManager_Factory;
import com.dee.app.sync.core.ContactsSyncManager_MembersInjector;
import com.dee.app.sync.utils.DeviceContactsFetcher;
import com.dee.app.sync.utils.GenerateContactUpdateItems;
import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public final class DaggerContactsSyncComponent implements ContactsSyncComponent {
    private ContactsCommonModule contactsCommonModule;
    private ContactsCoreComponent contactsCoreComponent;

    private DaggerContactsSyncComponent(Builder builder) {
        initialize(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    private NetworkUtils getNetworkUtils() {
        return (NetworkUtils) Preconditions.checkNotNull(this.contactsCommonModule.getNetworkUtils(this.contactsCoreComponent.getIdentityService()), "Cannot return null from a non-@Nullable @Provides method");
    }

    private ContactsMetricsRecorder getContactsMetricsRecorder() {
        return (ContactsMetricsRecorder) Preconditions.checkNotNull(this.contactsCommonModule.getContactsMetricsRecorder((AlexaCommsCoreMetricsService) Preconditions.checkNotNull(this.contactsCoreComponent.getMetricsService(), "Cannot return null from a non-@Nullable component method")), "Cannot return null from a non-@Nullable @Provides method");
    }

    private UserAgent getUserAgent() {
        return (UserAgent) Preconditions.checkNotNull(this.contactsCommonModule.getUserAgent((String) Preconditions.checkNotNull(this.contactsCoreComponent.getApplicationName(), "Cannot return null from a non-@Nullable component method")), "Cannot return null from a non-@Nullable @Provides method");
    }

    private ContactsSyncGatewayService getContactsSyncGatewayService() {
        return new ContactsSyncGatewayService(getNetworkUtils(), (ObjectMapper) Preconditions.checkNotNull(this.contactsCoreComponent.getObjectMapper(), "Cannot return null from a non-@Nullable component method"), (INetworkingClient) Preconditions.checkNotNull(this.contactsCoreComponent.getNetworkClient(), "Cannot return null from a non-@Nullable component method"), getContactsMetricsRecorder(), getUserAgent());
    }

    private ContactsDownSyncApi getContactsDownSyncApi() {
        return new ContactsDownSyncApi((SharedPreferences) Preconditions.checkNotNull(this.contactsCoreComponent.getSharedPreferences(), "Cannot return null from a non-@Nullable component method"), getContactsSyncGatewayService());
    }

    private CanSyncContactsApi getCanSyncContactsApi() {
        return new CanSyncContactsApi((SharedPreferences) Preconditions.checkNotNull(this.contactsCoreComponent.getSharedPreferences(), "Cannot return null from a non-@Nullable component method"), (String) Preconditions.checkNotNull(this.contactsCoreComponent.getDeviceId(), "Cannot return null from a non-@Nullable component method"), getContactsSyncGatewayService());
    }

    private GetMasterDeviceIdApi getGetMasterDeviceIdApi() {
        return new GetMasterDeviceIdApi((SharedPreferences) Preconditions.checkNotNull(this.contactsCoreComponent.getSharedPreferences(), "Cannot return null from a non-@Nullable component method"), getContactsSyncGatewayService());
    }

    private GetContactByContactIdApi getGetContactByContactIdApi() {
        return new GetContactByContactIdApi((SharedPreferences) Preconditions.checkNotNull(this.contactsCoreComponent.getSharedPreferences(), "Cannot return null from a non-@Nullable component method"), getContactsSyncGatewayService());
    }

    private PermissionsUtils getPermissionsUtils() {
        return (PermissionsUtils) Preconditions.checkNotNull(this.contactsCommonModule.getPermissionsUtils((ActivityLifecycleUtil) Preconditions.checkNotNull(this.contactsCoreComponent.getActivityLifecycleUtil(), "Cannot return null from a non-@Nullable component method"), (Context) Preconditions.checkNotNull(this.contactsCoreComponent.getContext(), "Cannot return null from a non-@Nullable component method")), "Cannot return null from a non-@Nullable @Provides method");
    }

    private SyncUtils getSyncUtils() {
        return new SyncUtils((String) Preconditions.checkNotNull(this.contactsCoreComponent.getApplicationName(), "Cannot return null from a non-@Nullable component method"));
    }

    private DeviceContactsFetcher getDeviceContactsFetcher() {
        return new DeviceContactsFetcher((Context) Preconditions.checkNotNull(this.contactsCoreComponent.getContext(), "Cannot return null from a non-@Nullable component method"), (String) Preconditions.checkNotNull(this.contactsCoreComponent.getDeviceId(), "Cannot return null from a non-@Nullable component method"));
    }

    private GenerateContactUpdateItems getGenerateContactUpdateItems() {
        return new GenerateContactUpdateItems(new ContactConfig(), getContactsSyncGatewayService(), getDeviceContactsFetcher());
    }

    private ContactsUpSyncApi getContactsUpSyncApi() {
        return new ContactsUpSyncApi((SharedPreferences) Preconditions.checkNotNull(this.contactsCoreComponent.getSharedPreferences(), "Cannot return null from a non-@Nullable component method"), getPermissionsUtils(), getContactsSyncGatewayService(), getSyncUtils(), getGenerateContactUpdateItems(), (String) Preconditions.checkNotNull(this.contactsCoreComponent.getDeviceId(), "Cannot return null from a non-@Nullable component method"), getContactsMetricsRecorder());
    }

    private ContactsSyncApiHandler getContactsSyncApiHandler() {
        return new ContactsSyncApiHandler(getContactsDownSyncApi(), getCanSyncContactsApi(), getGetMasterDeviceIdApi(), getGetContactByContactIdApi(), getContactsUpSyncApi());
    }

    private void initialize(Builder builder) {
        this.contactsCoreComponent = builder.contactsCoreComponent;
        this.contactsCommonModule = builder.contactsCommonModule;
    }

    @Override // com.dee.app.sync.dagger.ContactsSyncComponent
    public ContactsSyncManager getContactsSyncManager() {
        return injectContactsSyncManager(ContactsSyncManager_Factory.newContactsSyncManager());
    }

    private ContactsSyncManager injectContactsSyncManager(ContactsSyncManager contactsSyncManager) {
        ContactsSyncManager_MembersInjector.injectContactsSyncApiHandler(contactsSyncManager, getContactsSyncApiHandler());
        return contactsSyncManager;
    }

    public static final class Builder {
        private ContactsCommonModule contactsCommonModule;
        private ContactsCoreComponent contactsCoreComponent;

        private Builder() {
        }

        public ContactsSyncComponent build() {
            if (this.contactsCommonModule == null) {
                this.contactsCommonModule = new ContactsCommonModule();
            }
            if (this.contactsCoreComponent == null) {
                throw new IllegalStateException(ContactsCoreComponent.class.getCanonicalName() + " must be set");
            }
            return new DaggerContactsSyncComponent(this);
        }

        public Builder contactsCoreComponent(ContactsCoreComponent contactsCoreComponent) {
            this.contactsCoreComponent = (ContactsCoreComponent) Preconditions.checkNotNull(contactsCoreComponent);
            return this;
        }
    }
}
