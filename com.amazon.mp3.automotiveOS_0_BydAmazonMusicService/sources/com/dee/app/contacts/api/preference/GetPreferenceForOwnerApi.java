package com.dee.app.contacts.api.preference;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import com.dee.app.contacts.api.AbstractContactApiAction;
import com.dee.app.contacts.common.exceptions.ClientNotSupportedException;
import com.dee.app.contacts.common.models.server.PreferenceForOwner;
import com.dee.app.contacts.common.models.server.PreferenceForOwnerResponse;
import com.dee.app.contacts.common.network.gateway.PreferenceServiceGateway;
import com.dee.app.contacts.common.utils.ContactsLogger;
import com.dee.app.contacts.common.utils.PreferenceUtils;
import com.dee.app.contacts.interfaces.models.apis.getpreferenceforowner.GetPreferenceForOwnerQueryConfig;
import com.dee.app.contacts.interfaces.models.apis.getpreferenceforowner.GetPreferenceForOwnerRequest;
import com.dee.app.contacts.interfaces.models.apis.getpreferenceforowner.GetPreferenceForOwnerResponse;
import com.dee.app.contacts.interfaces.models.data.enums.ContactsOwnerPreferenceTarget;
import com.dee.app.contacts.interfaces.models.data.enums.PreferenceNameSpace;
import com.dee.app.contacts.interfaces.models.data.preference.contactownerpreference.ContactOwnerPreference;
import com.dee.app.contacts.interfaces.models.data.preference.contactownerpreference.ContactOwnerPreferenceKey;
import com.dee.app.contacts.interfaces.models.data.preference.contactownerpreference.Preference;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes5.dex */
public class GetPreferenceForOwnerApi extends AbstractContactApiAction<GetPreferenceForOwnerRequest, GetPreferenceForOwnerResponse> {
    private static final ContactsLogger LOG = ContactsLogger.getLogger("TAG", GetPreferenceForOwnerApi.class);
    private final String mDeviceId;
    private final PreferenceServiceGateway mPreferenceServiceGateway;
    private final PreferenceUtils mPreferenceUtils;

    public GetPreferenceForOwnerApi(SharedPreferences sharedPreferences, String str, PreferenceUtils preferenceUtils, PreferenceServiceGateway preferenceServiceGateway) {
        super(sharedPreferences);
        this.mDeviceId = str;
        this.mPreferenceUtils = preferenceUtils;
        this.mPreferenceServiceGateway = preferenceServiceGateway;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.dee.app.contacts.api.AbstractContactApiAction
    public Observable<GetPreferenceForOwnerResponse> handleAction(GetPreferenceForOwnerRequest getPreferenceForOwnerRequest) {
        return handleGetPreferenceForOwnerAction(getPreferenceForOwnerRequest).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation());
    }

