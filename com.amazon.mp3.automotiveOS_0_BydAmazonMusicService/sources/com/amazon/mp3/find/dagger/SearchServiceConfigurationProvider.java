package com.amazon.mp3.find.dagger;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.net.RequestInterceptorFactory;
import com.amazon.mp3.prime.browse.BrowseLanguageProvider;
import com.amazon.mp3.search.SearchURLProvider;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.search.SearchService;
import java.net.URL;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchServiceConfigurationProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0014\u001a\u0004\u0018\u00010\u0011R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/find/dagger/SearchServiceConfigurationProvider;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "accountCredentialStorage", "Lcom/amazon/mp3/account/credentials/AccountCredentialStorage;", "customerId", "", Splash.PARAMS_LOCALE, "Ljava/util/Locale;", "mricToken", "requestInterceptor", "Lcom/amazon/hermes/RequestInterceptor;", "requestInterceptorFactory", "Lcom/amazon/mp3/net/RequestInterceptorFactory;", "searchConfiguration", "Lcom/amazon/music/search/SearchService$Configuration;", "searchUrl", "Ljava/net/URL;", "getConfiguration", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchServiceConfigurationProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = SearchServiceConfigurationProvider.class.getSimpleName();
    private final AccountCredentialStorage accountCredentialStorage;
    private final Context context;
    private final String customerId;
    private final Locale locale;
    private final String mricToken;
    private final RequestInterceptor requestInterceptor;
    private final RequestInterceptorFactory requestInterceptorFactory;
    private final SearchService.Configuration searchConfiguration;
    private final URL searchUrl;

    public SearchServiceConfigurationProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        ChildUserUtil childUserUtil = ChildUserUtil.INSTANCE;
        boolean zIsChildUser = childUserUtil.isChildUser(context);
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
        Intrinsics.checkNotNullExpressionValue(accountCredentialStorage, "get(context)");
        this.accountCredentialStorage = accountCredentialStorage;
        Companion companion = INSTANCE;
        URL searchURL = companion.getSearchURL();
        this.searchUrl = searchURL;
        RequestInterceptorFactory requestInterceptorFactory = new RequestInterceptorFactory(context);
        this.requestInterceptorFactory = requestInterceptorFactory;
        RequestInterceptor requestInterceptorProvideRequestInterceptor = requestInterceptorFactory.provideRequestInterceptor();
        Intrinsics.checkNotNullExpressionValue(requestInterceptorProvideRequestInterceptor, "requestInterceptorFactor…ovideRequestInterceptor()");
        this.requestInterceptor = requestInterceptorProvideRequestInterceptor;
        Locale locale = new BrowseLanguageProvider().getLocale(context);
        Intrinsics.checkNotNullExpressionValue(locale, "BrowseLanguageProvider().getLocale(context)");
        this.locale = locale;
        String customerId = AccountDetailUtil.get(context).getCustomerId();
        this.customerId = customerId;
        SearchService.Configuration searchConfiguration = null;
        String musicRequestIdentityContextToken = zIsChildUser ? childUserUtil.getMusicRequestIdentityContextToken(context) : null;
        this.mricToken = musicRequestIdentityContextToken;
        if (searchURL != null) {
            String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
            Intrinsics.checkNotNullExpressionValue(musicTerritoryOfResidence, "getMusicTerritoryOfResidence()");
            String deviceId = accountCredentialStorage.getDeviceId();
            Intrinsics.checkNotNullExpressionValue(deviceId, "accountCredentialStorage.deviceId");
            String deviceType = accountCredentialStorage.getDeviceType();
            Intrinsics.checkNotNullExpressionValue(deviceType, "accountCredentialStorage.deviceType");
            searchConfiguration = companion.getSearchConfiguration(searchURL, requestInterceptorProvideRequestInterceptor, musicTerritoryOfResidence, deviceId, deviceType, locale, customerId, musicRequestIdentityContextToken);
        }
        this.searchConfiguration = searchConfiguration;
    }

    /* JADX INFO: compiled from: SearchServiceConfigurationProvider.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JL\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u0004H\u0002J\n\u0010\u0013\u001a\u0004\u0018\u00010\tH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/find/dagger/SearchServiceConfigurationProvider$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getSearchConfiguration", "Lcom/amazon/music/search/SearchService$Configuration;", "pSearchURL", "Ljava/net/URL;", "pRequestInterceptor", "Lcom/amazon/hermes/RequestInterceptor;", "pMusicTerritory", "pDeviceId", "pDeviceType", "pLanguageLocale", "Ljava/util/Locale;", "pCustomerId", "pMRICToken", "getSearchURL", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final URL getSearchURL() {
            try {
                return SearchURLProvider.getURL();
            } catch (Exception e) {
                Log.error(SearchServiceConfigurationProvider.TAG, Intrinsics.stringPlus("Could not get Search suggestion URL: ", e.getMessage()));
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final SearchService.Configuration getSearchConfiguration(final URL pSearchURL, final RequestInterceptor pRequestInterceptor, final String pMusicTerritory, final String pDeviceId, final String pDeviceType, final Locale pLanguageLocale, final String pCustomerId, final String pMRICToken) {
            return new SearchService.Configuration() { // from class: com.amazon.mp3.find.dagger.SearchServiceConfigurationProvider$Companion$getSearchConfiguration$1
                @Override // com.amazon.music.search.SearchService.Configuration
                @NonNull
                /* JADX INFO: renamed from: getTenzingTextSearchUrl, reason: from getter */
                public URL get$pSearchURL() {
                    return pSearchURL;
                }

                @Override // com.amazon.music.search.SearchService.Configuration
                @NonNull
                /* JADX INFO: renamed from: getRequestInterceptor, reason: from getter */
                public RequestInterceptor get$pRequestInterceptor() {
                    return pRequestInterceptor;
                }

                @Override // com.amazon.music.search.SearchService.Configuration
                @NonNull
                /* JADX INFO: renamed from: getLanguageLocale, reason: from getter */
                public Locale get$pLanguageLocale() {
                    return pLanguageLocale;
                }

                @Override // com.amazon.music.search.SearchService.Configuration
                @NonNull
                /* JADX INFO: renamed from: getMusicTerritory, reason: from getter */
                public String get$pMusicTerritory() {
                    return pMusicTerritory;
                }

                @Override // com.amazon.music.search.SearchService.Configuration
                @NonNull
                /* JADX INFO: renamed from: getDeviceId, reason: from getter */
                public String get$pDeviceId() {
                    return pDeviceId;
                }

                @Override // com.amazon.music.search.SearchService.Configuration
                @NonNull
                /* JADX INFO: renamed from: getDeviceType, reason: from getter */
                public String get$pDeviceType() {
                    return pDeviceType;
                }

                @Override // com.amazon.music.search.SearchService.Configuration
                @NonNull
                /* JADX INFO: renamed from: getCustomerId, reason: from getter */
                public String get$pCustomerId() {
                    return pCustomerId;
                }

                @Override // com.amazon.music.search.SearchService.Configuration
                @NonNull
                /* JADX INFO: renamed from: getMusicRequestIdentityContextToken, reason: from getter */
                public String get$pMRICToken() {
                    return pMRICToken;
                }
            };
        }
    }

    /* JADX INFO: renamed from: getConfiguration, reason: from getter */
    public final SearchService.Configuration getSearchConfiguration() {
        return this.searchConfiguration;
    }
}
