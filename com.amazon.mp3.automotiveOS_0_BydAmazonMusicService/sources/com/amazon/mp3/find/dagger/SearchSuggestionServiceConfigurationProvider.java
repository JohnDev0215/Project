package com.amazon.mp3.find.dagger;

import android.content.Context;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.net.RequestInterceptorFactory;
import com.amazon.mp3.search.SearchURLProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.search.suggestion.SearchSuggestionServiceConfiguration;
import java.net.URL;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchSuggestionServiceConfigurationProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u0016\u0010\t\u001a\u0004\u0018\u00010\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\rR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/find/dagger/SearchSuggestionServiceConfigurationProvider;", "", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/mp3/account/credentials/AccountCredentialStorage;", "accountCredentialStorage", "Lcom/amazon/mp3/account/credentials/AccountCredentialStorage;", "Ljava/net/URL;", "searchUrl", "Ljava/net/URL;", "Lcom/amazon/mp3/net/RequestInterceptorFactory;", "requestInterceptorFactory", "Lcom/amazon/mp3/net/RequestInterceptorFactory;", "Lcom/amazon/hermes/RequestInterceptor;", "requestInterceptor", "Lcom/amazon/hermes/RequestInterceptor;", "Lcom/amazon/music/search/suggestion/SearchSuggestionServiceConfiguration;", "searchSuggestionConfiguration", "Lcom/amazon/music/search/suggestion/SearchSuggestionServiceConfiguration;", "<init>", "(Landroid/content/Context;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class SearchSuggestionServiceConfigurationProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = SearchSuggestionServiceConfigurationProvider.class.getSimpleName();
    private final AccountCredentialStorage accountCredentialStorage;
    private final Context context;
    private final RequestInterceptor requestInterceptor;
    private final RequestInterceptorFactory requestInterceptorFactory;
    private final SearchSuggestionServiceConfiguration searchSuggestionConfiguration;
    private final URL searchUrl;

    public SearchSuggestionServiceConfigurationProvider(Context context) {
        SearchSuggestionServiceConfigurationDefault searchSuggestionServiceConfigurationDefault;
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
            searchSuggestionServiceConfigurationDefault = null;
        } else {
            String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
            Intrinsics.checkNotNullExpressionValue(musicTerritoryOfResidence, "getMusicTerritoryOfResidence()");
            String deviceId = accountCredentialStorage.getDeviceId();
            Intrinsics.checkNotNullExpressionValue(deviceId, "accountCredentialStorage.deviceId");
            String deviceType = accountCredentialStorage.getDeviceType();
            Intrinsics.checkNotNullExpressionValue(deviceType, "accountCredentialStorage.deviceType");
            searchSuggestionServiceConfigurationDefault = new SearchSuggestionServiceConfigurationDefault(searchURL, requestInterceptorProvideRequestInterceptor, musicTerritoryOfResidence, deviceId, deviceType);
        }
        this.searchSuggestionConfiguration = searchSuggestionServiceConfigurationDefault;
    }

    /* JADX INFO: compiled from: SearchSuggestionServiceConfigurationProvider.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/find/dagger/SearchSuggestionServiceConfigurationProvider$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getSearchURL", "Ljava/net/URL;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final URL getSearchURL() {
            try {
                return SearchURLProvider.getSuggestionURL();
            } catch (Exception e) {
                Log.error(SearchSuggestionServiceConfigurationProvider.TAG, Intrinsics.stringPlus("Could not get Search suggestion URL: ", e.getMessage()));
                return null;
            }
        }
    }
}
