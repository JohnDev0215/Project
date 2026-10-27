package com.dee.app.contacts.api;

import com.dee.app.contacts.api.customeridentity.GetAccountsApi;
import com.dee.app.contacts.api.customeridentity.GetPhoneNumberVerificationURLApi;
import com.dee.app.contacts.interfaces.models.apis.getaccounts.GetAccountsRequest;
import com.dee.app.contacts.interfaces.models.apis.getaccounts.GetAccountsResponse;
import com.dee.app.contacts.interfaces.models.apis.getpnvurl.GetPhoneNumberVerificationURLRequest;
import com.dee.app.contacts.interfaces.models.apis.getpnvurl.GetPhoneNumberVerificationURLResponse;
import rx.Observable;

/* JADX INFO: loaded from: classes5.dex */
public class CustomerIdentityApiHandler {
    private final AbstractContactApiAction<GetAccountsRequest, GetAccountsResponse> mGetAccountsApi;
    private final AbstractContactApiAction<GetPhoneNumberVerificationURLRequest, GetPhoneNumberVerificationURLResponse> mGetPhoneNumberVerificationURLApi;

    public CustomerIdentityApiHandler(GetPhoneNumberVerificationURLApi getPhoneNumberVerificationURLApi, GetAccountsApi getAccountsApi) {
        this.mGetPhoneNumberVerificationURLApi = getPhoneNumberVerificationURLApi;
        this.mGetAccountsApi = getAccountsApi;
    }

    public Observable<GetPhoneNumberVerificationURLResponse> getPhoneNumberVerificationURL(GetPhoneNumberVerificationURLRequest getPhoneNumberVerificationURLRequest) {
        return this.mGetPhoneNumberVerificationURLApi.performAction(getPhoneNumberVerificationURLRequest);
    }

    public Observable<GetAccountsResponse> getAccounts(GetAccountsRequest getAccountsRequest) {
        return this.mGetAccountsApi.performAction(getAccountsRequest);
    }
}
