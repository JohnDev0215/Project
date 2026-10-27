package com.dee.app.contacts.dagger;

import android.content.SharedPreferences;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commsnetworking.api.INetworkingClient;
import com.dee.app.contacts.api.CustomerIdentityApiHandler;
import com.dee.app.contacts.api.PreferenceApiHandler;
import com.dee.app.contacts.api.customeridentity.GetAccountsApi;
import com.dee.app.contacts.api.customeridentity.GetPhoneNumberVerificationURLApi;
import com.dee.app.contacts.api.preference.GetContactIdentityPreferenceApi;
import com.dee.app.contacts.api.preference.GetContactPreferenceApi;
import com.dee.app.contacts.api.preference.GetPreferenceForOwnerApi;
import com.dee.app.contacts.api.preference.SetContactIdentityPreferenceApi;
import com.dee.app.contacts.api.preference.SetContactPreferenceApi;
import com.dee.app.contacts.api.preference.SetPreferenceForOwnerApi;
import com.dee.app.contacts.common.dagger.ContactsCoreComponent;
import com.dee.app.contacts.common.dagger.modules.ContactsCommonModule;
import com.dee.app.contacts.common.network.gateway.CustomerIdentityServiceGateway;
import com.dee.app.contacts.common.network.gateway.PreferenceServiceGateway;
import com.dee.app.contacts.common.utils.ContactsMetricsRecorder;
import com.dee.app.contacts.common.utils.NetworkUtils;
import com.dee.app.contacts.common.utils.PreferenceUtils;
import com.dee.app.contacts.common.utils.UserAgent;
import com.dee.app.contacts.core.ContactsPreferenceManager;
import com.dee.app.contacts.core.ContactsPreferenceManager_Factory;
import com.dee.app.contacts.core.ContactsPreferenceManager_MembersInjector;
import com.dee.app.contacts.core.CustomerIdentityManager;
import com.dee.app.contacts.core.CustomerIdentityManager_Factory;
import com.dee.app.contacts.core.CustomerIdentityManager_MembersInjector;
import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public final class DaggerContactsApiComponent implements ContactsApiComponent {
    private ContactsCommonModule contactsCommonModule;
    private ContactsCoreComponent contactsCoreComponent;

    private DaggerContactsApiComponent(Builder builder) {
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

    private PreferenceServiceGateway getPreferenceServiceGateway() {
        return new PreferenceServiceGateway((ObjectMapper) Preconditions.checkNotNull(this.contactsCoreComponent.getObjectMapper(), "Cannot return null from a non-@Nullable component method"), getNetworkUtils(), (INetworkingClient) Preconditions.checkNotNull(this.contactsCoreComponent.getNetworkClient(), "Cannot return null from a non-@Nullable component method"), getContactsMetricsRecorder(), getUserAgent());
    }

    private GetContactPreferenceApi getGetContactPreferenceApi() {
        return new GetContactPreferenceApi((SharedPreferences) Preconditions.checkNotNull(this.contactsCoreComponent.getSharedPreferences(), "Cannot return null from a non-@Nullable component method"), getPreferenceServiceGateway());
    }

    private SetContactPreferenceApi getSetContactPreferenceApi() {
        return new SetContactPreferenceApi((SharedPreferences) Preconditions.checkNotNull(this.contactsCoreComponent.getSharedPreferences(), "Cannot return null from a non-@Nullable component method"), getPreferenceServiceGateway());
    }

    private GetContactIdentityPreferenceApi getGetContactIdentityPreferenceApi() {
        return new GetContactIdentityPreferenceApi((SharedPreferences) Preconditions.checkNotNull(this.contactsCoreComponent.getSharedPreferences(), "Cannot return null from a non-@Nullable component method"), getPreferenceServiceGateway());
    }

    private SetContactIdentityPreferenceApi getSetContactIdentityPreferenceApi() {
        return new SetContactIdentityPreferenceApi((SharedPreferences) Preconditions.checkNotNull(this.contactsCoreComponent.getSharedPreferences(), "Cannot return null from a non-@Nullable component method"), getPreferenceServiceGateway());
    }

    private PreferenceUtils getPreferenceUtils() {
        return new PreferenceUtils((String) Preconditions.checkNotNull(this.contactsCoreComponent.getApplicationName(), "Cannot return null from a non-@Nullable component method"));
    }

    private GetPreferenceForOwnerApi getGetPreferenceForOwnerApi() {
        return new GetPreferenceForOwnerApi((SharedPreferences) Preconditions.checkNotNull(this.contactsCoreComponent.getSharedPreferences(), "Cannot return null from a non-@Nullable component method"), (String) Preconditions.checkNotNull(this.contactsCoreComponent.getDeviceId(), "Cannot return null from a non-@Nullable component method"), getPreferenceUtils(), getPreferenceServiceGateway());
    }

    private SetPreferenceForOwnerApi getSetPreferenceForOwnerApi() {
        return new SetPreferenceForOwnerApi((SharedPreferences) Preconditions.checkNotNull(this.contactsCoreComponent.getSharedPreferences(), "Cannot return null from a non-@Nullable component method"), (String) Preconditions.checkNotNull(this.contactsCoreComponent.getDeviceId(), "Cannot return null from a non-@Nullable component method"), getPreferenceUtils(), getPreferenceServiceGateway());
    }

    private PreferenceApiHandler getPreferenceApiHandler() {
        return new PreferenceApiHandler(getGetContactPreferenceApi(), getSetContactPreferenceApi(), getGetContactIdentityPreferenceApi(), getSetContactIdentityPreferenceApi(), getGetPreferenceForOwnerApi(), getSetPreferenceForOwnerApi());
    }

    private CustomerIdentityServiceGateway getCustomerIdentityServiceGateway() {
        return new CustomerIdentityServiceGateway((ObjectMapper) Preconditions.checkNotNull(this.contactsCoreComponent.getObjectMapper(), "Cannot return null from a non-@Nullable component method"), (String) Preconditions.checkNotNull(this.contactsCoreComponent.getApplicationName(), "Cannot return null from a non-@Nullable component method"), getNetworkUtils(), (INetworkingClient) Preconditions.checkNotNull(this.contactsCoreComponent.getNetworkClient(), "Cannot return null from a non-@Nullable component method"), getContactsMetricsRecorder(), getUserAgent());
    }

    private GetPhoneNumberVerificationURLApi getGetPhoneNumberVerificationURLApi() {
        return new GetPhoneNumberVerificationURLApi((SharedPreferences) Preconditions.checkNotNull(this.contactsCoreComponent.getSharedPreferences(), "Cannot return null from a non-@Nullable component method"), getCustomerIdentityServiceGateway());
    }

    private GetAccountsApi getGetAccountsApi() {
        return new GetAccountsApi((SharedPreferences) Preconditions.checkNotNull(this.contactsCoreComponent.getSharedPreferences(), "Cannot return null from a non-@Nullable component method"), getCustomerIdentityServiceGateway());
    }

    private CustomerIdentityApiHandler getCustomerIdentityApiHandler() {
        return new CustomerIdentityApiHandler(getGetPhoneNumberVerificationURLApi(), getGetAccountsApi());
    }

    private void initialize(Builder builder) {
        this.contactsCoreComponent = builder.contactsCoreComponent;
        this.contactsCommonModule = builder.contactsCommonModule;
    }

    @Override // com.dee.app.contacts.dagger.ContactsApiComponent
    public ContactsPreferenceManager getPreferenceManager() {
        return injectContactsPreferenceManager(ContactsPreferenceManager_Factory.newContactsPreferenceManager());
    }

    @Override // com.dee.app.contacts.dagger.ContactsApiComponent
    public CustomerIdentityManager getCustomerIdentityManager() {
        return injectCustomerIdentityManager(CustomerIdentityManager_Factory.newCustomerIdentityManager());
    }

    private ContactsPreferenceManager injectContactsPreferenceManager(ContactsPreferenceManager contactsPreferenceManager) {
        ContactsPreferenceManager_MembersInjector.injectMPreferenceApiHandler(contactsPreferenceManager, getPreferenceApiHandler());
        return contactsPreferenceManager;
    }

    private CustomerIdentityManager injectCustomerIdentityManager(CustomerIdentityManager customerIdentityManager) {
        CustomerIdentityManager_MembersInjector.injectMCustomerIdentityApiHandler(customerIdentityManager, getCustomerIdentityApiHandler());
        return customerIdentityManager;
    }

    public static final class Builder {
        private ContactsCommonModule contactsCommonModule;
        private ContactsCoreComponent contactsCoreComponent;

        private Builder() {
        }

        public ContactsApiComponent build() {
            if (this.contactsCommonModule == null) {
                this.contactsCommonModule = new ContactsCommonModule();
            }
            if (this.contactsCoreComponent == null) {
                throw new IllegalStateException(ContactsCoreComponent.class.getCanonicalName() + " must be set");
            }
            return new DaggerContactsApiComponent(this);
        }

        public Builder contactsCoreComponent(ContactsCoreComponent contactsCoreComponent) {
            this.contactsCoreComponent = (ContactsCoreComponent) Preconditions.checkNotNull(contactsCoreComponent);
            return this;
        }
    }
}
