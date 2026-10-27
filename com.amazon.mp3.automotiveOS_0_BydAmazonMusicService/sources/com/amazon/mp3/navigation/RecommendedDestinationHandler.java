package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.music.destination.RecommendedDestination;
import com.amazon.music.router.DestinationHandler;

/* JADX INFO: loaded from: classes3.dex */
public class RecommendedDestinationHandler implements DestinationHandler<RecommendedDestination> {
    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(final Context context, final RecommendedDestination recommendedDestination) {
        Intent recommendedIntent = PrimeActivityFactory.getRecommendedIntent(context);
        recommendedIntent.setFlags(268435456);
        context.startActivity(recommendedIntent);
    }
}
