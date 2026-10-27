package com.amazon.mp3.language.preference;

import android.content.Context;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.net.RequestInterceptorFactory;
import com.amazon.mp3.prime.browse.BrowseLanguageProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.languagepreference.Configuration;
import com.amazon.music.languagepreference.LanguagePreference;
import com.amazon.music.subscription.BrowseMode;
import java.net.MalformedURLException;
import java.net.URL;

/* JADX INFO: loaded from: classes3.dex */
public class LanguagePreferenceProvider {
    private static final String TAG = "LanguagePreferenceProvider";

    public static LanguagePreference buildLanguagePreference(final Context context) {
        Configuration.RequestContent requestContent;
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
        if (new BrowseMode(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider()).getBrowseMode().equals(BrowseMode.Mode.PRIME)) {
            requestContent = Configuration.RequestContent.PRIME;
        } else {
            requestContent = Configuration.RequestContent.MUSIC_SUBSCRIPTION;
        }
        Configuration.RequestContent requestContent2 = requestContent;
        RequestInterceptor requestInterceptorProvideRequestInterceptor = new RequestInterceptorFactory(context).provideRequestInterceptor();
        BrowseLanguageProvider browseLanguageProvider = new BrowseLanguageProvider();
        try {
            return new LanguagePreference(new Configuration(context, new URL(Environment.STRATUS.get().toURL()), new URL(Environment.MUSE_URL.get().toURL() + "getAvailableContentLanguages"), requestInterceptorProvideRequestInterceptor, accountCredentialStorage.getDeviceId(), accountCredentialStorage.getDeviceType(), AccountDetailUtil.getMusicTerritoryOfResidence(), browseLanguageProvider.getLocale(context), requestContent2));
        } catch (MalformedURLException e) {
            Log.error(TAG, "Can't initialize language preference", e);
            return null;
        }
    }
}
