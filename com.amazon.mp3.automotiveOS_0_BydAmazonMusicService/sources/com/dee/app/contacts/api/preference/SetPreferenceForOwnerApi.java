package com.dee.app.contacts.api.preference;

import android.content.SharedPreferences;
import com.dee.app.contacts.api.AbstractContactApiAction;
import com.dee.app.contacts.common.exceptions.ClientNotSupportedException;
import com.dee.app.contacts.common.network.gateway.PreferenceServiceGateway;
import com.dee.app.contacts.common.utils.ContactsLogger;
import com.dee.app.contacts.common.utils.PreferenceUtils;
import com.dee.app.contacts.interfaces.models.apis.setpreferenceforowner.SetPreferenceForOwnerRequest;
import com.dee.app.contacts.interfaces.models.apis.setpreferenceforowner.SetPreferenceForOwnerResponse;
import com.dee.app.contacts.interfaces.models.data.enums.ContactsOwnerPreferenceTarget;
import com.dee.app.contacts.interfaces.models.data.enums.PreferenceNameSpace;
import com.dee.app.contacts.interfaces.models.data.preference.contactownerpreference.ContactOwnerPreferenceKey;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes5.dex */
public class SetPreferenceForOwnerApi extends AbstractContactApiAction<SetPreferenceForOwnerRequest, SetPreferenceForOwnerResponse> {
    private static final ContactsLogger LOG = ContactsLogger.getLogger("TAG", SetPreferenceForOwnerApi.class);
    private final String mDeviceId;
    private final PreferenceServiceGateway mPreferenceServiceGateway;
    private final PreferenceUtils mPreferenceUtils;

    public SetPreferenceForOwnerApi(SharedPreferences sharedPreferences, String str, PreferenceUtils preferenceUtils, PreferenceServiceGateway preferenceServiceGateway) {
        super(sharedPreferences);
        this.mDeviceId = str;
        this.mPreferenceUtils = preferenceUtils;
        this.mPreferenceServiceGateway = preferenceServiceGateway;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.dee.app.contacts.api.AbstractContactApiAction
    public Observable<SetPreferenceForOwnerResponse> handleAction(SetPreferenceForOwnerRequest setPreferenceForOwnerRequest) {
        return handleSetPreferenceForOwnerAction(setPreferenceForOwnerRequest).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation());
    }

    private Observable<SetPreferenceForOwnerResponse> handleSetPreferenceForOwnerAction(SetPreferenceForOwnerRequest setPreferenceForOwnerRequest) {
        try {
            return this.mPreferenceServiceGateway.setPreferenceForOwner(getUpdatedRequest(setPreferenceForOwnerRequest)).map(new Func1() { // from class: com.dee.app.contacts.api.preference.SetPreferenceForOwnerApi$$ExternalSyntheticLambda0
                @Override // rx.functions.Func1
                public final Object call(Object obj) {
                    return SetPreferenceForOwnerApi.lambda$handleSetPreferenceForOwnerAction$0((String) obj);
                }
            }).doOnError(new Action1() { // from class: com.dee.app.contacts.api.preference.SetPreferenceForOwnerApi$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    SetPreferenceForOwnerApi.lambda$handleSetPreferenceForOwnerAction$1((Throwable) obj);
                }
            });
        } catch (ClientNotSupportedException e) {
            return Observable.error(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ SetPreferenceForOwnerResponse lambda$handleSetPreferenceForOwnerAction$0(String str) {
        SetPreferenceForOwnerResponse setPreferenceForOwnerResponse = new SetPreferenceForOwnerResponse();
        setPreferenceForOwnerResponse.setSuccess(true);
        return setPreferenceForOwnerResponse;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleSetPreferenceForOwnerAction$1(Throwable th) {
        LOG.e("Failed to set preference for owner. ", th);
    }

    private SetPreferenceForOwnerRequest getUpdatedRequest(SetPreferenceForOwnerRequest setPreferenceForOwnerRequest) throws ClientNotSupportedException {
        PreferenceNameSpace preferenceNameSpaceForClient;
        String value;
        ContactOwnerPreferenceKey preferenceKey = setPreferenceForOwnerRequest.getPreference().getPreferenceKey();
        int i = AnonymousClass1.$SwitchMap$com$dee$app$contacts$interfaces$models$data$preference$contactownerpreference$ContactOwnerPreferenceKey[preferenceKey.ordinal()];
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
            throw new IllegalStateException("Unexpected ContactOwnerPreferenceKey value: " + preferenceKey);
        }
        setPreferenceForOwnerRequest.setTarget(value);
        setPreferenceForOwnerRequest.setPreferenceNameSpace(preferenceNameSpaceForClient);
        return setPreferenceForOwnerRequest;
    }

    /* JADX INFO: renamed from: com.dee.app.contacts.api.preference.SetPreferenceForOwnerApi$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$dee$app$contacts$interfaces$models$data$preference$contactownerpreference$ContactOwnerPreferenceKey;

        static {
            int[] iArr = new int[ContactOwnerPreferenceKey.values().length];
            $SwitchMap$com$dee$app$contacts$interfaces$models$data$preference$contactownerpreference$ContactOwnerPreferenceKey = iArr;
            try {
                iArr[ContactOwnerPreferenceKey.AUTO_DISCOVERY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$dee$app$contacts$interfaces$models$data$preference$contactownerpreference$ContactOwnerPreferenceKey[ContactOwnerPreferenceKey.AMAZON_CONTACTS.ordinal()] = 2;
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
}
