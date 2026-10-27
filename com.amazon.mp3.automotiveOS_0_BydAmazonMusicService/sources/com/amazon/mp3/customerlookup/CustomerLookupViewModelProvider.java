package com.amazon.mp3.customerlookup;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.store.html5.map.MAPCookiesManager;
import com.amazon.mp3.styles.DefaultStyleSheet;
import com.amazon.music.connect.IdentityProvider;
import com.amazon.music.connect.customerlookup.viewmodels.CustomerLookupViewModel;
import com.amazon.music.connect.customerlookup.viewmodels.CustomerLookupViewModelFactory;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: CustomerLookupViewModelProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u0004\u0018\u00010\rR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/customerlookup/CustomerLookupViewModelProvider;", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "TAG", "", "appContext", "Landroid/content/Context;", "kotlin.jvm.PlatformType", "styleSheet", "Lcom/amazon/mp3/styles/DefaultStyleSheet;", "get", "Lcom/amazon/music/connect/customerlookup/viewmodels/CustomerLookupViewModel;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomerLookupViewModelProvider {
    private final String TAG;
    private final FragmentActivity activity;
    private final Context appContext;
    private final DefaultStyleSheet styleSheet;

    public CustomerLookupViewModelProvider(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
        this.TAG = Reflection.getOrCreateKotlinClass(CustomerLookupViewModelProvider.class).getSimpleName();
        Context applicationContext = activity.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "activity.applicationContext");
        this.styleSheet = new DefaultStyleSheet(applicationContext);
        this.appContext = AmazonApplication.getApplication().getApplicationContext();
    }

    public final CustomerLookupViewModel get() {
        final AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(this.appContext);
        final AccountDetailUtil accountDetailUtil = AccountDetailUtil.get(this.appContext);
        Pair[] pairArr = new Pair[2];
        EndpointsProvider endpointsProvider = EndpointsProvider.get();
        pairArr[0] = TuplesKt.to("resultsEndpoint", endpointsProvider == null ? null : endpointsProvider.getCustomerLookupResultsApiEndpoint());
        EndpointsProvider endpointsProvider2 = EndpointsProvider.get();
        pairArr[1] = TuplesKt.to("suggestionsEndpoint", endpointsProvider2 != null ? endpointsProvider2.getCustomerLookupSuggestionsApiEndpoint() : null);
        return (CustomerLookupViewModel) ViewModelProviders.of(this.activity, new CustomerLookupViewModelFactory(new IdentityProvider() { // from class: com.amazon.mp3.customerlookup.CustomerLookupViewModelProvider$get$identityProvider$1
            @Override // com.amazon.music.connect.IdentityProvider
            public String getToken() {
                try {
                    return MAPCookiesManager.getCookiesManager().getToken();
                } catch (Exception unused) {
                    return null;
                }
            }

            @Override // com.amazon.music.connect.IdentityProvider
            public String getCustomerId() {
                AccountDetailUtil accountDetailUtil2 = accountDetailUtil;
                if (accountDetailUtil2 == null) {
                    return null;
                }
                return accountDetailUtil2.getCustomerId();
            }

            @Override // com.amazon.music.connect.IdentityProvider
            public String getDeviceId() {
                AccountCredentialStorage accountCredentialStorage2 = accountCredentialStorage;
                if (accountCredentialStorage2 == null) {
                    return null;
                }
                return accountCredentialStorage2.getDeviceId();
            }

            @Override // com.amazon.music.connect.IdentityProvider
            public String getDeviceType() {
                AccountCredentialStorage accountCredentialStorage2 = accountCredentialStorage;
                if (accountCredentialStorage2 == null) {
                    return null;
                }
                return accountCredentialStorage2.getDeviceType();
            }
        }, this.styleSheet, MapsKt__MapsKt.mapOf(pairArr))).get(CustomerLookupViewModel.class);
    }
}