    private Observable<GetPreferenceForOwnerResponse> handleGetPreferenceForOwnerAction(GetPreferenceForOwnerRequest getPreferenceForOwnerRequest) {
        try {
            getPreferenceForOwnerRequest.setQueryConfig(getQueryConfig(getPreferenceForOwnerRequest.getPreferenceKey(), getPreferenceForOwnerRequest.getQueryConfig()));
            return this.mPreferenceServiceGateway.getPreferenceForOwner(getPreferenceForOwnerRequest).map(new Func1() { // from class: com.dee.app.contacts.api.preference.GetPreferenceForOwnerApi$$ExternalSyntheticLambda0
                @Override // rx.functions.Func1
                public final Object call(Object obj) {
                    return this.f$0.lambda$handleGetPreferenceForOwnerAction$0((PreferenceForOwnerResponse) obj);
                }
            }).doOnError(new Action1() { // from class: com.dee.app.contacts.api.preference.GetPreferenceForOwnerApi$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    GetPreferenceForOwnerApi.lambda$handleGetPreferenceForOwnerAction$1((Throwable) obj);
                }
            });
        } catch (ClientNotSupportedException e) {
            return Observable.error(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ GetPreferenceForOwnerResponse lambda$handleGetPreferenceForOwnerAction$0(PreferenceForOwnerResponse preferenceForOwnerResponse) {
        GetPreferenceForOwnerResponse getPreferenceForOwnerResponse = new GetPreferenceForOwnerResponse();
        getPreferenceForOwnerResponse.setPreferenceList(buildContactPreferenceListForOwner(preferenceForOwnerResponse));
        return getPreferenceForOwnerResponse;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleGetPreferenceForOwnerAction$1(Throwable th) {
        LOG.e("Failed to get preference for owner. ", th);
    }

    /* JADX INFO: renamed from: com.dee.app.contacts.api.preference.GetPreferenceForOwnerApi$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$dee$app$contacts$interfaces$models$data$preference$contactownerpreference$ContactOwnerPreferenceKey;

        static {
            int[] iArr = new int[ContactOwnerPreferenceKey.values().length];
            $SwitchMap$com$dee$app$contacts$interfaces$models$data$preference$contactownerpreference$ContactOwnerPreferenceKey = iArr;
            try {
                iArr[ContactOwnerPreferenceKey.AMAZON_CONTACTS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$dee$app$contacts$interfaces$models$data$preference$contactownerpreference$ContactOwnerPreferenceKey[ContactOwnerPreferenceKey.AUTO_DISCOVERY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$dee$app$contacts$interfaces$models$data$preference$contactownerpreference$ContactOwnerPreferenceKey[ContactOwnerPreferenceKey.CONTACT_IMPORT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$dee$app$contacts$interfaces$models$data$preference$contactownerpreference$ContactOwnerPreferenceKey[ContactOwnerPreferenceKey.PHONE_NUMBER_VERIFIED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private GetPreferenceForOwnerQueryConfig getQueryConfig(ContactOwnerPreferenceKey contactOwnerPreferenceKey, GetPreferenceForOwnerQueryConfig getPreferenceForOwnerQueryConfig) throws ClientNotSupportedException {
        PreferenceNameSpace preferenceNameSpaceForClient;
        String value;
        int i = AnonymousClass1.$SwitchMap$com$dee$app$contacts$interfaces$models$data$preference$contactownerpreference$ContactOwnerPreferenceKey[contactOwnerPreferenceKey.ordinal()];
        if (i == 1 || i == 2) {
            preferenceNameSpaceForClient = PreferenceNameSpace.AMAZON;
            value = ContactsOwnerPreferenceTarget.ALL.getValue();
        } else if (i == 3) {
            preferenceNameSpaceForClient = this.mPreferenceUtils.getPreferenceNameSpaceForClient();
            value = this.mDeviceId;
        } else if (i == 4) {
            preferenceNameSpaceForClient = PreferenceNameSpace.AMAZON;
            value = this.mPreferenceUtils.getContactsOwnerPreferenceTargetForClient().getValue();
        } else {
            throw new IllegalStateException("Unexpected ContactOwnerPreferenceKey value: " + contactOwnerPreferenceKey);
        }
        if (getPreferenceForOwnerQueryConfig == null) {
            getPreferenceForOwnerQueryConfig = new GetPreferenceForOwnerQueryConfig();
        }
        getPreferenceForOwnerQueryConfig.setPreferenceNameSpace(preferenceNameSpaceForClient);
        getPreferenceForOwnerQueryConfig.setTarget(value);
        return getPreferenceForOwnerQueryConfig;
    }

    @TargetApi(24)
    private List<Preference> buildContactPreferenceListForOwner(PreferenceForOwnerResponse preferenceForOwnerResponse) {
        return (List) preferenceForOwnerResponse.getPreferences().stream().map(new Function() { // from class: com.dee.app.contacts.api.preference.GetPreferenceForOwnerApi$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return GetPreferenceForOwnerApi.lambda$buildContactPreferenceListForOwner$2((PreferenceForOwner) obj);
            }
        }).collect(Collectors.toList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Preference lambda$buildContactPreferenceListForOwner$2(PreferenceForOwner preferenceForOwner) {
        ContactOwnerPreference contactPreferenceForKeyAndValue = ContactOwnerPreference.getContactPreferenceForKeyAndValue(preferenceForOwner.getKey(), preferenceForOwner.getValue());
        PreferenceNameSpace preferenceNameSpaceKeyForValue = PreferenceNameSpace.getPreferenceNameSpaceKeyForValue(preferenceForOwner.getNamespace());
        Preference preference = new Preference();
        preference.setPreferenceNameSpace(preferenceNameSpaceKeyForValue);
        preference.setPreference(contactPreferenceForKeyAndValue);
        preference.setTarget(preferenceForOwner.getTarget());
        return preference;
    }
}
