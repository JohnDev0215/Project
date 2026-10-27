package com.dee.app.contacts.api.customeridentity;

import android.content.SharedPreferences;
import com.dee.app.contacts.api.AbstractContactApiAction;
import com.dee.app.contacts.common.network.gateway.CustomerIdentityServiceGateway;
import com.dee.app.contacts.common.utils.ContactsLogger;
import com.dee.app.contacts.interfaces.models.apis.getaccounts.GetAccountsRequest;
import com.dee.app.contacts.interfaces.models.apis.getaccounts.GetAccountsResponse;
import com.dee.app.contacts.interfaces.models.data.Account;
import com.dee.app.contacts.interfaces.models.data.server.ServerAccount;
import com.dee.app.contacts.interfaces.models.data.server.ServerPhoneNumberMetadata;
import java.util.ArrayList;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes5.dex */
public class GetAccountsApi extends AbstractContactApiAction<GetAccountsRequest, GetAccountsResponse> {
    private static final ContactsLogger LOG = ContactsLogger.getLogger("TAG", GetAccountsApi.class);
    private final CustomerIdentityServiceGateway mCustomerIdentityServiceGateway;

    public GetAccountsApi(SharedPreferences sharedPreferences, CustomerIdentityServiceGateway customerIdentityServiceGateway) {
        super(sharedPreferences);
        this.mCustomerIdentityServiceGateway = customerIdentityServiceGateway;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.dee.app.contacts.api.AbstractContactApiAction
    public Observable<GetAccountsResponse> handleAction(GetAccountsRequest getAccountsRequest) {
        return handleGetAccountsAction(getAccountsRequest).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation());
    }

    private Observable<GetAccountsResponse> handleGetAccountsAction(GetAccountsRequest getAccountsRequest) {
        return this.mCustomerIdentityServiceGateway.getAccounts(getAccountsRequest).map(new Func1() { // from class: com.dee.app.contacts.api.customeridentity.GetAccountsApi$$ExternalSyntheticLambda0
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return GetAccountsApi.lambda$handleGetAccountsAction$0((ServerAccount[]) obj);
            }
        }).doOnError(new Action1() { // from class: com.dee.app.contacts.api.customeridentity.GetAccountsApi$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                GetAccountsApi.lambda$handleGetAccountsAction$1((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ GetAccountsResponse lambda$handleGetAccountsAction$0(ServerAccount[] serverAccountArr) {
        ArrayList arrayList = new ArrayList();
        for (ServerAccount serverAccount : serverAccountArr) {
            ServerPhoneNumberMetadata phoneNumberMetadataForTheActor = serverAccount.getPhoneNumberMetadataForTheActor();
            String phoneNumber = serverAccount.getPhoneNumber();
            String phoneCountryCode = serverAccount.getPhoneCountryCode();
            Boolean signedInUser = serverAccount.getSignedInUser();
            String mapDirectedDefaultActorId = serverAccount.getMapDirectedDefaultActorId();
            String countryCode = null;
            String phoneNumber2 = phoneNumberMetadataForTheActor != null ? phoneNumberMetadataForTheActor.getPhoneNumber() : null;
            if (phoneNumberMetadataForTheActor != null) {
                countryCode = phoneNumberMetadataForTheActor.getCountryCode();
            }
            arrayList.add(new Account(phoneNumber, phoneCountryCode, signedInUser, mapDirectedDefaultActorId, phoneNumber2, countryCode));
        }
        LOG.i("Fetched accounts size = " + arrayList.size());
        return new GetAccountsResponse(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleGetAccountsAction$1(Throwable th) {
        LOG.i("GetAccounts api failed. ", th);
    }
}
