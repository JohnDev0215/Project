package com.dee.app.contacts.core;

import com.dee.app.contacts.api.CustomerIdentityApiHandler;
import com.dee.app.contacts.interfaces.models.apis.getaccounts.GetAccountsRequest;
import com.dee.app.contacts.interfaces.models.apis.getaccounts.GetAccountsResponse;
import com.dee.app.contacts.interfaces.models.apis.getpnvurl.GetPhoneNumberVerificationURLRequest;
import com.dee.app.contacts.interfaces.models.apis.getpnvurl.GetPhoneNumberVerificationURLResponse;
import rx.Observable;

/* JADX INFO: loaded from: classes5.dex */
public class CustomerIdentityManager {
    CustomerIdentityApiHandler mCustomerIdentityApiHandler;

    public Observable<GetPhoneNumberVerificationURLResponse> getPhoneNumberVerificationURL(GetPhoneNumberVerificationURLRequest getPhoneNumberVerificationURLRequest) {
        return this.mCustomerIdentityApiHandler.getPhoneNumberVerificationURL(getPhoneNumberVerificationURLRequest);
    }

    public Observable<GetAccountsResponse> getAccounts(GetAccountsRequest getAccountsRequest) {
        return this.mCustomerIdentityApiHandler.getAccounts(getAccountsRequest);
    }
}
