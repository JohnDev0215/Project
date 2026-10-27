package com.dee.app.contacts.core;

import com.dee.app.contacts.api.CustomerIdentityApiHandler;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes5.dex */
public final class CustomerIdentityManager_MembersInjector implements MembersInjector<CustomerIdentityManager> {
    public static void injectMCustomerIdentityApiHandler(CustomerIdentityManager customerIdentityManager, CustomerIdentityApiHandler customerIdentityApiHandler) {
        customerIdentityManager.mCustomerIdentityApiHandler = customerIdentityApiHandler;
    }
}
