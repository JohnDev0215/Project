package com.amazon.mp3.recentlyplayed;

import android.content.Context;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.net.RequestInterceptorFactory;
import com.amazon.music.ContentAccessType;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.recents.Configuration;
import com.amazon.music.recents.RecentsController;
import com.amazon.music.subscription.BrowseMode;

/* JADX INFO: loaded from: classes4.dex */
public final class RecentsFactory {
    private static Configuration createConfiguration(final Context context) {
        return new Configuration(new RequestInterceptorFactory(context.getApplicationContext()).provideRequestInterceptor(), AccountDetailUtil.get(context.getApplicationContext()).getHomeMarketPlace(), new BrowseMode(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider()).getBrowseMode().equals(BrowseMode.Mode.PRIME) ? ContentAccessType.PRIME : ContentAccessType.HAWKFIRE);
    }

    public static RecentsController createRecentsController(final Context context) {
        return new RecentsController(createConfiguration(context));
    }
}
