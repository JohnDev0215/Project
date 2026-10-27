package com.amazon.mp3.prime.browse;

import android.content.Context;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.environment.MusicURL;
import com.amazon.mp3.net.RequestInterceptorFactory;
import com.amazon.music.recommendation.Configuration;
import com.amazon.music.recommendation.DefaultRecommendationService;
import com.amazon.music.recommendation.Recommendation;
import com.amazon.music.recommendation.RecommendationService;
import com.amazon.music.recommendation.rx.RxRecommendation;

/* JADX INFO: loaded from: classes4.dex */
public final class RecommendationsFactory {
    public static final RxRecommendation createRxBrowseRecommendation(Context context) {
        AccountDetailUtil.get(context);
        return new RxRecommendation(createBrowseRecommendation(AccountDetailUtil.getMusicTerritoryOfResidence(), context.getApplicationContext()));
    }

    public static final Recommendation createBrowseRecommendation(final String musicTerritoryOfResidence, final Context context) {
        return new Recommendation(createBrowseRecommendationsService(context.getApplicationContext()), musicTerritoryOfResidence, context.getResources().getConfiguration().locale);
    }

    public static RecommendationService createBrowseRecommendationsService(final Context context) {
        return new DefaultRecommendationService(new Configuration.Builder().withRequestInterceptor(new RequestInterceptorFactory(context.getApplicationContext()).provideRequestInterceptor()).withRecommendationURL(MusicURL.getMuseUrl()).build());
    }
}
