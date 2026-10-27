package com.dee.app.contacts.api.customeridentity;

import android.content.SharedPreferences;
import com.dee.app.contacts.api.AbstractContactApiAction;
import com.dee.app.contacts.common.network.gateway.CustomerIdentityServiceGateway;
import com.dee.app.contacts.common.utils.ContactsLogger;
import com.dee.app.contacts.interfaces.models.apis.getpnvurl.GetPhoneNumberVerificationURLRequest;
import com.dee.app.contacts.interfaces.models.apis.getpnvurl.GetPhoneNumberVerificationURLResponse;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes5.dex */
public class GetPhoneNumberVerificationURLApi extends AbstractContactApiAction<GetPhoneNumberVerificationURLRequest, GetPhoneNumberVerificationURLResponse> {
    private static final ContactsLogger LOG = ContactsLogger.getLogger("TAG", GetPhoneNumberVerificationURLApi.class);
    private final CustomerIdentityServiceGateway mCustomerIdentityServiceGateway;

    public GetPhoneNumberVerificationURLApi(SharedPreferences sharedPreferences, CustomerIdentityServiceGateway customerIdentityServiceGateway) {
        super(sharedPreferences);
        this.mCustomerIdentityServiceGateway = customerIdentityServiceGateway;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.dee.app.contacts.api.AbstractContactApiAction
    public Observable<GetPhoneNumberVerificationURLResponse> handleAction(GetPhoneNumberVerificationURLRequest getPhoneNumberVerificationURLRequest) {
        return handleGetPhoneNumberVerificationURLAction(getPhoneNumberVerificationURLRequest).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation());
    }

    private Observable<GetPhoneNumberVerificationURLResponse> handleGetPhoneNumberVerificationURLAction(GetPhoneNumberVerificationURLRequest getPhoneNumberVerificationURLRequest) {
        return this.mCustomerIdentityServiceGateway.getPhoneNumberVerificationURL(getPhoneNumberVerificationURLRequest).doOnError(new Action1() { // from class: com.dee.app.contacts.api.customeridentity.GetPhoneNumberVerificationURLApi$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                GetPhoneNumberVerificationURLApi.lambda$handleGetPhoneNumberVerificationURLAction$0((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleGetPhoneNumberVerificationURLAction$0(Throwable th) {
        LOG.i("GetPhoneNumberVerificationURL api failed. ", th);
    }
}
