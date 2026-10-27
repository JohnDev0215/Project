package com.amazon.mp3.find.dagger;

import android.content.Context;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.net.RequestInterceptorFactory;
import com.amazon.mp3.prime.browse.BrowseLanguageProvider;
import com.amazon.mp3.search.SearchURLProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.authentication.AuthDataType;
import com.amazon.music.search.suggestion.instantsearch.InstantSearchSuggestionServiceConfiguration;
import java.net.URL;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InstantSearchSuggestionServiceConfigurationProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u0004\u0018\u00010\bJ\n\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/find/dagger/InstantSearchSuggestionServiceConfigurationProvider;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "accountCredentialStorage", "Lcom/amazon/mp3/account/credentials/AccountCredentialStorage;", "instantSearchConfiguration", "Lcom/amazon/music/search/suggestion/instantsearch/InstantSearchSuggestionServiceConfiguration;", "requestInterceptor", "Lcom/amazon/hermes/RequestInterceptor;", "requestInterceptorFactory", "Lcom/amazon/mp3/net/RequestInterceptorFactory;", "searchUrl", "Ljava/net/URL;", "getConfiguration", "getSessionId", "", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class InstantSearchSuggestionServiceConfigurationProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = InstantSearchSuggestionServiceConfigurationProvider.class.getSimpleName();
    private final AccountCredentialStorage accountCredentialStorage;
    private final Context context;
    private final InstantSearchSuggestionServiceConfiguration instantSearchConfiguration;
    private final RequestInterceptor requestInterceptor;
    private final RequestInterceptorFactory requestInterceptorFactory;
    private final URL searchUrl;

    public InstantSearchSuggestionServiceConfigurationProvider(Context context) {
        InstantSearchSuggestionServiceConfigurationDefault instantSearchSuggestionServiceConfigurationDefault;
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
        Intrinsics.checkNotNullExpressionValue(accountCredentialStorage, "get(context)");
        this.accountCredentialStorage = accountCredentialStorage;
        URL searchURL = INSTANCE.getSearchURL();
        this.searchUrl = searchURL;
        RequestInterceptorFactory requestInterceptorFactory = new RequestInterceptorFactory(context);
        this.requestInterceptorFactory = requestInterceptorFactory;
        RequestInterceptor requestInterceptorProvideRequestInterceptor = requestInterceptorFactory.provideRequestInterceptor();
        Intrinsics.checkNotNullExpressionValue(requestInterceptorProvideRequestInterceptor, "requestInterceptorFactor…ovideRequestInterceptor()");
        this.requestInterceptor = requestInterceptorProvideRequestInterceptor;
        if (searchURL == null) {
            instantSearchSuggestionServiceConfigurationDefault = null;
        } else {
            String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
            Intrinsics.checkNotNullExpressionValue(musicTerritoryOfResidence, "getMusicTerritoryOfResidence()");
            String deviceId = accountCredentialStorage.getDeviceId();
            Intrinsics.checkNotNullExpressionValue(deviceId, "accountCredentialStorage.deviceId");
            String deviceType = accountCredentialStorage.getDeviceType();
            Intrinsics.checkNotNullExpressionValue(deviceType, "accountCredentialStorage.deviceType");
            String customerId = AccountDetailUtil.get(context).getCustomerId();
            String string = new BrowseLanguageProvider().getLocale(context).toString();
            Intrinsics.checkNotNullExpressionValue(string, "BrowseLanguageProvider()…ocale(context).toString()");
            instantSearchSuggestionServiceConfigurationDefault = new InstantSearchSuggestionServiceConfigurationDefault(searchURL, requestInterceptorProvideRequestInterceptor, musicTerritoryOfResidence, deviceId, deviceType, customerId, string, getSessionId());
        }
        this.instantSearchConfiguration = instantSearchSuggestionServiceConfigurationDefault;
    }

    /* JADX INFO: compiled from: InstantSearchSuggestionServiceConfigurationProvider.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/find/dagger/InstantSearchSuggestionServiceConfigurationProvider$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getSearchURL", "Ljava/net/URL;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final URL getSearchURL() {
            try {
                return SearchURLProvider.getInstantSearchURL();
            } catch (Exception e) {
                Log.error(InstantSearchSuggestionServiceConfigurationProvider.TAG, Intrinsics.stringPlus("Could not get Search suggestion URL: ", e.getMessage()));
                return null;
            }
        }
    }

    /* JADX INFO: renamed from: getConfiguration, reason: from getter */
    public final InstantSearchSuggestionServiceConfiguration getInstantSearchConfiguration() {
        return this.instantSearchConfiguration;
    }

    private final String getSessionId() {
        try {
            return AmazonApplication.getAuthInfoMAPR5().find(AuthDataType.SESSION_ID);
        } catch (Exception e) {
            Log.warning(TAG, "getSessionId failed", e);
            return null;
        }
    }
}
