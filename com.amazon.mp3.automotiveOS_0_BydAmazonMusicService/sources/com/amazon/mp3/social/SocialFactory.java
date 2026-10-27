package com.amazon.mp3.social;

import android.content.Context;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.net.RequestInterceptorFactory;
import com.amazon.music.social.Configuration;
import com.amazon.music.social.SocialServiceController;
import com.amazon.music.social.rx.RxSocialServiceController;

/* JADX INFO: loaded from: classes4.dex */
public final class SocialFactory {
    public static Configuration createConfiguration(final Context context) {
        RequestInterceptorFactory requestInterceptorFactory = new RequestInterceptorFactory(context.getApplicationContext());
        return new Configuration.Builder().withRequestInterceptor(requestInterceptorFactory.provideRequestInterceptor()).withMarketplace(AccountDetailUtil.get(context.getApplicationContext()).getHomeMarketPlace()).build();
    }

    public static SocialServiceController createSocialController(final Context context) {
        return new SocialServiceController(createConfiguration(context));
    }

    public static RxSocialServiceController createRxSocialController(final Context context) {
        return new RxSocialServiceController(createSocialController(context));
    }

    public static MusicRelationshipProvider createMusicRelationshipProvider(final Context context) {
        return new MusicRelationshipProviderImpl(context, createRxSocialController(context));
    }
}
