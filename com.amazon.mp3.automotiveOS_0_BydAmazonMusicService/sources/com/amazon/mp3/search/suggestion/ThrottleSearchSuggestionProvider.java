package com.amazon.mp3.search.suggestion;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.search.SearchURLProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.search.suggestion.HttpSearchSuggestionService;
import com.amazon.music.search.suggestion.SearchSuggestionServiceConfiguration;
import com.amazon.music.search.suggestion.ThrottleSearchSuggestion;
import java.net.URL;

/* JADX INFO: loaded from: classes4.dex */
public class ThrottleSearchSuggestionProvider {
    private static final String TAG = "ThrottleSearchSuggestionProvider";
    private final Context mContext;
    private URL mSearchURL;
    private SearchSuggestionServiceConfiguration mSuggestionServiceConfiguration;

    public ThrottleSearchSuggestionProvider(final Context context, final RequestInterceptor requestInterceptor) {
        this.mContext = context;
        final AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
        URL searchURL = getSearchURL();
        this.mSearchURL = searchURL;
        if (searchURL != null) {
            this.mSuggestionServiceConfiguration = new SearchSuggestionServiceConfiguration() { // from class: com.amazon.mp3.search.suggestion.ThrottleSearchSuggestionProvider.1
                @Override // com.amazon.music.search.suggestion.SearchSuggestionServiceConfiguration
                @NonNull
                /* JADX INFO: renamed from: getURL */
                public URL getSearchURL() {
                    return ThrottleSearchSuggestionProvider.this.mSearchURL;
                }

                @Override // com.amazon.music.search.suggestion.SearchSuggestionServiceConfiguration
                @NonNull
                public RequestInterceptor getRequestInterceptor() {
                    return requestInterceptor;
                }

                @Override // com.amazon.music.search.suggestion.SearchSuggestionServiceConfiguration
                @NonNull
                public String getMusicTerritory() {
                    return AccountDetailUtil.getMusicTerritoryOfResidence();
                }

                @Override // com.amazon.music.search.suggestion.SearchSuggestionServiceConfiguration
                @NonNull
                public String getDeviceId() {
                    return accountCredentialStorage.getDeviceId();
                }

                @Override // com.amazon.music.search.suggestion.SearchSuggestionServiceConfiguration
                @NonNull
                public String getDeviceType() {
                    return accountCredentialStorage.getDeviceType();
                }
            };
        }
    }

    private URL getSearchURL() {
        try {
            return SearchURLProvider.getSuggestionURL();
        } catch (Exception e) {
            Log.error(TAG, "Could not get Search suggestion URL: " + e.getMessage());
            return null;
        }
    }

    public ThrottleSearchSuggestion getThrottleSearchSuggestion() {
        if (this.mSuggestionServiceConfiguration == null) {
            return null;
        }
        return ThrottleSearchSuggestion.builder(new HttpSearchSuggestionService(this.mSuggestionServiceConfiguration)).build();
    }
}
